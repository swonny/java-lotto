package lotto;

import View.GuideMessage;
import View.Input;
import View.ResultMessage;
import enumCollections.RankNumber;

import java.util.EnumMap;
import java.util.List;

public class Kiosk {
    static final int LOTTO_PRICE = 1000;

    static public void start(Buyer buyer) {
        try {
            int payment = getPayment();
            List<Lotto> lottos = Generator.generate(payment);
            printLottoInformation(lottos);
            buyer.buy(lottos);
            List<Integer> winningNumbers = getWinningNumbers();
            int bonusNumber = getBonusNumber(winningNumbers);
            printResult(Checker.compareAllLottos(buyer, winningNumbers, bonusNumber), payment);
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
        }
    }

    static private void printLottoInformation(List<Lotto> lottos) {
        ResultMessage.printLottoInformation(lottos);
    }

    static private List<Integer> getWinningNumbers() {
        GuideMessage.printGetWinningNumbers();
        return Input.getWinningNumbers();
    }

    static private int getBonusNumber(List<Integer> winningNumbers) {
        GuideMessage.printGetBonusNumber();
        return Input.getBonusNumber(winningNumbers);
    }

    static private void printResult(EnumMap<RankNumber, Integer> resultStatistics, int payment) {
        ResultMessage.printStatistics(resultStatistics);
        ResultMessage.printYield(Calculator.getYield(payment, resultStatistics));
    }

    static private int getPayment() {
        GuideMessage.printGetPayment();
        return Input.getPayment();
    }

    static private int countLottos(int payment) {
        return payment / LOTTO_PRICE;
    }
}
