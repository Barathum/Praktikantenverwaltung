package praktikantenverwaltung;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.text.DefaultFormatterFactory;

import org.jdesktop.swingx.JXDatePicker;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import org.jdesktop.swingx.calendar.DatePickerFormatter;

public class PraktikantenVerwaltung_ViewTabelleAnspr extends JFrame {
	
	/**
	 * erstellen der Fields
	 */
	private JPanel contentPane;
	private JPanel mainPanel;
	JMenuItem menuEintrag = new JMenuItem("Neuer Eintrag");
	JMenuItem menuSuche = new JMenuItem("Suche Praktikanten");
	JMenuItem menuAllePraktikanten = new JMenuItem("Zeige alle Praktikanten");
	JMenuItem menuAnsprechpartner = new JMenuItem("Suche Ansprechpartner");
	JMenuItem menuAlleAnsprechpartner = new JMenuItem("Zeige alle Ansprechpartner");
	private JTable table_suche;
	private JScrollPane scrollPane_SuchlisteAnspr;
	private String[] spaltennamenprak = {"ID",
            "Nachname",
            "Vorname",
            "Status",
            "Startdatum",
            "Enddatum", 
            "Anmerkung",
            "Letzte Änderung"};
	private Object[][] datenprak;
	private String[] spaltennamenansprech = {
            "Nachname",
            "Vorname",
            "Telefonnummer",
            "E-Mail-Adresse", 
            "Abteilung", 
            "Raumnummer"};
	private Object[][] datenansprech;
	private Vector comboBoxItems_schule;
	private Integer idAnspr1 = new Integer(0);
	private Integer idAnspr2 = new Integer(0);
	private Integer idAnspr3 = new Integer(0);
	private Integer idAnsprBearb = new Integer(0);
	private String letzteAenderung;
	private boolean Ansprbearb1gedrueckt = false;
	private boolean Ansprbearb2gedrueckt = false;
	private boolean Ansprbearb3gedrueckt = false;
	private Vector comboBoxItems_wohn;
	private JButton buttonAnsprBearb;
	private JButton buttonAnsprLoesch;
	private JButton buttonAnsprInfo;

	/**
	 * Create the frame.
	 */
	public PraktikantenVerwaltung_ViewTabelleAnspr() {
		setResizable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(5, 5, 1280, 720);
		
		
		mainPanel = new JPanel();
		getContentPane().add(mainPanel, BorderLayout.CENTER);
		mainPanel.setLayout(new CardLayout(0, 0));
		
		 
		 JPanel panel_tabellenAnspr = new JPanel();
		 mainPanel.add(panel_tabellenAnspr, "card_6");
		 panel_tabellenAnspr.setLayout(new BorderLayout(0, 0));
		 
		 scrollPane_SuchlisteAnspr = new JScrollPane();
		 panel_tabellenAnspr.add(scrollPane_SuchlisteAnspr);
		 scrollPane_SuchlisteAnspr.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		 
		 JPanel panel_9 = new JPanel();
		 panel_tabellenAnspr.add(panel_9, BorderLayout.SOUTH);
		 
		 buttonAnsprBearb = new JButton("Bearbeiten");
		 
		 buttonAnsprLoesch = new JButton("L\u00F6schen");
		 
		 buttonAnsprInfo = new JButton("Info");
		 GroupLayout gl_panel_9 = new GroupLayout(panel_9);
		 gl_panel_9.setHorizontalGroup(
		 	gl_panel_9.createParallelGroup(Alignment.LEADING)
		 		.addGroup(gl_panel_9.createSequentialGroup()
		 			.addGap(20)
		 			.addComponent(buttonAnsprBearb)
		 			.addGap(18)
		 			.addComponent(buttonAnsprLoesch, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
		 			.addGap(18)
		 			.addComponent(buttonAnsprInfo, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
		 			.addContainerGap(953, Short.MAX_VALUE))
		 );
		 gl_panel_9.setVerticalGroup(
		 	gl_panel_9.createParallelGroup(Alignment.LEADING)
		 		.addGroup(gl_panel_9.createSequentialGroup()
		 			.addGap(9)
		 			.addGroup(gl_panel_9.createParallelGroup(Alignment.BASELINE)
		 				.addComponent(buttonAnsprBearb)
		 				.addComponent(buttonAnsprLoesch)
		 				.addComponent(buttonAnsprInfo))
		 			.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		 );
		 panel_9.setLayout(gl_panel_9);
	}

}
