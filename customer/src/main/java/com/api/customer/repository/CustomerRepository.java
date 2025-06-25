package com.api.customer.repository;

import com.api.customer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, UUID> {
    /**
     * Retrieves a customer entity by its unique identifier
     * @param customerId
     * @return customer if found
     */
    @Override
    Optional<Customer> findById(UUID customerId);

    /**
     *
     * Check if Customer with the email address exists
     * @param emailAddress
     * @return true if customer with given email id exists
     */
    boolean existsByEmailAddress(String emailAddress);
}
