import java.util.*;
public class ZobristTable
{
	public ZobristTable(int _x,int _y,int _z)
	{
		Random random = new Random();
		turn = random.nextLong();
		hash = new long[_x][_y][_z];
		for(int x=0;x<_x;x++)
			for(int y=0;y<_y;y++)
				for(int z=0;z<_z;z++)
					hash[x][y][z] = random.nextLong();
	}

	long hash[][][];
	long turn;
}
