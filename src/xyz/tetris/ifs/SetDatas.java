package xyz.tetris.ifs;

import xyz.tetris.logic.Box;

public interface SetDatas {
	
	public void setPaint(Box[][] boxes);
	public void setNextBlocks(Box[][] block);
	public void setScore(long score);
	public void gameOver();

}
