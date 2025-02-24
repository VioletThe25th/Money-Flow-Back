package com.example.Money.Flow.Model;

import jakarta.persistence.*;
import jakarta.validation.Valid;

import java.io.Serializable;

@Entity
@Table(name = "BudgetPartage")
public class ModelBudgetPartage implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Valid
    @ManyToOne
    @JoinColumn(name = "user_id", insertable = true, updatable = false, nullable = false)
    private ModelUser user;

    @Valid
    @ManyToOne
    @JoinColumn(name = "compte_id", insertable = true, updatable = true, nullable = false)
    private ModelCompte compte;

    @Valid
    @ManyToOne
    @JoinColumn(name = "role", insertable = true, updatable = true, nullable = false)
    private ModelRole role;

    public ModelBudgetPartage(){}

    public ModelBudgetPartage(ModelUser user, ModelCompte compte, ModelRole role){
        this.user = user;
        this.compte = compte;
        this.role = role;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public ModelCompte getCompte() {
        return compte;
    }

    public void setCompte(ModelCompte compte) {
        this.compte = compte;
    }

    public ModelRole getRole() {
        return role;
    }

    public void setRole(ModelRole role) {
        this.role = role;
    }

    public ModelUser getUser_id() {
        return user;
    }

    public void setUser_id(ModelUser user_id) {
        this.user = user_id;
    }
}