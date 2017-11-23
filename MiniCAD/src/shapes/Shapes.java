package shapes;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.Serializable;

public abstract class Shapes implements Serializable {//shapes����,�˴�ʵ��ģʽ�ο��˿γ��е�shapes����
	private static final long serialVersionUID = 1L;
	protected Color c = Color.BLACK;
	protected float f=1.0f;

	
	public abstract void draw(Graphics2D g);//��ͼ����
	public abstract Boolean contains(int x, int y);//�ж������������Ƿ���ͼ����
	public abstract void change(int x, int y);//�ı�ͼ��λ��ʹ֮������ƶ�
	public abstract void coarsen();//�Ӵַ���
	public abstract void thinner();//��ϸ����
	public abstract void bigger();//����䳤����
	public abstract void smaller();//��С���̷���
	public abstract void setColor(Color c);//������ɫ����
	
}