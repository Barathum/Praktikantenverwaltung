package testpack;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicComboPopup;

import org.jdesktop.swingx.JXDatePicker;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

public class PraktikantenVerwaltung_View extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JPanel mainPanel;
	
	JMenuItem menuEintrag = new JMenuItem("Neuer Eintrag");
	JMenuItem menuSuche = new JMenuItem("Suche Praktikanten");
	JMenuItem menuAllePraktikanten = new JMenuItem("Zeige alle Praktikanten");
	JMenuItem menuAnsprechpartner = new JMenuItem("Suche Ansprechpartner");
	JMenuItem menuAlleAnsprechpartner = new JMenuItem("Zeige alle Ansprechpartner");
	JMenuItem menuAnsprechpartnerbearbeiten = new JMenuItem("Bearbeite Ansprechpartner");
	private JTextField textField_id;
	private JTextField textField_nn;
	private JTextField textField_vn;
	private JXDatePicker datePicker_gb;
	private JTextField textField_tele;
	private JTextField textField_mail;
	private JTextField textField_mobil;
	private JTextField textField_haus;
	private JTextField textField_gnn;
	private JTextField textField_gvn;
	private JXDatePicker datePicker_startdatum;
	private JXDatePicker datePicker_enddatum;
	private JTextField textField_vnansprech;
	private JTextField textField_teleansprech;
	private JTextField textField_mailansprech;
	private JTextField textField_abteilungansprech;
	private JTextField textField_raumnransprech;
	private JTextField textField_suchbegriffprak;
	private JTextField textField_suchbegriffansp;
	private JTable table_suche;
	private JScrollPane scrollPane_Suchliste;
	private JButton btn_NachrichtSchreiben;
	private String[] spaltennamenprak = {"ID",
            "Nachname",
            "Vorname",
            "Status",
            "Startdatum",
            "Enddatum", 
            "Anmerkung",
            "Letzte Änderung"};
	private Object[][] datenprak = {
		    {"1", "Smith",
		     "John","Zusage", "11.02.2014", "28.01.2015", "lol","01.02.2004"},
		     {"2", "blubb",
			     "John","Absage", "13.02.2014", "01.03.2015", "ssd","03.22.2015"}
		};
	private String[] spaltennamenansprech = {
            "Nachname",
            "Vorname",
            "Telefonnummer",
            "E-Mail-Adresse", 
            "Abteilung", 
            "Raumnummer"};
	private Object[][] datenansprech = {
		    {"Ansprechmichnich", "LOL",
		     "0123456789", "asasa@asas.de", "sadasd", "1"}
		};
	private JComboBox comboBox_schule;
	private Vector comboBoxItems_schule;
	private JComboBox comboBox_wohn;
	private JComboBox comboBox_schulform;
	private JComboBox comboBox_nnansprech;
	private JTextField txtDeutschland;
	private JComboBox comboBox_str;
	private JComboBox comboBox_geburtsort;
	private JTextField textField_plz;
	private JTextField textField_RaumAnsprBearb;
	private JTextField textField_AbteilAnsprBearb;
	private JTextField textField_MailAnsprBearb;
	private JTextField textField_TelAnsprBearb;
	private JTextField textField_VornameAnsprBearb;
	private JTextField textField_NameAnsprBearb;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PraktikantenVerwaltung_View frame = new PraktikantenVerwaltung_View();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PraktikantenVerwaltung_View() {
		setResizable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(5, 5, 1280, 720);
		
		
		mainPanel = new JPanel();
		getContentPane().add(mainPanel, BorderLayout.CENTER);
		mainPanel.setLayout(new CardLayout(0, 0));
		
		
		JPanel panel_Steckbrief = new JPanel();
		mainPanel.add(panel_Steckbrief, "card_1");
		
		JPanel panel_ansprbearb = new JPanel();
		mainPanel.add(panel_ansprbearb, "card_5");
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		
		JLabel label_4 = new JLabel("Vorname:");
		
		JLabel label_5 = new JLabel("Nachname(Ansprechpartner):");
		
		JLabel label_6 = new JLabel("E-Mail-Adresse:");
		
		JLabel label_7 = new JLabel("Telefonnummer:");
		
		JLabel label_8 = new JLabel("Abteilung:");
		
		JLabel label_9 = new JLabel("Raumnummer:");
		
		textField_RaumAnsprBearb = new JTextField();
		textField_RaumAnsprBearb.setColumns(10);
		
		textField_AbteilAnsprBearb = new JTextField();
		textField_AbteilAnsprBearb.setColumns(10);
		
		textField_MailAnsprBearb = new JTextField();
		textField_MailAnsprBearb.setColumns(10);
		
		textField_TelAnsprBearb = new JTextField();
		textField_TelAnsprBearb.setColumns(10);
		
		textField_VornameAnsprBearb = new JTextField();
		textField_VornameAnsprBearb.setColumns(10);
		
		textField_NameAnsprBearb = new JTextField();
		textField_NameAnsprBearb.setColumns(10);
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addComponent(label_4)
						.addComponent(label_5)
						.addGroup(gl_panel_2.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(label_6, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(label_7, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addComponent(label_8, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_9, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE))
					.addGap(39)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING, false)
						.addComponent(textField_RaumAnsprBearb)
						.addComponent(textField_AbteilAnsprBearb)
						.addComponent(textField_MailAnsprBearb)
						.addComponent(textField_TelAnsprBearb)
						.addComponent(textField_VornameAnsprBearb)
						.addComponent(textField_NameAnsprBearb, GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE))
					.addGap(84))
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_5)
						.addComponent(textField_NameAnsprBearb, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_4)
						.addComponent(textField_VornameAnsprBearb, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_7)
						.addComponent(textField_TelAnsprBearb, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_6)
						.addComponent(textField_MailAnsprBearb, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_8)
						.addComponent(textField_AbteilAnsprBearb, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_9)
						.addComponent(textField_RaumAnsprBearb, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(23, Short.MAX_VALUE))
		);
		panel_2.setLayout(gl_panel_2);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		
		JLabel label_10 = new JLabel("Anmerkungen zum Einsatzort");
		label_10.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JLabel lblBearbeiteAnsprechpartner = new JLabel("Bearbeite  Ansprechpartner");
		lblBearbeiteAnsprechpartner.setFont(new Font("Tahoma", Font.BOLD, 14));
		GroupLayout gl_panel_ansprbearb = new GroupLayout(panel_ansprbearb);
		gl_panel_ansprbearb.setHorizontalGroup(
			gl_panel_ansprbearb.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_ansprbearb.createSequentialGroup()
					.addGroup(gl_panel_ansprbearb.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_ansprbearb.createSequentialGroup()
							.addGap(192)
							.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 432, GroupLayout.PREFERRED_SIZE)
							.addGap(68)
							.addGroup(gl_panel_ansprbearb.createParallelGroup(Alignment.LEADING)
								.addComponent(label_10, GroupLayout.PREFERRED_SIZE, 211, GroupLayout.PREFERRED_SIZE)
								.addComponent(scrollPane_3, GroupLayout.PREFERRED_SIZE, 256, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_panel_ansprbearb.createSequentialGroup()
							.addGap(442)
							.addComponent(lblBearbeiteAnsprechpartner, GroupLayout.PREFERRED_SIZE, 216, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(316, Short.MAX_VALUE))
		);
		gl_panel_ansprbearb.setVerticalGroup(
			gl_panel_ansprbearb.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_ansprbearb.createSequentialGroup()
					.addGap(98)
					.addComponent(lblBearbeiteAnsprechpartner)
					.addGap(85)
					.addComponent(label_10, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_panel_ansprbearb.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 186, GroupLayout.PREFERRED_SIZE)
						.addComponent(scrollPane_3, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(245, Short.MAX_VALUE))
		);
		
		JTextArea textArea_AnmerkOrtBearb = new JTextArea();
		scrollPane_3.setViewportView(textArea_AnmerkOrtBearb);
		panel_ansprbearb.setLayout(gl_panel_ansprbearb);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		
		JLabel lblNewLabel = new JLabel("Daten zur Person");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		
		JLabel lblStrae = new JLabel("Stra\u00DFe:");
		
		JLabel lblHausnummer = new JLabel("PLZ:");
		
		JLabel lblPlz = new JLabel("Land:");
		
		JLabel lblWohnort = new JLabel("Telefon:");
		
		textField_tele = new JTextField();
		textField_tele.setColumns(10);
		
		JLabel lblTelefon = new JLabel("E-Mail:");
		
		textField_mail = new JTextField();
		textField_mail.setColumns(10);
		
		JLabel lblMobilnummer = new JLabel("Mobiltelefon:");
		
		textField_mobil = new JTextField();
		textField_mobil.setColumns(10);
		
		JLabel lblMobilnummer_1 = new JLabel("Hausnummer:");
		
		textField_haus = new JTextField();
		textField_haus.setColumns(10);
		
		JLabel lblMailadresse = new JLabel("Wohnort:");
		
		JLabel lblGesetzlicherVertreter = new JLabel("Gesetzlicher Vertreter");
		lblGesetzlicherVertreter.setFont(new Font("Tahoma", Font.ITALIC, 11));
		
		textField_gnn = new JTextField();
		textField_gnn.setColumns(10);
		
		textField_gvn = new JTextField();
		textField_gvn.setColumns(10);
		
		JLabel label = new JLabel("Nachname:");
		
		JLabel label_1 = new JLabel("Vorname:");
		
		String comboBoxListe_land[] = {"Deutschland"};
		
		
		Vector comboBoxItems_wohn=new Vector();
	    comboBoxItems_wohn.add("");
	    comboBoxItems_wohn.add("Rethem");
	    comboBoxItems_wohn.add("Wolfenbüttel");
	    comboBoxItems_wohn.add("Braunschweig");
	    comboBoxItems_wohn.add("1");
	    comboBoxItems_wohn.add("5");
	    
		this.comboBox_wohn = new JComboBox(comboBoxItems_wohn);
		this.comboBox_wohn.setEditable(true);
		AutoCompleteDecorator.decorate(this.comboBox_wohn);
		
		Vector comboBoxItems_str=new Vector();
		comboBoxItems_str.add("");
		comboBoxItems_str.add("Lange Straße");
	    comboBoxItems_str.add("Kurz Straße");
	    comboBoxItems_str.add("Braunschweig");
	    comboBoxItems_str.add("1");
	    comboBoxItems_str.add("5");
	    
		this.comboBox_str = new JComboBox(comboBoxItems_str);
		this.comboBox_str.setEditable(true);
		this.comboBox_str.setUI(new BasicComboBoxUI() {
			           @Override  
			            protected JButton createArrowButton() {  
			                return new JButton() {  
			                    @Override  
		                  public int getWidth() {  
			                return 0;  
			                   }  
			               };  
			           }  
			        });
		this.comboBox_str.setBorder(BorderFactory.createLineBorder(Color.black));

		AutoCompleteDecorator.decorate(this.comboBox_str);
		
		txtDeutschland = new JTextField();
		txtDeutschland.setText("Deutschland");
		txtDeutschland.setColumns(10);
		
		textField_plz = new JTextField();
		textField_plz.setColumns(10);
		GroupLayout gl_panel_4 = new GroupLayout(panel_4);
		gl_panel_4.setHorizontalGroup(
			gl_panel_4.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_4.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
						.addComponent(lblStrae)
						.addComponent(lblHausnummer)
						.addComponent(lblPlz)
						.addComponent(lblMobilnummer)
						.addComponent(lblTelefon)
						.addComponent(lblWohnort))
					.addGap(25)
					.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_4.createSequentialGroup()
							.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
									.addGroup(gl_panel_4.createSequentialGroup()
										.addComponent(comboBox_str, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED))
									.addGroup(gl_panel_4.createSequentialGroup()
										.addComponent(textField_tele, 124, 124, Short.MAX_VALUE)
										.addGap(42))
									.addGroup(gl_panel_4.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(textField_mail, Alignment.LEADING)
										.addComponent(textField_mobil, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)))
								.addGroup(gl_panel_4.createSequentialGroup()
									.addComponent(textField_plz, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)))
							.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_4.createSequentialGroup()
									.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING, false)
											.addComponent(lblMailadresse, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
											.addComponent(lblMobilnummer_1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE))
										.addComponent(label_1)
										.addComponent(label, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
										.addComponent(comboBox_wohn, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)
										.addGroup(gl_panel_4.createParallelGroup(Alignment.TRAILING)
											.addComponent(textField_haus, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)
											.addComponent(textField_gnn, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)
											.addComponent(textField_gvn, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE))))
								.addComponent(lblGesetzlicherVertreter, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE))
							.addGap(35))
						.addGroup(gl_panel_4.createSequentialGroup()
							.addComponent(txtDeutschland, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())))
		);
		gl_panel_4.setVerticalGroup(
			gl_panel_4.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_4.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_4.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblStrae)
						.addComponent(lblMobilnummer_1)
						.addComponent(textField_haus, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBox_str, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_4.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblHausnummer)
						.addComponent(lblMailadresse)
						.addComponent(comboBox_wohn, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_plz, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_4.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPlz)
						.addComponent(txtDeutschland, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblGesetzlicherVertreter)
					.addGroup(gl_panel_4.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel_4.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_4.createParallelGroup(Alignment.BASELINE)
								.addComponent(textField_gnn, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(label))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_4.createParallelGroup(Alignment.BASELINE)
								.addComponent(textField_gvn, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(label_1))
							.addPreferredGap(ComponentPlacement.RELATED, 20, Short.MAX_VALUE))
						.addGroup(gl_panel_4.createSequentialGroup()
							.addGap(6)
							.addGroup(gl_panel_4.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblWohnort)
								.addComponent(textField_tele, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_4.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblTelefon)
								.addComponent(textField_mail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addGroup(gl_panel_4.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblMobilnummer)
						.addComponent(textField_mobil, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		panel_4.setLayout(gl_panel_4);
		
		JLabel lblKontaktdaten = new JLabel("Kontaktdaten");
		lblKontaktdaten.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		
		JLabel lblNameDerSchule = new JLabel("Name der Schule:");
		
		JLabel lblSchulform = new JLabel("Schulform:");
		
		JLabel lblSiemensprtnerschule = new JLabel("Siemens-Partnerschule:");
		
		JLabel lblAnmerkungen = new JLabel("Anmerkungen:");
		
		String comboBoxListe_partners[] = {"Ja", "Nein"};
		JComboBox comboBox_partners = new JComboBox(comboBoxListe_partners);
		
		JScrollPane scrollPane = new JScrollPane();
		
		Vector comboBoxItems_schule=new Vector();
	    comboBoxItems_schule.add("");
	    comboBoxItems_schule.add("Ostfalia");
	    comboBoxItems_schule.add("Blubb");
	    comboBoxItems_schule.add("...");
	    comboBoxItems_schule.add("1");
	    comboBoxItems_schule.add("5");
	    
		this.comboBox_schule = new JComboBox(comboBoxItems_schule);
		this.comboBox_schule.setEditable(true);
		AutoCompleteDecorator.decorate(this.comboBox_schule);
		
		Vector comboBoxItems_schulform=new Vector();
		comboBoxItems_schulform.add("");
		comboBoxItems_schulform.add("Real");
		comboBoxItems_schulform.add("Haupt");
		comboBoxItems_schulform.add("Gym");
		comboBoxItems_schulform.add("Gesamt");
		comboBoxItems_schulform.add("5");
	    
		this.comboBox_schulform = new JComboBox(comboBoxItems_schulform);
		this.comboBox_schulform.setEditable(true);
		AutoCompleteDecorator.decorate(this.comboBox_schulform);
		GroupLayout gl_panel_5 = new GroupLayout(panel_5);
		gl_panel_5.setHorizontalGroup(
			gl_panel_5.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_5.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_5.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 313, Short.MAX_VALUE)
						.addGroup(gl_panel_5.createSequentialGroup()
							.addComponent(lblNameDerSchule)
							.addPreferredGap(ComponentPlacement.RELATED, 105, Short.MAX_VALUE)
							.addComponent(comboBox_schule, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_5.createSequentialGroup()
							.addComponent(lblSchulform)
							.addPreferredGap(ComponentPlacement.RELATED, 138, Short.MAX_VALUE)
							.addComponent(comboBox_schulform, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_5.createSequentialGroup()
							.addComponent(lblSiemensprtnerschule)
							.addPreferredGap(ComponentPlacement.RELATED, 76, Short.MAX_VALUE)
							.addComponent(comboBox_partners, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblAnmerkungen))
					.addContainerGap())
		);
		gl_panel_5.setVerticalGroup(
			gl_panel_5.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_5.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_5.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNameDerSchule)
						.addComponent(comboBox_schule, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_5.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSchulform)
						.addComponent(comboBox_schulform, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_5.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSiemensprtnerschule)
						.addComponent(comboBox_partners, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblAnmerkungen)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		JTextArea textArea_anmerkschule = new JTextArea();
		textArea_anmerkschule.setLineWrap(true);
		scrollPane.setViewportView(textArea_anmerkschule);
		panel_5.setLayout(gl_panel_5);
		
		JLabel lblSchule = new JLabel("Schule");
		lblSchule.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JPanel panel_6 = new JPanel();
		panel_6.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		
		JLabel lblMitarbeiterkind = new JLabel("MiKi:");
		
		JLabel lblGrad = new JLabel("Grad:");
		
		String comboBoxListe_miki[] = {"Ja", "Nein"};
		JComboBox comboBox_miki = new JComboBox(comboBoxListe_miki);
		
		String comboBoxListe_grad[] = {"Eltern", "Geschwister", "Onkel/Tante", "Großeltern", "siehe Anmerkungen"};
		JComboBox comboBox_grad = new JComboBox(comboBoxListe_grad);
		GroupLayout gl_panel_6 = new GroupLayout(panel_6);
		gl_panel_6.setHorizontalGroup(
			gl_panel_6.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_6.createSequentialGroup()
					.addGap(26)
					.addGroup(gl_panel_6.createParallelGroup(Alignment.LEADING)
						.addComponent(lblGrad)
						.addComponent(lblMitarbeiterkind))
					.addGap(18)
					.addGroup(gl_panel_6.createParallelGroup(Alignment.LEADING)
						.addComponent(comboBox_miki, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBox_grad, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(26, Short.MAX_VALUE))
		);
		gl_panel_6.setVerticalGroup(
			gl_panel_6.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_6.createSequentialGroup()
					.addGap(20)
					.addGroup(gl_panel_6.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblMitarbeiterkind)
						.addComponent(comboBox_miki, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel_6.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblGrad)
						.addComponent(comboBox_grad, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(28, Short.MAX_VALUE))
		);
		panel_6.setLayout(gl_panel_6);
		
		JLabel lblMitarbeiterkind_1 = new JLabel("Mitarbeiterkind");
		lblMitarbeiterkind_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JLabel lblAnmerkungenZurPerson = new JLabel("Anmerkungen zur Person");
		lblAnmerkungenZurPerson.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		
		JLabel lblDatenZumPraktikum = new JLabel("Daten zum Praktikum");
		lblDatenZumPraktikum.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JPanel panel_7 = new JPanel();
		panel_7.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		
		JLabel lblStartdatum = new JLabel("Startdatum:");
		
		JLabel lblEnddatum = new JLabel("Enddatum:");
		
		datePicker_startdatum = new JXDatePicker();
		datePicker_startdatum.setDate(Calendar.getInstance().getTime());
		datePicker_startdatum.setFormats(new SimpleDateFormat("dd.MM.yyyy"));
		
		datePicker_enddatum = new JXDatePicker();
		datePicker_enddatum.setDate(Calendar.getInstance().getTime());
		datePicker_enddatum.setFormats(new SimpleDateFormat("dd.MM.yyyy"));
		
		JLabel lblStatus = new JLabel("Status:");
		
		String comboBoxListe_state[] = {"leer", "Eingangsbestätigung", "Zusage", "Selbstabsage", "Absage", "anwesend", "abgeschlossen"};
		JComboBox comboBox_status = new JComboBox(comboBoxListe_state);
		GroupLayout gl_panel_7 = new GroupLayout(panel_7);
		gl_panel_7.setHorizontalGroup(
			gl_panel_7.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_7.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_7.createParallelGroup(Alignment.LEADING)
						.addComponent(lblStartdatum)
						.addComponent(lblEnddatum)
						.addComponent(lblStatus))
					.addGap(27)
					.addGroup(gl_panel_7.createParallelGroup(Alignment.LEADING)
						.addComponent(comboBox_status, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)
						.addComponent(datePicker_startdatum, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)
						.addComponent(datePicker_enddatum, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(66, Short.MAX_VALUE))
		);
		gl_panel_7.setVerticalGroup(
			gl_panel_7.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_7.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_7.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblStartdatum)
						.addComponent(datePicker_startdatum, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_7.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEnddatum)
						.addComponent(datePicker_enddatum, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_7.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblStatus)
						.addComponent(comboBox_status, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(23, Short.MAX_VALUE))
		);
		panel_7.setLayout(gl_panel_7);
		
		JPanel panel_8 = new JPanel();
		panel_8.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		
		JButton button_woche1 = new JButton("1");
		
		JButton button_woche2 = new JButton("2");
		
		JButton button_woche3 = new JButton("3");
		GroupLayout gl_panel_8 = new GroupLayout(panel_8);
		gl_panel_8.setHorizontalGroup(
			gl_panel_8.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_8.createSequentialGroup()
					.addContainerGap(15, Short.MAX_VALUE)
					.addComponent(button_woche1, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(button_woche2, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
					.addGap(15)
					.addComponent(button_woche3, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
					.addGap(116))
		);
		gl_panel_8.setVerticalGroup(
			gl_panel_8.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_8.createSequentialGroup()
					.addGroup(gl_panel_8.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_8.createSequentialGroup()
							.addGap(2)
							.addComponent(button_woche3, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_8.createSequentialGroup()
							.addGap(2)
							.addComponent(button_woche2, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_8.createSequentialGroup()
							.addGap(2)
							.addComponent(button_woche1, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel_8.setLayout(gl_panel_8);
		
		JLabel lblWochen = new JLabel("Woche");
		lblWochen.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JLabel lblAnmerkungenZumPraktikum = new JLabel("Anmerkungen zum Praktikum");
		lblAnmerkungenZumPraktikum.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JScrollPane scrollPane_2 = new JScrollPane();
		
		JLabel lblEinsatzort = new JLabel("Einsatzort");
		lblEinsatzort.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JPanel panel_9 = new JPanel();
		panel_9.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		
		JLabel lblNachnameansprechpartner = new JLabel("Nachname(Ansprechpartner):");
		
		JLabel lblVorname_1 = new JLabel("Vorname:");
		
		textField_vnansprech = new JTextField();
		textField_vnansprech.setColumns(10);
		
		JLabel lblTelefonnummer = new JLabel("Telefonnummer:");
		
		textField_teleansprech = new JTextField();
		textField_teleansprech.setColumns(10);
		
		JLabel lblEmailadresse = new JLabel("E-Mail-Adresse:");
		
		textField_mailansprech = new JTextField();
		textField_mailansprech.setColumns(10);
		
		Vector comboBoxItems_nnansprech=new Vector();
	    comboBoxItems_nnansprech.add("");
	    comboBoxItems_nnansprech.add("Meier");
	    comboBoxItems_nnansprech.add("Müller");
	    comboBoxItems_nnansprech.add("Schröder");
	    comboBoxItems_nnansprech.add("...");
	    comboBoxItems_nnansprech.add("5");
	    
		this.comboBox_nnansprech = new JComboBox(comboBoxItems_nnansprech);
		this.comboBox_nnansprech.setEditable(true);
		AutoCompleteDecorator.decorate(this.comboBox_nnansprech);
		
		JLabel lblAbteilung = new JLabel("Abteilung:");
		
		JLabel lblRaumnummer = new JLabel("Raumnummer:");
		
		textField_abteilungansprech = new JTextField();
		textField_abteilungansprech.setColumns(10);
		
		textField_raumnransprech = new JTextField();
		textField_raumnransprech.setColumns(10);
		GroupLayout gl_panel_9 = new GroupLayout(panel_9);
		gl_panel_9.setHorizontalGroup(
			gl_panel_9.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_9.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_9.createParallelGroup(Alignment.LEADING)
						.addComponent(lblVorname_1)
						.addComponent(lblNachnameansprechpartner)
						.addGroup(gl_panel_9.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(lblEmailadresse, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(lblTelefonnummer, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addComponent(lblAbteilung, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblRaumnummer, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE))
					.addGap(39)
					.addGroup(gl_panel_9.createParallelGroup(Alignment.LEADING, false)
						.addComponent(textField_raumnransprech)
						.addComponent(textField_abteilungansprech)
						.addComponent(textField_mailansprech)
						.addComponent(textField_teleansprech)
						.addComponent(textField_vnansprech)
						.addComponent(comboBox_nnansprech, GroupLayout.PREFERRED_SIZE, 243, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(22, Short.MAX_VALUE))
		);
		gl_panel_9.setVerticalGroup(
			gl_panel_9.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_9.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_9.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNachnameansprechpartner)
						.addComponent(comboBox_nnansprech, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_9.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblVorname_1)
						.addComponent(textField_vnansprech, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_9.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTelefonnummer)
						.addComponent(textField_teleansprech, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_9.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEmailadresse)
						.addComponent(textField_mailansprech, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_9.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAbteilung)
						.addComponent(textField_abteilungansprech, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_9.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblRaumnummer)
						.addComponent(textField_raumnransprech, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(25, Short.MAX_VALUE))
		);
		panel_9.setLayout(gl_panel_9);
		
		JLabel lblAnmerkungenZumEinsatzort = new JLabel("Anmerkungen zum Einsatzort");
		lblAnmerkungenZumEinsatzort.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JScrollPane scrollPane_4 = new JScrollPane();
		
		JButton btnSpeichern = new JButton("Speichern");
		
		JScrollPane scrollPane_5 = new JScrollPane();
		
		JLabel lblStatus_1 = new JLabel("Info");
		lblStatus_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JButton btnNachrichtErstellen = new JButton("Nachricht erstellen");
		GroupLayout gl_panel_Steckbrief = new GroupLayout(panel_Steckbrief);
		gl_panel_Steckbrief.setHorizontalGroup(
			gl_panel_Steckbrief.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_Steckbrief.createSequentialGroup()
					.addGap(43)
					.addGroup(gl_panel_Steckbrief.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_panel_Steckbrief.createSequentialGroup()
							.addGroup(gl_panel_Steckbrief.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel)
								.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(33)
							.addGroup(gl_panel_Steckbrief.createParallelGroup(Alignment.LEADING)
								.addComponent(lblKontaktdaten, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
								.addComponent(panel_4, GroupLayout.PREFERRED_SIZE, 503, GroupLayout.PREFERRED_SIZE))
							.addGap(36)
							.addGroup(gl_panel_Steckbrief.createParallelGroup(Alignment.LEADING)
								.addComponent(lblSchule, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
								.addComponent(panel_5, GroupLayout.PREFERRED_SIZE, 335, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_panel_Steckbrief.createSequentialGroup()
							.addGroup(gl_panel_Steckbrief.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_Steckbrief.createSequentialGroup()
									.addGroup(gl_panel_Steckbrief.createParallelGroup(Alignment.LEADING)
										.addComponent(lblMitarbeiterkind_1, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)
										.addComponent(panel_6, GroupLayout.PREFERRED_SIZE, 223, GroupLayout.PREFERRED_SIZE))
									.addGap(18)
									.addGroup(gl_panel_Steckbrief.createParallelGroup(Alignment.LEADING)
										.addComponent(lblAnmerkungenZurPerson, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
										.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 188, GroupLayout.PREFERRED_SIZE)))
								.addGroup(gl_panel_Steckbrief.createSequentialGroup()
									.addComponent(lblEinsatzort, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(lblWochen, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(panel_8, GroupLayout.PREFERRED_SIZE, 254, GroupLayout.PREFERRED_SIZE))
								.addComponent(panel_9, GroupLayout.PREFERRED_SIZE, 482, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_panel_Steckbrief.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_Steckbrief.createParallelGroup(Alignment.LEADING)
									.addGroup(gl_panel_Steckbrief.createSequentialGroup()
										.addPreferredGap(ComponentPlacement.RELATED)
										.addGroup(gl_panel_Steckbrief.createParallelGroup(Alignment.TRAILING)
											.addComponent(lblDatenZumPraktikum, Alignment.LEADING)
											.addComponent(panel_7, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE))
										.addGroup(gl_panel_Steckbrief.createParallelGroup(Alignment.LEADING)
											.addGroup(gl_panel_Steckbrief.createSequentialGroup()
												.addGap(31)
												.addComponent(scrollPane_2, GroupLayout.PREFERRED_SIZE, 374, GroupLayout.PREFERRED_SIZE))
											.addGroup(gl_panel_Steckbrief.createSequentialGroup()
												.addGap(29)
												.addComponent(lblAnmerkungenZumPraktikum, GroupLayout.PREFERRED_SIZE, 198, GroupLayout.PREFERRED_SIZE))))
									.addComponent(lblAnmerkungenZumEinsatzort, GroupLayout.PREFERRED_SIZE, 211, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel_Steckbrief.createSequentialGroup()
									.addComponent(scrollPane_4, GroupLayout.PREFERRED_SIZE, 256, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_panel_Steckbrief.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panel_Steckbrief.createSequentialGroup()
											.addGap(33)
											.addComponent(btnNachrichtErstellen, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_panel_Steckbrief.createSequentialGroup()
											.addGap(32)
											.addComponent(btnSpeichern, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE)))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_panel_Steckbrief.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panel_Steckbrief.createSequentialGroup()
											.addGap(18)
											.addComponent(lblStatus_1, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_panel_Steckbrief.createSequentialGroup()
											.addGap(17)
											.addComponent(scrollPane_5, GroupLayout.PREFERRED_SIZE, 204, GroupLayout.PREFERRED_SIZE)))))))
					.addContainerGap(51, Short.MAX_VALUE))
		);
		gl_panel_Steckbrief.setVerticalGroup(
			gl_panel_Steckbrief.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_Steckbrief.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_Steckbrief.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(lblKontaktdaten)
						.addComponent(lblSchule, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_Steckbrief.createParallelGroup(Alignment.LEADING, false)
						.addComponent(panel_5, 0, 0, Short.MAX_VALUE)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(panel_4, GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
					.addGroup(gl_panel_Steckbrief.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_Steckbrief.createParallelGroup(Alignment.LEADING, false)
							.addGroup(gl_panel_Steckbrief.createSequentialGroup()
								.addGroup(gl_panel_Steckbrief.createParallelGroup(Alignment.BASELINE)
									.addComponent(lblAnmerkungenZurPerson, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
									.addComponent(lblDatenZumPraktikum, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(gl_panel_Steckbrief.createParallelGroup(Alignment.LEADING)
									.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
									.addComponent(panel_7, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
							.addGroup(gl_panel_Steckbrief.createSequentialGroup()
								.addComponent(lblMitarbeiterkind_1, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(panel_6, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
						.addGroup(gl_panel_Steckbrief.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblAnmerkungenZumPraktikum, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(scrollPane_2, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_Steckbrief.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel_Steckbrief.createSequentialGroup()
							.addGroup(gl_panel_Steckbrief.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_Steckbrief.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED, 4, Short.MAX_VALUE)
									.addGroup(gl_panel_Steckbrief.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblEinsatzort, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblWochen, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.RELATED))
								.addGroup(gl_panel_Steckbrief.createSequentialGroup()
									.addGap(18)
									.addGroup(gl_panel_Steckbrief.createParallelGroup(Alignment.LEADING)
										.addComponent(lblAnmerkungenZumEinsatzort, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
										.addComponent(panel_8, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.RELATED)))
							.addGroup(gl_panel_Steckbrief.createParallelGroup(Alignment.LEADING)
								.addComponent(scrollPane_4, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE)
								.addComponent(panel_9, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addContainerGap())
						.addGroup(gl_panel_Steckbrief.createSequentialGroup()
							.addComponent(btnNachrichtErstellen, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
							.addGap(4)
							.addComponent(btnSpeichern, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
							.addGap(25))
						.addGroup(gl_panel_Steckbrief.createSequentialGroup()
							.addComponent(lblStatus_1, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
							.addGap(4)
							.addComponent(scrollPane_5, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE)
							.addGap(26))))
		);
		
		JTextArea textArea_konsole = new JTextArea();
		textArea_konsole.setBackground(UIManager.getColor("Button.background"));
		textArea_konsole.setLineWrap(true);
		textArea_konsole.setEnabled(false);
		textArea_konsole.setEditable(false);
		scrollPane_5.setViewportView(textArea_konsole);
		
		JTextArea textArea_anmerkeinsatz = new JTextArea();
		textArea_anmerkeinsatz.setLineWrap(true);
		scrollPane_4.setViewportView(textArea_anmerkeinsatz);
		
		JTextArea textArea_anmerkprakt = new JTextArea();
		textArea_anmerkprakt.setLineWrap(true);
		scrollPane_2.setViewportView(textArea_anmerkprakt);
		
		JTextArea textArea_anmerkperson = new JTextArea();
		textArea_anmerkperson.setLineWrap(true);
		scrollPane_1.setViewportView(textArea_anmerkperson);
		
		JLabel lblNewLabel_1 = new JLabel("ID:");
		
		JLabel lblAnrede = new JLabel("Anrede:");
		
		JLabel lblNachname = new JLabel("Nachname:");
		
		JLabel lblVorname = new JLabel("Vorname:");
		
		JLabel lblGeburtstag = new JLabel("Geburtsdatum:");
		
		JLabel lblGeburtsort = new JLabel("Geburtsort");
		
		textField_id = new JTextField();
		textField_id.setEditable(false);
		textField_id.setColumns(10);
		
		textField_nn = new JTextField();
		textField_nn.setColumns(10);
		
		textField_vn = new JTextField();
		textField_vn.setColumns(10);
		
		datePicker_gb = new JXDatePicker();
		datePicker_gb.setDate(Calendar.getInstance().getTime());
		datePicker_gb.setFormats(new SimpleDateFormat("dd.MM.yyyy"));
		
		String comboBoxListe_anrede[] = {"Herr", "Frau"};
		JComboBox comboBox_anrede = new JComboBox(comboBoxListe_anrede);
		
		Vector comboBoxItems_geburtsort = new Vector();
		comboBoxItems_geburtsort.add("");
		comboBoxItems_geburtsort.add("Braunschweig");
		comboBoxItems_geburtsort.add("Walsrode");
	    comboBoxItems_geburtsort.add("Unter der Brücke");
	    comboBoxItems_geburtsort.add("1");
	    comboBoxItems_geburtsort.add("5");
	    
		this.comboBox_geburtsort = new JComboBox(comboBoxItems_geburtsort);
		this.comboBox_geburtsort.setEditable(true);
		this.comboBox_geburtsort.setUI(new BasicComboBoxUI() {
			           @Override  
			            protected JButton createArrowButton() {  
			                return new JButton() {  
			                    @Override  
		                  public int getWidth() {  
			                return 0;  
			                   }  
			               };  
			           }  
			        });
		this.comboBox_geburtsort.setBorder(BorderFactory.createLineBorder(Color.black));

		AutoCompleteDecorator.decorate(this.comboBox_geburtsort);
		comboBox_geburtsort.setEditable(true);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblNewLabel_1)
							.addPreferredGap(ComponentPlacement.RELATED, 94, Short.MAX_VALUE)
							.addComponent(textField_id, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblAnrede)
							.addPreferredGap(ComponentPlacement.RELATED, 70, Short.MAX_VALUE)
							.addComponent(comboBox_anrede, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblNachname)
							.addPreferredGap(ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
							.addComponent(textField_nn, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblVorname)
							.addPreferredGap(ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
							.addComponent(textField_vn, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblGeburtstag)
							.addPreferredGap(ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
							.addComponent(datePicker_gb, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblGeburtsort)
							.addPreferredGap(ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
							.addComponent(comboBox_geburtsort, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(textField_id, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAnrede)
						.addComponent(comboBox_anrede, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNachname)
						.addComponent(textField_nn, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblVorname)
						.addComponent(textField_vn, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblGeburtstag)
						.addComponent(datePicker_gb, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblGeburtsort)
						.addComponent(comboBox_geburtsort, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(33, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		panel_Steckbrief.setLayout(gl_panel_Steckbrief);
		
		JPanel panel_SuchePrak = new JPanel();
		mainPanel.add(panel_SuchePrak, "card_2");
		panel_SuchePrak.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel_1.setBounds(400, 150, 453, 191);
		panel_SuchePrak.add(panel_1);
		
		
		String comboBoxListe_kritprak[] = {"Nachname"};
		JComboBox comboBox_kriteriumprak = new JComboBox(comboBoxListe_kritprak);
		comboBox_kriteriumprak.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JLabel lblKriterium = new JLabel("Kriterium:");
		lblKriterium.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblSuchbegriff = new JLabel("Suchbegriff:");
		lblSuchbegriff.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		textField_suchbegriffprak = new JTextField();
		textField_suchbegriffprak.setColumns(10);
		
		JButton btn_sucheprak = new JButton("Suchen");
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(62)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addComponent(lblKriterium)
								.addComponent(lblSuchbegriff))
							.addGap(61)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING, false)
								.addComponent(textField_suchbegriffprak)
								.addComponent(comboBox_kriteriumprak, 0, 198, Short.MAX_VALUE)))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(133)
							.addComponent(btn_sucheprak, GroupLayout.PREFERRED_SIZE, 178, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(56, Short.MAX_VALUE))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(31)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblKriterium)
						.addComponent(comboBox_kriteriumprak, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSuchbegriff, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_suchbegriffprak, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
					.addComponent(btn_sucheprak, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
					.addGap(27))
		);
		panel_1.setLayout(gl_panel_1);
		
		JLabel lblSucheNachPraktikanten = new JLabel("Suche nach Praktikanten");
		lblSucheNachPraktikanten.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSucheNachPraktikanten.setBounds(400, 125, 216, 14);
		panel_SuchePrak.add(lblSucheNachPraktikanten);
		
		
		JPanel panel_Ansprech = new JPanel();
		panel_Ansprech.setLayout(null);
		mainPanel.add(panel_Ansprech, "card_3");
		
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel_3.setBounds(400, 150, 453, 191);
		panel_Ansprech.add(panel_3);
		
		JLabel label_2 = new JLabel("Kriterium:");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel label_3 = new JLabel("Suchbegriff:");
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		textField_suchbegriffansp = new JTextField();
		textField_suchbegriffansp.setColumns(10);
		
		String comboBoxListe_kritansp[] = {"Nachname"};
		JComboBox comboBox_kriteriumansp = new JComboBox(comboBoxListe_kritansp);
		comboBox_kriteriumansp.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JButton btn_sucheansp = new JButton("Suchen");
		GroupLayout gl_panel_3 = new GroupLayout(panel_3);
		gl_panel_3.setHorizontalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGap(0, 453, Short.MAX_VALUE)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_3.createSequentialGroup()
							.addGap(62)
							.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
								.addComponent(label_2)
								.addComponent(label_3))
							.addGap(61)
							.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING, false)
								.addComponent(textField_suchbegriffansp)
								.addComponent(comboBox_kriteriumansp, 0, 198, Short.MAX_VALUE)))
						.addGroup(gl_panel_3.createSequentialGroup()
							.addGap(133)
							.addComponent(btn_sucheansp, GroupLayout.PREFERRED_SIZE, 178, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(56, Short.MAX_VALUE))
		);
		gl_panel_3.setVerticalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGap(0, 191, Short.MAX_VALUE)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addGap(31)
					.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_2)
						.addComponent(comboBox_kriteriumansp, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_3, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_suchbegriffansp, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
					.addComponent(btn_sucheansp, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
					.addGap(27))
		);
		panel_3.setLayout(gl_panel_3);
		
		JLabel lblSucheNachAnsprechpartnern = new JLabel("Suche nach Ansprechpartnern");
		lblSucheNachAnsprechpartnern.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSucheNachAnsprechpartnern.setBounds(400, 125, 216, 14);
		panel_Ansprech.add(lblSucheNachAnsprechpartnern);
		
		JPanel panel_tabllen = new JPanel();
		mainPanel.add(panel_tabllen, "card_4");
		panel_tabllen.setLayout(new BorderLayout(0, 0));
		
		scrollPane_Suchliste = new JScrollPane();
		panel_tabllen.add(scrollPane_Suchliste);
		scrollPane_Suchliste.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		JPanel panel_10 = new JPanel();
		panel_tabllen.add(panel_10, BorderLayout.SOUTH);
		
		JButton btn_bearbeiten = new JButton("Bearbeiten");
		
		JButton btn_loeschen = new JButton("L\u00F6schen");
		
		JButton btn_info = new JButton("Info");
		
		btn_NachrichtSchreiben = new JButton("Nachricht erstellen");
		GroupLayout gl_panel_10 = new GroupLayout(panel_10);
		gl_panel_10.setHorizontalGroup(
			gl_panel_10.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_10.createSequentialGroup()
					.addGap(20)
					.addComponent(btn_bearbeiten)
					.addGap(18)
					.addComponent(btn_loeschen, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btn_info, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btn_NachrichtSchreiben)
					.addContainerGap(808, Short.MAX_VALUE))
		);
		gl_panel_10.setVerticalGroup(
			gl_panel_10.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_10.createSequentialGroup()
					.addGap(9)
					.addGroup(gl_panel_10.createParallelGroup(Alignment.BASELINE)
						.addComponent(btn_bearbeiten)
						.addComponent(btn_loeschen)
						.addComponent(btn_info)
						.addComponent(btn_NachrichtSchreiben))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel_10.setLayout(gl_panel_10);
		
		
		 JMenuBar menu = new JMenuBar();
		    JMenu start = new JMenu("Start");
		    start.add(menuEintrag);
		    start.add(menuSuche);
		    start.add(menuAnsprechpartner);
		    start.add(menuAllePraktikanten);
		    start.add(menuAlleAnsprechpartner);
		    start.add(menuAnsprechpartnerbearbeiten);
		    menu.add(start);
		    getContentPane().add(menu, BorderLayout.NORTH);
		
		menuEintrag.addActionListener(this);
	    menuSuche.addActionListener(this);
	    menuAllePraktikanten.addActionListener(this);
	    menuAnsprechpartner.addActionListener(this);
	    menuAlleAnsprechpartner.addActionListener(this);
	    menuAnsprechpartnerbearbeiten.addActionListener(this);
	   
		
	}

	public static void setFixedWidth(Component component, int width )
    {
    	component.setSize( new Dimension( width, Short.MAX_VALUE ) );
    	Dimension preferredSize = component.getPreferredSize();
    	component.setPreferredSize( new Dimension( width, preferredSize.height ) );
    }
	public void actionPerformed(ActionEvent evt) {
		 Object src = evt.getSource();
		 CardLayout cardLayout = (CardLayout) mainPanel.getLayout();
		    if (src == menuEintrag) {
		    	cardLayout.show(mainPanel, "card_1");
		    } else if (src == menuSuche) {
		    	cardLayout.show(mainPanel, "card_2");
		    } else if (src == menuAnsprechpartner) {
				cardLayout.show(mainPanel, "card_3");
		    } else if (src == menuAllePraktikanten) {
				cardLayout.show(mainPanel, "card_4");
				table_suche = new JTable(new DefaultTableModel_PraktikantenVerwaltung(spaltennamenprak, datenprak));
				table_suche.setSelectionMode(0);
				table_suche.setAutoCreateRowSorter(true);
				scrollPane_Suchliste.setViewportView(table_suche);
				btn_NachrichtSchreiben.setVisible(true);
			} else if (src == menuAlleAnsprechpartner) {
				cardLayout.show(mainPanel, "card_4");
				table_suche = new JTable(new DefaultTableModel_PraktikantenVerwaltung(spaltennamenansprech, datenansprech));
				table_suche.setSelectionMode(0);
				table_suche.setAutoCreateRowSorter(true);
				scrollPane_Suchliste.setViewportView(table_suche);
				btn_NachrichtSchreiben.setVisible(false);
			} else if (src == menuAnsprechpartnerbearbeiten) {
				cardLayout.show(mainPanel, "card_5");
			}
		
	}
}