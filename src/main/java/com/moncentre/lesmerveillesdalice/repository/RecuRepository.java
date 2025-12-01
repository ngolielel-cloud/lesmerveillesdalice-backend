package com.moncentre.lesmerveillesdalice.repository;

import com.moncentre.lesmerveillesdalice.entity.Recu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecuRepository extends JpaRepository<Recu, Integer> {

    Recu findByNumeroRecu(String numeroRecu);
}