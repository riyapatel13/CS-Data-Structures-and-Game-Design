import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.awt.*;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

public class JuliaProgram extends JPanel implements AdjustmentListener, ActionListener
{
	JFrame frame;
	JScrollBar aScroll, bScroll, satScroll, darkScroll;
	JPanel scrollPanel, textPanel, bigPanel, checkBoxPanel;
	JLabel aLabel, bLabel, satLabel, darkLabel;
	JCheckBox aCheck, bCheck;
	double a=0.0, b=0.0;
	float sat=1.0f, dark=1.0f;
	int negA = 1, negB = 1;
	final int maxIter = 50;
	DecimalFormat threeDecimals=new DecimalFormat("0.000");
	public JuliaProgram()
	{
		frame = new JFrame("Julia Set Program"); //instantiates the frame
		frame.add(this); //adds everything you draw into the frame

		scrollPanel = new JPanel();
		textPanel = new JPanel();
		bigPanel = new JPanel();
		checkBoxPanel = new JPanel();

		aLabel = new JLabel("A: "+threeDecimals.format(a));
		bLabel = new JLabel("B: "+threeDecimals.format(b));
		satLabel = new JLabel("Saturation: "+threeDecimals.format(sat));
		darkLabel = new JLabel("Darkness: "+threeDecimals.format(dark));


		aScroll = new JScrollBar(JScrollBar.HORIZONTAL, 0, 0, 0, 6000);
		aScroll.addAdjustmentListener(this);
		aScroll.setPreferredSize(new Dimension(700,20));
		bScroll = new JScrollBar(JScrollBar.HORIZONTAL, 0, 0, 0, 6000);
		bScroll.addAdjustmentListener(this);
		bScroll.setPreferredSize(new Dimension(700,20));
		satScroll = new JScrollBar(JScrollBar.HORIZONTAL, 1000, 0, 0, 1000);
		satScroll.addAdjustmentListener(this);
		satScroll.setPreferredSize(new Dimension(700,20));
		darkScroll = new JScrollBar(JScrollBar.HORIZONTAL, 1000, 0, 0, 1000);
		darkScroll.addAdjustmentListener(this);
		darkScroll.setPreferredSize(new Dimension(700,20));

		aCheck = new JCheckBox("Negative A");
		aCheck.addActionListener(this);
		bCheck = new JCheckBox("Negative B");
		bCheck.addActionListener(this);

		checkBoxPanel.add(aCheck);
		checkBoxPanel.add(bCheck);
		checkBoxPanel.setLayout(new GridLayout(2,1));

		textPanel.add(aLabel);
		textPanel.add(bLabel);
		textPanel.add(satLabel);
		textPanel.add(darkLabel);
		textPanel.setLayout(new GridLayout(4,1));

		scrollPanel.add(aScroll);
		scrollPanel.add(bScroll);
		scrollPanel.add(satScroll);
		scrollPanel.add(darkScroll);
		scrollPanel.setLayout(new GridLayout(4,1)); //4=rows, 1=columns

		bigPanel.add(BorderLayout.WEST, checkBoxPanel);
		bigPanel.add(BorderLayout.WEST, textPanel);
		bigPanel.add(BorderLayout.EAST, scrollPanel);

		frame.add(BorderLayout.SOUTH, bigPanel);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //press any key to continue shows up
		frame.setSize(1000,800);
		frame.setVisible(true); //should be last line of constructor so you can see everything


	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		drawJuliaSet(g);
	}

	public void adjustmentValueChanged(AdjustmentEvent e)
	{
		if(e.getSource()==aScroll)
		{
			a=aScroll.getValue()/1000.0;
			aLabel.setText("A: "+threeDecimals.format(a));
		}
		if(e.getSource()==bScroll)
		{
			b=bScroll.getValue()/1000.0;
			bLabel.setText("B: "+threeDecimals.format(b));
		}
		if(e.getSource()==satScroll)
		{
			sat=satScroll.getValue()/1000.0f;
			satLabel.setText("Saturation: "+threeDecimals.format(sat));
		}
		if(e.getSource()==darkScroll)
		{
			dark=darkScroll.getValue()/1000.0f;
			darkLabel.setText("Darkness: "+threeDecimals.format(dark));
		}
		repaint();
	}

	public void actionPerformed(ActionEvent e)
	{
		if (aCheck.isSelected())
		{
			negA = -1;
		}
		else negA = 1;

		if (bCheck.isSelected())
		{
			negB = -1;
		}
		else negB = 1;
		repaint();
	}

	public void drawJuliaSet(Graphics g)
	{
		int w = frame.getWidth();
		int h = frame.getHeight();
		BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		double zoom = 0.5;

		for (int i=0; i<w; i++)
		{
			for (int j=0; j<h; j++)
			{
				float iter = maxIter;
				double zx = 1.5*(i-0.5*w)/(0.5*zoom*w);
				double zy = (j-0.5*h)/(0.5*zoom*h);

				while (zx*zx+zy*zy<6 && iter>0)
				{
					double difSquare = zx*zx-zy*zy+a*negA;
					zy = (2*zx*zy)+b*negB;
					zx = difSquare;
					iter--;
				}
				int c;
				if (iter>0)
					c = Color.HSBtoRGB((maxIter/iter) % 1, sat, dark);
				else c = Color.HSBtoRGB(maxIter/iter, 1, 0);
				image.setRGB(i,j,c);
			}
		}
		g.drawImage(image,0,0-bigPanel.getHeight()/2,null);
	}

	public static void main(String[] args)
	{
		JuliaProgram app = new JuliaProgram();
	}
}