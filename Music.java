import sun.audio.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;

public class Music
{
	String filename = "resources/TDF.wav";

	InputStream backMusic = null;
	AudioStream music = null;

	public Music()
	{
		try
		{
			backMusic = new FileInputStream(filename);

		}
		catch(FileNotFoundException e)
		{
			System.out.println("FileNotFoundException : Failed to Play SoundTrack.");
		}

		try
		{
			music = new AudioStream(backMusic);

		}
		catch (IOException e)
		{
			System.out.println("IOException found.");
		}

		AudioPlayer.player.start(music);
	}


}
