package dev.mayankg.accounts.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity @Getter
@Setter @ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customer")
@SuppressWarnings("unused")
public class Customer extends BaseEntity {

    @Id
    @Column(name = "customer_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "mobile_number")
    private String mobileNumber;
}