package com.moncentre.lesmerveillesdalice.controller;

import com.moncentre.lesmerveillesdalice.dto.FactureDTO;
import com.moncentre.lesmerveillesdalice.entity.Facture;
import com.moncentre.lesmerveillesdalice.service.FactureService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/factures")
@CrossOrigin("*")
public class FactureController {

    private final FactureService service;

    public FactureController(FactureService service) {
        this.service = service;
    }

    // Créer une facture (liée à client + prestation)
    @PostMapping("/{idClient}/{idPrestation}")
    public FactureDTO create(@RequestBody Facture facture,
                             @PathVariable Integer idClient,
                             @PathVariable Integer idPrestation) {
        return service.create(facture, idClient, idPrestation);
    }

    // Récupérer une facture
    @GetMapping("/{id}")
    public FactureDTO getById(@PathVariable Integer id) {
        return service.getById(id);
    }

    // toutes les factures
    @GetMapping
    public List<FactureDTO> getAll() {
        return service.getAll();
    }
}