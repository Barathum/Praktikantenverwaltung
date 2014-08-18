package praktikantenverwaltung;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

public class PraktikantenVerwaltung_ViewPrakt extends JFrame {
	/**
	 * erstellen der Fields
	 */
	private JPanel contentPane;
	private JPanel mainPanel;
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
	private JComboBox comboBox_schule;
	private Vector comboBoxItems_schule;
	private JComboBox comboBox_wohn;
	private JComboBox comboBox_schulform;
	private JTextField txtDeutschland;
	private JComboBox comboBox_str;
	private JComboBox comboBox_geburtsort;
	private JTextField textField_plz;
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
	private JButton buttonAnsprInfo;

	public PraktikantenVerwaltung_ViewPrakt(){

		/**
		 * Frame mit allen Panels usw. erstellen
		 */
			setResizable(true);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(5, 5, 1280, 720);
			
			
			mainPanel = new JPanel();
			getContentPane().add(mainPanel, BorderLayout.CENTER);
			mainPanel.setLayout(new CardLayout(0, 0));
			
			
			JPanel panel_Steckbrief = new JPanel();
			mainPanel.add(panel_Steckbrief, "card_1");
			
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
			
			SimpleDateFormat longFormat = new SimpleDateFormat( "dd.MM.yyyy" );
			SimpleDateFormat shortFormat = new SimpleDateFormat( "dd.MM.yy" );
			Date startDate = new Date( 0 );//01.01.1970
			shortFormat.set2DigitYearStart( startDate );

			DatePickerFormatter formatter = new DatePickerFormatter(
			// invers sequence for parsing to satisfy the year parsing rules
			        new DateFormat[] {shortFormat, longFormat}) {

			            @Override
			            public String valueToString(Object value) throws ParseException {
			                if (value == null) return null;
			                return getFormats()[1].format(value);
			            }
			        } ;
			DefaultFormatterFactory factory = new DefaultFormatterFactory(formatter );
			datePicker_startdatum = new JXDatePicker();
			datePicker_startdatum.setDate(Calendar.getInstance().getTime());
			datePicker_startdatum.setFormats(new SimpleDateFormat("dd.MM.yyyy"));
			datePicker_startdatum.getEditor().setFormatterFactory(factory);

			datePicker_enddatum = new JXDatePicker();
			datePicker_enddatum.setDate(Calendar.getInstance().getTime());
			datePicker_enddatum.setFormats(new SimpleDateFormat("dd.MM.yyyy"));
			datePicker_enddatum.getEditor().setFormatterFactory(factory);
			
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
			datePicker_gb.getEditor().setFormatterFactory(factory);
			
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
	}
	public void setPraktSpeichernListener(ActionListener l){ 
        this.btnSpeichern.addActionListener(l); 
	}
	public ArrayList<String> getInhaltPrakt(){
		SimpleDateFormat sdfToDate = new SimpleDateFormat("dd.MM.yyyy");
		ArrayList<String> liste = new ArrayList<String>();
			liste.add((String) textField_id.getText());
			liste.add((String) comboBox_anrede.getSelectedItem());
			liste.add((String) textField_nn.getText());
			liste.add((String) textField_vn.getText());
			liste.add((String) sdfToDate.format(datePicker_gb.getDate()));
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
			liste.add((String) sdfToDate.format(datePicker_startdatum.getDate()));
			liste.add((String) sdfToDate.format(datePicker_enddatum.getDate()));
			liste.add((String) comboBox_status.getSelectedItem());
			liste.add((String) textArea_anmerkprakt.getText());
			liste.add((String) idAnspr1.toString());
			liste.add((String) idAnspr2.toString());
			liste.add((String) idAnspr3.toString());
			liste.add((String) textArea_konsole.getText());
			liste.add((String) sdfToDate.format(System.currentTimeMillis()));
		return liste;
	}
	/**
	 * Gibt die Inhalte der Ansprechpartner zurück
	 * @return
	 */
	public ArrayList<ArrayList<String>> getInhaltAnspr(){
		SimpleDateFormat sdfToDate = new SimpleDateFormat("dd.MM.yyyy");
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
	 * Setzt das Feld Praktikanten ID auf den Wert von id
	 * @param id
	 */
	public void setPraktId(String id){
		textField_id.setText(id);
	}
}
