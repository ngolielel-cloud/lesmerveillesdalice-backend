package com.moncentre.lesmerveillesdalice.dto;

import java.time.LocalDateTime;

public class FactureDTO {

    private Integer idFacture;
    private Float montant;
    private LocalDateTime dateEmission;
    private String statut;

    private Integer idClient;
    private String nomClient;

    private Integer idPrestation;
    private String typePrestation;

    private Integer idPaiement;
    private String modePaiement;

    // ---- GETTERS & SETTERS ----

    public Integer getIdFacture() { return idFacture; }
    public void setIdFacture(Integer idFacture) { this.idFacture = idFacture; }

    public Float getMontant() { return montant; }
    public void setMontant(Float montant) { this.montant = montant; }

    public LocalDateTime getDateEmission() { return dateEmission; }
    public void setDateEmission(LocalDateTime dateEmission) { this.dateEmission = dateEmission; }

    public String getStatut() { return statut; }
    public void setStatut(String statut) { this.statut = statut; }

    public Integer getIdClient() { return idClient; }
    public void setIdClient(Integer idClient) { this.idClient = idClient; }

    public String getNomClient() { return nomClient; }
    public void setNomClient(String nomClient) { this.nomClient = nomClient; }

    public Integer getIdPrestation() { return idPrestation; }
    public void setIdPrestation(Integer idPrestation) { this.idPrestation = idPrestation; }

    public String getTypePrestation() { return typePrestation; }
    public void setTypePrestation(String typePrestation) { this.typePrestation = typePrestation; }

    public Integer getIdPaiement() { return idPaiement; }
    public void setIdPaiement(Integer idPaiement) { this.idPaiement = idPaiement; }

    public String getModePaiement() { return modePaiement; }
    public void setModePaiement(String modePaiement) { this.modePaiement = modePaiement; }
}