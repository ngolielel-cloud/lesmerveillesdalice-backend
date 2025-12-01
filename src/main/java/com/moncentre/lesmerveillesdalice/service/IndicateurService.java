package com.moncentre.lesmerveillesdalice.service;

import com.moncentre.lesmerveillesdalice.entity.Indicateur;
import com.moncentre.lesmerveillesdalice.entity.Rapport;
import com.moncentre.lesmerveillesdalice.repository.IndicateurRepository;
import com.moncentre.lesmerveillesdalice.repository.RapportRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IndicateurService {

    private final IndicateurRepository repo;
    private final RapportRepository rapportRepo;

    public IndicateurService(IndicateurRepository repo, RapportRepository rapportRepo) {
        this.repo = repo;
        this.rapportRepo = rapportRepo;
    }

    // 🔹 Créer un indicateur
    public Indicateur create(Indicateur indicateur, Integer idRapport) {
        Rapport rapport = rapportRepo.findById(idRapport)
                .orElseThrow(() -> new RuntimeException("Rapport introuvable"));

        indicateur.setRapport(rapport);
        return repo.save(indicateur);
    }

    // 🔹 Lire tout
    public List<Indicateur> getAll() {
        return repo.findAll();
    }

    // 🔹 Par ID
    public Indicateur getById(Integer id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Indicateur introuvable"));
    }

    // 🔹 Par rapport
    public List<Indicateur> getByRapport(Integer idRapport) {
        Rapport rapport = rapportRepo.findById(idRapport)
                .orElseThrow(() -> new RuntimeException("Rapport introuvable"));
        return repo.findByRapport(rapport);
    }

    // 🔹 Mise à jour
    public Indicateur update(Integer id, Indicateur newData) {
        Indicateur existant = getById(id);

        existant.setNom(newData.getNom());
        existant.setValeur(newData.getValeur());
        existant.setUnite(newData.getUnite());

        return repo.save(existant);
    }

    // 🔹 Supprimer
    public void delete(Integer id) {
        repo.deleteById(id);
    }
}