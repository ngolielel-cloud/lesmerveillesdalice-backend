package com.moncentre.lesmerveillesdalice.repository;

import com.moncentre.lesmerveillesdalice.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

    Client findByTelephone(String telephone);

    // ✔ liste des clients par service
    List<Client> findByService_IdService(Integer idService);

    // ✔ compteur par service
    long countByService_IdService(Integer idService);
}