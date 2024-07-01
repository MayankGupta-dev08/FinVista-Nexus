package dev.mayankg.cards.constants;

public final class CardsEnum {

    private CardsEnum() {
        // restricting instantiation
    }

    public enum CardType {
        RUPAY_CREDIT_CARD("Rupay Credit Card"),
        MASTER_CREDIT_CARD("Master  Credit Card"),
        VISA_CREDIT_CARD("Visa Credit Card");

        private final String value;

        CardType(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    public enum CreditCardLimit {
        THIRTY_THOUSAND(30_000),
        FIFTY_THOUSAND(50_000),
        SEVENTY_FIVE_THOUSAND(75_000),
        ONE_LAKH(1_00_000),
        ONE_HALF_LAKH(1_50_000),
        TWO_LAKH(2_00_000);

        private Integer amount;

        CreditCardLimit(Integer amount) {
            this.amount = amount;
        }

        public Integer getAmount() {
            return amount;
        }

        public void setAmount(Integer amount) {
            this.amount = amount;
        }
    }

    public enum StatusCode {
        STATUS_200("200"),
        STATUS_201("201"),
        STATUS_417("417"),
        STATUS_500("500");

        private final String value;

        StatusCode(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    public enum StatusMessage {
        MESSAGE_200("Request processed successfully"),
        MESSAGE_201("Card issued successfully"),
        MESSAGE_417_UPDATE("Update operation failed. Please try again or contact Dev team"),
        MESSAGE_417_DELETE("Delete operation failed. Please try again or contact Dev team"),
        MESSAGE_500("An error occurred. Please try again or contact Dev team");

        private final String message;

        StatusMessage(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }
}