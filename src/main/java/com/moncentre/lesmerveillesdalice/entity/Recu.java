package com.moncentre.lesmerveillesdalice.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "recu")
public class Recu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_recu")
    private Integer idRecu;

    @Column(name = "numero_recu")
    private String numeroRecu;

    @Lob
    @Column(name = "fichier")
    private byte[] fichier;

    @OneToOne
    @JoinColumn(name = "id_paiement", unique = true)
    private Paiement paiement;

    // ------ GETTERS & SETTERS ------
    public Integer getIdRecu() {
        return idRecu;
    }

    public void setIdRecu(Integer idRecu) {
        this.idRecu = idRecu;
    }

    public String getNumeroRecu() {
        return numeroRecu;
    }

    public void setNumeroRecu(String numeroRecu) {
        this.numeroRecu = numeroRecu;
    }

    public byte[] getFichier() {
        return fichier;
    }

    public void setFichier(byte[] fichier) {
        this.fichier = fichier;
    }

    public Paiement getPaiement() {
        return paiement;
    }

    public void setPaiement(Paiement paiement) {
        this.paiement = paiement;
    }
}