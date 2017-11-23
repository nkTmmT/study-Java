package shapes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class Rectangle extends Shapes {//矩形图形的各种功能实现
	private static final long serialVersionUID = 1L;
	private int x;
	private int y;
	private int width;
	private int height;
	
	public Rectangle(int x, int y, int width, int height)
	{
		this.x = Math.min(x, width);
		this.y = Math.min(y, height);
		this.width = Math.abs(x - width);
		this.height = Math.abs(y - height);
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(c);
		g.setStroke(new BasicStroke(f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL));
		g.drawRect(x, y, width, height);
	}

	@Override
	public Boolean contains(int x, int y) {
		Boolean change=false;
		if((x>this.x&&x<this.width+this.x)&&(y>this.y&&y<this.height+this.y))
		{
			change=true;
		}
		return change;
	}

	@Override
	public void change(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public void coarsen() {
		f++;
	}

	@Override
	public void thinner() {
		if(f>0)
			f--;
	}

	@Override
	public void bigger() {
		width+=10;
		height+=10;
	}

	@Override
	public void smaller() {
		width-=10;
		height-=10;
	}

	@Override
	public void setColor(Color c) {
		this.c=c;
	}

}
