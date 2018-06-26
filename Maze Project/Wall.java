import java.awt.Color;
import java.awt.Polygon;
public class Wall
{
	int[] xArr, yArr;
	Color c;
	public Wall(int []xs, int[]ys, Color c)
	{
		xArr = xs;
		yArr = ys;
		this.c=c;
	}
	public Color getColor()
	{
		return c;
	}
	public Polygon getPoly()
	{
		return new Polygon(yArr,xArr,xArr.length);
	}
}