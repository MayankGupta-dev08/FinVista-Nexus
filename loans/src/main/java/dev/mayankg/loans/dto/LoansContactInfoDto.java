package dev.mayankg.loans.dto;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.List;

@ConfigurationProperties(prefix = "loans")
public record LoansContactInfoDto(String message, HashMap<String, String> contactDetails, List<String> onCallSupport) {
}