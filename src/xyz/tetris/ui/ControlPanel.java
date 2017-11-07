package xyz.tetris.ui;

import xyz.tetris.logic.Box;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


/**
 * 游戏的控制面板类
 */
public class ControlPanel extends JPanel implements ActionListener {


    private JLabel nextBlockLbl = new JLabel("下一个方块");
    private JLabel levelLbl = new JLabel("级      数");
    private JLabel scoreLbl = new JLabel("分      数");

    private JTextField levelTxt = new JTextField("1");
    private JTextField scoreTxt = new JTextField("0");

    private JButton startBtn = new JButton("开     始");
    private JButton pauseBtn = new JButton("暂    停");
    private JButton resumeBtn = new JButton("恢    复");
    private JButton stopBtn = new JButton("结    束");
    private JButton turnLevelUpBtn = new JButton("升一级");
    private JButton turnLevelDownBtn = new JButton("降一级");

    private JPanel topPanel = new JPanel(new BorderLayout());
    private NextPanel nextPanel = new NextPanel();
    private JPanel plInfo = new JPanel(new GridLayout(4, 1));
    private JPanel plButton = new JPanel(new GridLayout(6, 1));

    private Border border = new EtchedBorder(EtchedBorder.RAISED, Color.white, new Color(148, 145, 140));

    private GameFunction gameFunction;
    private int level=1;


    ControlPanel(GameFunction gameFunction) {
        this.gameFunction = gameFunction;
        initUI();
        addListener();
        this.setFocusable(true);
        this.requestFocus();
    }

    private void initUI() {
        this.setLayout(new GridLayout(3, 1, 0, 4));

        topPanel.add(nextBlockLbl, BorderLayout.NORTH);
        topPanel.add(nextPanel);
        topPanel.setBorder(border);

        plInfo.add(levelLbl);
        plInfo.add(levelTxt);
        plInfo.add(scoreLbl);
        plInfo.add(scoreTxt);
        plInfo.setBorder(border);

        levelTxt.setEditable(false);
        scoreTxt.setEditable(false);
        plButton.add(startBtn);
        plButton.add(pauseBtn);
        plButton.add(resumeBtn);
        plButton.add(stopBtn);
        plButton.add(turnLevelUpBtn);
        plButton.add(turnLevelDownBtn);
        plButton.setBorder(border);
        add(topPanel);
        add(plInfo);
        add(plButton);
        this.setBorder(border);
    }

    private void addListener() {
        this.addKeyListener(new ControlKeyListener());
        startBtn.addActionListener(this);
        resumeBtn.addActionListener(this);
        pauseBtn.addActionListener(this);
        stopBtn.addActionListener(this);
        turnLevelUpBtn.addActionListener(this);
        turnLevelDownBtn.addActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startBtn) {
            gameFunction.startGame();
        }
        if (e.getSource() == pauseBtn) {
            gameFunction.pauseGame();
        }
        if (e.getSource() == resumeBtn) {
            gameFunction.resumeGame();
        }
        if (e.getSource() == stopBtn) {
            gameFunction.stopGame();
        }
        if (e.getSource() == turnLevelUpBtn) {

            level++;
            if (level > 10) {
                level=10;
                return;
            }
            gameFunction.setLevel(level);
            levelTxt.setText(""+level);

        }
        if (e.getSource() == turnLevelDownBtn) {
            level--;
            if (level <1) {
                level=1;
                return;
            }
            gameFunction.setLevel(level);
            levelTxt.setText(""+level);
        }
        this.requestFocus();
    }

    private class ControlKeyListener extends KeyAdapter {
        public void keyPressed(KeyEvent ke) {

            switch (ke.getKeyCode()) {
                case KeyEvent.VK_DOWN:
                    gameFunction.ctrlBlock(2);
                    break;
                case KeyEvent.VK_LEFT:
                    gameFunction.ctrlBlock(3);
                    break;
                case KeyEvent.VK_RIGHT:
                    gameFunction.ctrlBlock(1);
                    break;
                case KeyEvent.VK_UP:
                    gameFunction.ctrlBlock(4);
                    break;
                default:
                    break;
            }
        }
    }

    void setScore(long score) {
        scoreTxt.setText(String.valueOf(score));
    }

    void setNext(Box[][] block) {
        nextPanel.setNext(block);
    }

    /**
     * 俄罗斯方块的预先窗口
     */
    private class NextPanel extends JPanel {
        /**
         *
         */
        private Color frontColor = Color.LIGHT_GRAY, backColor = Color.gray;
        private int boxWidth, boxHeight;
        private boolean isSizing= false;
        private Box[][] block;

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (!isSizing)
                fanning();
            if (block == null) return;

            for (int i = 0; i < block.length; i++)
                for (int j = 0; j < block[i].length; j++) {
                    Color isColor = (block[i][j].isColor()) ? frontColor : backColor;
                    g.setColor(isColor);
                    g.fill3DRect(j * boxWidth, i * boxHeight, boxWidth, boxHeight, true);

                }
        }

        private void fanning() {
            boxWidth = this.getWidth() / 4;
            boxHeight = this.getHeight() / 4;
            isSizing = true;
        }

        void setNext(Box[][] block) {
            this.block = block;
            this.repaint();
        }

    }
}
