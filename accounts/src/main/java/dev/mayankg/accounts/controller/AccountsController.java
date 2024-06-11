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
}