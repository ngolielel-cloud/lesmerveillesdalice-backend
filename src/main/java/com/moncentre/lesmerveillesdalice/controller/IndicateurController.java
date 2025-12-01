package com.moncentre.lesmerveillesdalice.controller;

import com.moncentre.lesmerveillesdalice.entity.Indicateur;
import com.moncentre.lesmerveillesdalice.service.IndicateurService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/indicateurs")
@CrossOrigin(origins = "*")
public class IndicateurController {

    private final IndicateurService service;

    public IndicateurController(IndicateurService service) {
        this.service = service;
    }

    // 🔹 Créer
    @PostMapping("/{idRapport}")
    public Indicateur create(
            @PathVariable Integer idRapport,
            @RequestBody Indicateur indicateur
    ) {
        return service.create(indicateur, idRapport);
    }

    // 🔹 Tout afficher
    @GetMapping
    public List<Indicateur> getAll() {
        return service.getAll();
    }

    // 🔹 Par ID
    @GetMapping("/{id}")
    public Indicateur getById(@PathVariable Integer id) {
        return service.getById(id);
    }

    // 🔹 Par rapport
    @GetMapping("/rapport/{idRapport}")
    public List<Indicateur> getByRapport(@PathVariable Integer idRapport) {
        return service.getByRapport(idRapport);
    }

    // 🔹 Mise à jour
    @PutMapping("/{id}")
    public Indicateur update(
            @PathVariable Integer id,
            @RequestBody Indicateur indicateur
    ) {
        return service.update(id, indicateur);
    }

    // 🔹 Supprimer
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }
}