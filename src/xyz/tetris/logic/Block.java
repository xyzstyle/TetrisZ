package xyz.tetris.logic;


public class Block {
	//一个块占的行数是4行
    private static final int BOXES_ROWS=4;
    //一个块占的列数是4列
    private static final int BOXES_COLS=4;
    //方块的样式数目是7
    public static final int BLOCK_KIND_NUMBER=7;
    //每一种样式方块的反转状态数目为4
    public static final int BLOCK_STATUS_NUMBER=4;
    /**
	 * 分别对应对7种模型的28种状态
	 */
    private  int x;

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	private int y;
	public final static int[][] STYLES = {// 共28种状态
		{0x0f00, 0x4444, 0x0f00, 0x4444}, // 长条型的四种状态
		{0x04e0, 0x0464, 0x00e4, 0x04c4}, // 'T'型的四种状态
		{0x4620, 0x6c00, 0x4620, 0x6c00}, // 反'Z'型的四种状态
		{0x2640, 0xc600, 0x2640, 0xc600}, // 'Z'型的四种状态
		{0x6220, 0x1700, 0x2230, 0x0740}, // '7'型的四种状态
		{0x6440, 0x0e20, 0x44c0, 0x8e00}, // 反'7'型的四种状态
		{0x0660, 0x0660, 0x0660, 0x0660}, // 方块的四种状态
	};



	/**
	 * 4x4的方格（Box）构成俄罗斯方块
	 */
	private Box[][] boxes=new Box[BOXES_ROWS][BOXES_COLS];
	
	public Block(){
		for (int i = 0; i < boxes.length; i++)
			for (int j = 0; j < boxes[i].length; j++) {
				boxes[i][j] = new Box(false);
			}
	}
	public Block(int x,int y,int style){
		this.x=x;
		this.y=y;
		setStyle(style);		
	}
	public Box[][] getBoxes() {
		return boxes;
	}

	private  int style;
	
	public int getStyle() {
		return style;
	}

	public static final  int KEY = 0x8000;
	

	
	public void setStyle(int style){
		this.style=style;
		int key=KEY;
		for(int i=0;i<boxes.length;i++)
			for(int j=0;j<boxes[i].length;j++){
				boolean isColor=((key&style)!=0);
			    boxes[i][j]=new Box(isColor);
				key>>=1;
			}
	}
}
