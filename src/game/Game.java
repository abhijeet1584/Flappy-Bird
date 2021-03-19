package game;

import graphics.GameFrame;

public class Game extends Thread implements Runnable
{
	@Override
	public void run()
	{
		new GameFrame();
	}
}
