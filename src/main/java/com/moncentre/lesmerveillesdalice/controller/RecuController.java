package com.moncentre.lesmerveillesdalice.controller;

import com.moncentre.lesmerveillesdalice.entity.Recu;
import com.moncentre.lesmerveillesdalice.service.RecuService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/recus")
@CrossOrigin(origins = "*")
public class RecuController {

    private final RecuService service;

    public RecuController(RecuService service) {
        this.service = service;
    }

    @PostMapping(value = "/generer/{idPaiement}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Recu genererRecu(
            @PathVariable Integer idPaiement,
            @RequestParam("fichier") MultipartFile fichier
    ) throws Exception {

        byte[] bytes = fichier.getBytes();
        return service.genererRecu(idPaiement, bytes);
    }

    @GetMapping("/{id}")
    public Recu getById(@PathVariable Integer id) {
        return service.getById(id);
    }
}