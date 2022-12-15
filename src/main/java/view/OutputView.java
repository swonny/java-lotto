package view;

import constant.WinningStandard;
import lotto.Lotto;

import java.util.Arrays;
import java.util.List;

import static constant.ViewMessage.*;

public class OutputView {
    private static void println(String value) {
        System.out.println(value);
    }

    private static void printNewLine() {
        System.out.println();
    }

    private static String integerToString(int numericValue) {
        return Integer.toString(numericValue);
    }

    private static void print(String value) {
        System.out.print(value);
    }

    private static void printFormat(String value, String formatValue) {
        System.out.printf(value, formatValue);
    }

    public static void printExceptionMessage(Exception exception) {
        println("[ERROR] " + exception.getMessage());
    }

    public static void printReadingPayment() {
        println(READ_PAYMENT);
    }

    public static void printGeneratedLottos(List<Lotto> lottos) {
        printFormat(BOUGHT_LOTTOS, integerToString(lottos.size()));
        printNewLine();
        lottos.stream().forEach(lotto -> println(lotto.numbers().toString()));
        printNewLine();
    }

    public static void printReadingWinningNumbers() {
        println(READ_WINNING_NUMBERS);
    }

    public static void printReadingBonusNumber() {
        printNewLine();
        println(READ_BONUS_NUMBER);
    }

    public static void printResultStatistics(List<String> result) {
        printNewLine();
        println(RESULT_TITLE);
        println(RESULT_DELIMITER);
        Arrays.stream(WinningStandard.values())
                .forEach(winningStandard ->
                        printResultFormat(
                                RESULT_FORMAT,
                                winningStandard.getSameNumber(),
                                winningStandard.getAmount(),
                                result.remove(0))
                );
    }

    private static void printResultFormat(String resultFormat, String sameNumbers, String amount, String count) {
        System.out.printf(resultFormat, sameNumbers, amount, count);
        printNewLine();
    }

    public static void printReturnRate(float returnRate) {
        System.out.printf(RETURN_RATE, returnRate);
    }
}
