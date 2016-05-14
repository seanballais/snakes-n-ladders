import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class SetPlayerInfo extends JFrame
{
	public String p1, p2;
	private JPanel buttonHolder;
	private JButton game;

	private JPanel[] imgPanel = new JPanel[2];
	private JButton[] selectColor  = new JButton[2];

	public Icon[] pieceImages = new Icon[4];
	public JLabel[] pieceImgHolder = new JLabel[4];
	public String[] pieceImgNames = {"resources/bluePiece.jpg", "resources/redPiece.jpg", "resources/yellowPiece.jpg", "resources/greenPiece.jpg"};

	public Icon[] charImages = new Icon[4];
	public JLabel[] charImgHolder = new JLabel[4];
	public String[] charImgNames = {"resources/b.jpg", "resources/r.jpg", "resources/y.jpg", "resources/g.jpg"};

	private Font eras = new Font("Eras Bold ITC", Font.ITALIC + Font.BOLD, 20);

	public SetPlayerInfo()
	{
		setLayout(new BorderLayout(2,2));

		UIManager.put("OptionPane.messageFont", new Font("Britannic Bold", Font.PLAIN, 16));
		UIManager.put("OptionPane.buttonFont", new Font("Times New Roman", Font.BOLD, 16));
		UIManager.put("OptionPane.foreground", Color.BLACK);

		p1 = JOptionPane.showInputDialog("PLAYER 1 : ");
		p2 = JOptionPane.showInputDialog("PLAYER 2 : ");

		createPieceImages();
		createCharacterImages();

		add(imgPanel[0], BorderLayout.CENTER);
		add(imgPanel[1], BorderLayout.EAST);
	}

	public void createPieceImages()
	{
		imgPanel[0] = new JPanel();
		imgPanel[0].setLayout(new GridLayout(2,2));
		imgPanel[0].setBackground(Color.BLACK);

		for(int x = 0; x < 4; x++)
		{
			pieceImages[x] = new ImageIcon(pieceImgNames[x]);
			pieceImgHolder[x] = new JLabel(pieceImages[x]);
			imgPanel[0].add(pieceImgHolder[x]);
		}
	}


	public void createCharacterImages()
	{
		imgPanel[1] = new JPanel();
		imgPanel[1].setLayout(new GridLayout(2,2));
		imgPanel[1].setBackground(Color.YELLOW);

		for(int a = 0; a < 4; a++)
		{
			charImages[a] = new ImageIcon(charImgNames[a]);
			charImgHolder[a] = new JLabel(charImages[a]);
			imgPanel[1].add(charImgHolder[a]);
		}
	}

	public void createButtons()
	{
		JPanel slctClr;

		buttonHolder = new JPanel();
		buttonHolder.setLayout(new GridLayout(2,1));

		slctClr = new JPanel();
		slctClr.setLayout(new GridLayout(1,2));

		for(int i = 0; i < 2; i++)
		{
			selectColor[i] = new JButton("Select Color");
			selectColor[i].setHorizontalAlignment(SwingConstants.CENTER);
			slctClr.add(selectColor[i]);
		}




	}

}
