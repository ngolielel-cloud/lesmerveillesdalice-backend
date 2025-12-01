package com.moncentre.lesmerveillesdalice.mapper;

import com.moncentre.lesmerveillesdalice.dto.ClientDTO;
import com.moncentre.lesmerveillesdalice.entity.Client;
import com.moncentre.lesmerveillesdalice.entity.ServiceCentre;

public class ClientMapper {

    public static ClientDTO toDTO(Client c) {
        ClientDTO dto = new ClientDTO();
        dto.setId(c.getIdClient());
        dto.setNom(c.getNom());
        dto.setPrenom(c.getPrenom());
        dto.setTelephone(c.getTelephone());
        dto.setEmail(c.getEmail());
        dto.setSexe(c.getSexe());
        dto.setCodeSecret(c.getCodeSecret());

        if (c.getService() != null) {
            dto.setServiceId(c.getService().getIdService());
            dto.setNomService(c.getService().getNomService());
        }

        return dto;
    }

    public static Client toEntity(ClientDTO dto, ServiceCentre service) {
        Client c = new Client();

        if (dto.getId() != null) {
            c.setIdClient(dto.getId());
        }

        c.setNom(dto.getNom());
        c.setPrenom(dto.getPrenom());
        c.setTelephone(dto.getTelephone());
        c.setEmail(dto.getEmail());
        c.setSexe(dto.getSexe());
        c.setCodeSecret(dto.getCodeSecret());
        c.setService(service);

        return c;
    }
}