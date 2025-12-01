package com.moncentre.lesmerveillesdalice.mapper;

import com.moncentre.lesmerveillesdalice.dto.ServiceDTO;
import com.moncentre.lesmerveillesdalice.entity.ServiceCentre;

public class ServiceMapper {

    public static ServiceDTO toDTO(ServiceCentre s) {
        ServiceDTO dto = new ServiceDTO();

        dto.setId(s.getIdService());
        dto.setNomService(s.getNomService());
        dto.setCategorie(s.getCategorie());
        dto.setPrix(s.getPrix());
        dto.setDescription(s.getDescription());

        return dto;
    }
}