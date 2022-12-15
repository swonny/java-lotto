package lotto;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LottoGenerator {
    private static final int PRICE_PER_LOTTO = 1_000;

    public List<Lotto> generate(int payment) {
        int count = getLottoCounts(payment);
        List<Lotto> lottos = new ArrayList<>();
        for (int countIndex = 0; countIndex < count; countIndex++) {
            lottos.add(generateLotto());
        }
        return lottos;
    }

    private Lotto generateLotto() {
        List<Integer> numbers = Randoms.pickUniqueNumbersInRange(1, 45, 6);
        numbers = numbers.stream().sorted().collect(Collectors.toList());
        return new Lotto(numbers);
    }

    private int getLottoCounts(int payment) {
        if (payment % PRICE_PER_LOTTO != 0) {
            throw new IllegalArgumentException("로또 금액은 1,000원 단위로 입력해주세요.");
        }
        return payment / PRICE_PER_LOTTO;
    }
}
