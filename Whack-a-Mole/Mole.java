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
import java.util.ArrayList;

public class Mole
{
	boolean popUp = false;
	boolean forward = true;
	boolean hit = false;
	int x;
	int y;
	int imgCount = 11;
	int mCount, hitCount = 0;
	BufferedImage guy;

	public Mole()
	{
	}

	public boolean getPop(){return popUp;}
	public void setPop(boolean value){popUp = value;}
	public boolean getForward(){return forward;}
	public void setForward(boolean value){forward = value;}
	public int getX(){return x;}
	public void setX(int val){x=val;}
	public int getY(){return y;}
	public void setY(int val){y=val;}
	public int getImgCount(){return imgCount;}
	public void setImgCount(int val){imgCount=val;}
	public int getCount(){return mCount;}
	public void setCount(int val){mCount=val;}
	public int getHitCount(){return hitCount;}
	public void setHitCount(int val){hitCount=val;}
	public BufferedImage getImage(){return guy;}
	public void setImage(BufferedImage g){guy = g;}
	public void setHit(boolean h){hit = h;}
	public boolean getHit(){return hit;}
	public Rectangle getBorder()
	{
		return new Rectangle(x, y, 44, 23);
    }
}