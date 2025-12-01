package com.moncentre.lesmerveillesdalice.repository;

import com.moncentre.lesmerveillesdalice.entity.Prestation;
import com.moncentre.lesmerveillesdalice.entity.Client;
import com.moncentre.lesmerveillesdalice.entity.Personnel;
import com.moncentre.lesmerveillesdalice.entity.ServiceCentre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PrestationRepository extends JpaRepository<Prestation, Integer> {

    List<Prestation> findByClient(Client client);

    List<Prestation> findByPersonnel(Personnel personnel);

    List<Prestation> findByService(ServiceCentre service);

    List<Prestation> findByStatut(String statut);
}