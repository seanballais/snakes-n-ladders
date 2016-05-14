import java.awt.Font;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.event.*;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

public class ChoosePieceColorFrame extends JFrame implements ActionListener
{
	private JButton game;
	public static String p1, p2;

	private Icon[] pieceIconImages = new Icon[4];
	private JButton[] pickColorButtons = new JButton[2];
	private JButton[] confirmColorButtons = new JButton[2];
	private JPanel[] pieceImagePanels = new JPanel[4];
	private JLabel[] pieceImageLabels = new JLabel[4];
	private JTextField[] playerAndColorText = new JTextField[2];

	private static int[] ctr = new int[2];
	private Color[] colorPicked = new Color[2];
	private Color[] colors = {
		Color.BLUE,
		Color.RED,
		Color.YELLOW,
		Color.GREEN
	};
	private String[] pieceImageNames = {
		"resources/bluePiece.jpg",
		"resources/redPiece.jpg",
		"resources/yellowPiece.jpg",
		"resources/greenPiece.jpg"
	};
	private Font eras = new Font("Eras Bold ITC", Font.ITALIC + Font.BOLD, 20);


	public ChoosePieceColorFrame()
	{
		super("Choose Color of Piece");
		setLayout(new BorderLayout(0,0));

		UIManager.put("OptionPane.messageFont", new Font("Britannic Bold", Font.PLAIN, 16));
		UIManager.put("OptionPane.buttonFont", new Font("Times New Roman", Font.BOLD, 16));
		UIManager.put("OptionPane.foreground", Color.BLACK);
		//UIManager.put("Button.disabledText", Color.DARK_GRAY);

		System.out.println("Play Clicked.\n\nProcessing Player Input...");
		p1 = JOptionPane.showInputDialog("PLAYER 1: ");
		p2 = JOptionPane.showInputDialog("PLAYER 2: ");

		JPanel panelForImages = new JPanel();
		JPanel panelForButtons = new JPanel();

		System.out.println("Proceeding to Pick Color Frame...");
		panelForImages.setLayout(new GridLayout(2,2));

		for(int x = 0; x < 4; x++)
		{
			pieceIconImages[x] = new ImageIcon(pieceImageNames[x]);
			pieceImageLabels[x] = new JLabel(pieceIconImages[x]);
			pieceImagePanels[x] = new JPanel();
			pieceImagePanels[x].add(pieceImageLabels[x]);
			pieceImagePanels[x].setBackground(Color.DARK_GRAY);
			panelForImages.add(pieceImagePanels[x]);
		}

		for(int y = 0; y < 2; y++)
		{
			playerAndColorText[y] = new JTextField(String.format("PLAYER %d Color : None", y+1));
			playerAndColorText[y].setFont(eras);
			playerAndColorText[y].setHorizontalAlignment(SwingConstants.CENTER);
			playerAndColorText[y].setBackground(Color.BLACK);
			playerAndColorText[y].setForeground(Color.WHITE);
			playerAndColorText[y].setEditable(false);
			playerAndColorText[y].setVisible(true);

			pickColorButtons[y] = new JButton("Select Color");
			pickColorButtons[y].setFont(eras);
			pickColorButtons[y].setEnabled(true);
			pickColorButtons[y].setVisible(true);

			confirmColorButtons[y] = new JButton("Confirm");
			confirmColorButtons[y].setFont(eras);
			confirmColorButtons[y].setEnabled(false);
			confirmColorButtons[y].setVisible(true);
		}

		game = new JButton("GAME");
		game.setFont(eras);
		game.setVisible(true);

		panelForButtons.setLayout(new GridLayout(4,2));
		panelForButtons.add(playerAndColorText[0]);
		panelForButtons.add(playerAndColorText[1]);
		panelForButtons.add(pickColorButtons[0]);
		panelForButtons.add(pickColorButtons[1]);
		panelForButtons.add(confirmColorButtons[0]);
		panelForButtons.add(confirmColorButtons[1]);
		panelForButtons.add(game);

		add(panelForImages, BorderLayout.CENTER);
		add(panelForButtons, BorderLayout.SOUTH);

		setSize(600,680);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		for(int k = 0; k < 2; k++)
		{
			pickColorButtons[k].addActionListener(this);
			confirmColorButtons[k].addActionListener(this);
		}

		game.addActionListener(this);
	}



	public void actionPerformed(ActionEvent evt)
	{
		for(int a = 0; a < 2; a++)
		{
			if(evt.getSource() == pickColorButtons[a])
			{
				if(ctr[a] == 0)
				{
					JOptionPane.showMessageDialog(this, String.format("Select Color For Player %d", a+1),
												String.format("Player %d : %s", a+1, p1),
												JOptionPane.PLAIN_MESSAGE);

					System.out.println(String.format("Counter of Player %d : ", a+1) + ctr[a]);

					for(int x = 0; x < 2; x++)
					{
						if(evt.getSource() == pickColorButtons[x])
						{
							pickColorButtons[x].setEnabled(true);
							pickColorButtons[x].setText("Pick Color");
						}else
							pickColorButtons[x].setEnabled(false);
					}

					ctr[a]++;

				}else if(ctr[a] == 1) {

					System.out.println(String.format("Counter of Player %d : ", a+1) + ctr[a]);
					if(playerAndColorText[a].getForeground() == Color.WHITE)
					{
						JOptionPane.showMessageDialog(this, "No Selected Color Yet.\nSelect Color of Piece First\n" +
													  "Before Clicking \"Pick Color\".","Undefined Color Choice",
													  JOptionPane.WARNING_MESSAGE);

					}else{
						colorPicked[a] = playerAndColorText[a].getForeground();
						confirmColorButtons[a].setText("Confirm Choice?");
						confirmColorButtons[a].setEnabled(true);
					}
				}

			}

		}

		for(int i = 0; i < 2; i++)
		{
			if(evt.getSource() == confirmColorButtons[i])
			{
				pickColorButtons[i].setEnabled(false);
				pickColorButtons[i].setText("I AM");
				confirmColorButtons[i].setText("READY");
				confirmColorButtons[i].setEnabled(false);
			}else
				pickColorButtons[i].setEnabled(true);

			ctr[1] = 0;
		}

		if(evt.getSource() == game)
		{
			new GameFrame();
			ChoosePieceColorFrame.this.dispose();
		}

		for(int w = 0; w < 4; w++)
			pieceImagePanels[w].addMouseListener(new MouseHandler());

		System.out.printf("Color 1 : %s\n", colorPicked[0]);
		System.out.printf("Color 2 : %s\n\n", colorPicked[1]);
	}



	private class MouseHandler extends MouseAdapter
	{
		public void mouseClicked(MouseEvent e)
		{
			String[] colorString = {"BLUE", "RED", "YELLOW", "GREEN"};

			for(int i = 0; i < 4; i++)
			{
				if(e.getSource() == pieceImagePanels[i])
				{
					pieceImagePanels[i].setBackground(colors[i]);

					if(ctr[0] == 1)
					{
						playerAndColorText[0].setText("PLAYER 1 Color : " + colorString[i]);
						playerAndColorText[0].setForeground(colors[i]);
						confirmColorButtons[0].setEnabled(false);
					}
					if(ctr[1] == 1)
					{
						playerAndColorText[1].setText("PLAYER 2 Color : " + colorString[i]);
						playerAndColorText[1].setForeground(colors[i]);
						confirmColorButtons[1].setEnabled(false);
					}

				}else
					pieceImagePanels[i].setBackground(Color.DARK_GRAY);

			}
		}
	}

}
