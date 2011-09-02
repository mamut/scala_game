import java.util.*;
public class MoveSet extends ArrayDeque<Move>
{
	public MoveSet copy()
	{
		MoveSet copy = new MoveSet();
		for(Move move : this)
			copy.add(move.copy());
		return copy;
	}
}
