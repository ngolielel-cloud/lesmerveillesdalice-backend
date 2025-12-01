package com.moncentre.lesmerveillesdalice.controller;

import com.moncentre.lesmerveillesdalice.entity.Notification;
import com.moncentre.lesmerveillesdalice.service.NotificationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
@CrossOrigin(origins = "*")
public class NotificationController {

    private final NotificationService service;

    public NotificationController(NotificationService service) {
        this.service = service;
    }

    // 🔹 Créer une notification
    @PostMapping("/create")
    public Notification create(
            @RequestBody Notification notification,
            @RequestParam(required = false) Integer idClient,
            @RequestParam(required = false) Integer idPersonnel
    ) {
        return service.creerNotification(notification, idClient, idPersonnel);
    }

    // 🔹 Tout afficher
    @GetMapping
    public List<Notification> getAll() {
        return service.getAll();
    }

    // 🔹 Client
    @GetMapping("/client/{id}")
    public List<Notification> getByClient(@PathVariable Integer id) {
        return service.getByClient(id);
    }

    // 🔹 Personnel
    @GetMapping("/personnel/{id}")
    public List<Notification> getByPersonnel(@PathVariable Integer id) {
        return service.getByPersonnel(id);
    }

    // 🔹 Type
    @GetMapping("/type/{type}")
    public List<Notification> getByType(@PathVariable String type) {
        return service.getByType(type);
    }

    // 🔹 Suppression
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }
}