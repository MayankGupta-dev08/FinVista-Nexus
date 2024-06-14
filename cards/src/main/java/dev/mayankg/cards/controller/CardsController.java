package dev.mayankg.cards.controller;

import dev.mayankg.cards.constants.CardsEnum;
import dev.mayankg.cards.dto.CardsDto;
import dev.mayankg.cards.dto.ErrorResponseDto;
import dev.mayankg.cards.dto.ResponseDto;
import dev.mayankg.cards.service.ICardsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "CRUD REST APIs for Cards Service in FinVista Nexus",
        description = "CRUD REST APIs in FinVista Nexus to CREATE, UPDATE, FETCH AND DELETE loan details"
)
@Slf4j
@Validated
@RestController
@AllArgsConstructor
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
public class CardsController {

    private ICardsService cardsService;

    @Operation(
            summary = "Create Card REST API",
            description = "REST API to create new Card inside FinVista Nexus"
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
    public ResponseEntity<ResponseDto> createCard(
            @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile Number must be 10 digits")
            @RequestParam String mobileNumber
    ) {
        log.info("CardsController#createCard");

        cardsService.createCard(mobileNumber);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(CardsEnum.StatusCode.STATUS_201.getValue(),
                        CardsEnum.StatusMessage.MESSAGE_201.getMessage()));
    }


    @Operation(
            summary = "Fetch Card REST API",
            description = "REST API to fetch Card inside FinVista Nexus based on a mobile number"
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
    public ResponseEntity<CardsDto> fetchCard(
            @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile Number must be 10 digits")
            @RequestParam String mobileNumber
    ) {
        log.info("CardsController#fetchCard");

        CardsDto cardsDto = cardsService.fetchCard(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(cardsDto);
    }


    @Operation(
            summary = "Update Card REST API",
            description = "REST API to update Card inside FinVista Nexus based on a account number"
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
    public ResponseEntity<ResponseDto> updateCard(@Valid @RequestBody CardsDto loansDto) {
        log.info("CardsController#updateCard");

        boolean isLoanUpdated = cardsService.updateCard(loansDto);
        if (isLoanUpdated)
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(CardsEnum.StatusCode.STATUS_200.getValue(),
                            CardsEnum.StatusMessage.MESSAGE_200.getMessage()));

        return ResponseEntity
                .status(HttpStatus.EXPECTATION_FAILED)
                .body(new ResponseDto(CardsEnum.StatusCode.STATUS_417.getValue(),
                        CardsEnum.StatusMessage.MESSAGE_417_UPDATE.getMessage()));
    }


    @Operation(
            summary = "Delete Card REST API",
            description = "REST API to delete Card inside FinVista Nexus based on a mobile number"
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
    public ResponseEntity<ResponseDto> deleteCard(
            @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile Number must be 10 digits")
            @RequestParam String mobileNumber
    ) {
        log.info("CardsController#deleteCard");

        boolean isLoanDeleted = cardsService.deleteCard(mobileNumber);
        if (isLoanDeleted)
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(CardsEnum.StatusCode.STATUS_200.getValue(),
                            CardsEnum.StatusMessage.MESSAGE_200.getMessage()));

        return ResponseEntity
                .status(HttpStatus.EXPECTATION_FAILED)
                .body(new ResponseDto(CardsEnum.StatusCode.STATUS_417.getValue(),
                        CardsEnum.StatusMessage.MESSAGE_417_DELETE.getMessage()));
    }
}