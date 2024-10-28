package com.example.Money.Flow.Model;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "BudgetPartage")
public class ModelBudgetPartage implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = true, updatable = false, nullable = false)
    private ModelUser user_id;

    @Column(name = "compte_id", insertable = true, updatable = true, nullable = false)
    private int compte_id;

    @ManyToOne
    @JoinColumn(name = "role", insertable = true, updatable = true, nullable = false)
    private ModelRole role;

    public ModelBudgetPartage(){}

    public ModelBudgetPartage(ModelUser user, int compte_id, ModelRole role){
        this.user_id = user;
        this.compte_id = compte_id;
        this.role = role;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
