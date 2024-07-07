package dev.mayankg.accounts.dto;

/**
 * @param accountNumber
 * @param name
 * @param email
 * @param mobileNumber
 */
public record AccountsMessageDto(Long accountNumber, String name, String email, String mobileNumber) {
}