/**
 * 
 */
package xyz.tetris.logic;

import java.awt.*;

/**
 * @author: zxy
 * @File: MainMenu.java
 * @Date: 2015.10.26
 * @Describe: 俄罗斯方块的java实现
 */

/**
 * 方格类组成俄罗斯方块和场景的基本元素
 */
public class Box implements Cloneable{
    private boolean color;
    private Dimension size=new Dimension();
    public Box(boolean color){
    	this.color=color;
    }
	public boolean isColor() {
		return color;
	}
	public void setColor(boolean color) {
		this.color = color;
	}
	public Dimension getSize() {
		return size;
	}
	public void setSize(Dimension size) {
		this.size = size;
	} 
	/**
	 * 覆盖Object的Object clone()，实现克隆
	 * @return Object,克隆的结果
	 */
	
	public Object clone(){
		Object cloned=null;
		try {
			cloned=super.clone();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cloned;
	}
}
