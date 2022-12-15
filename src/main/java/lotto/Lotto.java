package lotto;

import java.util.Collections;
import java.util.List;

public class Lotto {
    private static final Integer MINIMUM = 1;
    private static final Integer MAXIMUM = 45;
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        validateSize(numbers);
        validateRange(numbers);
        validateDuplication(numbers);
    }

    private void validateDuplication(List<Integer> numbers) {
        for (Integer number : numbers) {
            if (numbers.indexOf(number) != numbers.lastIndexOf(number)) {
                throw  new IllegalArgumentException("중복되는 숫자가 존재합니다.");
            }
        }
    }

    private void validateRange(List<Integer> numbers) {
        if (numbers.stream().anyMatch(number -> number < MINIMUM || number > MAXIMUM)) {
            throw new IllegalArgumentException("로또 숫자의 범위가 맞지 않습니다.");
        }
    }

    private void validateSize(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException();
        }
    }

    public List<Integer> numbers() {
        return Collections.unmodifiableList(numbers);
    }
}
