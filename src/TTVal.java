public class TTVal
{
	TTVal(int val,Bound bound,int depth)
	{
		this.val = val;
		this.bound = bound;
		this.depth = depth;
	}

	public State bestmove = null;
	public int val;
	public Bound bound;
	public int depth;
}
