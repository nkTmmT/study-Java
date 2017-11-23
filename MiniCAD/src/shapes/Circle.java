package shapes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class Circle extends Shapes {//圆图形的各种功能实现
	private static final long serialVersionUID = 1L;
	private int x;
	private int y;
	private int radius;
	
	public Circle(int x, int y, int radius)
	{
		this.x = x;
		this.y = y;
		this.radius = radius;
	}
	@Override
	public void draw(Graphics2D g) {
		g.setColor(c);
		g.setStroke(new BasicStroke(f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL));
		g.drawOval(x-radius, y-radius, radius*2, radius*2);
	}
	
	@Override
	public Boolean contains(int x, int y) {
		Boolean change = false;
		int xZhou = (int) Math.pow(this.x-x, 2);
		int yZhou = (int) Math.pow(this.y-y, 2);
		if(xZhou+yZhou<=radius*radius)
		{
			change=true;
		}
		return change;
	}
	@Override
	public void change(int x, int y) {
		this.x=x;
		this.y=y;
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
		radius += 5;
	}
	@Override
	public void smaller() {
		radius -= 5;
	}
	@Override
	public void setColor(Color c) {
		this.c=c;
	}
}