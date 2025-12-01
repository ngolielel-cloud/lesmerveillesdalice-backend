package com.moncentre.lesmerveillesdalice.repository;

import com.moncentre.lesmerveillesdalice.entity.ServiceCentre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceCentreRepository extends JpaRepository<ServiceCentre, Integer> {

    List<ServiceCentre> findByCategorie(String categorie);
}