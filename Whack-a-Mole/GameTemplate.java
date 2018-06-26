// PRESS ENTER TO START THE GAME

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
import sun.audio.*;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;


public class GameTemplate extends JPanel implements KeyListener,Runnable, MouseListener
{
	private float angle;
	private int x;
	private int y;
	private JFrame frame;
	Thread t;
	private boolean gameOn, up, down, left, right;
	BufferedImage guy, origHammer, background, title;
	BufferedImage[] guys=new BufferedImage[12];
	BufferedImage[] hammers=new BufferedImage[5];
	ArrayList<Mole> moles = new ArrayList<Mole>();
	Image subGuy, hammer;
	Rectangle hammerRect;
	boolean restart=false;
	boolean forward = true;
	boolean space = false;
	boolean start = false;
	boolean openScreen = true, mainScreen= false, endScreen = false;
	int imgCount=0, hammerCount=0, count=0, gameTime=0, score=0, notPop=0;
	int mouseX = 90;
	int mouseY = 25;
	AudioPlayer MGP = AudioPlayer.player;
	AudioStream BGM;
	AudioData MD;
	ContinuousAudioDataStream loop = null;
	File soundFile = new File("Rysk_-_Hej-eFCL-TNU1qY.wav");
	Clip clip;

	

	public GameTemplate()
	{
		frame=new JFrame();
		x=100;
		y=100;
		gameOn=true;

		try {

			//mole spritesheet
			guy = ImageIO.read(new File("molesheet.png"));
			for(int x=0;x<12;x++)
			{
				guys[x]=guy.getSubimage(x*44,0,44,47);
			}

			//scaling hammer
			origHammer = ImageIO.read(new File("hammerspritesheet.png"));
			AffineTransform tx = new AffineTransform();
			tx.scale(0.50, 0.50);
			AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
			origHammer = op.filter(origHammer, null);

			//background scaling
			background = ImageIO.read(new File("grass.jpg"));
			tx = new AffineTransform();
			tx.scale(0.80, 0.88);
			op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
			background = op.filter(background, null);

			//hammer spritesheet
			for(int x=0;x<5;x++)
			{
				hammers[x]=origHammer.getSubimage((int)(x*220*0.50),0,(int)(220*0.50),(int)(250*0.50));
			}

			//title 
			title = ImageIO.read(new File("imageedit_3_4084637470.png"));
			tx = new AffineTransform();
			tx.scale(0.30, 0.25);
			op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
			title = op.filter(title, null);
		}
		catch (IOException e) {
			System.out.println("error");
		}
		
		//music
		/*try
		{
			BGM = new AudioStream(new FileInputStream("C:\\Users\\riaup\\Desktop\\Rysk_-_Hej-eFCL-TNU1qY.wav"));
			MD = BGM.getData();
			loop = new ContinuousAudioDataStream(MD);
		}
		catch (IOException e) {
			System.out.println("no music");
		}
		MGP.start(loop);*/
		
		
		
		

		subGuy=guys[0];
		hammer=hammers[0];
		hammerRect = new Rectangle(mouseX+15, mouseY+110, 50,10);

		//initializing mole arraylist
		for (int i=0; i<9; i++)
		{
			Mole m = new Mole();
			m.setImage(guys[11]);
			if(i%3==0)
				m.setX(100);
			else if(i%3==1)
				m.setX(240);
			else if(i%3==2)
				m.setX(380);

			if(i<3)
				m.setY(125);
			else if (i<6)
				m.setY(225);
			else if (i<9)
				m.setY(325);
			//m.setX(guys[11]);
			//m.setY(guys[11]);
			moles.add(m);
		}

		int popNum = (int)(Math.random()*9);
		moles.get(popNum).setPop(true);


		frame.addKeyListener(this);
		addMouseListener(this);
		frame.add(this);
		frame.setSize(570,500);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		t=new Thread(this);
		t.start();
		music();
	}

	public void run()
	{
		while(true)
		{
			
			if(gameOn && start)
			{
					notPop=0;
					//hammer
					if (space)
					{
						if (hammerCount<4)
						{
							hammerCount++;
							hammer = hammers[hammerCount];
						}

						if(hammerCount==4)
						{
							space = false;
						}
					}
					else
					{
						if(hammerCount>0)
						{
							hammerCount--;
							hammer = hammers[hammerCount];
						}
					}

					//moving hammer
					if (left)
					{
						if (mouseX == 90)
							mouseX = 90;
						else mouseX-=140;
						hammerRect = new Rectangle(mouseX+15, mouseY+110, 50,10);
					}
					if (right)
					{
						if (mouseX == 370)
							mouseX = 370;
						else mouseX+=140;
						hammerRect = new Rectangle(mouseX+15, mouseY+110, 50,10);
					}
					if (up)
					{
						if (mouseY == 25)
							mouseY = 25;
						else mouseY-=100;
						hammerRect = new Rectangle(mouseX+15, mouseY+110, 50,10);
					}
					if (down)
					{
						if (mouseY == 225)
							mouseY = 225;
						else mouseY+=100;
						hammerRect = new Rectangle(mouseX+15, mouseY+110, 50,10);
					}

					up = false;
					down = false;
					left = false;
					right = false;

					//moles
					for (Mole mole : moles)
					{
						if (mole.getPop())
						{
							if (mole.getImgCount()==11 && mole.getForward())
							{
								mole.setImgCount(0);
								mole.setImage(guys[mole.getImgCount()]);
							}

							else if (mole.getImgCount()<10 && mole.getForward())
							{
								mole.setImgCount(mole.getImgCount()+1);
								mole.setImage(guys[mole.getImgCount()]);
							}
							else if (mole.getImgCount()==10 && mole.getForward())
							{
								mole.setForward(!mole.getForward());
								mole.setImgCount(mole.getImgCount()-1);
								mole.setImage(guys[mole.getImgCount()]);
							}
							else if (mole.getImgCount()<10 && !mole.getForward())
							{
								if (mole.getImgCount()== 0)
								{
									mole.setPop(false);
									mole.setImgCount(11);
									mole.setImage(guys[mole.getImgCount()]);
									mole.setForward(true);

									int popNum = (int)(Math.random()*moles.size());
									while (popNum>=moles.size())
							 			popNum = (int)(Math.random()*moles.size());
									moles.get(popNum).setPop(true);

									continue;
								}
								mole.setImgCount(mole.getImgCount()-1);
								mole.setImage(guys[mole.getImgCount()]);
							}
						}
					}

				//whack a mole part
				for (int i=0; i<moles.size(); i++)
				{
					if (moles.get(i).getPop() && hammerCount==4 && hammerRect.intersects(moles.get(i).getBorder()))
					{
						//moles.remove(i);
						moles.get(i).setHit(true);
						//System.out.println(moles.get(i).getHitCount());
						moles.get(i).setHitCount(moles.get(i).getHitCount()+1);
						//System.out.println(moles.get(i).getHitCount());
						int popNum = (int)(Math.random()*moles.size());
						while (popNum>=moles.size())
							 popNum = (int)(Math.random()*moles.size());
						moles.get(popNum).setPop(true);
						score++;
					}

					if (moles.get(i).getHit())
					{
						moles.get(i).setHitCount(moles.get(i).getHitCount()+1);
						//System.out.println(moles.get(i).getHitCount());
					}
					if (moles.get(i).getHitCount()>100)
					{
						Mole m = new Mole();
						m.setImage(guys[11]);
						m.setX(moles.get(i).getX());
						m.setY(moles.get(i).getY());
						moles.remove(i);
						moles.add(m);
					}
				}

				for (Mole mole:moles)
				{
					if (!mole.getPop())
						notPop++;
				}
				if (notPop==9)
				{
					int popNum = (int)(Math.random()*moles.size());
					moles.get(popNum).setPop(true);
				}
				gameTime+=60;
			}
			//gameTime+=60;

			//ending game
			if (gameTime>60000)
			{
				gameOn = false;
			}



			if(restart)
			{
				//resetting all the variables
				restart=false;
				mouseX=90;
				mouseY=25;
				score=0;
				gameTime=0;
				moles = new ArrayList<Mole>();

				//initializing mole arraylist
				for (int i=0; i<9; i++)
				{
					Mole m = new Mole();
					m.setImage(guys[11]);
					if(i%3==0)
						m.setX(100);
					else if(i%3==1)
						m.setX(240);
					else if(i%3==2)
						m.setX(380);

					if(i<3)
						m.setY(125);
					else if (i<6)
						m.setY(225);
					else if (i<9)
						m.setY(325);
					moles.add(m);
				}
				//setting a mole to pop
				int popNum = (int)(Math.random()*9);
				moles.get(popNum).setPop(true);
				gameOn=true;
			}

			repaint();
			try
			{
				t.sleep(60);
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
		//g2d.setColor(Color.GREEN);
		//g2d.fillRect(50,50,450,400);
		g2d.drawImage(background, 60, 0, null);
		g2d.setColor(Color.WHITE);
		g2d.setFont(new Font("Dialog", Font.PLAIN, 18));

		if (!start)
		{
			g2d.drawImage(title, 125, 7, null);
			g2d.drawString("Try to hit as many moles as you can in 1 minute.", 72, 250);
			g2d.drawString("Use the arrow keys to move the hammer and", 85, 280);
			g2d.drawString("click the mouse to hit the mole. Good luck!", 92, 310);
		}
		else if (gameOn)
		{
			g2d.drawString("Score: "+score, 65, 20);
			g2d.drawString("Time Remaining: "+(60-gameTime/1000), 305, 20);
			//g2d.drawRect(mouseX+15, mouseY+110, 50,10);
			//g2d.draw(hammerRect);
			for (Mole mole:moles)
			{
				if (!mole.getHit())
				g2d.drawImage(mole.getImage(),mole.getX(),mole.getY(),null);
				//g2d.draw(mole.getBorder());
			}
			g2d.drawImage(hammer,mouseX,mouseY,null);
			//System.out.println(mouseX+", "+mouseY);
		}
		else
		{
			g2d.setFont(new Font("Dialog", Font.PLAIN, 24));
			//g2d.setColor(Color.WHITE);
			g2d.drawString("GAME OVER!", 183, 200);
			g2d.drawString("Score: "+score, 218, 230);
			g2d.drawString("Press R to restart", 173, 260);
		}

	}
	public void keyPressed(KeyEvent key)
	{
		//hammerMovement
		if(key.getKeyCode()==37)
		{
			//mouseX-=20;
			//hammerRect = new Rectangle(mouseX+15, mouseY+110, 50,10);
			left=true;
		}
		if(key.getKeyCode()==38)
		{
			//mouseY-=20;
			//hammerRect = new Rectangle(mouseX+15, mouseY+110, 50,10);
			up=true;
		}
		if(key.getKeyCode()==39)
		{
			//mouseX+=20;
			//hammerRect = new Rectangle(mouseX+15, mouseY+110, 50,10);
			right=true;
		}
		if(key.getKeyCode()==40)
		{
			//mouseY+=20;
			//hammerRect = new Rectangle(mouseX+15, mouseY+110, 50,10);
			down=true;
		}
		if(key.getKeyCode()==82)
			restart=true;
		if(key.getKeyCode()==KeyEvent.VK_ENTER)
			start=true;
	}
	public void keyReleased(KeyEvent key)
	{
	}
	public void keyTyped(KeyEvent key)
	{
	}
	public void mouseMoved(MouseEvent e)
	{
	}
	public void mousePressed(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseClicked(MouseEvent e) {
		//mouseX = e.getX();
		//mouseY = e.getY();
		space = true;
		//System.out.println(mouseX + ", "+ mouseY);
		repaint();
	}
	
	public void music()
	{

				
						try {
				
							AudioInputStream ais = AudioSystem.getAudioInputStream(soundFile);
				
							AudioFormat format = ais.getFormat();
				
							DataLine.Info info = new DataLine.Info(Clip.class,format);
				
							clip = (Clip) AudioSystem.getLine(info);
				
							clip.open(ais);
				
							clip.start();
				
			clip.loop(Clip.LOOP_CONTINUOUSLY);
			}catch(Exception e){}
	}

	public static void main(String args[])
	{
		GameTemplate app=new GameTemplate();
	}
	
}