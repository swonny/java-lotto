package ExceptionCollections;

import enumCollections.Exceptions;

public class PaymentException extends CommonException {
    public static void validate(String payment) {
        hasCharacters(payment);
        validateUnit(stringToInteger(payment));
    }

    public static void validateUnit(int payment) {
        if (payment % PRICE_PER_LOTTO != 0) {
            throw new IllegalArgumentException(Exceptions.getMessage(Exceptions.WRONG_MONEY_UNIT));
        }
    }
}
