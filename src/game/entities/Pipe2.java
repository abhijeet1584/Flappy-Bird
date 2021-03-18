package game.entities;

import java.awt.Graphics;
import java.awt.Color;

import graphics.GamePanel;

public class Pipe2 extends Pipe
{
	@Override
	protected void newPipe()
	{
		X = GamePanel.WINDOW_WIDTH + (GamePanel.WINDOW_WIDTH/2);
		HEIGHT = getHeight();
		Y = 0; // Since it will hang from the top
	}
	
	@Override
	public void update(Graphics g)
	{
		X -= 5;
		X2 = X + WIDTH;
		Y2 = Y + HEIGHT;
		if (X2 <= 0)
		{
			X = GamePanel.WINDOW_WIDTH; // sets the X position to the starting point
			HEIGHT = getHeight();
			Y = 0;
			
			X2 = X + WIDTH;
			Y2 = Y + HEIGHT;
		}
		renderTexture(g);
	}
}
