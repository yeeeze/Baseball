package com.baseball.engine.model;

import lombok.AllArgsConstructor;
import java.util.function.BiConsumer;

@AllArgsConstructor
public class Numbers {
    private Integer[] nums;

    public void indexdForEach(BiConsumer<Integer, Integer> consumer) {
        for (int i = 0; i < nums.length; i++) {
            consumer.accept(nums[i], i);
        }
    }
}
