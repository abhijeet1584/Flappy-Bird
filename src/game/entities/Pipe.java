package game.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import javax.swing.ImageIcon;

import graphics.GamePanel;

public class Pipe
{
	// Constant
	public final int WIDTH = 50;
	static final int CHUNK_HEIGHT = 50;
	protected final String PIPE_TEXTURE = "src/res/images/pipeTile.png";
	
	// values
	public int HEIGHT;
	public int X; // first x coordinate (main)
	public int X2; // second x coordinate
	public int Y; // first y coordinate (main)
	public int Y2; // second y coordinate
	
	// Objects
	Random random;
	ImageIcon pipeTexture;
	
	public Pipe()
	{
		random = new Random();
		pipeTexture = new ImageIcon(PIPE_TEXTURE);
		newPipe();
	}
	
	/**
	 * The frame height is divided into chunks. For efficient rendering
	 * The height is divided into 10 chunks
	 * 500 / 10 = 50
	 * Each chunk contains 50 pixels
	 * The max height of a pipe will be 7 chunks = 7 * 50 = 350 pixels
	 * and the extra 20 pixels are the floor
	 */
	protected int getHeight()
	{
		int chunks = random.nextInt(7) + 1; // Random number from 1 up to 7
		int height = chunks * CHUNK_HEIGHT;
		return height;
	}
	
	protected void newPipe()
	{
		X = GamePanel.WINDOW_WIDTH;
		HEIGHT = getHeight();
		Y = (GamePanel.WINDOW_HEIGHT - HEIGHT) - GamePanel.FLOOR_HEIGHT; // extra 20 pixels of the floor
	}
	
	public void update(Graphics g)
	{
		X -= 5;
		X2 = X + WIDTH;
		Y2 = Y + HEIGHT;
		if (X2 <= 0)
		{
			X = GamePanel.WINDOW_WIDTH; // sets the X position of the pipe back to the starting point
			HEIGHT = getHeight();
			Y = (GamePanel.WINDOW_HEIGHT - HEIGHT) - 20; // extra 20 pixels of the black floor
			
			// updating the other coordinates
			X2 = X + WIDTH;
			Y2 = Y + HEIGHT;
		}
		renderTexture(g); // draw the pipe with new coordinates
	}
	
	protected void renderTexture(Graphics g)
	{
		for (int textures = Y; textures <= Y2 - GamePanel.FLOOR_HEIGHT; textures += CHUNK_HEIGHT)
		{
			g.drawImage(pipeTexture.getImage(), X, textures, null);
		}
	}
	
	// Reset method
	public void reset()
	{
		newPipe();
	}
}
