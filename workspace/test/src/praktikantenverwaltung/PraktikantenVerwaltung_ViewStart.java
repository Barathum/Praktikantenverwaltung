package praktikantenverwaltung;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class PraktikantenVerwaltung_ViewStart extends JFrame {

	
	private static final long serialVersionUID = 1L;
	/**
	 * erstellen der Fields
	 */
	private JPanel mainPanel;
	JMenuItem menuNeuerPraktikant = new JMenuItem("Neuer Praktikant");
	JMenuItem menuNeuerAnsprechpartner = new JMenuItem("Neuer Ansprechpartner");
	JMenuItem menuAllePraktikanten = new JMenuItem("Zeige Praktikantentabelle");
	JMenuItem menuAlleAnsprechpartner = new JMenuItem("Zeige Ansprechpartnertabelle");

	/**
	 * Create the frame.
	 */
	public PraktikantenVerwaltung_ViewStart() {
		setResizable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(5, 5, 1280, 720);
		
		
		mainPanel = new JPanel();
		getContentPane().add(mainPanel, BorderLayout.CENTER);
		mainPanel.setLayout(new CardLayout(0, 0));
		
		JPanel panel_Start = new JPanel();
		panel_Start.setLayout(null);
		mainPanel.add(panel_Start, "card_3");
		
		 /**
		 * Anfügen der Menüeinträge in das Startmenu
		 */
		 JMenuBar menu = new JMenuBar();
		    JMenu start = new JMenu("Start");
		    start.add(menuNeuerPraktikant);
		    start.add(menuNeuerAnsprechpartner);
		    start.add(menuAllePraktikanten);
		    start.add(menuAlleAnsprechpartner);
		    menu.add(start);
		    getContentPane().add(menu, BorderLayout.NORTH);
	}
	public void setNeuerPraktListener(ActionListener l){ 
        this.menuNeuerPraktikant.addActionListener(l); 
	}
	public void setNeuerAnsprListener(ActionListener l){ 
        this.menuNeuerAnsprechpartner.addActionListener(l); 
	}
	public void setTabellePraktListener(ActionListener l){ 
        this.menuAllePraktikanten.addActionListener(l); 
	}
	public void setTabelleAnsprListener(ActionListener l){ 
        this.menuAlleAnsprechpartner.addActionListener(l); 
	}

}
