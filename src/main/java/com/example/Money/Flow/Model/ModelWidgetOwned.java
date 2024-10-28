package com.example.Money.Flow.Model;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "widget_owned")
public class ModelWidgetOwned implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "tableau_id")
    private ModelTableau tableau;

    @OneToOne
    @JoinColumn(name = "widget_id")
    private ModelWidget widget;

    @Column(name = "x_position", insertable = true, updatable = true, nullable = false)
    private int x_position;

    @Column(name = "y_position", insertable = true, updatable = true, nullable = false)
    private int y_position;

    public ModelWidgetOwned(){}

    public ModelWidgetOwned(ModelTableau tableau, ModelWidget widget, int x_position, int y_position){
        this.tableau = tableau;
        this.widget = widget;
        this.x_position = x_position;
        this.y_position = y_position;
    }
}
