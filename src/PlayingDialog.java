import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Okno dialogowe, w którym prezentowane s¹ zasady rozgrywki.
 */
public class PlayingDialog extends JDialog
{
	final static String tekst = 
"<html><body bgcolor=#F0F0F0>"+
"<h1>Jak graæ<h1><hr>"+
"<p>Aby zaznaczyæ pionek, kliknij go.</p>"+
"<p>Aby wykonaæ ruch zaznaczonym pionkiem, kliknij dowolne puste pole. Jeœli ruch nie narusza regu³ gry, zostanie wykonany.</p>"+
"<p>Aby odznaczyæ pionek, kliknij go ponownie.</p>"+
"<p>Pionek widoczny w prawym dolnym rogu oznacza bie¿¹cego gracza. Jeœli obok widnieje klepsydra, ruch nale¿y do gracza komputerowego.</p>"+
"<p>Poziom trudnoœci mo¿na modyfikowaæ w menu \"Opcje\".</p>"+
"</body></html>";

	public PlayingDialog(JFrame parent)
	{
		super(parent,"Jak graæ",false);

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

