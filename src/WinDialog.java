import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class WinDialog extends JDialog
{
	public JLabel text;

	public WinDialog(JFrame parent)
	{
		super(parent,"Koniec gry",true);

		setSize(250,100);
		setResizable(false);
		setLocationByPlatform(true);

		text = new JLabel("",JLabel.CENTER);
		add(text,BorderLayout.CENTER);

		JButton ok = new JButton("Ok");
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

