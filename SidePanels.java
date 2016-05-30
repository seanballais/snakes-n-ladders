import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.UIManager;
import javax.swing.SwingConstants;
import java.util.Random;

public class SidePanels extends JPanel implements ActionListener
{
	private int diceNo;
	public static char triggerEvent;

	private ButtonGroup glue;
	private JPanel diceResultPanel;
	private JLabel playersTitle;
	private JFrame instructionsFrame, creditsFrame;
	private JTextField currentPlayer, tilePosition;

	public static Border border;
	public static JPanel leftPanel;
	public static JLabel diceResult;
	public static JButton stop, resume, pause, back, back2;
	public static JButton newGame, backToMenu, instructions, credits, rollDice;

	private Icon[] diceImages = new Icon[6];
	private JPanel[] mainPanels = new JPanel[2];
	private JPanel[] playerAndStatusPanels = new JPanel[2];
	public static JTextField[] scoreOfPlayers = new JTextField[2];
	public static JTextField[] nameDisplayFields = new JTextField[2];
	public static JTextField[] currentPlayerAndTilePosition = new JTextField[4];
	public static JRadioButton[] numberOfRounds = new JRadioButton[5];
	private Font eras = new Font("Eras Bold ITC", Font.ITALIC + Font.BOLD, 15);

	Random randomizer = new Random();

	public SidePanels()
	{
		//blank constructor for use in setplayerinfo
	}

	public SidePanels(Border b)
	{
		UIManager.put("Button.text", Color.WHITE);
		UIManager.put("Button.background", new Color(0,0,128));
		UIManager.put("Button.foreground", new Color(255,128,0));
		UIManager.put("Button.font", new Font("Eras Bold ITC", Font.BOLD, 15));
		UIManager.put("OptionPane.messageFont", new Font("Britannic Bold", Font.PLAIN, 16));

		setLayout(new BorderLayout(5,5));
		setPreferredSize(new Dimension(280,200));
		border = b;

		for(int w = 0; w < 2; w++)
			mainPanels[w] = new JPanel();

		createPlayerAndStatusPanel();
		createDicePanel();

		add(mainPanels[0], BorderLayout.CENTER);
		add(mainPanels[1], BorderLayout.SOUTH);
	}


	public void createPlayerAndStatusPanel()
	{
		mainPanels[0].setLayout(new BorderLayout(2,2));

		for(int x = 0; x < 2; x++)
			playerAndStatusPanels[x] = new JPanel();

		createButtonsOnTop();
		createPlayersDisplayPanel();
		createStatusPanel();

		mainPanels[0].add(playerAndStatusPanels[0], BorderLayout.NORTH);
		mainPanels[0].add(playerAndStatusPanels[1], BorderLayout.CENTER);
	}


	public void createButtonsOnTop()
	{
		JPanel jButtonPanel = new JPanel();

		newGame = new JButton(new ImageIcon("resources/buttonIcons/newGameButton.png"));
		backToMenu = new JButton(new ImageIcon("resources/buttonIcons/mainmenuButton.png"));

		newGame.setRolloverIcon(new ImageIcon("resources/buttonIcons/newGameHover.png"));
		backToMenu.setRolloverIcon(new ImageIcon("resources/buttonIcons/mainmenuHover.png"));

		jButtonPanel.setLayout(new GridLayout(1,2));
		jButtonPanel.setBackground(new Color(0,96,255));
		jButtonPanel.setPreferredSize(new Dimension(30,30));
		jButtonPanel.add(newGame);
		jButtonPanel.add(backToMenu);

		playerAndStatusPanels[0].setLayout(new BorderLayout(0,0));
		playerAndStatusPanels[0].add(jButtonPanel);

		newGame.addActionListener(this);
		backToMenu.addActionListener(this);
	}


	public void createPlayersDisplayPanel()
	{
		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();

		for(int a = 0; a < 2; a++)
		{
			nameDisplayFields[a] = new JTextField(String.format("  Player %d:", a+1), 13);
			nameDisplayFields[a].setEditable(false);
			nameDisplayFields[a].setFont(eras);
			nameDisplayFields[a].setBorder(BorderFactory.createLoweredBevelBorder());

			scoreOfPlayers[a] = new JTextField("0", 2);
			scoreOfPlayers[a].setFont(eras);
			scoreOfPlayers[a].setEditable(false);
			scoreOfPlayers[a].setHorizontalAlignment(SwingConstants.CENTER);
			scoreOfPlayers[a].setBorder(BorderFactory.createLoweredBevelBorder());

			panel1.add(nameDisplayFields[a]);
			panel1.add(scoreOfPlayers[a]);
		}

		playersTitle =  new JLabel("PLAYERS", SwingConstants.CENTER);
		playersTitle.setFont(new Font("Eras Bold ITC", Font.BOLD, 25));
		playersTitle.setVerticalAlignment(SwingConstants.TOP);
		playersTitle.setForeground(new Color(255,128,0));

		panel1.setLayout(new FlowLayout());
		panel1.setBackground(new Color(40,40,40));
		panel1.setPreferredSize(new Dimension(250,160));
		panel1.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3, true));

		panel2.setLayout(new FlowLayout());
		panel2.setPreferredSize(new Dimension(290,250));
		panel2.setBackground(new Color(0,0,128));
		panel2.setForeground(Color.WHITE);
		panel2.setBorder(border);
		panel2.add(playersTitle);
		panel2.add(panel1);

		playerAndStatusPanels[1].setBackground(new Color(0,0,0));
		playerAndStatusPanels[1].setLayout(new FlowLayout());
		playerAndStatusPanels[1].add(panel2);
	}


	public void createStatusPanel()
	{
		JPanel[] statusTxtField = new JPanel[2];
		JPanel txtFieldPanel;

		currentPlayerAndTilePosition[0] = new JTextField("Current Player", 10);
		currentPlayerAndTilePosition[1] = new JTextField("Tile #", 5);
		currentPlayerAndTilePosition[2] = new JTextField(10);
		currentPlayerAndTilePosition[3] = new JTextField("0", 5);

		for(int b = 0; b < 2; b++)
			statusTxtField[b] = new JPanel();

		statusTxtField[0].setLayout(new GridLayout(2,1));
		statusTxtField[1].setLayout(new GridLayout(2,1));

		for(int a = 0; a < 4; a++)
		{
			currentPlayerAndTilePosition[a].setFont(eras);
			currentPlayerAndTilePosition[a].setEditable(false);
			currentPlayerAndTilePosition[a].setForeground(Color.WHITE);
			currentPlayerAndTilePosition[a].setBackground(Color.DARK_GRAY);
			currentPlayerAndTilePosition[a].setHorizontalAlignment(SwingConstants.CENTER);
			currentPlayerAndTilePosition[a].setBorder(BorderFactory.createRaisedBevelBorder());

			if((a % 2) == 0)
				statusTxtField[0].add(currentPlayerAndTilePosition[a]);
			else
				statusTxtField[1].add(currentPlayerAndTilePosition[a]);
		}

		txtFieldPanel = new JPanel();
		txtFieldPanel.setBackground(new Color(0,0,128));
		txtFieldPanel.add(statusTxtField[0], BorderLayout.CENTER);
		txtFieldPanel.add(statusTxtField[1], BorderLayout.EAST);

		playerAndStatusPanels[1].add(txtFieldPanel);
	}

	public void createDicePanel()
	{
		JPanel playPausePanel;

		stop = new JButton(new ImageIcon("resources/stop.png"));
		pause = new JButton(new ImageIcon("resources/pause.png"));
		resume = new JButton(new ImageIcon("resources/play.png"));

		resume.addActionListener(this);
		pause.addActionListener(this);
		stop.addActionListener(this);

		String[] diceNames = {"resources/diceIcons/dice1.jpg", "resources/diceIcons/dice2.jpg",
							  "resources/diceIcons/dice3.jpg", "resources/diceIcons/dice4.jpg",
							  "resources/diceIcons/dice5.jpg", "resources/diceIcons/dice6.jpg"};

		rollDice = new JButton(new ImageIcon("resources/buttonIcons/rolldiceButton.png"));
		rollDice.setPreferredSize(new Dimension(40,40));
		rollDice.addActionListener(this);

		for(int x = 0; x < 6; x++)
			diceImages[x] = new ImageIcon(diceNames[x]);

		diceResult = new JLabel();

		playPausePanel = new JPanel();
		playPausePanel.setLayout(new GridLayout(1,3));
		playPausePanel.add(resume);
		playPausePanel.add(pause);
		playPausePanel.add(stop);

		diceResultPanel = new JPanel();
		diceResultPanel.setBackground(Color.BLACK);
		diceResultPanel.setPreferredSize(new Dimension(120,150));
		diceResultPanel.add(diceResult);

		mainPanels[1].setLayout(new BorderLayout(2,2));
		mainPanels[1].add(rollDice, BorderLayout.NORTH);
		mainPanels[1].setPreferredSize(new Dimension(240,300));
		mainPanels[1].add(diceResultPanel, BorderLayout.CENTER);
		mainPanels[1].add(playPausePanel, BorderLayout.SOUTH);
	}

	public void resetGame()
	{
		BoardPanel.tileNo[GameProper.ctrForTile1].setIcon(null);
		BoardPanel.tileNo[GameProper.ctrForTile2].setIcon(null);
		diceResult.setIcon(null);

		SetPlayerInfo.ctr = 1;
		SetPlayerInfo.disableMouseListener = 't';
		GameProper.diceNo = 0;
		GameProper.ctrForTurn = 0;
		GameProper.ctrForTile1 = 0;
		GameProper.ctrForTile2 = 0;


		for(int i = 0; i < 4; i++)
		{
			if(i < 2)
			{
				currentPlayerAndTilePosition[i].setForeground(Color.WHITE);
				nameDisplayFields[i].setText("  Player " + (i+1) + ":");
				scoreOfPlayers[i].setText("0");
			}else
				currentPlayerAndTilePosition[i].setText("");

		}

		triggerEvent = '\0';
		resume.removeActionListener(this);
		pause.removeActionListener(this);
		stop.removeActionListener(this);
	}

	public void actionPerformed(ActionEvent e)
	{
		Icon icon = new ImageIcon("resources/sl.png");

		if(e.getSource() == newGame)
		{
			SetPlayerInfo info = new SetPlayerInfo();

			info.setSize(600,650);
			info.setUndecorated(true);
			info.setLocationRelativeTo(null);
			info.getRootPane().setBorder(border);
			info.setVisible(true);

			newGame.setEnabled(false);
			backToMenu.setEnabled(false);

		}else if(e.getSource() == backToMenu) {

			System.exit(0);

		}else if(e.getSource() == rollDice) {

			if(triggerEvent == 't')
			{
				diceNo = randomizer.nextInt(diceImages.length);
				diceResult.setIcon(diceImages[diceNo]);
				diceResult.setHorizontalAlignment(SwingConstants.CENTER);

				for(int x = 0; x < 4; x++)
				{
					if(GameProper.ctrForTurn % 2 == 0)
						currentPlayerAndTilePosition[x].setForeground(SetPlayerInfo.colors[SetPlayerInfo.colorIndexOfPlayers[1]]);
					else
						currentPlayerAndTilePosition[x].setForeground(SetPlayerInfo.colors[SetPlayerInfo.colorIndexOfPlayers[0]]);
				}

				GameProper g = new GameProper(diceNo);
				new Music("resources/RollDice.wav");

			}else if(triggerEvent == 'f')
				JOptionPane.showMessageDialog(null, "Game Paused. Resume to Roll.", "Paused", JOptionPane.INFORMATION_MESSAGE);
			else
				JOptionPane.showMessageDialog(null, "No Game in Commence. Create Game First.", "Woops!", JOptionPane.WARNING_MESSAGE);

		}else if(e.getSource() == pause) {
			if(triggerEvent == 't')
			{
				JOptionPane.showMessageDialog(null, new JLabel(icon, JLabel.CENTER), "Game Paused", JOptionPane.PLAIN_MESSAGE);
				triggerEvent = 'f';

			}else if(triggerEvent == 'f')
				JOptionPane.showMessageDialog(null, "  Currently Paused.", "Game Paused", JOptionPane.INFORMATION_MESSAGE);
			else
				JOptionPane.showMessageDialog(null, "No Game in Commence. Create Game First.", "Woops!", JOptionPane.WARNING_MESSAGE);

		}else if(e.getSource() == resume) {
			if(triggerEvent == 'f')
			{
				JOptionPane.showMessageDialog(null, new JLabel(icon, JLabel.CENTER), "Game Resumed", JOptionPane.PLAIN_MESSAGE);
				triggerEvent = 't';
			}else if(triggerEvent == '\0')
				JOptionPane.showMessageDialog(null, "No Game in Commence. Create Game First.", "Woops!", JOptionPane.WARNING_MESSAGE);

		}else if(e.getSource() == stop) {
			if(triggerEvent == '\0')
				JOptionPane.showMessageDialog(null, "No Game in Commence. Create Game First.", "Woops!", JOptionPane.WARNING_MESSAGE);

			else{
				int counter = JOptionPane.showConfirmDialog(null, "  Abandon Match?", "End Game?", JOptionPane.YES_NO_OPTION);

				if(counter == 0)
				{
					newGame.addActionListener(this);
					backToMenu.addActionListener(this);
					rollDice.removeActionListener(this);

					resetGame();
				}
			}

		}

	}

}
