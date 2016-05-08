import java.util.Random;
import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;

public class SidePanel extends JPanel implements ActionListener
{
	private JButton rollDice;
	private JLabel gameName;
	private JTextField p1Name, p2Name, playerTurn, position1, position2;
	private JPanel[] diceScoreTitlePlayer = new JPanel[3];

	Icon[] diceImages = new ImageIcon[6];
	private String[] diceString = {"dice1.jpg", "dice2.jpg", "dice3.jpg",
						   "dice4.jpg", "dice5.jpg", "dice6.jpg"};


	public SidePanel(String player1, String player2)
	{
		for(int x = 0; x < 3; x++) {
			diceScoreTitlePlayer[x] = new JPanel();
		}

		gameTitlePanel();
		playersPanel(player1, player2);
		dicePanel();

		add(diceScoreTitlePlayer[0]);
		add(diceScoreTitlePlayer[1]);
		add(diceScoreTitlePlayer[2]);
	}

	public void gameTitlePanel()
	{
		diceScoreTitlePlayer[0].setLayout(new BorderLayout(0,0));

		gameName = new JLabel("Snakes" + "\n" + "&" + "\n" + "Ladders");
		gameName.setFont(new Font("AR JULIAN", Font.PLAIN, 40));
		gameName.setHorizontalAlignment(SwingConstants.CENTER);
		gameName.setForeground(Color.YELLOW);

		diceScoreTitlePlayer[0].setBackground(Color.BLACK);
		diceScoreTitlePlayer[0].add(gameName, BorderLayout.CENTER);
	}

	public void playersPanel(String player1, String player2)
	{
		diceScoreTitlePlayer[1].setLayout(new GridLayout(5,1));

		p1Name = new JTextField("Player 1 : " + player1, 20);
		p1Name.setFont(new Font("Britannic Bold", Font.PLAIN, 25));
		p1Name.setHorizontalAlignment(SwingConstants.CENTER);
		p1Name.setBackground(Color.BLACK);
		p1Name.setForeground(Color.YELLOW);
		p1Name.setEditable(false);

		position1 = new JTextField("Tile # ", 10);
		position1.setFont(new Font("Britannic Bold", Font.PLAIN, 25));
		position1.setHorizontalAlignment(SwingConstants.CENTER);
		position1.setBackground(Color.BLACK);
		position1.setForeground(Color.YELLOW);
		position1.setEditable(false);

		p2Name = new JTextField("Player 2 : " + player2, 20);
		p2Name.setFont(new Font("Britannic Bold", Font.PLAIN, 25));
		p2Name.setHorizontalAlignment(SwingConstants.CENTER);
		p2Name.setBackground(Color.BLACK);
		p2Name.setForeground(Color.YELLOW);
		p2Name.setEditable(false);

		position2 = new JTextField("Tile # ", 10);
		position2.setFont(new Font("Britannic Bold", Font.PLAIN, 25));
		position2.setHorizontalAlignment(SwingConstants.CENTER);
		position2.setBackground(Color.BLACK);
		position2.setForeground(Color.YELLOW);
		position2.setEditable(false);

		playerTurn = new JTextField("PLAYER 1's TURN", 10);
		playerTurn.setFont(new Font("AR JULIAN", Font.PLAIN, 30));
		playerTurn.setHorizontalAlignment(SwingConstants.CENTER);
		playerTurn.setForeground(Color.BLACK);
		playerTurn.setEditable(false);

		diceScoreTitlePlayer[1].add(p1Name);
		diceScoreTitlePlayer[1].add(p2Name);
		diceScoreTitlePlayer[1].add(position1);
		diceScoreTitlePlayer[1].add(position2);
	}

	public void dicePanel()
	{
		diceScoreTitlePlayer[2].setLayout(new BorderLayout(5,5));

		Icon icon = new ImageIcon("resources/rolldice.jpg");

		rollDice = new JButton("ROLL", icon);
		rollDice.setFont(new Font("Berlin Sans FB", Font.BOLD, 30));
		rollDice.setBackground(Color.BLACK);
		rollDice.setForeground(Color.YELLOW);
		rollDice.setPreferredSize(new Dimension(90,60));

		diceScoreTitlePlayer[2].setBackground(Color.BLACK);
		diceScoreTitlePlayer[2].add(rollDice, BorderLayout.SOUTH);

		for(int x = 0; x < diceImages.length; x++)
			diceImages[x] = new ImageIcon(diceString[x]);

		rollDice.addActionListener(this);

	}

	public void actionPerformed(ActionEvent evt)
	{
		Random randomizer = new Random();

		int diceNo = randomizer.nextInt(diceImages.length);
		JLabel result = new JLabel(diceImages[diceNo]);
		diceScoreTitlePlayer[2].add(result, BorderLayout.CENTER);
	}

}
