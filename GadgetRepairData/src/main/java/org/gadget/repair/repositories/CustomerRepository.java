package org.gadget.repair.repositories;

import org.gadget.repair.model.Customer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class CustomerRepository {

    private final Map<String, Customer> customers = new HashMap<>();

    public void save(Customer customer) {
        customers.put(customer.getEmail(), customer);
    }

    public Optional<Customer> findByEmail(String email) {
        return Optional.ofNullable(customers.get(email));
    }

    public List<Customer> findAll() {
        return new ArrayList<>(customers.values());
    }

}
