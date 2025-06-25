package com.api.customer.service;

import com.api.customer.entity.Customer;
import com.api.customer.repository.CustomerRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    //Insert a new Customer into the DB
    public Customer createCustomer(Customer customer) {
        if (customerRepository.existsByEmailAddress(customer.getEmailAddress())) {
            throw new RuntimeException("Customer already exists with email address " + customer.getEmailAddress());
        }
        return customerRepository.save(customer);
    }

    //Fetch Customer Details
    @Transactional(readOnly = true)
    public Customer getCustomer(UUID id){
        if (customerRepository.existsById(id)) {
            return customerRepository.findById(id).get();
        }
        else {
            throw new RuntimeException("Customer not found with id " + id);
        }
    }

    //Update Customer Details
    public Customer updateCustomer(UUID id, Customer customer) {
        Customer user =  getCustomer(id);

        //Check if email has changed and is unique
        if (!user.getEmailAddress().equals(customer.getEmailAddress())
        && customerRepository.existsByEmailAddress(customer.getEmailAddress())) {
            throw new RuntimeException("Customer already exists with email address " + customer.getEmailAddress());
        }
        user.setFirstName(customer.getFirstName());
        user.setMiddleName(customer.getMiddleName());
        user.setLastName(customer.getLastName());
        user.setEmailAddress(customer.getEmailAddress());
        user.setPhoneNumber(customer.getPhoneNumber());

        return customerRepository.save(user);
    }

    //Delete Customer
    public void deleteCustomer(UUID id) {
        if (customerRepository.existsById(id)) {
            customerRepository.deleteById(id);
        }
        else {
            throw new RuntimeException("Customer not found with id " + id);
        }
    }
}
