package com.moncentre.lesmerveillesdalice.repository;

import com.moncentre.lesmerveillesdalice.entity.DossierClient;
import com.moncentre.lesmerveillesdalice.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DossierClientRepository extends JpaRepository<DossierClient, Integer> {

    // 🔹 Trouver un dossier via un client
    DossierClient findByClient(Client client);

    // 🔹 Trouver un dossier via l'id du client
    DossierClient findByClient_IdClient(Integer idClient);

    // 🔹 Statistiques : nombre de dossiers selon le statut
    long countByStatut(String statut);

    // 🔹 Tous les dossiers d’un service (Kinésithérapie = 1, Massage = 2, Spa = 3, Sport = 4)
    List<DossierClient> findByClient_Service_IdService(Integer idService);

    // 🔹 Recherche dans les dossiers selon nom/prénom/téléphone du client
    List<DossierClient> findByClient_NomContainingIgnoreCaseOrClient_PrenomContainingIgnoreCaseOrClient_TelephoneContaining(
            String nom, String prenom, String telephone
    );
}