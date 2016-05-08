
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class StartMenu extends JFrame implements ActionListener
{
	private JButton play;
	private JButton exit;
	private Font font = new Font("Eras Bold ITC", Font.ITALIC, 20);

	public StartMenu()
	{
		super("Snakes & Ladders");
		setLayout(new BorderLayout(5,5));

		JLabel cover = new JLabel(new ImageIcon("resources/snakesLadders.jpg"));

		play = new JButton("Play");
		play.setFont(font);
		exit = new JButton("Exit");
		exit.setFont(font);

		add(play, BorderLayout.NORTH);
		add(cover, BorderLayout.CENTER);
		add(exit, BorderLayout.SOUTH);

		play.addActionListener(this);
		exit.addActionListener(this);

	}

	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == play) {
			GameFrame frame = new GameFrame();

			frame.setSize(1100,700);
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			StartMenu.this.dispose();
		} else if(e.getSource() == exit) {
			System.exit(0);
		}
	}
}
