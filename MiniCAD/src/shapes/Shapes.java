package shapes;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.Serializable;

public abstract class Shapes implements Serializable {//shapes父类,此处实现模式参考了课程中的shapes程序
	private static final long serialVersionUID = 1L;
	protected Color c = Color.BLACK;
	protected float f=1.0f;

	
	public abstract void draw(Graphics2D g);//画图方法
	public abstract Boolean contains(int x, int y);//判断鼠标所点击点是否在图形内
	public abstract void change(int x, int y);//改变图形位置使之随鼠标移动
	public abstract void coarsen();//加粗方法
	public abstract void thinner();//变细方法
	public abstract void bigger();//变大或变长方法
	public abstract void smaller();//变小或变短方法
	public abstract void setColor(Color c);//设置颜色方法
	
}