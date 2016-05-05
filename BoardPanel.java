import java.awt.Color;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.BorderFactory;

public class BoardPanel extends JPanel
{
	private JPanel[] tiles = new JPanel[100];
	private JLabel[] tileNo = new JLabel[100];
	private Color[] colors = {
		Color.RED,
		Color.YELLOW,
		Color.MAGENTA,
		new Color(128, 191, 0),
		Color.BLUE,
		Color.WHITE
	};

	public BoardPanel()
	{
		int ctr = 99, colorCtr = 0;
		for(int x = 0; x < 10; x++) {
			for(int y = 0; y < 10; y++) {
				String s = Integer.toString(ctr);

				tileNo[ctr] = new JLabel(s);
				tileNo[ctr].setFont(new Font("Arial", Font.BOLD, 15));

				colorCtr = colorCtr % colors.length;

				tiles[ctr] = new JPanel();
				tiles[ctr].setBorder(BorderFactory.createRaisedBevelBorder());
				tiles[ctr].setBackground(colors[colorCtr]);
				tiles[ctr].add(tileNo[ctr]);

				add(tiles[ctr]);

				ctr--;
				colorCtr++;
			}
		}
	}
}
