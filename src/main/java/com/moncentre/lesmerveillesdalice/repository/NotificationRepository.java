package com.moncentre.lesmerveillesdalice.repository;

import com.moncentre.lesmerveillesdalice.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Integer> {

    List<Notification> findByClient_IdClient(Integer idClient);

    List<Notification> findByPersonnel_IdPersonnel(Integer idPersonnel);

    List<Notification> findByType(String type);
}