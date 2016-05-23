import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.event.*;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.border.*;
import javax.swing.BorderFactory;
import javax.swing.UIManager;

public class GameFrame extends JFrame
{
	private JPanel blank;
	private JLabel titleLogo;
	private JPanel boardAndTitle;

	private Color[] colorOfLines = new Color[4];
	private Border[] coloredLines = new Border[4];

	public Border compound1, compound2, compound3;
	private Border raised = BorderFactory.createRaisedBevelBorder();
	private Border lowered = BorderFactory.createLoweredBevelBorder();

	BoardPanel board;
	SidePanels sidePanel;

	public GameFrame()
	{
		setLayout(new BorderLayout(5,5));
		setBackground(Color.BLACK);

		attachBlankPanel();
		attachBoardPanel();

		sidePanel = new SidePanels(compound3);
		sidePanel.setPreferredSize(new Dimension(300,300));

		add(blank, BorderLayout.WEST);
		add(boardAndTitle, BorderLayout.CENTER);
		add(sidePanel,BorderLayout.EAST);

		new Music();
	}

	public void attachBlankPanel()
	{
		JPanel bPanel;

		blank = new JPanel();
		blank.setBackground(Color.BLACK);
		blank.setPreferredSize(new Dimension(60,60));
	}

	public void attachBoardPanel()
	{
		PointGeneration pg = new PointGeneration();
        int[] tiles = pg.getTiles();
		board = new BoardPanel(tiles);

		colorOfLines[0] = new Color(96,96,96);
		colorOfLines[1] = new Color(30,30,30);
		colorOfLines[2] = new Color(0,0,0);
		colorOfLines[3] = new Color(255,255,255);

		int[] sizes = {4, 4, 8, 3};

		for(int x = 0; x < 4; x++)
			coloredLines[x] = BorderFactory.createLineBorder(colorOfLines[x], sizes[x], true);

		compound1 = BorderFactory.createCompoundBorder(coloredLines[0], coloredLines[1]);
		compound2 = BorderFactory.createCompoundBorder(coloredLines[2], coloredLines[3]);
		compound3 = BorderFactory.createCompoundBorder(compound2, compound1);
		titleLogo = new JLabel(new ImageIcon("resources/SL.jpg"));

		boardAndTitle = new JPanel();
		boardAndTitle.setBorder(compound3);
		boardAndTitle.setBackground(new Color(0,0,128));
		boardAndTitle.setLayout(new BorderLayout(5,5));
		boardAndTitle.add(titleLogo, BorderLayout.NORTH);
		boardAndTitle.add(board, BorderLayout.CENTER);
	}

}
