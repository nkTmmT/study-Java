package shapes;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class MyString extends Shapes {//文字图形的各种功能实现
	private static final long serialVersionUID = 1L;
	private String str;
	private int x;
	private int y;
	private int fontSize;
	private int fontType = Font.PLAIN;
	
	public MyString(String s, int x, int y, int fontSize )
	{
		this.str=s;
		this.x = x;
		this.y = y; 
		this.fontSize=fontSize;
	}
	
	@Override
	public void draw(Graphics2D g) {
		g.setColor(c);
		g.setFont(new Font("Dialog", fontType, fontSize));
		g.drawString(str, x, y);
	}

	@Override
	public Boolean contains(int x, int y) {
		Boolean change=false;
		if((x>this.x&&x<this.x+str.length()*fontSize)&&(y<this.y&&y>this.y-fontSize))
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
		fontType=Font.BOLD;
	}

	@Override
	public void thinner() {
		fontType=Font.PLAIN;
	}

	@Override
	public void bigger() {
		fontSize++;
	}

	@Override
	public void smaller() {
		if(fontSize>0)
		fontSize--;
	}

	@Override
	public void setColor(Color c) {
		this.c=c;
	}

}
