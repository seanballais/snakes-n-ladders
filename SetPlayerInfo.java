import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class SetPlayerInfo extends JFrame implements ActionListener
{
	private JLabel title;
	private JFrame f = new JFrame();
	private JButton game, selectColor, start;
	private JPanel imgPanel, buttonHolder, titleColorChange;

	public JLabel[] pieceImages = new JLabel[4];
	public JPanel[] pieceImgHolder = new JPanel[4];
	public String[] pieceImgNames = {"resources/bluePiece.png", "resources/redPiece.png", "resources/yellowPiece.png", "resources/greenPiece.png"};
	private String[] pieceImgShine = {"resources/blueshine.png", "resources/redshine.png", "resources/yellowshine.png", "resources/greenshine.png"};
	private Font eras = new Font("Eras Bold ITC", Font.ITALIC + Font.BOLD, 20);
	public static Color[] colors = {Color.BLUE, Color.RED, Color.YELLOW, Color.GREEN};

	public static String p1, p2;
	public static char disableMouseListener = 't';
	public static int[] colorIndexOfPlayers = {-1,-1};

	public SetPlayerInfo()
	{
		setLayout(new BorderLayout(2,2));

		UIManager.put("OptionPane.messageFont", new Font("Britannic Bold", Font.PLAIN, 16));
		UIManager.put("OptionPane.buttonFont", new Font("Times New Roman", Font.BOLD, 18));
		UIManager.put("Button.disabledText", Color.BLACK);
		UIManager.put("Button.foreground", new Color(255,128,0));

		p1 = JOptionPane.showInputDialog("PLAYER 1 : ");
		p2 = JOptionPane.showInputDialog("PLAYER 2 : ");

		createPieceImages();
		createSelectButtons();
		createTitleColorChanger();

		add(titleColorChange, BorderLayout.NORTH);
		add(imgPanel, BorderLayout.CENTER);
		add(buttonHolder, BorderLayout.SOUTH);
	}

	public void createTitleColorChanger()
	{
		titleColorChange = new JPanel();

		title = new JLabel("CHOOSE COLOR");
		title.setBackground(new Color(0,0,128));
		title.setForeground(new Color(255,64,0));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(new Font("Eras Bold ITC", Font.BOLD, 40));

		titleColorChange.add(title, SwingConstants.CENTER);
		titleColorChange.setBorder(SidePanels.border);
		titleColorChange.setBackground(Color.BLUE);

		title.addMouseListener(new MouseHandler());
	}

	public void createPieceImages()
	{
		imgPanel = new JPanel();
		imgPanel.setLayout(new GridLayout(2,2));
		imgPanel.setBackground(Color.BLACK);

		for(int x = 0; x < 4; x++)
		{
			pieceImages[x] = new JLabel(new ImageIcon(pieceImgNames[x]));
			pieceImages[x].addMouseListener(new MouseHandler());
			pieceImgHolder[x] = new JPanel();
			pieceImgHolder[x].setBackground(Color.BLACK);
			pieceImgHolder[x].setBorder(new LineBorder(Color.ORANGE, 5, true));
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
			disableMouseListener = 'f';

			JOptionPane.showMessageDialog(SetPlayerInfo.this, "Choose Color For Player " + ctr,
										"Color of Piece", JOptionPane.INFORMATION_MESSAGE);

			for(int j = 0; j < 4; j++)
				pieceImgHolder[j].addMouseListener(new MouseHandler());

		}else if(e.getSource() == game) {

			this.dispose();

			SidePanels.newGame.setEnabled(false);
			SidePanels.instructions.setEnabled(false);
			SidePanels.credits.setEnabled(false);

			JOptionPane.showMessageDialog(null, " Game Play Commenced. Roll Dice.", "Start", JOptionPane.PLAIN_MESSAGE);

			SidePanels.rollDice.setEnabled(true);
			SidePanels.pause.setEnabled(true);
		}
	}

	private class MouseHandler extends MouseAdapter
	{
		public void mouseClicked(MouseEvent e)
		{
			for(int x = 0; x < 4; x++)
			{
				if((e.getSource() == pieceImgHolder[x] || e.getSource() == pieceImages[x])
					&& disableMouseListener == 'f')
				{
					if(ctr == 1)
						colorIndexOfPlayers[0] = x;
					else
						colorIndexOfPlayers[1] = x;

					if(colorIndexOfPlayers[0] != colorIndexOfPlayers[1])
					{
						pieceImages[x].setText("Player " + ctr);
						pieceImages[x].setFont(eras);
						pieceImages[x].setVerticalAlignment(SwingConstants.CENTER);
						pieceImgHolder[x].setBackground(colors[x]);
					}else{

						JOptionPane.showMessageDialog(null, "Color Already Selected, Pick Another Color.",
												"Retry", JOptionPane.WARNING_MESSAGE);
						ctr--;
					}

				}
			}

			if(disableMouseListener == 'f')
				ctr++;

			switch(ctr)
			{
				case 1 : JOptionPane.showMessageDialog(SetPlayerInfo.this, "  Press 'Select Color'.", "Warning",
										JOptionPane.WARNING_MESSAGE);
						 break;

				case 2 : JOptionPane.showMessageDialog(SetPlayerInfo.this, "Choose Color For Player " + ctr,
										"Color of Piece", JOptionPane.INFORMATION_MESSAGE);
						 break;

				case 3 : game.setEnabled(true);
						 disableMouseListener = 't';
						 ctr++;
						 break;

				case 4 : JOptionPane.showMessageDialog(SetPlayerInfo.this, "Color Pick Finished. Click 'GAME' below to Proceed.",
										"Warning", JOptionPane.WARNING_MESSAGE);
			}
		}

		public void mouseEntered(MouseEvent e)
		{
			for(int x = 0; x < pieceImages.length; x++)
			{
				if(e.getSource() == pieceImages[x] || e.getSource() == pieceImgHolder[x])
				{
					if(disableMouseListener == 'f' && colorIndexOfPlayers[0] != x)
					{
						pieceImages[x].setIcon(new ImageIcon(pieceImgShine[x]));

						if(x == 0)
							title.setForeground(Color.CYAN);
						else
							title.setForeground(colors[x]);

					}else if(disableMouseListener == 't') {

						pieceImages[x].setIcon(new ImageIcon(pieceImgNames[x]));
						title.setForeground(new Color(255,128,0));

					}
				}

				else
					pieceImages[x].setIcon(new ImageIcon(pieceImgNames[x]));
			}
		}

		public void mouseExited(MouseEvent e)
		{
			for(int y = 0; y < pieceImages.length; y++)
				pieceImages[y].setIcon(new ImageIcon(pieceImgNames[y]));

			title.setForeground(new Color(255,128,0));
		}
	}

}