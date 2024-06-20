package dev.mayankg.accounts.service.clients;

import dev.mayankg.accounts.dto.LoansDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Declarative class for Loans as Feign Client
 */
@FeignClient("loans")
public interface LoansFeignClient {

    /**
     * Fetches the loan details of the customer holding an account in FinVista Nexus
     *
     * @param mobileNumber
     * @return Loans Details of the customer
     */
    @GetMapping("/api/fetch")
    ResponseEntity<LoansDto> fetchLoansDetails(@RequestParam String mobileNumber);
}
