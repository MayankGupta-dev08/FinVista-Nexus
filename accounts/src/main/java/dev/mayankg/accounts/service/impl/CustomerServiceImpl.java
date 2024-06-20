package dev.mayankg.accounts.service.impl;

import dev.mayankg.accounts.dto.CustomerDetailsDto;
import dev.mayankg.accounts.entity.Accounts;
import dev.mayankg.accounts.entity.Customer;
import dev.mayankg.accounts.exception.ResourceNotFoundException;
import dev.mayankg.accounts.mapper.AccountsMapper;
import dev.mayankg.accounts.mapper.CustomerDetailsMapper;
import dev.mayankg.accounts.repository.AccountsRepository;
import dev.mayankg.accounts.repository.CustomerRepository;
import dev.mayankg.accounts.service.ICustomerService;
import dev.mayankg.accounts.service.clients.CardsFeignClient;
import dev.mayankg.accounts.service.clients.LoansFeignClient;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
@SuppressWarnings("unused")
public class CustomerServiceImpl implements ICustomerService {

    private final AccountsRepository accountsRepository;
    private final CustomerRepository customerRepository;
    private final CardsFeignClient cardsFeignClient;
    private final LoansFeignClient loansFeignClient;

    /**
     * @param mobileNumber - Input Mobile Number
     * @return Customer Details based on a given mobileNumber
     */
    @Override
    public CustomerDetailsDto fetchCustomerDetails(String mobileNumber) {
        log.info("CustomerServiceImpl#fetchCustomerDetails");

        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );
        Accounts account = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );

        CustomerDetailsDto customerDetailsDto = CustomerDetailsMapper.mapToCustomerDto(customer);
        customerDetailsDto.setAccountsDto(AccountsMapper.mapToAccountsDto(account));
        customerDetailsDto.setCardsDto(cardsFeignClient.fetchCardDetails(mobileNumber).getBody());
        customerDetailsDto.setLoansDto(loansFeignClient.fetchLoansDetails(mobileNumber).getBody());
        return customerDetailsDto;
    }
}
