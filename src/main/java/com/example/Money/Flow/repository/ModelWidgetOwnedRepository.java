package com.example.Money.Flow.repository;

import com.example.Money.Flow.Model.ModelWidgetOwned;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModelWidgetOwnedRepository extends JpaRepository<ModelWidgetOwned, Long> {

    // Lister tous les widgets d’un tableau
    List<ModelWidgetOwned> findByTableauId(Long tableauId);

    // (Optionnel) Chercher par widget si besoin
    List<ModelWidgetOwned> findByWidgetId(Long widgetId);
}
