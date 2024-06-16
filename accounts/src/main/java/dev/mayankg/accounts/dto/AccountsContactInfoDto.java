package dev.mayankg.accounts.dto;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.List;

@ConfigurationProperties(prefix = "accounts")
public record AccountsContactInfoDto(String message, HashMap<String, String> contactDetails, List<String> onCallSupport) {
}