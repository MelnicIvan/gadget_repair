package org.gadget.repair.services;

import org.gadget.repair.enums.RepairStatus;
import org.gadget.repair.model.RepairOrder;
import org.gadget.repair.repositories.RepairOrderRepository;

import java.util.List;
import java.util.UUID;

public class RepairOrderService {

    private final RepairOrderRepository orderRepository;

    public RepairOrderService(RepairOrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void createOrder(RepairOrder order) {
        orderRepository.save(order);
    }

    public void updateStatus(UUID orderId, RepairStatus status) {
        orderRepository.findById(orderId).ifPresent(order -> order.getGadget().setRepairStatus(status));
    }

    public List<RepairOrder> getOrdersByCustomerEmail(String customerEmail) {
        return orderRepository.findByCustomerEmail(customerEmail);
    }

    public List<RepairOrder> getAllOrders() {
        return orderRepository.findAll();
    }

}
