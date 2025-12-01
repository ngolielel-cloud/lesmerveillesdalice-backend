package com.moncentre.lesmerveillesdalice.service;

import com.moncentre.lesmerveillesdalice.dto.PrestationDTO;
import com.moncentre.lesmerveillesdalice.entity.*;
import com.moncentre.lesmerveillesdalice.exception.ResourceNotFoundException;
import com.moncentre.lesmerveillesdalice.mapper.PrestationMapper;
import com.moncentre.lesmerveillesdalice.repository.*;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PrestationService {

    private final PrestationRepository repo;
    private final ClientRepository clientRepo;
    private final PersonnelRepository personnelRepo;
    private final ServiceRepository serviceRepo;

    public PrestationService(
            PrestationRepository repo,
            ClientRepository clientRepo,
            PersonnelRepository personnelRepo,
            ServiceRepository serviceRepo
    ) {
        this.repo = repo;
        this.clientRepo = clientRepo;
        this.personnelRepo = personnelRepo;
        this.serviceRepo = serviceRepo;
    }

    // 🔵 CREATE
    public PrestationDTO create(Prestation p, Integer idClient, Integer idPersonnel, Integer idService) {

        Client client = clientRepo.findById(idClient)
                .orElseThrow(() -> new ResourceNotFoundException("Client introuvable"));

        Personnel personnel = personnelRepo.findById(idPersonnel)
                .orElseThrow(() -> new ResourceNotFoundException("Personnel introuvable"));

        ServiceCentre service = serviceRepo.findById(idService)
                .orElseThrow(() -> new ResourceNotFoundException("Service introuvable"));

        p.setClient(client);
        p.setPersonnel(personnel);
        p.setService(service);

        if (p.getDatePrestation() == null) {
            p.setDatePrestation(LocalDateTime.now());
        }

        if (p.getStatut() == null || p.getStatut().isEmpty()) {
            p.setStatut("En_cours");
        }

        Prestation saved = repo.save(p);
        return PrestationMapper.toDTO(saved);
    }

    // 🔵 TOUTES LES PRESTATIONS
    public List<PrestationDTO> getAll() {
        return repo.findAll()
                .stream()
                .map(PrestationMapper::toDTO)
                .toList();
    }

    // 🔵 PAR CLIENT
    public List<PrestationDTO> getByClient(Integer idClient) {
        Client c = clientRepo.findById(idClient)
                .orElseThrow(() -> new ResourceNotFoundException("Client introuvable"));

        return repo.findByClient(c)
                .stream()
                .map(PrestationMapper::toDTO)
                .toList();
    }

    // 🔵 PAR PERSONNEL
    public List<PrestationDTO> getByPersonnel(Integer idPersonnel) {
        Personnel p = personnelRepo.findById(idPersonnel)
                .orElseThrow(() -> new ResourceNotFoundException("Personnel introuvable"));

        return repo.findByPersonnel(p)
                .stream()
                .map(PrestationMapper::toDTO)
                .toList();
    }

    public List<PrestationDTO> getByService(Integer idService) {
        ServiceCentre service = serviceRepo.findById(idService)
            .orElseThrow(() -> new ResourceNotFoundException("Service introuvable"));

        return repo.findByService(service)
            .stream().map(PrestationMapper::toDTO).toList();
    }
    
    // 🔵 UPDATE
    public PrestationDTO update(Integer id, Prestation data) {
        Prestation p = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Prestation introuvable"));

        p.setType(data.getType());
        p.setHistorique(data.getHistorique());

        if (data.getDatePrestation() != null) {
            p.setDatePrestation(data.getDatePrestation());
        }

        return PrestationMapper.toDTO(repo.save(p));
    }

    // 🔴 DELETE
    public void delete(Integer id) {
        if (!repo.existsById(id)) {
            throw new ResourceNotFoundException("Prestation introuvable");
        }
        repo.deleteById(id);
    }

    // 🔵 TERMINER
    public PrestationDTO terminer(Integer id) {
        Prestation p = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Prestation introuvable"));

        p.setStatut("Termine");
        return PrestationMapper.toDTO(repo.save(p));
    }
}