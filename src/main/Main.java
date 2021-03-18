package main;

import game.Game;

// Main class for calling the Game Class
public class Main
{
	private Game game;
	
	public void start()
	{
		game = new Game();
		game.start();
	}
	
	public static void main(String[] args)
	{
		Main main = new Main();
		main.start();
	}
}