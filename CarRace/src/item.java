import java.awt.*;
import java.util.Random;

import javax.swing.*;
public class item {
	static int x;
	static int y;
	static int radius;
	static int dy = 2;
	item(int x, int y, int radius)
	{
		this.x = x;
		this.y = y;
		this.radius = radius;
	}
	public static void draw(Graphics2D g)
	{
		g.setColor(Color.RED);
		g.fillOval(x, y, 2*radius, 2*radius);	
	}
	public static void update()
	{
		y = y + dy;
		if(y > 768)
		{
		y = 0;
		}
	}
}
