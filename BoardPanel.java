import java.io.File;
import java.io.IOException;
import java.awt.Font;
import java.awt.Color;
import java.awt.Point;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.AlphaComposite;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.imageio.ImageIO;

public class BoardPanel extends JPanel
{
	private int colorCtr = 0;
	private static int ctr = 99;

	private int[][] tilePosition;
	public static int[] teleportTiles;

	public static JPanel[] tiles = new JPanel[100];
  	public static JLabel[] tileNo = new JLabel[100];

	private Color paleGreen = new Color(128, 191, 0);
	private Color[] colors = {Color.RED, Color.YELLOW, Color.MAGENTA,
								paleGreen, Color.BLUE, Color.WHITE};
	private Boolean printed = false;

	public BoardPanel(int[] objTiles)
	{
		setLayout(new GridLayout(10,10));
		this.teleportTiles = objTiles;

		tilePosition = new int[10][10];

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
	}

	public void paint(Graphics g)
	{
		super.paint(g);

		for (int objCtr = 0; objCtr < 14; objCtr++) {
			BoardPanel.tileNo[teleportTiles[objCtr]].setIcon(new ImageIcon("resources/teleportTile.png"));
		}
	}
}
