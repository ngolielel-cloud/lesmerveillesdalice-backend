package com.moncentre.lesmerveillesdalice.mapper;

import com.moncentre.lesmerveillesdalice.dto.DossierClientDTO;
import com.moncentre.lesmerveillesdalice.entity.DossierClient;

public class DossierClientMapper {

    public static DossierClientDTO toDTO(DossierClient d) {

        DossierClientDTO dto = new DossierClientDTO();

        dto.setId(d.getId());
        dto.setDateCreation(d.getDateCreation());
        dto.setStatut(d.getStatut());
        dto.setHistorique(d.getHistorique());

        dto.setIdClient(d.getClient().getIdClient());
        dto.setNomClient(d.getClient().getNom());
        dto.setPrenomClient(d.getClient().getPrenom());
        dto.setTelephone(d.getClient().getTelephone());
        dto.setSexe(d.getClient().getSexe());

        return dto;
    }
}