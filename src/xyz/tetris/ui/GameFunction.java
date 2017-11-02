package xyz.tetris.ui;

public interface GameFunction {
    void startGame();

    void pauseGame();

    void resumeGame();

    void stopGame();

    void setLevel(int level);

    void setFrontColor();

    void setBackColor();

    void ctrlBlock(int variety);
}
