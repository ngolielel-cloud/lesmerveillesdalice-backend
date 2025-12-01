package com.moncentre.lesmerveillesdalice.dto;

public class ClientDTO {

    private Integer id;
    private String nom;
    private String prenom;
    private String telephone;
    private String email;
    private String sexe;
    private String codeSecret;

    private Integer serviceId;
    private String nomService;

    // Getters & Setters

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

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

    public Integer getServiceId() { return serviceId; }
    public void setServiceId(Integer serviceId) { this.serviceId = serviceId; }

    public String getNomService() { return nomService; }
    public void setNomService(String nomService) { this.nomService = nomService; }
}