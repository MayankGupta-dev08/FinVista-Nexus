package dev.mayankg.accounts.controller;

import dev.mayankg.accounts.constants.AccountsEnum;
import dev.mayankg.accounts.dto.CustomerDto;
import dev.mayankg.accounts.dto.ResponseDto;
import dev.mayankg.accounts.service.IAccountsService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@AllArgsConstructor
@SuppressWarnings("unused")
@RequestMapping(path = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class AccountsController {

    private IAccountsService accountsService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccount(@RequestBody CustomerDto customerDto) {
        log.info("AccountsController#createAccount");

        accountsService.createAccount(customerDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(AccountsEnum.StatusCode.STATUS_201.getValue(),
                        AccountsEnum.StatusMessage.MESSAGE_201.getMessage()));
    }

    @GetMapping("/fetch")
    public ResponseEntity<CustomerDto> getAccount(@RequestParam String mobileNumber) {
        log.info("AccountsController#getAccount");

        CustomerDto customerDto = accountsService.fetchAccount(mobileNumber);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(customerDto);
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateAccount(@RequestBody CustomerDto customerDto) {
        log.info("AccountsController#updateAccount");

        boolean isAccountUpdated = accountsService.updateAccount(customerDto);
        if (isAccountUpdated)
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(AccountsEnum.StatusCode.STATUS_200.getValue(),
                            AccountsEnum.StatusMessage.MESSAGE_200.getMessage()));

        return ResponseEntity
                .status(HttpStatus.EXPECTATION_FAILED)
                .body(new ResponseDto(AccountsEnum.StatusCode.STATUS_417.getValue(),
                        AccountsEnum.StatusMessage.MESSAGE_417_UPDATE.getMessage()));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteAccount(@RequestParam String mobileNumber) {
        log.info("AccountsController#deleteAccount");

        boolean isAccountDeleted = accountsService.deleteAccount(mobileNumber);
        if (isAccountDeleted)
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(AccountsEnum.StatusCode.STATUS_200.getValue(),
                            AccountsEnum.StatusMessage.MESSAGE_200.getMessage()));

        return ResponseEntity
                .status(HttpStatus.EXPECTATION_FAILED)
                .body(new ResponseDto(AccountsEnum.StatusCode.STATUS_417.getValue(),
                        AccountsEnum.StatusMessage.MESSAGE_417_DELETE.getMessage()));
    }
}