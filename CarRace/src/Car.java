import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;


public class Car {

	int x,y;
	int velX=0,velY=0;
	int speed;
	int count = 0;
	String name;
	
	public Car(int x, int y,int speed, String name){
		this.x=x;
		this.y=y;
		this.speed=speed;
		this.name = name;
	}
	
	public void update(){
		x+=velX;
		y+=velY;
		checkBarCollision();
		checkCollision();
	}
	
	public void draw(Graphics2D g2d){
		g2d.drawImage(getCarImage(),x,y,null);
		
	}
	
	public Image getCarImage(){
		ImageIcon ic =new ImageIcon(name);
		return ic.getImage();
	}
	
	//=======================================================================
	public void checkBarCollision(){
		if (x<15) x=15;
		if (y<0) y=0;
		if (y>622) y=622;
		if (x>450) x=450;
		
	}
	// kiem tra xung dot
	public void checkCollision(){
		// tao ra 1 arraylist voi so chuong ngai vat duoc thiet lap trong lop GamePanel
		ArrayList<EnemyCar> enemies=GamePanel.getEnemyList();// danh sach mang nay gom co 3 phan tu
		// kiem tra xung dot 
		for (int i=0;i<enemies.size();i++){
			// nhan lai cac chuong ngai vat
			EnemyCar tempEnemy=enemies.get(i);
			// neu xay ra xung dot thi xoa chuong ngai vat
			if (getBounds().intersects(enemies.get(i).getBounds())){
				count ++;
				GamePanel.removeEnemy(tempEnemy);// xo chuong ngai vat di
				
				ScorePanel.lives--;// so lan cham chuong ngai vat
				ScorePanel.removeLive(ScorePanel.liveList.get(ScorePanel.lives));
				ScorePanel.updateDie(count);
				ScorePanel.updateBlood(100*count/3);
			}
		
		}
		
	}
	
	//===========================================================================
	
	public void keyPressed(KeyEvent e){
		
		int key=e.getKeyCode();
		
		if(key== KeyEvent.VK_UP){
			velY=-speed;
		} else if (key== KeyEvent.VK_DOWN){
			velY=speed;
		} else if (key== KeyEvent.VK_LEFT){
			velX=-speed;
		} else if (key== KeyEvent.VK_RIGHT){
			velX=speed;
		}
		
	}

	public void keyReleased(KeyEvent e){
		
		int key=e.getKeyCode();
		
		if(key== KeyEvent.VK_UP){
			velY=0;
		} else if (key== KeyEvent.VK_DOWN){
			velY=0;
		} else if (key== KeyEvent.VK_LEFT){
			velX=0;
		} else if (key== KeyEvent.VK_RIGHT){
			velX=0;
		}
		
	}
	
	public Rectangle getBounds(){
		return new Rectangle(x,y,getCarImage().getWidth(null),getCarImage().getHeight(null));
	}
	
}
