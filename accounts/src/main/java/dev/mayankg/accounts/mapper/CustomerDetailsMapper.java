package dev.mayankg.accounts.mapper;

import dev.mayankg.accounts.dto.CustomerDetailsDto;
import dev.mayankg.accounts.entity.Customer;

public final class CustomerDetailsMapper {

    private CustomerDetailsMapper() {
        // restricting instantiation
    }

    public static CustomerDetailsDto mapToCustomerDto(Customer customer) {
        CustomerDetailsDto customerDetailsDto = new CustomerDetailsDto();
        return mapToCustomerDto(customer, customerDetailsDto);
    }

    public static CustomerDetailsDto mapToCustomerDto(Customer customer, CustomerDetailsDto customerDetailsDto) {
        customerDetailsDto.setName(customer.getName());
        customerDetailsDto.setEmail(customer.getEmail());
        customerDetailsDto.setMobileNumber(customer.getMobileNumber());
        return customerDetailsDto;
    }
}