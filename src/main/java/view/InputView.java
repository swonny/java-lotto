package view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    public static String readPayment() {
        OutputView.printReadingPayment();
        return read();
    }

    private static String read() {
        return Console.readLine();
    }

    public static String readWinningNumbers() {
        OutputView.printReadingWinningNumbers();
        return read();
    }

    public static String readBonusNumbers() {
        OutputView.printReadingBonusNumber();
        return read();
    }
}
