package com.api.customer;

import com.api.customer.entity.Customer;
import com.api.customer.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/customers")
@CrossOrigin(origins = "*") // Allow requests from all origins (for testing or public APIs)
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    /**
     * Creates a new customer.
     * @param customer The customer details from the request body (validated).
     * @return 200 OK with created customer, or 400 Bad Request if validation fails.
     */
    @PostMapping
        public ResponseEntity<Customer> createCustomer(@Valid  @RequestBody Customer customer) {
        try{
            Customer createdCustomer = customerService.createCustomer(customer);
            return ResponseEntity.ok().body(createdCustomer);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Retrieves a customer by their UUID.
     * @param id Customer ID from the path.
     * @return 200 OK with customer, or 404 Not Found if not present.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable UUID id) {
        try {
            Customer customer = customerService.getCustomer(id);
            return ResponseEntity.ok().body(customer);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Retrieves a paginated list of all customers.
     * @param pageable Page request parameters like size, page, and sort.
     * @return Page of customers with 200 OK.
     */
    @GetMapping
    public ResponseEntity<Page<Customer>> getAllCustomers(Pageable  pageable) {
        Page<Customer> customers = customerService.getAllCustomers(pageable);
        return ResponseEntity.ok().body(customers);
    }

    /**
     * Updates an existing customer by ID.
     * @param id Customer ID to update.
     * @param customer The updated customer details.
     * @return 200 OK with updated customer, or 400 Bad Request if update fails.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable UUID id, @Valid  @RequestBody Customer customer) {
        try {
            Customer updatedCustomer = customerService.updateCustomer(id, customer);
            return ResponseEntity.ok().body(updatedCustomer);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Deletes a customer by ID.
     * @param id Customer ID to delete.
     * @return 204 No Content on success, or 404 Not Found if customer doesn't exist.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Customer> deleteCustomer(@PathVariable UUID id) {
        try {
            customerService.deleteCustomer(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
