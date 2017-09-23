import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.swing.*;


public class box {
	
	JFrame fr;
	static JLabel warnL,infoL,errorL;
	static JLabel lb;
	Icon warnIcon,infoIcon,errorIcon;
	static JButton ok,yes,no;
	
	public box()
	{
		//-----------------------------------------------------------------------------
		//create frame
		fr=new JFrame();
		fr.setVisible(true);
		fr.setSize(300,200);
		fr.setLayout(null);
		
		//-----------------------------------------------------------------------------
		//add icon
		warnIcon = UIManager.getIcon("OptionPane.warningIcon");
		infoIcon = UIManager.getIcon("OptionPane.informationIcon");
		errorIcon = UIManager.getIcon("OptionPane.errorIcon");
		
		//-----------------------------------------------------------------------------
		//add label
		lb= new JLabel();
		lb.setBounds(80,50,200,40);
		fr.add(lb);
		
		warnL =new JLabel(warnIcon);
		warnL.setBounds(20,40,60,60);
		
		errorL =new JLabel(errorIcon);
		errorL.setBounds(20,40,60,60);
		
		infoL =new JLabel(infoIcon);
		infoL.setBounds(20,40,60,60);
		
		//-----------------------------------------------------------------------------
		//add button
		ok=new JButton("OK");
		ok.setBounds(110,100,60,30);
        ok.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                fr.dispose();
            }
        });      

	}
	
	public static void warnBox(String str){
		//create box
		box app=new box();
		app.fr.add(ok);
		app.fr.add(warnL);
		lb.setText(str);
	}
	
	public static void errorBox(String str){
		//create box
		box app=new box();
		app.fr.add(ok);
		app.fr.add(errorL);
		lb.setText(str);
	}
	
	public static void infoBox(String str){
		//create box
		box app=new box();
		app.fr.add(ok);
		app.fr.add(infoL);
		lb.setText(str);
	}
	public static void overGame(String str){
		//create box
		box app=new box();
		app.fr.add(ok);
		app.fr.add(infoL);
		lb.setText(str);
	}

}
