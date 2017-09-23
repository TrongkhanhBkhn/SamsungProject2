import java.applet.Applet;
import java.applet.AudioClip;
import java.net.MalformedURLException;
import java.net.URL;


public class Music implements Runnable {
	AudioClip aud;
	URL url;
	Music() 
	{
		url = Applet.class.getResource("");
		Applet.newAudioClip(url);
		
	}
	@Override
	public void run() {
		aud.loop();		
	}
	
}
