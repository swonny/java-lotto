package lotto;

import constant.WinningStandard;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;

public class ResultMaker {
    // TODO :ResultRepository로 만들어서 결과 저장하고 있게 해도 될듯 & 미리 초기화 작업 해야하고, 메소드두개에서 쓰니까
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

    public float getReturnRate(int size, EnumMap<WinningStandard, Integer> resultStatistics) {
        return 0;
    }
}
