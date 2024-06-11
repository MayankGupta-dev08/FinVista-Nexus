package dev.mayankg.accounts.mapper;

import dev.mayankg.accounts.dto.CustomerDto;
import dev.mayankg.accounts.entity.Customer;

@SuppressWarnings("unused")
public class CustomerMapper {

    private CustomerMapper() {
    }

    public static Customer mapToCustomer(CustomerDto customerDto) {
        Customer customer = new Customer();
        return mapToCustomer(customerDto, customer);
    }

    public static Customer mapToCustomer(CustomerDto customerDto, Customer customer) {
        customer.setName(customerDto.getName());
        customer.setEmail(customerDto.getEmail());
        customer.setMobileNumber(customerDto.getMobileNumber());
        return customer;
    }

    public static CustomerDto mapToCustomerDto(Customer customer) {
        CustomerDto customerDto = new CustomerDto();
        return mapToCustomerDto(customer, customerDto);
    }

    public static CustomerDto mapToCustomerDto(Customer customer, CustomerDto customerDto) {
        customerDto.setName(customer.getName());
        customerDto.setEmail(customer.getEmail());
        customerDto.setMobileNumber(customer.getMobileNumber());
        return customerDto;
    }

}