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

public class BadGuy
{
	double x=0;
	double y=0;
	BufferedImage img;
	Image subGuy;
	BufferedImage[] imgs=new BufferedImage[9];
	boolean right;
	int imgCount=0;
	int attackCount=0;
	
	public BadGuy()
	{
		y=527;
		int rand = (int)(Math.random()*2)+1;
		if (rand==2)
		{
			x=-300;
			//x=500;
			try{
				img = ImageIO.read(new File("EnemyRight.png"));
				AffineTransform tx = new AffineTransform();
				tx.scale(2, 2);
				AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
				img = op.filter(img, null);
			}catch(Exception e){}
			right=true;
			for(int x=0;x<9;x++)
			{
				imgs[x]=img.getSubimage(x*64*2,0,64*2,73*2); 
			}
		}
		else 
		{
			x=2100;
			//x=500;
			right = false;
			try{
				img = ImageIO.read(new File("EnemyLeft.png"));
				AffineTransform tx = new AffineTransform();
				tx.scale(2, 2);
				AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
				img = op.filter(img, null);
			}catch(Exception e){}
			for(int x=0;x<9;x++)
			{
				imgs[x]=img.getSubimage(x*64*2,0,64*2,73*2); 
			}
		}
		
		subGuy = imgs[0];
		
	}
	public double getX(){return x;}
	public void setX(double val){x=val;}
	public double getY(){return y;}
	public void setY(double val){y=val;}
	public Image getImage(){return subGuy;}
	public void setImage(Image g){subGuy = g;}
	public int getAttackCount(){return attackCount;}
	public void setAttackCount(int g){attackCount = g;}
	public boolean isRight(){return right;}
	public void move()
	{
		if (right)
			x+=0.5;
		else x-=0.5;
		imgCount++;
		if (imgCount==8)
			imgCount=0;
		subGuy=imgs[imgCount];
	}
	public Rectangle getBorder()
	{
		return new Rectangle((int)x+50, (int)y+30, 30, 60);
    }
}