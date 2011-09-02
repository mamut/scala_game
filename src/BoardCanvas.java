import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;

import java.net.URL;
import javax.imageio.*;

public class BoardCanvas extends JPanel
{
	/*{{{BoardCanvas*/
	public BoardCanvas()
	{
		setPreferredSize(new Dimension(WIDTH,HEIGHT));
	}
	/*}}}*/

	/*{{{paint()*/
	public void paint(Graphics g)
	{
		g.drawImage(board,0,0,this);

		for(int y=0;y<14;y++)
		{
			for(int x=0;x<11;x++)
			{
				if(state.fields[y][x]==Fields.WHITE)
				{
					g.drawImage(white,x*40,y*40,this);	
				}
				else if(state.fields[y][x]==Fields.BLACK)
				{
					g.drawImage(black,x*40,y*40,this);	
				}
			}
		}
		if(Scala.gameOn)
		{
			if(state.currentPlayerIsWhite)//white
			{
				if(!Scala.playerWhiteIsHuman)
					g.drawImage(hourglass,9*40,13*40,this);
				g.drawImage(white,10*40,13*40,this);
			}
			else//black
			{
				if(!Scala.playerBlackIsHuman)
					g.drawImage(hourglass,9*40,13*40,this);
				g.drawImage(black,10*40,13*40,this);
			}
		}
		if(selected)
		{
			if(state.currentPlayerIsWhite)
			{
				g.drawImage(checkWhite,selectedX*40,selectedY*40,this);
			}
			else
			{
				g.drawImage(checkBlack,selectedX*40,selectedY*40,this);
			}
		}
	}
	/*}}}*/

	/*{{{loadState()*/
	public void loadState(State s)
	{
		gameOn = true;
		state = State.StateCopy(s);
		repaint();
	}
	/*}}}*/

	/*{{{constants, fields*/
	public static final LoadImage img = new LoadImage();

	private State state = new State();
	public boolean gameOn = false;

	private final BufferedImage board = img.loadImage("img/board.gif");
	private final BufferedImage black = img.loadImage("img/black.gif");
	private final BufferedImage white = img.loadImage("img/white.gif");

	private final BufferedImage checkWhite = img.loadImage("img/checkwhite.gif");
	private final BufferedImage checkBlack = img.loadImage("img/checkblack.gif");

	private final BufferedImage hourglass = img.loadImage("img/hourglass.gif");

	private static final int WIDTH = 440;
	private static final int HEIGHT = 560;

	public boolean lock = true;

	public boolean selected = false;
	public int selectedX = 0;
	public int selectedY = 0;
	/*}}}*/
}
