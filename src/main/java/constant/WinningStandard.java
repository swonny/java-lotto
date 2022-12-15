package constant;

import lotto.Lotto;

import java.util.Arrays;
import java.util.List;

public enum WinningStandard {
    FIFTH_PRIZE(3, "5,000원", false),
    FOURTH_PRIZE(4, "50,000원", false),
    THIRD_PRIZE(5, "1,500,000원", false),
    SECOND_PRIZE(5, "30,000,000원", true),
    FIRST_PRIZE(6, "2,000,000,000원", false);

    private final int sameNumberCount;
    private final String winningAmount;
    private final boolean hasBonus;

    WinningStandard(int sameNumberCount, String winningAmount, boolean hasBonus) {
        this.sameNumberCount = sameNumberCount;
        this.winningAmount = winningAmount;
        this.hasBonus = hasBonus;
    }

    public static WinningStandard getRank(Lotto lotto, List<Integer> winningNumbers, int bonusNumber) {
        List<Integer> lottoNumbers = lotto.numbers();
        int sameNumberCount = (int) winningNumbers.stream().filter(number -> lottoNumbers.contains(number)).count();
        if (sameNumberCount == THIRD_PRIZE.sameNumberCount && lottoNumbers.contains(bonusNumber)) {
            return SECOND_PRIZE;
        }
        return getRankFromWinningStandard(sameNumberCount);
    }

    private static WinningStandard getRankFromWinningStandard(int count) {
        return Arrays.stream(WinningStandard.values())
                .filter(rank -> rank.sameNumberCount == count)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("당첨기준 범위 밖입니다."));
    }

    public int getSameNumber() {
        return this.sameNumberCount;
    }

    public String getAmount() {
        return this.winningAmount;
    }
}
