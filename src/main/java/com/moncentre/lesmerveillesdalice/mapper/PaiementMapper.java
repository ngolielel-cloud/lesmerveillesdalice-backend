package com.moncentre.lesmerveillesdalice.mapper;

import com.moncentre.lesmerveillesdalice.dto.PaiementDTO;
import com.moncentre.lesmerveillesdalice.entity.Paiement;

public class PaiementMapper {

    public static PaiementDTO toDTO(Paiement p) {

        PaiementDTO dto = new PaiementDTO();

        dto.setIdPaiement(p.getIdPaiement());
        dto.setMode(p.getMode());
        dto.setDatePaiement(p.getDatePaiement());

        if (p.getFacture() != null) {
            dto.setIdFacture(p.getFacture().getIdFacture());
            dto.setMontantFacture(p.getFacture().getMontant());
        }

        return dto;
    }
}