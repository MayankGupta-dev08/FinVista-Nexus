package dev.mayankg.accounts.mapper;

import dev.mayankg.accounts.dto.AccountsDto;
import dev.mayankg.accounts.entity.Accounts;

@SuppressWarnings("unused")
public class AccountsMapper {

    private AccountsMapper() {
    }

    public static Accounts mapToAccounts(AccountsDto accountsDto) {
        Accounts accounts = new Accounts();
        return mapToAccounts(accountsDto, accounts);
    }

    public static Accounts mapToAccounts(AccountsDto accountsDto, Accounts accounts) {
        accounts.setAccountNumber(accountsDto.getAccountNumber());
        accounts.setAccountType(accountsDto.getAccountType());
        accounts.setBranchAddress(accountsDto.getBranchAddress());
        return accounts;
    }

    public static AccountsDto mapToAccountsDto(Accounts accounts) {
        AccountsDto accountsDto = new AccountsDto();
        return mapToAccountsDto(accounts, accountsDto);
    }

    public static AccountsDto mapToAccountsDto(Accounts accounts, AccountsDto accountsDto) {
        accountsDto.setAccountNumber(accounts.getAccountNumber());
        accountsDto.setAccountType(accounts.getAccountType());
        accountsDto.setBranchAddress(accounts.getBranchAddress());
        return accountsDto;
    }
}