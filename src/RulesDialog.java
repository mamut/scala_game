import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Okno dialogowe, w którym prezentowane s¹ zasady rozgrywki.
 */
public class RulesDialog extends JDialog
{
	final static String tekst = 
"<html><body bgcolor=#F0F0F0>"+
"<h1>Zasady<h1><hr>"+

"<h2>Grupa</h2>"+ 
"<p>Zbiór po³¹czonych (poziomo, pionowo b¹dŸ na skos) pionków.</p>"+

"<h2>Tura</h2>"+
"<p>W ka¿ej turze gracz wykonuje ruch b¹dŸ skok jednym ze swoich pionków.</p>"+
"<p>Ruch - przesuniêcie pionka na jedno z s¹siednich (poziomo, pionowo b¹dŸ na skos) pustych pól.</p>"+
"<p>Skok - pionek mo¿e przeskakiwaæ nad innymi pionkami (dowolnego koloru), l¹duj¹c na przeciwleg³ym pustym polu. "+
"<p>Nie mo¿na umieœciæ swojego pionka we w³asnej bazie.</p>"+

"<h2>Po³¹czenie</h2>"+ 
"<p>Po ka¿dym ruchu b¹dŸ skoku pionek musi po³¹czony (pionowo, poziomo b¹dŸ na skos) z innym pionkiem ze swojej grupy.</p>"+
"<p>W wyniku ruchu nie mog¹ powstaæ dwie równoliczne grupy, je¿eli obie zawieraj¹ pionki obu graczy.</p>"+
"<p>Nie mo¿na rozdzieliæ zupe³nie pionków obu graczy.</p>"+

"<h2>Bicie</h2>"+ 
"<p>Jeœli po ruchu b¹dŸ skoku na planszy znajduj¹ siê pionku nie po³¹czone z grup¹ (t¹ sam¹, do której nale¿y poruszony pionek), "+
"zostaj¹ one zbite (niezale¿nie od koloru). To gwarantuje, ¿e po ka¿dym ruchu na planszy pozostaje tylko jedna grupa.</p>"+
 
"<h2>Cel gry</h2>"+
"<p>Wygrywa gracz, który wprowadzi swojego pionka do bazy przeciwnika.</p>"+

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

