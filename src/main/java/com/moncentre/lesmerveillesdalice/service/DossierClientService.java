

package com.moncentre.lesmerveillesdalice.service;

import com.moncentre.lesmerveillesdalice.entity.Client;
import com.moncentre.lesmerveillesdalice.entity.DossierClient;
import com.moncentre.lesmerveillesdalice.repository.ClientRepository;
import com.moncentre.lesmerveillesdalice.repository.DossierClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DossierClientService {

    private final DossierClientRepository repo;
    private final ClientRepository clientRepo;

    public DossierClientService(DossierClientRepository repo, ClientRepository clientRepo) {
        this.repo = repo;
        this.clientRepo = clientRepo;
    }

    public List<DossierClient> getAll() {
        return repo.findAll();
    }

    public DossierClient getByClientId(Integer idClient) {
        return repo.findByClient_IdClient(idClient);
    }

    // 🔥 Dossiers kiné (ID service = 1)
    public List<DossierClient> getDossiersKine(Integer idPersonnel) {
        return repo.findByClient_Service_IdService(1);
    }

    // 🔥 Dossiers massage (ID service = 2)
    public List<DossierClient> getDossiersMassage(Integer idPersonnel) {
        return repo.findByClient_Service_IdService(2);
    }

    // 🔥 Dossiers spa (ID service = 3)
    public List<DossierClient> getDossiersSpa(Integer idPersonnel) {
        return repo.findByClient_Service_IdService(3);
    }

    // 🔥 Dossiers sport (ID service = 4)
    public List<DossierClient> getDossiersSport(Integer idPersonnel) {
        return repo.findByClient_Service_IdService(4);
    }

    public long countByStatut(String statut) {
        return repo.countByStatut(statut);
    }

    // ❤️ CREATE AVEC CLIENT
    public DossierClient create(DossierClient dossier) {

        // Ici on charge le client
        if (dossier.getClient() != null && dossier.getClient().getIdClient() != null) {

            Client client = clientRepo.findById(dossier.getClient().getIdClient())
                    .orElse(null);

            dossier.setClient(client);
        }

        return repo.save(dossier);
    }

    // ❤️ UPDATE AVEC CLIENT
    public DossierClient update(Integer id, DossierClient dossier) {

        dossier.setId(id);

        if (dossier.getClient() != null && dossier.getClient().getIdClient() != null) {

            Client client = clientRepo.findById(dossier.getClient().getIdClient())
                    .orElse(null);

            dossier.setClient(client);
        }

        return repo.save(dossier);
    }

    public void delete(Integer id) {
        repo.deleteById(id);
    }
}