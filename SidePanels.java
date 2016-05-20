import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.util.Random;

public class SidePanels extends JPanel implements ActionListener, ItemListener
{
	private int diceNo;
	public static Border border;
	private ButtonGroup glue;
	private static JPanel twoChipHolder;
	private JLabel playersTitle, diceResult;
	private JTextField currentPlayer, tilePosition;
	public static JButton newGame, exit, instructions, credits, rollDice, pause;

	private JPanel[] mainPanels = new JPanel[2];
	private JPanel[] playerAndStatusPanels = new JPanel[2];
	public static JTextField[] nameDisplayFields = new JTextField[2];
	public static JTextField[] scoreOfPlayers = new JTextField[2];
	public static JTextField[] currentPlayerAndTilePosition = new JTextField[4];
	public static JRadioButton[] numberOfRounds = new JRadioButton[5];
	private Icon[] diceImages = new Icon[6];

	private Font eras = new Font("Eras Bold ITC", Font.ITALIC + Font.BOLD, 15);

	SetPlayerInfo info;

	public SidePanels(Border b)
	{
		UIManager.put("RadioButton.disabledText", Color.BLACK);
		UIManager.put("Button.text", Color.WHITE);
		UIManager.put("Button.background", new Color(0,0,128));
		UIManager.put("Button.foreground", new Color(255,128,0));
		UIManager.put("Button.font", new Font("Eras Bold ITC", Font.BOLD, 15));
		UIManager.put("RadioButton.font", new Font("Eras Bold ITC", Font.ITALIC + Font.BOLD, 12));
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
		String[] number = {"Checkered", "Casual", "Dark", "Chocolate", "Rainbow"};

		JPanel jButtonPanel = new JPanel();
		JPanel radioButtonPanel = new JPanel();

		newGame = new JButton("New Game");
		exit = new JButton("Exit");

		jButtonPanel.setLayout(new GridLayout(1,2));
		jButtonPanel.setBackground(new Color(0,96,255));
		jButtonPanel.add(newGame);
		jButtonPanel.add(exit);

		glue = new ButtonGroup();
/*
		for(int x = 0; x < 5; x++)
		{
			numberOfRounds[x] = new JRadioButton(number[x], false);
			numberOfRounds[x].setEnabled(false);
			radioButtonPanel.add(numberOfRounds[x]);
			glue.add(numberOfRounds[x]);
		}
*/
		/*TitledBorder tBorder = new TitledBorder(new LineBorder(Color. WHITE, 2), "Color Theme");
		tBorder.setTitleJustification(TitledBorder.CENTER);
		tBorder.setTitleColor(Color.WHITE);*/

		/*radioButtonPanel.setBorder(tBorder);
		radioButtonPanel.setBackground(Color.DARK_GRAY);
		radioButtonPanel.setLayout(new BoxLayout(radioButtonPanel, BoxLayout.X_AXIS));*/

		playerAndStatusPanels[0].setLayout(new BorderLayout(2,2));
		playerAndStatusPanels[0].add(jButtonPanel, BorderLayout.CENTER);
		/*playerAndStatusPanels[0].add(radioButtonPanel, BorderLayout.SOUTH);*/

		newGame.addActionListener(this);
		exit.addActionListener(this);

		/*for(int y = 0; y < 5; y++)
			numberOfRounds[y].addItemListener(this);*/
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
		playersTitle.setForeground(new Color(255,128,0));
		playersTitle.setVerticalAlignment(SwingConstants.TOP);

		panel1.setLayout(new FlowLayout());
		panel1.setBackground(new Color(40,40,40));
		panel1.setPreferredSize(new Dimension(250,210));
		panel1.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3, true));

		panel2.setLayout(new FlowLayout());
		panel2.setBorder(border);
		panel2.setBackground(new Color(0,0,128));
		panel2.setForeground(Color.WHITE);
		panel2.setPreferredSize(new Dimension(290,300));
		panel2.add(playersTitle);
		panel2.add(panel1);

		playerAndStatusPanels[1].setLayout(new FlowLayout());
		playerAndStatusPanels[1].setBackground(new Color(0,0,0));
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
		JPanel buttonsPanel;
		JPanel diceResultPanel;

		String[] diceNames = {"resources/dice1.jpg", "resources/dice2.jpg", "resources/dice3.jpg", "resources/dice4.jpg",
							  "resources/dice5.jpg", "resources/dice6.jpg"};

		rollDice = new JButton("Roll Dice");
		rollDice.setEnabled(false);
		instructions = new JButton("Instructions");
		credits = new JButton("About");
		pause = new JButton("Pause");
		pause.setEnabled(false);

		buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new GridLayout(2,2));
		buttonsPanel.add(pause);
		buttonsPanel.add(instructions);
		buttonsPanel.add(credits);

		for(int x = 0; x < 6; x++)
			diceImages[x] = new ImageIcon(diceNames[x]);

		diceResult = new JLabel();

		diceResultPanel = new JPanel();
		diceResultPanel.setBackground(Color.BLACK);
		diceResultPanel.setPreferredSize(new Dimension(120,150));
		diceResultPanel.add(diceResult);

		mainPanels[1].setLayout(new BorderLayout(2,2));
		mainPanels[1].add(rollDice, BorderLayout.NORTH);
		mainPanels[1].add(diceResultPanel, BorderLayout.CENTER);
		mainPanels[1].add(buttonsPanel, BorderLayout.SOUTH);

		rollDice.addActionListener(this);
		instructions.addActionListener(this);
		credits.addActionListener(this);
		pause.addActionListener(this);
	}


	public void gameInstructions()
	{
		JTextArea instructTextArea;

		instructTextArea = new JTextArea(100,120);

		instructTextArea.setFont(new Font("Serif", Font.BOLD, 20));
		instructTextArea.setEditable(false);
		instructTextArea.setForeground(Color.YELLOW);
		instructTextArea.setBackground(Color.BLACK);

		JFrame instructs = new JFrame();

		instructs.add(instructTextArea);
		instructs.setUndecorated(true);
		instructs.setSize(800,500);
		instructs.setLocationRelativeTo(null);
		instructs.setVisible(true);
	}


	public void gameCredits()
	{
		JFrame c = new JFrame();
		JScrollPane scrollPane;

		c.setTitle("About The Game");
		c.setSize(590,700);

		JPanel topPanel = new JPanel();
		topPanel.setLayout(new BorderLayout(0,0));
		c.getContentPane().add(topPanel);

		Icon image = new ImageIcon("resources/CreditsFINAL.jpg");
		JLabel label = new JLabel(image);

		scrollPane = new JScrollPane();
		scrollPane.getViewport().add(label);
		topPanel.add(scrollPane, BorderLayout.CENTER);

		c.setVisible(true);
		c.setLocationRelativeTo(null);
	}


	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == newGame)
		{
			info = new SetPlayerInfo();
			info.setSize(600,650);
			info.setUndecorated(true);
			info.setLocationRelativeTo(null);
			info.getRootPane().setBorder(border);
			info.setVisible(true);

			nameDisplayFields[0].setText("  Player 1: " + SetPlayerInfo.p1);
			nameDisplayFields[1].setText("  Player 2: " + SetPlayerInfo.p2);

			SidePanels.newGame.setEnabled(false);
			SidePanels.instructions.setEnabled(false);
			SidePanels.credits.setEnabled(false);
			SidePanels.exit.setEnabled(false);

		}else if(e.getSource() == exit) {

			int dialogResult = JOptionPane.showConfirmDialog(null, " Confirm Leave?", "Quit", JOptionPane.YES_NO_OPTION);

			JFrame f = new JFrame();

			if(dialogResult == 0)
			{
				System.exit(0);
				f.setContentPane(new JLabel(new ImageIcon("resources/lastFrameGames.png")));
				f.setSize(200,200);
				f.setUndecorated(true);
				f.getRootPane().setBorder(new LineBorder(Color.BLACK, 8, true));
				f.setLocationRelativeTo(null);
				f.setVisible(true);
			}

		}else if(e.getSource() == rollDice) {

			Random randomizer = new Random();

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
			//new Music("soundEffects/RollDice.wav");

		}else if(e.getSource() == instructions)

			gameInstructions();

		else if(e.getSource() == credits)
		{
			gameCredits();

		}else if(e.getSource() == pause) {

			Icon icon = new ImageIcon("resources/sl.png");

			if(e.getSource() == pause)
				JOptionPane.showMessageDialog(null, new JLabel(icon, JLabel.CENTER), "Game Paused", JOptionPane.PLAIN_MESSAGE);
		}

	}


	public void itemStateChanged(ItemEvent e)
	{
		for(int x = 0; x < 5; x++)
			numberOfRounds[x].setEnabled(false);
	}

}
