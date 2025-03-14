package com.example.Money.Flow.controller;

import com.example.Money.Flow.Model.ModelRole;
import com.example.Money.Flow.service.ModelRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

    @Autowired
    private ModelRoleService roleService;

    /**
     * GET - Lister tous les rôles
     */
    @GetMapping
    public ResponseEntity<List<ModelRole>> getAllRoles() {
        List<ModelRole> roles = roleService.getAllRoles();
        return ResponseEntity.ok(roles);
    }

    /**
     * GET - Récupérer un rôle par ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<ModelRole> getRoleById(@PathVariable Long id) {
        return roleService.getRoleById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * POST - Créer un nouveau rôle
     */
    @PostMapping
    public ResponseEntity<ModelRole> createRole(@RequestBody ModelRole role) {
        try {
            ModelRole created = roleService.createRole(role);
            return ResponseEntity.ok(created);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    /**
     * PUT - Mettre à jour un rôle existant
     */
    @PutMapping("/{id}")
    public ResponseEntity<ModelRole> updateRole(@PathVariable Long id, @RequestBody ModelRole role) {
        try {
            ModelRole updated = roleService.updateRole(id, role);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * DELETE - Supprimer un rôle
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRole(@PathVariable Long id) {
        try {
            roleService.deleteRole(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * GET - Récupérer un rôle par son nom
     */
    @GetMapping("/search")
    public ResponseEntity<ModelRole> getRoleByName(@RequestParam String roleName) {
        return roleService.getRoleByName(roleName)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
