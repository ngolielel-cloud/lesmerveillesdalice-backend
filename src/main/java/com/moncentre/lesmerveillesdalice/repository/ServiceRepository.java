package com.moncentre.lesmerveillesdalice.repository;

import com.moncentre.lesmerveillesdalice.entity.ServiceCentre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ServiceRepository extends JpaRepository<ServiceCentre, Integer> {

    // Récupérer tous les services d’une catégorie (Kiné, Massage, Spa, Sport…)
    List<ServiceCentre> findByCategorie(String categorie);
}