package dev.mayankg.accounts.service.impl;

import dev.mayankg.accounts.constants.AccountsEnum;
import dev.mayankg.accounts.dto.CustomerDto;
import dev.mayankg.accounts.entity.Accounts;
import dev.mayankg.accounts.entity.Customer;
import dev.mayankg.accounts.exception.CustomerAlreadyExistingException;
import dev.mayankg.accounts.mapper.CustomerMapper;
import dev.mayankg.accounts.repository.AccountsRepository;
import dev.mayankg.accounts.repository.CustomerRepository;
import dev.mayankg.accounts.service.IAccountsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountsServiceImpl implements IAccountsService {

    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;

    /**
     * @param customerDto - CustomerDto object
     */
    @Override
    public void createAccount(CustomerDto customerDto) {
        Customer customer = CustomerMapper.mapToCustomer(customerDto);
        Optional<Customer> optionalCustomer = customerRepository.findByMobileNumber(customer.getMobileNumber());
        if (optionalCustomer.isPresent()) {
            throw new CustomerAlreadyExistingException(
                    String.format("Customer already registered with this %s mobile number", customerDto.getMobileNumber()));
        }
        customer.setCreatedBy("user");
        customer.setCreatedAt(LocalDateTime.now());
        Customer savedCustomer = customerRepository.save(customer);
        accountsRepository.save(createNewAccount(savedCustomer));
    }

    /**
     * @param customer
     * @return newly created account
     */
    private Accounts createNewAccount(Customer customer) {
        Accounts newAccount = new Accounts();
        newAccount.setCustomerId(customer.getCustomerId());
        long accountNumber = 1000000000L + new Random().nextInt(900000000);
        newAccount.setAccountNumber(accountNumber);
        newAccount.setAccountType(AccountsEnum.AccountType.SAVINGS.getValue());
        newAccount.setBranchAddress(AccountsEnum.Address.MAIN.getValue());
        newAccount.setCreatedBy("user");
        newAccount.setCreatedAt(LocalDateTime.now());
        return newAccount;
    }
}