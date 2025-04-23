package org.gadget.repair.services;

import org.gadget.repair.model.Customer;
import org.gadget.repair.repositories.CustomerRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class CustomerService {

    private final CustomerRepository repository;

    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    public void registerCustomer(Customer customer) {
        repository.save(customer);
    }

    public Optional<Customer> findByEmail(String email) {
        return repository.findByEmail(email);
    }

    public List<Customer> getAllCustomers() {
        return repository.findAll();
    }

}
