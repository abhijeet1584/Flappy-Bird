package graphics;

import javax.swing.JFrame;
import javax.swing.ImageIcon;
import java.awt.Image;

public class GameFrame extends JFrame
{	
	// Constants
	private Image icon;
	private String ICON_PATH = "src/res/images/icon.png";
	
	// Objects
	private GamePanel panel;
	
	public GameFrame()
	{
		panel = new GamePanel();
		icon = new ImageIcon(ICON_PATH).getImage();
		
		// Frame properties
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setTitle("Flappy Bird");
		add(panel);
		pack();
		setIconImage(icon);
		setLocationRelativeTo(null);
		setVisible(true);
	}
}