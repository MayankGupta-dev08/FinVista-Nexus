package dev.mayankg.loans.service.impl;

import dev.mayankg.loans.constants.LoansEnum;
import dev.mayankg.loans.dto.LoansDto;
import dev.mayankg.loans.entity.Loans;
import dev.mayankg.loans.exception.LoanAlreadyExistingException;
import dev.mayankg.loans.exception.ResourceNotFoundException;
import dev.mayankg.loans.mapper.LoansMapper;
import dev.mayankg.loans.repository.LoansRepository;
import dev.mayankg.loans.service.ILoansService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class LoansServiceImpl implements ILoansService {

    private LoansRepository loansRepository;

    /**
     * @param mobileNumber - Mobile Number of the Customer
     */
    @Override
    public void createLoan(String mobileNumber) {
        Optional<Loans> optionalLoan = loansRepository.findByMobileNumber(mobileNumber);
        if (optionalLoan.isPresent()) {
            throw new LoanAlreadyExistingException("Loan already registered with the given mobile number : " + mobileNumber);
        }
        loansRepository.save(createNewDefaultLoan(mobileNumber));
    }

    /**
     * @param mobileNumber - Input mobile Number
     * @return Loan Details based on a given mobileNumber
     */
    @Override
    public LoansDto fetchLoan(String mobileNumber) {
        Loans loans = loansRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Loan", "Mobile Number", mobileNumber)
        );
        return LoansMapper.mapToLoansDto(loans);
    }

    /**
     * @param loansDto - LoansDto Object
     * @return boolean indicating if the update of card details is successful or not
     */
    @Override
    public boolean updateLoan(LoansDto loansDto) {
        Loans loans = loansRepository.findByLoanNumber(loansDto.getLoanNumber()).orElseThrow(
                () -> new ResourceNotFoundException("Loan", "Loan Number", loansDto.getLoanNumber()));
        LoansMapper.mapToLoans(loansDto, loans);
        loansRepository.save(loans);
        return  true;
    }

    /**
     * @param mobileNumber - Input Mobile Number
     * @return boolean indicating if the deleting of loan details is successful or not
     */
    @Override
    public boolean deleteLoan(String mobileNumber) {
        Loans loans = loansRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Loan", "mobileNumber", mobileNumber)
        );
        loansRepository.deleteById(loans.getLoanId());
        return true;
    }

    private Loans createNewDefaultLoan(String mobileNumber) {
        Loans newLoan = new Loans();
        long randomLoanNumber = 100000000000L + new Random().nextInt(900000000);
        newLoan.setLoanNumber(Long.toString(randomLoanNumber));
        newLoan.setMobileNumber(mobileNumber);
        newLoan.setLoanType(LoansEnum.LoanType.HOME_LOAN.getValue());
        newLoan.setTotalLoan(LoansEnum.LoanAmount.FIFTY_LAKH.getAmount());
        newLoan.setAmountPaid(0);
        newLoan.setOutstandingAmount(LoansEnum.LoanAmount.FIFTY_LAKH.getAmount());
        return newLoan;
    }
}