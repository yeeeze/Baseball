package com.baseball.engine;

import com.baseball.engine.io.Input;
import com.baseball.engine.io.Output;
import com.baseball.engine.model.BallCount;
import com.baseball.engine.model.Numbers;
import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
public class BaseBall implements Runnable {
    private final int COUNT_OF_NUMBERS = 3;

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
        return null;
    }

    private Optional<Numbers> parse(String inputString) {
        return null;
    }

}
