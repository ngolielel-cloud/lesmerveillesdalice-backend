package com.moncentre.lesmerveillesdalice.service;

import com.moncentre.lesmerveillesdalice.dto.ServiceDTO;
import com.moncentre.lesmerveillesdalice.entity.ServiceCentre;
import com.moncentre.lesmerveillesdalice.exception.ResourceNotFoundException;
import com.moncentre.lesmerveillesdalice.mapper.ServiceMapper;
import com.moncentre.lesmerveillesdalice.repository.ServiceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceCentreService {

    private final ServiceRepository repo;

    public ServiceCentreService(ServiceRepository repo) {
        this.repo = repo;
    }

    // 🔹 Liste de tous les services
    public List<ServiceDTO> getAll() {
        return repo.findAll()
                .stream()
                .map(ServiceMapper::toDTO)
                .toList();
    }

    // 🔹 Un service par ID
    public ServiceDTO getById(Integer id) {
        ServiceCentre s = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Service introuvable"));
        return ServiceMapper.toDTO(s);
    }

    // 🔥 🔥 🔥 Récupérer les services par catégorie (Kiné, Massage, Spa, Sport)
    public List<ServiceDTO> getByCategorie(String categorie) {
        return repo.findByCategorie(categorie)
                .stream()
                .map(ServiceMapper::toDTO)
                .toList();
    }

    // 🔹 Création
    public ServiceDTO create(ServiceCentre s) {

        if (s.getPrix() != null && s.getPrix() < 0) {
            throw new RuntimeException("Le prix ne peut pas être négatif");
        }

        ServiceCentre saved = repo.save(s);
        return ServiceMapper.toDTO(saved);
    }

    // 🔹 Mise à jour
    public ServiceDTO update(Integer id, ServiceCentre s) {

        ServiceCentre existant = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Service introuvable"));

        existant.setNomService(s.getNomService());
        existant.setCategorie(s.getCategorie());
        existant.setPrix(s.getPrix());
        existant.setDescription(s.getDescription());

        ServiceCentre saved = repo.save(existant);
        return ServiceMapper.toDTO(saved);
    }

    // 🔹 Suppression
    public void delete(Integer id) {
        if (!repo.existsById(id)) {
            throw new ResourceNotFoundException("Service introuvable");
        }
        repo.deleteById(id);
    }
}