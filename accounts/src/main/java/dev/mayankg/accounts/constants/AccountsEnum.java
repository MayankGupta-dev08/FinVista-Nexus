package dev.mayankg.accounts.constants;

@SuppressWarnings("unused")
public final class AccountsEnum {

    private AccountsEnum() {
        // restrict instantiation
    }

    public enum AccountType {
        SAVINGS("Savings"),
        CURRENT("Current");

        private final String value;

        AccountType(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    public enum Address {
        MAIN("133 Main Street, New Delhi, India"),
        OTHER("123 Main Street, New York, USA");

        private final String value;

        Address(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
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
        MESSAGE_201("Account created successfully"),
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