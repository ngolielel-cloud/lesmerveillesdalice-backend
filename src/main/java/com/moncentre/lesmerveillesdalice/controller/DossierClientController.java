package com.moncentre.lesmerveillesdalice.controller;

import com.moncentre.lesmerveillesdalice.entity.DossierClient;
import com.moncentre.lesmerveillesdalice.service.DossierClientService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dossiers")
@CrossOrigin(origins = "*")
public class DossierClientController {

    private final DossierClientService service;

    public DossierClientController(DossierClientService service) {
        this.service = service;
    }

    // ⭐ Récupérer TOUS les dossiers
    @GetMapping("")
    public List<DossierClient> getAll() {
        return service.getAll();
    }

    // dossier d'un client
    @GetMapping("/client/{idClient}")
    public DossierClient getByClient(@PathVariable Integer idClient) {
        return service.getByClientId(idClient);
    }

    
    // dossier kine
    @GetMapping("/kine/{idPersonnel}")
    public List<DossierClient> getDossiersKine(@PathVariable Integer idPersonnel) {
        return service.getDossiersKine(idPersonnel);
    }

    //dossier massage
    @GetMapping("/massage/{idPersonnel}")
    public List<DossierClient> getDossiersMassage(@PathVariable Integer idPersonnel) {
        return service.getDossiersMassage(idPersonnel);
    }

    //dossier spa
    @GetMapping("/spa/{idPersonnel}")
    public List<DossierClient> getDossiersSpa(@PathVariable Integer idPersonnel) {
        return service.getDossiersSpa(idPersonnel);
    }

    
    //dossier sport
    @GetMapping("/sport/{idPersonnel}")
    public List<DossierClient> getDossiersSport(@PathVariable Integer idPersonnel) {
        return service.getDossiersSport(idPersonnel);
    }

    @PostMapping("/create")
    public DossierClient create(@RequestBody DossierClient dossier) {
        return service.create(dossier);
    }

    @PutMapping("/{id}")
    public DossierClient update(@PathVariable Integer id, @RequestBody DossierClient dossier) {
        return service.update(id, dossier);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }
}