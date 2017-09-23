import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.applet.AudioClip;
import javax.swing.*;
import java.applet.*;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.xml.ws.handler.MessageContext;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class Interface extends JFrame implements ActionListener, Runnable,
		 MouseListener, MouseMotionListener {
	JLabel startLabel, selectLabel, exitLabel, helpLabel, aboutLabel;
	JLabel bsLabel, baLabel, bhLabel;
	JButton exitButton;
	JButton helpButton;
	JButton aboutButton;
	JButton selectButton;
	JLabel startIcon;
	JFrame iFrame, selectFrame;
	static JFrame frame;
	JFrame player;
	JPanel panel;
	JLabel label, lRace;
	URL url;
	public static AudioClip aud1 = null;
	JLabel aLabel, hLabel, pLabel, eLabel, sLabel;
	ImageIcon race, stIcon, icar1, i1;
	JLabel c1Label, c2Label, c3Label, c4Label, c5Label, c6Label, c7Label;
	JLabel cs1Label, cs2Label, cs3Label, cs4Label, cs5Label, cs6Label, cs7Label;
	JLabel car1, car2, car3, car4, car5, car6, car7;
	static JLabel avataLabel, nameLabel, ageLabel;
	JPanel p;
	JButton browseButton, pButton, cButton;
	static JTextField nameTField, ageTField;
	JPanel avatar;
	JFileChooser fc;
	String imgAdd, name = null, age = null;
	File file;
	static Thread gameThread, scoreThread;
	int count = 0;
	static GamePanel game;
	static ScorePanel score;

	public Interface(String str) {
		super(str);
		
		/************** Giao dien chinh *******/
		ImageIcon img = new ImageIcon(
				"Images/anh2.jpg");
		panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(0, 0, 1366, 768);
		this.add(panel);
		label = new JLabel(img);
		label.setBounds(0, 0, 1366, 768);
		label.addMouseMotionListener(this);
		label.addMouseListener(this);
		panel.add(label);

		ImageIcon race = new ImageIcon(
				"Images/Race.png");
		lRace = new JLabel(race);
		lRace.setBounds(0, 0, 512, 512);
		lRace.addMouseMotionListener(this);
		label.add(lRace);

		/***** Button Control ********/
		ImageIcon stIcon = new ImageIcon(
				"Images/start.png");
		startIcon = new JLabel(stIcon);
		startIcon.setBounds(80, 500, 128, 128);
		label.add(startIcon);
		startIcon.addMouseListener(this);

		startLabel = new JLabel("Play");
		startLabel.setFont(new Font("start", Font.HANGING_BASELINE, 30));
		startLabel.setBounds(110, 630, 80, 30);
		startLabel.setForeground(Color.yellow);
		label.add(startLabel);

		ImageIcon sIcon = new ImageIcon(
				"Images/volang.png");
		sLabel = new JLabel(sIcon);
		sLabel.setBounds(248, 500, 128, 128);
		startIcon.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent e) {
				if(count !=0)
				player = new JFrame("Player");
				player.setVisible(true);
				player.setSize(300, 350);
				p = new JPanel();
				p.setBounds(0, 0, 300, 350);
				p.setLayout(null);
				player.add(p);
				
				
				avataLabel = new JLabel();
				avataLabel.setBounds(50,30, 100, 100);
				avataLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				p.add(avataLabel);
				
				browseButton = new JButton("Brownse");
				browseButton.setBounds(160, 60, 80, 30);
				browseButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				p.add(browseButton);
				
				nameLabel = new JLabel("Name");
				nameLabel.setBounds(10, 150, 80, 30);
				p.add(nameLabel);
				nameTField = new JTextField();
				nameTField.setBounds(90, 150, 150, 30);
				p.add(nameTField);
				
				ageLabel = new JLabel("Age");
				ageLabel.setBounds(10, 200, 80, 30);
				p.add(ageLabel);
				ageTField = new JTextField();
				ageTField.setBounds(90, 200, 150, 30);
				p.add(ageTField);
				
				pButton = new JButton("Start");
				pButton.setBounds(10, 250, 100, 40);
				p.add(pButton);
				
				cButton = new JButton("Cancel");
				cButton.setBounds(150, 250, 100, 40);
				p.add(cButton);
				
				cButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						player.setVisible(false);
					}
				});
				
				
				
				fc = new JFileChooser();
				browseButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						if(fc.showOpenDialog(player) == JFileChooser.APPROVE_OPTION)
						{
							file = fc.getSelectedFile();
							String path = file.getAbsolutePath();
							System.out.print(path);
							int i = path.lastIndexOf('.');
							String subPath = null;
							if(i > 0)
							{
						    	subPath = path.substring(i+1);
							}
							if(!((subPath.equals("jpeg"))||(subPath.equals("jpg"))||(subPath.equals("png"))||(subPath.equals("bmp"))||(subPath.equals("gif"))))
							{
								box.errorBox("File format not supported");
							}
							ImageIcon img = new ImageIcon(path);
							avataLabel.setIcon(img);
							
						}
						
						
					}
				});
				pButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						name = nameTField.getText();
						age  = ageTField.getText();
						
						if((name.length() == 0)||(age.length() == 0))
						{
							box.warnBox("Name or Age error");
						}
						else{
					    
						
						switch(count)
						{
						case 1:
						{
							player.setVisible(false);
							frame=new JFrame("Racing Game");
							frame.setSize(830, 750);
							frame.setResizable(false);
							frame.setVisible(true);
							frame.setLayout(null);
							 game=new GamePanel(5,1,"image/car7.png");
							 score=new ScorePanel(name);
							
							frame.add(game);
							frame.add(score);
							
							gameThread=new Thread(game);
							gameThread.start();
							scoreThread=new Thread(score);
							scoreThread.start();
							
						}break;
						case 2:
						{
							player.setVisible(false);
							frame=new JFrame("Racing Game");
							frame.setSize(830, 750);
							frame.setResizable(false);
							frame.setVisible(true);
							frame.setLayout(null);
							game=new GamePanel(5,1,"image/car1.png");
							 score=new ScorePanel(name);
							
							frame.add(game);
							frame.add(score);
							
							gameThread=new Thread(game);
							gameThread.start();
							scoreThread=new Thread(score);
							scoreThread.start();
							
						}break;
						case 3:
						{
							player.setVisible(false);
							frame=new JFrame("Racing Game");
							frame.setSize(830, 750);
							frame.setResizable(false);
							frame.setVisible(true);
							frame.setLayout(null);
							 game=new GamePanel(5,1,"image/car2.png");
							score=new ScorePanel(name);
							
							frame.add(game);
							frame.add(score);
							
							gameThread=new Thread(game);
							gameThread.start();
							scoreThread=new Thread(score);
							scoreThread.start();
							
						}break;
						case 4:
						{
							player.setVisible(false);
							frame=new JFrame("Racing Game");
							frame.setSize(830, 750);
							frame.setResizable(false);
							frame.setVisible(true);
							frame.setLayout(null);
							 game=new GamePanel(5,1,"image/car3.png");
							 score=new ScorePanel(name);
							
							frame.add(game);
							frame.add(score);
							
							gameThread=new Thread(game);
							gameThread.start();
							scoreThread=new Thread(score);
							scoreThread.start();
							
						}break;
						case 5:
						{
							player.setVisible(false);
							frame=new JFrame("Racing Game");
							frame.setSize(830, 750);
							frame.setResizable(false);
							frame.setVisible(true);
							frame.setLayout(null);
							game=new GamePanel(5,1,"image/car4.png");
							 score=new ScorePanel(name);
							
							frame.add(game);
							frame.add(score);
							
							gameThread=new Thread(game);
							gameThread.start();
							scoreThread=new Thread(score);
							scoreThread.start();
							
						}break;
						case 6:
						{
							player.setVisible(false);
							frame=new JFrame("Racing Game");
							frame.setSize(830, 750);
							frame.setResizable(false);
							frame.setVisible(true);
							frame.setLayout(null);
							game=new GamePanel(5,1,"image/car5.png");
							 score=new ScorePanel(name);
							
							frame.add(game);
							frame.add(score);
							
							gameThread=new Thread(game);
							gameThread.start();
							scoreThread=new Thread(score);
							scoreThread.start();
							
						}break;
						case 7:
						{
							player.setVisible(false);
							frame=new JFrame("Racing Game");
							frame.setSize(830, 750);
							frame.setResizable(false);
							frame.setVisible(true);
							frame.setLayout(null);
							game=new GamePanel(5,1,"image/car6.png");
							score=new ScorePanel(name);
							
							frame.add(game);
							frame.add(score);
							
							gameThread=new Thread(game);
							gameThread.start();
							scoreThread=new Thread(score);
							scoreThread.start();
						}break;
						default: break;
						}
					}
						
					}
				});
							
			}

			@Override
			public void mousePressed(MouseEvent e) {
				
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				
				
			}
			
		});
		sLabel.addMouseListener(this);
		label.add(sLabel);

		selectLabel = new JLabel("Select");
		selectLabel.setFont(new Font("select", Font.HANGING_BASELINE, 30));
		selectLabel.setBounds(268, 630, 100, 30);
		selectLabel.setForeground(Color.yellow);
		label.add(selectLabel);

		ImageIcon hIcon = new ImageIcon(
				"Images/Help.png");
		hLabel = new JLabel(hIcon);
		hLabel.setBounds(418, 500, 128, 128);
		hLabel.addMouseListener(this);
		label.add(hLabel);

		helpLabel = new JLabel("Help");
		helpLabel.setFont(new Font("help", Font.HANGING_BASELINE, 30));
		helpLabel.setBounds(438, 630, 80, 30);
		helpLabel.setForeground(Color.yellow);
		label.add(helpLabel);

		ImageIcon aIcon = new ImageIcon(
				"Images/about.png");
		aLabel = new JLabel(aIcon);
		aLabel.setBounds(586, 500, 128, 128);
		label.add(aLabel);
		aLabel.addMouseListener(this);

		aboutLabel = new JLabel("About");
		aboutLabel.setFont(new Font("about", Font.HANGING_BASELINE, 30));
		aboutLabel.setForeground(Color.yellow);
		aboutLabel.setBounds(610, 630, 90, 30);
		label.add(aboutLabel);

		ImageIcon eIcon = new ImageIcon(
				"Images/exit.png");
		eLabel = new JLabel(eIcon);
		eLabel.setBounds(754, 500, 128, 128);
		eLabel.addMouseListener(this);
		label.add(eLabel);

		exitLabel = new JLabel("Exit");
		exitLabel.setFont(new Font("exit", Font.HANGING_BASELINE, 30));
		exitLabel.setBounds(774, 630, 80, 30);
		exitLabel.setForeground(Color.yellow);
		label.add(exitLabel);
		/***********************audioclip**************************/

		this.setSize(1366, 768);
		this.setVisible(true);
	}

	private URL Get_Location(String filename) {
		URL url = null;
		try {
			url = this.getClass().getResource(filename);
		} catch (Exception e) {
		}
		return url;
	}

	@Override
	public void run() {
		/********* audio clip ***************/
	}

	
	@Override
	public void mouseClicked(MouseEvent e) {
		Object obj = e.getSource();
		if (obj == car1) {
			selectFrame.setVisible(false);
			this.setVisible(true);
			this.repaint();
			count =1;
			
		}
		if (obj == car2) {
			selectFrame.setVisible(false);
			this.setVisible(true);
			this.repaint();
			count =2;
		}
		if (obj == car3) {
			selectFrame.setVisible(false);
			this.setVisible(true);
			this.repaint();
			count =3;
		}
		if (obj == car4) {
			selectFrame.setVisible(false);
			this.setVisible(true);
			this.repaint();
			count =4;
		}
		if (obj == car5) {
			selectFrame.setVisible(false);
			this.setVisible(true);
			this.repaint();
			count =4;
		}
		if (obj == car6) {
			selectFrame.setVisible(false);
			this.setVisible(true);
			this.repaint();
			count =6;
		}
		if (obj == car7) {
			selectFrame.setVisible(false);
			this.setVisible(true);
			this.repaint();
			count =7;
		}
		if (obj == aLabel) {
			this.setVisible(false);
			final JFrame helpFrame = new JFrame("Help");
			JPanel aPanel = new JPanel();
			aPanel.setLayout(null);
			aPanel.setBounds(0, 0, 500, 768);
			helpFrame.add(aPanel);
			// text
			JLabel textLabel = new JLabel();
			textLabel.setBounds(0, 0, 500, 768);
			aPanel.add(textLabel);
			textLabel.setFont(new Font("back", Font.ROMAN_BASELINE, 20));
			String sText1 = "<html>&nbsp;&nbsp;&nbsp;Car Racing Pro is developed"
					+ " by<br><pre>1.&nbsp;Chu Trong Khanh    DT5-K55 20101700"
					+ "<br>2.&nbsp;Cao Duc Toan       DT5-K55 20102328"
					+ "<br>3.&nbsp;Truong Trung Thanh DT5-K55 20102112<pre/>"
					+ "<br><br><center>We are the students of Hanoi<br>"
					+ " University of Science And Technology<br>"
					+ "School of Electronics and Telecommunications.<br>"
					+ "Four year First semester.";
			textLabel.setText(sText1);
			// add anh
			JPanel panel2 = new JPanel();
			panel2.setLayout(null);
			panel2.setBounds(500, 0, 800, 768);
			helpFrame.add(panel2);
			ImageIcon img1 = new ImageIcon(
					"Images/BachKhoa.jpg");
			JLabel imgLabel = new JLabel(img1);
			imgLabel.setBounds(500, 0, 800, 768);
			imgLabel.setVisible(true);
			panel2.add(imgLabel);

			helpFrame.setSize(1366, 768);
			helpFrame.setVisible(true);

			ImageIcon bIcon = new ImageIcon(
					"Images/back.png");
			baLabel = new JLabel(bIcon);
			baLabel.setFont(new Font("left", Font.HANGING_BASELINE, 30));
			baLabel.setBounds(0, 0, 64, 64);
			baLabel.setForeground(Color.RED);
			aPanel.add(baLabel);
			baLabel.addMouseListener(new MouseListener(){

				@Override
				public void mouseClicked(MouseEvent e) {
					setVisible(true);
					helpFrame.setVisible(false);
					
				}
				@Override
				public void mousePressed(MouseEvent e) {					
				}
				@Override
				public void mouseReleased(MouseEvent e) {				
				}
				@Override
				public void mouseEntered(MouseEvent e) {
				}
				@Override
				public void mouseExited(MouseEvent e) {	
				}
				
			});
			

		}
		if (obj == hLabel) {

			this.setVisible(false);
			final JFrame helpFrame = new JFrame("Help");
			JPanel hPanel = new JPanel();
			hPanel.setLayout(null);
			hPanel.setBounds(0, 0, 1366, 768);
			helpFrame.add(hPanel);
			// text
			JLabel textLabel = new JLabel();
			textLabel.setBounds(0, 0, 500, 768);
			hPanel.add(textLabel);
			textLabel.setFont(new Font("back", Font.ROMAN_BASELINE, 20));
			String sTextHelp1 = "<html>&nbsp;&nbsp;HI!. Wellcome to Car Racing."
					+ "&nbsp;The objctive of the game is to avoid other cars and survive as much as possible."
					+ "<br/><br<br>&nbsp;&nbsp;The controls are<br/>"
					+ "<pre>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Up - car move upward"
					+ "<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Down - car move downward"
					+ "<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Right - car move right"
					+ "<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Left - car move left</pre>"
					+ "<br/>In the main paly mood their are some buttons"
					+ " to help you instantly in the time of playing.."
					+ "<br><br><br><br><center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;All the best<center/>"
					+ "&nbsp; Contact : Chu Trong Khanh, Cao Duc Toan, Truong Trung Thanh";
			textLabel.setText(sTextHelp1);
			helpFrame.repaint();
			ImageIcon bIcon = new ImageIcon(
					"Images/back.png");
			bhLabel = new JLabel(bIcon);
			bhLabel.setFont(new Font("left", Font.HANGING_BASELINE, 30));
			bhLabel.setBounds(0, 0, 64, 64);
			bhLabel.setForeground(Color.RED);
			hPanel.add(bhLabel);
			bhLabel.addMouseListener(new MouseListener(){

				@Override
				public void mouseClicked(MouseEvent e) {
					setVisible(true);
					helpFrame.setVisible(false);
					
				}
				@Override
				public void mousePressed(MouseEvent e) {					
				}
				@Override
				public void mouseReleased(MouseEvent e) {				
				}
				@Override
				public void mouseEntered(MouseEvent e) {
				}
				@Override
				public void mouseExited(MouseEvent e) {	
				}
				
			});
			
			helpFrame.setSize(1366, 768);
			helpFrame.setVisible(true);
		}
		if (obj == eLabel) {
			this.setVisible(false);
			System.exit(0);
		}
		if (obj == sLabel) {
			this.setVisible(false);
			selectFrame = new JFrame("Select");
			JPanel sPanel = new JPanel();
			sPanel.setLayout(null);
			sPanel.setBounds(0, 0, 1366, 768);
			selectFrame.add(sPanel);
			/******** nen = **************/
			ImageIcon anen = new ImageIcon(
					"Images/ubuntu.jpg");
			final JLabel nen = new JLabel(anen);
			nen.setBounds(0, 0, 1366, 768);
			sPanel.add(nen);
			ImageIcon bIcon = new ImageIcon(
					"/Images/back.png");
			bsLabel = new JLabel(bIcon);
			bsLabel.setFont(new Font("left", Font.HANGING_BASELINE, 30));
			bsLabel.setBounds(0, 0, 64, 64);
			bsLabel.setForeground(Color.RED);
			nen.add(bsLabel);
			bsLabel.addMouseListener(new MouseListener(){

				@Override
				public void mouseClicked(MouseEvent e) {
					setVisible(true);
					selectFrame.setVisible(false);
					
				}
				@Override
				public void mousePressed(MouseEvent e) {					
				}
				@Override
				public void mouseReleased(MouseEvent e) {				
				}
				@Override
				public void mouseEntered(MouseEvent e) {
				}
				@Override
				public void mouseExited(MouseEvent e) {	
				}
				
			});
			
			/****************************************** add Car ************************************/

			ImageIcon iCar1 = new ImageIcon(
					"sCar/audi TT.png");
			ImageIcon iCar2 = new ImageIcon(
					"sCar/icon2.png");
			ImageIcon iCar3 = new ImageIcon(
					"sCar/cheryler.png");
			ImageIcon iCar4 = new ImageIcon(
					"sCar/Lamborghini.png.png");
			ImageIcon iCar5 = new ImageIcon(
					"sCar/bungati.png");
			ImageIcon iCar6 = new ImageIcon(
					"sCar/Porche.png");
			ImageIcon iCar7 = new ImageIcon(
					"sCar/icon4.png");

			ImageIcon icar1 = new ImageIcon(
					"sCar/i1.png");
			ImageIcon icar2 = new ImageIcon(
					"sCar/i2.png");
			ImageIcon icar3 = new ImageIcon(
					"sCar/i3.png");
			ImageIcon icar4 = new ImageIcon(
					"sCar/i4.png");
			ImageIcon icar5 = new ImageIcon(
					"sCar/i5.png");
			ImageIcon icar6 = new ImageIcon(
					"sCar/i6.png");
			ImageIcon icar7 = new ImageIcon(
					"sCar/i7.png");

			c1Label = new JLabel(icar1);
			c2Label = new JLabel(icar2);
			c3Label = new JLabel(icar3);
			c4Label = new JLabel(icar4);
			c5Label = new JLabel(icar5);
			c6Label = new JLabel(icar6);
			c7Label = new JLabel(icar7);
			car1 = new JLabel(iCar1);
			car1.setBounds(20, 500, 128, 128);
			nen.add(car1);
			car1.addMouseMotionListener(new MouseMotionListener() {
				public void mouseDragged(MouseEvent e) {
				}

				public void mouseMoved(MouseEvent e) {
					c1Label.setBounds(500, 200, 256, 256);
					c1Label.setVisible(true);
					c2Label.setVisible(false);
					c3Label.setVisible(false);
					c4Label.setVisible(false);
					c5Label.setVisible(false);
					c6Label.setVisible(false);
					c7Label.setVisible(false);
					nen.add(c1Label);
					nen.repaint();
				}
			});
			cs1Label = new JLabel("Tux");
			cs1Label.setFont(new Font("c1", Font.HANGING_BASELINE, 30));
			cs1Label.setBounds(30, 620, 80, 30);
			cs1Label.setForeground(Color.red);
			nen.add(cs1Label);

			car2 = new JLabel(iCar2);
			car2.setBounds(168, 500, 128, 128);
			nen.add(car2);
			car2.addMouseListener(this);
			car2.addMouseMotionListener(new MouseMotionListener() {
				public void mouseDragged(MouseEvent e) {
				}

				public void mouseMoved(MouseEvent e) {
					c2Label.setBounds(500, 200, 256, 256);
					c2Label.setVisible(true);
					c1Label.setVisible(false);
					c3Label.setVisible(false);
					c4Label.setVisible(false);
					c5Label.setVisible(false);
					c6Label.setVisible(false);
					c7Label.setVisible(false);
					nen.add(c2Label);
					nen.repaint();
				}
			});
			car2.addMouseListener(this);
			cs2Label = new JLabel("Hexley");
			cs2Label.setFont(new Font("c2", Font.HANGING_BASELINE, 30));
			cs2Label.setBounds(158, 620, 110, 30);
			cs2Label.setForeground(Color.red);
			nen.add(cs2Label);

			car3 = new JLabel(iCar3);
			car3.setBounds(306, 500, 128, 128);
			nen.add(car3);
			car3.addMouseListener(this);
			car3.addMouseMotionListener(new MouseMotionListener() {
				public void mouseDragged(MouseEvent e) {
				}

				public void mouseMoved(MouseEvent e) {
					c3Label.setBounds(500, 200, 256, 256);
					c3Label.setVisible(true);
					c1Label.setVisible(false);
					c2Label.setVisible(false);
					c4Label.setVisible(false);
					c5Label.setVisible(false);
					c6Label.setVisible(false);
					c7Label.setVisible(false);
					nen.add(c3Label);
					nen.repaint();
				}
			});

			 cs3Label = new JLabel("Pidgin");
			cs3Label.setFont(new Font("c3", Font.HANGING_BASELINE, 30));
			cs3Label.setBounds(306, 620, 110, 30);
			cs3Label.setForeground(Color.red);
			nen.add(cs3Label);

			car4 = new JLabel(iCar4);
			car4.setBounds(444, 500, 128, 128);
			nen.add(car4);
			car4.addMouseListener(this);
			car4.addMouseMotionListener(new MouseMotionListener() {
				public void mouseDragged(MouseEvent e) {
				}

				public void mouseMoved(MouseEvent e) {
					c4Label.setBounds(500, 200, 256, 256);
					c4Label.setVisible(true);
					c1Label.setVisible(false);
					c2Label.setVisible(false);
					c3Label.setVisible(false);
					c5Label.setVisible(false);
					c6Label.setVisible(false);
					c7Label.setVisible(false);
					nen.add(c4Label);
					nen.repaint();
				}
			});

			cs4Label = new JLabel("Lyon");
			cs4Label.setFont(new Font("c4", Font.HANGING_BASELINE, 30));
			cs4Label.setBounds(464, 620, 100, 30);
			cs4Label.setForeground(Color.RED);
			nen.add(cs4Label);

			car5 = new JLabel(iCar5);
			car5.setBounds(582, 500, 128, 128);
			nen.add(car5);
			car5.addMouseListener(this);
			car5.addMouseMotionListener(new MouseMotionListener() {
				public void mouseDragged(MouseEvent e) {
				}

				public void mouseMoved(MouseEvent e) {
					c5Label.setBounds(500, 200, 256, 256);
					c5Label.setVisible(true);
					c1Label.setVisible(false);
					c2Label.setVisible(false);
					c4Label.setVisible(false);
					c3Label.setVisible(false);
					c6Label.setVisible(false);
					c7Label.setVisible(false);
					nen.add(c5Label);
					nen.repaint();
				}
			});

			cs5Label = new JLabel("Tiger");
			cs5Label.setFont(new Font("c5", Font.HANGING_BASELINE, 30));
			cs5Label.setBounds(592, 620, 90, 30);
			cs5Label.setForeground(Color.red);
			nen.add(cs5Label);

			car6 = new JLabel(iCar6);
			car6.setBounds(720, 500, 128, 128);
			nen.add(car6);
			car6.addMouseListener(this);
			car6.addMouseMotionListener(new MouseMotionListener() {
				public void mouseDragged(MouseEvent e) {
				}

				public void mouseMoved(MouseEvent e) {
					c6Label.setBounds(500, 200, 256, 256);
					c6Label.setVisible(true);
					c1Label.setVisible(false);
					c2Label.setVisible(false);
					c4Label.setVisible(false);
					c5Label.setVisible(false);
					c3Label.setVisible(false);
					c7Label.setVisible(false);
					nen.add(c6Label);
					nen.repaint();
				}
			});

			 cs6Label = new JLabel("Dolphin");
			cs6Label.setFont(new Font("c6", Font.HANGING_BASELINE, 30));
			cs6Label.setBounds(720, 620, 120, 30);
			cs6Label.setForeground(Color.red);
			nen.add(cs6Label);

			car7 = new JLabel(iCar7);
			car7.setBounds(868, 500, 128, 128);
			nen.add(car7);
			car7.addMouseListener(this);
			car7.addMouseMotionListener(new MouseMotionListener() {
				public void mouseDragged(MouseEvent e) {
				}

				public void mouseMoved(MouseEvent e) {
					c7Label.setBounds(500, 200, 256, 256);
					c7Label.setVisible(true);
					c1Label.setVisible(false);
					c2Label.setVisible(false);
					c4Label.setVisible(false);
					c5Label.setVisible(false);
					c6Label.setVisible(false);
					c3Label.setVisible(false);
					nen.add(c7Label);
					nen.repaint();
				}
			});

			cs7Label = new JLabel("Dragon");
			cs7Label.setFont(new Font("c7", Font.HANGING_BASELINE, 30));
			cs7Label.setBounds(878, 620, 110, 30);
			cs7Label.setForeground(Color.red);
			nen.add(cs7Label);

			nen.addMouseMotionListener(new MouseMotionListener() {

				@Override
				public void mouseDragged(MouseEvent e) {

				}

				@Override
				public void mouseMoved(MouseEvent e) {
					c7Label.setVisible(false);
					c1Label.setVisible(false);
					c2Label.setVisible(false);
					c4Label.setVisible(false);
					c5Label.setVisible(false);
					c6Label.setVisible(false);
					c3Label.setVisible(false);
					nen.repaint();
				}

			});
			car1.addMouseListener(this);
			car2.addMouseListener(this);
			car3.addMouseListener(this);
			car4.addMouseListener(this);
			car5.addMouseListener(this);
			car6.addMouseListener(this);
			car7.addMouseListener(this);
			selectFrame.setSize(1366, 768);
			selectFrame.setVisible(true);
		}
	
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mouseDragged(MouseEvent e) {
	}

	@Override
	public void mouseMoved(MouseEvent event) {
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}

	
	
	

}
