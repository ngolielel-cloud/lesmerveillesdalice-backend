package com.moncentre.lesmerveillesdalice.controller;

import com.moncentre.lesmerveillesdalice.entity.Role;
import com.moncentre.lesmerveillesdalice.entity.Utilisateur;
import com.moncentre.lesmerveillesdalice.repository.RoleRepository;
import com.moncentre.lesmerveillesdalice.repository.UtilisateurRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/utilisateurs")
@CrossOrigin(origins = "http://localhost:4200")
public class UtilisateurController {

    private final UtilisateurRepository utilisateurRepository;
    private final RoleRepository roleRepository;

    public UtilisateurController(UtilisateurRepository utilisateurRepository,
                                 RoleRepository roleRepository) {
        this.utilisateurRepository = utilisateurRepository;
        this.roleRepository = roleRepository;
    }

    // 🔹 Liste des utilisateurs
    @GetMapping
    public List<Utilisateur> getAll() {
        return utilisateurRepository.findAll();
    }

    // 🔹 Récupérer un utilisateur par ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id) {
        return utilisateurRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 🔹 Activer / désactiver un compte
    @PutMapping("/{id}/statut")
    public ResponseEntity<?> updateStatut(@PathVariable Long id, @RequestParam boolean actif) {

        Utilisateur u = utilisateurRepository.findById(id)
                .orElse(null);

        if (u == null) return ResponseEntity.notFound().build();

        u.setActif(actif);
        utilisateurRepository.save(u);

        return ResponseEntity.ok("Statut modifié avec succès.");
    }

    // 🔹 Modifier les rôles d’un utilisateur
    @PutMapping("/{id}/roles")
    public ResponseEntity<?> updateRoles(@PathVariable Long id, @RequestBody List<String> rolesNames) {

        Utilisateur u = utilisateurRepository.findById(id)
                .orElse(null);

        if (u == null) return ResponseEntity.notFound().build();

        Set<Role> roles = new HashSet<>();

        for (String nom : rolesNames) {
            Role role = roleRepository.findByNomRole(nom)
                    .orElseThrow(() -> new RuntimeException("Rôle introuvable : " + nom));
            roles.add(role);
        }

        u.setRoles(roles);
        utilisateurRepository.save(u);

        return ResponseEntity.ok("Rôles mis à jour.");
    }

    // 🔹 Supprimer un utilisateur
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        if (!utilisateurRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        utilisateurRepository.deleteById(id);
        return ResponseEntity.ok("Utilisateur supprimé.");
    }
}