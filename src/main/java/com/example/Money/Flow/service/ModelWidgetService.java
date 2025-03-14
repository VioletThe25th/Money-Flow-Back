package com.example.Money.Flow.service;

import com.example.Money.Flow.Model.ModelWidget;
import com.example.Money.Flow.repository.ModelWidgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ModelWidgetService {

    @Autowired
    private ModelWidgetRepository widgetRepository;

    /**
     * Lister tous les widgets
     */
    public List<ModelWidget> getAllWidgets() {
        return widgetRepository.findAll();
    }

    /**
     * Récupérer un widget par ID
     */
    public Optional<ModelWidget> getWidgetById(Long id) {
        return widgetRepository.findById(id);
    }

    /**
     * Créer un nouveau widget
     */
    public ModelWidget createWidget(ModelWidget widget) {
        // Option : éviter la duplication de libellé
        if (widgetRepository.existsByLibelle(widget.getLibelle())) {
            throw new RuntimeException("Un widget avec ce libellé existe déjà !");
        }
        return widgetRepository.save(widget);
    }

    /**
     * Mettre à jour un widget existant
     */
    public ModelWidget updateWidget(Long id, ModelWidget updatedWidget) {
        return widgetRepository.findById(id)
                .map(widget -> {
                    widget.setLibelle(updatedWidget.getLibelle());
                    return widgetRepository.save(widget);
                })
                .orElseThrow(() -> new RuntimeException("Widget introuvable"));
    }

    /**
     * Supprimer un widget
     */
    public void deleteWidget(Long id) {
        if (!widgetRepository.existsById(id)) {
            throw new RuntimeException("Widget introuvable");
        }
        widgetRepository.deleteById(id);
    }

    /**
     * Récupérer un widget par son libellé
     */
    public Optional<ModelWidget> getWidgetByLibelle(String libelle) {
        return widgetRepository.findByLibelle(libelle);
    }
}
