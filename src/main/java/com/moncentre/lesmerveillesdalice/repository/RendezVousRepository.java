package com.moncentre.lesmerveillesdalice.repository;

import com.moncentre.lesmerveillesdalice.entity.RendezVous;
import com.moncentre.lesmerveillesdalice.entity.ServiceCentre;
import com.moncentre.lesmerveillesdalice.entity.Personnel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface RendezVousRepository extends JpaRepository<RendezVous, Integer> {

    List<RendezVous> findByCode(String code);

    List<RendezVous> findByStatut(String statut);

    List<RendezVous> findByService(ServiceCentre service);

    List<RendezVous> findByDateHeureBetween(LocalDateTime start, LocalDateTime end);
    
    List<RendezVous> findByPersonnel(Personnel personnel);
    
    // ⭐ Statistiques : compter par statut
    long countByStatut(String statut);
    long countByDateHeureBetween(LocalDateTime debut, LocalDateTime fin);

    long countByDateHeureAfter(LocalDateTime date);

    // ⭐ Statistiques : compter par service ID
    @Query("SELECT COUNT(r) FROM RendezVous r WHERE r.service.idService = :idService")
    long countByService(@Param("idService") Integer idService);

	int countByServiceAndDateHeure(ServiceCentre service, LocalDateTime dateHeure);

}
