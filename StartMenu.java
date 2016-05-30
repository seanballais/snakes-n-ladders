import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import javax.swing.border.LineBorder;

public class StartMenu extends JFrame implements ActionListener
{
	private JButton credBack, instBack;
	private JPanel menuButtonPanel, blankPanel;
	private JFrame creditsFrame, instructionsFrame;
	private JButton play, instructions, credits, quit;

	public StartMenu()
	{
		UIManager.put("Button.background", new Color(0,0,128));
		UIManager.put("Button.foreground", new Color(255,128,0));
		UIManager.put("Button.font", new Font("Eras Bold ITC", Font.BOLD, 15));
		UIManager.put("OptionPane.messageFont", new Font("Britannic Bold", Font.PLAIN, 16));

		setContentPane(new JLabel(new ImageIcon("resources/CoverMP.jpg")));
		setLayout(new BorderLayout(10,10));

		blankPanel = new JPanel();
		blankPanel.setBackground(new Color(0,0,0,0));

		createMenuButtons();
		add(blankPanel, BorderLayout.CENTER);
		add(menuButtonPanel, BorderLayout.SOUTH);
	}

	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == play)
		{
			this.dispose();

			GameFrame game = new GameFrame();
			game.setSize(1060,720);
			game.setUndecorated(true);
			game.setVisible(true);
			game.setLocationRelativeTo(null);
			game.getRootPane().setBorder(game.compound3);
			game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		}else if(e.getSource() == instructions) {
			gameInstructions();
			this.setEnabled(false);

		}else if(e.getSource() == credits) {
			gameCredits();
			this.setEnabled(false);

		}else if(e.getSource() == quit) {

			int dialogResult = JOptionPane.showConfirmDialog(null, "Do You Really Wish to Quit?", "Quit", JOptionPane.YES_NO_OPTION);

			if(dialogResult == 0)
			{
				JFrame f = new JFrame();

				System.exit(0);

				f.setSize(200,200);
				f.setUndecorated(true);
				f.setLocationRelativeTo(null);
				f.getRootPane().setBorder(new LineBorder(Color.BLACK, 8, true));
				f.setContentPane(new JLabel(new ImageIcon("resources/lastFrameGames.png")));
				f.setVisible(true);
			}

		}else if(e.getSource() == credBack) {
			creditsFrame.dispose();
			this.setEnabled(true);

		}else if(e.getSource() == instBack) {
			instructionsFrame.dispose();
			this.setEnabled(true);
		}

	}

	public void createMenuButtons()
	{
		play = new JButton(new ImageIcon("resources/buttonIcons/playButton.png"));
		play.setRolloverIcon(new ImageIcon("resources/buttonIcons/playHover.png"));
		quit = new JButton(new ImageIcon("resources/buttonIcons/quitButton.png"));
		quit.setRolloverIcon(new ImageIcon("resources/buttonIcons/quitHover.png"));
		credits = new JButton(new ImageIcon("resources/buttonIcons/creditsButton.png"));
		credits.setRolloverIcon(new ImageIcon("resources/buttonIcons/creditHover.png"));
		instructions = new JButton(new ImageIcon("resources/buttonIcons/instructionsButton.png"));
		instructions.setRolloverIcon(new ImageIcon("resources/buttonIcons/instructionsHover.png"));

		play.setPreferredSize(new Dimension(350,200));
		quit.setPreferredSize(new Dimension(350,200));
		credits.setPreferredSize(new Dimension(350,200));
		instructions.setPreferredSize(new Dimension(350,200));

		menuButtonPanel = new JPanel();
		menuButtonPanel.setBackground(new Color(0,0,0,0));
		menuButtonPanel.setPreferredSize(new Dimension(300,300));
		menuButtonPanel.setLayout(new BoxLayout(menuButtonPanel, BoxLayout.Y_AXIS));

		menuButtonPanel.add(play);
		menuButtonPanel.add(Box.createRigidArea(new Dimension(0,8)));
		menuButtonPanel.add(instructions);
		menuButtonPanel.add(Box.createRigidArea(new Dimension(0,8)));
		menuButtonPanel.add(credits);
		menuButtonPanel.add(Box.createRigidArea(new Dimension(0,8)));
		menuButtonPanel.add(quit);

		play.addActionListener(this);
		quit.addActionListener(this);
		credits.addActionListener(this);
		instructions.addActionListener(this);
	}

	public void gameInstructions()
	{
		JPanel picturePanel = new JPanel();
		JPanel bPanel = new JPanel();

		instBack = new JButton("BACK");
		instBack.setFont(new Font("Eras Bold ITC", Font.PLAIN, 40));
		instBack.addActionListener(this);

		picturePanel.setBackground(new Color(0,0,128));
		picturePanel.add(new JLabel(new ImageIcon("resources/instructions.jpg")), SwingConstants.CENTER);
		bPanel.setBackground(new Color(0,0,128));
		bPanel.setLayout(new BorderLayout(0,0));
		bPanel.setPreferredSize(new Dimension(50,50));
		bPanel.add(instBack, BorderLayout.CENTER);

		instructionsFrame = new JFrame();
		instructionsFrame.setLayout(new BorderLayout(2,2));
		instructionsFrame.add(picturePanel, BorderLayout.CENTER);
		instructionsFrame.add(bPanel, BorderLayout.SOUTH);

		instructionsFrame.setUndecorated(true);
		instructionsFrame.setSize(1060,720);
		instructionsFrame.setLocationRelativeTo(null);
		instructionsFrame.setVisible(true);
		instructionsFrame.getRootPane().setBorder(new LineBorder(Color.BLACK, 8));
	}

	public void gameCredits()
	{
		JPanel topPanel = new JPanel();
		JPanel bPanel = new JPanel();
		JScrollPane scrollPane;


		credBack = new JButton("BACK");
		credBack.addActionListener(this);
		bPanel.setLayout(new BorderLayout(0,0));
		bPanel.setPreferredSize(new Dimension(40,40));
		bPanel.add(credBack);

		creditsFrame = new JFrame();
		topPanel.setLayout(new BorderLayout(0,0));
		creditsFrame.getContentPane().add(topPanel);

		Icon image = new ImageIcon("resources/CreditsFINAL.jpg");
		JLabel label = new JLabel(image);

		scrollPane = new JScrollPane();
		scrollPane.getViewport().add(label);
		topPanel.add(scrollPane, BorderLayout.CENTER);

		creditsFrame.setLayout(new BorderLayout(0,0));
		creditsFrame.add(topPanel, BorderLayout.CENTER);
		creditsFrame.add(bPanel, BorderLayout.SOUTH);

		creditsFrame.setUndecorated(true);
		creditsFrame.setSize(560,700);
		creditsFrame.setVisible(true);
		creditsFrame.setLocationRelativeTo(null);
		creditsFrame.getRootPane().setBorder(SidePanels.border);
	}

}
