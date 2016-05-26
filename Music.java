import sun.audio.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;

public class Music
{
	InputStream backMusic = null;
	AudioStream music = null;

	InputStream diceBackMusic = null;
	AudioStream diceMusic = null;

	public Music()
	{
		String filename = "resources/SpringField.wav";

		try
		{
			backMusic = new FileInputStream(filename);
		}
		catch(FileNotFoundException e) {

			System.out.println("FileNotFoundException : Failed to Play SoundTrack.");
		}

		try
		{
			music = new AudioStream(backMusic);
		}
		catch (IOException e) {

			System.out.println("IOException found.");
		}

		AudioPlayer.player.start(music);
	}
	
	public Music(String m)
	{		
		try
		{
			diceBackMusic = new FileInputStream(m);
		}
		catch(FileNotFoundException e) {
			
			System.out.println("FileNotFoundException : Failed to Play SoundTrack.");
		}
		
		try
		{
			diceMusic = new AudioStream(diceBackMusic);
		}
		catch (IOException e) {
			
			System.out.println("IOException found.");
		}
		
		AudioPlayer.player.start(diceMusic);
	}
}
