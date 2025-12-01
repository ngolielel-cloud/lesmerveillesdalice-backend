package com.moncentre.lesmerveillesdalice.controller;

import com.moncentre.lesmerveillesdalice.dto.AuthRequest;
import com.moncentre.lesmerveillesdalice.dto.AuthResponse;
import com.moncentre.lesmerveillesdalice.dto.RegisterRequest;
import com.moncentre.lesmerveillesdalice.entity.Role;
import com.moncentre.lesmerveillesdalice.entity.Utilisateur;
import com.moncentre.lesmerveillesdalice.repository.RoleRepository;
import com.moncentre.lesmerveillesdalice.repository.UtilisateurRepository;
import com.moncentre.lesmerveillesdalice.security.CustomUserDetails;
import com.moncentre.lesmerveillesdalice.security.JwtService;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    private final UtilisateurRepository utilisateurRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthController(UtilisateurRepository utilisateurRepository,
                          RoleRepository roleRepository,
                          PasswordEncoder passwordEncoder,
                          AuthenticationManager authenticationManager,
                          JwtService jwtService) {

        this.utilisateurRepository = utilisateurRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    // INSCRIPTION
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {

        if (utilisateurRepository.existsByUsername(request.getUsername())) {
            return ResponseEntity.badRequest().body("Nom d'utilisateur déjà pris.");
        }

        if (utilisateurRepository.existsByEmail(request.getEmail())) {
            return ResponseEntity.badRequest().body("Email déjà utilisé.");
        }

        Set<Role> roles = new HashSet<>();
        for (String r : request.getRoles()) {
            Role role = roleRepository.findByNomRole(r)
                    .orElseThrow(() -> new RuntimeException("Rôle introuvable : " + r));
            roles.add(role);
        }

        Utilisateur user = new Utilisateur();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRoles(roles);

        utilisateurRepository.save(user);

        return ResponseEntity.ok("Utilisateur créé avec succès.");
    }

    // LOGIN
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {

        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),   // email OU username
                        request.getPassword()
                )
        );

        CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
        Utilisateur user = userDetails.getUtilisateur();

        String mainRole = user.getRoles().stream()
                .findFirst()
                .map(Role::getNomRole)
                .orElse("UNKNOWN");

        Map<String, Object> claims = new HashMap<>();
        claims.put("role", mainRole);

        String token = jwtService.generateToken(user.getUsername(), claims);

        return ResponseEntity.ok(new AuthResponse(token, mainRole));
    }
    }