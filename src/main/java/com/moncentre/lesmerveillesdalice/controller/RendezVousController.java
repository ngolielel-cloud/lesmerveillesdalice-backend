package com.moncentre.lesmerveillesdalice.controller;

import com.moncentre.lesmerveillesdalice.dto.RendezVousDTO;
import com.moncentre.lesmerveillesdalice.service.RendezVousService;
import com.moncentre.lesmerveillesdalice.repository.RendezVousRepository;
import com.moncentre.lesmerveillesdalice.repository.ClientRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/rendezvous")
@CrossOrigin("*")
public class RendezVousController {

    private final RendezVousService service;
    private final RendezVousRepository rdvRepo;
    private final ClientRepository clientRepo;

    public RendezVousController(
            RendezVousService service,
            RendezVousRepository rdvRepo,
            ClientRepository clientRepo
    ) {
        this.service = service;
        this.rdvRepo = rdvRepo;
        this.clientRepo = clientRepo;
    }

    // =========================================================
    // 📌 LECTURE
    // =========================================================

    @GetMapping
    public List<RendezVousDTO> getAll() {
        return service.getAll();
    }

    @GetMapping("/du-jour")
    public List<RendezVousDTO> getToday() {
        return service.getDuJour();
    }

    @GetMapping("/en-attente")
    public List<RendezVousDTO> getWaiting() {
        return service.getEnAttente();
    }

    @GetMapping("/code/{code}")
    public List<RendezVousDTO> getByCode(@PathVariable String code) {
        return service.getRdvByCode(code);
    }

    @GetMapping("/service/{idService}")
    public List<RendezVousDTO> getByService(@PathVariable Integer idService) {
        return service.getRdvByService(idService);
    }

    // RDV pour un personnel (kine, massage, spa, sport)
    @GetMapping("/personnel/{idPersonnel}")
    public List<RendezVousDTO> getByPersonnel(@PathVariable Integer idPersonnel) {
        return service.getRdvByPersonnel(idPersonnel);
    }

    // RDV pour un admin par service
    @GetMapping("/admin/service/{idService}")
    public List<RendezVousDTO> getByServiceAdmin(@PathVariable Integer idService) {
        return service.getRdvByService(idService);
    }

    // =========================================================
    // 🧡 CRUD POUR LA GÉRANTE (utilisé par Angular)
    // =========================================================

    // ➕ Créer un RDV
    @PostMapping
    public RendezVousDTO createFromGerante(@RequestBody Map<String, Object> data) {
        return service.createFromAdmin(data);
    }

    // ✏ Modifier un RDV
    @PutMapping("/{id}")
    public RendezVousDTO updateFromGerante(
            @PathVariable Integer id,
            @RequestBody Map<String, Object> data
    ) {
        return service.updateFromAdmin(id, data);
    }

    // ❌ Annuler
    @PutMapping("/annuler/{id}")
    public RendezVousDTO annuler(@PathVariable Integer id) {
        return service.annuler(id);
    }

    // 🔁 Reporter (changer la date/heure)
    @PutMapping("/reporter/{id}")
    public RendezVousDTO reporter(
            @PathVariable Integer id,
            @RequestBody Map<String, String> body
    ) {
        String dateHeure = body.get("dateHeure");
        return service.reporter(id, dateHeure);
    }

    // 🗑 Supprimer
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        service.supprimer(id);
    }

    // =========================================================
    // 📊 STATISTIQUES
    // =========================================================

    // Statuts
    @GetMapping("/admin/statut/{statut}")
    public long countByStatutAdmin(@PathVariable String statut) {
        return rdvRepo.countByStatut(statut);
    }

    @GetMapping("/stats/statuts")
    public Map<String, Long> getStatsByStatut() {
        Map<String, Long> stats = new HashMap<>();
        stats.put("confirme", rdvRepo.countByStatut("Confirmé"));
        stats.put("attente", rdvRepo.countByStatut("En_attente"));
        stats.put("annule", rdvRepo.countByStatut("Annulé"));
        stats.put("termine", rdvRepo.countByStatut("Terminé"));
        return stats;
    }

    // Par service
    @GetMapping("/stats/services")
    public Map<String, Long> getStatsByService() {
        Map<String, Long> stats = new HashMap<>();
        stats.put("kine", rdvRepo.countByService(1));
        stats.put("massage", rdvRepo.countByService(2));
        stats.put("spa", rdvRepo.countByService(3));
        stats.put("sport", rdvRepo.countByService(4));
        return stats;
    }

    // Total clients
    @GetMapping("/stats/clients")
    public long getTotalClients() {
        return clientRepo.count();
    }

    @GetMapping("/stats/rdv-today")
    public long getRdvToday() {
        LocalDate today = LocalDate.now();
        LocalDateTime start = today.atStartOfDay();
        LocalDateTime end = today.atTime(23, 59);
        return rdvRepo.countByDateHeureBetween(start, end);
    }

    @GetMapping("/stats/rdv-future")
    public long getRdvFuture() {
        return rdvRepo.countByDateHeureAfter(LocalDateTime.now());
    }

    @GetMapping("/stats/rdv-attente")
    public long getRdvAttente() {
        return rdvRepo.countByStatut("En_attente");
    }

    @GetMapping("/stats/frequentation")
    public Map<String, Long> getFrequentation() {
        Map<String, Long> map = new HashMap<>();
        map.put("KINE", rdvRepo.countByService(1));
        map.put("MASSAGE", rdvRepo.countByService(2));
        map.put("SPA", rdvRepo.countByService(3));
        map.put("SPORT", rdvRepo.countByService(4));
        return map;
    }
}