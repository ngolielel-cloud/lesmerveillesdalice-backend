package com.moncentre.lesmerveillesdalice.mapper;

import com.moncentre.lesmerveillesdalice.dto.PersonnelDTO;
import com.moncentre.lesmerveillesdalice.entity.Personnel;
import com.moncentre.lesmerveillesdalice.entity.ServiceCentre;

public class PersonnelMapper {

    public static PersonnelDTO toDTO(Personnel p) {
        PersonnelDTO dto = new PersonnelDTO();

        dto.setId(p.getIdPersonnel());
        dto.setNom(p.getNom());
        dto.setPrenom(p.getPrenom());
        dto.setSexe(p.getSexe());
        dto.setRole(p.getRole());

        ServiceCentre s = p.getService();
        if (s != null) {
            dto.setIdService(s.getIdService());
            dto.setNomService(s.getNomService());
        }

        return dto;
    }
}