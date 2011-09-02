import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *Okno dialogowe, wyœwietlaj¹ce informacje o programie i jego twórcy.
 */
public class AuthorDialog extends JDialog
{
	final static String tekst = 
		"<html><h1>Scala v.0.99</h1><hr>Autor: Krzysztof Urban"+
		"<br>Data: 9 marca 2009<br>Projekt zaliczeniowy z przedmiotu Sztuczna inteligencja.</html>";

	public AuthorDialog(JFrame parent)
	{
		super(parent,"O autorze",false);

		setLocationByPlatform(true);
		setSize(450,200);
		setResizable(false);

		add( new JLabel(tekst,JLabel.CENTER),BorderLayout.CENTER);

		JButton ok = new JButton("Zamknij");
		ok.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent ae)
				{
					setVisible(false);
				}
			});

		JPanel panel = new JPanel();
		panel.add(ok);
		add(panel,BorderLayout.SOUTH);

	}
}

