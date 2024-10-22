package dev.mayankg.accounts.service.impl;

import dev.mayankg.accounts.constants.AccountsEnum;
import dev.mayankg.accounts.dto.AccountsDto;
import dev.mayankg.accounts.dto.AccountsMessageDto;
import dev.mayankg.accounts.dto.CustomerDto;
import dev.mayankg.accounts.entity.Accounts;
import dev.mayankg.accounts.entity.Customer;
import dev.mayankg.accounts.exception.CustomerAlreadyExistingException;
import dev.mayankg.accounts.exception.ResourceNotFoundException;
import dev.mayankg.accounts.mapper.AccountsMapper;
import dev.mayankg.accounts.mapper.CustomerMapper;
import dev.mayankg.accounts.repository.AccountsRepository;
import dev.mayankg.accounts.repository.CustomerRepository;
import dev.mayankg.accounts.service.IAccountsService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountsServiceImpl implements IAccountsService {

    private static final Logger logger = LoggerFactory.getLogger(AccountsServiceImpl.class);

    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;
    private StreamBridge streamBridge;

    /**
     * @param customerDto - CustomerDto object
     */
    @Override
    public void createAccount(CustomerDto customerDto) {
        logger.info("AccountsServiceImpl#createAccount");
        Customer customer = CustomerMapper.mapToCustomer(customerDto);
        Optional<Customer> optionalCustomer = customerRepository.findByMobileNumber(customer.getMobileNumber());
        if (optionalCustomer.isPresent()) {
            throw new CustomerAlreadyExistingException(
                    String.format("Customer already registered with this %s mobile number", customerDto.getMobileNumber()));
        }
        Customer savedCustomer = customerRepository.save(customer);
        Accounts savedAccount = accountsRepository.save(createNewAccount(savedCustomer));

        // sending an async  message from accounts to message m/s
        sendCommunication(savedAccount, savedCustomer);
    }

    /**
     * @param mobileNumber
     * @return customerDto for the respective mobileNumber
     */
    @Override
    public CustomerDto fetchAccount(String mobileNumber) {
        logger.info("AccountsServiceImpl#fetchAccount");
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );
        Accounts account = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );
        CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer);
        customerDto.setAccountsDto(AccountsMapper.mapToAccountsDto(account));
        return customerDto;
    }

    /**
     * @param customerDto - CustomerDto Object
     * @return boolean indicating if the update of Account details is successful or not
     */
    @Override
    public boolean updateAccount(CustomerDto customerDto) {
        logger.info("AccountsServiceImpl#updateAccount");
        boolean isUpdated = false;
        AccountsDto accountsDto = customerDto.getAccountsDto();
        if (accountsDto != null) {
            Long accountNumber = accountsDto.getAccountNumber();
            Accounts theAccount = accountsRepository.findById(accountNumber).orElseThrow(
                    () -> new ResourceNotFoundException("Accounts", "AccountNumber", accountNumber.toString())
            );
            Accounts account = AccountsMapper.mapToAccounts(accountsDto, theAccount);
            accountsRepository.save(account);

            Long customerId = account.getCustomerId();
            Customer theCustomer = customerRepository.findById(customerId).orElseThrow(
                    () -> new ResourceNotFoundException("Customer", "CustomerID", customerId.toString())
            );
            Customer customer = CustomerMapper.mapToCustomer(customerDto, theCustomer);
            customerRepository.save(customer);
            isUpdated = true;
        }

        return isUpdated;
    }

    /**
     * @param mobileNumber - Input Mobile Number
     * @return boolean indicating if the deleting of Account details is successful or not
     */
    @Override
    public boolean deleteAccount(String mobileNumber) {
        logger.info("AccountsServiceImpl#deleteAccount");
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );
        accountsRepository.deleteByCustomerId(customer.getCustomerId());
        customerRepository.deleteById(customer.getCustomerId());
        return true;
    }

    /**
     * @param accountNumber - Long
     * @return boolean indicating if the update of communication status is successful or not
     */
    @Override
    public boolean updateCommunicationStatus(Long accountNumber) {
        boolean isUpdated = false;
        if (accountNumber != null) {
            Accounts accounts = accountsRepository.findById(accountNumber).orElseThrow(
                    () -> new ResourceNotFoundException("Account", "AccountNumber", accountNumber.toString())
            );
            accounts.setCommunicationSw(true);
            accountsRepository.save(accounts);
            isUpdated = true;
        }
        return isUpdated;
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
        return newAccount;
    }

    private void sendCommunication(Accounts account, Customer customer) {
        AccountsMessageDto accountsMessageDto = new AccountsMessageDto(
                account.getAccountNumber(), customer.getName(), customer.getEmail(), customer.getMobileNumber());
        logger.info("Sending Communication request for the details: {}", accountsMessageDto);
        boolean isSent = streamBridge.send("sendCommunication-out-0", accountsMessageDto);
        logger.info("Is the Communication request successfully triggered ? : {}", isSent);
    }
}