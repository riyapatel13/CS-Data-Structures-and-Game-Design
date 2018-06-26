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


public class Block
{
	double x;
	double y;
	BufferedImage img;
		
	public Block(double x, double y, BufferedImage img)
	{
		this.x = x;
		this.y = y;
		this.img = img;
	}
	
	public double getX(){return x;}
	public void setX(double val){x=val;}
	public double getY(){return y;}
	public void setY(double val){y=val;}
	public BufferedImage getImage(){return img;}
	public void setImage(BufferedImage g){img = g;}
	public Rectangle getBorder()
	{
		return new Rectangle((int)x*60, (int)y*60+150, 60, 60);
    }
	
}