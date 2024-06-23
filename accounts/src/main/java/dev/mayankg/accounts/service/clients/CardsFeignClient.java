package dev.mayankg.accounts.service.clients;

import dev.mayankg.accounts.dto.CardsDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Declarative class for Cards as Feign Client
 */
@FeignClient("cards")
public interface CardsFeignClient {

    /**
     * Fetches the card details of the customer holding an account in FinVista Nexus
     *
     * @param mobileNumber
     * @return Cards Details of the customer
     */
    @GetMapping("/api/fetch")
    ResponseEntity<CardsDto> fetchCardDetails(
            @RequestHeader("fvnBank-correlation-id") String correlationId,
            @RequestParam String mobileNumber
    );
}
