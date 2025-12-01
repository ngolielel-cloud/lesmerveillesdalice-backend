package com.moncentre.lesmerveillesdalice.dto;

import java.time.LocalDateTime;

public class PaiementDTO {

    private Integer idPaiement;
    private String mode;
    private LocalDateTime datePaiement;

    private Integer idFacture;
    private Float montantFacture;

    // --- GETTERS & SETTERS ---

    public Integer getIdPaiement() { return idPaiement; }
    public void setIdPaiement(Integer idPaiement) { this.idPaiement = idPaiement; }

    public String getMode() { return mode; }
    public void setMode(String mode) { this.mode = mode; }

    public LocalDateTime getDatePaiement() { return datePaiement; }
    public void setDatePaiement(LocalDateTime datePaiement) { this.datePaiement = datePaiement; }

    public Integer getIdFacture() { return idFacture; }
    public void setIdFacture(Integer idFacture) { this.idFacture = idFacture; }

    public Float getMontantFacture() { return montantFacture; }
    public void setMontantFacture(Float montantFacture) { this.montantFacture = montantFacture; }
}