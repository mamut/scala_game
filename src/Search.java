import java.util.*;

public class Search
{
	static ZobristTable zobristTable = new ZobristTable(Scala.XFIELDS,Scala.YFIELDS-2,4);

	public static HashMap<Long,TTVal> TranspositionTable = new HashMap<Long,TTVal>();

	public static boolean timeIsUp = false;

	/*{{{alphaBeta()*/
	public static int alphaBeta(State s, int depth, int alpha, int beta)
	{
		if(timeIsUp||depth==0||isTerminalNode(s)) return eval(s);

		for(int child=0;child<numOfSucc(s);child++)
		{
			int val = -alphaBeta(succ(s,child),depth-1,-beta,-alpha);
			if(val > alpha) alpha = val;
			if(alpha >= beta) return beta;
		}
		return alpha;
	}
	/*}}}*/

/*{{{alphaBetaFSTT*/
	public static int alphaBetaFSTT(State s, int depth, int alpha, int beta)
	{
		int prevAlpha = alpha;	
		if(timeIsUp||depth==0||isTerminalNode(s)) return eval(s);
		
		TTVal tt = TTLookup(s);
		if(tt!=null && tt.depth >= depth)
		{
			if(tt.bound == Bound.LOWER) alpha = Math.max(alpha,tt.val);
			if(tt.bound == Bound.UPPER) beta = Math.min(beta,tt.val);
			if(tt.bound == Bound.ACCURATE) alpha = beta = tt.val;
			if(alpha>=beta) return tt.val;
		}
		if(tt!=null)
		{//??????????????//
		}

		int best = Integer.MIN_VALUE;

		for(int child=0;child<numOfSucc(s);child++)
		{
			int val = -alphaBetaFSTT(succ(s,child),depth-1,-beta,-alpha);
			if(val > best) best = val;
			if(best >= beta) break;
			if(best > alpha) alpha = best;
		}
		SaveTT(s, best, depth, prevAlpha, beta);
		return best;
	}
	public static TTVal TTLookup(State s)
	{
		return TranspositionTable.get(zobristHash(s));
	}

	public static void SaveTT(State s,int val,int depth,int alpha,int beta)
	{
		Bound bound;

		if(val <= alpha) bound = Bound.UPPER;
		else if(val >= beta) bound = Bound.LOWER;
		else bound = Bound.ACCURATE;

		InsertToTT(s,val,bound,depth);
	}

	private static void InsertToTT(State s,int val,Bound bound,int depth)
	{
		TranspositionTable.put(zobristHash(s),new TTVal(val,bound,depth));
	}
/*}}}*/

	/*{{{negascout*/
	public static int negascout(State s,int depth,int alpha,int beta)
	{
		if(timeIsUp||depth==0||isTerminalNode(s)) return eval(s);
		int b = beta;

		for(int child=0;child<numOfSucc(s);child++)
		{
			int val = -negascout(succ(s,child),depth-1,-b,-alpha);
			if(val > alpha) alpha = val;
			if(alpha >= beta) return alpha;
			if(alpha>=b)
			{
				alpha = -negascout(succ(s,child),depth-1,-beta,-alpha);
				if(alpha>=beta)
					return alpha;
			}
			b = alpha+1;
		}
		return alpha;
	}
	/*}}}*/

	/*{{{negascoutTT*/
	public static int negascoutTT(State s,int depth,int alpha,int beta)
	{
		int prevAlpha = alpha;	
		if(timeIsUp||depth==0||isTerminalNode(s)) return eval(s);
		TTVal tt = TTLookup(s);
		if(tt!=null && tt.depth >= depth)
		{
			if(tt.bound == Bound.LOWER) alpha = Math.max(alpha,tt.val);
			if(tt.bound == Bound.UPPER) beta = Math.min(beta,tt.val);
			if(tt.bound == Bound.ACCURATE) alpha = beta = tt.val;
			if(alpha>=beta) return tt.val;
		}
		if(tt!=null)
		{//??????????????//
		}

		int b = beta;
		for(int child=0;child<numOfSucc(s);child++)
		{
			int val = -negascoutTT(succ(s,child),depth-1,-b,-alpha);

			if(val > alpha) alpha = val;
			if(alpha >= beta) return alpha;
			if(alpha>=b)
			{
				alpha = -negascoutTT(succ(s,child),depth-1,-beta,-alpha);
				if(alpha>=beta)
					return alpha;
			}
			b = alpha+1;
		}
		SaveTT(s, alpha, depth, prevAlpha, beta);
		return alpha;
	}
	/*}}}*/

	/*{{{highest*/
	public static int highest(State s)
	{
		return highest(s,s.currentPlayerIsWhite);
	}

	public static int highest(State s,boolean who)
	{
		for(int y=1;y<13;y++)
		{
			if(!who)
				y=13-y;
			for(int x=0;x<11;x++)
			{
				if(who&&s.fields[y][x]==Fields.WHITE)
					return y;
				else if(!who&&s.fields[y][x]==Fields.BLACK)
					return y;
			}
			if(!who)
				y=13-y;
		}
		return 0;
	}
	/*}}}*/

	/*{{{lowest*/
	public static int lowest(State s)
	{
		return lowest(s,s.currentPlayerIsWhite);
	}

	public static int lowest(State s,boolean who)
	{
		for(int y=12;y>0;y--)
		{
			if(!who)
				y=13-y;
			for(int x=0;x<11;x++)
			{
				if(who&&s.fields[y][x]==Fields.WHITE)
					return y;
				else if(!who&&s.fields[y][x]==Fields.BLACK)
					return y;
			}
			if(!who)
				y=13-y;
		}
		return 0;
	}
	/*}}}*/

	/*{{{isTerminalNode*/
	public static boolean isTerminalNode(State s)
	{
		if(s.fields[13][5]==Fields.BLACK||s.fields[0][5]==Fields.WHITE)
			return true;
		if(Scala.remis(s))
			return true;
		return false;
	}
	/*}}}*/

	/*{{{eval()*/
	public static int eval(State s)
	{
		if(s.fields[0][5]==Fields.WHITE)
			return Integer.MAX_VALUE-1;
		if(s.fields[13][5]==Fields.BLACK)
			return Integer.MIN_VALUE+1;
		if(Scala.remis(s))
			return 0;

		int value = 0;
		for(int y=1;y<13;y++)
		{
			for(int x=0;x<11;x++)
			{
				if(s.fields[y][x]==Fields.WHITE)
				{
					value+=(14-y)*(14-y);
					if(y<=5)
					{
						if(x==5)
							value+=40;
						else if(x==4||x==6)
							value+=20;
						else if(x==3||x==7)
							value+=10;
					}
				}
				else if(s.fields[y][x]==Fields.BLACK)
				{
					value-=(y)*(y);
					if(y>=8)
					{
						if(x==5)
							value-=40;
						else if(x==4||x==6)
							value-=20;
						else if(x==3||x==7)
							value-=10;
					}
				}
			}
			value+=(s.whiteCount)*(s.whiteCount)*10;
			value-=(s.blackCount)*(s.blackCount)*10;
		}
		return value;
	}
	/*}}}*/

	/*{{{zobristHash*/
	public static long zobristHash(State s)
	{
		long hash = 0;
		for(int y=1;y<Scala.YFIELDS-1;y++)
			for(int x=0;x<Scala.XFIELDS;x++)
			{
				Fields field = s.fields[y][x];
				int z;
				if(field==Fields.WHITE)
					z=0;
				else if(field==Fields.BLACK)
					z=1;
				else if(field==Fields.FREE)
					z=2;
				else
					z=3;
				hash^=zobristTable.hash[x][y-1][z];
			}
		if(s.currentPlayerIsWhite)
			hash^=zobristTable.turn;
		return hash;
	}
	/*}}}*/

	/*{{{succ()*/
	private static State succ(State s,int child)
	{
		if(!s.gotStates)
			s.generateSucc();
		return s.successors.remove(0);
	}
	/*}}}*/

	/*{{{numOfSucc*/
	private static int numOfSucc(State s)
	{
		if(!s.gotStates)
			s.generateSucc();
		return s.successors.size();
	}
	/*}}}*/
}
