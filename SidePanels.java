import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
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

public class SidePanels extends JPanel implements ActionListener, ItemListener
{	
	private int diceNo;
	private ButtonGroup glue;
	private JLabel playersTitle, diceResult;
	private JFrame instructionsFrame, creditsFrame;
	private JTextField currentPlayer, tilePosition;
	
	public static Border border;
	public static JPanel leftPanel;
	public static JButton stop, resume, pause, back, back2;
	public static JButton newGame, exit, instructions, credits, rollDice;

	private Icon[] diceImages = new Icon[6];
	private JPanel[] mainPanels = new JPanel[2];
	private JPanel[] playerAndStatusPanels = new JPanel[2];
	public static JTextField[] scoreOfPlayers = new JTextField[2];
	public static JTextField[] nameDisplayFields = new JTextField[2];
	public static JTextField[] currentPlayerAndTilePosition = new JTextField[4];
	public static JRadioButton[] numberOfRounds = new JRadioButton[5];

	private Font eras = new Font("Eras Bold ITC", Font.ITALIC + Font.BOLD, 15);

	public SidePanels(Border b)
	{
		UIManager.put("Button.text", Color.WHITE);
		UIManager.put("Button.background", new Color(0,0,128));
		UIManager.put("Button.foreground", new Color(255,128,0));
		UIManager.put("Button.font", new Font("Eras Bold ITC", Font.BOLD, 15));
		UIManager.put("RadioButton.disabledText", Color.BLACK);
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
		mainPanels[1].setPreferredSize(new Dimension(240,240));
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
/*
		glue = new ButtonGroup();

		for(int x = 0; x < 5; x++)
		{
			numberOfRounds[x] = new JRadioButton(number[x], false);
			numberOfRounds[x].setEnabled(false);
			radioButtonPanel.add(numberOfRounds[x]);
			glue.add(numberOfRounds[x]);
		}

		TitledBorder tBorder = new TitledBorder(new LineBorder(Color. WHITE, 2), "Color Theme");
		tBorder.setTitleJustification(TitledBorder.CENTER);
		tBorder.setTitleColor(Color.WHITE);

		radioButtonPanel.setBorder(tBorder);
		radioButtonPanel.setBackground(Color.DARK_GRAY);
		radioButtonPanel.setLayout(new BoxLayout(radioButtonPanel, BoxLayout.X_AXIS));
*/
		playerAndStatusPanels[0].setLayout(new BorderLayout(2,2));
		playerAndStatusPanels[0].add(jButtonPanel, BorderLayout.CENTER);
	//	playerAndStatusPanels[0].add(radioButtonPanel, BorderLayout.SOUTH);

		newGame.addActionListener(this);
		exit.addActionListener(this);

	//	for(int y = 0; y < 5; y++)
	//		numberOfRounds[y].addItemListener(this);
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
		panel1.setPreferredSize(new Dimension(250,190));
		panel1.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3, true));

		panel2.setLayout(new FlowLayout());
		panel2.setPreferredSize(new Dimension(290,280));
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
		JPanel diceResultPanel;
		JPanel instructionsCreditsPanel;

		String[] diceNames = {"resources/dice1.jpg", "resources/dice2.jpg", "resources/dice3.jpg", "resources/dice4.jpg",
							  "resources/dice5.jpg", "resources/dice6.jpg"};

		credits = new JButton("About");
		rollDice = new JButton("Roll Dice");
		instructions = new JButton("Instructions");
		
		rollDice.setEnabled(false);

		for(int x = 0; x < 6; x++)
			diceImages[x] = new ImageIcon(diceNames[x]);

		diceResult = new JLabel();
		
		instructionsCreditsPanel = new JPanel();
		instructionsCreditsPanel.setLayout(new GridLayout(1,2));
		instructionsCreditsPanel.add(instructions);
		instructionsCreditsPanel.add(credits);

		diceResultPanel = new JPanel();
		diceResultPanel.setBackground(Color.BLACK);
		diceResultPanel.setPreferredSize(new Dimension(120,150));
		diceResultPanel.add(diceResult);

		mainPanels[1].setLayout(new BorderLayout(2,2));
		mainPanels[1].add(rollDice, BorderLayout.NORTH);
		mainPanels[1].add(diceResultPanel, BorderLayout.CENTER);
		mainPanels[1].add(instructionsCreditsPanel, BorderLayout.SOUTH);
				
		credits.addActionListener(this);
		rollDice.addActionListener(this);
		instructions.addActionListener(this);
	}


	public void gameInstructions()
	{		
		JPanel picturePanel = new JPanel();
		JPanel bPanel = new JPanel();
		
		back = new JButton("BACK");
		back.setFont(new Font("Eras Bold ITC", Font.ITALIC, 40));
		back.addActionListener(this);
		
		picturePanel.setBackground(new Color(0,0,128));
		picturePanel.add(new JLabel(new ImageIcon("resources/instructions.jpg")), SwingConstants.CENTER);
		bPanel.setBackground(new Color(0,0,128));
		bPanel.setLayout(new BorderLayout(0,0));
		bPanel.setPreferredSize(new Dimension(50,50));
		bPanel.add(back, BorderLayout.CENTER);
		
		instructionsFrame = new JFrame();
		instructionsFrame.setLayout(new BorderLayout(2,2));
		instructionsFrame.add(picturePanel, BorderLayout.CENTER);
		instructionsFrame.add(bPanel, BorderLayout.SOUTH);
		
		instructionsFrame.setUndecorated(true);
		instructionsFrame.setSize(1060,720);
		instructionsFrame.setLocationRelativeTo(null);
		instructionsFrame.setVisible(true);
		instructionsFrame.getRootPane().setBorder(border);
	}


	public void gameCredits()
	{
		disableMenuButtons();
		
		JPanel bPanel = new JPanel();
		back2 = new JButton("BACK");
		back2.addActionListener(this);
		bPanel.setLayout(new BorderLayout(0,0));
		bPanel.add(back2);
		
		creditsFrame = new JFrame();
		JScrollPane scrollPane;

		JPanel topPanel = new JPanel();
		topPanel.setLayout(new BorderLayout(0,0));
		creditsFrame.getContentPane().add(topPanel);

		Icon image = new ImageIcon("resources/CreditsFINAL.jpg");
		JLabel label = new JLabel(image);

		scrollPane = new JScrollPane();
		scrollPane.getViewport().add(label);
		topPanel.add(scrollPane, BorderLayout.CENTER);
		
		creditsFrame.setLayout(new BorderLayout(2,2));
		creditsFrame.add(topPanel, BorderLayout.CENTER);
		creditsFrame.add(bPanel, BorderLayout.SOUTH);
		
		creditsFrame.setUndecorated(true);
		creditsFrame.setSize(595,700);
		creditsFrame.setVisible(true);
		creditsFrame.setLocationRelativeTo(null);
		creditsFrame.getRootPane().setBorder(border);
	}
	
	public void createLeftPanel()
	{
		JPanel buttonsPanel;
		JPanel blankPanel;
		
		leftPanel = new JPanel();
		blankPanel = new JPanel();
		buttonsPanel = new JPanel();
		
		stop = new JButton(new ImageIcon("resources/stop.png"));
		pause = new JButton(new ImageIcon("resources/pause.png"));
		resume = new JButton(new ImageIcon("resources/play.png"));
		resume.setEnabled(false);
		stop.setEnabled(false);
		pause.setEnabled(false);
		
		buttonsPanel.setLayout(new GridLayout(3,1));	
		buttonsPanel.add(resume);
		buttonsPanel.add(pause);
		buttonsPanel.add(stop);
								
								
		blankPanel.setBackground(Color.BLACK);
		leftPanel.setLayout(new BorderLayout(0,0));
		leftPanel.setPreferredSize(new Dimension(90,90));
		leftPanel.setBackground(Color.BLACK);
		leftPanel.add(blankPanel, BorderLayout.CENTER);
		leftPanel.add(buttonsPanel, BorderLayout.SOUTH);
		
		stop.addActionListener(this);
		pause.addActionListener(this);
		resume.addActionListener(this);
	}
	
	public static void disableMenuButtons()
	{
		SidePanels.newGame.setEnabled(false);
		SidePanels.rollDice.setEnabled(false);
		SidePanels.instructions.setEnabled(false);
		SidePanels.credits.setEnabled(false);
		SidePanels.exit.setEnabled(false);
	}
	
	public static void enableMenuButtons()
	{
		SidePanels.newGame.setEnabled(true);
		SidePanels.rollDice.setEnabled(true);
		SidePanels.instructions.setEnabled(true);
		SidePanels.credits.setEnabled(true);
		SidePanels.exit.setEnabled(true);
	}

	public void resetTextFields()
	{
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
			
			disableMenuButtons();

		}else if(e.getSource() == exit) {

			int dialogResult = JOptionPane.showConfirmDialog(null, "Do You Really Wish to Quit?", "Quit", JOptionPane.YES_NO_OPTION);

			if(dialogResult == 0)
			{
				JFrame f = new JFrame();
				
				System.exit(0);
				
				f.setSize(200,200);
				f.setUndecorated(true);
				f.setLocationRelativeTo(null);
				f.getRootPane().setBorder(new LineBorder(Color.BLACK, 8, true));
				f.setContentPane(new JLabel(new ImageIcon("resources/lastFrameGames.png")));
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
			new Music("resources/RollDice.wav");

		}else if(e.getSource() == instructions) {
			gameInstructions();
			disableMenuButtons();
			
		}else if(e.getSource() == credits)
			gameCredits();
		
		else if(e.getSource() == pause) {
			JOptionPane.showMessageDialog(null, new JLabel(icon, JLabel.CENTER), "Game Paused", JOptionPane.PLAIN_MESSAGE);
			pause.setEnabled(false);
			resume.setEnabled(true);
			stop.setEnabled(true);
			disableMenuButtons();
				
		}else if(e.getSource() == resume) {
			JOptionPane.showMessageDialog(null, new JLabel(icon, JLabel.CENTER), "Game Resumed", JOptionPane.PLAIN_MESSAGE);
			resume.setEnabled(false);
			rollDice.setEnabled(true);
			stop.setEnabled(false);
			
		}else if(e.getSource() == stop) {
			int ctr = JOptionPane.showConfirmDialog(null, "  Abandon Match?", "End Game", JOptionPane.YES_NO_OPTION);
			
			if(ctr == 0)
			{
				enableMenuButtons();
				rollDice.setEnabled(false);
				resume.setEnabled(false);
				stop.setEnabled(false);
				pause.setEnabled(false);
				
				BoardPanel.tileNo[GameProper.ctrForTile1].setIcon(null);
				BoardPanel.tileNo[GameProper.ctrForTile2].setIcon(null);
								
				SetPlayerInfo.ctr = 1;
				SetPlayerInfo.disableMouseListener = 't';
				GameProper.diceNo = 0;
				GameProper.ctrForTurn = 0;
				GameProper.ctrForTile1 = 0;
				GameProper.ctrForTile2 = 0;
								
				resetTextFields();
			}	
						
		}else if(e.getSource() == back) {
			
			instructionsFrame.dispose();
			enableMenuButtons();
			rollDice.setEnabled(false);
			
		}else if(e.getSource() == back2){
			
			creditsFrame.dispose();
			enableMenuButtons();
			rollDice.setEnabled(false);
			
		}
	}
	
	
	public void itemStateChanged(ItemEvent e)
	{

			
	}

}
