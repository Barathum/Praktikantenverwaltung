package praktikantenverwaltung;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Vector;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class PraktikantenVerwaltung_AuslastDiagramme extends JFrame implements ActionListener{

	/**
	 * erstellen der Fields
	 */
	private static final long serialVersionUID = 1L;
	private JPanel mainPanel;
	
	public PraktikantenVerwaltung_AuslastDiagramme(PraktikantenVerwaltung_Control control , PraktikantenVerwaltung_Modell model){
//		setDatenPrakt(_control.ArrayListtoArray(Tabellen_Eintraege));
		setResizable(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(20, 20, 1280, 720);
		this.setTitle("Auslastungsdiagramm");
		
		mainPanel = new JPanel();
		getContentPane().add(mainPanel, BorderLayout.CENTER);
		mainPanel.setLayout(new CardLayout(0, 0));
		
		JPanel panel_auslast = new JPanel();
		mainPanel.add(panel_auslast, "card_4");
		GroupLayout gl_panel_auslast = new GroupLayout(panel_auslast);
		gl_panel_auslast.setHorizontalGroup(
			gl_panel_auslast.createParallelGroup(Alignment.LEADING)
				.addGap(0, 1264, Short.MAX_VALUE)
		);
		gl_panel_auslast.setVerticalGroup(
			gl_panel_auslast.createParallelGroup(Alignment.LEADING)
				.addGap(0, 682, Short.MAX_VALUE)
		);
		panel_auslast.setLayout(gl_panel_auslast);
		
	}
	//Sorgt dafür das beim Drücken der Enter-Taste weiter geschalten wird(neues Feld)
	Action enterAction = new AbstractAction()
	{
		private static final long serialVersionUID = 1L;

		@Override
	    public void actionPerformed(ActionEvent e)
	    {
    		KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
	        manager.getFocusOwner().transferFocus();
	    }
	};
	public void actionPerformed(ActionEvent evt) {
		evt.getSource();
	}
}
