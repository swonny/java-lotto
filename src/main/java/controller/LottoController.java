package controller;

import constant.WinningStandard;
import lotto.Lotto;
import lotto.LottoGenerator;
import lotto.ResultMaker;
import view.InputView;
import view.OutputView;

import java.util.EnumMap;
import java.util.List;
import java.util.stream.Collectors;

public class LottoController {

    private static final int WINNING_NUMBER_SIZE = 6;
    private final ResultMaker resultMaker;

    public LottoController(ResultMaker resultMaker) {
        this.resultMaker = resultMaker;
    }

    public void run() {
        List<Lotto> lottos = generateLottos(new LottoGenerator());
        OutputView.printGeneratedLottos(lottos);
        List<Integer> winningNumbers = getWinningNumbers(InputView.readWinningNumbers());
        int bonusNumber = getBonusNumber(winningNumbers, InputView.readBonusNumbers());
        EnumMap<WinningStandard, Integer> resultStatistics = resultMaker.getStatistics(lottos, winningNumbers, bonusNumber);
        OutputView.printResultStatistics(resultStatistics);
        double returnRate = resultMaker.getReturnRate(lottos.size(), resultStatistics);
        OutputView.printReturnRate(returnRate);
    }

    private List<Lotto> generateLottos(LottoGenerator lottoGenerator) {
        try {
            int payment = getPayment(InputView.readPayment());
            return lottoGenerator.generate(payment);
        } catch (IllegalArgumentException exception) {
            OutputView.printExceptionMessage(exception);
            return generateLottos(lottoGenerator);
        }
    }

    private List<Integer> getWinningNumbers(String winningNumbers) {
        try {
            List<String> splitNumbers = split(winningNumbers);
            validateWinningNumbers(splitNumbers);
            return splitNumbers.stream().map(Integer::valueOf).collect(Collectors.toList());
        } catch (IllegalArgumentException exception) {
            OutputView.printExceptionMessage(exception);
            return getWinningNumbers(InputView.readWinningNumbers());
        }
    }

    private int getBonusNumber(List<Integer> winningNumbers, String bonusNumber) {
        try {
            validateNumericOnly(bonusNumber);
            int numericBonusNumber = Integer.valueOf(bonusNumber);
            validateBonusNumberDuplication(winningNumbers, numericBonusNumber);
            return numericBonusNumber;
        } catch (IllegalArgumentException exception) {
            OutputView.printExceptionMessage(exception);
            return getBonusNumber(winningNumbers, InputView.readBonusNumbers());
        }
    }

    private void validateBonusNumberDuplication(List<Integer> winningNumbers, int bonusNumber) {
        if (winningNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException("당첨번호와 중복되는 숫자는 입력할 수 없습니다.");
        }
    }

    private void validateNumericOnly(String bonusNumber) {
        if (!bonusNumber.matches("[0-9]+")) {
            throw new IllegalArgumentException("숫자만 입력할 수 있습니다.");
        }
    }

    private void validateWinningNumbers(List<String> splitNumbers) {
        validateWinningNumberSize(splitNumbers);
        validateWinningNumberDuplication(splitNumbers);
    }

    private void validateWinningNumberDuplication(List<String> splitNumbers) {
        for (String number : splitNumbers) {
            if (splitNumbers.lastIndexOf(number) != splitNumbers.indexOf(number)) {
                throw new IllegalArgumentException("중복되는 숫자가 존재합니다.");
            }
        }
    }

    private void validateWinningNumberSize(List<String> splitNumbers) {
        if (splitNumbers.size() != WINNING_NUMBER_SIZE) {
            throw new IllegalArgumentException("6개의 숫자를 입력해주세요.");
        }
    }

    private List<String> split(String winningNumbers) {
        // TODO : ',' 없는 것에 대한 validate 추가해보기
        return List.of(winningNumbers.split(","));
    }

    private int getPayment(String payment) {
        try {
            validatePayment(payment);
            return Integer.valueOf(payment);
        } catch (IllegalArgumentException exception) {
            OutputView.printExceptionMessage(exception);
            return getPayment(InputView.readPayment());
        }
    }

    private void validatePayment(String payment) {
        if (!payment.matches("[0-9]+") || payment.equals("0")) {
            throw new IllegalArgumentException("0원을 입력할 수 없으며, 숫자만 입력할 수 있습니다.");
        }
    }
}
