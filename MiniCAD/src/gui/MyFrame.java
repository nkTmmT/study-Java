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


public class MyFrame extends JFrame {//�������������frame���
	private static final long serialVersionUID = 1L;
	private FunctionJpanel jp = new FunctionJpanel();
	private PaintJpanel jp1 = new PaintJpanel();
	
	public MyFrame() {//���캯��
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
	
	
	private class MyMenuBar extends JMenuBar implements ActionListener{//�ڲ�����Ӳ˵���
		private static final long serialVersionUID = 1L;
		private JMenu file = new JMenu("�ļ�");
		private JMenu about = new JMenu("����");
		private JMenuItem save = new JMenuItem("����");
		private JMenuItem load = new JMenuItem("��ȡ");
		private JMenuItem author = new JMenuItem("������Ϣ");
		private JMenuItem help = new JMenuItem("����ָ��");
		
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
				JOptionPane.showMessageDialog(jp1, "�����ƿ���:TmmT\nQQ:1090718046.");
			}
			if(e.getSource()==help)
			{
				JOptionPane.showMessageDialog(jp1, "1.��ͼ:\n���Ҫ����ͼ�ΰ�ť,�ڿհ״�������������������.\n"
													+ "2.����ͼ��:\n"
													+ "���̲���: Ӣ������״̬��,ѡ��ͼ��Ҫ������ͼ��\n"
													+ "���: > , ��ϸ: < , �Ŵ�: + , ��С: - , ɾ��: d .\n"
													+"������: ��ͼ���ϵ���������ѡ��,��ס����ɽ����϶�.");
			}
		}
		
	}
	
	
	private class ButtonPanel extends JPanel implements ActionListener{//�ڲ�����ӻ�ͼ���ܰ�ť
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
			icons.add(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("image/ֱ��.png"))));
			icons.add(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("image/Բ.png"))));
			icons.add(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("image/����.png"))));
			icons.add(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("image/���.png"))));
			icons.add(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("image/����.png"))));
			addFunction();
		}

		
		public void addFunction()
		{
			for(int i=0; i<icons.size(); i++)//����ͼ���Сʹ֮�ʺϰ�ť��С
			{
				icons.get(i).getImage();
				Images.add(icons.get(i).getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT));
			}
			
			for(int i=0; i<buttons.size(); i++)//���ð�ť����ɫ,ͼ���Լ�����
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
	

	
	
	
	private class ColorPanel extends JPanel implements ActionListener {//�ڲ�����Ӹı���ɫ���ܰ�ť
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
		
		public void setButton()//����ɫ�Ͱ�ťһһ��Ӧ
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
