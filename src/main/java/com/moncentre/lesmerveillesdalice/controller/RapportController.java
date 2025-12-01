package com.moncentre.lesmerveillesdalice.controller;

import com.moncentre.lesmerveillesdalice.entity.Rapport;
import com.moncentre.lesmerveillesdalice.service.RapportService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rapports")
@CrossOrigin(origins = "*")
public class RapportController {

    private final RapportService service;

    public RapportController(RapportService service) {
        this.service = service;
    }

    // 🔹 Créer
    @PostMapping
    public Rapport create(@RequestBody Rapport rapport) {
        return service.create(rapport);
    }

    // 🔹 Tout afficher
    @GetMapping
    public List<Rapport> getAll() {
        return service.getAll();
    }

    // 🔹 Par ID
    @GetMapping("/{id}")
    public Rapport getById(@PathVariable Integer id) {
        return service.getById(id);
    }

    // 🔹 Par type
    @GetMapping("/type/{type}")
    public List<Rapport> getByType(@PathVariable String type) {
        return service.getByType(type);
    }

    // 🔹 Par période
    @GetMapping("/periode/{periode}")
    public List<Rapport> getByPeriode(@PathVariable String periode) {
        return service.getByPeriode(periode);
    }

    // 🔹 Mise à jour
    @PutMapping("/{id}")
    public Rapport update(@PathVariable Integer id, @RequestBody Rapport rapport) {
        return service.update(id, rapport);
    }

    // 🔹 Supprimer
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }
}