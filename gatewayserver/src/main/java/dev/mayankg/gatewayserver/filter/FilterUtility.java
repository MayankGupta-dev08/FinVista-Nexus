package dev.mayankg.gatewayserver.filter;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.util.List;

@Component
public class FilterUtility {

    public static final String CORRELATION_ID = "fvnBank-correlation-id";

    public String getCorrelationId(HttpHeaders httpRequestHeaders) {
        List<String> correlationIds = httpRequestHeaders.get(CORRELATION_ID);
        if (correlationIds != null)
            return correlationIds.stream().findFirst().get();

        return null;
    }

    public ServerWebExchange setCorrelationId(ServerWebExchange exchange, String correlationId) {
        return this.setRequestHeader(exchange, CORRELATION_ID, correlationId);
    }

    private ServerWebExchange setRequestHeader(ServerWebExchange exchange, String name, String value) {
        // Mutate the current exchange to allow modification
        return exchange.mutate()
                // Mutate the request within the exchange
                .request(exchange.getRequest().mutate()
                        // Set the specified header
                        .header(name, value)
                        // Build the modified request
                        .build())
                // Build the modified exchange with the new request
                .build();
    }

}
