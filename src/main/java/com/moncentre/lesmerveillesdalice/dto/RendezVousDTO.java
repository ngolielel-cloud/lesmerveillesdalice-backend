package com.moncentre.lesmerveillesdalice.dto;

import java.time.LocalDateTime;

public class RendezVousDTO {

    private Integer id;
    private LocalDateTime dateHeure;
    private String motif;
    private String statut;

    // ⭐ Ajout du code secret
    private String code;

    // Infos client
    private Integer idClient;
    private String nomClient;

    // Infos personnel
    private Integer idPersonnel;
    private String nomPersonnel;

    // Infos service
    private Integer idService;
    private String nomService;

    // Getters / Setters

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public LocalDateTime getDateHeure() { return dateHeure; }
    public void setDateHeure(LocalDateTime dateHeure) { this.dateHeure = dateHeure; }

    public String getMotif() { return motif; }
    public void setMotif(String motif) { this.motif = motif; }

    public String getStatut() { return statut; }
    public void setStatut(String statut) { this.statut = statut; }

    // ⭐ CODE SECRET
    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

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