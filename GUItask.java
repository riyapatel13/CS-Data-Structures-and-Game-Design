//RIYA PATEL

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class GUItask extends JPanel implements ActionListener {

	private JButton buttons[];
	private String names[] = { "North", "South", "East","West"};
	private BorderLayout layout;
	private JPanel bigPanel,buttonPanel,scrollPanel;
	private JFrame frame;
	private String[] menuFont = {"10","20","30"};
	JComboBox fontSize = new JComboBox(menuFont);
	private String[] ftype = {"Serif","Courier","Helvetica"};
	JComboBox fontStyle = new JComboBox(ftype);
	private String[] fcolor = {"Green","Cyan","Random"};
	JComboBox fontColor = new JComboBox(fcolor);
	private String[] bcolor = {"Black","White","Random"};
	JComboBox backColor = new JComboBox(bcolor);
	private String[] butcolor = {"No Color","Orange","Red","Random"};
	JComboBox buttonColor = new JComboBox(butcolor);
	private JTextArea textArea;
	private int defSize;
	private int defStyle;
	private GridLayout gl;


	public GUItask()
	{
		frame=new JFrame("Border Layout Demo");
		frame.add(this);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		layout = new BorderLayout();
		gl = new GridLayout(1,9);
		bigPanel=new JPanel();
		buttonPanel=new JPanel();
		textArea = new JTextArea();

		buttons = new JButton [names.length];

		for (int count = 0; count<names.length; count++)
		{
			buttons[count] = new JButton(names[count]);
			buttons[count].addActionListener(this);
		}

		//adding action listener
		fontSize.addActionListener(this);
		fontStyle.addActionListener(this);
		fontColor.addActionListener(this);
		backColor.addActionListener(this);
		buttonColor.addActionListener(this);

		//setting up button panel
		buttonPanel.setLayout(gl);
		buttonPanel.add(buttons[0]);
		buttonPanel.add(buttons[1]);
		buttonPanel.add(buttons[2]);
		buttonPanel.add(buttons[3]);
		buttonPanel.add(fontSize);
		buttonPanel.add(fontStyle);
		buttonPanel.add(fontColor);
		buttonPanel.add(backColor);
		buttonPanel.add(buttonColor);

		//setting up frame
		bigPanel.setLayout(new BorderLayout());
		bigPanel.add(buttonPanel,BorderLayout.NORTH);
		bigPanel.add(textArea, BorderLayout.CENTER);
		frame.setLayout(new BorderLayout());
		frame.add(bigPanel);
		frame.setSize(800,500);
		frame.setVisible(true);

		//default settings
		defSize = buttons[0].getFont().getSize();
		defStyle = buttons[0].getFont().getStyle();
		textArea.setFont(new Font("Serif", defStyle, 10));
		textArea.setForeground(Color.GREEN);
		textArea.setBackground(Color.BLACK);
		for (JButton but: buttons)
			but.setBorder(null);
	}

	public void actionPerformed(ActionEvent event)
	{
			//buttons for the location of the menu box
			if (event.getSource() == buttons[0])
			{
				frame.remove(buttonPanel);
				gl = new GridLayout(1,9);
				buttonPanel.setLayout(gl);
				frame.add(buttonPanel,BorderLayout.NORTH);
			}
			else if (event.getSource() == buttons[1])
			{
				frame.remove(buttonPanel);
				gl = new GridLayout(1,9);
				buttonPanel.setLayout(gl);
				frame.add(buttonPanel,BorderLayout.SOUTH);
			}
			else if (event.getSource() == buttons[2])
			{
				frame.remove(buttonPanel);
				gl = new GridLayout(9,1);
				buttonPanel.setLayout(gl);
				frame.add(buttonPanel,BorderLayout.EAST);
			}
			else if (event.getSource() == buttons[3])
			{
				frame.remove(buttonPanel);
				gl = new GridLayout(9,1);
				buttonPanel.setLayout(gl);
				frame.add(buttonPanel,BorderLayout.WEST);
			}

			//font size
			else if (event.getSource() == fontSize)
			{
				String msg = (String)fontSize.getSelectedItem();
				String name = textArea.getFont().getFontName();
				int style = textArea.getFont().getStyle();
				if (msg.equals("10"))
					textArea.setFont(new Font(name, style, 10));
				else if (msg.equals("20"))
					textArea.setFont(new Font(name, style, 20));
				else if (msg.equals("30"))
					textArea.setFont(new Font(name, style, 30));
			}

			//different font types - changes font of everything (including buttons)
			else if (event.getSource() == fontStyle)
			{
				String msg = (String)fontStyle.getSelectedItem();
				int style = textArea.getFont().getStyle();
				int size = textArea.getFont().getSize();
				if (msg.equals("Serif"))
				{
					textArea.setFont(new Font("Serif", style, size));
					fontSize.setFont(new Font("Serif", defStyle, defSize));
					fontColor.setFont(new Font("Serif", defStyle, defSize));
					backColor.setFont(new Font("Serif", defStyle, defSize));
					buttonColor.setFont(new Font("Serif", defStyle, defSize));
					for (int i=0; i<buttons.length; i++)
						buttons[i].setFont(new Font("Serif", defStyle, defSize));
				}
				else if (msg.equals("Courier"))
				{
					textArea.setFont(new Font("Courier", style, size));
					fontSize.setFont(new Font("Courier", defStyle, defSize));
					fontColor.setFont(new Font("Courier", defStyle, defSize));
					backColor.setFont(new Font("Courier", defStyle, defSize));
					buttonColor.setFont(new Font("Courier", defStyle, defSize));
					for (int i=0; i<buttons.length; i++)
						buttons[i].setFont(new Font("Courier", defStyle, defSize));
				}
				else if (msg.equals("Helvetica"))
				{
					textArea.setFont(new Font("Helvetica", style, size));
					fontSize.setFont(new Font("Helvetica", defStyle, defSize));
					fontColor.setFont(new Font("Helvetica", defStyle, defSize));
					backColor.setFont(new Font("Helvetica", defStyle, defSize));
					buttonColor.setFont(new Font("Helvetica", defStyle, defSize));
					for (int i=0; i<buttons.length; i++)
						buttons[i].setFont(new Font("Helvetica", defStyle, defSize));
				}
			}

			//font color
			else if (event.getSource() == fontColor)
			{
				String msg = (String)fontColor.getSelectedItem();
				if (msg.equals("Green"))
					textArea.setForeground(Color.GREEN);
				else if (msg.equals("Cyan"))
					textArea.setForeground(Color.CYAN);
				else if (msg.equals("Random"))
				{
					int r = (int)(Math.random()*255)+1;
					int g = (int)(Math.random()*255)+1;
					int b = (int)(Math.random()*255)+1;
					textArea.setForeground(new Color(r,g,b));
				}
			}

			//text area background color
			else if (event.getSource() == backColor)
			{
				String msg = (String)backColor.getSelectedItem();
				if (msg.equals("Black"))
					textArea.setBackground(Color.BLACK);
				else if (msg.equals("White"))
					textArea.setBackground(Color.WHITE);
				else if (msg.equals("Random"))
				{
					int r = (int)(Math.random()*255)+1;
					int g = (int)(Math.random()*255)+1;
					int b = (int)(Math.random()*255)+1;
					textArea.setBackground(new Color(r,g,b));
				}
			}

			//border color of buttons
			else if (event.getSource() == buttonColor)
			{
				String msg = (String)buttonColor.getSelectedItem();
				if (msg.equals("No Color"))
				{
					for (JButton but: buttons)
						but.setBorder(null);
				}
				else if (msg.equals("Orange"))
				{
					for (JButton but: buttons)
						but.setBorder(new LineBorder(Color.ORANGE));
				}
				else if (msg.equals("Red"))
				{
					for (JButton but: buttons)
						but.setBorder(new LineBorder(Color.RED));
				}
				else if (msg.equals("Random"))
				{
					int r = (int)(Math.random()*255)+1;
					int g = (int)(Math.random()*255)+1;
					int b = (int)(Math.random()*255)+1;
					for (JButton but: buttons)
						but.setBorder(new LineBorder(new Color(r,g,b)));
				}
			}


			frame.invalidate();
			frame.validate();


	}
	public static void main(String args[] )
	{
		GUItask application = new GUItask();
	}
}