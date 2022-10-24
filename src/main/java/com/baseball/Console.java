package com.baseball;

import com.baseball.engine.io.Input;
import com.baseball.engine.io.Output;
import com.baseball.engine.model.BallCount;

import java.util.Scanner;

public class Console implements Input, Output {
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void ballCount(BallCount bc) {
        System.out.println(bc.getStrike() + "Strikes, " + bc.getBall() + " Balls");
    }

    @Override
    public void inputError() {
        System.out.println("입력이 잘못되었습니다.");
    }

    @Override
    public void correct() {
        System.out.println("정답입니다.");
    }

    @Override
    public String input(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }
}
