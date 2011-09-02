import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Okno dialogowe, w kt�rym prezentowane s� zasady rozgrywki.
 */
public class PlayingDialog extends JDialog
{
	final static String tekst = 
"<html><body bgcolor=#F0F0F0>"+
"<h1>Jak gra�<h1><hr>"+
"<p>Aby zaznaczy� pionek, kliknij go.</p>"+
"<p>Aby wykona� ruch zaznaczonym pionkiem, kliknij dowolne puste pole. Je�li ruch nie narusza regu� gry, zostanie wykonany.</p>"+
"<p>Aby odznaczy� pionek, kliknij go ponownie.</p>"+
"<p>Pionek widoczny w prawym dolnym rogu oznacza bie��cego gracza. Je�li obok widnieje klepsydra, ruch nale�y do gracza komputerowego.</p>"+
"<p>Poziom trudno�ci mo�na modyfikowa� w menu \"Opcje\".</p>"+
"</body></html>";

	public PlayingDialog(JFrame parent)
	{
		super(parent,"Jak gra�",false);

		setLocationByPlatform(true);
		setSize(450,400);
		setResizable(false);

		JEditorPane textPane = new JEditorPane("text/html",tekst);
		textPane.setEditable(false);
		JScrollPane rulesInfo = new JScrollPane(textPane);
		add(rulesInfo,BorderLayout.CENTER);

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

