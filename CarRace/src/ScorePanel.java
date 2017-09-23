import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.Timer;
import javax.swing.plaf.ProgressBarUI;


public class ScorePanel extends JPanel implements ActionListener,Runnable{
	
	int level;
	static ArrayList<Live> liveList=new ArrayList<Live>();
	int liveCount=3;
	Timer timer;
	static JLabel  amountLabel;
	static JProgressBar blood;
	static JLabel bloodLabel;
	static int score;
	static int lives=3;
	static String name;
	
	public ScorePanel(String name){
		setBounds(500,0,300,700);
		setBackground(Color.MAGENTA);
		setFocusable(true);
		setLayout(null);
		this.name = name;
		
		JLabel scoreLabel=new JLabel("SCORE");
		scoreLabel.setBounds(80, 50, 200, 50);
		scoreLabel.setFont(new Font("Serif",Font.PLAIN,50));
		add(scoreLabel);
		
		JLabel dieLabel = new JLabel("DIE");
		dieLabel.setBounds(100, 450, 100, 50);
		dieLabel.setFont(new Font("die", Font.PLAIN, 50));
		add(dieLabel);
		
		amountLabel = new JLabel("0");
		amountLabel.setBounds(130, 520, 100, 50);
		amountLabel.setFont(new Font("die", Font.PLAIN, 50));
		add(amountLabel);
		
		bloodLabel = new JLabel(name);
		bloodLabel.setBounds(0, 185, 80, 50);
		bloodLabel.setFont(new Font("blood", Font.PLAIN, 20));
		add(bloodLabel);
		
		blood = new JProgressBar();
		blood.setBounds(100, 200, 200, 20);
		blood.setBackground(Color.RED);
		blood.setValue(0);
		
		add(blood);		
		// hien thi ra so mang nguoi choi
		//for (int i=0;i<liveCount;i++){
			liveList.add(new Live(50,350, "image/blue_car.gif"));
			liveList.add(new Live(50+80,350, "image/red_car.gif"));
			liveList.add(new Live(50+160,350, "image/green_car.gif"));
		//}
		
		
	}
	
	public void paint(Graphics g){
		super.paint(g);
		Graphics2D g2d=(Graphics2D)g;
		for (int i=0;i<liveList.size();i++){
			liveList.get(i).draw(g2d);
		}
		g2d.setFont(new Font("Serif",Font.PLAIN,50));
		g2d.drawString(Integer.toString(score), 130, 150);
	
	}
	// xoa so mang nguoi choi
	public static void removeLive(Live l){
		liveList.remove(l);
	}
	public static void updateDie(int die)
	{
		 amountLabel.setText(Integer.toString(die));
	}
	public static void updateBlood(int bl)
	{
		blood.setValue(bl);
		
	}

	@Override
	public void run() {
		timer=new Timer(10,this);
		timer.start();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		repaint();
	}
	

}
