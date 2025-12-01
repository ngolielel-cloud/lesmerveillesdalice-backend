package com.moncentre.lesmerveillesdalice.repository;

import com.moncentre.lesmerveillesdalice.entity.Personnel;
import com.moncentre.lesmerveillesdalice.entity.ServiceCentre;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PersonnelRepository extends JpaRepository<Personnel, Integer> {

    List<Personnel> findByService(ServiceCentre service);
}