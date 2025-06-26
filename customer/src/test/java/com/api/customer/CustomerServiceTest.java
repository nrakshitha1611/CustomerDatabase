package com.api.customer;

import com.api.customer.entity.Customer;
import com.api.customer.repository.CustomerRepository;
import com.api.customer.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;

    @Test
    public void testCreateCustomer() {
        //Mock customer details, this is the given information
        Customer customer = new Customer("Rakshitha", "Nagaraj", "rakshitha123@email.com");
        customer.setPhoneNumber("123456789");
        when(customerRepository.existsByEmailAddress(customer.getEmailAddress())).thenReturn(false);
        when(customerRepository.save(customer)).thenReturn(customer);

        //When
        Customer result = customerService.createCustomer(customer);

        //Results verified: Then
        assertNotNull(result);
        assertEquals("Rakshitha", result.getFirstName());
        verify(customerRepository).save(customer);
    }

    @Test
    public void testExceptionWhenEmailExists() {

        //Mock details
        Customer customer = new Customer("Ray", "Johnson", "ray123@email.com");
        when(customerRepository.existsByEmailAddress(anyString())).thenReturn(true);

        //Assertion check verifying results
        assertThrows(RuntimeException.class, () -> customerService.createCustomer(customer));
    }
}
