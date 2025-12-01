package com.moncentre.lesmerveillesdalice.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "dossier_client")
public class DossierClient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_dossier")
    private Integer iddossier;

    @ManyToOne
    @JoinColumn(name = "id_client", nullable = false)
    private Client client;

    private LocalDateTime dateCreation = LocalDateTime.now();
    private String statut;
    private String historique;

    private String motif;
    private String antecedents;
    private String douleurs;
    private String autresInfos;

    // =====================
    // GETTERS & SETTERS
    // =====================

    public Integer getId() {
        return iddossier;
    }

    public void setId(Integer id) {
        this.iddossier = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public LocalDateTime getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDateTime dateCreation) {
        this.dateCreation = dateCreation;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public String getHistorique() {
        return historique;
    }

    public void setHistorique(String historique) {
        this.historique = historique;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public String getAntecedents() {
        return antecedents;
    }

    public void setAntecedents(String antecedents) {
        this.antecedents = antecedents;
    }

    public String getDouleurs() {
        return douleurs;
    }

    public void setDouleurs(String douleurs) {
        this.douleurs = douleurs;
    }

    public String getAutresInfos() {
        return autresInfos;
    }

    public void setAutresInfos(String autresInfos) {
        this.autresInfos = autresInfos;
    }
}