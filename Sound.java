
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound implements Runnable {
	Clip c;
	int x1;
	Point mymax;
	int mp, tim;

	AudioInputStream ad;
	File f;

	

	public void run() {

		c.start();
		try {

			c.setMicrosecondPosition(300);

			Thread.sleep(tim);
			c.stop();
			c.setMicrosecondPosition(300);

		} catch (Exception e) {
			System.out.println("Error Here");
		}
	}

	void running(int x, int tim) {
		this.tim = tim;
		if (x1 != x) {
			x1 = x;
			f = new File("../public/w" + x1 + ".wav");

			try {
				ad = AudioSystem.getAudioInputStream(f);

				c = AudioSystem.getClip();
				c.open(ad);
				Thread th = new Thread(this);

				th.start();

			} catch (Exception e) {
				System.out.println("Dissappointment" + e.getMessage());
			}
		}
		run();

	}
}
