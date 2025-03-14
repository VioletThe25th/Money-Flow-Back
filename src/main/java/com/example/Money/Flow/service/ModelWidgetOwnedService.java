package com.example.Money.Flow.service;

import com.example.Money.Flow.Model.ModelWidgetOwned;
import com.example.Money.Flow.repository.ModelWidgetOwnedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ModelWidgetOwnedService {

    @Autowired
    private ModelWidgetOwnedRepository widgetOwnedRepository;

    /**
     * Lister tous les widgets possédés
     */
    public List<ModelWidgetOwned> getAllWidgetsOwned() {
        return widgetOwnedRepository.findAll();
    }

    /**
     * Récupérer un widget possédé par ID
     */
    public Optional<ModelWidgetOwned> getWidgetOwnedById(Long id) {
        return widgetOwnedRepository.findById(id);
    }

    /**
     * Créer un widget possédé
     */
    public ModelWidgetOwned createWidgetOwned(ModelWidgetOwned widgetOwned) {
        return widgetOwnedRepository.save(widgetOwned);
    }

    /**
     * Mettre à jour un widget possédé
     */
    public ModelWidgetOwned updateWidgetOwned(Long id, ModelWidgetOwned updatedWidgetOwned) {
        return widgetOwnedRepository.findById(id)
                .map(widgetOwned -> {
                    widgetOwned.setTableau(updatedWidgetOwned.getTableau());
                    widgetOwned.setWidget(updatedWidgetOwned.getWidget());
                    widgetOwned.setX_position(updatedWidgetOwned.getX_position());
                    widgetOwned.setY_position(updatedWidgetOwned.getY_position());
                    return widgetOwnedRepository.save(widgetOwned);
                })
                .orElseThrow(() -> new RuntimeException("WidgetOwned introuvable"));
    }

    /**
     * Supprimer un widget possédé
     */
    public void deleteWidgetOwned(Long id) {
        if (!widgetOwnedRepository.existsById(id)) {
            throw new RuntimeException("WidgetOwned introuvable");
        }
        widgetOwnedRepository.deleteById(id);
    }

    /**
     * Lister les widgets possédés d'un tableau spécifique
     */
    public List<ModelWidgetOwned> getWidgetsByTableauId(Long tableauId) {
        return widgetOwnedRepository.findByTableauId(tableauId);
    }

    /**
     * Lister les widgets par widget ID
     */
    public List<ModelWidgetOwned> getWidgetsByWidgetId(Long widgetId) {
        return widgetOwnedRepository.findByWidgetId(widgetId);
    }
}
