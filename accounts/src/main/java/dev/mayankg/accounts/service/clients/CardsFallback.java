package dev.mayankg.accounts.service.clients;

import dev.mayankg.accounts.dto.CardsDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public final class CardsFallback implements CardsFeignClient{

    /**
     * Fetches the card details of the customer holding an account in FinVista Nexus
     *
     * @param correlationId
     * @param mobileNumber
     * @return Cards Details of the customer
     */
    @Override
    public ResponseEntity<CardsDto> fetchCardDetails(String correlationId, String mobileNumber) {
        return null;
    }
}
