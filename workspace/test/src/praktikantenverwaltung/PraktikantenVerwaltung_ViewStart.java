package praktikantenverwaltung;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import org.apache.commons.io.FileUtils;

public class PraktikantenVerwaltung_ViewStart extends JFrame {

	
	private static final long serialVersionUID = 1L;
	/**
	 * erstellen der Fields
	 */
	private JPanel mainPanel;
	private String tempFolder = new String("temp");
	JMenuItem menuNeuerPraktikant = new JMenuItem("Neuer Praktikant");
	JMenuItem menuNeuerAnsprechpartner = new JMenuItem("Neuer Ansprechpartner");
	JMenuItem menuAllePraktikanten = new JMenuItem("Zeige Praktikantentabelle");
	JMenuItem menuAlleAnsprechpartner = new JMenuItem("Zeige Ansprechpartnertabelle");

	/**
	 * Create the frame.
	 */
	public PraktikantenVerwaltung_ViewStart() {
		setResizable(true);
		setBounds(5, 5, 1280, 720);
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		
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
		    this.addWindowListener(new WindowAdapter() {
				@Override
				public void windowOpened(WindowEvent e) {
				}
				@Override
				public void windowIconified(WindowEvent e) {
				}
				@Override
				public void windowDeiconified(WindowEvent e) {
				}
				@Override
				public void windowDeactivated(WindowEvent e) {
				}
				@Override
				public void windowClosing(WindowEvent e) {
//					int confirmed = JOptionPane.showConfirmDialog(null, 
//					        "Wirklich Schließen? Alle nicht gespeicherten Daten gehen verloren!", "",
//					        JOptionPane.YES_NO_OPTION);
//					    if (confirmed == JOptionPane.YES_OPTION) {
					    	try {
								FileUtils.cleanDirectory(new File(tempFolder));
							} catch (IOException e1) {
								System.out.println("Der Ordner " + tempFolder + "existiert nicht.");
//								e1.printStackTrace();
							}
					    	System.exit(0);
//					    }
				}
				@Override
				public void windowClosed(WindowEvent e) {
					
				}
				@Override
				public void windowActivated(WindowEvent e) {
				}
			});
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
