package com.moncentre.lesmerveillesdalice.dto;

import java.time.LocalDateTime;

public class ClientRdvRequest {

    // ⭐ INFOS CLIENT
    private String nom;
    private String prenom;
    private String sexe;
    private String dateNaissance;
    private String telephone;
    private String email;

    // ⭐ INFOS RDV
    private LocalDateTime dateHeure;
    private String motif;    //  🔥 ancien "besoin"
    private Integer id_Service;

    // ⭐ FORMULAIRE KINÉ
    private String antecedents;
    private String douleur;
    private String intensite;
    private String localisation;
    private String traitementActuel;
    private String allergies;
    private String diabete;
    private String tension;
    private String motifConsultation;

    // ⭐ MASSAGE / SPA / SPORT
    private String typeMassage;
    private String typeSpa;
    private String typeSport;

    private String objectif;
    private String douleurs;
    private String frequence;

    // ⭐ GETTERS & SETTERS

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }

    public String getSexe() { return sexe; }
    public void setSexe(String sexe) { this.sexe = sexe; }

    public String getDateNaissance() { return dateNaissance; }
    public void setDateNaissance(String dateNaissance) { this.dateNaissance = dateNaissance; }

    public String getTelephone() { return telephone; }
    public void setTelephone(String telephone) { this.telephone = telephone; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public LocalDateTime getDateHeure() { return dateHeure; }
    public void setDateHeure(LocalDateTime dateHeure) { this.dateHeure = dateHeure; }

    public String getMotif() { return motif; }
    public void setMotif(String motif) { this.motif = motif; }

    public Integer getIdService() { return id_Service; }
    public void setIdService(Integer idService) { this.id_Service = idService; }

    public String getAntecedents() { return antecedents; }
    public void setAntecedents(String antecedents) { this.antecedents = antecedents; }

    public String getDouleur() { return douleur; }
    public void setDouleur(String douleur) { this.douleur = douleur; }

    public String getIntensite() { return intensite; }
    public void setIntensite(String intensite) { this.intensite = intensite; }

    public String getLocalisation() { return localisation; }
    public void setLocalisation(String localisation) { this.localisation = localisation; }

    public String getTraitementActuel() { return traitementActuel; }
    public void setTraitementActuel(String traitementActuel) { this.traitementActuel = traitementActuel; }

    public String getAllergies() { return allergies; }
    public void setAllergies(String allergies) { this.allergies = allergies; }

    public String getDiabete() { return diabete; }
    public void setDiabete(String diabete) { this.diabete = diabete; }

    public String getTension() { return tension; }
    public void setTension(String tension) { this.tension = tension; }

    public String getMotifConsultation() { return motifConsultation; }
    public void setMotifConsultation(String motifConsultation) { this.motifConsultation = motifConsultation; }

    public String getTypeMassage() { return typeMassage; }
    public void setTypeMassage(String typeMassage) { this.typeMassage = typeMassage; }

    public String getTypeSpa() { return typeSpa; }
    public void setTypeSpa(String typeSpa) { this.typeSpa = typeSpa; }

    public String getTypeSport() { return typeSport; }
    public void setTypeSport(String typeSport) { this.typeSport = typeSport; }

    public String getObjectif() { return objectif; }
    public void setObjectif(String objectif) { this.objectif = objectif; }

    public String getDouleurs() { return douleurs; }
    public void setDouleurs(String douleurs) { this.douleurs = douleurs; }

    public String getFrequence() { return frequence; }
    public void setFrequence(String frequence) { this.frequence = frequence; }
}