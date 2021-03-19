package game.entities;

import java.awt.Graphics;
import javax.swing.ImageIcon;
import java.awt.Image;

import graphics.GamePanel;

public class Bird
{
	// Constants
	public final int WIDTH = 30;
	public final int HEIGHT = WIDTH;
	public final int G = 12;
	private final String BIRD_IMAGE_PATH = "src/res/images/bird.png";
	
	// Values
	public final int X = 200; // main X coordinate 
	public final int X2 = X + WIDTH; // second X coordinate
	public int Y; // main Y coordinate
	public int Y2 = Y + HEIGHT; // second Y coordinate
	public int gravity = 0; // Initially will be 0, value will be changed in the GamePanel class
	
	// Objects
	private Image birdTexture;
	
	public Bird()
	{
		newBird();
	}
	
	public void newBird()
	{
		Y = (GamePanel.WINDOW_HEIGHT / 2) - HEIGHT; // Placing the bird at the exact center
		birdTexture = new ImageIcon(BIRD_IMAGE_PATH).getImage();
	}
	
	public void update(Graphics g)
	{
		Y += gravity;
		Y2 = Y + HEIGHT;
		
		//Bird is at top, to stop audio from playing infinitely
		if (Y > 10)
			GamePanel.birdIsAtTop = false;
		
		// Collision with walls
		if (Y <= 0)
		{
			Y = 0;
			GamePanel.birdIsAtTop = true;
		}
			
		if (Y2 >= GamePanel.WINDOW_HEIGHT - GamePanel.FLOOR_HEIGHT)
			Y = (GamePanel.WINDOW_HEIGHT - GamePanel.FLOOR_HEIGHT) - HEIGHT;
		
		draw(g);
	}
	
	private void draw(Graphics g)
	{
		g.fillRect(X, Y, WIDTH, HEIGHT);
		g.drawImage(birdTexture, X, Y, null);
	}
	
	public void reset()
	{
		newBird();
	}
}