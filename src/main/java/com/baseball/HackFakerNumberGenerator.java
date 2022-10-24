package com.baseball;

import com.baseball.engine.io.NumberGenerator;
import com.baseball.engine.model.Numbers;
import com.github.javafaker.Faker;

import java.util.stream.Stream;

public class HackFakerNumberGenerator implements NumberGenerator {
    private final Faker faker = new Faker();

    @Override
    public Numbers generate(int count) {
        Numbers nums = new Numbers(
                Stream.generate(() -> faker.number().randomDigitNotZero())
                        .distinct()
                        .limit(count)
                        .toArray(Integer[]::new)
        );
        System.out.println(nums);
        return nums;
    }
}
