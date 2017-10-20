/**
 * 
 */
package xyz.tetris.ui;

import xyz.tetris.ifs.Controls;
import xyz.tetris.logic.Box;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


/**
 * @author: zxy
 * @File: MainMenu.java
 * @Date: 2015.10.26
 * @Describe: 俄罗斯方块的java实现
 */

/**
 * 游戏的控制面板类
 */
public class ControlPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5345521640892397395L;

	private JLabel next_block = new JLabel("下一个方块"), levle = new JLabel("级      数"), score = new JLabel("分      数");

	private JTextField tlevle = new JTextField("" + 5), tscore = new JTextField("0");

	private JButton btPlay = new JButton("开     始"), btPause = new JButton("暂    停"), btStop = new JButton("结    束"),
			btTurnLevelUp = new JButton("升一级"), btTurnLevelDown = new JButton("降一级");

	private JPanel topPanel = new JPanel(new BorderLayout());
	private NextPanle nextBlock = new NextPanle();
	private JPanel plInfo = new JPanel(new GridLayout(4, 1));
	private JPanel plButton = new JPanel(new GridLayout(5, 1));

	private Border border = new EtchedBorder(EtchedBorder.RAISED, Color.white, new Color(148, 145, 140));

	private Controls controls;

	
	

	public ControlPanel() {
		this.setLayout(new GridLayout(3, 1, 0, 4));

		topPanel.add(next_block, BorderLayout.NORTH);
		topPanel.add(nextBlock);
		topPanel.setBorder(border);

		plInfo.add(levle);
		plInfo.add(tlevle);
		plInfo.add(score);
		plInfo.add(tscore);
		plInfo.setBorder(border);

		tlevle.setEditable(false);
		tscore.setEditable(false);

		plButton.add(btPlay);
		plButton.add(btPause);
		plButton.add(btStop);
		plButton.add(btTurnLevelUp);
		plButton.add(btTurnLevelDown);
		plButton.setBorder(border);

		add(topPanel);
		add(plInfo);
		add(plButton);

		this.addKeyListener(new ControlKeyListener());
		this.setBorder(border);
		this.setFocusable(true);
		this.requestFocus();
	}

	private class ControlKeyListener extends KeyAdapter {
		public void keyPressed(KeyEvent ke) {
			
			switch (ke.getKeyCode()) {
			case KeyEvent.VK_DOWN:
				controls.ctlDirection(2);;
				break;
			case KeyEvent.VK_LEFT:
				controls.ctlDirection(3);
				break;
			case KeyEvent.VK_RIGHT:
				controls.ctlDirection(1);
				break;
			case KeyEvent.VK_UP:
				controls.ctlDirection(4);
				break;
			default:
				break;
			}
		}
	}

	public void setControls(Controls controls) {
		this.controls = controls;
	}
	public void setScore(long score){
		tscore.setText(String.valueOf(score));
	}

	public void setNext(Box[][] block) {
		nextBlock.setNext(block);
	}

	/**
	 * 俄罗斯方块的预先窗口
	 */
	private class NextPanle extends JPanel {
		/**
		 * 
		 */
		private static final long serialVersionUID = -5701119359570146705L;
		private Color frontColor = Color.LIGHT_GRAY, backColor = Color.gray;
		private int boxWidth, boxHeight;
		private boolean isTiped = false;
		private Box[][] block;
		

		public NextPanle() {
			
		}

		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			if (!isTiped)
				fanning();
			
			for (int i = 0; i < block.length; i++)
				for (int j = 0; j < block[i].length; j++) {
					Color isColor = (block[i][j].isColor()) ? frontColor : backColor;
					g.setColor(isColor);
					g.fill3DRect(j * boxWidth, i * boxHeight, boxWidth, boxHeight, true);
					
				}
		}

		public void fanning() {
			boxWidth = this.getWidth() / 4;
			boxHeight = this.getHeight() / 4;
			isTiped = true;
		}

		public void setNext(Box[][] block) {
			this.block=block;
			this.repaint();
		}

	}
}
