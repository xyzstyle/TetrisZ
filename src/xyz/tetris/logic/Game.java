package xyz.tetris.logic;

import xyz.tetris.ifs.Controls;
import xyz.tetris.ifs.SetDatas;

import javax.swing.*;

import static xyz.tetris.logic.Block.BLOCK_KIND_NUMBER;
import static xyz.tetris.logic.Block.BLOCK_STATUS_NUMBER;
import static xyz.tetris.logic.Block.STYLES;

public class Game extends Thread implements Controls {
    private Box[][] boxes;
    private int rows, cols;
    private Block block;
    private int score;
    private boolean playing = true;
    private SetDatas setDatas;

    public Game(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        boxes = new Box[rows][cols];
        for (int i = 0; i < boxes.length; i++)
            for (int j = 0; j < boxes[i].length; j++) {
                boxes[i][j] = new Box(false);
            }
    }


    public Box[][] getBoxes() {
        return boxes;
    }

    /**
     * 得到某一行某一列的方格引用。
     *
     * @param row int, 要引用的方格所在的行
     * @param col int, 要引用的方格所在的列
     * @return Box, 在row行col列的方格的引用
     */
    public Box getBox(int row, int col) {
        if (row < 0 || row > boxes.length - 1 || col < 0 || col > boxes[0].length - 1)
            return null;
        return boxes[row][col];
    }

    /**
     * 检查画布中是否有全填满的行，如果有就删除之
     */
    public int checkFullLine() {
        int count = 0;
        int value=0;
        for (int i = 0; i < boxes.length; i++) {
            int row = -1;
            boolean fullLineBoxColor = true;
            for (int j = 0; j < boxes[i].length; j++) {
                if (!boxes[i][j].isColor()) {
                    fullLineBoxColor = false;
                    break;
                }
            }
            if (fullLineBoxColor) {
                row = i;
                removeLine(row);
                count++;
                value = value + (int)Math.pow((double)count,2d);
            }
        }
        return value;
    }

    /**
     * 当一行被游戏者叠满后，将此行清除，
     *
     * @param row int, 要清除的行
     */
    private synchronized void removeLine(int row) {
        for (int i = row; i > 0; i--)
            for (int j = 0; j < boxes[i].length; j++) {
                boxes[i][j] = (Box) boxes[i - 1][j].clone();
            }
        refresh();

    }

    public void refresh() {
        setDatas.setPaint(boxes);
    }

    /**
     * 根据最顶行是否被占，判断游戏是否已经结束了。
     *
     * @return boolean, true-游戏结束了，false-游戏未结束
     */
    public boolean isGameOver() {
        for (int j = 0; j < cols; j++) {
            if (boxes[0][j].isColor())
                return true;
        }
        return false;
    }

    public void reset() {
       for (int i = 0; i < boxes.length; i++) {
            for (int j = 0; j < boxes[i].length; j++)
                boxes[i][j].setColor(false);
        }


    }





    private synchronized boolean MoveBlockTo(int newRow, int newCol) {
        if (!isMoveAble(newRow, newCol)) return false;
        erase();
        block.setX(newCol);
        block.setY(newRow);
        display();
        refresh();
        //注意相关功能移动到GameCanvas中：canvas.repaint();
        return true;
    }

    private boolean isMoveAble(int newRow, int newCol) {
        erase();
        Box[][] blockBoxes = block.getBoxes();
        for (int i = 0; i < blockBoxes.length; i++)
            for (int j = 0; j < blockBoxes[i].length; j++) {
                if (blockBoxes[i][j].isColor()) {
                    Box box = getBox(newRow + i, newCol + j);
                    if (box == null || box.isColor()) {
                        display();
                        return false;
                    }
                }
            }
        display();
        return true;
    }

    /**
     * 让当前块放置在场景的对应位置上，要等到下次重画场景时才能看见
     */
    private void display() {
        Box[][] blockBoxes = block.getBoxes();

        for (int i = 0; i < blockBoxes.length; i++)
            for (int j = 0; j < blockBoxes[i].length; j++) {
                if (blockBoxes[i][j].isColor()) {
                    Box box = getBox(block.getY() + i, block.getX() + j);
                    if (box == null)
                        continue;
                    box.setColor(true);
                }
            }
    }

    private void erase() {
        Box[][] blockBoxes = block.getBoxes();
        for (int i = 0; i < blockBoxes.length; i++)
            for (int j = 0; j < blockBoxes[i].length; j++) {
                if (blockBoxes[i][j].isColor()) {
                    Box box = getBox(block.getY() + i, block.getX() + j);
                    if (box == null)
                        continue;
                    box.setColor(false);
                }
            }
    }

    private void moveBlockLeft() {
        MoveBlockTo(block.getY(), block.getX() - 1);
    }

    private void moveBlockRight() {
        MoveBlockTo(block.getY(), block.getX() + 1);
    }

    private void moveBlockDown() {
        MoveBlockTo(block.getY() + 1, block.getX());
    }

    /**
     * 块变形
     */
    private void turnBlockStyle() {
        for (int i = 0; i < BLOCK_KIND_NUMBER; i++)
            for (int j = 0; j < BLOCK_STATUS_NUMBER; j++) {
                if (STYLES[i][j] == block.getStyle()) {
                    int newStyle = STYLES[i][(j + 1) % BLOCK_STATUS_NUMBER];
                    turnTo(newStyle);
                    return;
                }
            }
    }

    /**
     * 当前块能否变成newStyle指定的样式，主要考虑
     * 边界和以及被其他块挡住不能移动的情况。
     * newStyle希望改变的块样式，对应STYLES的28个值中的一个
     * return boolean,true-能改变，false-不能改变
     */
    private boolean isTurnAble(int newStyle) {
        erase();
        int key = Block.KEY;
        Box[][] blockBoxes = block.getBoxes();
        for (int i = 0; i < blockBoxes.length; i++)
            for (int j = 0; j < blockBoxes[i].length; j++) {
                if ((key & newStyle) != 0) {
                    Box box = getBox(i + block.getY(), j + block.getX());
                    if (box == null || box.isColor()) {
                        display();
                        return false;
                    }
                }
                key >>= 1;
            }
        display();
        return true;
    }

    /**
     * 将当前块变成newStyle所指定的块样式
     *
     * @param newStyle int,将要改变成的块样式，对应STYLES的28个值中的一个
     *                 return boolean, true-改变成功，false-改变失败
     */
    private void turnTo(int newStyle) {
        if (!isTurnAble(newStyle)) return;
        erase();
        block.setStyle(newStyle);
        display();
        refresh();
    }

    public void setGetDatas(SetDatas setDatas) {
        this.setDatas = setDatas;
    }

    @Override
    public void ctlDirection(int dir) {
        switch (dir) {
            case 1:
                moveBlockRight();
                break;
            case 2:
                moveBlockDown();
                break;
            case 3:
                moveBlockLeft();
                break;
            case 4:
                turnBlockStyle();
                break;
            default:
                break;
        }

    }

    @Override
    public void ctlGrade(int grade) {

    }

    @Override
    public void ctlGame(int game) {

    }




    @Override
    public void run() {
        int col = (int) (Math.random() * (cols - 3));
        int style =  Block.STYLES[(int) (Math.random() * 7)][(int) (Math.random() * 4)];
        boolean isFalling=true;
        while (playing) {
            if (block != null&& isFalling==true) {// 第一次循环时，block为空
                isFalling= MoveBlockTo(block.getY() + 1, block.getX());
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ie) {
                    ie.printStackTrace();
                }
                continue;
            }

            int increase = checkFullLine();
            if (increase != 0) {
                score += increase;
                setDatas.setScore(score);
            }
            if (isGameOver()) {
                // 下面注释的语句要修改
                //JOptionPane.showMessageDialog(boxContainer, "Game Over!");
                return;
            }
            block = new Block(col, -1, style);
            isFalling=true;
            col = (int) (Math.random() * (cols - 3));
            style = Block.STYLES[(int) (Math.random() * 7)][(int) (Math.random() * 4)];
            setDatas.setNextBlocks(block.getBoxes());
        }
    }
}
