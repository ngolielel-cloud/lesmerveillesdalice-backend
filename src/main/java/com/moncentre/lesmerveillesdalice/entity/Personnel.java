package com.moncentre.lesmerveillesdalice.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "personnel")
public class Personnel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_personnel")
    private Integer idPersonnel;

    @Column(name = "nom", nullable = false)
    private String nom;

    @Column(name = "prenom", nullable = false)
    private String prenom;

    @Column(name = "sexe", length = 1)  // M / F
    private String sexe;

    @Column(name = "role", nullable = false)
    private String role;

    // 🔥 Relation : un personnel travaille dans un service
    @ManyToOne
    @JoinColumn(name = "id_service")
    private ServiceCentre service;

    // ======== GETTERS & SETTERS ========

    public Integer getIdPersonnel() {
        return idPersonnel;
    }

    public void setIdPersonnel(Integer idPersonnel) {
        this.idPersonnel = idPersonnel;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public ServiceCentre getService() {
        return service;
    }

    public void setService(ServiceCentre service) {
        this.service = service;
    }
}