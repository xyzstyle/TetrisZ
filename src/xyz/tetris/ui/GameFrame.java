/**
 *
 */
package xyz.tetris.ui;

import xyz.tetris.ifs.SetDatas;
import xyz.tetris.logic.Box;
import xyz.tetris.logic.Game;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

/**
 * @author zxy
 * @File: GameFrame.java
 * @Date: 2015.10.26
 * @Describe: 俄罗斯方块的java实现
 */

/**
 * 游戏的窗体类
 */
public class GameFrame extends JFrame implements SetDatas {

    /**
     *
     */
    private static final long serialVersionUID = 4217692399057032483L;
    // 定义相关的对象变量
    private GameCanvas gameCanvas;
    private MainMenu mainMenu;
    private ControlPanel ctrlPanel;
    // 定义线程对象变量
    private Game game;
    //private ErsBlock block;


    public GameFrame() {
        // 创建界面对象（场景、控制面板、菜单栏）,其中场景方格横20，列12
        // 设置边界布局的水平间隔为6，垂直间隔为0
        // 将界面对象放到对应的窗体位置上
        // 设置窗体尺寸为：宽215，高392

        mainMenu = new MainMenu(this);
        gameCanvas = new GameCanvas(20, 12);
        ctrlPanel = new ControlPanel();
        setLayout(new BorderLayout(6, 0));
        setJMenuBar(mainMenu);
        add(gameCanvas, BorderLayout.CENTER);
        add(ctrlPanel, BorderLayout.EAST);
        setSize(315, 392);
        /**
         * 窗体居中，获取屏幕的宽度减去窗体的宽度除2作为窗体左上角的横坐标， 获取屏幕的高度减去窗体的高度除2作为窗体左上角的纵坐标
         */
        this.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - getWidth()) / 2,
                (Toolkit.getDefaultToolkit().getScreenSize().height - getHeight()) / 2);

        // 说明该语句的作用
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent ce) {
                gameCanvas.fanning();
            }
        });
        this.setVisible(true);
        // 开始游戏
        play();
    }


    public static void main(String[] args) {
        new GameFrame();

    }

    // 运行游戏
    public void play() {
        // 场景重置，也就是清场
        // 创建游戏线程对象，并使对象就绪

        game = new Game(20, 12);
        game.setGetDatas(this);
        game.start();
        ctrlPanel.setControls(game);
        //ctrlPanel.setGame(gameThread);// 说明其作用
        //ctrlPanel.requestFocus();// 说明其作用
    }

    /**
     * 根据字串设置窗口外观
     *
     * @param plaf String, 窗口外观的描述
     */
    public void setWindowStyle(String plaf) {
        try {
            UIManager.setLookAndFeel(plaf);
            SwingUtilities.updateComponentTreeUI(this);
        } catch (Exception e) {
        }
    }

    @Override
    public void setPaint(Box[][] boxes) {
        gameCanvas.setBoxes(boxes);
        gameCanvas.repaint();

    }

    @Override
    public void setNextBlocks(Box[][] block) {
        ctrlPanel.setNext(block);

    }

    @Override
    public void setScore(long score) {
        ctrlPanel.setScore(score);

    }
}
