import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Score extends JPanel{
	JLabel name, score, hight;
	static JLabel nLabel,sLabel, hLabel;
	JButton back, exit;
	public Score() {
		this.setLayout(null);
		name = new JLabel("Name");
		this.add(name);
		name.setBounds(20, 20, 80, 40);
		
		nLabel = new JLabel();
		this.add(nLabel);
		nLabel.setBounds(100, 20, 80, 40);
		
		score = new JLabel("Score");
		this.add(score);
		score.setBounds(20, 100, 80, 40);
		this.setSize(300, 300);
		
		sLabel = new JLabel();
		this.add(sLabel);
		sLabel.setBounds(100, 100, 80, 40);
		
		hight = new JLabel("HighScore");
		this.add(hight);
		hight.setBounds(20, 180, 80, 40);
		this.setSize(300, 300);
		
		hLabel = new JLabel();
		this.add(hLabel);
		hLabel.setBounds(100, 180, 80, 40);
	}

}
