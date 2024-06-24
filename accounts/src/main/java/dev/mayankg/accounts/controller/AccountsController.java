package dev.mayankg.accounts.controller;

import dev.mayankg.accounts.constants.AccountsEnum;
import dev.mayankg.accounts.dto.AccountsContactInfoDto;
import dev.mayankg.accounts.dto.CustomerDto;
import dev.mayankg.accounts.dto.ErrorResponseDto;
import dev.mayankg.accounts.dto.ResponseDto;
import dev.mayankg.accounts.service.IAccountsService;
import io.github.resilience4j.retry.annotation.Retry;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author Mayanak Gupta - dev.mayankg
 */
@Tag(
        name = "CRUD REST APIs for Accounts in FinVista Nexus",
        description = "CRUD REST APIs in FinVista Nexus to CREATE, UPDATE, FETCH AND DELETE account details"
)
@Slf4j
@RestController
@Validated
@SuppressWarnings("unused")
@RequestMapping(path = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class AccountsController {

    @Value("${build.version}")
    private String buildVersion;

    private final Environment environment;

    private final IAccountsService accountsService;

    private AccountsContactInfoDto accountsContactInfoDto;

    @Autowired
    public AccountsController(Environment environment, IAccountsService accountsService) {
        this.environment = environment;
        this.accountsService = accountsService;
    }

    @Autowired
    public void setAccountsContactInfoDto(AccountsContactInfoDto accountsContactInfoDto) {
        this.accountsContactInfoDto = accountsContactInfoDto;
    }

    @Operation(
            summary = "Create Account REST API",
            description = "REST API to create new Customer & Account inside FinVista Nexus"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP Status CREATED"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customerDto) {
        log.info("AccountsController#createAccount");

        accountsService.createAccount(customerDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(AccountsEnum.StatusCode.STATUS_201.getValue(),
                        AccountsEnum.StatusMessage.MESSAGE_201.getMessage()));
    }


    @Operation(
            summary = "Fetch Account REST API",
            description = "REST API to fetch Customer & Account inside FinVista Nexus based on a mobile number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP Status CREATED"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @GetMapping("/fetch")
    public ResponseEntity<CustomerDto> getAccount(
            @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile Number must be 10 digits")
            @RequestParam String mobileNumber
    ) {
        log.info("AccountsController#getAccount");

        CustomerDto customerDto = accountsService.fetchAccount(mobileNumber);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(customerDto);
    }


    @Operation(
            summary = "Update Account Details REST API",
            description = "REST API to update Customer & Account inside FinVista Nexus based on a account number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Expectation Failed"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateAccount(@Valid @RequestBody CustomerDto customerDto) {
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


    @Operation(
            summary = "Delete Account & Customer Details REST API",
            description = "REST API to delete Customer & Account inside FinVista Nexus based on a mobile number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Expectation Failed"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteAccount(
            @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile Number must be 10 digits")
            @RequestParam String mobileNumber
    ) {
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

    @Operation(
            summary = "Get Build information",
            description = "Get Build information that is deployed into accounts microservice"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP Status CREATED"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @Retry(name = "getBuildInfo", fallbackMethod = "getBuildInfoFallback")
    @GetMapping("/build-info")
    public ResponseEntity<String> getBuildInfo() {
        log.info("AccountsController#getBuildInfo");
        return ResponseEntity.status(HttpStatus.OK).body(buildVersion);
    }

    public ResponseEntity<String> getBuildInfoFallback(Throwable throwable) {
        log.info("AccountsController#getBuildInfoFallback");
        return ResponseEntity.status(HttpStatus.OK).body("v3.3.0");
    }

    @Operation(
            summary = "Get Java version",
            description = "Get Java versions details that is installed into accounts microservice"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP Status CREATED"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @GetMapping("/java-version")
    public ResponseEntity<String> getJavaVersion() {
        log.info("AccountsController#getJavaVersion");
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(environment.getProperty("JAVA_HOME"));
    }

    @Operation(
            summary = "Get Contact Info",
            description = "Contact Info details that can be reached out in case of any issues"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP Status CREATED"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @GetMapping("/contact-info")
    public ResponseEntity<AccountsContactInfoDto> getContactInfo() {
        log.info("AccountsController#getContactInfo");
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(accountsContactInfoDto);
    }
}