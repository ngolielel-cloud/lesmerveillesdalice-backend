package com.moncentre.lesmerveillesdalice.service;

import com.moncentre.lesmerveillesdalice.entity.Client;
import com.moncentre.lesmerveillesdalice.entity.Notification;
import com.moncentre.lesmerveillesdalice.entity.Personnel;
import com.moncentre.lesmerveillesdalice.repository.ClientRepository;
import com.moncentre.lesmerveillesdalice.repository.NotificationRepository;
import com.moncentre.lesmerveillesdalice.repository.PersonnelRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificationService {

    private final NotificationRepository repo;
    private final ClientRepository clientRepo;
    private final PersonnelRepository personnelRepo;

    public NotificationService(
            NotificationRepository repo,
            ClientRepository clientRepo,
            PersonnelRepository personnelRepo
    ) {
        this.repo = repo;
        this.clientRepo = clientRepo;
        this.personnelRepo = personnelRepo;
    }

    // 🔹 Créer une notification
    public Notification creerNotification(Notification notif, Integer idClient, Integer idPersonnel) {

        if (idClient != null) {
            Client client = clientRepo.findById(idClient)
                    .orElseThrow(() -> new RuntimeException("Client introuvable"));
            notif.setClient(client);
        }

        if (idPersonnel != null) {
            Personnel pers = personnelRepo.findById(idPersonnel)
                    .orElseThrow(() -> new RuntimeException("Personnel introuvable"));
            notif.setPersonnel(pers);
        }

        notif.setDateEnvoi(LocalDateTime.now());

        return repo.save(notif);
    }

    // 🔹 Obtenir toutes les notifications
    public List<Notification> getAll() {
        return repo.findAll();
    }

    // 🔹 Notifications d’un client
    public List<Notification> getByClient(Integer idClient) {
        return repo.findByClient_IdClient(idClient);
    }

    // 🔹 Notifications d’un personnel
    public List<Notification> getByPersonnel(Integer idPersonnel) {
        return repo.findByPersonnel_IdPersonnel(idPersonnel);
    }

    // 🔹 Notifications par type
    public List<Notification> getByType(String type) {
        return repo.findByType(type);
    }

    // 🔹 Suppression
    public void delete(Integer id) {
        repo.deleteById(id);
    }
}