package com.baseball;

import com.baseball.engine.BaseBall;
import com.baseball.engine.io.Input;
import com.baseball.engine.io.NumberGenerator;
import com.baseball.engine.io.Output;

public class App {

    public static void main(String[] args) {
        NumberGenerator generator = new FakerNumberGenerator();
        Console console = new Console();

        new BaseBall(generator, console, console).run();
    }
}
