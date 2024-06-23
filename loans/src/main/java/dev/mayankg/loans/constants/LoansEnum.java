package dev.mayankg.loans.constants;

public final class LoansEnum {

    private LoansEnum() {
        // restricting instantiation
    }

    public enum LoanType {
        HOME_LOAN("Home Loan"),
        BIKE_LOAN("Bike Loan"),
        CAR_LOAN("Car Loan"),
        STUDENT_LOAN("Student Loan");

        private final String value;

        LoanType(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    public enum LoanAmount {
        ONE_LAKH(1_00_000),
        FIVE_LAKH(5_00_000),
        TEN_LAKH(10_00_000),
        FIFTY_LAKH(50_00_000);

        private Integer amount;

        LoanAmount(Integer amount) {
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
        MESSAGE_201("Loan granted successfully"),
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