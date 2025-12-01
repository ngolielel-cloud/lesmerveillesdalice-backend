package com.moncentre.lesmerveillesdalice.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "prestation")
public class Prestation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPrestation;

    private String type;          // Massage, Kiné, Spa...
    private String statut;        // Terminé, En cours...
    private LocalDateTime datePrestation;

    @Column(columnDefinition = "TEXT")
    private String historique;    // notes, évolution…

    // 🔗 Relations
    @ManyToOne
    @JoinColumn(name = "id_client")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "id_personnel")
    private Personnel personnel;

    @ManyToOne
    @JoinColumn(name = "id_service")
    private ServiceCentre service;

    // =========================
    // Getters & Setters
    // =========================

    public Integer getIdPrestation() { return idPrestation; }
    public void setIdPrestation(Integer idPrestation) { this.idPrestation = idPrestation; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getStatut() { return statut; }
    public void setStatut(String statut) { this.statut = statut; }

    public LocalDateTime getDatePrestation() { return datePrestation; }
    public void setDatePrestation(LocalDateTime datePrestation) { this.datePrestation = datePrestation; }

    public String getHistorique() { return historique; }
    public void setHistorique(String historique) { this.historique = historique; }

    public Client getClient() { return client; }
    public void setClient(Client client) { this.client = client; }

    public Personnel getPersonnel() { return personnel; }
    public void setPersonnel(Personnel personnel) { this.personnel = personnel; }

    public ServiceCentre getService() { return service; }
    public void setService(ServiceCentre service) { this.service = service; }
}