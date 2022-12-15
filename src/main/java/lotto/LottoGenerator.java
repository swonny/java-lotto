package lotto;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LottoGenerator {
    private static final int PRICE_PER_LOTTO = 1_000;

    public List<Lotto> generate(int payment) {
        // TODO : 반환값 변경
        // TODO : payment 1,000원단위 유효성검사
        // TODO : payment 개수 계산
        int count = getLottoCounts(payment);

    }

    private int getLottoCounts(int payment) {
        if (payment % PRICE_PER_LOTTO != 0) {
            throw new IllegalArgumentException("로또 금액은 1,000원 단위로 입력해주세요.");
        }
        return payment % PRICE_PER_LOTTO;
    }
}
