package com.baseball.engine;

import com.baseball.engine.io.Input;
import com.baseball.engine.io.NumberGenerator;
import com.baseball.engine.io.Output;
import com.baseball.engine.model.BallCount;
import com.baseball.engine.model.Numbers;
import lombok.AllArgsConstructor;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@AllArgsConstructor
public class BaseBall implements Runnable {
    private final int COUNT_OF_NUMBERS = 4;

    private NumberGenerator generator;
    private Input input;
    private Output output;

    @Override
    public void run() {
        Numbers answer = generator.generate(COUNT_OF_NUMBERS);

        while (true) {
            String inputString = this.input.input("숫자를 맞춰보세요. :");
            Optional<Numbers> inputNumbers = parse(inputString);
            if (inputNumbers.isEmpty()) {
                output.inputError();
                continue;
            }

            BallCount bc = ballCount(answer, inputNumbers.get());
            output.ballCount(bc);

            if (bc.getStrike() == COUNT_OF_NUMBERS) {
                output.correct();
                break;
            }
        }
    }

    private BallCount ballCount(Numbers answer, Numbers inputNumbers) {
//        answer.getNums();
//        for (int i = 0; i < answer.getNums().length; i++) {
//            answer.getNums()[i]
//        }
//        java의 함수형 인터페이스를 사용하여 반복로직 구현

        // racecondition을 방지하기 위해서 함수형 인터페이스 안에서는 변수의 write를 제한하고 있다.
        // 변수에 동기화기능을 추가해줘야 변수에 write 사용 가능
        AtomicInteger strike = new AtomicInteger();
        AtomicInteger ball = new AtomicInteger();

        answer.indexdForEach((a, i) -> {
            inputNumbers.indexdForEach((n, j) -> {
                if (!a.equals(n)) {
                    return;
                } else if (i == j) {
                    strike.addAndGet(1);
                } else {
                    ball.addAndGet(1);
                }
            });
        });
        return new BallCount(strike.get(), ball.get());
    }

    private Optional<Numbers> parse(String inputString) {
        if (inputString.length() != COUNT_OF_NUMBERS) {
            return Optional.empty();
        }

        long count = inputString.chars()
                .filter(Character::isDigit)
                .map(Character::getNumericValue)
                .filter(i -> i > 0)
                .distinct()
                .count();
        if (count != COUNT_OF_NUMBERS) {
            return Optional.empty();
        }

        return Optional.of(
                new Numbers(
                        inputString.chars()
                                .map(Character::getNumericValue)
                                .boxed()
                                .toArray(Integer[]::new)
                )
        );
    }

}
