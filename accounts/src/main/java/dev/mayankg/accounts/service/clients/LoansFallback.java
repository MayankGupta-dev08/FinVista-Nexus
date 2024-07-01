package dev.mayankg.accounts.service.clients;

import dev.mayankg.accounts.dto.LoansDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public final class LoansFallback implements LoansFeignClient {

    /**
     * Fetches the loan details of the customer holding an account in FinVista Nexus
     *
     * @param correlationId
     * @param mobileNumber
     * @return Loans Details of the customer
     */
    @Override
    public ResponseEntity<LoansDto> fetchLoansDetails(String correlationId, String mobileNumber) {
        return null;
    }
}
