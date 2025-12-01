package com.moncentre.lesmerveillesdalice.service;

import com.moncentre.lesmerveillesdalice.dto.PaiementDTO;
import com.moncentre.lesmerveillesdalice.entity.Facture;
import com.moncentre.lesmerveillesdalice.entity.Paiement;
import com.moncentre.lesmerveillesdalice.mapper.PaiementMapper;
import com.moncentre.lesmerveillesdalice.repository.FactureRepository;
import com.moncentre.lesmerveillesdalice.repository.PaiementRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaiementService {

    private final PaiementRepository repo;
    private final FactureRepository factureRepo;

    public PaiementService(PaiementRepository repo, FactureRepository factureRepo) {
        this.repo = repo;
        this.factureRepo = factureRepo;
    }

    // ✔ Enregistrer paiement
    public PaiementDTO create(Paiement paiement, Integer idFacture) {

        Facture facture = factureRepo.findById(idFacture)
                .orElseThrow(() -> new RuntimeException("Facture introuvable"));

        if (facture.getPaiement() != null) {
            throw new RuntimeException("Cette facture est déjà payée !");
        }

        paiement.setFacture(facture);
        facture.setStatut("Payée");

        Paiement saved = repo.save(paiement);
        factureRepo.save(facture);

        return PaiementMapper.toDTO(saved);
    }

    // ✔ Lire par ID
    public PaiementDTO getById(Integer id) {
        Paiement p = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Paiement introuvable"));
        return PaiementMapper.toDTO(p);
    }

    // ✔ Liste
    public List<PaiementDTO> getAll() {
        return repo.findAll()
                .stream()
                .map(PaiementMapper::toDTO)
                .toList();
    }
}