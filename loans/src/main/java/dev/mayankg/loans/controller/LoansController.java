package dev.mayankg.loans.controller;

import dev.mayankg.loans.constants.LoansEnum;
import dev.mayankg.loans.dto.ErrorResponseDto;
import dev.mayankg.loans.dto.LoansContactInfoDto;
import dev.mayankg.loans.dto.LoansDto;
import dev.mayankg.loans.dto.ResponseDto;
import dev.mayankg.loans.service.ILoansService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
        name = "CRUD REST APIs for Loans Service in FinVista Nexus",
        description = "CRUD REST APIs in FinVista Nexus to CREATE, UPDATE, FETCH AND DELETE loan details"
)
@Validated
@RestController
@SuppressWarnings("unused")
@RequestMapping(path = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class LoansController {

    private static final Logger logger = LoggerFactory.getLogger(LoansController.class);

    @Value("${build.version}")
    private String buildVersion;

    private final Environment environment;
    private final ILoansService loansService;

    private LoansContactInfoDto loansContactInfoDto;

    @Autowired
    public LoansController(Environment environment, ILoansService loansService) {
        this.environment = environment;
        this.loansService = loansService;
    }

    @Autowired
    public void setLoansContactInfoDto(LoansContactInfoDto loansContactInfoDto) {
        this.loansContactInfoDto = loansContactInfoDto;
    }

    @Operation(
            summary = "Create Loan REST API",
            description = "REST API to create new Loan inside FinVista Nexus"
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
    public ResponseEntity<ResponseDto> createLoan(
            @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile Number must be 10 digits")
            @RequestParam String mobileNumber
    ) {
        logger.info("LoansController#createLoan");
        loansService.createLoan(mobileNumber);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(LoansEnum.StatusCode.STATUS_201.getValue(),
                        LoansEnum.StatusMessage.MESSAGE_201.getMessage()));
    }


    @Operation(
            summary = "Fetch Loan REST API",
            description = "REST API to fetch Loan inside FinVista Nexus based on a mobile number"
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
    public ResponseEntity<LoansDto> fetchLoan(
            @RequestHeader("fvnBank-correlation-id") String correlationId,
            @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile Number must be 10 digits")
            @RequestParam String mobileNumber
    ) {
        logger.debug("LoansController#fetchLoan starts");
        //logger.debug("fvnBank-correlation-id correlation id found: {}", correlationId);
        LoansDto loansDto = loansService.fetchLoan(mobileNumber);
        logger.debug("LoansController#fetchLoan ends");
        return ResponseEntity.status(HttpStatus.OK).body(loansDto);
    }


    @Operation(
            summary = "Update Loan REST API",
            description = "REST API to update Loan inside FinVista Nexus based on a account number"
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
    public ResponseEntity<ResponseDto> updateLoan(@Valid @RequestBody LoansDto loansDto) {
        logger.info("LoansController#updateLoan");
        boolean isLoanUpdated = loansService.updateLoan(loansDto);
        if (isLoanUpdated)
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(LoansEnum.StatusCode.STATUS_200.getValue(),
                            LoansEnum.StatusMessage.MESSAGE_200.getMessage()));

        return ResponseEntity
                .status(HttpStatus.EXPECTATION_FAILED)
                .body(new ResponseDto(LoansEnum.StatusCode.STATUS_417.getValue(),
                        LoansEnum.StatusMessage.MESSAGE_417_UPDATE.getMessage()));
    }


    @Operation(
            summary = "Delete Loan REST API",
            description = "REST API to delete Loan inside FinVista Nexus based on a mobile number"
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
    public ResponseEntity<ResponseDto> deleteLoan(
            @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile Number must be 10 digits")
            @RequestParam String mobileNumber
    ) {
        logger.info("LoansController#deleteLoan");
        boolean isLoanDeleted = loansService.deleteLoan(mobileNumber);
        if (isLoanDeleted)
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(LoansEnum.StatusCode.STATUS_200.getValue(),
                            LoansEnum.StatusMessage.MESSAGE_200.getMessage()));

        return ResponseEntity
                .status(HttpStatus.EXPECTATION_FAILED)
                .body(new ResponseDto(LoansEnum.StatusCode.STATUS_417.getValue(),
                        LoansEnum.StatusMessage.MESSAGE_417_DELETE.getMessage()));
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
    @GetMapping("/build-info")
    public ResponseEntity<String> getBuildInfo() {
        logger.info("LoansController#getBuildInfo");
        return ResponseEntity.status(HttpStatus.OK).body(buildVersion);
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
        logger.info("LoansController#getJavaVersion");
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
    public ResponseEntity<LoansContactInfoDto> getContactInfo() {
        logger.info("LoansController#getContactInfo");
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(loansContactInfoDto);
    }
}