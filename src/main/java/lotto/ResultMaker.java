package lotto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ResultMaker {
    public List<String> getStatistics(List<Integer> winningNumbers, int bonusNumber) {
        // TODO : return 값 변경하기
        // TODO : return 하려면 먼저 list 초기화해야한다.
        List<String> returnvalue = new ArrayList<>();
        return Stream.of("1", "2", "3", "1", "2", "3").collect(Collectors.toList());
    }

    public float getReturnRate(int size, List<String> resultStatistics) {
        // TODO : return 값 변경하기
        return 0;
    }
}
