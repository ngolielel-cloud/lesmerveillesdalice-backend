package com.moncentre.lesmerveillesdalice.service;

import com.moncentre.lesmerveillesdalice.entity.Paiement;
import com.moncentre.lesmerveillesdalice.entity.Recu;
import com.moncentre.lesmerveillesdalice.repository.PaiementRepository;
import com.moncentre.lesmerveillesdalice.repository.RecuRepository;
import org.springframework.stereotype.Service;

@Service
public class RecuService {

    private final RecuRepository repo;
    private final PaiementRepository paiementRepo;

    public RecuService(RecuRepository repo, PaiementRepository paiementRepo) {
        this.repo = repo;
        this.paiementRepo = paiementRepo;
    }

    public Recu genererRecu(Integer idPaiement, byte[] fichier) {

        Paiement paiement = paiementRepo.findById(idPaiement)
                .orElseThrow(() -> new RuntimeException("Paiement introuvable"));

        // Génération du numéro du reçu
        String numero = "REC-" + System.currentTimeMillis();

        Recu recu = new Recu();
        recu.setNumeroRecu(numero);
        recu.setPaiement(paiement);
        recu.setFichier(fichier);

        return repo.save(recu);
    }

    public Recu getById(Integer id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Reçu introuvable"));
    }
}