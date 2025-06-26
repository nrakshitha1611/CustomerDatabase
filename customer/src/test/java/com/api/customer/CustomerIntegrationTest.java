package com.api.customer;

import com.api.customer.entity.Customer;
import com.api.customer.repository.CustomerRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureWebMvc
@Transactional
public class CustomerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testCreateAndRetrieveCustomer() throws Exception {
        //Create Customer Mock
        Customer customer = new Customer("Ray", "Johnson", "ray123@email.com");

        String response = mockMvc.perform(post("/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(customer)))
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();

        Customer createdCustomer = objectMapper.readValue(response, Customer.class);

        //Retrieving customer
        mockMvc.perform(get("api/customers"+ createdCustomer.getCustomerId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Ray"))
                .andExpect(jsonPath("$.emailAddress").value("ray123@email.com"));

    }
}
