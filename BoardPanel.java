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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.AffineTransformOp;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.Timer;
import javax.imageio.ImageIO;

public class BoardPanel extends JPanel
{
	private int colorCtr = 0;
	private static int ctr = 99;

	private int[][] tilePosition;
	public static int[] objTiles;
	public static int[] ladderTiles;
	public static int[] snakeTiles;

	public static JPanel[] tiles = new JPanel[100];
  	public static JLabel[] tileNo = new JLabel[100];

	private Color paleGreen = new Color(128, 191, 0);
	private Color[] colors = {Color.RED, Color.YELLOW, Color.MAGENTA,
								paleGreen, Color.BLUE, Color.WHITE};
	private Boolean printed = false;

	public BoardPanel(int[] objTiles)
	{
		setLayout(new GridLayout(10,10));
		this.objTiles = objTiles;

		tilePosition = new int[10][10];

		this.ladderTiles = new int[10];
		this.snakeTiles = new int[10];
		for (int i = 0; i < 10; i++) {
			this.ladderTiles[i] = this.objTiles[i];
			this.snakeTiles[i] = this.objTiles[i + 10];
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

		Point ladderLine1 = new Point(0, 0);
		Point ladderLine2 = new Point(0, 0);
		Point snakeLine1 = new Point(0, 0);
		Point snakeLine2 = new Point(0, 0);

        Graphics2D g2 = (Graphics2D) g;
		BufferedImage ladderImage = null;
		try {
			ladderImage = ImageIO.read(new File("resources/ladder.png"));
		} catch (IOException e) {
			System.out.println("File IO error.");
		}

		for (int objCtr = 0; objCtr < 1; objCtr += 2) {
			g2.setStroke(new BasicStroke(5));
			ladderLine1.x = getTilePoint(this.ladderTiles[objCtr]).x * 57;
	        ladderLine1.y = getTilePoint(this.ladderTiles[objCtr]).y * 53;
	        ladderLine2.x = getTilePoint(this.ladderTiles[objCtr + 1]).x * 57;
	        ladderLine2.y = getTilePoint(this.ladderTiles[objCtr + 1]).y * 53;

			snakeLine1.x = getTilePoint(this.snakeTiles[objCtr]).x * 57;
	        snakeLine1.y = getTilePoint(this.snakeTiles[objCtr]).y * 53;
	        snakeLine2.x = getTilePoint(this.snakeTiles[objCtr + 1]).x * 57;
	        snakeLine2.y = getTilePoint(this.snakeTiles[objCtr + 1]).y * 53;

			int ladderAngle = (int) getAngleFromPoint(ladderLine1, ladderLine2);
			int snakeAngle = (int) getAngleFromPoint(snakeLine1, snakeLine2);
			int ladderDistance = (int) distance(ladderLine1, ladderLine2);
			int snakeDistane = (int) distance(ladderLine2, ladderLine1);

			ladderImage = resizeImage(
				ladderImage,
				ladderImage.getWidth(),
				ladderDistance
			);

			ladderImage = rotate(ladderImage, ladderAngle);

			if (ladderLine1.y < ladderLine2.y) {
				g2.drawImage(ladderImage, ladderLine1.x, ladderLine1.y, null);
			} else if (ladderLine2.y < ladderLine1.y) {
				g2.drawImage(ladderImage, ladderLine2.x, ladderLine2.y, null);
			}
		}
	}


	private BufferedImage rotate(BufferedImage img, int angle)
	{
        int w = img.getWidth();
        int h = img.getHeight();

		BufferedImage dimg = dimg = new BufferedImage(w, h, img.getType());
        Graphics2D g = dimg.createGraphics();

		g.rotate(Math.toRadians(angle), w/2, h/2);
        g.drawImage(img, null, 0, 0);
        return dimg;
    }

	private BufferedImage resizeImage(final Image image, int width, int height)
	{
        final BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        final Graphics2D graphics2D = bufferedImage.createGraphics();
        graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics2D.setRenderingHint(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.drawImage(image, 0, 0, width, height, null);
        graphics2D.dispose();

        return bufferedImage;
    }

	private double getAngleFromPoint(Point firstPoint, Point secondPoint)
	{
    	if (secondPoint.x > firstPoint.x) { //above 0 to 180 degrees
        	return (Math.atan2((secondPoint.x - firstPoint.x), (firstPoint.y - secondPoint.y)) * 180 / Math.PI);
    	} else if (secondPoint.x < firstPoint.x) { //above 180 degrees to 360/0
        	return 360 - (Math.atan2((firstPoint.x - secondPoint.x), (firstPoint.y - secondPoint.y)) * 180 / Math.PI);
    	}

    	return Math.atan2(0 ,0);
	}

	private double distance(Point firstPoint, Point secondPoint)
	{
		int xDistance = 0;
		int yDistance = 0;

		if (firstPoint.x > secondPoint.x) {
			xDistance = (int) Math.pow(firstPoint.x - secondPoint.x, 2);
		} else if (firstPoint.x < secondPoint.x) {
			xDistance = (int) Math.pow(secondPoint.x - firstPoint.x, 2);
		}

		if (firstPoint.y > secondPoint.y) {
			yDistance = (int) Math.pow(firstPoint.y - secondPoint.y, 2);
		} else if (firstPoint.y < secondPoint.y) {
			yDistance = (int) Math.pow(secondPoint.y - firstPoint.y, 2);
		}

		return Math.sqrt(xDistance + yDistance);
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
