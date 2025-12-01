package com.moncentre.lesmerveillesdalice.controller;

import com.moncentre.lesmerveillesdalice.dto.PaiementDTO;
import com.moncentre.lesmerveillesdalice.entity.Paiement;
import com.moncentre.lesmerveillesdalice.service.PaiementService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/paiements")
@CrossOrigin("*")
public class PaiementController {

    private final PaiementService service;

    public PaiementController(PaiementService service) {
        this.service = service;
    }

    // ✔ Créer un paiement (lié à une facture)
    @PostMapping("/{idFacture}")
    public PaiementDTO create(@RequestBody Paiement paiement,
                              @PathVariable Integer idFacture) {
        return service.create(paiement, idFacture);
    }

    // ✔ Lire un paiement
    @GetMapping("/{id}")
    public PaiementDTO getById(@PathVariable Integer id) {
        return service.getById(id);
    }

    // ✔ Liste
    @GetMapping
    public List<PaiementDTO> getAll() {
        return service.getAll();
    }
}