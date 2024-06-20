package dev.mayankg.accounts.controller;

import dev.mayankg.accounts.dto.CustomerDetailsDto;
import dev.mayankg.accounts.dto.ErrorResponseDto;
import dev.mayankg.accounts.service.ICustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Pattern;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Mayanak Gupta - dev.mayankg
 */
@Tag(
        name = "REST API for Customers in FinVista Nexus",
        description = "REST API for Customers to fetch Customer details in FinVista Nexus"
)
@Slf4j
@Validated
@RestController
@SuppressWarnings("unused")
@RequestMapping(path = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class CustomerController {

    private final ICustomerService customerService;

    @Autowired
    public CustomerController(ICustomerService customerService) {
        this.customerService = customerService;
    }

    @Operation(
            summary = "Fetch Customer Details REST API",
            description = "REST API to fetch Customer Details of FinVista Nexus based on mobile number"
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
    @GetMapping("/fetchCustomerDetails")
    public ResponseEntity<CustomerDetailsDto> fetchCustomerDetails(
            @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile Number must be 10 digits")
            @RequestParam String mobileNumber
    ) {
        log.info("CustomerController#fetchCustomerDetails");

        CustomerDetailsDto customerDetailsDto = customerService.fetchCustomerDetails(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(customerDetailsDto);
    }
}
