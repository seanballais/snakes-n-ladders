import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.BasicStroke;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.BorderFactory;
import javax.swing.Timer;

public class BoardPanel extends JPanel
{
	public JPanel[] tiles = new JPanel[100];

	private Color paleGreen = new Color(128, 191, 0);
	private Color[] colors = {Color.RED, Color.YELLOW, Color.MAGENTA,
							  paleGreen, Color.BLUE, Color.WHITE};
	private int[][] tilePosition;

	public static int[] objTiles;
	public static int[] ladderTiles;
	public static int[] snakeTiles;
  	public static JLabel[] tileNo = new JLabel[100];
	private static int ctr = 99;
	private int colorCtr = 0;

	public BoardPanel(int[] objTiles)
	{
		setLayout(new GridLayout(10,10));
		this.objTiles = objTiles;

		tilePosition = new int[10][10];

		this.ladderTiles = new int[6];
		this.snakeTiles = new int[6];
		for (int i = 0; i < 6; i++) {
			this.ladderTiles[i] = this.objTiles[i];
			this.snakeTiles[i] = this.objTiles[i + 6];
		}

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

				// Add a position array to easily determine the XY coordinates
				tilePosition[x - 1][y] = ctr;

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

		Timer timer = new Timer(16,
			new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent ev)
				{
					repaint();
				}
			}
		);
		timer.start();
	}

	public void paint(Graphics g)
	{
		super.paint(g);

        int ladderLine1_x = 0;
        int ladderLine1_y = 0;
        int ladderLine2_x = 0;
        int ladderLine2_y = 0;
		int snakeLine1_x = 0;
        int snakeLine1_y = 0;
        int snakeLine2_x = 0;
        int snakeLine2_y = 0;

        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(5));

		for (int objCtr = 0; objCtr < 5; objCtr += 2) {
			ladderLine1_x = (getTilePoint(this.ladderTiles[objCtr]).x * 62) + 2;
	        ladderLine1_y = (getTilePoint(this.ladderTiles[objCtr]).y * 54) + 2;
	        ladderLine2_x = (getTilePoint(this.ladderTiles[objCtr + 1]).x * 62) + 2;
	        ladderLine2_y = (getTilePoint(this.ladderTiles[objCtr + 1]).y * 54) + 2;
			g2.setColor(Color.BLACK);
			g2.drawLine(
				ladderLine1_x + 31,
				ladderLine1_y + 27,
				ladderLine2_x + 31,
				ladderLine2_y + 27
			);

			snakeLine1_x = (getTilePoint(this.snakeTiles[objCtr]).x * 62) + 2;
	        snakeLine1_y = (getTilePoint(this.snakeTiles[objCtr]).y * 54) + 2;
	        snakeLine2_x = (getTilePoint(this.snakeTiles[objCtr + 1]).x * 62) + 2;
	        snakeLine2_y = (getTilePoint(this.snakeTiles[objCtr + 1]).y * 54) + 2;
			g2.setColor(Color.YELLOW);
			g2.drawLine(
				snakeLine1_x + 31,
				snakeLine1_y + 27,
				snakeLine2_x + 31,
				snakeLine2_y + 27
			);
		}
	}

    private Point getTilePoint(int tile)
    {
		for (int y = 0; y < 10; y++) {
			for (int x = 0; x < 10; x++) {
				if (tilePosition[y][x] == tile) {
					return new Point(x, y);
				}
			}
		}

        return new Point(-1, -1);
    }
}
