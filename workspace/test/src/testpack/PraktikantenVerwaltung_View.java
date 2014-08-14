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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

import javax.swing.AbstractButton;
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

import java.awt.SystemColor;

public class PraktikantenVerwaltung_View extends JFrame implements ActionListener{

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
	private JTextField textField_suchbegriffprak;
	private JTextField textField_suchbegriffansp;
	private JTable table_suche;
	private JScrollPane scrollPane_Suchliste;
	private JScrollPane scrollPane_SuchlisteAnspr;
	private JButton btn_praktNachrichtSchreiben;
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
	private JComboBox comboBox_schule;
	private Vector comboBoxItems_schule;
	private JComboBox comboBox_wohn;
	private JComboBox comboBox_schulform;
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
	private JTextField textField_RaumAnsprWoch3;
	private JTextField textField_AbteilAnsprWoch3;
	private JTextField textField_MailAnsprWoch3;
	private JTextField textField_VornameAnsprWoch3;
	private JTextField textField_RaumAnsprWoch2;
	private JTextField textField_AbteilAnsprWoch2;
	private JTextField textField_MailAnsprWoch2;
	private JTextField textField_TelAnsprWoch2;
	private JTextField textField_VornameAnsprWoch2;
	private JTextField textField_RaumAnsprWoch1;
	private JTextField textField_AbteilAnsprWoch1;
	private JTextField textField_MailAnsprWoch1;
	private JTextField textField_TelAnsprWoch1;
	private JTextField textField_VornameAnsprWoch1;
	private JButton btnSpeichern;
	private JTextArea textArea_konsole;
	private JButton button_SpeichernAnspr;
	private JButton btn_praktbearbeiten;
	private JButton btn_praktloeschen;
	private JButton btn_praktinfo;
	private JPanel panel_ansprechPartner;
	private JPanel panel_AnsprWoche1;
	private JPanel panel_AnsprWoche2;
	private JPanel panel_AnsprWoche3;
	private JButton button_woche1;
	private JButton button_woche2;
	private JButton button_woche3;
	private JComboBox comboBox_anrede;
	private JTextArea textArea_InfoAnspr;
	private JComboBox comboBox_partners;
	private JTextArea textArea_anmerkschule;
	private JComboBox comboBox_miki;
	private JComboBox comboBox_grad;
	private JComboBox comboBox_NameAnsprWoch1;
	private JTextArea textArea_EinsatzortAnsprWoche1;
	private JComboBox comboBox_NameAnsprWoch2;
	private JTextArea textArea_EinsatzortAnsprWoche2;
	private JTextField textField_TelAnsprWoch3;
	private JTextArea textArea_EinsatzortAnsprWoche3;
	private JTextArea textArea_anmerkprakt;
	private JTextArea textArea_anmerkperson;
	private JComboBox comboBox_status;
	private Integer idAnspr1 = new Integer(0);
	private Integer idAnspr2 = new Integer(0);
	private Integer idAnspr3 = new Integer(0);
	private Integer idAnsprBearb = new Integer(0);
	private String letzteAenderung;
	private JComboBox comboBox_NameAnsprWoch3;
	private JButton button_editAnspr2;
	private JButton button_editAnspr1;
	private JButton button_editAnspr3;
	private boolean Ansprbearb1gedrueckt = false;
	private boolean Ansprbearb2gedrueckt = false;
	private boolean Ansprbearb3gedrueckt = false;
	private JButton btn_sucheprak;
	private JButton btn_sucheansp;
	private JComboBox comboBox_kriteriumprak;
	private JComboBox comboBox_kriteriumansp;
	private Vector comboBoxItems_wohn;
	private JButton buttonAnsprBearb;
	private JButton buttonAnsprLoesch;
	private JTextArea textArea_AnmerkOrtBearb;
	private JButton buttonAnsprInfo;
	private JLabel lblBearbeiteAnsprechpartner;


	/**
	 * Alte Main
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					PraktikantenVerwaltung_View frame = new PraktikantenVerwaltung_View();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Frame mit allen Panels usw. erstellen
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
						.addComponent(textField_NameAnsprBearb, GroupLayout.PREFERRED_SIZE, 293, GroupLayout.PREFERRED_SIZE))
					.addGap(29))
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
		
		lblBearbeiteAnsprechpartner = new JLabel("Bearbeite  Ansprechpartner");
		lblBearbeiteAnsprechpartner.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		button_SpeichernAnspr = new JButton("Speichern");
		
		JScrollPane scrollPane_6 = new JScrollPane();
		
		JLabel lblInfo = new JLabel("Info");
		GroupLayout gl_panel_ansprbearb = new GroupLayout(panel_ansprbearb);
		gl_panel_ansprbearb.setHorizontalGroup(
			gl_panel_ansprbearb.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_ansprbearb.createSequentialGroup()
					.addGroup(gl_panel_ansprbearb.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_ansprbearb.createSequentialGroup()
							.addGap(142)
							.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 538, GroupLayout.PREFERRED_SIZE)
							.addGap(32)
							.addGroup(gl_panel_ansprbearb.createParallelGroup(Alignment.LEADING)
								.addComponent(scrollPane_3, GroupLayout.PREFERRED_SIZE, 295, GroupLayout.PREFERRED_SIZE)
								.addComponent(label_10, GroupLayout.PREFERRED_SIZE, 211, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_panel_ansprbearb.createSequentialGroup()
							.addGap(365)
							.addComponent(button_SpeichernAnspr, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addGroup(gl_panel_ansprbearb.createParallelGroup(Alignment.LEADING)
								.addComponent(scrollPane_6, GroupLayout.PREFERRED_SIZE, 308, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblInfo))))
					.addContainerGap(425, Short.MAX_VALUE))
				.addGroup(gl_panel_ansprbearb.createSequentialGroup()
					.addContainerGap(560, Short.MAX_VALUE)
					.addComponent(lblBearbeiteAnsprechpartner, GroupLayout.PREFERRED_SIZE, 216, GroupLayout.PREFERRED_SIZE)
					.addGap(488))
		);
		gl_panel_ansprbearb.setVerticalGroup(
			gl_panel_ansprbearb.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_ansprbearb.createSequentialGroup()
					.addGap(114)
					.addComponent(lblBearbeiteAnsprechpartner)
					.addGap(69)
					.addGroup(gl_panel_ansprbearb.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_panel_ansprbearb.createSequentialGroup()
							.addComponent(label_10, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(scrollPane_3))
						.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 186, GroupLayout.PREFERRED_SIZE))
					.addGap(34)
					.addGroup(gl_panel_ansprbearb.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel_ansprbearb.createSequentialGroup()
							.addComponent(lblInfo)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(scrollPane_6, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE))
						.addComponent(button_SpeichernAnspr, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(116, Short.MAX_VALUE))
		);
		
		textArea_InfoAnspr = new JTextArea();
		textArea_InfoAnspr.setFont(new Font("Monospaced", Font.PLAIN, 13));
		textArea_InfoAnspr.setForeground(Color.BLACK);
		textArea_InfoAnspr.setEditable(false);
		scrollPane_6.setViewportView(textArea_InfoAnspr);
		
		textArea_AnmerkOrtBearb = new JTextArea();
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
		
		
		
		this.comboBox_wohn = new JComboBox();
		this.comboBox_wohn.setEditable(true);
		AutoCompleteDecorator.decorate(this.comboBox_wohn);
	    
		this.comboBox_str = new JComboBox();
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
		comboBox_partners = new JComboBox(comboBoxListe_partners);
		
		JScrollPane scrollPane = new JScrollPane();
	    
		this.comboBox_schule = new JComboBox();
		this.comboBox_schule.setEditable(true);
		AutoCompleteDecorator.decorate(this.comboBox_schule);
		
		this.comboBox_schulform = new JComboBox();
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
		
		textArea_anmerkschule = new JTextArea();
		textArea_anmerkschule.setLineWrap(true);
		scrollPane.setViewportView(textArea_anmerkschule);
		panel_5.setLayout(gl_panel_5);
		
		JLabel lblSchule = new JLabel("Schule");
		lblSchule.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JPanel panel_6 = new JPanel();
		panel_6.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		
		JLabel lblMitarbeiterkind = new JLabel("MiKi:");
		
		JLabel lblGrad = new JLabel("Grad:");
		
		String comboBoxListe_miki[] = {"Nein" , "Ja"};
		comboBox_miki = new JComboBox(comboBoxListe_miki);
		
		String comboBoxListe_grad[] = {"-" , "Eltern", "Geschwister", "Onkel/Tante", "Großeltern", "siehe Anmerkungen"};
		comboBox_grad = new JComboBox(comboBoxListe_grad);
		comboBox_grad.setEnabled(false);
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
		comboBox_status = new JComboBox(comboBoxListe_state);
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
		
		button_woche1 = new JButton("1");
		
		button_woche2 = new JButton("2");
		
		button_woche3 = new JButton("3");
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
		
		Vector comboBoxItems_nnansprech=new Vector();
	    comboBoxItems_nnansprech.add("");
	    comboBoxItems_nnansprech.add("Meier");
	    comboBoxItems_nnansprech.add("Müller");
	    comboBoxItems_nnansprech.add("Schröder");
	    comboBoxItems_nnansprech.add("...");
	    comboBoxItems_nnansprech.add("5");
		
		JLabel lblAnmerkungenZumEinsatzort = new JLabel("Anmerkungen zum Einsatzort");
		lblAnmerkungenZumEinsatzort.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		btnSpeichern = new JButton("Speichern");
		
		JScrollPane scrollPane_5 = new JScrollPane();
		
		JLabel lblStatus_1 = new JLabel("Info");
		lblStatus_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JButton btnNachrichtErstellen = new JButton("Nachricht erstellen");
		
		panel_ansprechPartner = new JPanel();
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
								.addComponent(panel_ansprechPartner, GroupLayout.PREFERRED_SIZE, 782, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panel_Steckbrief.createSequentialGroup()
									.addGroup(gl_panel_Steckbrief.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panel_Steckbrief.createSequentialGroup()
											.addGroup(gl_panel_Steckbrief.createParallelGroup(Alignment.LEADING)
												.addComponent(lblMitarbeiterkind_1, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)
												.addComponent(panel_6, GroupLayout.PREFERRED_SIZE, 223, GroupLayout.PREFERRED_SIZE))
											.addGap(18)
											.addGroup(gl_panel_Steckbrief.createParallelGroup(Alignment.LEADING)
												.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 188, GroupLayout.PREFERRED_SIZE)
												.addComponent(lblAnmerkungenZurPerson, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)))
										.addGroup(gl_panel_Steckbrief.createSequentialGroup()
											.addComponent(lblEinsatzort, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)
											.addGap(18)
											.addComponent(lblWochen, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(panel_8, GroupLayout.PREFERRED_SIZE, 254, GroupLayout.PREFERRED_SIZE)))
									.addGroup(gl_panel_Steckbrief.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panel_Steckbrief.createSequentialGroup()
											.addGap(32)
											.addGroup(gl_panel_Steckbrief.createParallelGroup(Alignment.LEADING)
												.addComponent(panel_7, GroupLayout.PREFERRED_SIZE, 236, GroupLayout.PREFERRED_SIZE)
												.addComponent(lblDatenZumPraktikum)))
										.addGroup(gl_panel_Steckbrief.createSequentialGroup()
											.addGap(68)
											.addComponent(lblAnmerkungenZumEinsatzort, GroupLayout.PREFERRED_SIZE, 211, GroupLayout.PREFERRED_SIZE)))))
							.addGroup(gl_panel_Steckbrief.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_Steckbrief.createSequentialGroup()
									.addGap(4)
									.addGroup(gl_panel_Steckbrief.createParallelGroup(Alignment.LEADING)
										.addComponent(lblAnmerkungenZumPraktikum, GroupLayout.PREFERRED_SIZE, 198, GroupLayout.PREFERRED_SIZE)
										.addComponent(scrollPane_2, GroupLayout.PREFERRED_SIZE, 374, GroupLayout.PREFERRED_SIZE)))
								.addGroup(gl_panel_Steckbrief.createSequentialGroup()
									.addGap(18)
									.addGroup(gl_panel_Steckbrief.createParallelGroup(Alignment.LEADING)
										.addComponent(btnSpeichern, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE)
										.addComponent(btnNachrichtErstellen, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE))
									.addGap(18)
									.addGroup(gl_panel_Steckbrief.createParallelGroup(Alignment.LEADING)
										.addComponent(lblStatus_1, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
										.addComponent(scrollPane_5, GroupLayout.PREFERRED_SIZE, 204, GroupLayout.PREFERRED_SIZE))))))
					.addContainerGap(53, Short.MAX_VALUE))
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
					.addPreferredGap(ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
					.addGroup(gl_panel_Steckbrief.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_Steckbrief.createParallelGroup(Alignment.LEADING, false)
							.addGroup(gl_panel_Steckbrief.createSequentialGroup()
								.addComponent(lblMitarbeiterkind_1, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(panel_6, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addGroup(gl_panel_Steckbrief.createSequentialGroup()
								.addGroup(gl_panel_Steckbrief.createParallelGroup(Alignment.BASELINE)
									.addComponent(lblAnmerkungenZurPerson, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
									.addComponent(lblDatenZumPraktikum, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(gl_panel_Steckbrief.createParallelGroup(Alignment.TRAILING)
									.addComponent(panel_7, GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
									.addComponent(scrollPane_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE))))
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
									.addPreferredGap(ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
									.addGroup(gl_panel_Steckbrief.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblEinsatzort, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblWochen, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)))
								.addGroup(gl_panel_Steckbrief.createSequentialGroup()
									.addGap(18)
									.addGroup(gl_panel_Steckbrief.createParallelGroup(Alignment.TRAILING)
										.addComponent(lblAnmerkungenZumEinsatzort, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
										.addComponent(panel_8, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(panel_ansprechPartner, GroupLayout.PREFERRED_SIZE, 197, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(gl_panel_Steckbrief.createSequentialGroup()
							.addGap(77)
							.addComponent(lblStatus_1, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_panel_Steckbrief.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_panel_Steckbrief.createSequentialGroup()
									.addComponent(btnNachrichtErstellen, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnSpeichern, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
								.addComponent(scrollPane_5, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE))
							.addGap(26))))
		);
		panel_ansprechPartner.setLayout(new CardLayout(0, 0));
		
		panel_AnsprWoche1 = new JPanel();
		panel_ansprechPartner.add(panel_AnsprWoche1, "card_woche1");
		
		JPanel panel_13 = new JPanel();
		panel_13.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		
		JLabel label_23 = new JLabel("Vorname:");
		
		JLabel label_24 = new JLabel("Nachname(Ansprechpartner):");
		
		JLabel label_25 = new JLabel("E-Mail-Adresse:");
		
		JLabel label_26 = new JLabel("Telefonnummer:");
		
		JLabel label_27 = new JLabel("Abteilung:");
		
		JLabel label_28 = new JLabel("Raumnummer:");
		
		textField_RaumAnsprWoch1 = new JTextField();
		textField_RaumAnsprWoch1.setEditable(false);
		textField_RaumAnsprWoch1.setColumns(10);
		
		textField_AbteilAnsprWoch1 = new JTextField();
		textField_AbteilAnsprWoch1.setEditable(false);
		textField_AbteilAnsprWoch1.setColumns(10);
		
		textField_MailAnsprWoch1 = new JTextField();
		textField_MailAnsprWoch1.setEditable(false);
		textField_MailAnsprWoch1.setColumns(10);
		
		textField_TelAnsprWoch1 = new JTextField();
		textField_TelAnsprWoch1.setEditable(false);
		textField_TelAnsprWoch1.setColumns(10);
		
		textField_VornameAnsprWoch1 = new JTextField();
		textField_VornameAnsprWoch1.setEditable(false);
		textField_VornameAnsprWoch1.setColumns(10);
		
		comboBox_NameAnsprWoch1 = new JComboBox();
		comboBox_NameAnsprWoch1.setEditable(false);
		
		button_editAnspr1 = new JButton("");
		
		GroupLayout gl_panel_13 = new GroupLayout(panel_13);
		gl_panel_13.setHorizontalGroup(
			gl_panel_13.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_13.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_13.createParallelGroup(Alignment.LEADING)
						.addComponent(label_23)
						.addComponent(label_24)
						.addGroup(gl_panel_13.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(label_25, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(label_26, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addComponent(label_27, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_28, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel_13.createParallelGroup(Alignment.LEADING, false)
						.addComponent(textField_AbteilAnsprWoch1)
						.addComponent(textField_MailAnsprWoch1)
						.addComponent(textField_TelAnsprWoch1)
						.addComponent(textField_VornameAnsprWoch1, GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
						.addComponent(textField_RaumAnsprWoch1)
						.addComponent(comboBox_NameAnsprWoch1, GroupLayout.PREFERRED_SIZE, 239, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(button_editAnspr1, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(32, Short.MAX_VALUE))
		);
		gl_panel_13.setVerticalGroup(
			gl_panel_13.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_13.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_13.createParallelGroup(Alignment.LEADING)
						.addComponent(button_editAnspr1, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel_13.createSequentialGroup()
							.addGroup(gl_panel_13.createParallelGroup(Alignment.BASELINE)
								.addComponent(label_24)
								.addComponent(comboBox_NameAnsprWoch1, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_13.createParallelGroup(Alignment.BASELINE)
								.addComponent(label_23)
								.addComponent(textField_VornameAnsprWoch1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_13.createParallelGroup(Alignment.BASELINE)
								.addComponent(label_26)
								.addComponent(textField_TelAnsprWoch1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_13.createParallelGroup(Alignment.BASELINE)
								.addComponent(label_25)
								.addComponent(textField_MailAnsprWoch1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_13.createParallelGroup(Alignment.BASELINE)
								.addComponent(label_27)
								.addComponent(textField_AbteilAnsprWoch1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_13.createParallelGroup(Alignment.BASELINE)
								.addComponent(label_28)
								.addComponent(textField_RaumAnsprWoch1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(23, Short.MAX_VALUE))
		);
		panel_13.setLayout(gl_panel_13);
		
		JScrollPane scrollPane_8 = new JScrollPane();
		GroupLayout gl_panel_AnsprWoche1 = new GroupLayout(panel_AnsprWoche1);
		gl_panel_AnsprWoche1.setHorizontalGroup(
			gl_panel_AnsprWoche1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_AnsprWoche1.createSequentialGroup()
					.addComponent(panel_13, GroupLayout.PREFERRED_SIZE, 482, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(scrollPane_8, GroupLayout.PREFERRED_SIZE, 256, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(26, Short.MAX_VALUE))
		);
		gl_panel_AnsprWoche1.setVerticalGroup(
			gl_panel_AnsprWoche1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_AnsprWoche1.createSequentialGroup()
					.addGroup(gl_panel_AnsprWoche1.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_13, GroupLayout.PREFERRED_SIZE, 186, GroupLayout.PREFERRED_SIZE)
						.addComponent(scrollPane_8, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		textArea_EinsatzortAnsprWoche1 = new JTextArea();
		textArea_EinsatzortAnsprWoche1.setEditable(false);
		scrollPane_8.setViewportView(textArea_EinsatzortAnsprWoche1);
		panel_AnsprWoche1.setLayout(gl_panel_AnsprWoche1);
		
		panel_AnsprWoche2 = new JPanel();
		panel_ansprechPartner.add(panel_AnsprWoche2, "card_woche2");
		
		JPanel panel_12 = new JPanel();
		panel_12.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		
		JLabel label_17 = new JLabel("Vorname:");
		
		JLabel label_18 = new JLabel("Nachname(Ansprechpartner):");
		
		JLabel label_19 = new JLabel("E-Mail-Adresse:");
		
		JLabel label_20 = new JLabel("Telefonnummer:");
		
		JLabel label_21 = new JLabel("Abteilung:");
		
		JLabel label_22 = new JLabel("Raumnummer:");
		
		textField_RaumAnsprWoch2 = new JTextField();
		textField_RaumAnsprWoch2.setEditable(false);
		textField_RaumAnsprWoch2.setColumns(10);
		
		textField_AbteilAnsprWoch2 = new JTextField();
		textField_AbteilAnsprWoch2.setEditable(false);
		textField_AbteilAnsprWoch2.setColumns(10);
		
		textField_MailAnsprWoch2 = new JTextField();
		textField_MailAnsprWoch2.setEditable(false);
		textField_MailAnsprWoch2.setColumns(10);
		
		textField_TelAnsprWoch2 = new JTextField();
		textField_TelAnsprWoch2.setEditable(false);
		textField_TelAnsprWoch2.setColumns(10);
		
		textField_VornameAnsprWoch2 = new JTextField();
		textField_VornameAnsprWoch2.setEditable(false);
		textField_VornameAnsprWoch2.setColumns(10);
		
		comboBox_NameAnsprWoch2 = new JComboBox();
		comboBox_NameAnsprWoch2.setEditable(false);
		
		button_editAnspr2 = new JButton("");
		GroupLayout gl_panel_12 = new GroupLayout(panel_12);
		gl_panel_12.setHorizontalGroup(
			gl_panel_12.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_12.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_12.createParallelGroup(Alignment.LEADING)
						.addComponent(label_17)
						.addComponent(label_18)
						.addGroup(gl_panel_12.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(label_19, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(label_20, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addComponent(label_21, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_22, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel_12.createParallelGroup(Alignment.LEADING, false)
						.addComponent(textField_RaumAnsprWoch2)
						.addComponent(textField_AbteilAnsprWoch2)
						.addComponent(textField_MailAnsprWoch2)
						.addComponent(textField_TelAnsprWoch2)
						.addComponent(textField_VornameAnsprWoch2)
						.addComponent(comboBox_NameAnsprWoch2, 0, 239, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(button_editAnspr2, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
					.addGap(35))
		);
		gl_panel_12.setVerticalGroup(
			gl_panel_12.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_12.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_12.createParallelGroup(Alignment.LEADING)
						.addComponent(button_editAnspr2, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel_12.createSequentialGroup()
							.addGroup(gl_panel_12.createParallelGroup(Alignment.BASELINE)
								.addComponent(label_18)
								.addComponent(comboBox_NameAnsprWoch2, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_12.createParallelGroup(Alignment.BASELINE)
								.addComponent(label_17)
								.addComponent(textField_VornameAnsprWoch2, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_12.createParallelGroup(Alignment.BASELINE)
								.addComponent(label_20)
								.addComponent(textField_TelAnsprWoch2, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_12.createParallelGroup(Alignment.BASELINE)
								.addComponent(label_19)
								.addComponent(textField_MailAnsprWoch2, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_12.createParallelGroup(Alignment.BASELINE)
								.addComponent(label_21)
								.addComponent(textField_AbteilAnsprWoch2, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_12.createParallelGroup(Alignment.BASELINE)
								.addComponent(label_22)
								.addComponent(textField_RaumAnsprWoch2, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(23, Short.MAX_VALUE))
		);
		panel_12.setLayout(gl_panel_12);
		
		JScrollPane scrollPane_7 = new JScrollPane();
		GroupLayout gl_panel_AnsprWoche2 = new GroupLayout(panel_AnsprWoche2);
		gl_panel_AnsprWoche2.setHorizontalGroup(
			gl_panel_AnsprWoche2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_AnsprWoche2.createSequentialGroup()
					.addComponent(panel_12, GroupLayout.PREFERRED_SIZE, 482, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(scrollPane_7, GroupLayout.PREFERRED_SIZE, 256, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(26, Short.MAX_VALUE))
		);
		gl_panel_AnsprWoche2.setVerticalGroup(
			gl_panel_AnsprWoche2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_AnsprWoche2.createSequentialGroup()
					.addGroup(gl_panel_AnsprWoche2.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_12, GroupLayout.PREFERRED_SIZE, 186, GroupLayout.PREFERRED_SIZE)
						.addComponent(scrollPane_7, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		textArea_EinsatzortAnsprWoche2 = new JTextArea();
		textArea_EinsatzortAnsprWoche2.setEditable(false);
		scrollPane_7.setViewportView(textArea_EinsatzortAnsprWoche2);
		panel_AnsprWoche2.setLayout(gl_panel_AnsprWoche2);
		
		panel_AnsprWoche3 = new JPanel();
		panel_ansprechPartner.add(panel_AnsprWoche3, "card_woche3");
		
		JPanel panel_11 = new JPanel();
		panel_11.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		
		JLabel label_11 = new JLabel("Vorname:");
		
		JLabel label_12 = new JLabel("Nachname(Ansprechpartner):");
		
		JLabel label_13 = new JLabel("E-Mail-Adresse:");
		
		JLabel label_14 = new JLabel("Telefonnummer:");
		
		JLabel label_15 = new JLabel("Abteilung:");
		
		JLabel label_16 = new JLabel("Raumnummer:");
		
		textField_RaumAnsprWoch3 = new JTextField();
		textField_RaumAnsprWoch3.setEditable(false);
		textField_RaumAnsprWoch3.setColumns(10);
		
		textField_AbteilAnsprWoch3 = new JTextField();
		textField_AbteilAnsprWoch3.setEditable(false);
		textField_AbteilAnsprWoch3.setColumns(10);
		
		textField_MailAnsprWoch3 = new JTextField();
		textField_MailAnsprWoch3.setEditable(false);
		textField_MailAnsprWoch3.setColumns(10);
		
		textField_TelAnsprWoch3 = new JTextField();
		textField_TelAnsprWoch3.setEditable(false);
		textField_TelAnsprWoch3.setColumns(10);
		
		textField_VornameAnsprWoch3 = new JTextField();
		textField_VornameAnsprWoch3.setEditable(false);
		textField_VornameAnsprWoch3.setColumns(10);
		
		comboBox_NameAnsprWoch3 = new JComboBox();
		comboBox_NameAnsprWoch3.setEditable(false);
		
		button_editAnspr3 = new JButton("");
		GroupLayout gl_panel_11 = new GroupLayout(panel_11);
		gl_panel_11.setHorizontalGroup(
			gl_panel_11.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_11.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_11.createParallelGroup(Alignment.LEADING)
						.addComponent(label_11)
						.addComponent(label_12)
						.addGroup(gl_panel_11.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(label_13, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(label_14, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addComponent(label_15, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_16, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel_11.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(textField_TelAnsprWoch3)
						.addComponent(textField_VornameAnsprWoch3)
						.addComponent(textField_RaumAnsprWoch3, Alignment.LEADING)
						.addComponent(textField_AbteilAnsprWoch3, Alignment.LEADING)
						.addComponent(textField_MailAnsprWoch3, Alignment.LEADING)
						.addComponent(comboBox_NameAnsprWoch3, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 239, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(button_editAnspr3, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(35, Short.MAX_VALUE))
		);
		gl_panel_11.setVerticalGroup(
			gl_panel_11.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_11.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_11.createParallelGroup(Alignment.LEADING)
						.addComponent(button_editAnspr3, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel_11.createSequentialGroup()
							.addGroup(gl_panel_11.createParallelGroup(Alignment.BASELINE)
								.addComponent(label_12)
								.addComponent(comboBox_NameAnsprWoch3, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_11.createParallelGroup(Alignment.BASELINE)
								.addComponent(label_11)
								.addComponent(textField_VornameAnsprWoch3, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_11.createParallelGroup(Alignment.BASELINE)
								.addComponent(label_14)
								.addComponent(textField_TelAnsprWoch3, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_11.createParallelGroup(Alignment.BASELINE)
								.addComponent(label_13)
								.addComponent(textField_MailAnsprWoch3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_11.createParallelGroup(Alignment.BASELINE)
								.addComponent(label_15)
								.addComponent(textField_AbteilAnsprWoch3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_11.createParallelGroup(Alignment.BASELINE)
								.addComponent(label_16)
								.addComponent(textField_RaumAnsprWoch3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(23, Short.MAX_VALUE))
		);
		panel_11.setLayout(gl_panel_11);
		
		JScrollPane scrollPane_4 = new JScrollPane();
		GroupLayout gl_panel_AnsprWoche3 = new GroupLayout(panel_AnsprWoche3);
		gl_panel_AnsprWoche3.setHorizontalGroup(
			gl_panel_AnsprWoche3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_AnsprWoche3.createSequentialGroup()
					.addComponent(panel_11, GroupLayout.PREFERRED_SIZE, 482, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(scrollPane_4, GroupLayout.PREFERRED_SIZE, 256, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(26, Short.MAX_VALUE))
		);
		gl_panel_AnsprWoche3.setVerticalGroup(
			gl_panel_AnsprWoche3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_AnsprWoche3.createSequentialGroup()
					.addGroup(gl_panel_AnsprWoche3.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_11, GroupLayout.PREFERRED_SIZE, 186, GroupLayout.PREFERRED_SIZE)
						.addComponent(scrollPane_4, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		textArea_EinsatzortAnsprWoche3 = new JTextArea();
		textArea_EinsatzortAnsprWoche3.setEditable(false);
		scrollPane_4.setViewportView(textArea_EinsatzortAnsprWoche3);
		panel_AnsprWoche3.setLayout(gl_panel_AnsprWoche3);
		
		textArea_konsole = new JTextArea();
		textArea_konsole.setBackground(UIManager.getColor("Button.background"));
		textArea_konsole.setLineWrap(true);
		textArea_konsole.setEnabled(false);
		textArea_konsole.setEditable(false);
		scrollPane_5.setViewportView(textArea_konsole);
		
		textArea_anmerkprakt = new JTextArea();
		textArea_anmerkprakt.setLineWrap(true);
		scrollPane_2.setViewportView(textArea_anmerkprakt);
		
		textArea_anmerkperson = new JTextArea();
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
		
		SimpleDateFormat sdfToDate = new SimpleDateFormat("dd.MM.yyyy");
		Date datestart = new Date();
        try {
			datestart = sdfToDate.parse("01.01.1995");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		datePicker_gb.setDate(datestart);
		
		String comboBoxListe_anrede[] = {"Herr", "Frau"};
		comboBox_anrede = new JComboBox(comboBoxListe_anrede);
	    
		this.comboBox_geburtsort = new JComboBox();
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
		comboBox_kriteriumprak = new JComboBox(comboBoxListe_kritprak);
		comboBox_kriteriumprak.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JLabel lblKriterium = new JLabel("Kriterium:");
		lblKriterium.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblSuchbegriff = new JLabel("Suchbegriff:");
		lblSuchbegriff.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		textField_suchbegriffprak = new JTextField();
		textField_suchbegriffprak.setColumns(10);
		
		btn_sucheprak = new JButton("Suchen");
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
		comboBox_kriteriumansp = new JComboBox(comboBoxListe_kritansp);
		comboBox_kriteriumansp.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		btn_sucheansp = new JButton("Suchen");
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
		
		JPanel panel_tabllenPrakt = new JPanel();
		mainPanel.add(panel_tabllenPrakt, "card_4");
		panel_tabllenPrakt.setLayout(new BorderLayout(0, 0));
		
		scrollPane_Suchliste = new JScrollPane();
		panel_tabllenPrakt.add(scrollPane_Suchliste);
		scrollPane_Suchliste.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		JPanel panel_10 = new JPanel();
		panel_tabllenPrakt.add(panel_10, BorderLayout.SOUTH);
		
		btn_praktbearbeiten = new JButton("Bearbeiten");
		
		btn_praktloeschen = new JButton("L\u00F6schen");
		
		btn_praktinfo = new JButton("Info");
		
		btn_praktNachrichtSchreiben = new JButton("Nachricht erstellen");
		GroupLayout gl_panel_10 = new GroupLayout(panel_10);
		gl_panel_10.setHorizontalGroup(
			gl_panel_10.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_10.createSequentialGroup()
					.addGap(20)
					.addComponent(btn_praktbearbeiten)
					.addGap(18)
					.addComponent(btn_praktloeschen, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btn_praktinfo, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btn_praktNachrichtSchreiben)
					.addContainerGap(808, Short.MAX_VALUE))
		);
		gl_panel_10.setVerticalGroup(
			gl_panel_10.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_10.createSequentialGroup()
					.addGap(9)
					.addGroup(gl_panel_10.createParallelGroup(Alignment.BASELINE)
						.addComponent(btn_praktbearbeiten)
						.addComponent(btn_praktloeschen)
						.addComponent(btn_praktinfo)
						.addComponent(btn_praktNachrichtSchreiben))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel_10.setLayout(gl_panel_10);
		
		
		 
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
		
		 /**
		 * Anfügen der Menüeinträge in das Startmenu
		 */
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
		
		    /**
		     * ActionListener den einzelnen Menüeinträgen und Buttons zuweisen
		     */
		menuEintrag.addActionListener(this);
	    menuSuche.addActionListener(this);
	    menuAlleAnsprechpartner.addActionListener(this);
	    menuAnsprechpartnerbearbeiten.addActionListener(this);
	    menuAnsprechpartner.addActionListener(this);
	    button_woche1.addActionListener(this);
	    button_woche2.addActionListener(this);
	    button_woche3.addActionListener(this);
	    button_editAnspr1.addActionListener(this);
	    button_editAnspr2.addActionListener(this);
	    button_editAnspr3.addActionListener(this);
	    comboBox_miki.addActionListener(this);

	    
	}

	/**
	 * Setzt einen Listener auf den Speichernbutton auf der Praktikanten Ansicht
	 * @param l Listener der übergeben wird
	 */
	public void setPraktSpeichernListener(ActionListener l){ 
        this.btnSpeichern.addActionListener(l); 
	} 
	/**
	 * Setzt einen Listener auf den Speicher Button in der Ansprechpartner erstellen Ansicht
	 * @param l Listener der übergeben wird
	 */
	public void setAnsprSpeichernListener(ActionListener l){
		this.button_SpeichernAnspr.addActionListener(l);
	}
	/**
	 * Setzt einen Listener auf den LöschenButton in der Ansprechpartnerlisten Ansicht
	 * @param l Listener der übergeben wird
	 */
	public void setAnsprLoeschenListener(ActionListener l){
		this.buttonAnsprLoesch.addActionListener(l);
	}
	public void setAnsprBearbeitenListener(ActionListener l){
		this.buttonAnsprBearb.addActionListener(l);
	}
	public void setAnsprInfoListener(ActionListener l){
		this.buttonAnsprInfo.addActionListener(l);
	}
	/**
	 * Setzt einen Listener auf den LöschenButton in der Praktikantenlisten Ansicht
	 * @param l Listener der übergeben wird
	 */
	public void setPraktLoeschenListener(ActionListener l){
		this.btn_praktloeschen.addActionListener(l);
	}
	public void setPraktBearbeitenListener(ActionListener l){
		this.btn_praktbearbeiten.addActionListener(l);
	}
	/**
	 * Setzt einen Listener auf den Menüeintrag für Alle Praktikanten
	 * @param l Listener der übergeben wird
	 */
	public void setAllePraktListener(ActionListener l){
		this.menuAllePraktikanten.addActionListener(l);
	}
	/**
	 * Setzt einen Listener auf den Menüeintrag für Alle Ansprechpartner
	 * @param l Listener der übergeben wird
	 */
	public void setAlleAnsprListener(ActionListener l){
		this.menuAlleAnsprechpartner.addActionListener(l);
	}
	/**
	 * Setzt einen Listener auf den Suchen Button in der Praktikantensuche
	 * @param l Listener der übergeben wird
	 */
	public void setSuchePraktListener(ActionListener l){
		this.btn_sucheprak.addActionListener(l);
	}
	/**
	 * Setzt einen Listener auf den Suchen Button in der Ansprechpartneransicht
	 * @param l Listener der übergeben wird
	 */
	public void setSucheAnsprListener(ActionListener l){
		this.btn_sucheansp.addActionListener(l);
	}
	/**
	 * Setzt einen Listener auf den Menüeintrag für die Neuer Eintrag Ansicht
	 * @param l Listener der übergeben wird
	 */
	public void setNeuerEintragListener(ActionListener l){
		this.menuEintrag.addActionListener(l);
	}
	/**
	 * Setzt einen Listener auf die ComboBox NachnameAnsprechpartner 1 
	 * @param l Listener der übergeben wird
	 */
	public void setAnsprAusfuellListener1(ActionListener l){
		this.comboBox_NameAnsprWoch1.addActionListener(l);
	}
	/**
	 * Setzt einen Listener auf die ComboBox NachnameAnsprechpartner 2
	 * @param l Listener der übergeben wird
	 */
	public void setAnsprAusfuellListener2(ActionListener l){
		this.comboBox_NameAnsprWoch2.addActionListener(l);
	}
	/**
	 * Setzt einen Listener auf die ComboBox NachnameAnsprechpartner 3 
	 * @param l Listener der übergeben wird
	 */
	public void setAnsprAusfuellListener3(ActionListener l){
		this.comboBox_NameAnsprWoch3.addActionListener(l);
	}
	/**
	 * Setzt einen Listener auf die ComboBox Schule 
	 * @param l Listener der übergeben wird
	 */
	public void setSchulformAusfuellListener(ActionListener l){
		this.comboBox_schule.addActionListener(l);
	}
	/**
	 * setzt bestimmte Breite eines Komponenten
	 * @param component
	 * @param width
	 */
	public static void setFixedWidth(Component component, int width )
    {
    	component.setSize( new Dimension( width, Short.MAX_VALUE ) );
    	Dimension preferredSize = component.getPreferredSize();
    	component.setPreferredSize( new Dimension( width, preferredSize.height ) );
    }
	/**
	 * Funktion um die Tabelle für Praktikanten anzuzeigen
	 */
	public void showAllePrakt(){
		CardLayout cardLayout = (CardLayout) mainPanel.getLayout();
//		CardLayout cardLayoutAnspr = (CardLayout) panel_ansprechPartner.getLayout();
		cardLayout.show(mainPanel, "card_4");
		table_suche = new JTable(new DefaultTableModel_PraktikantenVerwaltung(spaltennamenprak, datenprak));
		table_suche.setSelectionMode(0);
		table_suche.setAutoCreateRowSorter(true);
		scrollPane_Suchliste.setViewportView(table_suche);
		btn_praktNachrichtSchreiben.setVisible(true);
	}
	public void showAnsprBearb(){
		CardLayout cardLayout = (CardLayout) mainPanel.getLayout();
//		CardLayout cardLayoutAnspr = (CardLayout) panel_ansprechPartner.getLayout();
		cardLayout.show(mainPanel, "card_5");
	}
	/**
	 * Funkion um die Tabelle für Ansprechpartner anzuzeigen
	 */
	public void showAlleAnspr(){
		CardLayout cardLayout = (CardLayout) mainPanel.getLayout();
		CardLayout cardLayoutAnspr = (CardLayout) panel_ansprechPartner.getLayout();
		cardLayout.show(mainPanel, "card_6");
		table_suche = new JTable(new DefaultTableModel_PraktikantenVerwaltung(spaltennamenansprech, datenansprech));
		table_suche.setSelectionMode(0);
		table_suche.setAutoCreateRowSorter(true);
		scrollPane_SuchlisteAnspr.setViewportView(table_suche);
	}
	public JTable getTable(){
		return table_suche;
	}
	/**
	 * Neuen Eintrag Seite anzeigen
	 */
	public void showNeuerEintrag(){
		CardLayout cardLayout = (CardLayout) mainPanel.getLayout();
		CardLayout cardLayoutAnspr = (CardLayout) panel_ansprechPartner.getLayout();
		cardLayout.show(mainPanel, "card_1");
	}
	/**
	 * Setter für die Daten die in der Tabelle Praktikanten angezeigt werden sollen
	 * @param daten
	 */
	public void setDatenPraktListe(Object[][] daten){
		this.datenprak = daten;
	}
	/**
	 * Setter für die Daten die in der Tabelle Ansprechpartner angezeigt werden sollen
	 * @param daten
	 */
	public void setDatenAnsprListe(Object[][] daten){
		this.datenansprech = daten;
	}
	/**
	 * Setzt den Inhalt der ComboBox Wohnort
	 * @param v
	 */
	public void setComboBoxItems_wohn(Vector v){
		comboBox_wohn.removeAllItems();
		for (int i = 0; i < v.size(); i++) {
		    comboBox_wohn.addItem(v.get(i));
		}
		AutoCompleteDecorator.decorate(this.comboBox_wohn);
	}
	/**
	 * Setzt den Inhalt der ComboBox Straße
	 * @param v
	 */
	public void setComboBoxItems_str(Vector v){
		comboBox_str.removeAllItems();
		for (int i = 0; i < v.size(); i++) {
		    comboBox_str.addItem(v.get(i));
		}
		AutoCompleteDecorator.decorate(this.comboBox_str);
	}
	/**
	 * Setzt den Inhalt der ComboBox Geburtsort
	 * @param v
	 */
	public void setComboBoxItems_geburtsort(Vector v){
		comboBox_geburtsort.removeAllItems();
		for (int i = 0; i < v.size(); i++) {
		    comboBox_geburtsort.addItem(v.get(i));
		}
		AutoCompleteDecorator.decorate(this.comboBox_geburtsort);
	}
	/**
	 * Setzt den Inhalt der ComboBox Schule
	 * @param v
	 */
	public void setComboBoxItems_schule(Vector v){
		comboBox_schule.removeAllItems();
		for (int i = 0; i < v.size(); i++) {
		    comboBox_schule.addItem(v.get(i));
		}
		AutoCompleteDecorator.decorate(this.comboBox_schule);
	}
	/**
	 * Setzt den Inhalt der ComboBox Schulform
	 * @param v
	 */
	public void setComboBoxItems_schulform(Vector v){
		comboBox_schulform.removeAllItems();
		for (int i = 0; i < v.size(); i++) {
			comboBox_schulform.addItem(v.get(i));
		}
		AutoCompleteDecorator.decorate(this.comboBox_schulform);
	}
	/**
	 * Setzt den Inhalt der ComboBoxen der Ansprechpartner Nachnamen
	 * @param v
	 */
	public void setComboBoxItems_AnsprNN(Vector v){
		comboBox_NameAnsprWoch1.removeAllItems();
		comboBox_NameAnsprWoch2.removeAllItems();
		comboBox_NameAnsprWoch3.removeAllItems();
		for (int i = 0; i < v.size(); i++) {
			comboBox_NameAnsprWoch1.addItem(v.get(i));
			comboBox_NameAnsprWoch2.addItem(v.get(i));
			comboBox_NameAnsprWoch3.addItem(v.get(i));
//			System.out.println(i);
		}
		AutoCompleteDecorator.decorate(this.comboBox_NameAnsprWoch1);
		AutoCompleteDecorator.decorate(this.comboBox_NameAnsprWoch2);
		AutoCompleteDecorator.decorate(this.comboBox_NameAnsprWoch3);
//		comboBox_NameAnsprWoch1.setEditable(false);
//		comboBox_NameAnsprWoch2.setEditable(false);
//		comboBox_NameAnsprWoch3.setEditable(false);
	}
	/**
	 * Fängt die Events der Action Listener ab
	 * Durchschalten der Ansicht
	 * Freischalten der Anderen Ansprechpartner Eingabefelder
	 */
	public void actionPerformed(ActionEvent evt) {
		 Object src = evt.getSource();
		 CardLayout cardLayout = (CardLayout) mainPanel.getLayout();
		 CardLayout cardLayoutAnspr = (CardLayout) panel_ansprechPartner.getLayout();
		     if (src == menuSuche) {
		    	cardLayout.show(mainPanel, "card_2");
		    } else if (src == menuAnsprechpartner) {
				cardLayout.show(mainPanel, "card_3");
		    } else if (src == button_woche1) {
				cardLayoutAnspr.show(panel_ansprechPartner,"card_woche1");
			} else if (src == button_woche2) {
				cardLayoutAnspr.show(panel_ansprechPartner,"card_woche2");	
			} else if (src == button_woche3) {
				cardLayoutAnspr.show(panel_ansprechPartner,"card_woche3");
			} else if (src == button_editAnspr1){
				comboBox_NameAnsprWoch1.setEditable(true);
				textField_VornameAnsprWoch1.setEditable(true);
				textField_TelAnsprWoch1.setEditable(true);
				textField_MailAnsprWoch1.setEditable(true);
				textField_AbteilAnsprWoch1.setEditable(true);
				textField_RaumAnsprWoch1.setEditable(true);
				textArea_EinsatzortAnsprWoche1.setEditable(true);
				Ansprbearb1gedrueckt = true;
				button_editAnspr1.setEnabled(false);
			} else if (src == button_editAnspr2){
				comboBox_NameAnsprWoch2.setEditable(true);
				textField_VornameAnsprWoch2.setEditable(true);
				textField_TelAnsprWoch2.setEditable(true);
				textField_MailAnsprWoch2.setEditable(true);
				textField_AbteilAnsprWoch2.setEditable(true);
				textField_RaumAnsprWoch2.setEditable(true);
				textArea_EinsatzortAnsprWoche2.setEditable(true);
				Ansprbearb2gedrueckt = true;
				button_editAnspr2.setEnabled(false);
			} else if (src == button_editAnspr3){
				comboBox_NameAnsprWoch3.setEditable(true);
				textField_VornameAnsprWoch3.setEditable(true);
				textField_TelAnsprWoch3.setEditable(true);
				textField_MailAnsprWoch3.setEditable(true);
				textField_AbteilAnsprWoch3.setEditable(true);
				textField_RaumAnsprWoch3.setEditable(true);
				textArea_EinsatzortAnsprWoche3.setEditable(true);
				Ansprbearb3gedrueckt = true;
				button_editAnspr3.setEnabled(false);
			} else if (src == comboBox_miki) {
				if (comboBox_miki.getSelectedItem() == "Nein") {
					comboBox_grad.setSelectedItem("-");
					comboBox_grad.setEnabled(false);
				}else{
					comboBox_grad.setEnabled(true);
				}
			}
		
	}
	
	public void resetAnprBearb(){
//		comboBox_NameAnsprWoch1.setEditable(false);
		textField_VornameAnsprWoch1.setEditable(false);
		textField_TelAnsprWoch1.setEditable(false);
		textField_MailAnsprWoch1.setEditable(false);
		textField_AbteilAnsprWoch1.setEditable(false);
		textField_RaumAnsprWoch1.setEditable(false);
		textArea_EinsatzortAnsprWoche1.setEditable(false);
		Ansprbearb1gedrueckt = false;
		button_editAnspr1.setEnabled(true);
		
//		comboBox_NameAnsprWoch2.setEditable(false);
		textField_VornameAnsprWoch2.setEditable(false);
		textField_TelAnsprWoch2.setEditable(false);
		textField_MailAnsprWoch2.setEditable(false);
		textField_AbteilAnsprWoch2.setEditable(false);
		textField_RaumAnsprWoch2.setEditable(false);
		textArea_EinsatzortAnsprWoche2.setEditable(false);
		Ansprbearb2gedrueckt = false;
		button_editAnspr2.setEnabled(true);
		
//		comboBox_NameAnsprWoch3.setEditable(false);
		textField_VornameAnsprWoch3.setEditable(false);
		textField_TelAnsprWoch3.setEditable(false);
		textField_MailAnsprWoch3.setEditable(false);
		textField_AbteilAnsprWoch3.setEditable(false);
		textField_RaumAnsprWoch3.setEditable(false);
		textArea_EinsatzortAnsprWoche3.setEditable(false);
		Ansprbearb3gedrueckt = false;
		button_editAnspr3.setEnabled(true);
	}
	/**
	 * setzt das Praktikanten Info/Konsolenfeld
	 * @param inf
	 */
	public void setInfoPrakt(String inf){
		this.textArea_konsole.setText(inf);
	}
	/**
	 * setzt das Ansprechpartner Info/Konsolenfeld
	 * @param inf
	 */
	public void setInfoAnspr(String inf){
		this.textArea_InfoAnspr.setText(inf);
	}
	/**
	 * Wurde Ansprechpartner1 bearbeitet
	 * @return
	 */
	public boolean getEditAnspr1(){
		return Ansprbearb1gedrueckt;
	}
	/**
	 * Wurde Ansprechpartner2 bearbeitet
	 * @return
	 */
	public boolean getEditAnspr2(){
		return Ansprbearb2gedrueckt;
	}
	/**
	 * Wurde Ansprechpartner3 bearbeitet
	 * @return
	 */
	public boolean getEditAnspr3(){
		return Ansprbearb3gedrueckt;
	}
	/**
	 * Gibt eine Arrayliste zurück mit Suchkriterium(Nachname, Vorname, etc.)und dem Suchbegriff für Praktikanten
	 * @return
	 */
	public ArrayList<String> getSuchePrakt(){
		ArrayList<String>  s = new ArrayList<String> ();
		s.add((String) this.comboBox_kriteriumprak.getSelectedItem());
		s.add(this.textField_suchbegriffprak.getText());
		return s;
	}
	/**
	 * Gibt eine Arrayliste zurück mit Suchkriterium(Nachname, Vorname, etc.)und dem Suchbegriff für Ansprechpartner
	 * @return
	 */
	public ArrayList<String>  getSucheAnspr(){
		ArrayList<String>  s = new ArrayList<String> ();
		s.add((String) this.comboBox_kriteriumansp.getSelectedItem());
		s.add(this.textField_suchbegriffansp.getText());
		return s;
	}
	/**
	 * gibt die Eingabe zurück die in der ComboBox NachnameAnsprechpartner 1 gemacht wurde
	 * @return
	 */
	public ArrayList<String>  getNameAnspr1(){
		ArrayList<String>  s = new ArrayList<String> ();
		s.add((String) this.comboBox_NameAnsprWoch1.getSelectedItem());
		return s;
	}
	/**
	 * gibt die Eingabe zurück die in der ComboBox NachnameAnsprechpartner 2 gemacht wurde
	 * @return
	 */
	public ArrayList<String>  getNameAnspr2(){
		ArrayList<String>  s = new ArrayList<String> ();
		s.add((String) this.comboBox_NameAnsprWoch2.getSelectedItem());
		return s;
	}
	/**
	 * gibt die Eingabe zurück die in der ComboBox NachnameAnsprechpartner 3 gemacht wurde
	 * @return
	 */
	public ArrayList<String>  getNameAnspr3(){
		ArrayList<String>  s = new ArrayList<String> ();
		s.add((String) this.comboBox_NameAnsprWoch3.getSelectedItem());
		return s;
	}
	/**
	 * gibt die Eingabe zurück die in der ComboBox Schule gemacht wurde
	 * @return
	 */
	public ArrayList<String>  getNameSchule(){
		ArrayList<String>  s = new ArrayList<String> ();
		s.add((String) this.comboBox_schule.getSelectedItem());
		return s;
	}
	/**
	 * Setzt den Inhalt der Ansprechpartner1 Felder entsprechend der Liste
	 * @param liste
	 */
	public void setInhaltAnspr1(ArrayList<ArrayList<String>> liste){
		try {
			idAnspr1 = Integer.parseInt(liste.get(0).get(0));
			textField_VornameAnsprWoch1.setText(liste.get(0).get(2));
			textField_TelAnsprWoch1.setText(liste.get(0).get(3));
			textField_MailAnsprWoch1.setText(liste.get(0).get(4));
			textField_AbteilAnsprWoch1.setText(liste.get(0).get(5));
			textField_RaumAnsprWoch1.setText(liste.get(0).get(6));
			textArea_EinsatzortAnsprWoche1.setText(liste.get(0).get(7));
		} catch (Exception e) {
			idAnspr1 = 0;
		}
	}
	/**
	 * Setzt den Inhalt der Ansprechpartner2 Felder entsprechend der Liste
	 * @param liste
	 */
	public void setInhaltAnspr2(ArrayList<ArrayList<String>> liste){
		try {
			idAnspr2 = Integer.parseInt(liste.get(0).get(0));
			textField_VornameAnsprWoch2.setText(liste.get(0).get(2));
			textField_TelAnsprWoch2.setText(liste.get(0).get(3));
			textField_MailAnsprWoch2.setText(liste.get(0).get(4));
			textField_AbteilAnsprWoch2.setText(liste.get(0).get(5));
			textField_RaumAnsprWoch2.setText(liste.get(0).get(6));
			textArea_EinsatzortAnsprWoche2.setText(liste.get(0).get(7));
		} catch (Exception e) {
			idAnspr2 = 0;
		}
	}
	/**
	 * Setzt den Inhalt der Ansprechpartner3 Felder entsprechend der Liste
	 * @param liste
	 */
	public void setInhaltAnspr3(ArrayList<ArrayList<String>> liste){
		try {
			idAnspr3 = Integer.parseInt(liste.get(0).get(0));
			textField_VornameAnsprWoch3.setText(liste.get(0).get(2));
			textField_TelAnsprWoch3.setText(liste.get(0).get(3));
			textField_MailAnsprWoch3.setText(liste.get(0).get(4));
			textField_AbteilAnsprWoch3.setText(liste.get(0).get(5));
			textField_RaumAnsprWoch3.setText(liste.get(0).get(6));
			textArea_EinsatzortAnsprWoche3.setText(liste.get(0).get(7));
		} catch (Exception e) {
			idAnspr3 = 0;
		}
	}
	/**
	 * Setzt den Inhalt des Schulform Feldes
	 * @param liste
	 */
	public void setInhaltSchulform(ArrayList<ArrayList<String>> liste){
		try {
			comboBox_schulform.setSelectedItem(liste.get(0).get(0));
		} catch (Exception e) {
			comboBox_schulform.setSelectedItem("");
		}
	}
	/**
	 * Setzt das Feld Praktikanten ID auf den Wert von id
	 * @param id
	 */
	public void setPraktId(String id){
		textField_id.setText(id);
	}
	/**
	 * Setzt die ID von Ansprechpartner 1
	 */
	public void setAnspr1Id(String id){
		idAnspr1 = Integer.parseInt(id);
	}
	/**
	 * Setzt die ID von Ansprechpartner 2
	 */
	public void setAnspr2Id(String id){
		idAnspr2 = Integer.parseInt(id);
	}
	/**
	 * Setzt die ID von Ansprechpartner 3
	 */
	public void setAnspr3Id(String id){
		idAnspr3 = Integer.parseInt(id);
	}
	/**
	 * Setzt die ID von Ansprechpartner 1
	 */
	public void setNameAnspr1(String name){
		comboBox_NameAnsprWoch1.setSelectedItem(name);
	}
	/**
	 * Setzt die ID von Ansprechpartner 2
	 */
	public void setNameAnspr2(String name){
		comboBox_NameAnsprWoch2.setSelectedItem(name);
	}
	/**
	 * Setzt die ID von Ansprechpartner 3
	 */
	public void setNameAnspr3(String name){
		comboBox_NameAnsprWoch3.setSelectedItem(name);
	}
	public void setStatebutton_SpeichernAnspr(boolean s){
		button_SpeichernAnspr.setEnabled(s);
	}
	public void setText_AnprbearbLabel(String t){
		lblBearbeiteAnsprechpartner.setText(t);
	}
	/**
	 * Gibt alle Inhalte der Felder beim Praktikanten zurück
	 * @return
	 */
	public ArrayList<String> getInhaltPrakt(){
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		ArrayList<String> liste = new ArrayList<String>();
			liste.add((String) textField_id.getText());
			liste.add((String) comboBox_anrede.getSelectedItem());
			liste.add((String) textField_nn.getText());
			liste.add((String) textField_vn.getText());
			liste.add((String) df.format(datePicker_gb.getDate()));
			liste.add((String) comboBox_geburtsort.getSelectedItem());
			liste.add((String) comboBox_str.getSelectedItem());
			liste.add((String) textField_plz.getText());
			liste.add((String) txtDeutschland.getText());
			liste.add((String) textField_tele.getText());
			liste.add((String) textField_mail.getText());
			liste.add((String) textField_mobil.getText());
			liste.add((String) textField_haus.getText());
			liste.add((String) comboBox_wohn.getSelectedItem());
			liste.add((String) textField_gnn.getText());
			liste.add((String) textField_gvn.getText());
			liste.add((String) comboBox_schule.getSelectedItem());
			liste.add((String) comboBox_schulform.getSelectedItem());
			liste.add((String) comboBox_partners.getSelectedItem());
			liste.add((String) textArea_anmerkschule.getText());
			liste.add((String) comboBox_miki.getSelectedItem());
			liste.add((String) comboBox_grad.getSelectedItem());
			liste.add((String) textArea_anmerkperson.getText());
			liste.add((String) df.format(datePicker_startdatum.getDate()));
			liste.add((String) df.format(datePicker_enddatum.getDate()));
			liste.add((String) comboBox_status.getSelectedItem());
			liste.add((String) textArea_anmerkprakt.getText());
			liste.add((String) idAnspr1.toString());
			liste.add((String) idAnspr2.toString());
			liste.add((String) idAnspr3.toString());
			liste.add((String) textArea_konsole.getText());
			liste.add((String) df.format(System.currentTimeMillis()));
		return liste;
	}
	/**
	 * Gibt die Inhalte der Ansprechpartner zurück
	 * @return
	 */
	public ArrayList<ArrayList<String>> getInhaltAnspr(){
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		ArrayList<ArrayList<String>> liste = new ArrayList<ArrayList<String>>();
			ArrayList<String> eintrag = new ArrayList<String>();
			eintrag.add((String) idAnspr1.toString());
			eintrag.add((String) comboBox_NameAnsprWoch1.getSelectedItem());
			eintrag.add((String) textField_VornameAnsprWoch1.getText());
			eintrag.add((String) textField_TelAnsprWoch1.getText());
			eintrag.add((String) textField_MailAnsprWoch1.getText());
			eintrag.add((String) textField_AbteilAnsprWoch1.getText());
			eintrag.add((String) textField_RaumAnsprWoch1.getText());
			eintrag.add((String) textArea_EinsatzortAnsprWoche1.getText());
			liste.add(eintrag);
			eintrag = new ArrayList<String>();
			eintrag.add((String) idAnspr2.toString());
			eintrag.add((String) comboBox_NameAnsprWoch2.getSelectedItem());
			eintrag.add((String) textField_VornameAnsprWoch2.getText());
			eintrag.add((String) textField_TelAnsprWoch2.getText());
			eintrag.add((String) textField_MailAnsprWoch2.getText());
			eintrag.add((String) textField_AbteilAnsprWoch2.getText());
			eintrag.add((String) textField_RaumAnsprWoch2.getText());
			eintrag.add((String) textArea_EinsatzortAnsprWoche2.getText());
			liste.add(eintrag);
			eintrag = new ArrayList<String>();
			eintrag.add((String) idAnspr3.toString());
			eintrag.add((String) comboBox_NameAnsprWoch3.getSelectedItem());
			eintrag.add((String) textField_VornameAnsprWoch3.getText());
			eintrag.add((String) textField_TelAnsprWoch3.getText());
			eintrag.add((String) textField_MailAnsprWoch3.getText());
			eintrag.add((String) textField_AbteilAnsprWoch3.getText());
			eintrag.add((String) textField_RaumAnsprWoch3.getText());
			eintrag.add((String) textArea_EinsatzortAnsprWoche3.getText());
			liste.add(eintrag);
//			System.out.println(liste);
		return liste;
	}
	public void setInhaltAnsprBearb(ArrayList<ArrayList<String>> daten){
		idAnsprBearb = Integer.parseInt(daten.get(0).get(0));
		textField_NameAnsprBearb.setText(daten.get(0).get(1));
		textField_VornameAnsprBearb.setText(daten.get(0).get(2));
		textField_TelAnsprBearb.setText(daten.get(0).get(3));
		textField_MailAnsprBearb.setText(daten.get(0).get(4));
		textField_AbteilAnsprBearb.setText(daten.get(0).get(5));
		textField_RaumAnsprBearb.setText(daten.get(0).get(6));
		textArea_AnmerkOrtBearb.setText(daten.get(0).get(7));
		textArea_InfoAnspr.setText(daten.get(0).get(8));
	}
	public ArrayList<String> getInhaltAnsprBearb(){
		ArrayList<String> liste = new ArrayList<String>();
		liste.add((String) idAnsprBearb.toString());
		liste.add((String) textField_NameAnsprBearb.getText());
		liste.add((String) textField_VornameAnsprBearb.getText());
		liste.add((String) textField_TelAnsprBearb.getText());
		liste.add((String) textField_MailAnsprBearb.getText());
		liste.add((String) textField_AbteilAnsprBearb.getText());
		liste.add((String) textField_RaumAnsprBearb.getText());
		liste.add((String) textArea_AnmerkOrtBearb.getText());
		liste.add((String) textArea_InfoAnspr.getText());
	return liste;
	}
}
