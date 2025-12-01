package com.moncentre.lesmerveillesdalice.mapper;

import com.moncentre.lesmerveillesdalice.dto.PrestationDTO;
import com.moncentre.lesmerveillesdalice.entity.Prestation;

public class PrestationMapper {

    public static PrestationDTO toDTO(Prestation p) {
        PrestationDTO dto = new PrestationDTO();

        dto.setIdPrestation(p.getIdPrestation());
        dto.setType(p.getType());
        dto.setStatut(p.getStatut());
        dto.setDatePrestation(p.getDatePrestation());
        dto.setHistorique(p.getHistorique());

        if (p.getClient() != null) {
            dto.setIdClient(p.getClient().getIdClient());
            dto.setNomClient(p.getClient().getNom());
        }

        if (p.getPersonnel() != null) {
            dto.setIdPersonnel(p.getPersonnel().getIdPersonnel());
            dto.setNomPersonnel(p.getPersonnel().getNom());
        }

        if (p.getService() != null) {
            dto.setIdService(p.getService().getIdService());
            dto.setNomService(p.getService().getNomService());
        }

        return dto;
    }
}