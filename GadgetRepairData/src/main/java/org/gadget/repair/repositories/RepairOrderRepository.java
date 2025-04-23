package org.gadget.repair.repositories;

import org.gadget.repair.model.RepairOrder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class RepairOrderRepository {

    private final Map<UUID, RepairOrder> orders = new HashMap<>();

    public void save(RepairOrder order) {
        orders.put(order.getId(), order);
    }

    public Optional<RepairOrder> findById(UUID id) {
        return Optional.ofNullable(orders.get(id));
    }

    public List<RepairOrder> findAll() {
        return new ArrayList<>(orders.values());
    }

    public List<RepairOrder> findByCustomerEmail(String customerEmail) {
        List<RepairOrder> result = new ArrayList<>();
        for (RepairOrder order : orders.values()) {
            if (order.getCustomer().getEmail().equals(customerEmail)) {
                result.add(order);
            }
        }
        return result;
    }

}
