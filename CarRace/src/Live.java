import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;


public class Live {
	
	int x,y;
	String img;
	
	public Live(int x, int y, String img){
		this.x=x;
		this.y=y;	
		this.img = img;
	}
	public void draw(Graphics2D g2d){
		g2d.drawImage(getLiveImage(),x,y,null);
	}
	
	public Image getLiveImage(){
		ImageIcon ic =new ImageIcon(img);
		return ic.getImage();
	}

}
