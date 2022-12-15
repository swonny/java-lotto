package lotto;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class LottoGeneratorTest {
    @Test
    public void testLottoGenerator_로또생성개수테스트() {
        LottoGenerator lottoGenerator = new LottoGenerator();
        assertThat(lottoGenerator.generate(1000).size())
                .isEqualTo(1);
    }

    @Test
    public void testLottoGenerator_로또생성테스트() {
        LottoGenerator lottoGenerator = new LottoGenerator();
        System.out.println(lottoGenerator.generate(1000).get(0).numbers().toString());
    }

    @Test
    public void testLottoGenerator_로또생성_단위오류테스트() {
        LottoGenerator lottoGenerator = new LottoGenerator();
        assertThatThrownBy(() -> lottoGenerator.generate(100))
                .hasMessageContaining("로또 금액은 1,000원 단위로 입력해주세요.");
    }
}