package org.gadget.repair.repositories;

import org.gadget.repair.model.Gadget;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class GadgetRepository {

    private final Map<UUID, Gadget> gadgets = new HashMap<>();

    public void save(Gadget gadget) {
        gadgets.put(gadget.getId(), gadget);
    }

    public Optional<Gadget> findById(UUID id) {
        return Optional.ofNullable(gadgets.get(id));
    }

    public List<Gadget> findAll() {
        return new ArrayList<>(gadgets.values());
    }

}
