package dev.mayankg.accounts.service.impl;

import dev.mayankg.accounts.dto.CardsDto;
import dev.mayankg.accounts.dto.CustomerDetailsDto;
import dev.mayankg.accounts.dto.LoansDto;
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
import org.springframework.http.ResponseEntity;
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
        log.info("Fetching customer details for mobile number: {}", mobileNumber);

        // Fetch customer details
        Customer customer = customerRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber));
        log.debug("Customer found: {}", customer);

        // Fetch account details
        Accounts account = accountsRepository.findByCustomerId(customer.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString()));
        log.debug("Account found for customer ID {}: {}", customer.getCustomerId(), account);

        // Map customer details
        CustomerDetailsDto customerDetailsDto = CustomerDetailsMapper.mapToCustomerDto(customer);
        customerDetailsDto.setAccountsDto(AccountsMapper.mapToAccountsDto(account));

        // Fetch card details
        try {
            ResponseEntity<CardsDto> cardsDtoResponseEntity = cardsFeignClient.fetchCardDetails(mobileNumber);
            if (cardsDtoResponseEntity != null && cardsDtoResponseEntity.getBody() != null) {
                customerDetailsDto.setCardsDto(cardsDtoResponseEntity.getBody());
                log.debug("Card details found for mobile number {}: {}", mobileNumber, cardsDtoResponseEntity.getBody());
            }
        } catch (Exception e) {
            log.warn("Failed to fetch card details for mobile number {}: {}", mobileNumber, e.getMessage());
        }

        // Fetch loan details
        try {
            ResponseEntity<LoansDto> loansDtoResponseEntity = loansFeignClient.fetchLoansDetails(mobileNumber);
            if (loansDtoResponseEntity != null && loansDtoResponseEntity.getBody() != null) {
                customerDetailsDto.setLoansDto(loansDtoResponseEntity.getBody());
                log.debug("Loan details found for mobile number {}: {}", mobileNumber, loansDtoResponseEntity.getBody());
            }
        } catch (Exception e) {
            log.warn("Failed to fetch loan details for mobile number {}: {}", mobileNumber, e.getMessage());
        }

        return customerDetailsDto;
    }
}
