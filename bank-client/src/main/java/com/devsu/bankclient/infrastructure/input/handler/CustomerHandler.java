package com.devsu.bankclient.infrastructure.input.handler;

import com.devsu.bankclient.application.port.input.CreateCustomerUseCase;
import com.devsu.bankclient.application.port.input.DeleteCustomerUseCase;
import com.devsu.bankclient.application.port.input.FindAllCustomerUseCase;
import com.devsu.bankclient.application.port.input.UpdateCustomerUseCase;
import com.devsu.bankclient.domain.model.Customer;
import com.devsu.bankclient.infrastructure.input.dto.CustomerRequestDTO;
import com.devsu.bankclient.infrastructure.input.dto.CustomerResponseDTO;
import com.devsu.bankclient.infrastructure.input.mapper.CustomerDTOMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomerHandler {

    private final CreateCustomerUseCase createCustomerUseCase;
    private final DeleteCustomerUseCase deleteCustomerUseCase;
    private final FindAllCustomerUseCase findAllCustomerUseCase;
    private final UpdateCustomerUseCase updateCustomerUseCase;


    public CustomerHandler(CreateCustomerUseCase createCustomerUseCase, DeleteCustomerUseCase deleteCustomerUseCase, FindAllCustomerUseCase findAllCustomerUseCase, UpdateCustomerUseCase updateCustomerUseCase) {
        this.createCustomerUseCase = createCustomerUseCase;
        this.deleteCustomerUseCase = deleteCustomerUseCase;
        this.findAllCustomerUseCase = findAllCustomerUseCase;
        this.updateCustomerUseCase = updateCustomerUseCase;
    }

    public List<CustomerResponseDTO> getCustomers() {
        return findAllCustomerUseCase.execute().stream()
                .map(CustomerDTOMapper::mapToResponse)
                .collect(Collectors.toUnmodifiableList());
    }

    public CustomerResponseDTO createCustomer(CustomerRequestDTO customerRequestDTO) {
        Customer customer = CustomerDTOMapper.mapToEntity(customerRequestDTO);
        return CustomerDTOMapper.mapToResponse(createCustomerUseCase.execute(customer));
    }

    public CustomerResponseDTO updateCustomer(CustomerRequestDTO customerRequestDTO) {
        Customer customer = CustomerDTOMapper.mapToEntity(customerRequestDTO);
        return CustomerDTOMapper.mapToResponse(updateCustomerUseCase.execute(customer));
    }

    public CustomerResponseDTO deleteCustomer(Integer customerId) {
        deleteCustomerUseCase.execute(customerId);
        return new CustomerResponseDTO();
    }
}
