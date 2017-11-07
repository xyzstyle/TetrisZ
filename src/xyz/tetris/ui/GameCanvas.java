/**
 * 
 */
package xyz.tetris.ui;

import xyz.tetris.logic.Box;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import java.awt.*;

/**
 * @author: zxy
 * @File: MainMenu.java
 * @Date: 2015.10.26
 * @Describe: 俄罗斯方块的java实现
 */

/**
 * 游戏的场景类
 */
public class GameCanvas extends JPanel {
	/**
	 * 
	 */

	private Color backColor = Color.blue, frontColor = Color.ORANGE;
	

	private int cols, rows;
	private Box[][] boxes;

	
	public void setBoxes(Box[][] boxes) {
		this.boxes = boxes;
	}



	public GameCanvas(int cols, int rows) {
		this.cols = cols;
		this.rows = rows;
		this.setBorder(border);
		
	}



	public Color getBackColor() {
		return backColor;
	}

	public void setBackColor(Color backColor) {
		this.backColor = backColor;
	}

	public Color getFrontColor() {
		return frontColor;
	}

	public void setFrontColor(Color frontColor) {
		this.frontColor = frontColor;
	}

	private Border border = new EtchedBorder(EtchedBorder.RAISED, Color.white, new Color(148, 145, 140));

	int boxWidth, boxHeight;

	public void fanning() {
		boxWidth = this.getWidth() / rows;
		boxHeight = this.getHeight() / cols;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(frontColor);
		
        if(!(boxes==null)){
		for (int i = 0; i < boxes.length; i++)
			for (int j = 0; j < boxes[i].length; j++) {
				g.setColor(boxes[i][j].isColor() ? frontColor : backColor);
				g.fill3DRect(j * boxWidth, i * boxHeight, boxWidth, boxHeight, true);
			}
		}

	}

}
