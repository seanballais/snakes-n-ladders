import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.util.Random;

public class SidePanels extends JPanel implements ActionListener, ItemListener
{
	private Border border;
	private ButtonGroup glue;
	private JLabel playersTitle, diceResult;
	private JTextField currentPlayer, tilePosition;
	private JButton play, exit, instructions, credits, rollDice;

	private JPanel[] mainPanels = new JPanel[2];
	private JPanel[] playerAndStatusPanels = new JPanel[2];
	private JTextField[] scoreOfPlayers = new JTextField[2];
	private JTextField[] nameDisplayFields = new JTextField[2];
	private JTextField[] currentPlayerAndTilePosition = new JTextField[4];
	private JRadioButton[] numberOfRounds = new JRadioButton[5];

	private Icon[] diceImages = new Icon[6];


	public SidePanels(Border b)
	{
		UIManager.put("RadioButton.disabledText", Color.BLACK);

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


	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == play)
		{
			SetPlayerInfo c = new SetPlayerInfo();
			c.setSize(700,800);
			c.setUndecorated(true);
			c.setLocationRelativeTo(null);
			c.getRootPane().setBorder(border);
			c.setVisible(true);

			nameDisplayFields[0].setText("Player 1: " + c.p1);
			nameDisplayFields[1].setText("Player 2: " + c.p2);

		}else if(e.getSource() == exit)

			System.exit(0);

		else if(e.getSource() == rollDice)
		{
			Random randomizer = new Random();

			int diceNo = randomizer.nextInt(diceImages.length);

			diceResult.setIcon(diceImages[diceNo]);
			diceResult.setHorizontalAlignment(SwingConstants.CENTER);
			System.out.println("Dice No : " + (diceNo+1));
		}

	}


	public void itemStateChanged(ItemEvent e)
	{
		for(int x = 0; x < 5; x++)
			numberOfRounds[x].setEnabled(false);
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
		String[] number = {"One", "Two", "Three", "Four", "Five"};

		JPanel jButtonPanel = new JPanel();
		JPanel radioButtonPanel = new JPanel();

		play = new JButton("New Game");
		exit = new JButton("Exit");

		jButtonPanel.setLayout(new GridLayout(1,2));
		jButtonPanel.setBackground(new Color(0,96,255));
		jButtonPanel.add(play);
		jButtonPanel.add(exit);

		glue = new ButtonGroup();

		for(int x = 0; x < 5; x++)
		{
			numberOfRounds[x] = new JRadioButton(number[x], false);
			radioButtonPanel.add(numberOfRounds[x]);
			glue.add(numberOfRounds[x]);
		}

		TitledBorder tBorder = new TitledBorder(new LineBorder(Color. WHITE, 2), "Number Of Rounds");
		tBorder.setTitleJustification(TitledBorder.CENTER);
		tBorder.setTitleColor(Color.WHITE);

		radioButtonPanel.setBorder(tBorder);
		radioButtonPanel.setBackground(Color.DARK_GRAY);
		radioButtonPanel.setLayout(new BoxLayout(radioButtonPanel, BoxLayout.X_AXIS));

		playerAndStatusPanels[0].setLayout(new BorderLayout(2,2));
		playerAndStatusPanels[0].add(jButtonPanel, BorderLayout.CENTER);
		playerAndStatusPanels[0].add(radioButtonPanel, BorderLayout.SOUTH);

		play.addActionListener(this);
		exit.addActionListener(this);

		for(int y = 0; y < 5; y++)
			numberOfRounds[y].addItemListener(this);
	}


	public void createPlayersDisplayPanel()
	{
		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();

		for(int a = 0; a < 2; a++)
		{
			nameDisplayFields[a] = new JTextField(String.format("  Player %d:", a+1), 13);
			nameDisplayFields[a].setEditable(false);
			nameDisplayFields[a].setBorder(BorderFactory.createLoweredBevelBorder());

			scoreOfPlayers[a] = new JTextField("0", 2);
			scoreOfPlayers[a].setEditable(false);
			scoreOfPlayers[a].setHorizontalAlignment(SwingConstants.CENTER);
			scoreOfPlayers[a].setBorder(BorderFactory.createLoweredBevelBorder());

			panel1.add(nameDisplayFields[a]);
			panel1.add(scoreOfPlayers[a]);
		}

		playersTitle =  new JLabel("PLAYERS", SwingConstants.CENTER);
		playersTitle.setVerticalAlignment(SwingConstants.TOP);

		panel1.setLayout(new FlowLayout());
		panel1.setBackground(new Color(40,40,40));
		panel1.setPreferredSize(new Dimension(190,230));
		panel1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3, true));

		panel2.setLayout(new FlowLayout());
		panel2.setBorder(border);
		panel2.setBackground(new Color(0,255,64));
		panel2.setPreferredSize(new Dimension(250,300));
		panel2.add(playersTitle);
		panel2.add(panel1);

		playerAndStatusPanels[1].setLayout(new FlowLayout());
		playerAndStatusPanels[1].setBackground(new Color(0,0,0));//0,255,128
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
			currentPlayerAndTilePosition[a].setEditable(false);
			currentPlayerAndTilePosition[a].setHorizontalAlignment(SwingConstants.CENTER);
			currentPlayerAndTilePosition[a].setBorder(BorderFactory.createRaisedBevelBorder());

			if((a % 2) == 0)
				statusTxtField[0].add(currentPlayerAndTilePosition[a]);
			else
				statusTxtField[1].add(currentPlayerAndTilePosition[a]);
		}

		txtFieldPanel = new JPanel();
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
		instructions = new JButton("Instructions");
		credits = new JButton("About");

		buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new GridLayout(1,2));
		buttonsPanel.add(instructions);
		buttonsPanel.add(credits);

		for(int x = 0; x < 6; x++)
			diceImages[x] = new ImageIcon(diceNames[x]);

		diceResult = new JLabel();

		diceResultPanel = new JPanel();
		diceResultPanel.setBackground(Color.DARK_GRAY);
		diceResultPanel.setPreferredSize(new Dimension(120,150));
		diceResultPanel.add(diceResult);

		mainPanels[1].setLayout(new BorderLayout(2,2));
		mainPanels[1].add(rollDice, BorderLayout.NORTH);
		mainPanels[1].add(diceResultPanel, BorderLayout.CENTER);
		mainPanels[1].add(buttonsPanel, BorderLayout.SOUTH);

		rollDice.addActionListener(this);
		instructions.addActionListener(this);
		credits.addActionListener(this);
	}

}
