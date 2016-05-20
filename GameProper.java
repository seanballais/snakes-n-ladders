import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class GameProper implements ActionListener
{
	private int diceNo;
	public static int ctrForTurn = 0, backCtr;
	private static int score1 = 0, score2 = 0;
	private static int ctrForTile1 = 0, ctrForTile2 = 0;
	public String[] jpgs = {"resources/b.png", "resources/r.png", "resources/y.png", "resources/g.png"};

	private JPanel snakePic;
	private JFrame anotherRound;
	private JButton playAgain, endGame;
	private JLabel winnerLabel, scores, title, scoreLabel;
	private JPanel[] confirmationPanels = new JPanel[3];

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
		SidePanels.currentPlayerAndTilePosition[2].setText(SetPlayerInfo.p1);

		for(int x = 0; x < 4; x++)
			SidePanels.currentPlayerAndTilePosition[x].setForeground(SetPlayerInfo.colors[SetPlayerInfo.colorIndexOfPlayers[0]]);

		BoardPanel.tileNo[0].setIcon(new ImageIcon(jpgs[SetPlayerInfo.colorIndexOfPlayers[0]]));
		BoardPanel.tileNo[0].setIcon(new ImageIcon(jpgs[SetPlayerInfo.colorIndexOfPlayers[1]]));
	}

	public void movePiece()
	{
		if((ctrForTurn % 2) == 1)
		{
			BoardPanel.tileNo[ctrForTile1].setIcon(null);

			if(ctrForTile1 == ctrForTile2)
				BoardPanel.tileNo[ctrForTile1].setIcon(new ImageIcon(jpgs[SetPlayerInfo.colorIndexOfPlayers[1]]));

			int oldTilePosition = ctrForTile1;
			ctrForTile1 = nextMove(ctrForTile1 + diceNo); // Replace 1 with diceNo
			if (oldTilePosition + diceNo != ctrForTile1) { // Replace 1 with diceNo
				JOptionPane.showMessageDialog(null, "Player 1 teleported to tile " + (ctrForTile1 + 1) + ".");
			}

			if(ctrForTile1 < 99)
				BoardPanel.tileNo[ctrForTile1].setIcon(new ImageIcon(jpgs[SetPlayerInfo.colorIndexOfPlayers[0]]));
			else
			{
				if(ctrForTile1 > 99)
				{
					ctrForTile1 -= 2*(ctrForTile1-99);
					BoardPanel.tileNo[ctrForTile1].setIcon(new ImageIcon(jpgs[SetPlayerInfo.colorIndexOfPlayers[0]]));

				}else if(ctrForTile1 == 99) {

					BoardPanel.tileNo[99].setIcon(new ImageIcon(jpgs[SetPlayerInfo.colorIndexOfPlayers[0]]));
					String s = Integer.toString(++score1);

					playAgainConfirmation(SetPlayerInfo.p1);

					SidePanels.rollDice.setEnabled(false);
					SidePanels.scoreOfPlayers[0].setText(s);

				}
			}

			String s2 = BoardPanel.tileNo[ctrForTile2].getText();
			SidePanels.currentPlayerAndTilePosition[2].setText(SetPlayerInfo.p2);
			SidePanels.currentPlayerAndTilePosition[3].setText(s2);

		}else{

			BoardPanel.tileNo[ctrForTile2].setIcon(null);

			if(ctrForTile1 == ctrForTile2)
				BoardPanel.tileNo[ctrForTile2].setIcon(new ImageIcon(jpgs[SetPlayerInfo.colorIndexOfPlayers[0]]));

			int oldTilePosition = ctrForTile2;
	    	ctrForTile2 = nextMove(ctrForTile2 + diceNo); // Replace 1 with diceNo
	        if (oldTilePosition + diceNo != ctrForTile2) { // Replace 1 with diceNo
	            JOptionPane.showMessageDialog(null, "Player 2 teleported to tile " + (ctrForTile2 + 1) + ".");
	        }

			if(ctrForTile2 < 99)
				BoardPanel.tileNo[ctrForTile2].setIcon(new ImageIcon(jpgs[SetPlayerInfo.colorIndexOfPlayers[1]]));
			else
			{
				if(ctrForTile2 > 99)
				{
					ctrForTile2 -= 2*(ctrForTile2-99);
					BoardPanel.tileNo[ctrForTile2].setIcon(new ImageIcon(jpgs[SetPlayerInfo.colorIndexOfPlayers[1]]));

				}else if(ctrForTile2 == 99) {

					BoardPanel.tileNo[99].setIcon(new ImageIcon(jpgs[SetPlayerInfo.colorIndexOfPlayers[1]]));
					String s = Integer.toString(++score2);

					playAgainConfirmation(SetPlayerInfo.p2);

					SidePanels.rollDice.setEnabled(false);
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

		winnerLabel.setFont(eras);
		scores.setFont(eras);
		scoreLabel.setFont(eras);
		winnerLabel.setForeground(orange);
		scores.setForeground(orange);
		scoreLabel.setForeground(orange);

		playAgain = new JButton("Play More");
		endGame = new JButton("End Game");
		playAgain.setFont(eras2);
		endGame.setFont(eras2);
		playAgain.addActionListener(this);
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
			anotherRound.dispose();

			SetPlayerInfo.disableMouseListener = 't';
			SetPlayerInfo.ctr = 1;
			ctrForTile1 = 0;
			ctrForTile2 = 0;
			ctrForTurn = 0;
			diceNo = 0;

			SidePanels.rollDice.setEnabled(true);
			setPieceOnBoard();

		}else if(e.getSource() == endGame) {

			title.setIcon(new ImageIcon("resources/gOver.png"));
			title.setText("");

			if(score1 > score2)
				winnerLabel.setText(SetPlayerInfo.p1 + " wins!");
			else if(score1 < score2)
				winnerLabel.setText(SetPlayerInfo.p2 + " wins!");
			else
				winnerLabel.setText("Draw Match.   Shake Hands.");

			backCtr++;
			endGame.setText("Back");
			playAgain.setVisible(false);

			if(backCtr == 2)
			{
				anotherRound.dispose();

				SetPlayerInfo.ctr = 1;
				ctrForTile1 = 0;
				ctrForTile2 = 0;
				backCtr = 0;
				score1 = 0;
				score2 = 0;
				diceNo = 0;

				SidePanels.newGame.setEnabled(true);
				SidePanels.pause.setEnabled(false);
				SidePanels.exit.setEnabled(true);
				SidePanels.credits.setEnabled(true);
				SidePanels.instructions.setEnabled(true);
			}

			for(int a = 0; a < 2; a++)
			{
				SidePanels.scoreOfPlayers[a].setText("0");
				SidePanels.nameDisplayFields[a].setText("  Player " + (a+1) + ":");
				SidePanels.currentPlayerAndTilePosition[a].setForeground(Color.WHITE);
				SidePanels.currentPlayerAndTilePosition[a+2].setForeground(Color.WHITE);
				SidePanels.currentPlayerAndTilePosition[a+2].setText("");
			}

			anotherRound.setSize(650,500);
			anotherRound.setLocationRelativeTo(null);
		}
	}

	public int nextMove(int nextTile)
	{
        int pairTile = 0;
		// Checks and moves the piece depending on the tile
		if (onLadderTile(nextTile)) {
            pairTile = getPairTile(nextTile, BoardPanel.ladderTiles);
			if (pairTile > nextTile) {
				System.out.println("Current tile (ladder): " + nextTile);
				System.out.println("Next move (ladder): " + pairTile);
				return pairTile;
			}
		} else if (onSnakeTile(nextTile)) {
            pairTile = getPairTile(nextTile, BoardPanel.snakeTiles);
			if (pairTile < nextTile) {
				System.out.println("Current tile (snake): " + nextTile);
				System.out.println("Next move (snake): " + pairTile);
				return pairTile;
			}
		}

		return nextTile;
	}

	public boolean onLadderTile(int tile)
	{
		for (int ladderCtr = 0; ladderCtr < BoardPanel.ladderTiles.length; ladderCtr++) {
            System.out.println("Value for ladder: " + BoardPanel.ladderTiles[ladderCtr]);
			// Check if the player is on a lower ladder tile
			if (tile == BoardPanel.ladderTiles[ladderCtr]) {
				return true;
			}
		}

		return false;
	}

	public int getPairTile(int tile, int[] tiles)
	{
		// Get the pair tile of the (int) tile.
		for (int pairCtr = 0; pairCtr < tiles.length; pairCtr++) {
			// Check if the player is on a lower ladder tile
			if (tiles[pairCtr] == tile) {
				if (pairCtr % 2 == 0) {
					// First element in the pair
					return tiles[pairCtr + 1];
				} else {
					// Second element in the pair
					return tiles[pairCtr - 1];
				}
			}
		}

		return tile;
	}

	public boolean onSnakeTile(int tile)
	{
		for (int ladderCtr = 0; ladderCtr < BoardPanel.snakeTiles.length; ladderCtr++) {
			// Check if the player is on a lower ladder tile
			if (tile == BoardPanel.snakeTiles[ladderCtr]) {
				return true;
			}
		}

		return false;
	}
}
