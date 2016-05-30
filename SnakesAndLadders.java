import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.border.LineBorder;
import javax.swing.BorderFactory;

public class SnakesAndLadders
{
	public static void main(String args[])
	{
		StartMenu menu = new StartMenu();

		menu.setSize(1000,700);
		menu.setUndecorated(true);
		menu.setVisible(true);
		menu.setLocationRelativeTo(null);
		menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menu.getRootPane().setBorder(new LineBorder(Color.BLACK, 8));
	}
}
