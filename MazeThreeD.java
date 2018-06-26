import java.awt.Graphics;
import java.awt.Color;
import java.awt.Polygon;
import java.awt.Font;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

//index out of bounds

public class MazeThreeD extends JPanel implements KeyListener,MouseListener
{
	JFrame frame;
	String [][] maze = new String[14][27];
	int xPos=2,yPos=0, orientation=90;
	ArrayList<Wall> wallList = new ArrayList<Wall>();
	int stepCount = 0;


	public MazeThreeD()
	{
		setBoard();
		setWalls();
		frame=new JFrame();
		frame.add(this);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1000,800);
		frame.setVisible(true);
		frame.addKeyListener(this);
	}
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.setColor(Color.BLACK); //this will set the background color
		g.fillRect(0,0,1000,800);

		//drawBoard here!

		g.setColor(Color.WHITE);


		//2D Maze Code

		for (int i=0; i<maze.length; i++)
		{
			for (int j=0; j<maze[0].length; j++)
			{
				g.drawRect(j*15,i*15,15,15);		
														
				if (maze[i][j].equals("o"))
				{										
					g.fillRect(j*15,i*15,15,15);
				}
			}
		}
		g.setColor(Color.RED);
		g.fillRect(yPos*15,xPos*15,15,15);
		


		for(Wall w:wallList)
		{
			g.setColor(w.getColor());
			g.fillPolygon(w.getPoly());
			g.setColor(Color.BLACK);
			g.drawPolygon(w.getPoly());
		}
		Font tr = new Font("TimesRoman", Font.PLAIN, 24);
		g.setFont(tr);
		g.setColor(Color.RED);
		g.drawString("Step Count: "+stepCount, 750, 700);
		
		if (orientation==0)
			g.drawString("Direction: "+orientation+" (up)", 750, 675);
		else if (orientation==90)
			g.drawString("Direction: "+orientation+" (right)", 750, 675);
		else if (orientation==180)
			g.drawString("Direction: "+orientation+" (down)", 750, 675);
		else if (orientation==270)
			g.drawString("Direction: "+orientation+" (left)", 750, 675);
		
		if (maze[xPos][yPos]==maze[12][26])
		{
			tr = new Font("TimesRoman", Font.PLAIN, 36);
			g.setFont(tr);
			g.setColor(Color.GREEN);
			g.drawString("CONGRATULATIONS!", 225, 400);
			g.drawString("You did it!", 325, 440);
		}
		if (maze[xPos][yPos]==maze[2][0] && orientation==270)
		{
			tr = new Font("TimesRoman", Font.PLAIN, 36);
			g.setFont(tr);
			g.setColor(Color.RED);
			g.drawString("TURN AROUND", 275, 400);
			g.drawString("You're back at the start", 240, 440);
		}
	}
	
	public void setWalls()
	{
		wallList=new ArrayList<Wall>();
		if (orientation==0) //facing up
		{
			for (int i=0; i<4; i++)
			{
				if (xPos-i>=0)
				{
					setTopWalls(i);
					setBottomWalls(i);
				}
				if (xPos-i>=0 && yPos-1>=0 && maze[xPos-i][yPos-1].equals("o"))
					setLeftWalls(i);
				if (xPos-i>=0 && yPos+1<maze[0].length && maze[xPos-i][yPos+1].equals("o"))
					setRightWalls(i);
			}
			for (int i=4; i>=0; i--)
			{
				if (xPos-i>=0 && maze[xPos-i][yPos].equals("o"))
					setBackWalls(i);
				else continue;
			}

		}
		if (orientation==90) //facing right
		{
			for (int i=0; i<4; i++)
			{
				if (yPos+i<maze[0].length)
				{
					setTopWalls(i);
					setBottomWalls(i);
				}
				if (xPos-1>=0 && yPos+i<maze[0].length && maze[xPos-1][yPos+i].equals("o"))
					setLeftWalls(i);
				if (xPos+1<maze.length && yPos+i<maze[0].length && maze[xPos+1][yPos+i].equals("o"))
					setRightWalls(i);
			}
			for (int i=4; i>=0; i--)
			{
				if (yPos+i<maze[0].length && maze[xPos][yPos+i].equals("o"))
					setBackWalls(i);
				else continue;
			}
		}
		if (orientation==180) //facing down
		{
			for (int i=0; i<4; i++)
			{
				if (xPos+i<maze.length)
				{
					setTopWalls(i);
					setBottomWalls(i);
				}
				if((xPos+i<maze.length && yPos+1<maze[0].length && maze[xPos+i][yPos+1].equals("o")))
					setLeftWalls(i);
				if((xPos+i<maze.length && yPos-1>=0 && maze[xPos+i][yPos-1].equals("o")))
					setRightWalls(i);
			}
			for (int i=4; i>=0; i--)
			{
				if (xPos+i<maze.length && maze[xPos+i][yPos].equals("o"))
					setBackWalls(i);
				else continue;
			}

		}
		if (orientation==270) //facing left
		{
			for (int i=0; i<4; i++)
			{
				if (yPos-i<maze[0].length)
				{
					setTopWalls(i);
					setBottomWalls(i);
				}
				if((xPos+1<maze.length && yPos-i>=0 && maze[xPos+1][yPos-i].equals("o")))
					setLeftWalls(i);
				if((xPos-1>=0 && yPos-i>=0 && maze[xPos-1][yPos-i].equals("o")))
					setRightWalls(i);
			}
			for (int i=4; i>=0; i--)
			{
				if (yPos-i>=0 && maze[xPos][yPos-i].equals("o"))
					setBackWalls(i);
				else if (yPos-i>=0 && maze[xPos][yPos-i]==maze[2][0])
					setBackWalls(i+1);
				else continue;
			}			

		}
	}

	public void setLeftWalls(int i)
	{
		int[] xLeft = {100+50*i, 700-50*i, 650-50*i, 150+50*i};
		int[] yLeft = {100+50*i, 100+50*i, 150+50*i, 150+50*i};

		wallList.add(new Wall(xLeft, yLeft,Color.GRAY));
	}

	public void setTopWalls(int i)
	{
		int[] yTop = {100+50*i, 700-50*i, 650-50*i, 150+50*i};
		int[] xTop = {100+50*i, 100+50*i, 150+50*i, 150+50*i};

		wallList.add(new Wall(xTop, yTop,Color.GRAY));
	}

	public void setBottomWalls(int i)
	{
		int[] yBot = {150+50*i, 650-50*i, 700-50*i, 100+50*i};
		int[] xBot = {650-50*i, 650-50*i, 700-50*i, 700-50*i};

		wallList.add(new Wall(xBot, yBot,Color.GRAY));
	}

	public void setRightWalls(int i)
	{
		int[] yRight = {650-50*i, 700-50*i, 700-50*i, 650-50*i};
		int[] xRight = {150+50*i, 100+50*i, 700-50*i, 650-50*i};

		wallList.add(new Wall(xRight, yRight,Color.GRAY));
	}
	
	public void setBackWalls(int i)
	{
		int[] yBack = {300-50*(4-i), 500+50*(4-i), 500+50*(4-i), 300-50*(4-i)};
		int[] xBack = {300-50*(4-i), 300-50*(4-i), 500+50*(4-i), 500+50*(4-i)};
		
		wallList.add(new Wall(xBack, yBack,Color.GRAY));
	}


	public void setBoard()
	{
		int r=0;
		try
		{
			BufferedReader input = new BufferedReader(new FileReader("MazeImage.txt"));
			String text;
			int col=0;
			while( (text=input.readLine())!= null)
			{
				for(int row=0; row<text.length(); row++)
				{
					maze[col][row] = text.substring(row,row+1);
				}
				col++;
			}

			/*for (col=0; col<maze.length; col++)
			{
				for (int row=0; row<maze[0].length; row++)
				{
					System.out.print(maze[col][row]);
				}
				System.out.println();
			}*/

		}
		catch (IOException io)
		{
			System.err.println("File error");
		}

		maze[yPos][xPos] = "x";

	}
	public void keyPressed(KeyEvent e)
	{
		 if (e.getKeyCode()==KeyEvent.VK_UP)
		 {
			 if (orientation == 0) //up
			 {
				 if (xPos-1 >= 0 && maze[xPos-1][yPos].equals("="))
				 {
					 xPos--;
					 stepCount++;
				 }
			 }
			 else if (orientation == 90)//right
			 {
				 if (yPos+1<maze[0].length && maze[xPos][yPos+1].equals("="))
				 {
					 yPos++;
					 stepCount++;
				 }
			 }
			 else if (orientation == 180)//down
			 {
				if (xPos+1<maze.length && maze[xPos+1][yPos].equals("="))
			 	{
					 xPos++;
					 stepCount++;
				}
			 }
			 else if (orientation == 270)//left
			 {
				 if (yPos-1 >= 0 && maze[xPos][yPos-1].equals("="))
				 {
					 yPos--;
					 stepCount++;
				 }
			 }
		 }
		 if (e.getKeyCode()==KeyEvent.VK_LEFT)
		 {
			 if (orientation == 0)
			 {
				 orientation = 270;
				 stepCount++;
			 }
			 else if (orientation == 90)
			 {
				 orientation = 0;
				 stepCount++;
			 }
			 else if (orientation == 180)
			 {
				 orientation = 90;
				 stepCount++;
			 }
			 else if (orientation == 270)
			 {
				 orientation = 180;
				 stepCount++;
			 }
		 }
		 if (e.getKeyCode()==KeyEvent.VK_RIGHT)
		 {
			 if (orientation == 0)
			 {
				 orientation = 90;
				 stepCount++;
			 }
			 else if (orientation == 90)
			 {
				 orientation = 180;
				 stepCount++;
			 }
			 else if (orientation == 180)
			 {
				 orientation = 270;
				 stepCount++;
			 }
			 else if (orientation == 270)
			 {
				 orientation = 0;
				 stepCount++;
			 }

		 }
		 setWalls();
		 repaint();
	}
	public void keyReleased(KeyEvent e)
	{
	}
	public void keyTyped(KeyEvent e)
	{
	}
	public void mouseClicked(MouseEvent e)
	{
	}
	public void mousePressed(MouseEvent e)
	{
	}
	public void mouseReleased(MouseEvent e)
	{
	}
	public void mouseEntered(MouseEvent e)
	{
	}
	public void mouseExited(MouseEvent e)
	{
	}
	public static void main(String args[])
	{
		MazeThreeD app=new MazeThreeD();
	}
}