import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, Runnable{

	AudioClip aud;
	Timer timer;
	Car car;
	item item;
	int carSpeed=5,extraSpeed;
	static ArrayList<EnemyCar> enemyCar=new ArrayList<EnemyCar>();
	ArrayList<WhiteBar> whiteBar=new ArrayList<WhiteBar>();
	int whiteBarCount=6;
	int enemyCount;
	Random rand=new Random();
	ArrayList<String> imageAddress=new ArrayList<String>();
	String name;
    Score score;
    int temp;
    ArrayList diem;
	
	public GamePanel(int enemyCount,int extraSpeed, String name){
		this.enemyCount=enemyCount;
		this.extraSpeed=extraSpeed;
		this.name = name;
		
		setBounds(0,0,500,700);
		setBackground(Color.LIGHT_GRAY);
		setFocusable(true);
		
		car=new Car(300,400,carSpeed, name);
		addKeyListener(new KeyAdapt(car));
		
		item = new item(100,60,10);
		
		imageAddress.add("image/car1.png");
		imageAddress.add("image/car2.png");
		imageAddress.add("image/car3.png");
		imageAddress.add("image/car4.png");
		imageAddress.add("image/car7.png");
		
		for (int i=0;i<enemyCount;i++){
			int k=rand.nextInt(5);
			addEnemyCar(new EnemyCar(15+rand.nextInt(435),0,k+extraSpeed,imageAddress.get(k)));
		}
		
		for (int i=0;i<whiteBarCount;i++){
			addWhiteBar(new WhiteBar(i*120));
		}
		
		//Music music = new Music();
	    //Thread audThread = new Thread(music);
	    //audThread.start();
	}
	
	public void paint(Graphics g){
		super.paint(g);
		Graphics2D g2d=(Graphics2D)g;

		for (int i=0;i<whiteBarCount;i++){
			whiteBar.get(i).draw(g2d);
		}
		
		for (int i=0;i<enemyCar.size();i++){
			enemyCar.get(i).draw(g2d);
		}
		
		car.draw(g2d);
		item.draw(g2d);
	}
	
	public void addWhiteBar(WhiteBar wb){
		whiteBar.add(wb);
	}
	
	public void addEnemyCar(EnemyCar e){
		enemyCar.add(e);
	}
	//tra lai danh sanh cac chuong ngai vat
	public static ArrayList<EnemyCar> getEnemyList(){
		return enemyCar;
	}
	
	public static void removeEnemy(EnemyCar e){
		enemyCar.remove(e);
	}
	
	public void replaceEnemy(){
		while(enemyCar.size()<enemyCount){
			int k=rand.nextInt(5);
			addEnemyCar(new EnemyCar(15+rand.nextInt(435),0,k+extraSpeed,imageAddress.get(k)));
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		replaceEnemy();
		for (int i=0;i<whiteBarCount;i++){
			whiteBar.get(i).update();;
		}
		for(int i=0;i<enemyCar.size();i++){
			enemyCar.get(i).update();
		}
		car.update();
		repaint();
		if(ScorePanel.lives==0){
			//System.out.println("game over");
			//System.exit(1);
			
			Interface.gameThread.stop();
			Interface.scoreThread.stop();
			Interface.game.setVisible(false);
			Interface.score.setVisible(false);
			Interface.frame.setSize(300, 300);
			Interface.frame.setTitle("Score");
			score = new Score();
			Interface.frame.add(score);
			
			diem = new ArrayList(3);
			diem.add(ScorePanel.score);
			temp = (int) diem.get(0);
			for(int i=0;i<diem.size();i++)
			{
				if(temp >(int) diem.get(i))
				temp =(int) diem.get(i);				
			}
			score.nLabel.setText(Interface.nameTField.getText());
			score.sLabel.setText(Integer.toString(ScorePanel.score));
			score.hLabel.setText(Integer.toString(temp));
			
		}
		//==============================================
		
		//============================================
	}

	@Override
	public void run() {
		timer=new Timer(10,this);
		timer.start();
		item.update();
		repaint();
		}
		
	}
	
	
