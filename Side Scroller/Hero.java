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

public class Hero
{
	int x;
	int y=550;
	int imgCount=0;
	BufferedImage img, img2;
	Image subGuy;
	BufferedImage[] imgs=new BufferedImage[9];
	BufferedImage[] imgs2=new BufferedImage[9];
	boolean right = true;
	public Hero()
	{
		int rand = (int)(Math.random()*2)+1;
		if (rand==2)
		{
			try
			{
				img = ImageIO.read(new File("DogeRunningForward.png"));
				img2 = ImageIO.read(new File("DogeRunningBackward.png"));
				for(int x=0;x<9;x++)
				{
					imgs[x]=img.getSubimage(x*79,0,79,102); //find the best rectangle
				}
				for(int x=0;x<9;x++)
				{
					imgs2[x]=img2.getSubimage(x*78,0,78,102); //find the best rectangle
				}
			}catch(Exception e){}
		}
		
		subGuy = imgs[0];
		y=550;
	}
	public int getX(){return x;}
	public void setX(int val){x=val;}
	public int getY(){return y;}
	public void setY(int val){y=val;}
	public Image getImage(){
		if(right)
			subGuy = imgs[imgCount];
		else subGuy = imgs2[imgCount];
		return subGuy;}
	public void setImage(Image g){subGuy = g;}
	public void setRight(boolean b){right = b;}
	public boolean isRight(){return right;}
	public int getImgCount(){return imgCount;}
	public void setImgCount(int b){imgCount = b;}
	public Rectangle getBorder()
	{
		if (right)
			return new Rectangle(x+4,y+30,45,45);
		return new Rectangle(x+12,y+30,45,45);
    }
}