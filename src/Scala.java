import java.io.*;
import java.lang.System;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.filechooser.*;

import java.net.URL;
import javax.imageio.*;

public class Scala{
	/*{{{ inicjalizacja w main*/
	public static void main(String[] args)
	{
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Scala");
		frame.setIconImage(img.loadImage("img/icon.gif"));
		frame.setResizable(false);
		frame.setLocationByPlatform(true);
	//	frame.setLayout(new GridBagLayout(2,1));
		frame.add(boardScala,BorderLayout.NORTH);
		progressBar.setPreferredSize(new Dimension(440,20));
		frame.add(progressBar,BorderLayout.SOUTH);

			chooser.setCurrentDirectory(new File("."));
			//chooser.setFileFilter(new FileNameExtensionFilter("Zapisany stan gry","scs"));		

					newGameItem.setAccelerator(KeyStroke.getKeyStroke("ctrl N"));
					newGameItem.addActionListener(new ActionListener()
							{
								public void actionPerformed(ActionEvent ae)
								{
									newGame();
								}
							});
					loadGameItem.setAccelerator(KeyStroke.getKeyStroke("ctrl L"));
					loadGameItem.addActionListener(new ActionListener()
						{
							public void actionPerformed(ActionEvent ae)
							{
								int result = chooser.showOpenDialog(frame);	
								File file = chooser.getSelectedFile();
								if(result == JFileChooser.APPROVE_OPTION)
									if(file!=null)
										loadGame(file);									
							}
						});
					saveGameItem.setEnabled(false);
					saveGameItem.setAccelerator(KeyStroke.getKeyStroke("ctrl S"));
					saveGameItem.addActionListener(new ActionListener()
						{
							public void actionPerformed(ActionEvent ae)
							{
								chooser.setSelectedFile(new File("game.scs"));
								int result = chooser.showSaveDialog(frame);	
								File file = chooser.getSelectedFile();
								if(result == JFileChooser.APPROVE_OPTION)
									if(file!=null)
										saveGame(file);
							}
						});
				
					exitGameItem.setAccelerator(KeyStroke.getKeyStroke("ctrl X"));
					exitGameItem.addActionListener( new ActionListener()
						{
							public void actionPerformed(ActionEvent ae)
							{
								System.exit(0);
							}
						}
					);

			gameMenu.add(newGameItem);
			gameMenu.addSeparator();
			gameMenu.add(loadGameItem);
			gameMenu.add(saveGameItem);
			gameMenu.addSeparator();
			gameMenu.add(exitGameItem);
					whiteBeginsItem.setSelected(firstPlayerWhite);
					blackBeginsItem.setSelected(!firstPlayerWhite);
					whiteBeginsItem.addActionListener(new ActionListener()
							{
								public void actionPerformed(ActionEvent ae)
								{
									firstPlayerWhite = true;
								}
							});
					blackBeginsItem.addActionListener(new ActionListener()
							{
								public void actionPerformed(ActionEvent ae)
								{
									firstPlayerWhite = false;
								}
							});
					whoBeginsGroup.add(whiteBeginsItem);
					whoBeginsGroup.add(blackBeginsItem);
					whoBeginsMenu.add(whiteBeginsItem);
					whoBeginsMenu.add(blackBeginsItem);
						playerWhiteIsHumanItem.setSelected(playerWhiteIsHuman);
						playerWhiteIsComputerItem.setSelected(!playerWhiteIsHuman);
						playerWhiteGroup.add(playerWhiteIsHumanItem);
						playerWhiteGroup.add(playerWhiteIsComputerItem);

						whiteLevelSpinner.setValue(whiteLevelValue);
						whiteLevelSpinner.setEnabled(!playerWhiteIsHuman);

						playerWhiteIsHumanItem.addActionListener(new ActionListener()
								{
									public void actionPerformed(ActionEvent ae)
									{
										playerWhiteIsHuman = true;
										whiteLevelSpinner.setEnabled(false);
									}
								});
						playerWhiteIsComputerItem.addActionListener(new ActionListener()
								{
									public void actionPerformed(ActionEvent ae)
									{
										playerWhiteIsHuman = false;
										whiteLevelSpinner.setEnabled(true);
									}
								});
						whiteLevelSpinner.addChangeListener(new ChangeListener()
								{
									public void stateChanged(ChangeEvent che)
									{
										SpinnerNumberModel model = (SpinnerNumberModel)whiteLevelSpinner.getModel();
										whiteLevelValue = model.getNumber().intValue();
									}
								});

						playerWhiteMenu.add(playerWhiteIsHumanItem);
						playerWhiteMenu.add(playerWhiteIsComputerItem);
						playerWhiteMenu.addSeparator();
						playerWhiteMenu.add(new JLabel("Poziom (1-10)"));
						playerWhiteMenu.add(whiteLevelSpinner);

						playerBlackIsHumanItem.setSelected(playerBlackIsHuman);
						playerBlackIsComputerItem.setSelected(!playerBlackIsHuman);
						playerBlackGroup.add(playerBlackIsHumanItem);
						playerBlackGroup.add(playerBlackIsComputerItem);

						blackLevelSpinner.setValue(blackLevelValue);
						blackLevelSpinner.setEnabled(!playerBlackIsHuman);

						playerBlackIsHumanItem.addActionListener(new ActionListener()
								{
									public void actionPerformed(ActionEvent ae)
									{
										playerBlackIsHuman = true;
										blackLevelSpinner.setEnabled(false);
									}
								});
						playerBlackIsComputerItem.addActionListener(new ActionListener()
								{
									public void actionPerformed(ActionEvent ae)
									{
										playerBlackIsHuman = false;
										blackLevelSpinner.setEnabled(true);
									}
								});
						blackLevelSpinner.addChangeListener(new ChangeListener()
								{
									public void stateChanged(ChangeEvent che)
									{
										SpinnerNumberModel model = (SpinnerNumberModel)blackLevelSpinner.getModel();
										blackLevelValue = model.getNumber().intValue();
									}
								});

						playerBlackMenu.add(playerBlackIsHumanItem);
						playerBlackMenu.add(playerBlackIsComputerItem);
						playerBlackMenu.addSeparator();
						playerBlackMenu.add(new JLabel("Poziom (1-10)"));
						playerBlackMenu.add(blackLevelSpinner);

					playersMenu.add(playerWhiteMenu);
					playersMenu.add(playerBlackMenu);

						alphabetaItem.setSelected(algorithmAB);
						otherItem.setSelected(!algorithmAB);
						alphabetaItem.addActionListener(new ActionListener()
								{
									public void actionPerformed(ActionEvent ae)
									{
										algorithmAB = true;
									}
								});
						otherItem.addActionListener(new ActionListener()
								{
									public void actionPerformed(ActionEvent ae)
									{
										algorithmAB = false;
									}
								});
						algorithmGroup.add(alphabetaItem);
						algorithmGroup.add(otherItem);
						algorithmMenu.add(alphabetaItem);
						algorithmMenu.add(otherItem);

				optionsMenu.add(whoBeginsMenu);
				optionsMenu.add(playersMenu);
				optionsMenu.addSeparator();
				optionsMenu.add(algorithmMenu);

					authorItem.addActionListener(new ActionListener()
							{
								public void actionPerformed(ActionEvent ae)
								{
									authorDialog.setVisible(true);
								}
							});
					rulesItem.setAccelerator(KeyStroke.getKeyStroke("ctrl R"));
					rulesItem.addActionListener(new ActionListener()
							{
								public void actionPerformed(ActionEvent ae)
								{
									rulesDialog.setVisible(true);
								}
							});
					playingItem.addActionListener(new ActionListener()
							{
								public void actionPerformed(ActionEvent ae)
								{
									playingDialog.setVisible(true);
								}
							});
			helpMenu.add(authorItem);
			helpMenu.add(rulesItem);
			helpMenu.add(playingItem);
		menu.add(gameMenu);
		menu.add(optionsMenu);
		menu.add(helpMenu);

		frame.setJMenuBar(menu);
		frame.pack();

		try
		{
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			SwingUtilities.updateComponentTreeUI(frame);
			SwingUtilities.updateComponentTreeUI(authorDialog);
			SwingUtilities.updateComponentTreeUI(rulesDialog);
			SwingUtilities.updateComponentTreeUI(playingDialog);
			SwingUtilities.updateComponentTreeUI(chooser);
			chooser.setFileFilter(new FileNameExtensionFilter("Zapisany stan gry","scs"));		
		}
		catch(Exception e) {}

		frame.setVisible(true);
	}
	/*}}}*/

	/*{{{newGame()*/
	private static void newGame()
	{
		MouseListener[] mouseList= boardScala.getMouseListeners();
		for(MouseListener mouse : mouseList)
			boardScala.removeMouseListener(mouse);
		game.stop();
		boardScala.selected = false;
		gameOn = true;
		currentPlayerWhite = firstPlayerWhite;
		currentBoardState = new State(beginState,currentPlayerWhite);
		whiteCount=14;
		blackCount=14;
		preMoveInit();
	}
	/*}}}*/

	/*{{{loadGame()*/
	private static void loadGame(File file)
	{
		try
		{
			MouseListener[] mouseList= boardScala.getMouseListeners();
			for(MouseListener mouse : mouseList)
				boardScala.removeMouseListener(mouse);
			game.stop();
			String temp;
			gameOn = true;
			boardScala.selected = false;
			currentBoardState = new State();
			BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			temp = in.readLine();	//Stan planszy
			for(int y=0;y<14;y++)	
			{
				temp = in.readLine();
				String[] numbers = temp.split("\\s");
				for(int x=0;x<11;x++)
				{
					 switch(Integer.parseInt(numbers[x]))
					 {
						 case 0: currentBoardState.fields[y][x]=Fields.OUT; break;
						 case 1: currentBoardState.fields[y][x]=Fields.FREE; break;
						 case 2: currentBoardState.fields[y][x]=Fields.WHITE; currentBoardState.whiteCount++; break;
						 case 3: currentBoardState.fields[y][x]=Fields.BLACK; currentBoardState.blackCount++; break;
						 case 4: currentBoardState.fields[y][x]=Fields.WHITEBASE; break;
						 case 5: currentBoardState.fields[y][x]=Fields.BLACKBASE; break;
					 }
				}
			}
			
			temp = in.readLine();	//Tura gracza
			temp = in.readLine();
			if(Integer.parseInt(temp)==1)
			{
				currentBoardState.currentPlayerIsWhite = true;
			}
			else
			{
				currentBoardState.currentPlayerIsWhite = false;
			}
			in.close();

			preMoveInit();
		}
		catch(IOException e) { e.printStackTrace(); }
	}
	/*}}}*/

	/*{{{saveGame*/
	private static void saveGame(File file)
	{
		try
		{
			MouseListener[] mouseList= boardScala.getMouseListeners();
			for(MouseListener mouse : mouseList)
				boardScala.removeMouseListener(mouse);
			game.stop();

			PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(file)));
			out.println("Stan planszy:");
			for(int y=0;y<14;y++)
			{
				for(int x=0;x<11;x++)
				{
					Fields tmp = currentBoardState.fields[y][x];
					if(tmp==Fields.OUT)
						out.print("0 ");
					else if(tmp==Fields.FREE)
						out.print("1 ");
					else if(tmp==Fields.WHITE)
						out.print("2 ");
					else if(tmp==Fields.BLACK)
						out.print("3 ");
					else if(tmp==Fields.WHITEBASE)
						out.print("4 ");
					else if(tmp==Fields.BLACKBASE)
						out.print("5 ");
				}
				out.println();
			}
			out.println("Tura gracza");
			if(currentBoardState.currentPlayerIsWhite)
			{
				out.println("1");
			}
			else
			{
				out.println("0");
			}
			out.close();
			game = new Thread()
				{
					public void run()
					{
						mayRunNext = false;
						while(gameOn)
						{
							nextMove();
						}
						mayRunNext = true;
					}
				};
			game.start();
		}
		catch(IOException e) { e.printStackTrace(); }
		
	}
	/*}}}*/

	/*{{{preMoveInit*/
	static void preMoveInit()
	{
		saveGameItem.setEnabled(true);
		boardScala.loadState(currentBoardState);
		currPlayerWhiteIsHuman = playerWhiteIsHuman;
		currPlayerBlackIsHuman = playerBlackIsHuman;
		currWhiteLevelValue = whiteLevelValue;
		currBlackLevelValue = blackLevelValue;
		currAlgorithmAB = algorithmAB;
		lastWhiteBest = 0;
		lastBlackBest = 0;
		counterWhite = 0;
		counterBlack = 0;
		timer.stop();
		progressBar.setValue(0);
		game = new Thread()
			{
				public void run()
				{
					mayRunNext = false;
					while(gameOn)
					{
						nextMove();
					}
					mayRunNext = true;
				}
			};
		game.start();
	}
	/*}}}*/

	/*{{{nextMove()*/
	private static void nextMove()
	{
		if((currentBoardState.currentPlayerIsWhite&&currPlayerWhiteIsHuman)
				||(!currentBoardState.currentPlayerIsWhite&&currPlayerBlackIsHuman))//cz³owiek
			humanMove();
		else//komputer
		{
			computerMove();
		}
		checkEndGame();
	}
	/*}}}*/

	/*{{{humanMove*/
	static void humanMove()
	{
		guardMove = true;
		MouseListener listener;
		if(currentBoardState.currentPlayerIsWhite&&currPlayerWhiteIsHuman)
			listener = whiteListener;
		else
			listener = blackListener;
		boardScala.addMouseListener(listener);

		while(guardMove)
		{
			try
			{Thread.currentThread().sleep(20);}
			catch (InterruptedException e)
			{}
		}
		boardScala.removeMouseListener(listener);

		if(currentBoardState.currentPlayerIsWhite)
		{
			int maxVal = Search.highest(currentBoardState);
			if(maxVal<=lastWhiteBest)
				counterWhite++;
			else
			{
				lastWhiteBest = maxVal;
				counterWhite = 0;
			}
		}
		else
		{
			int minVal = Search.highest(currentBoardState);
			if(minVal>=lastBlackBest)
				counterBlack++;
			else
			{
				lastBlackBest = minVal;
				counterBlack = 0;
			}
		}

		currentBoardState.currentPlayerIsWhite = !currentBoardState.currentPlayerIsWhite;
		boardScala.loadState(currentBoardState);
	}
	/*}}}*/

	/*{{{computerMove*/
	static void computerMove()
	{
		Search.timeIsUp = false;
		timer = new Thread()
				{
					public void run()
					{
						for(int time = 0;time<16;time++)
						{
							progressBar.setValue(time);
							try
							{Thread.currentThread().sleep(1000);}
							catch (InterruptedException e)
							{}
						}
						Search.timeIsUp = true;
					}
				};
		timer.start();

		currentBoardState.gotStates=false;
		currentBoardState.generateSucc();
		int to = currentBoardState.successors.size();
		State next = null;
		int val = Integer.MIN_VALUE;

		if(currAlgorithmAB)
		{
			int level;
			if(currentBoardState.currentPlayerIsWhite)
				level = currWhiteLevelValue;
			else
				level = currBlackLevelValue;

			for(int i=0;i<to;i++)
			{
				int tempVal;
				State tempState = currentBoardState.successors.get(i);
				if(Search.isTerminalNode(tempState))
					tempVal = Search.eval(tempState);
				else
					tempVal = Search.alphaBeta(tempState, level-1, Integer.MIN_VALUE, Integer.MAX_VALUE);
					//tempVal = -Search.negascout(tempState, level, Integer.MIN_VALUE, Integer.MAX_VALUE);

				if(!currentBoardState.currentPlayerIsWhite)
					tempVal=-tempVal;

				if(tempVal>=val)
				{
					next = tempState;
					val = tempVal;
				}
			}
		}
		else
		{
			int level;
			if(currentBoardState.currentPlayerIsWhite)
				level = currWhiteLevelValue;
			else
				level = currBlackLevelValue;

			for(int i=0;i<to;i++)
			{
				int tempVal;
				State tempState = currentBoardState.successors.get(i);
				if(Search.isTerminalNode(tempState))
					tempVal = Search.eval(tempState);
				else
					tempVal = Search.negascoutTT(tempState, level-1, Integer.MIN_VALUE, Integer.MAX_VALUE);

				if(!currentBoardState.currentPlayerIsWhite)
					tempVal=-tempVal;

				if(tempVal>=val)
				{
					next = tempState;
					val = tempVal;
				}
			}
		}
		timer.stop();
		progressBar.setValue(0);

		int high = Search.highest(currentBoardState);
		if(currentBoardState.currentPlayerIsWhite)
		{
			if(high<=lastWhiteBest)
			{
				counterWhite++;
			}
			else
			{
				lastWhiteBest = high;
				counterWhite = 0;
			}
		}
		else
		{
			if(high<=lastBlackBest)
			{
				counterBlack++;
			}
			else
			{
				lastBlackBest = high;
				counterBlack = 0;
			}
		}

		currentBoardState = next;
		boardScala.loadState(currentBoardState);
	}
	/*}}}*/

	/*{{{checkEndGame*/
	static void checkEndGame()
	{
		if(currentBoardState.fields[0][5]==Fields.WHITE)
			messageDisplay("Wygrywaj¹ bia³e!");
		else if(currentBoardState.fields[13][5]==Fields.BLACK)
			messageDisplay("Wygrywaj¹ czarne!");
		else if(remis(currentBoardState)||(counterWhite>REMIS&&counterBlack>REMIS))
			messageDisplay("Sytuacja patowa.");// Bia³e: "+lastWhiteBest+" Czarne: "+lastBlackBest);
	}
	/*}}}*/

	/*{{{remis*/
	public static boolean remis(State s)
	{
		if(s.whiteCount==1&&s.blackCount==1)
			return true;
		if((Search.lowest(s,false)+s.whiteCount)<=12&&(Search.lowest(s,true)+s.blackCount)<=12)
			return true;
		return false;
	}
	/*}}}*/

	/*{{{messageDisplay*/
	static void messageDisplay(String msg)
	{
		gameOn=false;
		boardScala.selected = false;
		boardScala.repaint();
		WinDialog message = new WinDialog(frame);
		message.setLocation(frame.getX()+frame.getWidth()/2-message.getWidth()/2,frame.getY()+frame.getHeight()/2-message.getHeight()/2);
		message.text.setText(msg);
		message.setVisible(true);
	}
	/*}}}*/

	/*{{{constants, flags*/

	/*{{{whiteListener*/
	static MouseListener whiteListener = new MouseListener()
			{
				public void mouseReleased(MouseEvent e)
				{
					int x = e.getX();
					int y = e.getY();

					x/=40;
					y/=40;

	//System.out.println(x+" "+y);
					Fields field = Scala.currentBoardState.fields[y][x];
					if(field==Fields.WHITE)
					{
	//System.out.println("BIA£E");
						if(!boardScala.selected)
						{
							boardScala.selected = true;
							boardScala.selectedX = x;
							boardScala.selectedY = y;
							boardScala.repaint();
						}else if(boardScala.selected&&boardScala.selectedX==x&&boardScala.selectedY==y)
						{
							boardScala.selected = false;
							boardScala.selectedX = 0;
							boardScala.selectedY = 0;

							/*if(multijump)
							{
								multijump = false;
								currentPlayerWhite = false;
								guardMove = false;
								boardScala.loadState(currentBoardState);
							}
							else*/
								boardScala.repaint();
						}
					}
					else if(boardScala.selected&&(field==Fields.FREE||field==Fields.BLACKBASE))
					{
						int distX = x-boardScala.selectedX;
						int distY = y-boardScala.selectedY;
						if(Math.abs(distX)<=1&&Math.abs(distY)<=1)//s¹siednie pole
						{
							//if(!multijump)
							{
								State tempState = new State(currentBoardState);
								if(tempState.checkAndUpdateState(new Move(boardScala.selectedX,boardScala.selectedY,x,y)))
								{
									boardScala.selected = false;
									boardScala.selectedX = 0;
									boardScala.selectedY = 0;
									Scala.guardMove = false;

									currentBoardState = tempState;
									boardScala.loadState(currentBoardState);
								}
							}
						}
						else if(Math.abs(distX)==0||Math.abs(distY)==0||Math.abs(distX)==Math.abs(distY))
						{
	//System.out.println("skok?");
							boolean good = true;	
							int tempX = boardScala.selectedX;
							int tempY = boardScala.selectedY;					
							for(;tempX!=x||tempY!=y;)
							{
	//System.out.println("skok: "+tempX+" "+tempY+" -> "+distX+" "+distY+" -> "+State.sign(distX)+" "+State.sign(distY));
								Fields checkField = currentBoardState.fields[tempY][tempX];
								if(checkField!=Fields.WHITE&&checkField!=Fields.BLACK)
								{
									good = false;
									break;
								}
								tempX+=State.sign(distX);
								tempY+=State.sign(distY);
							}
							if(good)
							{
								State tempState = new State(currentBoardState);
								if(tempState.checkAndUpdateState(new Move(boardScala.selectedX,boardScala.selectedY,x,y)))
								{
									/*if(!(currentBoardState.fields[y][x]==Fields.BLACKBASE))
									{
										boardScala.selected = true;
										boardScala.selectedX = x;
										boardScala.selectedY = y;
										Scala.guardMove = true;

										//multijump = true;
									}
									else*/
									{
										boardScala.selected = false;
										Scala.guardMove = false;
										//multijump = false;
									}

									currentBoardState = tempState;
									boardScala.loadState(currentBoardState);
								}
							}
						}
					}
				}

				public void mouseEntered(MouseEvent e) {}
				public void mouseExited(MouseEvent e) {}
				public void mousePressed(MouseEvent e) {}
				public void mouseClicked(MouseEvent e) {}
			};
/*}}}*/

	/*{{{blackListener*/
	static MouseListener blackListener = new MouseListener()
			{
				public void mouseReleased(MouseEvent e)
				{
					int x = e.getX();
					int y = e.getY();

					x/=40;
					y/=40;

//System.out.println(x+" "+y);
					Fields field = Scala.currentBoardState.fields[y][x];
					if(field==Fields.BLACK)
					{
//System.out.println("CZARNE");
						if(!boardScala.selected)
						{
							boardScala.selected = true;
							boardScala.selectedX = x;
							boardScala.selectedY = y;
							boardScala.repaint();
						}else if(boardScala.selected&&boardScala.selectedX==x&&boardScala.selectedY==y)
						{
							boardScala.selected = false;
							boardScala.selectedX = 0;
							boardScala.selectedY = 0;

							/*if(multijump)
							{
								multijump = false;
								currentPlayerWhite = true;
								guardMove = false;
								boardScala.loadState(currentBoardState);
							}
							else*/
								boardScala.repaint();
						}
					}
					else if(boardScala.selected&&(field==Fields.FREE||field==Fields.WHITEBASE))
					{
						int distX = x-boardScala.selectedX;
						int distY = y-boardScala.selectedY;
						if(Math.abs(distX)<=1&&Math.abs(distY)<=1)//s¹siednie pole
						{
							//if(!multijump)
							{
								State tempState = new State(currentBoardState);
								if(tempState.checkAndUpdateState(new Move(boardScala.selectedX,boardScala.selectedY,x,y)))
								{
									boardScala.selected = false;
									boardScala.selectedX = 0;
									boardScala.selectedY = 0;
									Scala.guardMove = false;

									currentBoardState = tempState;
									boardScala.loadState(currentBoardState);
								}
							}
						}
						else if(Math.abs(distX)==0||Math.abs(distY)==0||Math.abs(distX)==Math.abs(distY))
						{
//System.out.println("skok?");
							boolean good = true;	
							int tempX = boardScala.selectedX;
							int tempY = boardScala.selectedY;					
							for(;tempX!=x||tempY!=y;)
							{
//System.out.println("skok: "+tempX+" "+tempY+" -> "+distX+" "+distY+" -> "+State.sign(distX)+" "+State.sign(distY));
								Fields checkField = currentBoardState.fields[tempY][tempX];
								if(checkField!=Fields.WHITE&&checkField!=Fields.BLACK)
								{
									good = false;
									break;
								}
								tempX+=State.sign(distX);
								tempY+=State.sign(distY);
							}
							if(good)
							{
								State tempState = new State(currentBoardState);
								if(tempState.checkAndUpdateState(new Move(boardScala.selectedX,boardScala.selectedY,x,y)))
								{

									/*if(!(currentBoardState.fields[y][x]==Fields.WHITEBASE))
									{
										boardScala.selected = true;
										boardScala.selectedX = x;
										boardScala.selectedY = y;
										Scala.guardMove = true;

										//multijump = true;
									}
									else*/
									{
										boardScala.selected = false;
										Scala.guardMove = false;
										//multijump = false;
									}


									currentBoardState = tempState;
									boardScala.loadState(currentBoardState);
								}
							}
						}
					}
				}

				public void mouseEntered(MouseEvent e) {}
				public void mouseExited(MouseEvent e) {}
				public void mousePressed(MouseEvent e) {}
				public void mouseClicked(MouseEvent e) {}
			};
	/*}}}*/

	public static Thread timer = new Thread()
				{
					public void run()
					{
						for(int time = 0;time<16;time++)
						{
							progressBar.setValue(time);
							try
							{Thread.currentThread().sleep(1000);}
							catch (InterruptedException e)
							{}
						}
						Search.timeIsUp = true;
					}
				};

	public static Thread game = new Thread()
			{
				public void run()
				{
					mayRunNext = false;
					while(gameOn)
					{
						nextMove();
					}
					mayRunNext = true;
				}
			};

	private static final int[][] beginState = 
	/*		{//	 0 1 2 3 4 5 6 7 8 9 A			TEST
			{0,0,0,0,0,5,0,0,0,0,0},// 0
			{0,0,0,0,1,1,1,0,0,0,0},// 1
			{0,0,0,1,1,1,1,1,0,0,0},// 2
			{0,0,1,1,1,1,1,1,1,0,0},// 3
			{0,1,1,1,3,1,3,1,1,1,0},// 4
			{1,1,3,3,3,1,3,3,3,1,1},// 5
			{1,1,3,3,0,0,0,1,1,1,1},// 6
			{1,1,2,2,0,0,0,2,2,1,1},// 7
			{1,1,2,2,2,2,2,2,2,1,1},// 8
			{0,1,1,1,2,2,2,1,1,1,0},// 9
			{0,0,1,1,1,1,1,1,1,0,0},// A
			{0,0,0,1,1,1,1,1,0,0,0},// B
			{0,0,0,0,1,1,1,0,0,0,0},// C
			{0,0,0,0,0,4,0,0,0,0,0} // D
		};*/
			{//	 0 1 2 3 4 5 6 7 8 9 A			START
			{0,0,0,0,0,5,0,0,0,0,0},// 0
			{0,0,0,0,1,1,1,0,0,0,0},// 1
			{0,0,0,1,1,1,1,1,0,0,0},// 2
			{0,0,1,1,1,1,1,1,1,0,0},// 3
			{0,1,1,1,3,3,3,1,1,1,0},// 4
			{1,1,3,3,3,3,3,3,3,1,1},// 5
			{1,1,3,3,0,0,0,3,3,1,1},// 6
			{1,1,2,2,0,0,0,2,2,1,1},// 7
			{1,1,2,2,2,2,2,2,2,1,1},// 8
			{0,1,1,1,2,2,2,1,1,1,0},// 9
			{0,0,1,1,1,1,1,1,1,0,0},// A
			{0,0,0,1,1,1,1,1,0,0,0},// B
			{0,0,0,0,1,1,1,0,0,0,0},// C
			{0,0,0,0,0,4,0,0,0,0,0} // D
		};
	private static int lastWhiteBest;
	private static int counterWhite;

	private static int lastBlackBest;
	private static int counterBlack;

	private static int whiteCount = 14;
	private static int blackCount = 14;

	public static boolean currentPlayerWhite;
	public static State currentBoardState;

	private static boolean currPlayerWhiteIsHuman;
	private static boolean currPlayerBlackIsHuman;
	private static int currWhiteLevelValue;
	private static int currBlackLevelValue;
	private static boolean currAlgorithmAB;

	private static boolean firstPlayerWhite = true;//white
	public static boolean playerWhiteIsHuman = true;
	public static boolean playerBlackIsHuman = false;
	private static int whiteLevelValue = 3;
	private static int blackLevelValue = 3;
	private static boolean algorithmAB = false;

	public static boolean gameOn = false;
	private static boolean mayRunNext = true;
	public static boolean multijump = false;
	public static boolean guardMove;

	public static final int XFIELDS = 11;
	public static final int YFIELDS = 14;

	public static final int REMIS = 25;

	public static final LoadImage img = new LoadImage();

	/*}}}*/

/*{{{Elementy interfejsu*/

	final private static JFrame frame = new JFrame();

	final static BoardCanvas boardScala = new BoardCanvas();

	final static JProgressBar progressBar = new JProgressBar(0,16);

	final private static JFileChooser chooser = new JFileChooser();

	final private static AuthorDialog authorDialog = new AuthorDialog(frame);
	final private static RulesDialog rulesDialog = new RulesDialog(frame);
	final private static PlayingDialog playingDialog = new PlayingDialog(frame);

	final private static JMenuBar menu = new JMenuBar();

	final private static JMenu gameMenu = new JMenu("Gra");
	final private static JMenu optionsMenu = new JMenu("Opcje");
	final private static JMenu whoBeginsMenu = new JMenu("Kto zaczyna");
	final private static JMenu playersMenu = new JMenu("Gracze");
	final private static JMenu playerWhiteMenu = new JMenu("Bia³y");
	final private static JMenu playerBlackMenu = new JMenu("Czarny");
	final private static JMenu algorithmMenu = new JMenu("Algorytm");
	final private static JMenu helpMenu = new JMenu("Pomoc");

	final private static JMenuItem newGameItem = new JMenuItem("Nowa gra");
	final private static JMenuItem loadGameItem = new JMenuItem("Wczytaj grê");
	final private static JMenuItem saveGameItem = new JMenuItem("Zapisz grê");
	final private static JMenuItem exitGameItem = new JMenuItem("Zakoñcz grê");
	final private static JMenuItem authorItem = new JMenuItem("Autor");
	final private static JMenuItem rulesItem = new JMenuItem("Zasady");
	final private static JMenuItem playingItem = new JMenuItem("Jak graæ");

	final private static ButtonGroup whoBeginsGroup = new ButtonGroup();
	final private static ButtonGroup playerWhiteGroup = new ButtonGroup();
	final private static ButtonGroup playerBlackGroup = new ButtonGroup();
	final private static ButtonGroup algorithmGroup = new ButtonGroup();

	final private static JRadioButtonMenuItem whiteBeginsItem = new JRadioButtonMenuItem("Bia³e");
	final private static JRadioButtonMenuItem blackBeginsItem = new JRadioButtonMenuItem("Czarne");
	final private static JRadioButtonMenuItem playerWhiteIsHumanItem = new JRadioButtonMenuItem("Cz³owiek");
	final private static JRadioButtonMenuItem playerWhiteIsComputerItem = new JRadioButtonMenuItem("Komputer");
	final private static JRadioButtonMenuItem playerBlackIsHumanItem = new JRadioButtonMenuItem("Cz³owiek");
	final private static JRadioButtonMenuItem playerBlackIsComputerItem = new JRadioButtonMenuItem("Komputer");
	final private static JRadioButtonMenuItem alphabetaItem = new JRadioButtonMenuItem("Alfa-beta");
	final private static JRadioButtonMenuItem otherItem = new JRadioButtonMenuItem("Negascout + TT");

	final private static SpinnerModel modelWhite = new SpinnerNumberModel(3, 1, 10, 1);
	final private static SpinnerModel modelBlack = new SpinnerNumberModel(3, 1, 10, 1);

	final private static JSpinner whiteLevelSpinner = new JSpinner(modelWhite);
	final private static JSpinner blackLevelSpinner = new JSpinner(modelBlack);
	/*}}}*/
}
