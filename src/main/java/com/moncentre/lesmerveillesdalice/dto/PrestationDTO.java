package com.moncentre.lesmerveillesdalice.dto;

import java.time.LocalDateTime;

public class PrestationDTO {

    private Integer idPrestation;
    private String type;
    private String statut;
    private LocalDateTime datePrestation;
    private String historique;

    private Integer idClient;
    private String nomClient;

    private Integer idPersonnel;
    private String nomPersonnel;

    private Integer idService;
    private String nomService;

    // Getters & Setters
    public Integer getIdPrestation() { return idPrestation; }
    public void setIdPrestation(Integer idPrestation) { this.idPrestation = idPrestation; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getStatut() { return statut; }
    public void setStatut(String statut) { this.statut = statut; }

    public LocalDateTime getDatePrestation() { return datePrestation; }
    public void setDatePrestation(LocalDateTime datePrestation) { this.datePrestation = datePrestation; }

    public String getHistorique() { return historique; }
    public void setHistorique(String historique) { this.historique = historique; }

    public Integer getIdClient() { return idClient; }
    public void setIdClient(Integer idClient) { this.idClient = idClient; }

    public String getNomClient() { return nomClient; }
    public void setNomClient(String nomClient) { this.nomClient = nomClient; }

    public Integer getIdPersonnel() { return idPersonnel; }
    public void setIdPersonnel(Integer idPersonnel) { this.idPersonnel = idPersonnel; }

    public String getNomPersonnel() { return nomPersonnel; }
    public void setNomPersonnel(String nomPersonnel) { this.nomPersonnel = nomPersonnel; }

    public Integer getIdService() { return idService; }
    public void setIdService(Integer idService) { this.idService = idService; }

    public String getNomService() { return nomService; }
    public void setNomService(String nomService) { this.nomService = nomService; }
}