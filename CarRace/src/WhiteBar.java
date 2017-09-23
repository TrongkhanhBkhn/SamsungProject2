import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;


public class WhiteBar extends JPanel{
	
	int y;
	final int width=15,height=100;
	int speed=5;
	
	public WhiteBar(int y){
		this.y=y;
	}
	
	public void draw(Graphics2D g2d){
		g2d.setColor(Color.black);
		g2d.fillRect(0, y, width, height);
		g2d.fillRect(485, y, width, height);
	}
	

	
	public void update(){
		if (y<720)
			y+=speed;
		else
			y=0;
		
	}

}
