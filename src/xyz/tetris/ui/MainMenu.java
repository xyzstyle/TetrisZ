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
class MainMenu extends JMenuBar{
    /**
	 * 
	 */
	private static final long serialVersionUID = 8625660059237574385L;

	private JMenu
            mGame=new JMenu("游戏"),
            mControl=new JMenu("控制"),
            mWindowStyle=new JMenu("窗体风格"),
            mInfo=new JMenu("相关信息");
   
    private JMenuItem
            miNewGame = new JMenuItem("开始新游戏"),
			miSetBlockColor = new JMenuItem("设置俄罗斯方块的颜色..."),
			miSetBackColor = new JMenuItem("设置场景的颜色..."),
			miTurnHarder = new JMenuItem("提升一级"),
			miTurnEasier = new JMenuItem("下降一级"),
			miExit = new JMenuItem("退出游戏"),

			miPlay = new JMenuItem("开始"), 
			miPause = new JMenuItem("暂停"),
			miResume = new JMenuItem("继续"), 
			miStop = new JMenuItem("结束"),

			miAuthor = new JMenuItem("作者 : apple@radiantek.com"),
			miSourceInfo = new JMenuItem("你能通过邮箱过的源代码和文档");


	private JCheckBoxMenuItem 
	        miAsWindows = new JCheckBoxMenuItem("Windows风格"),
			miAsMotif = new JCheckBoxMenuItem("Motif风格"),
			miAsMetal = new JCheckBoxMenuItem("Metal风格", true);
	
	//private BlocksGame blocksGame;
	
    public MainMenu(final GameFrame gameFrame){
    	//this.blocksGame=blocksGame;
    	this.add(mGame);
    	this.add(mControl);
    	this.add(mWindowStyle);
    	this.add(mInfo);
    	
    	mGame.add(miNewGame);
		mGame.addSeparator();
		mGame.add(miSetBlockColor);
		mGame.add(miSetBackColor);
		mGame.addSeparator();
		mGame.add(miTurnHarder);
		mGame.add(miTurnEasier);
		mGame.addSeparator();
		mGame.add(miExit);

		mControl.add(miPlay);
		mControl.add(miPause);
		mControl.add(miResume);
		mControl.add(miStop);

		mWindowStyle.add(miAsWindows);
		mWindowStyle.add(miAsMotif);
		mWindowStyle.add(miAsMetal);

		mInfo.add(miAuthor);
		mInfo.add(miSourceInfo);
        
	    miNewGame.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
	    miSetBlockColor.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				//Color newFrontColor =
				       // JColorChooser.showDialog(blocksGame,
				                //"Set color for block", blocksGame.getGameCanvas().getFrontColor());
				//if (newFrontColor != null);
					//blocksGame..setFrontColor(newFrontColor);
			}
		});
	    miSetBackColor.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
	    miTurnHarder.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	    miTurnEasier.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	    miExit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});
	    miPlay.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
	    miPause.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
	    miResume.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
	    miStop.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
			}
		});
	    miAuthor.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	    miSourceInfo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
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
	    miAsWindows.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
    }
}
