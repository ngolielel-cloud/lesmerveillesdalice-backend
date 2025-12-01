package com.moncentre.lesmerveillesdalice.mapper;

import com.moncentre.lesmerveillesdalice.dto.RendezVousDTO;
import com.moncentre.lesmerveillesdalice.entity.RendezVous;

public class RendezVousMapper {

    public static RendezVousDTO toDTO(RendezVous rdv) {

        RendezVousDTO dto = new RendezVousDTO();

        dto.setId(rdv.getIdRendezVous());
        dto.setDateHeure(rdv.getDateHeure());
        dto.setMotif(rdv.getMotif());
        dto.setStatut(rdv.getStatut());

        // ⭐ Code secret
        dto.setCode(rdv.getCode());

        // CLIENT
        if (rdv.getClient() != null) {
            dto.setIdClient(rdv.getClient().getIdClient());
            dto.setNomClient(rdv.getClient().getNom());
        }

        // PERSONNEL
        if (rdv.getPersonnel() != null) {
            dto.setIdPersonnel(rdv.getPersonnel().getIdPersonnel());
            dto.setNomPersonnel(rdv.getPersonnel().getNom());
        }

        // SERVICE
        if (rdv.getService() != null) {
            dto.setIdService(rdv.getService().getIdService());
            dto.setNomService(rdv.getService().getNomService());
        }

        return dto;
    }
}