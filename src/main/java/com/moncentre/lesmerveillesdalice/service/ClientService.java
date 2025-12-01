package com.moncentre.lesmerveillesdalice.service;

import com.moncentre.lesmerveillesdalice.dto.ClientDTO;
import com.moncentre.lesmerveillesdalice.dto.ClientStatsDTO;
import com.moncentre.lesmerveillesdalice.entity.Client;
import com.moncentre.lesmerveillesdalice.entity.DossierClient;
import com.moncentre.lesmerveillesdalice.entity.ServiceCentre;
import com.moncentre.lesmerveillesdalice.mapper.ClientMapper;
import com.moncentre.lesmerveillesdalice.repository.ClientRepository;
import com.moncentre.lesmerveillesdalice.repository.DossierClientRepository;
import com.moncentre.lesmerveillesdalice.repository.ServiceCentreRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    private final ClientRepository repo;
    private final ServiceCentreRepository serviceRepo;
    private final DossierClientRepository dossierRepo;

    public ClientService(
        ClientRepository repo,
        ServiceCentreRepository serviceRepo,
        DossierClientRepository dossierRepo
    ) {
        this.repo = repo;
        this.serviceRepo = serviceRepo;
        this.dossierRepo = dossierRepo;
    }

    public List<ClientDTO> getAll() {
        return repo.findAll()
                .stream()
                .map(ClientMapper::toDTO)
                .toList();
    }

    public ClientDTO getById(Integer id) {
        return repo.findById(id)
                .map(ClientMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Client introuvable"));
    }

    public ClientDTO create(ClientDTO dto) {

        ServiceCentre sc = dto.getServiceId() != null
                ? serviceRepo.findById(dto.getServiceId()).orElse(null)
                : null;

        Client c = ClientMapper.toEntity(dto, sc);
        c = repo.save(c);

        // 🔥 Création automatique d’un dossier vide
        DossierClient dossier = new DossierClient();
        dossier.setClient(c);
        dossier.setStatut("En cours");
        dossierRepo.save(dossier);

        return ClientMapper.toDTO(c);
    }

    public ClientDTO update(Integer id, ClientDTO dto) {

        Client c = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Client introuvable"));

        c.setNom(dto.getNom());
        c.setPrenom(dto.getPrenom());
        c.setEmail(dto.getEmail());
        c.setTelephone(dto.getTelephone());
        c.setSexe(dto.getSexe());

        if (dto.getServiceId() != null) {
            ServiceCentre sc = serviceRepo.findById(dto.getServiceId()).orElse(null);
            c.setService(sc);
        }

        return ClientMapper.toDTO(repo.save(c));
    }

    public void delete(Integer id) {
        repo.deleteById(id);
    }

    public List<ClientDTO> getByService(String serviceName) {

        Integer id = switch (serviceName.toLowerCase()) {
            case "kine" -> 1;
            case "massage" -> 2;
            case "spa" -> 3;
            case "sport" -> 4;
            default -> null;
        };

        if (id == null) return List.of();

        return repo.findByService_IdService(id)
                .stream()
                .map(ClientMapper::toDTO)
                .toList();
    }

    public ClientStatsDTO stats() {
        long kine = repo.findByService_IdService(1).size();
        long massage = repo.findByService_IdService(2).size();
        long spa = repo.findByService_IdService(3).size();
        long sport = repo.findByService_IdService(4).size();

        return new ClientStatsDTO(kine, massage, spa, sport);
    }
}