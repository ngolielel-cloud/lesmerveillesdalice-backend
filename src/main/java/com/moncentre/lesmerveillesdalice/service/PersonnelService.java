package com.moncentre.lesmerveillesdalice.service;

import com.moncentre.lesmerveillesdalice.dto.PersonnelDTO;
import com.moncentre.lesmerveillesdalice.entity.Personnel;
import com.moncentre.lesmerveillesdalice.entity.ServiceCentre;
import com.moncentre.lesmerveillesdalice.exception.ResourceNotFoundException;
import com.moncentre.lesmerveillesdalice.mapper.PersonnelMapper;
import com.moncentre.lesmerveillesdalice.repository.PersonnelRepository;
import com.moncentre.lesmerveillesdalice.repository.ServiceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonnelService {

    private final PersonnelRepository repo;
    private final ServiceRepository serviceRepo;

    public PersonnelService(PersonnelRepository repo, ServiceRepository serviceRepo) {
        this.repo = repo;
        this.serviceRepo = serviceRepo;
    }

    public List<PersonnelDTO> getAll() {
        return repo.findAll()
                .stream()
                .map(PersonnelMapper::toDTO)
                .toList();
    }

    public PersonnelDTO getById(Integer id) {
        Personnel p = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Personnel introuvable"));
        return PersonnelMapper.toDTO(p);
    }

    public PersonnelDTO create(Personnel p, Integer idService) {

        ServiceCentre service = serviceRepo.findById(idService)
                .orElseThrow(() -> new ResourceNotFoundException("Service introuvable"));

        p.setService(service);

        Personnel saved = repo.save(p);
        return PersonnelMapper.toDTO(saved);
    }

    public PersonnelDTO update(Integer id, Personnel p) {

        Personnel existant = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Personnel introuvable"));

        existant.setNom(p.getNom());
        existant.setPrenom(p.getPrenom());
        existant.setSexe(p.getSexe());
        existant.setRole(p.getRole());

        // changer service
        if (p.getService() != null) {
            ServiceCentre service = serviceRepo.findById(p.getService().getIdService())
                    .orElseThrow(() -> new ResourceNotFoundException("Service introuvable"));
            existant.setService(service);
        }

        Personnel saved = repo.save(existant);
        return PersonnelMapper.toDTO(saved);
    }

    public void delete(Integer id) {
        if (!repo.existsById(id)) {
            throw new ResourceNotFoundException("Personnel introuvable");
        }
        repo.deleteById(id);
    }
}