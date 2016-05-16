import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.border.*;
import javax.swing.BorderFactory;

public class GameFrame extends JFrame
{
	private JLabel titleLogo;
	private JPanel boardAndTitle, piecesInitialPlacementPanel;

	private Icon[] pieceImages = new Icon[4];
	private Color[] colorOfLines = new Color[4];
	private Border[] coloredLines = new Border[4];

	protected Border compound1, compound2, compound3;
	private Border raised = BorderFactory.createRaisedBevelBorder();
	private Border lowered = BorderFactory.createLoweredBevelBorder();

	BoardPanel board;
	SidePanels sidePanel;

	public GameFrame()
	{
		setLayout(new BorderLayout(5,5));
		setBackground(Color.BLACK);

		new Music();

		attachInitialPiecePanel();
		attachBoardPanel();

		sidePanel = new SidePanels(compound3);
		sidePanel.setPreferredSize(new Dimension(300,300));

		add(piecesInitialPlacementPanel, BorderLayout.WEST);
		add(boardAndTitle, BorderLayout.CENTER);
		add(sidePanel,BorderLayout.EAST);
	}

	public void attachInitialPiecePanel()
	{
		JPanel blank = new JPanel();
		JPanel piecePanel = new JPanel();
		JLabel[] label = new JLabel[4];

		String[] pieces = {"resources/b.jpg", "resources/r.jpg", "resources/y.jpg", "resources/g.jpg"};

		blank.setBackground(Color.BLACK);
		piecePanel.setLayout(new GridLayout(4,1));

		for(int x = 0; x < 4; x++)
		{
			pieceImages[x] = new ImageIcon(pieces[x]);
			piecePanel.add(new JLabel(pieceImages[x]));
		}

		piecesInitialPlacementPanel = new JPanel();
		piecesInitialPlacementPanel.setLayout(new BorderLayout(2,2));
		piecesInitialPlacementPanel.add(blank, BorderLayout.CENTER);
		piecesInitialPlacementPanel.add(piecePanel, BorderLayout.SOUTH);

	}

	public void attachBoardPanel()
	{
		LinePointing lp = new LinePointing();
        int[] tiles = lp.getTiles();
		board = new BoardPanel(tiles);

		System.out.println("Tile Values");
        int ctr = 0;
        for (int i : tiles) {
            System.out.print("Number " + ++ctr + ": ");
            System.out.println(i);
        }

		System.out.println("\nLoading Program...\nProgram Run Success.\n");

		colorOfLines[0] = new Color(0,0,128);
		colorOfLines[1] = new Color(0,64,255);
		colorOfLines[2] = new Color(0,0,192);
		colorOfLines[3] = new Color(0,192,255);

		int[] sizes = {4, 4, 8, 3};

		for(int x = 0; x < 4; x++)
			coloredLines[x] = BorderFactory.createLineBorder(colorOfLines[x], sizes[x], true);

		compound1 = BorderFactory.createCompoundBorder(coloredLines[0], coloredLines[1]);
		compound2 = BorderFactory.createCompoundBorder(coloredLines[2], coloredLines[3]);
		compound3 = BorderFactory.createCompoundBorder(compound2, compound1);
		titleLogo = new JLabel(new ImageIcon("SL.jpg"));

		boardAndTitle = new JPanel();
		boardAndTitle.setBorder(compound3);
		boardAndTitle.setBackground(new Color(0,0,128));
		boardAndTitle.setLayout(new BorderLayout(5,5));
		boardAndTitle.add(titleLogo, BorderLayout.NORTH);
		boardAndTitle.add(board, BorderLayout.CENTER);
	}

}
