import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.lang.*;
import java.math.*;
import java.awt.image.*;
import java.applet.*;
import javax.swing.border.*;
import javax.imageio.ImageIO;

public class PowerUp
{
	double x;
	double y;
	BufferedImage img;

	public PowerUp(double heroX, double heroY)
	{
		x = heroX+75;
		y = heroY+30;
		try
		{
			img=ImageIO.read(new File("laser.png"));
		}catch(Exception e){}
	}

	public double getX(){return x;}
	public void setX(double val){x=val;}
	public double getY(){return y;}
	public void setY(double val){y=val;}
	public BufferedImage getImage(){return img;}
	public void setImage(BufferedImage g){img = g;}
	public Rectangle getBorder()
	{
		return new Rectangle((int)x, (int)y, 390, 50);
    }
}