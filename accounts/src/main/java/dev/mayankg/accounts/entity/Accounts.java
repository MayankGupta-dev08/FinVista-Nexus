package dev.mayankg.accounts.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity @Getter
@Setter @ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "accounts")
@SuppressWarnings("unused")
public class Accounts extends BaseEntity {

    @Column(name = "customer_id")
    private Long customerId;

    @Id // custom logic for this field
    @Column(name = "account_number")
    private Long accountNumber;

    @Column(name = "account_type")
    private String accountType;

    @Column(name = "branch_address")
    private String branchAddress;
}