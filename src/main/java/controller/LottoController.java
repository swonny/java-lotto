package controller;

import lotto.Lotto;
import lotto.LottoGenerator;
import lotto.ResultMaker;
import view.InputView;
import view.OutputView;

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
        // TODO : 당첨번호, 보너스번호 숫자 범위 유효성검사
        List<Integer> winningNumbers = getWinningNumbers(InputView.readWinningNumbers());
        int bonusNumber = getBonusNumber(winningNumbers, InputView.readBonusNumbers());
        List<String> resultStatistics = resultMaker.getStatistics(winningNumbers, bonusNumber);
        OutputView.printResultStatistics(resultStatistics);
        float returnRate = resultMaker.getReturnRate(lottos.size(), resultStatistics);
        OutputView.printReturnRate(returnRate);
    }

    private int getBonusNumber(List<Integer> winningNumbers, String bonusNumber) {
        // TODO : 위닝, 보너스 중복 합쳐서 한번에 해도 될듯
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

    private List<Integer> getWinningNumbers(String winningNumbers) {
        try {
            List<String> splitNumbers = split(winningNumbers);
            // TODO : 로또 서비스 만들어서 예외처리하ㅡㄴ 게 좋을듯
            validateWinningNumbers(splitNumbers);
            return splitNumbers.stream().map(Integer::valueOf).collect(Collectors.toList());
        } catch (IllegalArgumentException exception) {
            OutputView.printExceptionMessage(exception);
            return getWinningNumbers(InputView.readWinningNumbers());
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

    private List<Lotto> generateLottos(LottoGenerator lottoGenerator) {
        try {
            // TODO : 다시 입려받도록 구현했음 -> 종료시키게 바꿔보기
            int payment = getPayment(InputView.readPayment());
            return lottoGenerator.generate(payment);
        } catch (IllegalArgumentException exception) {
            OutputView.printExceptionMessage(exception);
            return generateLottos(lottoGenerator);
        }
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
        // TODO : 금액 범위 지정해보기
        // TODO : 0원 안 받게 payment 예외 클래스 추가해보기
        // TODO : 0원 상수처리
        if (!payment.matches("[0-9]+") || payment.equals("0")) {
            throw new IllegalArgumentException("0원을 입력할 수 없으며, 숫자만 입력할 수 있습니다.");
        }
    }


}
