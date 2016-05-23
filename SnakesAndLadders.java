import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.border.*;
import javax.swing.BorderFactory;

public class SnakesAndLadders
{
	public static void main(String args[])
	{
		GameFrame menu = new GameFrame();

		menu.setSize(1060,720);
		menu.setUndecorated(true);
		menu.setVisible(true);
		menu.setLocationRelativeTo(null);
        menu.getRootPane().setBorder(menu.compound3);
		menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
