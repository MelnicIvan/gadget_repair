package org.gadget.repair.services;

import org.gadget.repair.model.Gadget;
import org.gadget.repair.repositories.GadgetRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class GadgetService {

    private final GadgetRepository repository;

    public GadgetService(GadgetRepository repository) {
        this.repository = repository;
    }

    public void addGadget(Gadget gadget) {
        repository.save(gadget);
    }

    public Optional<Gadget> findById(UUID id) {
        return repository.findById(id);
    }

    public List<Gadget> getAllGadgets() {
        return repository.findAll();
    }

}
