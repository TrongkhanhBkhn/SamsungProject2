import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class KeyAdapt extends KeyAdapter{
		
		Car c;
		public KeyAdapt(Car car){
			this.c=car;
		}
		
		public void keyPressed(KeyEvent e){
			c.keyPressed(e);
			
		}

		public void keyReleased(KeyEvent e){
			c.keyReleased(e);
		}

}
