package dev.mayankg.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Schema(
        name = "Customer Details",
        description = "Schema to hold Customer, Account, Cards and Loans information"
)
@Data
public class CustomerDetailsDto {

    @Schema(description = "Name of the customer", example = "Techno Sorcerer")
    @NotEmpty(message = "Name can not be a null or empty")
    @Size(min = 5, max = 30, message = "The length of the customer name should be between 5 and 30")
    private String name;

    @Schema(description = "Email address of the customer", example = "techno@sorcerer.com")
    @NotEmpty(message = "Email address can not be a null or empty")
    @Email(message = "Email address should be a valid value")
    private String email;

    @Schema(description = "Mobile Number of the customer", example = "9345432123")
    @NotEmpty(message = "Mobile Number can not be a null or empty")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile Number must be 10 digits")
    private String mobileNumber;

    @Schema(description = "Account details of the Customer")
    private AccountsDto accountsDto;

    @Schema(description = "Cards details of the Customer")
    private CardsDto cardsDto;

    @Schema(description = "Loan details of the Customer")
    private LoansDto loansDto;
}
