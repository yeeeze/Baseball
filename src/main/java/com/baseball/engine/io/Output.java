package com.baseball.engine.io;

import com.baseball.engine.model.BallCount;

public interface Output {
    void ballCount(BallCount bc);

    void inputError();

    void correct();
}
