package lotto;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoGenerator {
    static final int PRICE_PER_LOTTO = 1000;

    public static List<Lotto> generate(String payment) {
        validatePayment(Integer.valueOf(payment));
        List<Lotto> lottos = new ArrayList<>();
        for (int lottoNumber = 0; lottoNumber < countNumberOfLotto(Integer.valueOf(payment)); lottoNumber++) {
            List<Integer> lottoNumbers = pickLottoNumbers();
            lottos.add(new Lotto(orderNumbers(lottoNumbers)));
        }
        return lottos;
    }

    public static void validatePayment(int amount) {
        if (amount % PRICE_PER_LOTTO != 0) {
            throw new IllegalArgumentException();
        }
    }

    public static int countNumberOfLotto(int amount) {
        return amount / PRICE_PER_LOTTO;
    }

    public static List<Integer> pickLottoNumbers() {
        return Randoms.pickUniqueNumbersInRange(1, 45, 6);
    }

    public static List<Integer> orderNumbers(List<Integer> numbers) {
        Collections.sort(numbers);
        return numbers;
    }
}
