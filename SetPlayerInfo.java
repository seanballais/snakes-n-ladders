import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class SetPlayerInfo extends JFrame implements ActionListener
{
	private JPanel imgPanel;
	private JButton game, selectColor;
	private JPanel buttonHolder;

	public JLabel[] pieceImages = new JLabel[4];
	public JPanel[] pieceImgHolder = new JPanel[4];
	public String[] pieceImgNames = {"resources/bluePiece.jpg", "resources/redPiece.jpg", "resources/yellowPiece.jpg", "resources/greenPiece.jpg"};
	private Font eras = new Font("Eras Bold ITC", Font.ITALIC + Font.BOLD, 20);

	public static String p1, p2;
	public static int[] colorIndexOfPlayers = new int[2];

	public SetPlayerInfo()
	{
		setLayout(new BorderLayout(2,2));

		UIManager.put("OptionPane.messageFont", new Font("Britannic Bold", Font.PLAIN, 16));
		UIManager.put("OptionPane.buttonFont", new Font("Times New Roman", Font.BOLD, 18));
		UIManager.put("Button.disabledText", Color.BLACK);
		UIManager.put("Button.foreground", Color.WHITE);

		p1 = JOptionPane.showInputDialog("PLAYER 1 : ");
		p2 = JOptionPane.showInputDialog("PLAYER 2 : ");

		createPieceImages();
		createSelectButtons();

		add(imgPanel, BorderLayout.CENTER);
		add(buttonHolder, BorderLayout.SOUTH);
	}

	public void createPieceImages()
	{
		imgPanel = new JPanel();
		imgPanel.setLayout(new GridLayout(2,2));
		imgPanel.setBackground(Color.BLACK);

		for(int x = 0; x < 4; x++)
		{
			pieceImages[x] = new JLabel(new ImageIcon(pieceImgNames[x]));
			pieceImgHolder[x] = new JPanel();
			pieceImgHolder[x].add(pieceImages[x]);

			imgPanel.add(pieceImgHolder[x]);
		}
	}


	public void createSelectButtons()
	{
		buttonHolder = new JPanel();
		buttonHolder.setLayout(new GridLayout(1,2));
		buttonHolder.setPreferredSize(new Dimension(50,50));

		selectColor = new JButton("Select Color");
		selectColor.setFont(new Font("Eras BOLD ITC", Font.BOLD + Font.ITALIC, 20));
		selectColor.setHorizontalAlignment(SwingConstants.CENTER);
		selectColor.addActionListener(this);

		game = new JButton("Game");
		game.setEnabled(false);
		game.setFont(new Font("Eras BOLD ITC", Font.BOLD + Font.ITALIC, 20));
		game.addActionListener(this);

		buttonHolder.add(selectColor);
		buttonHolder.add(game);
	}

	static int ctr = 1;
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == selectColor)
		{
			selectColor.setEnabled(false);
			JOptionPane.showMessageDialog(SetPlayerInfo.this, "Choose Color For Player " + ctr,
										"Color of Piece", JOptionPane.INFORMATION_MESSAGE);

			for(int j = 0; j < 4; j++)
				pieceImgHolder[j].addMouseListener(new MouseHandler());

		}else if(e.getSource() == game) {

			this.dispose();

			for(int x = 0; x < 5; x++)
				SidePanels.numberOfRounds[x].setEnabled(true);

		}

	}

	private class MouseHandler extends MouseAdapter
	{
		public void mouseClicked(MouseEvent e)
		{
			Color[] colors = {Color.BLUE, Color.RED, Color.YELLOW, Color.GREEN};

			for(int x = 0; x < 4; x++)
			{
				if(e.getSource() == pieceImgHolder[x])
				{
					pieceImages[x].setText("Player " + ctr);
					pieceImages[x].setFont(eras);
					pieceImages[x].setVerticalAlignment(SwingConstants.CENTER);
					pieceImgHolder[x].setBackground(colors[x]);

					if(ctr == 1)
						colorIndexOfPlayers[0] = x;
					else
						colorIndexOfPlayers[1] = x;
				}
			}

			ctr++;

			if(ctr == 2)
				JOptionPane.showMessageDialog(SetPlayerInfo.this, "Choose Color For Player " + ctr,
										"Color of Piece", JOptionPane.INFORMATION_MESSAGE);
			if(ctr == 3)
				game.setEnabled(true);
		}
	}

}
