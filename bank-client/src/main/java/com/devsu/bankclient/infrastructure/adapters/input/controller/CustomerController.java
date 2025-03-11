package com.devsu.bankclient.infrastructure.adapters.input.controller;

import com.devsu.bankclient.infrastructure.adapters.input.dto.CustomerRequestDTO;
import com.devsu.bankclient.infrastructure.adapters.input.dto.CustomerResponseDTO;
import com.devsu.bankclient.infrastructure.adapters.input.handler.CustomerHandler;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/customer")
public class CustomerController {

    private final CustomerHandler customerHandler;

    public CustomerController(CustomerHandler customerHandler) {
        this.customerHandler = customerHandler;
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponseDTO>> findAll() {
        return new ResponseEntity<>(customerHandler.getCustomers(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CustomerResponseDTO> createCustomer(@Valid @RequestBody CustomerRequestDTO customerRequestDTO) {
        CustomerResponseDTO response = customerHandler.createCustomer(customerRequestDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<CustomerResponseDTO> updateCustomer(@Valid @RequestBody CustomerRequestDTO customerRequestDTO) {
        CustomerResponseDTO response = customerHandler.updateCustomer(customerRequestDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{identification}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Integer identification) {
        customerHandler.deleteCustomer(identification);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
