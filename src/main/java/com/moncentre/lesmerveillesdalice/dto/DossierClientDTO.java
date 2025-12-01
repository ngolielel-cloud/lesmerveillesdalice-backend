package com.moncentre.lesmerveillesdalice.dto;

import java.time.LocalDateTime;

public class DossierClientDTO {

    private Integer id;
    private LocalDateTime dateCreation;
    private String statut;
    private String historique;

    private Integer idClient;
    private String nomClient;
    private String prenomClient;
    private String telephone;
    private String sexe;

    // GETTERS & SETTERS

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public LocalDateTime getDateCreation() { return dateCreation; }
    public void setDateCreation(LocalDateTime dateCreation) { this.dateCreation = dateCreation; }

    public String getStatut() { return statut; }
    public void setStatut(String statut) { this.statut = statut; }

    public String getHistorique() { return historique; }
    public void setHistorique(String historique) { this.historique = historique; }

    public Integer getIdClient() { return idClient; }
    public void setIdClient(Integer idClient) { this.idClient = idClient; }

    public String getNomClient() { return nomClient; }
    public void setNomClient(String nomClient) { this.nomClient = nomClient; }

    public String getPrenomClient() { return prenomClient; }
    public void setPrenomClient(String prenomClient) { this.prenomClient = prenomClient; }

    public String getTelephone() { return telephone; }
    public void setTelephone(String telephone) { this.telephone = telephone; }

    public String getSexe() { return sexe; }
    public void setSexe(String sexe) { this.sexe = sexe; }
}