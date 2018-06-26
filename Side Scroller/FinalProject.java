//RIYA PATEL
//SPRITE DOES NOT SHOW UP THE FIRST TIME. TRY RERUNNING
//HERO CAN JUMP ON BLOCKS BUT CANT FALL

//INSTRUCTIONS: use the arrow keys to move and the up arrow to jump. that bouncing head is a power up which is a laser that lasts for 
//				a certain amount of time. use it to kill the skeletons. keep running till you reach the red flag. 

import javax.swing.*;
import java.awt.*;
import java.awt.Font;
import java.awt.event.*;
import java.io.*;
import java.lang.*;
import java.math.*;
import java.awt.image.*;
import java.applet.*;
import javax.swing.border.*;
import javax.imageio.ImageIO;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.awt.geom.AffineTransform;


public class FinalProject extends JPanel implements KeyListener,Runnable
{
	private float angle;
	int x;
	int y;
	int bounce;
	int backgroundX, backgroundXPtTwo, backgroundXPtThree;
	private JFrame frame;
	Thread t;
	private boolean gameOn;
	BufferedImage backgroundOne, backgroundTwo, backgroundThree, backgroundFour, backgroundOnePtTwo, backgroundTwoPtTwo, backgroundThreePtTwo, backgroundFourPtTwo, backgroundOnePtThree, backgroundTwoPtThree, backgroundThreePtThree, backgroundFourPtThree;
	BufferedImage /*guy, guy2,*/ block, powerup, actualPower, endOne, endTwo, endThree, endFour, endPoint, finOne, finTwo, finThree;
	Image subGuy/*, subBg*/;
	//BufferedImage[] guyForward=new BufferedImage[9];
	//BufferedImage[] guyBackward=new BufferedImage[9];
	//BufferedImage[] bgls=new BufferedImage[9];
	//BufferedImage[] bgrs=new BufferedImage[9];
	boolean restart=false;
	//boolean right = true;
	boolean powerBounce = false;
	boolean activatedPower = false;
	boolean firstTime=true;
	boolean jumping=false;
	boolean win;
	boolean platform=false;
	PowerUp pu;
	//int imgCount=0;
	int temp=0;
	int jumpTime=0;
	int powerX = 1100;
	int actualPowerLoc;
	int powerTimer=0;
	int timeWoPower=0;
	boolean badIsDed;
	boolean fallFromBlock = false;
	double steps =0;
	ArrayList<Block> blocks = new ArrayList<Block>();
	Rectangle /*heroRect,*/ powerRect, laserRect, badRect, endRect;
	BadGuy bad = new BadGuy();
	Hero hero = new Hero();
	
	
	public FinalProject()
	{
		frame=new JFrame();
		x=100;
		y=550;
		gameOn=true;
		backgroundXPtTwo = 1920;
		backgroundXPtThree = 3840;
		
		try {
			backgroundOne = ImageIO.read(new File("layer_01_1920 x 1080.png"));
			backgroundTwo = ImageIO.read(new File("layer_03_1920 x 1080.png"));
			backgroundThree = ImageIO.read(new File("layer_05_1920 x 1080.png"));
			backgroundFour = ImageIO.read(new File("layer_08_1920 x 1080.png"));
			backgroundOnePtTwo = ImageIO.read(new File("layer_01_1920 x 1080.png"));
			backgroundTwoPtTwo = ImageIO.read(new File("layer_03_1920 x 1080.png"));
			backgroundThreePtTwo = ImageIO.read(new File("layer_05_1920 x 1080.png"));
			backgroundFourPtTwo = ImageIO.read(new File("layer_08_1920 x 1080.png"));
			backgroundOnePtThree = ImageIO.read(new File("layer_01_1920 x 1080.png"));
			backgroundTwoPtThree = ImageIO.read(new File("layer_03_1920 x 1080.png"));
			backgroundThreePtThree = ImageIO.read(new File("layer_05_1920 x 1080.png"));
			backgroundFourPtThree = ImageIO.read(new File("layer_08_1920 x 1080.png"));
			endPoint = ImageIO.read(new File("endpoint.png"));
			finOne = ImageIO.read(new File("finOne.png"));
			finTwo = ImageIO.read(new File("finTwo.png"));
			finThree = ImageIO.read(new File("finThree.png"));
			endOne = ImageIO.read(new File("endOne.png"));
			endTwo = ImageIO.read(new File("endTwo.png"));
			endThree = ImageIO.read(new File("endThree.png"));
			endFour = ImageIO.read(new File("endFour.png"));
			block = ImageIO.read(new File("block.png"));
			powerup = ImageIO.read(new File("powerup.png"));
			//actualPower = ImageIO.read(new File("laser.png"));
			//bgl = ImageIO.read(new File("EnemyLeft.png"));
			//bgr = ImageIO.read(new File("EnemyRight.png"));
			//heroRect = new Rectangle(x+15,580,45,45); 
			powerRect = new Rectangle(1545,560,75,75);
			//guy = ImageIO.read(new File("DogeRunningForward.png"));
			//guy2 = ImageIO.read(new File("DogeRunningBackward.png"));
			/*for(int x=0;x<9;x++)
			{
				guyForward[x]=guy.getSubimage(x*79,0,79,102); //find the best rectangle
			}
			for(int x=0;x<9;x++)
			{
				guyBackward[x]=guy2.getSubimage(x*78,0,78,102); //find the best rectangle
			}
			for(int x=0;x<9;x++)
			{
				bgls[x]=bgl.getSubimage(x*64,0,64,73); //find the best rectangle
			}
			for(int x=0;x<9;x++)
			{
				bgrs[x]=bgr.getSubimage(x*64,0,64,73); //find the best rectangle
			}*/
			endRect = new Rectangle(5385-(int)steps,398,20, 250);
		}
		catch (IOException e) {}
		
		try
		{
			BufferedReader input = new BufferedReader(new FileReader("BlockLocs.txt"));
			String text;
			int yVal = 0;
			while( (text=input.readLine())!= null)
			{
				//System.out.println(text);
				yVal++;
				for(int xVal=0; xVal<text.length(); xVal++)
				{
					if((text.substring(xVal,xVal+1)).equals("*"))
						blocks.add(new Block((double)xVal, (double)yVal, block));
				}
				//your code goes in here to chop up the maze design
				//fill maze array with actual maze stored in text file
				//col++;
			}

			
		}
		catch(Exception e){}

		//subGuy=guyForward[0];
		
		//subBg=bgs[0];
		frame.addKeyListener(this);
		frame.add(this);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		t=new Thread(this);
		t.start();
	}

	public void run()
	{
		while(true)
		{
			//System.out.println((int)(Math.random()*2)+1);
			
			if(gameOn)
			{
				//activatedPower = false;
				if (bad.getBorder().intersects(hero.getBorder()))
				{
					gameOn = false;
					win=false;
				}
				else if (endRect.intersects(hero.getBorder()))
				{
					gameOn=false;
					win=true;
				}
				else
				{
					bad.move();
					//powerX-=10;
					if (activatedPower)
					{
						//timeWoPower=0;
						if (pu.getBorder().intersects(bad.getBorder()))
							bad.setAttackCount(bad.getAttackCount()+10);
						if (bad.getAttackCount()>=1150)
						{
							badIsDed=false;
							if (bad.isRight())
								bad.setX(hero.getX()-1000);
							else bad.setX(hero.getX()+2100);
							bad.setAttackCount(0);
						}
						else if (bad.getAttackCount()>=1000)
						{
							badIsDed=true;
						}
						if (hero.isRight())
							pu.setX(hero.getX()+75);
						else pu.setX(hero.getX()-390);
						if (activatedPower)
						{
							powerTimer+=10;
						}
						if (powerTimer>=3000)
						{
							activatedPower = false;
							powerTimer=0;
						}
					}
					/*else 
					{
						timeWoPower+=10;
						powerX=-1000;
					}
					if (timeWoPower>=3000)
					{
						if (temp==0)
						{
							int rand = (int)(Math.random()*600)+(x-300);
							temp=rand;
							firstTime=true;
						}
						if (firstTime)
						{
							powerX=temp;
							firstTime=false;
						}
						else powerX-=10;
					}
					if (timeWoPower>=8000)
					{
						powerX=-1000;
						timeWoPower=0;
					}*/
					if (hero.getBorder().intersects(powerRect))
					{
						activatedPower = true;
						pu = new PowerUp(hero.getX(),hero.getY());
					}

					if (jumping)
					{
						/*for (Block b:blocks)
						{
							if(heroRect.intersects(b.getBorder()) && x>=b.getX()*60 && y>=b.getY()*60+150)
							{
								y=(int)b.getY()*60+150;
							}
						}*/
						if (jumpTime>=300)
						{
							jumping=false;
							jumpTime=0;
						}
						else if (jumpTime>=150 )
						{
							//y+=22;
							hero.setY(hero.getY()+14);
							if (hero.isRight())
							{
								//x+=5;
								hero.setX(hero.getX()+5);
								//heroRect = new Rectangle(x+4,y+30,45,45);
								hero.getBorder();
							}
							else 
							{
								//x-=5;
								hero.setX(hero.getX()-5);
								//heroRect = new Rectangle(x+12,y+302,45,45);
								hero.getBorder();
							}
							if (hero.getX()>=1100)
								hero.setX(1100);
							jumpTime+=10;
						}
						else
						{
							//y-=22;
								for (Block b:blocks)
								{
									
									if ((hero.getBorder().intersects(b.getBorder())))
									{
										hero.setY((int)b.getY()*70-21);
										hero.getBorder();
										
									}
									
									//else hero.setY(hero.getY()-14);
								}
							
							hero.setY(hero.getY()-14);
							if (hero.isRight())
							{
								hero.setX(hero.getX()+5);
								//x+=5;
								hero.getBorder();
								//heroRect = new Rectangle(x+4,y+30,45,45);
							}
							else 
							{
								//x-=5;
								hero.setX(hero.getX()-5);
								//heroRect = new Rectangle(x+12,y+30,45,45);
								hero.getBorder();
							}
							if (hero.getX()>=1100)
								hero.setX(1100);
							jumpTime+=10;
						}
					}
					

					repaint();
				}
			}
			if(restart)
			{
				restart=false;
				gameOn=true;
			}
			try
			{
				t.sleep(10);
			}catch(InterruptedException e)
			{
			}
		}
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;

		//all painting happens here!

		g2d.setColor(Color.YELLOW);
		g2d.fillRect(0,0,1920,1080);
		g2d.setColor(Color.BLACK);
		g2d.drawImage(backgroundFour,backgroundX,0,null);
		g2d.drawImage(backgroundThree,backgroundX,-200,null);
		g2d.drawImage(backgroundTwo,backgroundX,-200,null);
		g2d.drawImage(backgroundOne,backgroundX,-200,null);
		g2d.drawImage(backgroundFourPtTwo,backgroundXPtTwo,0,null);
		g2d.drawImage(backgroundThreePtTwo,backgroundXPtTwo,-200,null);
		g2d.drawImage(backgroundTwoPtTwo,backgroundXPtTwo,-200,null);
		g2d.drawImage(backgroundOnePtTwo,backgroundXPtTwo,-200,null);
		g2d.drawImage(backgroundFourPtThree,backgroundXPtThree,0,null);
		g2d.drawImage(backgroundThreePtThree,backgroundXPtThree,-200,null);
		g2d.drawImage(backgroundTwoPtThree,backgroundXPtThree,-200,null);
		g2d.drawImage(backgroundOnePtThree,backgroundXPtThree,-200,null);
		g2d.drawImage(endPoint,4920-(int)steps,180,null);
		g2d.setColor(Color.MAGENTA);
		//g2d.draw(endRect);
		//g2d.draw(hero.getBorder());
		if (badIsDed)
		{
			g2d.drawImage(endOne, (int)bad.getX()-100, (int)bad.getY()-75, null);
		}
		for (Block b:blocks)
		{
			g2d.draw(b.getBorder());
		}
		
		if (activatedPower)
		{
			g2d.drawImage(pu.getImage(), (int)pu.getX(), (int)pu.getY(), null);
			//g2d.draw(pu.getBorder());
			//powerup.setVisible(false);
		}
		else 
		{
			g2d.drawImage(powerup, powerX, 223+bounce, null);
			//g2d.draw(powerRect);
		}
		/*if (right)
		{
			subGuy = guyForward[imgCount];
			g2d.drawImage(subGuy,x,y,null);
		}
		else 
		{
			subGuy = guyBackward[imgCount];
			g2d.drawImage(subGuy,x,y,null);
		}*/
		g2d.drawImage(hero.getImage(), hero.getX(), hero.getY(), null);
		//subBg = bgs[imgCount];
		g2d.drawImage(bad.getImage(),(int)bad.getX(),(int)bad.getY(),null);
		for (Block b: blocks)
			g2d.drawImage(b.getImage(), (int)b.getX()*60, (int)b.getY()*60+150, null);
		//g2d.draw(bad.getBorder());
	
		if (!gameOn && !win)
		{
			g2d.drawImage(endOne, 1000, 200, null);
			g2d.drawImage(endTwo, 500, 800, null);
			g2d.drawImage(endThree, 250, 100, null);
			g2d.drawImage(endFour, 950, 650, null);
		}
		else if (!gameOn && win)
		{
			g2d.drawImage(finOne, 1000, 200, null);
			g2d.drawImage(finTwo, 500, 800, null);
			g2d.drawImage(finThree, 250, 100, null);
		}

	}
	public void keyPressed(KeyEvent key)
	{
		if(key.getKeyCode()==39)//right arrow key
		{
			//right=true;
			hero.setRight(true);
			//x+=10;
			hero.setX(hero.getX()+10);
			/*if (x>=1100)
				x=1100;
			imgCount++;
			if(imgCount>8)
				imgCount=1;*/
			if (hero.getX()>=1100)
				hero.setX(1100);
			hero.setImgCount(hero.getImgCount()+1);
			if (hero.getImgCount()>8)
				hero.setImgCount(1);
				
			backgroundX-=10;
			backgroundXPtTwo-=10;
			backgroundXPtThree-=10;
			
			if (backgroundX<=-1920)
				backgroundX=3840;
			if (backgroundXPtTwo<=-1920)
				backgroundXPtTwo=3840;
			if (backgroundXPtThree<=-1920)
				backgroundXPtThree=3840;
				
			for (Block b: blocks)
			{
				b.setX(b.getX()-0.25);
				if (b.getX()<=-22)
					b.setX(50);
			}
			
			powerX-=4;
			powerBounce = !powerBounce;
			if (powerBounce)
				bounce = -7;
			else bounce=0;
			
			/*if (right)
				actualPowerLoc = x+75;
			else actualPowerLoc = x-5;*/
			powerRect = new Rectangle(powerX+445,560,75,75);
			//heroRect = new Rectangle(x+4,y+30,45,45); 
			hero.getBorder();
			steps+=5;
			endRect = new Rectangle(5385-(int)steps,398,20, 250);
		}
		
		if(key.getKeyCode()==37)//left arrow key
		{
			//right=false;
			hero.setRight(false);
			//x-=10;
			hero.setX(hero.getX()-10);
			/*if (x<=5)
				x=5;
			imgCount++;
			if(imgCount>8)
				imgCount=1;*/
			if (hero.getX()<=5)
				hero.setX(5);
			hero.setImgCount(hero.getImgCount()+1);
			if (hero.getImgCount()>8)
				hero.setImgCount(1);
			powerBounce = !powerBounce;
			if (powerBounce)
				bounce = -7;
			else bounce=0;
			//actualPowerLoc = x-390;
			powerRect = new Rectangle(powerX+445,560,75,75);
			//heroRect = new Rectangle(x+12,y+30,45,45); 
			hero.getBorder();
		}
		if(key.getKeyCode()==38) //up arrow key
		{
			//y-=10;	
			jumping=true;
		}
		if(key.getKeyCode()==82) //r
			restart=true;
		
	}
	public void keyReleased(KeyEvent key)
	{
	}
	public void keyTyped(KeyEvent key)
	{
	}
	public static void main(String args[])
	{
		FinalProject app=new FinalProject();
	}
}