package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import shapes.Circle;
import shapes.Line;
import shapes.MyString;
import shapes.Rectangle;
import shapes.Shapes;

public class PaintJpanel extends JPanel{//图形所呈现的载体panel
		private static final long serialVersionUID = 1L;
		private String type ="null"; //功能开关
		private String type1 ="null"; //功能开关1
		private StringBuffer name =new StringBuffer(); //保存文件名buffer
		private int fontSize=40;//改变默认字体大小
		private int shapesCount;//确定所选图形在容器中的位置
		private ArrayList<Shapes> shapes = new ArrayList<Shapes>();//图形容器,此处实现模式参考了课程中的shapes程序
	    
		public PaintJpanel()//构造函数,将此panel设置成能获得焦点以及添加相关的监听实现
		{
			setFocusable(true);
			addMouseListener
			(new MouseAdapter() 
				{
					public void mousePressed(MouseEvent e)	
					{
						if(type.equals("null"))
						{
							for(int i=0; i<shapes.size(); i++)
							{
								if(shapes.get(i).contains(e.getX(), e.getY()))
								{
									shapesCount=i;
									type="move";
									type1="on";
								}
							}
						}
						if(type.equals("Line"))
						{
							line_x = e.getX();
							line_y = e.getY();
						}
						if(type.equals("Circle"))
						{
							circle_x = e.getX();
							circle_y = e.getY();
						}
						if(type.equals("Rectangle"))
						{
							rect_x = e.getX();
							rect_y = e.getY();
						}
						if(type.equals("String"))
						{
							string_x = e.getX();
							string_y = e.getY();
						}
					}
					public void mouseReleased(MouseEvent e)	
					{
						if(type.equals("move"))
						{
							type="null";
						}
						if(type.equals("Line"))
						{
							line_x1 = e.getX();
							line_y1 = e.getY();
							shapes.add(new Line(line_x, line_y, line_x1, line_y1));
							repaint();
							type="null";
						}
						if(type.equals("Circle"))
						{
							radius = (int) Math.hypot( (e.getX()-circle_x), (e.getY()-circle_y) );
							shapes.add(new Circle(circle_x, circle_y, radius));
							repaint();
							type="null";
						}	
						if(type.equals("Rectangle"))
						{
							rect_width=e.getX();
							rect_height=e.getY();
							shapes.add(new Rectangle(rect_x, rect_y, rect_width, rect_height));
							repaint();
							type="null";
						}
						if(type.equals("String"))
						{
							shapes.add(new MyString(input, string_x, string_y, fontSize ));
							repaint();
							type="null";
							input="";
							fontSize=40;
						}	
					}
				}
			);
			addMouseMotionListener
			(new MouseMotionAdapter() 
				{
					public void  mouseDragged(MouseEvent e) 
					{
						if(type.equals("move"))
						{
							shapes.get(shapesCount).change(e.getX(), e.getY());
							repaint();
						}
						if(type.equals("Line"))
						{
							line_x1 = e.getX();
							line_y1 = e.getY();
							repaint();
						}
						if(type.equals("Circle"))
						{
							radius = (int) Math.hypot( (e.getX()-circle_x), (e.getY()-circle_y) );
							repaint();
						}
						if(type.equals("Rectangle"))
						{
							rect_width=e.getX();
							rect_height=e.getY();
							repaint();
						}
						if(type.equals("String"))
						{
							if(e.getX()>string_x)
							{
								fontSize++;
								repaint();
							}
							if(e.getX()<string_x)
							{
								fontSize--;
								repaint();
							}
						}
					}
				}
			);
			addKeyListener(new KeyAdapter() {
				public void keyTyped(KeyEvent e)
				{
					if(e.getKeyChar()=='>'&&type1.equals("on"))//变粗
					{
						shapes.get(shapesCount).coarsen();
						repaint();
					}
					if(e.getKeyChar()=='<'&&type1.equals("on"))//变细
					{
						shapes.get(shapesCount).thinner();
						repaint();
					}
					if(e.getKeyChar()=='+'&&type1.equals("on"))//变大或者边长
					{
						shapes.get(shapesCount).bigger();
						repaint();
					}
					if(e.getKeyChar()=='-'&&type1.equals("on"))//变小或者变短
					{
						shapes.get(shapesCount).smaller();
						repaint();
					}
					if(e.getKeyChar()=='d'&&type1.equals("on"))//删除选中
					{
						shapes.remove(shapesCount);
						repaint();
					}
				}
			});
			
		}
		
		@Override
		public Dimension getPreferredSize() {
			return new Dimension(670, 800);
		}
		
		
		public void write() {//保存文件为.cad后缀名
			try {
				JFileChooser chooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("CAD文件", "cad");
				chooser.setFileFilter(filter);
				Outer:
				while(true) {
					if(chooser.showSaveDialog(this)==JFileChooser.APPROVE_OPTION)
					{
						name.append(chooser.getSelectedFile());
						name.append(".cad");
						File file = new File(name.toString());
						while(file.exists()) {
							JOptionPane.showMessageDialog(this, "文件已存在!");
							if(JOptionPane.showConfirmDialog(this, "是否覆盖?")==JOptionPane.OK_OPTION)
								break;
							name.delete(0, name.length());
							continue Outer;
						}
						ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(name.toString()));
						os.writeObject(this);
						os.close();
					}
					break;
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		
		public void read() {//读取所保存的.cad文件
			try {
				JFileChooser chooser = new JFileChooser();
			    FileNameExtensionFilter filter = new FileNameExtensionFilter(
			        "CAD文件", "cad");
			    chooser.setFileFilter(filter);
			    if(chooser.showOpenDialog(this)==JFileChooser.APPROVE_OPTION)
			    {
					ObjectInputStream is = new ObjectInputStream(new FileInputStream(chooser.getSelectedFile()));
					PaintJpanel in =(PaintJpanel)is.readObject();
					is.close();
					clear();
					this.shapes=in.shapes;
					repaint();
			    }
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		
		public void clear()//清楚所有图形并将相关参数归零
		{
			line_x=0;
			line_y=0;
			line_x1=0;
			line_y1=0;
			circle_x=0;
			circle_y=0;
			radius=0;
			rect_x=0;
			rect_y=0;
			rect_width=0;
			rect_height=0;
			string_x=0;
			string_y=0;
			shapes.removeAll(shapes);
			super.paintComponent(getGraphics());
		}
		
		public void setColor(Color c)//设置颜色
		{
			if(type1.equals("on"))
			{
				shapes.get(shapesCount).setColor(c);
			}
			if(type.equals("null")&&!type1.equals("on"))
			{
				shapes.get(shapes.size()-1).setColor(c);
			}
			repaint();
		}

		@Override
		public void paintComponent(Graphics g)//图形绘制方法
		{
			super.paintComponent(g);
			requestFocusInWindow(true);
			Graphics2D g2d = (Graphics2D) g;
			g2d.setFont(new Font("Dialog", Font.PLAIN, fontSize));
			if(type.equals("Line"))
			{
				g2d.drawLine(line_x,line_y, line_x1, line_y1);
			}
			if(type.equals("Circle"))
			{
				g2d.drawOval(circle_x-radius, circle_y-radius, radius*2, radius*2);
			}
			if(type.equals("Rectangle"))
			{
				g2d.drawRect(Math.min(rect_x, rect_width), Math.min(rect_y, rect_height), Math.abs(rect_x- rect_width), Math.abs(rect_y - rect_height));
			}
			if(type.equals("String"))
			{
				g2d.drawString(input, string_x, string_y);	
			}
			for(Shapes s : shapes)
			{
				s.draw(g2d);
			}
		}
		
		//直线图形相关参数以及方法
		private int line_x;
		private int line_y;
		private int line_x1;
		private int line_y1;

		public void addLine() {
			type="Line";
			type1="Line";
		}
		
		//圆图形相关参数以及方法
		private int circle_x;
		private int circle_y;
		private int radius;
		
		public void addCircle() {
			type="Circle";
			type1="Circle";
		}

		//矩形图形相关参数以及方法
		private int rect_x;
		private int rect_y;
		private int rect_width;
		private int rect_height;
		
		public void addRectangle() 
		{
			type="Rectangle";
			type1="Rectangle";
		}
		
		//文字图形相关参数以及方法
		private String input="";
		private int string_x;
		private int string_y;
		
		public void addString() {
			type="String";
			type1="String";
			input=JOptionPane.showInputDialog("请输入你要操作的文字:");
			if(input==null)
				type="null";
		}

		
		
}
