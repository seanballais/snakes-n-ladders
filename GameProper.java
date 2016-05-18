import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class GameProper
{
	public String[] jpgs = {"resources/b.jpg", "resources/r.jpg", "resources/y.jpg", "resources/g.jpg"};
	private JLabel[] sameTileOfPiecesJpg = new JLabel[2];

	private static int ctrForTurn = 0;
	private static int ctrForTile1 = 0, ctrForTile2 = 0;
	private static int diceNo;

	public GameProper()
	{
		//blank constructor for first instantiation on one class
	}

	public GameProper(int diceResult)
	{
		System.out.println("DICE THROW " + (ctrForTurn+1) + "...Finished");

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
			BoardPanel.tileNo[ctrForTile1].setIcon(null);

			if(ctrForTile1 == ctrForTile2)
				BoardPanel.tileNo[ctrForTile1].setIcon(new ImageIcon(jpgs[SetPlayerInfo.colorIndexOfPlayers[1]]));

            int oldTilePosition = ctrForTile1;
			ctrForTile1 = nextMove(ctrForTile1 + diceNo); // Replace 1 with diceNo
            if (oldTilePosition + diceNo != ctrForTile1) { // Replace 1 with diceNo
                JOptionPane.showMessageDialog(null, "Player 1 jumped to tile " + (ctrForTile1 + 1) + ".");
            }
            BoardPanel.tileNo[ctrForTile1].setIcon(new ImageIcon(jpgs[SetPlayerInfo.colorIndexOfPlayers[0]]));
		} else {
			BoardPanel.tileNo[ctrForTile2].setIcon(null);

			if(ctrForTile1 == ctrForTile2)
				BoardPanel.tileNo[ctrForTile2].setIcon(new ImageIcon(jpgs[SetPlayerInfo.colorIndexOfPlayers[0]]));


            int oldTilePosition = ctrForTile2;
    		ctrForTile2 = nextMove(ctrForTile2 + diceNo); // Replace 1 with diceNo
            if (oldTilePosition + diceNo != ctrForTile2) { // Replace 1 with diceNo
                JOptionPane.showMessageDialog(null, "Player 2 jumped to tile " + (ctrForTile2 + 1) + ".");
            }
			BoardPanel.tileNo[ctrForTile2].setIcon(new ImageIcon(jpgs[SetPlayerInfo.colorIndexOfPlayers[1]]));
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
