import java.awt.Font;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

public class GameProper implements ActionListener
{
	public static int diceNo;
	public static int score1 = 0, score2 = 0;
	public static int ctrForTile1 = 0, ctrForTile2 = 0;
	public static int ctrForTurn = 0, backCtr;

	private JPanel snakePic;
	private JFrame anotherRound;
	private JButton playAgain, endGame;
	private JLabel winnerLabel, scores, title, scoreLabel;

	private JPanel[] confirmationPanels = new JPanel[3];
	public static String[] jpgs = {"resources/b.png", "resources/r.png", "resources/y.png", "resources/g.png"};

	public GameProper()
	{
		//blank constructor for one call in one class
	}

	public GameProper(int diceResult)
	{
		ctrForTurn++;
		diceNo = diceResult+1;

		movePiece();
	}

	public void setPieceOnBoard()
	{
		BoardPanel.tileNo[0].setIcon(new ImageIcon(jpgs[SetPlayerInfo.colorIndexOfPlayers[0]]));
		BoardPanel.tileNo[0].setIcon(new ImageIcon(jpgs[SetPlayerInfo.colorIndexOfPlayers[1]]));
	}

	public void movePiece()
	{
		if((ctrForTurn % 2) == 1)
		{
			if (onTeleportTile(ctrForTile1)) {
				BoardPanel.tileNo[ctrForTile1].setIcon(new ImageIcon("resources/teleportTile.png"));
			} else {
				BoardPanel.tileNo[ctrForTile1].setIcon(null);
			}

			if(ctrForTile1 == ctrForTile2)
				BoardPanel.tileNo[ctrForTile1].setIcon(new ImageIcon(jpgs[SetPlayerInfo.colorIndexOfPlayers[1]]));


			int oldTilePosition = ctrForTile1;
			ctrForTile1 = nextMove(ctrForTile1 + diceNo); // Replace 1 with diceNo

			if(ctrForTile1 < 99)
				BoardPanel.tileNo[ctrForTile1].setIcon(new ImageIcon(jpgs[SetPlayerInfo.colorIndexOfPlayers[0]]));
			if (oldTilePosition + diceNo != ctrForTile1) // Replace 1 with diceNo
				JOptionPane.showMessageDialog(null, SetPlayerInfo.p1 + " teleported to tile " + (ctrForTile1 + 1) + ".");

			else
			{
				if(ctrForTile1 > 99)
				{
					int oldTilePosition1 = ctrForTile1;
					ctrForTile1 = nextMove(99 - (ctrForTile1 - 99));

					if (oldTilePosition1 + diceNo != ctrForTile1)
						JOptionPane.showMessageDialog(null, SetPlayerInfo.p1 + " teleported to tile " + (ctrForTile1 + 1) + ".");

					BoardPanel.tileNo[ctrForTile1].setIcon(new ImageIcon(jpgs[SetPlayerInfo.colorIndexOfPlayers[0]]));

				}else if(ctrForTile1 == 99) {

					BoardPanel.tileNo[99].setIcon(new ImageIcon(jpgs[SetPlayerInfo.colorIndexOfPlayers[0]]));
					String s = Integer.toString(++score1);

					playAgainConfirmation(SetPlayerInfo.p1);

					SidePanels.scoreOfPlayers[0].setText(s);

				}
			}

			String s2 = BoardPanel.tileNo[ctrForTile2].getText();
			SidePanels.currentPlayerAndTilePosition[2].setText(SetPlayerInfo.p2);
			SidePanels.currentPlayerAndTilePosition[3].setText(s2);

		}else{
			if (onTeleportTile(ctrForTile2)) {
				BoardPanel.tileNo[ctrForTile2].setIcon(new ImageIcon("resources/teleportTile.png"));
			} else {
				BoardPanel.tileNo[ctrForTile2].setIcon(null);
			}

			if(ctrForTile1 == ctrForTile2)
				BoardPanel.tileNo[ctrForTile2].setIcon(new ImageIcon(jpgs[SetPlayerInfo.colorIndexOfPlayers[0]]));

			int oldTilePosition = ctrForTile2;
	    	ctrForTile2 = nextMove(ctrForTile2 + diceNo); // Replace 1 with diceNo

			if(ctrForTile2 < 99)
				BoardPanel.tileNo[ctrForTile2].setIcon(new ImageIcon(jpgs[SetPlayerInfo.colorIndexOfPlayers[1]]));
	        if (oldTilePosition + diceNo != ctrForTile2) // Replace 1 with diceNo
	            JOptionPane.showMessageDialog(null, SetPlayerInfo.p2 + " teleported to tile " + (ctrForTile2 + 1) + ".");

			else
			{
				if(ctrForTile2 > 99)
				{
					int oldTilePosition2 = ctrForTile2;
					ctrForTile2 = nextMove(99 - (ctrForTile2 - 99));

					if (oldTilePosition2 + diceNo != ctrForTile2)
						JOptionPane.showMessageDialog(null, SetPlayerInfo.p2 + " teleported to tile " + (ctrForTile2 + 1) + ".");

					BoardPanel.tileNo[ctrForTile2].setIcon(new ImageIcon(jpgs[SetPlayerInfo.colorIndexOfPlayers[1]]));

				}else if(ctrForTile2 == 99) {

					BoardPanel.tileNo[99].setIcon(new ImageIcon(jpgs[SetPlayerInfo.colorIndexOfPlayers[1]]));
					String s = Integer.toString(++score2);

					playAgainConfirmation(SetPlayerInfo.p2);

					SidePanels.scoreOfPlayers[1].setText(s);

				}
			}

			String s1 = BoardPanel.tileNo[ctrForTile1].getText();
			SidePanels.currentPlayerAndTilePosition[2].setText(SetPlayerInfo.p1);
			SidePanels.currentPlayerAndTilePosition[3].setText(s1);
		}
	}

	public void playAgainConfirmation(String w)
	{
		JPanel labelPanels = new JPanel();
		SidePanels.triggerEvent = 'f';

		for(int x = 0 ; x < 3 ; x++)
		{
			confirmationPanels[x] = new JPanel();

			if(x < 2)
			{
				confirmationPanels[x].setBackground(new Color(0,0,128));
				confirmationPanels[x].setBorder(SidePanels.border);
			}
		}

		confirmationPanels[2].setBackground(Color.BLACK);

		Color orange = new Color(255,128,0);
		Font eras = new Font("Eras Bold ITC", Font.BOLD, 30);
		Font eras2 = new Font("Eras Bold ITC", Font.BOLD, 20);

		title = new JLabel("RESULTS", SwingConstants.CENTER);
		title.setFont(new Font("Eras Bold ITC", Font.BOLD, 40));
		title.setForeground(orange);

		winnerLabel = new JLabel(w + " Gained Point", SwingConstants.CENTER);
		scores = new JLabel(String.format("%d       ::       %d", score1, score2), SwingConstants.CENTER);
		scoreLabel = new JLabel(String.format("%s            %s", SetPlayerInfo.p1, SetPlayerInfo.p2), SwingConstants.CENTER);

		scores.setFont(eras);
		scoreLabel.setFont(eras);
		winnerLabel.setFont(eras);

		scores.setForeground(orange);
		scoreLabel.setForeground(orange);
		winnerLabel.setForeground(orange);

		playAgain = new JButton("Play More");
		playAgain.setFont(eras2);
		playAgain.addActionListener(this);

		endGame = new JButton("End Game");
		endGame.setFont(eras2);
		endGame.addActionListener(this);

		confirmationPanels[0].add(title, BorderLayout.CENTER);

		confirmationPanels[1].setLayout(new GridLayout(3,1));
		confirmationPanels[1].add(winnerLabel);
		confirmationPanels[1].add(scoreLabel);
		confirmationPanels[1].add(scores);

		confirmationPanels[2].setLayout(new BorderLayout(2,2));
		confirmationPanels[2].add(playAgain, BorderLayout.WEST);
		confirmationPanels[2].add(endGame, BorderLayout.EAST);

		anotherRound = new JFrame();
		anotherRound.setLayout(new BorderLayout(5,5));
		anotherRound.add(confirmationPanels[0], BorderLayout.NORTH);
		anotherRound.add(confirmationPanels[1], BorderLayout.CENTER);
		anotherRound.add(confirmationPanels[2], BorderLayout.SOUTH);

		anotherRound.setUndecorated(true);
		anotherRound.setSize(550,400);
		anotherRound.setVisible(true);
		anotherRound.getRootPane().setBorder(SidePanels.border);
		anotherRound.setLocationRelativeTo(null);
	}

	public void actionPerformed(ActionEvent e)
	{
		BoardPanel.tileNo[ctrForTile1].setIcon(null);
		BoardPanel.tileNo[ctrForTile2].setIcon(null);

		if(e.getSource() == playAgain)
		{
			setPieceOnBoard();
			anotherRound.dispose();

			SidePanels.currentPlayerAndTilePosition[2].setText(SetPlayerInfo.p1);
			SidePanels.currentPlayerAndTilePosition[3].setText("1");
			SidePanels.triggerEvent = 't';
			SidePanels.newGame.setEnabled(true);
			SidePanels.backToMenu.setEnabled(true);

			SetPlayerInfo.disableMouseListener = 't';
			SetPlayerInfo.ctr = 1;
			ctrForTile1 = 0;
			ctrForTile2 = 0;
			ctrForTurn = 0;
			diceNo = 0;

		}else if(e.getSource() == endGame) {

			title.setIcon(new ImageIcon("resources/gOver.png"));
			title.setText("");

			if(score1 > score2)
				winnerLabel.setText(SetPlayerInfo.p1 + " wins!");
			else if(score1 < score2)
				winnerLabel.setText(SetPlayerInfo.p2 + " wins!");
			else
				winnerLabel.setText("Draw Match. Shake Hands.");

			backCtr++;
			endGame.setText("Back");
			playAgain.setVisible(false);

			if(backCtr == 2)
			{
				anotherRound.dispose();

				SidePanels.diceResult.setIcon(null);
				SidePanels.triggerEvent = '\0';

				SetPlayerInfo.ctr = 1;
				ctrForTile1 = 0;
				ctrForTile2 = 0;
				backCtr = 0;
				score1 = 0;
				score2 = 0;
				diceNo = 0;

				for(int a = 0; a < 2; a++)
				{
					SidePanels.scoreOfPlayers[a].setText("0");
					SidePanels.nameDisplayFields[a].setText("  Player " + (a+1) + ":");
					SidePanels.currentPlayerAndTilePosition[a].setForeground(Color.WHITE);
					SidePanels.currentPlayerAndTilePosition[a+2].setForeground(Color.WHITE);
					SidePanels.currentPlayerAndTilePosition[a+2].setText("");
				}

			}

			anotherRound.setSize(650,500);
			anotherRound.setLocationRelativeTo(null);
		}
	}

	private int nextMove(int nextTile)
	{
		if (onTeleportTile(nextTile)) {
            return randomTile(nextTile);
		}

		return nextTile;
	}

	private boolean onTeleportTile(int tile)
	{
		for (int tileCtr = 0; tileCtr < BoardPanel.teleportTiles.length; tileCtr++) {
			if (tile == BoardPanel.teleportTiles[tileCtr]) {
				return true;
			}
		}

		return false;
	}

	private int randomTile(int tile)
	{
		Random rand = new Random();
		int nextTile = 0;
		while (BoardPanel.teleportTiles[(nextTile = rand.nextInt(10))] == tile);

		return BoardPanel.teleportTiles[nextTile];
	}
}
