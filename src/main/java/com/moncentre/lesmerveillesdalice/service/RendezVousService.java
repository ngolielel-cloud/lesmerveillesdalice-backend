package com.moncentre.lesmerveillesdalice.service;

import com.moncentre.lesmerveillesdalice.dto.RendezVousDTO;
import com.moncentre.lesmerveillesdalice.entity.*;
import com.moncentre.lesmerveillesdalice.exception.ResourceNotFoundException;
import com.moncentre.lesmerveillesdalice.mapper.RendezVousMapper;
import com.moncentre.lesmerveillesdalice.repository.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class RendezVousService {

    private final RendezVousRepository repo;
    private final ClientRepository clientRepo;
    private final PersonnelRepository personnelRepo;
    private final ServiceRepository serviceRepo;
    private final DossierClientRepository dossierRepo;

    public RendezVousService(
            RendezVousRepository repo,
            ClientRepository clientRepo,
            PersonnelRepository personnelRepo,
            ServiceRepository serviceRepo,
            DossierClientRepository dossierRepo
    ) {
        this.repo = repo;
        this.clientRepo = clientRepo;
        this.personnelRepo = personnelRepo;
        this.serviceRepo = serviceRepo;
        this.dossierRepo = dossierRepo;
    }

    // =====================================================
    // ⭐⭐ CREATION COMPLETE : CLIENT + DOSSIER + RDV (client)
    // =====================================================
    public RendezVousDTO creerComplet(Map<String, Object> data) {

        Client client = new Client();
        client.setNom((String) data.get("nom"));
        client.setPrenom((String) data.get("prenom"));
        client.setSexe((String) data.get("sexe"));
        client.setTelephone((String) data.get("telephone"));
        client.setEmail((String) data.get("email"));

        String code = String.valueOf((int) (Math.random() * 900000 + 100000));
        client.setCodeSecret(code);
        client = clientRepo.save(client);

        DossierClient dossier = new DossierClient();
        dossier.setClient(client);
        dossier.setStatut("Actif");
        dossier.setHistorique("Création automatique depuis prise de RDV");
        dossier.setDateCreation(LocalDateTime.now());
        dossierRepo.save(dossier);

        RendezVous rdv = new RendezVous();
        rdv.setClient(client);
        rdv.setMotif((String) data.get("motif"));
        rdv.setStatut("En_attente");
        rdv.setCode(code);

        rdv.setDateHeure(LocalDateTime.parse((String) data.get("dateHeure")));

        Integer idService = (Integer) data.get("idService");
        ServiceCentre service = serviceRepo.findById(idService)
                .orElseThrow(() -> new ResourceNotFoundException("Service non trouvé"));
        rdv.setService(service);

        RendezVous saved = repo.save(rdv);

        return RendezVousMapper.toDTO(saved);
    }

    // =====================================================
    // RDV Manuel (admin) — ancienne méthode
    // =====================================================
    public RendezVousDTO create(Map<String, Object> data) {

        try {
            Integer idClient = (Integer) data.get("idClient");
            Integer idPersonnel = (Integer) data.get("idPersonnel");
            Integer idService = (Integer) data.get("idService");

            String statut = (String) data.getOrDefault("statut", "En_attente");
            String motif = (String) data.getOrDefault("motif", "");

            String dateStr = (String) data.get("dateHeure");
            if (dateStr == null) {
                throw new RuntimeException("dateHeure manquant");
            }

            LocalDateTime dateHeure;
            try {
                dateHeure = LocalDateTime.parse(dateStr);
            } catch (Exception e) {
                throw new RuntimeException("Format date invalide : " + dateStr);
            }

            Client client = clientRepo.findById(idClient)
                    .orElseThrow(() -> new RuntimeException("Client introuvable : " + idClient));

            Personnel personnel = null;
            if (idPersonnel != null) {
                personnel = personnelRepo.findById(idPersonnel)
                        .orElseThrow(() -> new RuntimeException("Personnel introuvable : " + idPersonnel));
            }

            ServiceCentre service = serviceRepo.findById(idService)
                    .orElseThrow(() -> new RuntimeException("Service introuvable : " + idService));

            RendezVous rdv = new RendezVous();
            rdv.setClient(client);
            rdv.setPersonnel(personnel);
            rdv.setService(service);
            rdv.setMotif(motif);
            rdv.setStatut(statut);
            rdv.setDateHeure(dateHeure);
            rdv.setCode(client.getCodeSecret());

            return RendezVousMapper.toDTO(repo.save(rdv));

        } catch (Exception e) {
            throw new RuntimeException("Erreur création RDV : " + e.getMessage());
        }
    }
    // =====================================================
    // 🧡 MÉTHODES SPÉCIALES POUR LA GÉRANTE (map JSON plat)
    // =====================================================

    // ➕ Créer à partir d'un JSON avec idClient, idPersonnel, idService...
    public RendezVousDTO createFromAdmin(Map<String, Object> data) {

        Integer idClient = (Integer) data.get("idClient");
        Integer idPersonnel = (Integer) data.get("idPersonnel");
        Integer idService = (Integer) data.get("idService");

        String statut = (String) data.getOrDefault("statut", "En_attente");
        String motif = (String) data.getOrDefault("motif", "");

        String dateStr = (String) data.get("dateHeure");
        LocalDateTime dateHeure = LocalDateTime.parse(dateStr);

        Client client = clientRepo.findById(idClient)
                .orElseThrow(() -> new ResourceNotFoundException("Client introuvable"));

        Personnel personnel = null;
        if (idPersonnel != null) {
            personnel = personnelRepo.findById(idPersonnel)
                    .orElseThrow(() -> new ResourceNotFoundException("Personnel introuvable"));
        }

        ServiceCentre service = serviceRepo.findById(idService)
                .orElseThrow(() -> new ResourceNotFoundException("Service introuvable"));

        RendezVous rdv = new RendezVous();
        rdv.setClient(client);
        rdv.setPersonnel(personnel);
        rdv.setService(service);
        rdv.setMotif(motif);
        rdv.setStatut(statut);
        rdv.setDateHeure(dateHeure);
        rdv.setCode(client.getCodeSecret());

        return RendezVousMapper.toDTO(repo.save(rdv));
    }

    // ✏ Modifier un RDV existant
    public RendezVousDTO updateFromAdmin(Integer id, Map<String, Object> data) {
        RendezVous rdv = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rendez-vous introuvable"));

        if (data.containsKey("dateHeure")) {
            String dateStr = (String) data.get("dateHeure");
            rdv.setDateHeure(LocalDateTime.parse(dateStr));
        }

        if (data.containsKey("idClient")) {
            Integer idClient = (Integer) data.get("idClient");
            Client client = clientRepo.findById(idClient)
                    .orElseThrow(() -> new ResourceNotFoundException("Client introuvable"));
            rdv.setClient(client);
            rdv.setCode(client.getCodeSecret());
        }

        if (data.containsKey("idPersonnel")) {
            Integer idPersonnel = (Integer) data.get("idPersonnel");
            if (idPersonnel != null) {
                Personnel personnel = personnelRepo.findById(idPersonnel)
                        .orElseThrow(() -> new ResourceNotFoundException("Personnel introuvable"));
                rdv.setPersonnel(personnel);
            } else {
                rdv.setPersonnel(null);
            }
        }

        if (data.containsKey("idService")) {
            Integer idService = (Integer) data.get("idService");
            ServiceCentre service = serviceRepo.findById(idService)
                    .orElseThrow(() -> new ResourceNotFoundException("Service introuvable"));
            rdv.setService(service);
        }

        if (data.containsKey("statut")) {
            rdv.setStatut((String) data.get("statut"));
        }

        if (data.containsKey("motif")) {
            rdv.setMotif((String) data.get("motif"));
        }

        return RendezVousMapper.toDTO(repo.save(rdv));
    }

    // ❌ Annuler
    public RendezVousDTO annuler(Integer id) {
        RendezVous rdv = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rendez-vous introuvable"));
        rdv.setStatut("Annulé");
        return RendezVousMapper.toDTO(repo.save(rdv));
    }

    // 🔁 Reporter
    public RendezVousDTO reporter(Integer id, String dateHeureStr) {
        RendezVous rdv = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rendez-vous introuvable"));
        rdv.setDateHeure(LocalDateTime.parse(dateHeureStr));
        return RendezVousMapper.toDTO(repo.save(rdv));
    }

    // 🗑 Supprimer
    public void supprimer(Integer id) {
        if (!repo.existsById(id)) {
            throw new ResourceNotFoundException("Rendez-vous introuvable");
        }
        repo.deleteById(id);
    }

    // =====================================================
    // LECTURE & STATS (déjà existants)
    // =====================================================

    public List<RendezVousDTO> getAll() {
        return repo.findAll()
                .stream()
                .map(RendezVousMapper::toDTO)
                .toList();
    }

    public List<RendezVousDTO> getDuJour() {
        LocalDateTime start = LocalDate.now().atStartOfDay();
        LocalDateTime end = start.plusHours(23).plusMinutes(59);

        return repo.findByDateHeureBetween(start, end)
                .stream()
                .map(RendezVousMapper::toDTO)
                .toList();
    }

    public List<RendezVousDTO> getEnAttente() {
        return repo.findByStatut("En_attente")
                .stream()
                .map(RendezVousMapper::toDTO)
                .toList();
    }

    public List<RendezVousDTO> getRdvByCode(String code) {
        return repo.findByCode(code)
                .stream()
                .map(RendezVousMapper::toDTO)
                .toList();
    }

    public List<RendezVousDTO> getRdvByService(Integer idService) {
        ServiceCentre service = serviceRepo.findById(idService)
                .orElseThrow(() -> new ResourceNotFoundException("Service introuvable"));

        return repo.findByService(service)
                .stream()
                .map(RendezVousMapper::toDTO)
                .toList();
    }

    public List<RendezVousDTO> getRdvByPersonnel(Integer idPersonnel) {
        Personnel personnel = personnelRepo.findById(idPersonnel)
                .orElseThrow(() -> new ResourceNotFoundException("Personnel introuvable"));

        return repo.findByPersonnel(personnel)
                .stream()
                .map(RendezVousMapper::toDTO)
                .toList();
    }

    public Map<String, Long> getStats() {

        long totalRdv = repo.count();

        LocalDateTime start = LocalDate.now().atStartOfDay();
        LocalDateTime end = start.plusHours(23).plusMinutes(59);

        long rdvDuJour = repo.countByDateHeureBetween(start, end);
        long rdvAVenir = repo.countByDateHeureAfter(LocalDateTime.now());
        long dossiersActifs = dossierRepo.countByStatut("Actif");

        return Map.of(
                "totalRdv", totalRdv,
                "rdvDuJour", rdvDuJour,
                "rdvAVenir", rdvAVenir,
                "dossiersActifs", dossiersActifs
        );
    }
}