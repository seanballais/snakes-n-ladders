import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class GameFrame extends JFrame {
				
	public GameFrame() {
		
		super("SNAKES AND LADDERS");
		setLayout(new BorderLayout(15,15));
		
		String p1 = JOptionPane.showInputDialog("PLAYER 1: ");
		String p2 = JOptionPane.showInputDialog("PLAYER 2: ");
		
		BoardPanel board = new BoardPanel();
		board.setLayout(new GridLayout(10,10));
		
		SidePanel sidePanel = new SidePanel(p1,p2);
		sidePanel.setLayout(new GridLayout(3,1));
			
				
		add(board, BorderLayout.CENTER);
		add(sidePanel, BorderLayout.EAST);
			
	}
}
