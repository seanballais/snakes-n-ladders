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

			ctrForTile1 += diceNo;
			BoardPanel.tileNo[ctrForTile1].setIcon(new ImageIcon(jpgs[SetPlayerInfo.colorIndexOfPlayers[0]]));

		}else{

			BoardPanel.tileNo[ctrForTile2].setIcon(null);

			if(ctrForTile1 == ctrForTile2)
				BoardPanel.tileNo[ctrForTile2].setIcon(new ImageIcon(jpgs[SetPlayerInfo.colorIndexOfPlayers[0]]));

			ctrForTile2 += diceNo;
			BoardPanel.tileNo[ctrForTile2].setIcon(new ImageIcon(jpgs[SetPlayerInfo.colorIndexOfPlayers[1]]));

		}

	}
}
