package game.entities;

import java.awt.Graphics;
import java.awt.Color;

import graphics.GamePanel;

public class Bird
{
	// Constants
	public final int WIDTH = 30;
	public final int HEIGHT = WIDTH;
	public final int G = 12;
	
	// Values
	public final int X = 200; // main X coordinate 
	public final int X2 = X + WIDTH; // second X coordinate
	public int Y; // main Y coordinate
	public int Y2 = Y + HEIGHT; // second Y coordinate
	public int gravity = 0; // Initially will be 0, value will be changed in the GamePanel class
	
	public Bird()
	{
		newBird();
	}
	
	public void newBird()
	{
		Y = (GamePanel.WINDOW_HEIGHT / 2) - HEIGHT; // Placing the bird at the exact center
	}
	
	public void update(Graphics g)
	{
		Y += gravity;
		Y2 = Y + HEIGHT;
		
		// Collision with walls
		if (Y <= 0)
			Y = 0;
		
		if (Y2 >= GamePanel.WINDOW_HEIGHT - GamePanel.FLOOR_HEIGHT)
			Y = (GamePanel.WINDOW_HEIGHT - GamePanel.FLOOR_HEIGHT) - HEIGHT;
		
		draw(g);
	}
	
	private void draw(Graphics g)
	{
		g.setColor(Color.RED);
		g.fillRect(X, Y, WIDTH, HEIGHT);
	}
	
	public void reset()
	{
		newBird();
	}
}