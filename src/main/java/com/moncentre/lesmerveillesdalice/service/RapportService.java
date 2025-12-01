package com.moncentre.lesmerveillesdalice.service;

import com.moncentre.lesmerveillesdalice.entity.Rapport;
import com.moncentre.lesmerveillesdalice.repository.RapportRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RapportService {

    private final RapportRepository repo;

    public RapportService(RapportRepository repo) {
        this.repo = repo;
    }

    // 🔹 Créer un rapport
    public Rapport create(Rapport rapport) {
        return repo.save(rapport);
    }

    // 🔹 Tout afficher
    public List<Rapport> getAll() {
        return repo.findAll();
    }

    // 🔹 Un seul rapport
    public Rapport getById(Integer id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Rapport introuvable"));
    }

    // 🔹 Recherche par type
    public List<Rapport> getByType(String type) {
        return repo.findByType(type);
    }

    // 🔹 Recherche par période
    public List<Rapport> getByPeriode(String periode) {
        return repo.findByPeriode(periode);
    }

    // 🔹 Mise à jour
    public Rapport update(Integer id, Rapport newData) {
        Rapport existant = getById(id);

        existant.setTitre(newData.getTitre());
        existant.setType(newData.getType());
        existant.setPeriode(newData.getPeriode());
        existant.setCommentaire(newData.getCommentaire());

        return repo.save(existant);
    }

    // 🔹 Suppression
    public void delete(Integer id) {
        repo.deleteById(id);
    }
}