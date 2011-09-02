import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Okno dialogowe, w kt�rym prezentowane s� zasady rozgrywki.
 */
public class RulesDialog extends JDialog
{
	final static String tekst = 
"<html><body bgcolor=#F0F0F0>"+
"<h1>Zasady<h1><hr>"+

"<h2>Grupa</h2>"+ 
"<p>Zbi�r po��czonych (poziomo, pionowo b�d� na skos) pionk�w.</p>"+

"<h2>Tura</h2>"+
"<p>W ka�ej turze gracz wykonuje ruch b�d� skok jednym ze swoich pionk�w.</p>"+
"<p>Ruch - przesuni�cie pionka na jedno z s�siednich (poziomo, pionowo b�d� na skos) pustych p�l.</p>"+
"<p>Skok - pionek mo�e przeskakiwa� nad innymi pionkami (dowolnego koloru), l�duj�c na przeciwleg�ym pustym polu. "+
"<p>Nie mo�na umie�ci� swojego pionka we w�asnej bazie.</p>"+

"<h2>Po��czenie</h2>"+ 
"<p>Po ka�dym ruchu b�d� skoku pionek musi po��czony (pionowo, poziomo b�d� na skos) z innym pionkiem ze swojej grupy.</p>"+
"<p>W wyniku ruchu nie mog� powsta� dwie r�wnoliczne grupy, je�eli obie zawieraj� pionki obu graczy.</p>"+
"<p>Nie mo�na rozdzieli� zupe�nie pionk�w obu graczy.</p>"+

"<h2>Bicie</h2>"+ 
"<p>Je�li po ruchu b�d� skoku na planszy znajduj� si� pionku nie po��czone z grup� (t� sam�, do kt�rej nale�y poruszony pionek), "+
"zostaj� one zbite (niezale�nie od koloru). To gwarantuje, �e po ka�dym ruchu na planszy pozostaje tylko jedna grupa.</p>"+
 
"<h2>Cel gry</h2>"+
"<p>Wygrywa gracz, kt�ry wprowadzi swojego pionka do bazy przeciwnika.</p>"+

"<p><b>Copyright (c) 1986 Jose W. Diaz - Skill Games</b></p>"+
"</html></body>";

	public RulesDialog(JFrame parent)
	{
		super(parent,"Zasady",false);

		setLocationByPlatform(true);
		setSize(450,600);
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

