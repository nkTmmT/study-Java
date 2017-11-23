package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class MyFrame extends JFrame {//承载所有组件的frame框架
	private static final long serialVersionUID = 1L;
	private FunctionJpanel jp = new FunctionJpanel();
	private PaintJpanel jp1 = new PaintJpanel();
	
	public MyFrame() {//构造函数
		super("MiniCad");
		jp1.setBackground(new Color(245, 245, 247));
		add(jp1);
		jp.add(new ButtonPanel());
		jp.add(new ColorPanel());
		add(jp,BorderLayout.EAST);
		setJMenuBar(new MyMenuBar());
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("image/icon.png")));
		setVisible(true);
		setSize(800, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}
	
	
	private class MyMenuBar extends JMenuBar implements ActionListener{//内部类添加菜单栏
		private static final long serialVersionUID = 1L;
		private JMenu file = new JMenu("文件");
		private JMenu about = new JMenu("关于");
		private JMenuItem save = new JMenuItem("保存");
		private JMenuItem load = new JMenuItem("读取");
		private JMenuItem author = new JMenuItem("作者信息");
		private JMenuItem help = new JMenuItem("操作指南");
		
		public MyMenuBar() {
			save.addActionListener(this);
			load.addActionListener(this);
			author.addActionListener(this);
			help.addActionListener(this);
			file.add(save);
			file.add(load);
			about.add(author);
			about.add(help);
			add(file);
			add(about);
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==save)
			{
				jp1.write();
			}
			if(e.getSource()==load)
			{
				jp1.read();
			}
			if(e.getSource()==author)
			{
				JOptionPane.showMessageDialog(jp1, "网易云课堂:TmmT\nQQ:1090718046.");
			}
			if(e.getSource()==help)
			{
				JOptionPane.showMessageDialog(jp1, "1.画图:\n点击要画的图形按钮,在空白处按下鼠标左键滑动即可.\n"
													+ "2.操作图形:\n"
													+ "键盘操作: 英文输入状态下,选中图形要操作的图形\n"
													+ "变粗: > , 变细: < , 放大: + , 缩小: - , 删除: d .\n"
													+"鼠标操作: 在图形上点击左键即可选中,按住左键可进行拖动.");
			}
		}
		
	}
	
	
	private class ButtonPanel extends JPanel implements ActionListener{//内部类添加画图功能按钮
		private static final long serialVersionUID = 1L;
		
		private ArrayList<JButton> buttons = new ArrayList<JButton>();
		private ArrayList<ImageIcon> icons = new ArrayList<ImageIcon>();
		private ArrayList<Image> Images = new ArrayList<Image>();
		
		public ButtonPanel()
		{
			setLayout(new GridLayout(5,1));
			buttons.add(new JButton());
			buttons.add(new JButton());
			buttons.add(new JButton());
			buttons.add(new JButton());
			buttons.add(new JButton());
			icons.add(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("image/直线.png"))));
			icons.add(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("image/圆.png"))));
			icons.add(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("image/矩形.png"))));
			icons.add(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("image/清除.png"))));
			icons.add(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("image/文字.png"))));
			addFunction();
		}

		
		public void addFunction()
		{
			for(int i=0; i<icons.size(); i++)//重设图标大小使之适合按钮大小
			{
				icons.get(i).getImage();
				Images.add(icons.get(i).getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT));
			}
			
			for(int i=0; i<buttons.size(); i++)//设置按钮的颜色,图标以及功能
			{
				buttons.get(i).setBackground(new Color(240+3*i, 240+3*i, 240+3*i));
				buttons.get(i).addActionListener(this);
				buttons.get(i).setIcon(new ImageIcon(Images.get(i)));
				this.add(buttons.get(i));
			}
			
		}
		
		@Override
		public Dimension getPreferredSize() {
			return new Dimension(130, 550);
		}
		
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==buttons.get(0))
			{
				jp1.addLine();
			}
			if(e.getSource()==buttons.get(1)) 
			{
				jp1.addCircle();
			}
			if(e.getSource()==buttons.get(2))
			{			
				jp1.addRectangle();
			}
			if(e.getSource()==buttons.get(3))
			{
				jp1.clear();
			}	
			if(e.getSource()==buttons.get(4))
			{
				jp1.addString();
			}	
		}
		
	
}
	

	
	
	
	private class ColorPanel extends JPanel implements ActionListener {//内部类添加改变颜色功能按钮
		private static final long serialVersionUID = 1L;
		private ArrayList<Color> colors = new ArrayList<Color>();
		private JButton[] colorButtons;

		
		public ColorPanel()
		{
			setLayout(new GridLayout(4, 4));
			colors.add(new Color(254, 67, 101));
			colors.add(new Color(252, 157, 154));
			colors.add(new Color(249, 205, 173));
			colors.add(new Color(200, 200, 169));
			colors.add(new Color(131, 175, 155));
			colors.add(Color.BLACK);
			colors.add(Color.BLUE);
			colors.add(Color.CYAN);
			colors.add(Color.DARK_GRAY);
			colors.add(Color.GREEN);
			colors.add(Color.MAGENTA);
			colors.add(Color.ORANGE);
			colors.add(Color.PINK);
			colors.add(Color.RED);
			colors.add(Color.WHITE);
			colors.add(Color.YELLOW);
			setButton();
		}

		@Override
		public Dimension getPreferredSize() {
			return new Dimension(130, 130);
		}
		
		public void setButton()//将颜色和按钮一一对应
		{
			colorButtons = new JButton[colors.size()];
			for(int i = 0; i<colorButtons.length; i++)
			{
				colorButtons[i] = new JButton();
				colorButtons[i].addActionListener(this);
			}
			for(int i = 0; i<colorButtons.length; i++)
			{
				colorButtons[i].setBackground(colors.get(i));
				add(colorButtons[i]);
			}
		}
		
		
		@Override
		public void actionPerformed(ActionEvent e) {
			for(int i = 0; i<colorButtons.length; i++)
			{
				if(e.getSource()==colorButtons[i])
				{
					jp1.setColor(colors.get(i));
				}
			}
		}

	}
	
}
