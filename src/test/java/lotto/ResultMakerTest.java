package lotto;

import constant.WinningStandard;
import org.junit.jupiter.api.Test;

import java.util.EnumMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ResultMakerTest {
    @Test
    public void testResultMaker_당첨없음() {
        ResultMaker resultMaker = new ResultMaker();
        Lotto lotto1 = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        Lotto lotto2 = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        Lotto lotto3 = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        List<Lotto> lottos = List.of(lotto1, lotto2, lotto3);
        List<Integer> winningNumbers = List.of(7, 8, 9, 10, 11, 12);
        int bonusNumber = 13;
        EnumMap<WinningStandard, Integer> rankCount = resultMaker.getStatistics(lottos, winningNumbers, bonusNumber);
        System.out.println(rankCount);
    }

    @Test
    public void testResultMaker_1등당첨_3개() {
        ResultMaker resultMaker = new ResultMaker();
        Lotto lotto1 = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        Lotto lotto2 = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        Lotto lotto3 = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        List<Lotto> lottos = List.of(lotto1, lotto2, lotto3);
        List<Integer> winningNumbers = List.of(1, 2, 3, 4, 5, 6);
        int bonusNumber = 13;
        EnumMap<WinningStandard, Integer> rankCount = resultMaker.getStatistics(lottos, winningNumbers, bonusNumber);
        System.out.println(rankCount);
    }

    @Test
    public void testResultMaker_2등당첨_1개() {
        ResultMaker resultMaker = new ResultMaker();
        Lotto lotto1 = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        Lotto lotto2 = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        Lotto lotto3 = new Lotto(List.of(1, 2, 3, 4, 5, 7));
        List<Lotto> lottos = List.of(lotto1, lotto2, lotto3);
        List<Integer> winningNumbers = List.of(1, 2, 3, 4, 5, 9);
        int bonusNumber = 7;
        EnumMap<WinningStandard, Integer> rankCount = resultMaker.getStatistics(lottos, winningNumbers, bonusNumber);
        System.out.println(rankCount);
    }
}