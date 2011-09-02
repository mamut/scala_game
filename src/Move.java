public class Move
{
	public Move(int ax,int ay,int bx,int by)
	{
		this.ax = ax;
		this.ay = ay;
		this.bx = bx;
		this.by = by;
	}

	public Move copy()
	{
		Move copy = new Move(ax,ay,bx,by);
		return copy;
	}

	public int ax;
	public int ay;
	public int bx;
	public int by;
}
