package gui;

import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class FunctionJpanel extends JPanel {//存放功能按钮的panel
		private static final long serialVersionUID = 1L;
		
		public FunctionJpanel()
		{
			setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		}
		
		@Override
		public Dimension getPreferredSize() {
			return new Dimension(130, 800);
		}
		
}
