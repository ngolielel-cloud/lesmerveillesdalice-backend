package com.moncentre.lesmerveillesdalice.service;

import com.moncentre.lesmerveillesdalice.dto.RegisterRequest;
import com.moncentre.lesmerveillesdalice.entity.Role;
import com.moncentre.lesmerveillesdalice.entity.Utilisateur;
import com.moncentre.lesmerveillesdalice.repository.RoleRepository;
import com.moncentre.lesmerveillesdalice.repository.UtilisateurRepository;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UtilisateurService {

    private final UtilisateurRepository utilisateurRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder encoder;

    public UtilisateurService(UtilisateurRepository utilisateurRepository,
                              RoleRepository roleRepository,
                              PasswordEncoder encoder) {
        this.utilisateurRepository = utilisateurRepository;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
    }

    public Utilisateur register(RegisterRequest req) {

        Set<Role> roles = new HashSet<>();
        for (String r : req.getRoles()) {
            Role role = roleRepository.findByNomRole(r)
                    .orElseThrow(() -> new RuntimeException("Rôle introuvable : " + r));
            roles.add(role);
        }

        Utilisateur u = new Utilisateur();
        u.setUsername(req.getUsername());
        u.setEmail(req.getEmail());
        u.setPassword(encoder.encode(req.getPassword()));
        u.setRoles(roles);
        u.setActif(true);

        return utilisateurRepository.save(u);
    }
}