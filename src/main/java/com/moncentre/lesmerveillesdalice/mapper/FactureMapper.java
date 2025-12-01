package com.moncentre.lesmerveillesdalice.mapper;

import com.moncentre.lesmerveillesdalice.dto.FactureDTO;
import com.moncentre.lesmerveillesdalice.entity.Facture;
import com.moncentre.lesmerveillesdalice.entity.Paiement;

public class FactureMapper {

    public static FactureDTO toDTO(Facture f) {

        FactureDTO dto = new FactureDTO();

        dto.setIdFacture(f.getIdFacture());
        dto.setMontant(f.getMontant());
        dto.setDateEmission(f.getDateEmission());
        dto.setStatut(f.getStatut());

        // Client
        if (f.getClient() != null) {
            dto.setIdClient(f.getClient().getIdClient());
            dto.setNomClient(f.getClient().getNom());
        }

        // Prestation
        if (f.getPrestation() != null) {
            dto.setIdPrestation(f.getPrestation().getIdPrestation());
            dto.setTypePrestation(f.getPrestation().getType());
        }

        // Paiement
        Paiement p = f.getPaiement();
        if (p != null) {
            dto.setIdPaiement(p.getIdPaiement());
            dto.setModePaiement(p.getMode());
        }

        return dto;
    }
}