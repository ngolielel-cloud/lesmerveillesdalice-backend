package com.moncentre.lesmerveillesdalice.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "rendez_vous")
public class RendezVous {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rendez_vous")
    private Integer idRendezVous;

    @Column(nullable = false)
    private LocalDateTime dateHeure;   // date + heure

    @Column(nullable = false)
    private String statut;             // En_attente, Confirmé, Annulé

    //@Column(columnDefinition = "TEXT")
    private String motif;              // besoin du client

    @Column(nullable = false)
    private String code;               // ⭐ CODE SECRET DU CLIENT

    // Relations
    @ManyToOne
    @JoinColumn(name = "id_client")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "id_personnel")
    private Personnel personnel;

    @ManyToOne
    @JoinColumn(name = "id_service")
    private ServiceCentre service;

    // GETTERS & SETTERS
    public Integer getIdRendezVous() {
        return idRendezVous;
    }

    public void setIdRendezVous(Integer idRendezVous) {
        this.idRendezVous = idRendezVous;
    }

    public LocalDateTime getDateHeure() {
        return dateHeure;
    }

    public void setDateHeure(LocalDateTime dateHeure) {
        this.dateHeure = dateHeure;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Personnel getPersonnel() {
        return personnel;
    }

    public void setPersonnel(Personnel personnel) {
        this.personnel = personnel;
    }

    public ServiceCentre getService() {
        return service;
    }

    public void setService(ServiceCentre service) {
        this.service = service;
    }
}