package lotto;

import constant.WinningStandard;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;

public class ResultMaker {
    private static final int PRICE_PER_LOTTO = 1_000;

    public EnumMap<WinningStandard, Integer> getStatistics(List<Lotto> lottos, List<Integer> winningNumbers, int bonusNumber) {
        EnumMap<WinningStandard, Integer> rankCount = new EnumMap<>(WinningStandard.class);
        rankCount = initializeRankCount(rankCount);
        for (Lotto lotto : lottos) {
            try {
                WinningStandard rank = WinningStandard.getRank(lotto, winningNumbers, bonusNumber);
                rankCount.put(rank, rankCount.get(rank) + 1);
            } catch (IllegalArgumentException exception) {
            }
        }
        return rankCount;
    }

    private EnumMap<WinningStandard, Integer> initializeRankCount(EnumMap<WinningStandard, Integer> rankCount) {
        Arrays.stream(WinningStandard.values())
                .forEach(rank -> rankCount.put(rank, 0));
        return rankCount;
    }

    public double getReturnRate(int lottoCount, EnumMap<WinningStandard, Integer> resultStatistics) {
        int payment = lottoCount * PRICE_PER_LOTTO;
        int totalPrize = resultStatistics.keySet().stream()
                .map(rank -> rank.getAmount() * resultStatistics.get(rank))
                .reduce(0, Integer::sum);
        double rate = ((double) totalPrize / (double) payment) * 100;
        System.out.println((double) totalPrize / (double) payment);
        return Math.round(rate * 10) / 10.0;
    }
}
