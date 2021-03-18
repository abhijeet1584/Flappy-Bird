package game;

import graphics.GameFrame;

public class Game extends Thread
{
	public void start()
	{
		new GameFrame();
	}
}
