package com.moncentre.lesmerveillesdalice.repository;

import com.moncentre.lesmerveillesdalice.entity.Indicateur;
import com.moncentre.lesmerveillesdalice.entity.Rapport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IndicateurRepository extends JpaRepository<Indicateur, Integer> {

    List<Indicateur> findByRapport(Rapport rapport);
}