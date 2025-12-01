package com.moncentre.lesmerveillesdalice.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_client")
    private Integer idClient;

    private String nom;
    private String prenom;
    private String telephone;
    private String email;

    @Column(length = 1)
    private String sexe;

    private String codeSecret;

    @ManyToOne
    @JoinColumn(name = "id_service")
    private ServiceCentre service;

    // Getters & Setters

    public Integer getIdClient() { return idClient; }
    public void setIdClient(Integer idClient) { this.idClient = idClient; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }

    public String getTelephone() { return telephone; }
    public void setTelephone(String telephone) { this.telephone = telephone; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getSexe() { return sexe; }
    public void setSexe(String sexe) { this.sexe = sexe; }

    public String getCodeSecret() { return codeSecret; }
    public void setCodeSecret(String codeSecret) { this.codeSecret = codeSecret; }

    public ServiceCentre getService() { return service; }
    public void setService(ServiceCentre service) { this.service = service; }
}