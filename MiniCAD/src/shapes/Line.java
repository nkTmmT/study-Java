package shapes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class Line extends Shapes {//直线图形的各种功能实现
	private static final long serialVersionUID = 1L;
	private int x1;
	private int y1;
	private int x2;
	private int y2;
	private final double a;

	public Line(int x1, int y1, int x2, int y2)
	{
		this.x1 = x1; this.y1 = y1;
		this.x2 = x2; this.y2 = y2;
		this.a=(double)(y2-y1)/(x2-x1);
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(c);
		g.setStroke(new BasicStroke(f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL));
		g.drawLine(x1, y1, x2, y2);
	}

	@Override
	public Boolean contains(int x, int y) {
		Boolean change=false;
		if(Math.abs(x2-x1)!=0&&Math.abs(y2-y1)!=0)
		{
			if((x>Math.min(x1, x2)&&x<Math.max(x1, x2))&&(y>Math.min(y1, y2)&&y<Math.max(y1, y2)))
			{
				change=true;
			}
		}
		else if(Math.abs(x2-x1)==0&&Math.abs(y2-y1)!=0)
		{
			if(y>Math.min(y1, y2)&&y<Math.max(y1, y2)&&x>=x1-10&&x<x1+10)
			{
				change=true;
			}
		}
		else if(Math.abs(y2-y1)==0&&Math.abs(x2-x1)!=0)
		{
			if(x>Math.min(x1, x2)&&x<Math.max(x1, x2)&&y>=y1-10&&y<y1+10)
			{
				change=true;
			}
		}
		return change;
	}

	@Override
	public void change(int x, int y) {
		int xOffset=x-this.x1;
		int yOffset=y-this.y1;
		this.x1+=xOffset;
		this.y1+=yOffset;
		this.x2+=xOffset;
		this.y2+=yOffset;
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
		if(x1==x2&&y1!=y2)
		{
			y2+=10;
		}
		else if(y1==y2&&x1!=x2)
		{
			x2+=10;
		}
		else if(a>0)
		{
			x2+=5;
			y2+=5*a;
			
		}
		else if(a<0&&x2>0&&y2>0)
		{
			x2-=5;
			y2-=5*a;
		}
	}

	@Override
	public void smaller() {
		if(x1==x2&&y1!=y2&&y2>0)
		{
			y2-=10;
		}
		else if(y1==y2&&x1!=x2&&x2>0)
		{
			x2-=10;
		}
		else if(a>0&&x2>0&&y2>0)
		{
			x2-=5;
			y2-=5*a;
		}
		else if(a<0)
		{
			x2+=5;
			y2+=5*a;
		}
	}

	@Override
	public void setColor(Color c) {
		this.c=c;
	}

}
