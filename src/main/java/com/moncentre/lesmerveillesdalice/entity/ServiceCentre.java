package com.moncentre.lesmerveillesdalice.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "service_centre")
public class ServiceCentre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_service")
    private Integer idService;

    @Column(name = "nom_service", nullable = false)
    private String nomService;   // Massage, Kinésithérapie...

    @Column(name = "categorie")
    private String categorie;    // Bien-être, Sport, Rééducation...

    @Column(name = "prix")
    private Double prix;         // Prix moyen

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;  // ce que le service offre : consultation, séance, cavitation, gommage, etc.

    // ====== GETTERS & SETTERS ======

    public Integer getIdService() {
        return idService;
    }

    public void setIdService(Integer idService) {
        this.idService = idService;
    }

    public String getNomService() {
        return nomService;
    }

    public void setNomService(String nomService) {
        this.nomService = nomService;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}