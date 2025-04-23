package org.gadget.repair;

import org.gadget.repair.enums.GadgetType;
import org.gadget.repair.enums.RepairStatus;
import org.gadget.repair.model.Customer;
import org.gadget.repair.model.Gadget;
import org.gadget.repair.model.RepairOrder;
import org.gadget.repair.repositories.CustomerRepository;
import org.gadget.repair.repositories.GadgetRepository;
import org.gadget.repair.repositories.RepairOrderRepository;
import org.gadget.repair.services.CustomerService;
import org.gadget.repair.services.GadgetService;
import org.gadget.repair.services.RepairOrderService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CustomerRepository customerRepo = new CustomerRepository();
        GadgetRepository gadgetRepo = new GadgetRepository();
        RepairOrderRepository orderRepo = new RepairOrderRepository();

        CustomerService customerService = new CustomerService(customerRepo);
        GadgetService gadgetService = new GadgetService(gadgetRepo);
        RepairOrderService orderService = new RepairOrderService(orderRepo);

        while (true) {
            System.out.println("""
                    === Gadget Repair Service ===
                    1. Register Customer
                    2. Add Gadget
                    3. Create Repair Order
                    4. Update Order Status
                    5. View Orders by Customer
                    0. Exit
                    """);

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1 -> {
                    System.out.println("Enter first name:");
                    String firstName = scanner.nextLine();
                    System.out.println("Enter last name:");
                    String lastName = scanner.nextLine();
                    System.out.println("Enter email:");
                    String email = scanner.nextLine();
                    System.out.println("Enter telephone:");
                    String phone = scanner.nextLine();

                    Customer customer = new Customer(UUID.randomUUID(), firstName, lastName, email, phone);
                    customerService.registerCustomer(customer);
                    System.out.println("Customer registered.\n");
                }

                case 2 -> {
                    System.out.println("Enter gadget name:");
                    String name = scanner.nextLine();
                    System.out.println("Select type (SMARTPHONE, PC, LAPTOP, TABLET, HEADPHONES):");
                    GadgetType type = GadgetType.valueOf(scanner.nextLine().toUpperCase());

                    Gadget gadget = new Gadget(UUID.randomUUID(), name, type, RepairStatus.NEW);
                    gadgetService.addGadget(gadget);
                    System.out.println("Gadget added.");
                }

                case 3 -> {
                    System.out.println("Enter customer email:");
                    String customerEmail = scanner.nextLine();
                    System.out.println("Enter gadget ID:");
                    UUID gadgetId = UUID.fromString(scanner.nextLine());
                    System.out.println("Describe the problem:");
                    String problem = scanner.nextLine();

                    Optional<Customer> customer = customerService.findByEmail(customerEmail);
                    Optional<Gadget> gadget = gadgetService.findById(gadgetId);

                    if (customer.isPresent() && gadget.isPresent()) {
                        RepairOrder order = new RepairOrder(
                                UUID.randomUUID(),
                                gadget.get(),
                                customer.get(),
                                LocalDateTime.now(),
                                problem
                        );
                        orderService.createOrder(order);
                        System.out.println("Repair order created.");
                    } else {
                        System.out.println("Invalid IDs provided.");
                    }
                }

                case 4 -> {
                    System.out.println("Enter order ID:");
                    UUID orderId = UUID.fromString(scanner.nextLine());
                    System.out.println("Enter new status (NEW, IN_PROGRESS, COMPLETED, CANCELED):");
                    RepairStatus status = RepairStatus.valueOf(scanner.nextLine().toUpperCase());

                    orderService.updateStatus(orderId, status);
                    System.out.println("Status updated.");
                }

                case 5 -> {
                    System.out.println("Enter customer email:");
                    String customerEmail = scanner.nextLine();

                    List<RepairOrder> orders = orderService.getOrdersByCustomerEmail(customerEmail);
                    if (orders.isEmpty()) {
                        System.out.println("No orders found.");
                    } else {
                        for (RepairOrder order : orders) {
                            System.out.printf("Order ID: %s, Gadget: %s, Status: %s, Problem: %s%n",
                                    order.getId(),
                                    order.getGadget().getName(),
                                    order.getGadget().getRepairStatus(),
                                    order.getProblemDescription());
                        }
                    }
                }

                case 0 -> {
                    System.out.println("Goodbye!");
                    return;
                }

                default -> System.out.println("Invalid option.");
            }
        }
    }
}
