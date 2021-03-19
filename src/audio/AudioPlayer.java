package audio;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class AudioPlayer
{
	public static final File flap = new File("src/res/sounds/flap.wav");
	public static final File hit = new File("src/res/sounds/hit.wav");
	public static final File point = new File("src/res/sounds/point.wav");
	
	public void play(File file)
	{
		try
		{			
			Clip clip = AudioSystem.getClip();
			AudioInputStream inputStream = AudioSystem.getAudioInputStream(file);
			clip.open(inputStream);
			clip.start();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}