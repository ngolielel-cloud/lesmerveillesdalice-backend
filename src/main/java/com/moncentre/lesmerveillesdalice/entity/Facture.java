package com.moncentre.lesmerveillesdalice.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "facture")
public class Facture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_facture")
    private Integer idFacture;

    private Float montant;

    @Column(name = "date_emission")
    private LocalDateTime dateEmission = LocalDateTime.now();

    private String statut;

    // -------- RELATIONS --------

    @ManyToOne
    @JoinColumn(name = "id_client")
    private Client client;

    @OneToOne
    @JoinColumn(name = "id_prestation", unique = true)
    private Prestation prestation;

    @OneToOne(mappedBy = "facture", cascade = CascadeType.ALL)
    private Paiement paiement;

    // -------- GETTERS & SETTERS --------

    public Integer getIdFacture() { return idFacture; }
    public void setIdFacture(Integer idFacture) { this.idFacture = idFacture; }

    public Float getMontant() { return montant; }
    public void setMontant(Float montant) { this.montant = montant; }

    public LocalDateTime getDateEmission() { return dateEmission; }
    public void setDateEmission(LocalDateTime dateEmission) { this.dateEmission = dateEmission; }

    public String getStatut() { return statut; }
    public void setStatut(String statut) { this.statut = statut; }

    public Client getClient() { return client; }
    public void setClient(Client client) { this.client = client; }

    public Prestation getPrestation() { return prestation; }
    public void setPrestation(Prestation prestation) { this.prestation = prestation; }

    public Paiement getPaiement() { return paiement; }
    public void setPaiement(Paiement paiement) { this.paiement = paiement; }
}