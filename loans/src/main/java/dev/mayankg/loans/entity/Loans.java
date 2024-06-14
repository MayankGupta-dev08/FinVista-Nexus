package dev.mayankg.loans.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter @Setter
@ToString @Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "loans")
public class Loans extends BaseEntity {

    @Id
    @Column(name = "loan_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long loanId;

    @Column(name = "mobile_number")
    private String mobileNumber;

    @Column(name = "loan_number")
    private String loanNumber;

    @Column(name = "loan_type")
    private String loanType;

    @Column(name = "total_loan")
    private Integer totalLoan;

    @Column(name = "amount_paid")
    private Integer amountPaid;

    @Column(name = "outstanding_amount")
    private Integer outstandingAmount;

}