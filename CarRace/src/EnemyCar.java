import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;


public class EnemyCar {

	int x,y;
	int speed;
	String imageAddress;
	
	public EnemyCar(int x, int y,int speed,String address){
		this.x=x;
		this.y=y;
		this.speed=speed;
		this.imageAddress=address;
		
	}
	
	public void update(){
		y+=speed;
		checkCollision();
	}
	
	public void draw(Graphics2D g2d){
		g2d.drawImage(getEnemyImage(),x,y,null);
		//g2d.draw(getBounds());
	}
	
	public Image getEnemyImage(){
		ImageIcon ic =new ImageIcon(imageAddress);
		return ic.getImage();
	}
	
	public void checkCollision(){
		if (y>700){
			GamePanel.removeEnemy(this);
			ScorePanel.score++;
		}
	}
	
	public Rectangle getBounds(){
		return new Rectangle(x,y,getEnemyImage().getWidth(null),getEnemyImage().getHeight(null));
	}
	
}
