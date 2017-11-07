/**
 *
 */
package xyz.tetris.ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author: zxy
 * @File: MainMenu.java
 * @Date: 2015.10.26
 * @Describe: 俄罗斯方块的java实现
 */

/**
 * 俄罗斯方块的菜单栏
 */
class MainMenu extends JMenuBar {
    /**
     *
     */
    private static final long serialVersionUID = 8625660059237574385L;
    private final GameFunction gameFunction;

    private JMenu
            gameMenu = new JMenu("游戏"),
            controlMenu = new JMenu("控制"),
            windowStyleMenu = new JMenu("窗体风格"),
            infoMenu = new JMenu("相关信息");

    private JMenuItem
            newGameMenuItem = new JMenuItem("开始新游戏"),
            setBlockColorMenuItem = new JMenuItem("设置俄罗斯方块的颜色..."),
            setBackColorMenuItem = new JMenuItem("设置场景的颜色..."),
            turnHarderMenuItem = new JMenuItem("提升一级"),
            turnEasyMenuItem = new JMenuItem("下降一级"),
            exitMenuItem = new JMenuItem("退出游戏"),

    startGameMenuItem = new JMenuItem("开始"),
            pauseGameMenuItem = new JMenuItem("暂停"),
            resumeGameMenuItem = new JMenuItem("继续"),
            stopGameMenuItem = new JMenuItem("结束"),

    miAuthor = new JMenuItem("作者 : apple@radiantek.com"),
            miSourceInfo = new JMenuItem("你能通过邮箱过的源代码和文档");


    private JCheckBoxMenuItem
            miAsWindows = new JCheckBoxMenuItem("Windows风格"),
            miAsMotif = new JCheckBoxMenuItem("Motif风格"),
            miAsMetal = new JCheckBoxMenuItem("Metal风格", true);

    //private BlocksGame blocksGame;

    public MainMenu(GameFunction gameFunction) {
        this.gameFunction = gameFunction;
        this.add(gameMenu);
        this.add(controlMenu);
        this.add(windowStyleMenu);
        this.add(infoMenu);

        gameMenu.add(newGameMenuItem);
        gameMenu.addSeparator();
        gameMenu.add(setBlockColorMenuItem);
        gameMenu.add(setBackColorMenuItem);
        gameMenu.addSeparator();
        gameMenu.add(turnHarderMenuItem);
        gameMenu.add(turnEasyMenuItem);
        gameMenu.addSeparator();
        gameMenu.add(exitMenuItem);

        controlMenu.add(startGameMenuItem);
        controlMenu.add(pauseGameMenuItem);
        controlMenu.add(resumeGameMenuItem);
        controlMenu.add(stopGameMenuItem);

        windowStyleMenu.add(miAsWindows);
        windowStyleMenu.add(miAsMotif);
        windowStyleMenu.add(miAsMetal);

        infoMenu.add(miAuthor);
        infoMenu.add(miSourceInfo);

        newGameMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameFunction.startGame();
            }
        });
        setBlockColorMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameFunction.setFrontColor();
            }
        });
        setBackColorMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameFunction.setBackColor();
            }
        });
        turnHarderMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        turnEasyMenuItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        exitMenuItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        startGameMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 gameFunction.startGame();
            }
        });
        pauseGameMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 gameFunction.pauseGame();
            }
        });
        resumeGameMenuItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                gameFunction.resumeGame();
            }
        });
        stopGameMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameFunction.stopGame();
            }
        });
        miAuthor.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        miSourceInfo.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        miAsMetal.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        miAsMotif.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        miAsWindows.addActionListener((e) -> {
            System.out.println("eee");
        });
    }
}
