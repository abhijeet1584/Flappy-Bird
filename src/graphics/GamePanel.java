package graphics;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyListener;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.Timer;
import java.awt.Font;

import game.entities.*;

public class GamePanel extends JPanel implements KeyListener, ActionListener
{
	// Constants
	public static final int WINDOW_HEIGHT = 500;	
	public static final int WINDOW_WIDTH = 700;
	public static final int FLOOR_HEIGHT = 20;
	private final int SPACE = 32;
	private final int ESC = 27;
	
	// Checkers 
	boolean isRunning = false;
	boolean paused = false;
	boolean gameOver = false;
	
	// Objects
	private Timer timer;
	private Pipe pipe1;
	private Pipe2 pipe2; // this is the downward pipe
	private Bird bird;
	
	public GamePanel()
	{
		// Panel properties
		setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
		addKeyListener(this);
		setFocusable(true);
		
		// Entity declaration
		pipe1 = new Pipe();
		pipe2 = new Pipe2();
		bird = new Bird();
		
		timer = new Timer(1000/60, this);
	}
	
	private void setBackground(Graphics g)
	{
		final String BACKGROUND_PATH = "src/res/images/background.png";
		Image background = new ImageIcon(BACKGROUND_PATH).getImage();
		g.drawImage(background, 0, 0, null);
	}
	
	// The main paint method
	public void paint(Graphics g)
	{
		super.paint(g);
		setBackground(g);
		draw(g);
	}
	
	private void draw(Graphics g)
	{
		updatePanel(g);
	}
	
	private void updatePanel(Graphics g)
	{
		bird.update(g);
		pipe1.update(g);
		pipe2.update(g);
		collision(g);
		if (paused == true)
			paused(g);
		
		if (isRunning == false)
			startScreen(g);
	}
	
	/**
	 * ====================================================================
	 * The main collision checking will happen at the collision method
	 * if isRunning then it will check, if it isn't It won't
	 * ====================================================================
	 */
	private void collision(Graphics g)
	{
		if (isRunning)
		{			
			if (bird.X <= pipe1.X2 && pipe1.X <= bird.X2 && pipe1.Y <= bird.Y2 && bird.Y <= pipe1.Y2)
			{
				timer.stop();
				endScreen(g);
				gameOver = true;
			}

			if (bird.X <= pipe2.X2 && pipe2.X <= bird.X2 && pipe2.Y <= bird.Y2 && bird.Y <= pipe2.Y2)
			{
				timer.stop();
				endScreen(g);
				gameOver = true;
			}
		}
	}
	
	private void paused(Graphics g)
	{
		g.setColor(Color.BLUE);
		g.setFont(new Font("JetBrains Mono", Font.BOLD, 30));
		g.drawString("PAUSED, press ESC to Resume", 100, 100);
	}
	
	private void endScreen(Graphics g)
	{
		g.setColor(Color.BLUE);
		g.setFont(new Font("JetBrains Mono", Font.BOLD, 40));
		g.drawString("Game Over", 250, 250);
	}
	
	// resets all the positions
	private void reset()
	{
		pipe1.reset();
		pipe2.reset();
		bird.reset();
	}
	
	private void startScreen(Graphics g)
	{
		g.setColor(Color.BLUE);
		g.setFont(new Font("JetBrains Mono", Font.BOLD, 30));
		g.drawString("Flappy Bird (Mostly just a red box)", 45, 200);
	}
	
	/*
	 * ==================================
	 * KeyAdapter methods              
	 * ==================================
	 */
	@Override
	public void keyTyped(KeyEvent e)
	{
		// e.getKeyChar();
	}
	
	@Override
	public void keyPressed(KeyEvent e)
	{
		// e.getKeyCode();
		
		// Starting the game
		if (isRunning == false && e.getKeyCode() == SPACE) 
		{
			start();
		}
		
		// Jumping the bird
		if (isRunning && e.getKeyCode() == SPACE)
		{
			bird.gravity = -bird.G;
			bird.Y -= 5;
		}
		
		// Pause and Resume
		if (isRunning && e.getKeyCode() == ESC)
		{
			if (paused == false)
			{
				paused = true;
				timer.stop(); // Pause
				repaint();
			}
			
			else if (paused == true)
			{
				paused = false;
				timer.start(); // Resume
				repaint();
			}
		}
		
		// Restart
		if (gameOver && e.getKeyCode() == SPACE)
		{
			gameOver = false;
			reset();
			start();
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e)
	{
		bird.gravity = bird.G;
	}
	
	
	/**
	 * =========================================================================================
	 * The actionPerformed will be called every frame                                        
	 * the actionPerformed will call the repaint() which will in turn call the paint method  
	 * The paint method will call the User implemented draw method and update method          
	 * =========================================================================================
	 */
	public void actionPerformed(ActionEvent e)
	{
		repaint();
	}
	
	private void start()
	{
		this.isRunning = true;
		timer.start();
	}
}
