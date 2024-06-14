package dev.mayankg.loans.mapper;

import dev.mayankg.loans.dto.LoansDto;
import dev.mayankg.loans.entity.Loans;

public final class LoansMapper {

    private LoansMapper() {
        // private instance
    }

    public static Loans mapToLoans(LoansDto loansDto) {
        return mapToLoans(loansDto, new Loans());
    }

    public static Loans mapToLoans(LoansDto loansDto, Loans loans) {
        loans.setLoanNumber(loansDto.getLoanNumber());
        loans.setLoanType(loansDto.getLoanType());
        loans.setTotalLoan(loansDto.getTotalLoan());
        loans.setAmountPaid(loansDto.getAmountPaid());
        loans.setMobileNumber(loansDto.getMobileNumber());
        loans.setOutstandingAmount(loansDto.getOutstandingAmount());
        return loans;
    }

    public static LoansDto mapToLoansDto(Loans loans) {
        return mapToLoansDto(loans, new LoansDto());
    }

    public static LoansDto mapToLoansDto(Loans loans, LoansDto loansDto) {
        loansDto.setLoanNumber(loans.getLoanNumber());
        loansDto.setLoanType(loans.getLoanType());
        loansDto.setTotalLoan(loans.getTotalLoan());
        loansDto.setAmountPaid(loans.getAmountPaid());
        loansDto.setMobileNumber(loans.getMobileNumber());
        loansDto.setOutstandingAmount(loans.getOutstandingAmount());
        return loansDto;
    }
}