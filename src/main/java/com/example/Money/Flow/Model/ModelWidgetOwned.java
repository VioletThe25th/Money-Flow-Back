package com.example.Money.Flow.Model;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.io.Serializable;

@Entity
@Table(name = "widget_owned")
public class ModelWidgetOwned implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Valid
    @OneToOne
    @JoinColumn(name = "tableau_id")
    private ModelTableau tableau;

    @Valid
    @ManyToOne
    @JoinColumn(name = "widget_id")
    private ModelWidget widget;

    @NotNull
    @Positive
    @Column(name = "x_position", insertable = true, updatable = true, nullable = false)
    private int x_position;

    @NotNull
    @Positive
    @Column(name = "y_position", insertable = true, updatable = true, nullable = false)
    private int y_position;

    public ModelWidgetOwned(){}

    public ModelWidgetOwned(ModelTableau tableau, ModelWidget widget, int x_position, int y_position){
        this.tableau = tableau;
        this.widget = widget;
        this.x_position = x_position;
        this.y_position = y_position;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ModelTableau getTableau() {
        return tableau;
    }

    public void setTableau(ModelTableau tableau) {
        this.tableau = tableau;
    }

    public ModelWidget getWidget() {
        return widget;
    }

    public void setWidget(ModelWidget widget) {
        this.widget = widget;
    }

    public int getX_position() {
        return x_position;
    }

    public void setX_position(int x_position) {
        this.x_position = x_position;
    }

    public int getY_position() {
        return y_position;
    }

    public void setY_position(int y_position) {
        this.y_position = y_position;
    }
}
