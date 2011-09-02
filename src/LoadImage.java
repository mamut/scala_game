import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;

import java.net.URL;
import javax.imageio.*;

public class LoadImage
{
	public BufferedImage loadImage(String path)
	{
		URL url=null;
		try
		{
			url = getClass().getClassLoader().getResource(path);
			return ImageIO.read(url);
		} catch (Exception e)
		{
			System.out.println("Nie mo¿na znaleŸæ obrazka " + path +" w "+url);
			System.out.println("B³¹d: "+e.getClass().getName()+" "+e.getMessage());
			System.exit(0);
			return null;
		}
	}	
}
