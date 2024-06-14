package dev.mayankg.cards.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter @Setter
@ToString @Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cards")
public class Cards extends BaseEntity {

    @Id @Column(name = "card_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cardId;

    @Column(name = "mobile_number")
    private String mobileNumber;

    @Column(name = "card_number")
    private String cardNumber;

    @Column(name = "card_type")
    private String cardType;

    @Column(name = "total_limit")
    private Integer totalLimit;

    @Column(name = "amount_used")
    private Integer amountUsed;

    @Column(name = "available_amount")
    private Integer availableAmount;

}