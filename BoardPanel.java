import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.BorderFactory;

public class BoardPanel extends JPanel 
{	
	public JPanel[] tiles = new JPanel[100];
	public JLabel[] tileNo = new JLabel[100];
	
	private Color paleGreen = new Color(128, 191, 0);
	private Color[] colors = {Color.RED, Color.YELLOW, Color.MAGENTA, 
							  paleGreen, Color.BLUE, Color.WHITE};
							  
	private static int ctr = 99;
	private int colorCtr = 0;	
	
	public BoardPanel() 
	{
		setLayout(new GridLayout(10,10));
		
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
	
}