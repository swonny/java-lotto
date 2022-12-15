package lotto;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LottoGenerator {
    public List<Lotto> generate(int payment) {
        // TODO : 반환값 변경
        // TODO : payment 1,000원단위 유효성검사
        // TODO : payment 개수 계산
        return Arrays.asList(new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6)));
    }
}
