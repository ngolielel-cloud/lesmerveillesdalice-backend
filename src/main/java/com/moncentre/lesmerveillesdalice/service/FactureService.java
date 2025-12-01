package com.moncentre.lesmerveillesdalice.service;

import com.moncentre.lesmerveillesdalice.dto.FactureDTO;
import com.moncentre.lesmerveillesdalice.entity.Client;
import com.moncentre.lesmerveillesdalice.entity.Facture;
import com.moncentre.lesmerveillesdalice.entity.Prestation;
import com.moncentre.lesmerveillesdalice.mapper.FactureMapper;
import com.moncentre.lesmerveillesdalice.repository.ClientRepository;
import com.moncentre.lesmerveillesdalice.repository.FactureRepository;
import com.moncentre.lesmerveillesdalice.repository.PrestationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FactureService {

    private final FactureRepository repo;
    private final ClientRepository clientRepo;
    private final PrestationRepository prestationRepo;

    public FactureService(FactureRepository repo,
                          ClientRepository clientRepo,
                          PrestationRepository prestationRepo) {
        this.repo = repo;
        this.clientRepo = clientRepo;
        this.prestationRepo = prestationRepo;
    }

    // CRÉER une facture
    public FactureDTO create(Facture facture, Integer idClient, Integer idPrestation) {

        Client client = clientRepo.findById(idClient)
                .orElseThrow(() -> new RuntimeException("Client introuvable"));

        Prestation prestation = prestationRepo.findById(idPrestation)
                .orElseThrow(() -> new RuntimeException("Prestation introuvable"));

        facture.setClient(client);
        facture.setPrestation(prestation);

        Facture saved = repo.save(facture);

        return FactureMapper.toDTO(saved);
    }

    // Récupérer par ID
    public FactureDTO getById(Integer id) {
        Facture f = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Facture introuvable"));
        return FactureMapper.toDTO(f);
    }

    // Liste de toutes les factures
    public List<FactureDTO> getAll() {
        return repo.findAll().stream()
                .map(FactureMapper::toDTO)
                .toList();
    }
}