import java.util.*;

public class State
{
	/*{{{constructors*/
	public State()
	{
		fields = new Fields[14][11];
		for(int y=0;y<14;y++)
		{
			for(int x=0;x<11;x++)
			{
				fields[y][x]=Fields.OUT;
			}
		}
		currentPlayerIsWhite = true;
	}

	public State(Fields[][] newFields,boolean currentPlayerIsWhite)
	{
		fields = new Fields[14][11];
		for(int y=0;y<14;y++)
		{
			for(int x=0;x<11;x++)
			{
				fields[y][x]=newFields[y][x];
				if(fields[y][x]==Fields.WHITE)
					whiteCount++;
				if(fields[y][x]==Fields.BLACK)
					blackCount++;
			}
		}
		this.currentPlayerIsWhite = currentPlayerIsWhite;
	}

	public State(State s)
	{
		Fields[][] from = s.fields;
		fields = new Fields[14][11];
		for(int y=0;y<14;y++)
		{
			for(int x=0;x<11;x++)
			{
				fields[y][x]=from[y][x];
			}
		}
		whiteCount = s.whiteCount;
		blackCount = s.blackCount;
		currentPlayerIsWhite = s.currentPlayerIsWhite;
	}

	public State(int[][] numbers,boolean currentPlayerIsWhite)
	{
		fields = new Fields[14][11];
		gotStates = false;
		successors = new ArrayList<State>();
		for(int y=0;y<14;y++)
		{
			for(int x=0;x<11;x++)
			{
				switch(numbers[y][x])
				{
					case 0: fields[y][x] = Fields.OUT; break;
					case 1: fields[y][x] = Fields.FREE; break;
					case 2: fields[y][x] = Fields.WHITE; whiteCount++; break;
					case 3: fields[y][x] = Fields.BLACK; blackCount++; break;
					case 4: fields[y][x] = Fields.WHITEBASE; break;
					case 5: fields[y][x] = Fields.BLACKBASE; break;
				}
			}
		}
		this.currentPlayerIsWhite = currentPlayerIsWhite;
	}
	/*}}}*/

	/*{{{StateCopy*/
	public static State StateCopy(State s)
	{
		Fields[][] to = new Fields[14][11];
		for(int y=0;y<14;y++)
		{
			for(int x=0;x<11;x++)
			{
				to[y][x]=s.fields[y][x];
			}
		}

		return new State(to,s.currentPlayerIsWhite);
	}
	/*}}}*/

	/*{{{generateSucc()*/
	public void generateSucc()
	{
		successors = new ArrayList<State>();
		boolean shift = false;
		if(!currentPlayerIsWhite)
			shift = true;
		for(int y=1;y<Scala.YFIELDS-1;y++)
		{
			if(shift)
				y=14-y;
			for(int x=0;x<Scala.XFIELDS;x++)
			{
				if((currentPlayerIsWhite&&fields[y][x]==Fields.WHITE)||((!currentPlayerIsWhite)&&fields[y][x]==Fields.BLACK))
				{
					for(int vy=-1;vy<=1;vy++)
					{
						int movY = y+vy;
						if(movY>=0&&movY<14)
						{
							for(int vx=-1;vx<=1;vx++)
							{
								int movX = x+vx;
								if(movX>=0&&movX<11&&(vx!=0||vy!=0))//dla ka¿dego przyleg³ego pola
								{
									Fields field = fields[movY][movX];
									//if((field==Fields.BLACKBASE&&currentPlayerIsWhite)||(field==Fields.WHITEBASE&&!currentPlayerIsWhite))
									//	System.out.println("WZIIIIII"+currentPlayerIsWhite);
									if(field==Fields.FREE||
										(field==Fields.BLACKBASE&&currentPlayerIsWhite)||
											(field==Fields.WHITEBASE&&!currentPlayerIsWhite))//o ile jest puste
									{
										State newState = new State(this);
										if(newState.checkAndUpdateState(new Move(x,y,movX,movY)))//jesli ruch jest wykonalny
										{
											newState.currentPlayerIsWhite = !newState.currentPlayerIsWhite;
											successors.add(newState);//dodajemy
										}
									}
									else if(field==Fields.WHITE||field==Fields.BLACK)//na s¹siednim polu kamieñ, szukamy skoków
									{
										getJumps(vx,vy,x,y,successors,StateCopy(this));//!!!!!!!!!!
									}
								}
							}
						}
					}
				}
			}
			if(shift)
				y=14-y;
		}
		Random random = new Random();
		int j=successors.size();
		for(int i=0; i<j; i++)
		{
			int x = random.nextInt(j-i);
			Collections.swap(successors,i,x);
		}
		gotStates = true;
	}
	/*}}}*/

	/*{{{getJumps()*/
	void getJumps(int dirX,int dirY,int posX,int posY,ArrayList<State> list,State prevState)
	{
		if(dirX==0&&dirY==0)
			return;

		int newPosX = posX + dirX;
		int newPosY = posY + dirY;
		Fields field;
		while(newPosX>=0&&newPosX<11&&newPosY>=0&&newPosY<14)
		{
			field = prevState.fields[newPosY][newPosX];
			if(field==Fields.OUT)
				break;
			if(field==Fields.FREE||field==Fields.WHITEBASE||field==Fields.BLACKBASE)
			{
				State tempState = new State(prevState);
				if(tempState.checkAndUpdateState(new Move(posX,posY,newPosX,newPosY)))
				{
					if(tempState.fields[newPosY][newPosX]==Fields.WHITE)
						tempState.currentPlayerIsWhite = false;
					else
						tempState.currentPlayerIsWhite = true;
					list.add(tempState);
				}
				break;
			}

			newPosX+=dirX;
			newPosY+=dirY;
		}
	}
	/*}}}*/

	/*{{{sign()*/
	public static int sign(int a)
	{
		if(a>0)
			return 1;
		if(a<0)
			return -1;
		return 0;
	}
	/*}}}*/

	/*{{{checkAndUpdateState()*/
	public boolean checkAndUpdateState(Move move)
	{
		int fromX = move.ax;
		int fromY = move.ay;
		int toX = 	move.bx;
		int toY = 	move.by;

		int whiteCountPrev = whiteCount;
		int blackCountPrev = blackCount;

		fields[toY][toX] = fields[fromY][fromX];
		fields[fromY][fromX] = Fields.FREE;
		if(fields[0][5]==Fields.BLACK||fields[13][5]==Fields.WHITE)
			return false;

		boolean[][] connected = new boolean[14][11];
		for(int y=0;y<14;y++)
			for(int x=0;x<11;x++)
				connected[y][x] = false;
		
		connect(connected,toX,toY);
		/*for(int y=0;y<14;y++)
		{
			System.out.println();
			for(int x=0;x<11;x++)
				if(connected[y][x])
					System.out.print("1 ");
				else
					System.out.print("0 ");
		}
		System.out.println();
		System.out.println();*/

		whiteCount=0;
		blackCount=0;

		for(int y=0;y<14;y++)
		{
			for(int x=0;x<11;x++)
			{
				if((!connected[y][x])&&(fields[y][x]==Fields.WHITE||fields[y][x]==Fields.BLACK))
					fields[y][x] = Fields.FREE;
				else if(connected[y][x]&&fields[y][x]==Fields.WHITE)
					whiteCount++;
				else if(connected[y][x]&&fields[y][x]==Fields.BLACK)
					blackCount++;
			}
		}
		if(whiteCount==0||blackCount==0)
		{
			//System.out.println("Nie mo¿na rozdzieliæ obu kolorów.");
			return false;
		}
		if((((blackCountPrev-blackCount)+(whiteCountPrev-whiteCount))==(whiteCount+blackCount))
				&&(whiteCountPrev==whiteCount||blackCountPrev==blackCount))
		{
			//System.out.println("Nie mo¿na stworzyæ równolicznych grup, jeœli obie nie zawieraj¹ ró¿nych kolorów.");
			return false;
		}
		if(((blackCountPrev-blackCount)+(whiteCountPrev-whiteCount))>(whiteCount+blackCount))//TEST!!!!!!!!!!!
		{
			//System.out.println("Pion nie nie mo¿e trafiæ do mniej licznej grupy.");
			return false;
		}
		//currentPlayerIsWhite = !currentPlayerIsWhite;
		return true;
	}
	/*}}}*/

	/*{{{connect()*/
	private void connect(boolean[][] connected,int X,int Y)
	{
		connected[Y][X] = true;
		for(int y=0;y<3;y++)
		{
			int newY = Y-1+y;
			if((newY>=0)&&(newY<14))
			{
				for(int x=0;x<3;x++)
				{
					int newX = X-1+x;
					if((x!=1||y!=1)&&(newX>=0)&&(newX<11))
						if((fields[newY][newX]==Fields.WHITE||fields[newY][newX]==Fields.BLACK)&&!connected[newY][newX])
							connect(connected,newX,newY);
				}
			}
		}
	}
	/*}}}*/

	/*{{{constants*/
	public Fields[][] fields;
	public int whiteCount=0;
	public int blackCount=0;
	public boolean gotStates = false;
	ArrayList<State> successors = new ArrayList<State>();
	public boolean currentPlayerIsWhite;

	private static final int JUMPDEPTH = 1;
	/*}}}*/
}

