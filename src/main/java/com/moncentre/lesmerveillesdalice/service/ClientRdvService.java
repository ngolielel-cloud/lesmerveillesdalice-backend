

package com.moncentre.lesmerveillesdalice.service;

import com.moncentre.lesmerveillesdalice.dto.ClientRdvRequest;
import com.moncentre.lesmerveillesdalice.dto.RendezVousDTO;
import com.moncentre.lesmerveillesdalice.entity.Client;
import com.moncentre.lesmerveillesdalice.entity.DossierClient;
import com.moncentre.lesmerveillesdalice.entity.Personnel;
import com.moncentre.lesmerveillesdalice.entity.RendezVous;
import com.moncentre.lesmerveillesdalice.entity.ServiceCentre;
import com.moncentre.lesmerveillesdalice.exception.ResourceNotFoundException;
import com.moncentre.lesmerveillesdalice.mapper.RendezVousMapper;
import com.moncentre.lesmerveillesdalice.repository.*;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClientRdvService {

    private final ClientRepository clientRepo;
    private final PersonnelRepository personnelRepo;
    private final ServiceRepository serviceRepo;
    private final RendezVousRepository rdvRepo;
    private final DossierClientRepository dossierRepo;

    public ClientRdvService(ClientRepository clientRepo,
                            PersonnelRepository personnelRepo,
                            ServiceRepository serviceRepo,
                            RendezVousRepository rdvRepo,
                            DossierClientRepository dossierRepo) {

        this.clientRepo = clientRepo;
        this.personnelRepo = personnelRepo;
        this.serviceRepo = serviceRepo;
        this.rdvRepo = rdvRepo;
        this.dossierRepo = dossierRepo;
    }

    // ====================================================
    // ⭐⭐ 1️⃣ CRÉATION COMPLÈTE : CLIENT + RDV + DOSSIER ⭐⭐
    // ====================================================
    public RendezVousDTO createFromClient(ClientRdvRequest req) {

        // 🔹 Vérifier date / heure
        checkDateTimeRules(req.getDateHeure());

        // 🔹 Trouver service
        ServiceCentre service =
                serviceRepo.findById(req.getIdService())
                        .orElseThrow(() -> new ResourceNotFoundException("Service introuvable"));

        // 🔹 Vérifier capacité
        checkServiceCapacity(service, req.getDateHeure());

        // 🔹 Choisir un personnel
        Personnel personnel = getAvailablePersonnel(service);

        // 🔹 Créer ou récupérer le client
        Client client = getOrCreateClient(req);

        // 🔹 Créer rendez-vous
        RendezVous rdv = new RendezVous();
        rdv.setClient(client);
        rdv.setPersonnel(personnel);
        rdv.setService(service);
        rdv.setDateHeure(req.getDateHeure());
        rdv.setMotif(req.getMotif());
        rdv.setStatut("En_attente");

        // ⭐ AJOUT DU CODE SECRET DANS LE RDV (OBLIGATOIRE)
        rdv.setCode(client.getCodeSecret());

        RendezVous saved = rdvRepo.save(rdv);

        // 🔹 Si kiné → créer dossier médical
        if (service.getNomService().equalsIgnoreCase("Kinésithérapie")) {
            createKineDossierIfNeeded(client, req);
        }

        return RendezVousMapper.toDTO(saved);
    }

    // ====================================================
    // ⭐⭐ 2️⃣ VÉRIFICATION DES HORAIRES ⭐⭐
    // ====================================================
    private void checkDateTimeRules(LocalDateTime dateHeure) {

        LocalDateTime now = LocalDateTime.now();

        if (dateHeure.isBefore(now)) {
            throw new RuntimeException("La date ou l'heure est déjà passée.");
        }

        int day = dateHeure.getDayOfWeek().getValue();
        int hour = dateHeure.getHour();
        int minute = dateHeure.getMinute();

        if (day == 1) throw new RuntimeException("Le centre est fermé le lundi.");

        // Mardi-vendredi
        if (day >= 2 && day <= 5) {
            if (!(hour > 7 && hour < 20) && !(hour == 19 && minute <= 30)) {
                throw new RuntimeException("Horaire non autorisé pour mardi–vendredi.");
            }
        }

        // Samedi-dimanche
        if (day == 6 || day == 7) {
            if (!(hour > 8 && hour < 18) && !(hour == 17 && minute <= 30)) {
                throw new RuntimeException("Horaire non autorisé pour samedi–dimanche.");
            }
        }
    }

    // ====================================================
    // ⭐⭐ 3️⃣ CAPACITÉ PAR SERVICE ⭐⭐
    // ====================================================
    private void checkServiceCapacity(ServiceCentre service, LocalDateTime dateHeure) {

        int nb = rdvRepo.countByServiceAndDateHeure(service, dateHeure);

        switch (service.getNomService().toLowerCase()) {

            case "kinésithérapie":
                if (nb >= 3) throw new RuntimeException("Ce créneau kiné est complet (max 3).");
                break;

            case "massage":
                if (nb >= 1) throw new RuntimeException("Ce créneau massage est complet.");
                break;

            case "spa":
                if (nb >= 2) throw new RuntimeException("Spa complet (max 2).");
                break;

            case "salle de sport":
                if (nb >= 6) throw new RuntimeException("Ce créneau sport est complet (max 6).");
                break;
        }
    }

    // ====================================================
    // ⭐⭐ 4️⃣ CHOISIR PERSONNEL DISPONIBLE ⭐⭐
    // ====================================================
    private Personnel getAvailablePersonnel(ServiceCentre service) {

        List<Personnel> personnels = personnelRepo.findByService(service);

        if (personnels.isEmpty()) {
            throw new RuntimeException("Aucun personnel disponible pour ce service.");
        }

        return personnels.get(0);
    }

    // ====================================================
    // ⭐⭐ 5️⃣ CRÉER OU RÉCUPÉRER CLIENT ⭐⭐
    // ====================================================
    private Client getOrCreateClient(ClientRdvRequest req) {

        Client existing = clientRepo.findByTelephone(req.getTelephone());

        if (existing != null) {
            return existing;
        }

        Client c = new Client();
        c.setNom(req.getNom());
        c.setPrenom(req.getPrenom());
        c.setSexe(req.getSexe());
        c.setTelephone(req.getTelephone());
        c.setEmail(req.getEmail());

        // ⭐ Génération du code secret UNIQUE
        String code = "CLT-" + System.currentTimeMillis();
        c.setCodeSecret(code);

        return clientRepo.save(c);
    }

    // ====================================================
    // ⭐⭐ 6️⃣ CRÉATION DOSSIER KINÉ ⭐⭐
    // ====================================================
    private void createKineDossierIfNeeded(Client client, ClientRdvRequest req) {

        if (dossierRepo.findByClient(client) != null) return;

        DossierClient dossier = new DossierClient();
        dossier.setClient(client);
        dossier.setStatut("Ouvert");

        StringBuilder hist = new StringBuilder();
        hist.append("Création dossier kiné\n");
        hist.append("Motif : ").append(req.getMotifConsultation()).append("\n");
        hist.append("Antécédents : ").append(req.getAntecedents()).append("\n");
        hist.append("Douleur : ").append(req.getDouleur()).append("\n");

        dossier.setHistorique(hist.toString());

        dossierRepo.save(dossier);
    }
}

