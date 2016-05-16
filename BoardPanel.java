import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.BasicStroke;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.BorderFactory;

public class BoardPanel extends JPanel
{
	public JPanel[] tiles = new JPanel[100];
	public int[] objTiles;

	private Color paleGreen = new Color(128, 191, 0);
	private Color[] colors = {Color.RED, Color.YELLOW, Color.MAGENTA,
							  paleGreen, Color.BLUE, Color.WHITE};

  	public static JLabel[] tileNo = new JLabel[100];
	private static int ctr = 99;
	private int colorCtr = 0;

	public BoardPanel(int[] objTiles)
	{
		setLayout(new GridLayout(10,10));
		this.objTiles = objTiles;

		for(int x = 1; x <= 10; x++)
		{
			for(int y = 0; y < 10; y++)
			{
				String s = Integer.toString(ctr+1);

				tileNo[ctr] = new JLabel(s);
				tileNo[ctr].setFont(new Font("Arial", Font.BOLD, 15));

				colorCtr = colorCtr % colors.length;

				tiles[ctr] = new JPanel();
				tiles[ctr].setBorder(BorderFactory.createRaisedBevelBorder());
				tiles[ctr].setBackground(colors[colorCtr]);
				tiles[ctr].add(tileNo[ctr]);

				add(tiles[ctr]);

				if(y < 9)
				{
					if((x % 2) == 0)
						ctr++;
					else
						ctr--;
				}

				colorCtr++;
			}
			ctr -= 10;
		}
	}

	public void paint(Graphics g)
	{
		super.paint(g);

        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(3));

		for (int i = 0; i < 4; i++) {
			// Paint ladders
			g2.drawLine( // First line
				(int) getOrigin(objTiles[i]).x - 15,
				(int) getOrigin(objTiles[i]).y + 15,
				(int) getOrigin(objTiles[i + 1]).x - 15,
				(int) getOrigin(objTiles[i + 1]).y + 15
			);

			g2.drawLine( // Second line
				(int) getOrigin(objTiles[i]).x + 15,
				(int) getOrigin(objTiles[i]).y - 15,
				(int) getOrigin(objTiles[i + 1]).x + 15,
				(int) getOrigin(objTiles[i + 1]).y - 15
			);
		}

		g.setColor(Color.YELLOW);
		for (int i = 4; i < 8; i++) {
			// Paint ladders
			g2.drawLine(
				(int) getOrigin(objTiles[i]).x,
				(int) getOrigin(objTiles[i]).y,
				(int) getOrigin(objTiles[i + 1]).x,
				(int) getOrigin(objTiles[i + 1]).y
			);
		}
	}

	private Point getOrigin(int tile)
	{
		// Get the origin coordinate of a tile
		// 100 - tile to correct the associated tiles
		int xOrigin = ((tile) % 10) * 62; // Tile width is 62 pixels.
		int yOrigin = ((Integer.parseInt(Integer.toString(tile).substring(0, 1)))) * 62;

		return new Point(xOrigin + 31, yOrigin + 31);
	}
}
