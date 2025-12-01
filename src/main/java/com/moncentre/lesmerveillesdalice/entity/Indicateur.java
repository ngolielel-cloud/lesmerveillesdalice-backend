package com.moncentre.lesmerveillesdalice.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "indicateur")
public class Indicateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_indicateur")
    private Integer idIndicateur;

    @Column(nullable = false, length = 150)
    private String nom;

    @Column(nullable = false)
    private Double valeur;

    @Column(length = 20)
    private String unite;

    @ManyToOne
    @JoinColumn(name = "id_rapport")
    private Rapport rapport;

    // ======== GETTERS & SETTERS ========

    public Integer getIdIndicateur() {
        return idIndicateur;
    }

    public void setIdIndicateur(Integer idIndicateur) {
        this.idIndicateur = idIndicateur;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Double getValeur() {
        return valeur;
    }

    public void setValeur(Double valeur) {
        this.valeur = valeur;
    }

    public String getUnite() {
        return unite;
    }

    public void setUnite(String unite) {
        this.unite = unite;
    }

    public Rapport getRapport() {
        return rapport;
    }

    public void setRapport(Rapport rapport) {
        this.rapport = rapport;
    }
}