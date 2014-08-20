package praktikantenverwaltung;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
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

import javax.swing.JCheckBox;

public class PraktikantenVerwaltung_ViewPrakt extends JFrame implements ActionListener{
	/**
	 * erstellen der Fields
	 */
	private PraktikantenVerwaltung_Modell _model; 
	private PraktikantenVerwaltung_Control _control; 
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
	private boolean ansprbearb1gedrueckt = false;
	private boolean ansprbearb2gedrueckt = false;
	private boolean ansprbearb3gedrueckt = false;
	private JButton btn_sucheprak;
	private JButton btn_sucheansp;
	private JComboBox comboBox_kriteriumprak;
	private JComboBox comboBox_kriteriumansp;
	private Vector comboBoxItems_wohn;
	private JButton buttonAnsprBearb;
	private JButton buttonAnsprLoesch;
	private JButton buttonAnsprInfo;
	private Integer hoechstePraktID = 100000;
	private Integer hoechsteAnsprID = 100000;
	private String neuePraktID = "";
	private String neueAnsprID = "0";
	private JXDatePicker datePicker_Antwortfrist;
	private JTextArea textArea_anmerkprakt;
	private JCheckBox chckbxDatenVollst;
	private int updateOrInsert;

	public PraktikantenVerwaltung_ViewPrakt(){
		  this._model = new PraktikantenVerwaltung_Modell();
		  this._control = new PraktikantenVerwaltung_Control();
		  updateOrInsert = 1;
		  viewKontrukt();
		  comboBox_autocomplete();
	}
	public PraktikantenVerwaltung_ViewPrakt(ArrayList<ArrayList<String>> Praktikanteneintrageintrag){
		  this._model = new PraktikantenVerwaltung_Modell();
		  this._control = new PraktikantenVerwaltung_Control();
		  updateOrInsert = 2;
		  viewKontrukt();
		  comboBox_autocomplete();
		  setInhaltPrakt(Praktikanteneintrageintrag);
	}
	private void viewKontrukt(){
		/**
		 * Frame mit allen Panels usw. erstellen
		 */
			setResizable(true);
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setBounds(20, 20, 1280, 720);
			
			
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
			
			String comboBoxListe_partners[] = {"Nein" , "Ja"};
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
			
			String comboBoxListe_state[] = {"leer", "Eingangsbestätigung", "Bewerbung unvollständig" ,  "Zusage", "Selbstabsage", "Absage", "anwesend", "abgeschlossen"};
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
			
			JLabel lblAnmerkungenZumPraktikum = new JLabel("    Anmerkungen zum Praktikum");
			lblAnmerkungenZumPraktikum.setFont(new Font("Tahoma", Font.BOLD, 12));
			
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
			
			JPanel panel_1 = new JPanel();
			panel_1.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
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
												.addPreferredGap(ComponentPlacement.RELATED, 87, Short.MAX_VALUE)
												.addGroup(gl_panel_Steckbrief.createParallelGroup(Alignment.LEADING)
													.addComponent(lblDatenZumPraktikum)
													.addComponent(panel_7, GroupLayout.PREFERRED_SIZE, 236, GroupLayout.PREFERRED_SIZE))
												.addGap(30))
											.addGroup(gl_panel_Steckbrief.createSequentialGroup()
												.addGap(71)
												.addComponent(lblAnmerkungenZumEinsatzort, GroupLayout.PREFERRED_SIZE, 211, GroupLayout.PREFERRED_SIZE)))))
								.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addGroup(gl_panel_Steckbrief.createParallelGroup(Alignment.TRAILING)
									.addGroup(gl_panel_Steckbrief.createSequentialGroup()
										.addGroup(gl_panel_Steckbrief.createParallelGroup(Alignment.LEADING)
											.addComponent(btnSpeichern, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE)
											.addComponent(btnNachrichtErstellen, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE))
										.addGap(18)
										.addGroup(gl_panel_Steckbrief.createParallelGroup(Alignment.LEADING)
											.addComponent(lblStatus_1, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
											.addComponent(scrollPane_5, GroupLayout.PREFERRED_SIZE, 204, GroupLayout.PREFERRED_SIZE)))
									.addGroup(gl_panel_Steckbrief.createSequentialGroup()
										.addComponent(lblAnmerkungenZumPraktikum, GroupLayout.PREFERRED_SIZE, 270, GroupLayout.PREFERRED_SIZE)
										.addGap(112))
									.addGroup(gl_panel_Steckbrief.createSequentialGroup()
										.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 363, GroupLayout.PREFERRED_SIZE)
										.addGap(2)))))
						.addContainerGap(53, Short.MAX_VALUE))
			);
			gl_panel_Steckbrief.setVerticalGroup(
				gl_panel_Steckbrief.createParallelGroup(Alignment.TRAILING)
					.addGroup(gl_panel_Steckbrief.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_panel_Steckbrief.createParallelGroup(Alignment.TRAILING)
							.addGroup(gl_panel_Steckbrief.createSequentialGroup()
								.addGroup(gl_panel_Steckbrief.createParallelGroup(Alignment.BASELINE)
									.addComponent(lblNewLabel)
									.addComponent(lblKontaktdaten)
									.addComponent(lblSchule, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(gl_panel_Steckbrief.createParallelGroup(Alignment.LEADING, false)
									.addComponent(panel_5, 0, 0, Short.MAX_VALUE)
									.addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(panel_4, GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE))
								.addPreferredGap(ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
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
												.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
												.addComponent(panel_7, GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE))))
									.addComponent(lblAnmerkungenZumPraktikum, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(gl_panel_Steckbrief.createParallelGroup(Alignment.TRAILING)
									.addGroup(gl_panel_Steckbrief.createSequentialGroup()
										.addGroup(gl_panel_Steckbrief.createParallelGroup(Alignment.LEADING)
											.addGroup(gl_panel_Steckbrief.createSequentialGroup()
												.addPreferredGap(ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
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
										.addComponent(lblStatus_1, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addGroup(gl_panel_Steckbrief.createParallelGroup(Alignment.TRAILING)
											.addGroup(gl_panel_Steckbrief.createSequentialGroup()
												.addComponent(btnNachrichtErstellen, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(btnSpeichern, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
											.addComponent(scrollPane_5, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE))
										.addGap(26))))
							.addGroup(gl_panel_Steckbrief.createSequentialGroup()
								.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 176, GroupLayout.PREFERRED_SIZE)
								.addGap(196))))
			);
			
			JScrollPane scrollPane_2 = new JScrollPane();
			
			datePicker_Antwortfrist = new JXDatePicker();
			datePicker_Antwortfrist.setDate(Calendar.getInstance().getTime());
			datePicker_Antwortfrist.setFormats(new SimpleDateFormat("dd.MM.yyyy"));
			datePicker_Antwortfrist.getEditor().setFormatterFactory(factory);
			
			chckbxDatenVollst = new JCheckBox("");
			
			JLabel lblAntwortBis = new JLabel("Antwort bis:");
			
			JLabel lblAnmerkung = new JLabel("Anmerkungen:");
			
			JLabel lblUnterlagenVollstndig = new JLabel("Unterlagen vollst\u00E4ndig:");
			GroupLayout gl_panel_1 = new GroupLayout(panel_1);
			gl_panel_1.setHorizontalGroup(
				gl_panel_1.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panel_1.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_panel_1.createSequentialGroup()
								.addComponent(scrollPane_2, GroupLayout.PREFERRED_SIZE, 339, GroupLayout.PREFERRED_SIZE)
								.addContainerGap())
							.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_panel_1.createSequentialGroup()
									.addComponent(lblAnmerkung, GroupLayout.PREFERRED_SIZE, 158, GroupLayout.PREFERRED_SIZE)
									.addContainerGap(193, Short.MAX_VALUE))
								.addGroup(gl_panel_1.createSequentialGroup()
									.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
										.addComponent(lblAntwortBis, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblUnterlagenVollstndig))
									.addPreferredGap(ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
									.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
										.addComponent(chckbxDatenVollst)
										.addComponent(datePicker_Antwortfrist, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE))
									.addGap(57)))))
			);
			gl_panel_1.setVerticalGroup(
				gl_panel_1.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panel_1.createSequentialGroup()
						.addGap(6)
						.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
							.addComponent(chckbxDatenVollst)
							.addComponent(lblUnterlagenVollstndig))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
							.addComponent(datePicker_Antwortfrist, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblAntwortBis))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(lblAnmerkung)
						.addGap(7)
						.addComponent(scrollPane_2, GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
						.addContainerGap())
			);
			
			textArea_anmerkprakt = new JTextArea();
			scrollPane_2.setViewportView(textArea_anmerkprakt);
			panel_1.setLayout(gl_panel_1);
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
			comboBox_NameAnsprWoch1.setEditable(true);
			
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
			comboBox_NameAnsprWoch2.setEditable(true);
			
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
			comboBox_NameAnsprWoch3.setEditable(true);
			
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
				datestart = new Date();
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
			
			this.setPraktSpeichernListener(new PraktSpeichernListener());
			this.setAnsprAusfuellListener1(new AnsprAusfuellListener1());
			this.setAnsprAusfuellListener2(new AnsprAusfuellListener2());
			this.setAnsprAusfuellListener3(new AnsprAusfuellListener3());
			button_editAnspr1.addActionListener(this);
		    button_editAnspr2.addActionListener(this);
		    button_editAnspr3.addActionListener(this);
		    button_woche1.addActionListener(this);
		    button_woche2.addActionListener(this);
		    button_woche3.addActionListener(this);
	}
	public void setPraktSpeichernListener(ActionListener l){ 
        this.btnSpeichern.addActionListener(l); 
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
	public ArrayList<String> getInhaltPrakt(){
		SimpleDateFormat sdfToDate = new SimpleDateFormat("dd.MM.yyyy");
		ArrayList<String> liste = new ArrayList<String>();
			liste.add((String) textField_id.getText());
			if (comboBox_anrede.getSelectedItem().toString().trim().length() > 0){liste.add((String) comboBox_anrede.getSelectedItem().toString().trim());}else{liste.add("");}
			if (textField_nn.getText().trim().length() > 0){liste.add((String) textField_nn.getText().trim());}else{liste.add("");}
			if (textField_vn.getText().trim().length() > 0){liste.add((String) textField_vn.getText().trim());}else{liste.add("");}
			if (sdfToDate.format(datePicker_gb.getDate()).trim().length() > 0){liste.add((String) sdfToDate.format(datePicker_gb.getDate()).trim());}else{liste.add("");}
			if (comboBox_geburtsort.getSelectedItem().toString().trim().length() > 0){liste.add((String) comboBox_geburtsort.getSelectedItem().toString().trim());}else{liste.add("");}
			if (comboBox_str.getSelectedItem().toString().trim().length() > 0){liste.add((String) comboBox_str.getSelectedItem().toString().trim());}else{liste.add("");}
			if (textField_plz.getText().trim().length() > 0){liste.add((String) textField_plz.getText().trim());}else{liste.add("");}
			if (txtDeutschland.getText().trim().length() > 0){liste.add((String) txtDeutschland.getText().trim());}else{liste.add("");}
			if (textField_tele.getText().trim().length() > 0){liste.add((String) textField_tele.getText().trim());}else{liste.add("");}
			if (textField_mail.getText().trim().length() > 0){liste.add((String) textField_mail.getText().trim());}else{liste.add("");}
			if (textField_mobil.getText().trim().length() > 0){liste.add((String) textField_mobil.getText().trim());}else{liste.add("");}
			if (textField_haus.getText().trim().length() > 0){liste.add((String) textField_haus.getText().trim());}else{liste.add("");}
			if (comboBox_wohn.getSelectedItem().toString().trim().length() > 0){liste.add((String) comboBox_wohn.getSelectedItem().toString().trim());}else{liste.add("");}
			if (textField_gnn.getText().trim().length() > 0){liste.add((String) textField_gnn.getText().trim());}else{liste.add("");}
			if (textField_gvn.getText().trim().length() > 0){liste.add((String) textField_gvn.getText().trim());}else{liste.add("");}
			if (comboBox_schule.getSelectedItem().toString().trim().length() > 0){liste.add((String) comboBox_schule.getSelectedItem().toString().trim());}else{liste.add("");}
			if (comboBox_schulform.getSelectedItem().toString().trim().length() > 0){liste.add((String) comboBox_schulform.getSelectedItem().toString().trim());}else{liste.add("");}
			if (comboBox_partners.getSelectedItem().toString().trim().length() > 0){liste.add((String) comboBox_partners.getSelectedItem().toString().trim());}else{liste.add("");}
			if (textArea_anmerkschule.getText().trim().length() > 0){liste.add((String) textArea_anmerkschule.getText().trim());}else{liste.add("");}
			if (comboBox_miki.getSelectedItem().toString().trim().length() > 0){liste.add((String) comboBox_miki.getSelectedItem().toString().trim());}else{liste.add("");}
			if (comboBox_grad.getSelectedItem().toString().trim().length() > 0){liste.add((String) comboBox_grad.getSelectedItem().toString().trim());}else{liste.add("");}
			if (textArea_anmerkperson.getText().trim().length() > 0){liste.add((String) textArea_anmerkperson.getText().trim());}else{liste.add("");}
			if (sdfToDate.format(datePicker_startdatum.getDate()).trim().length() > 0){liste.add((String) sdfToDate.format(datePicker_startdatum.getDate()).trim());}else{liste.add("");}
			if (sdfToDate.format(datePicker_enddatum.getDate()).trim().length() > 0){liste.add((String) sdfToDate.format(datePicker_enddatum.getDate()).trim());}else{liste.add("");}
			if (comboBox_status.getSelectedItem().toString().trim().length() > 0){liste.add((String) comboBox_status.getSelectedItem().toString().trim());}else{liste.add("");}
			if (textArea_anmerkprakt.getText().trim().length() > 0){liste.add((String) textArea_anmerkprakt.getText().trim());}else{liste.add("");}
			liste.add((String) idAnspr1.toString());
			liste.add((String) idAnspr2.toString());
			liste.add((String) idAnspr3.toString());
			liste.add((String) textArea_konsole.getText().trim());
			liste.add((String) sdfToDate.format(System.currentTimeMillis()));
			if (chckbxDatenVollst.isSelected()) {
				liste.add("1");
			}else {
				liste.add("0");
			}
			if (sdfToDate.format(datePicker_Antwortfrist.getDate()).trim().length() > 0){liste.add((String) sdfToDate.format(datePicker_Antwortfrist.getDate()).trim());}else{liste.add("");}
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
			if (comboBox_NameAnsprWoch1.getSelectedItem().toString().trim().length() > 0){eintrag.add((String) comboBox_NameAnsprWoch1.getSelectedItem().toString().trim());}else{eintrag.add("");}
			if (textField_VornameAnsprWoch1.getText().trim().length() > 0){eintrag.add((String) textField_VornameAnsprWoch1.getText().trim());}else{eintrag.add("");}
			if (textField_TelAnsprWoch1.getText().trim().length() > 0){eintrag.add((String) textField_TelAnsprWoch1.getText().trim());}else{eintrag.add("");}
			if (textField_MailAnsprWoch1.getText().trim().length() > 0){eintrag.add((String) textField_MailAnsprWoch1.getText().trim());}else{eintrag.add("");}
			if (textField_AbteilAnsprWoch1.getText().trim().length() > 0){eintrag.add((String) textField_AbteilAnsprWoch1.getText().trim());}else{eintrag.add("");}
			if (textField_RaumAnsprWoch1.getText().trim().length() > 0){eintrag.add((String) textField_RaumAnsprWoch1.getText().trim());}else{eintrag.add("");}
			if (textArea_EinsatzortAnsprWoche1.getText().trim().length() > 0){eintrag.add((String) textArea_EinsatzortAnsprWoche1.getText().trim());}else{eintrag.add("");}
			eintrag.add("");
			eintrag.add("");
			eintrag.add("");
			eintrag.add("");
			eintrag.add("");
			liste.add(eintrag);
			eintrag = new ArrayList<String>();
			eintrag.add((String) idAnspr2.toString());
			if (comboBox_NameAnsprWoch2.getSelectedItem().toString().trim().length() > 0){eintrag.add((String) comboBox_NameAnsprWoch2.getSelectedItem().toString().trim());}else{eintrag.add("");}
			if (textField_VornameAnsprWoch2.getText().trim().length() > 0){eintrag.add((String) textField_VornameAnsprWoch2.getText().trim());}else{eintrag.add("");}
			if (textField_TelAnsprWoch2.getText().trim().length() > 0){eintrag.add((String) textField_TelAnsprWoch2.getText().trim());}else{eintrag.add("");}
			if (textField_MailAnsprWoch2.getText().trim().length() > 0){eintrag.add((String) textField_MailAnsprWoch2.getText().trim());}else{eintrag.add("");}
			if (textField_AbteilAnsprWoch2.getText().trim().length() > 0){eintrag.add((String) textField_AbteilAnsprWoch2.getText().trim());}else{eintrag.add("");}
			if (textField_RaumAnsprWoch2.getText().trim().length() > 0){eintrag.add((String) textField_RaumAnsprWoch2.getText().trim());}else{eintrag.add("");}
			if (textArea_EinsatzortAnsprWoche2.getText().trim().length() > 0){eintrag.add((String) textArea_EinsatzortAnsprWoche2.getText().trim());}else{eintrag.add("");}
			eintrag.add("");
			eintrag.add("");
			eintrag.add("");
			eintrag.add("");
			eintrag.add("");
			liste.add(eintrag);
			eintrag = new ArrayList<String>();
			eintrag.add((String) idAnspr3.toString());
			if (comboBox_NameAnsprWoch3.getSelectedItem().toString().trim().length() > 0){eintrag.add((String) comboBox_NameAnsprWoch3.getSelectedItem().toString().trim());}else{eintrag.add("");}
			if (textField_VornameAnsprWoch3.getText().trim().length() > 0){eintrag.add((String) textField_VornameAnsprWoch3.getText().trim());}else{eintrag.add("");}
			if (textField_TelAnsprWoch3.getText().trim().length() > 0){eintrag.add((String) textField_TelAnsprWoch3.getText().trim());}else{eintrag.add("");}
			if (textField_MailAnsprWoch3.getText().trim().length() > 0){eintrag.add((String) textField_MailAnsprWoch3.getText().trim());}else{eintrag.add("");}
			if (textField_AbteilAnsprWoch3.getText().trim().length() > 0){eintrag.add((String) textField_AbteilAnsprWoch3.getText().trim());}else{eintrag.add("");}
			if (textField_RaumAnsprWoch3.getText().trim().length() > 0){eintrag.add((String) textField_RaumAnsprWoch3.getText().trim());}else{eintrag.add("");}
			if (textArea_EinsatzortAnsprWoche3.getText().trim().length() > 0){eintrag.add((String) textArea_EinsatzortAnsprWoche3.getText().trim());}else{eintrag.add("");}
			eintrag.add("");
			eintrag.add("");
			eintrag.add("");
			eintrag.add("");
			eintrag.add("");
			liste.add(eintrag);
//			System.out.println(liste);
		return liste;
	}
	/**
	 * Wurde Ansprechpartner1 bearbeitet
	 * @return
	 */
	public boolean getEditAnspr1(){
		return ansprbearb1gedrueckt;
	}
	/**
	 * Wurde Ansprechpartner2 bearbeitet
	 * @return
	 */
	public boolean getEditAnspr2(){
		return ansprbearb2gedrueckt;
	}
	/**
	 * Wurde Ansprechpartner3 bearbeitet
	 * @return
	 */
	public boolean getEditAnspr3(){
		return ansprbearb3gedrueckt;
	}
	/**
	 * Setzt das Feld Praktikanten ID auf den Wert von id
	 * @param id
	 */
	public void setPraktId(String id){
		textField_id.setText(id);
	}
	/**
	 * setzt das Praktikanten Info/Konsolenfeld
	 * @param inf
	 */
	public void setInfoPrakt(String inf){
		this.textArea_konsole.setText(inf);
	}
	public void setInfoAnspr(String inf){
//		this.textArea_InfoAnspr.setText(inf);
	}
	/**
	    * Innere Klasse für das Ausfüllen der Ansprechpartner Felder
	    * @author Barathum
	    *
	    */
	   class AnsprAusfuellListener1 implements ActionListener{ 
		   public void actionPerformed(ActionEvent e) { 
			 Anspr1Ausfuellen(); 
         }  
	   }
	   public void Anspr1Ausfuellen(){
		   if (getEditAnspr1()==false) {
		   ArrayList<ArrayList<String>> daten = new ArrayList<ArrayList<String>>();
		   ArrayList<ArrayList<String>> datenid = new ArrayList<ArrayList<String>>();
		   Integer id = new Integer(0);
		   datenid = _model.getData("SELECT ID FROM ANSPRECHPARTNER WHERE NN LIKE '" + getNameAnspr1().get(0) + "%' ORDER BY NN;");
		   if (getNameAnspr1().get(0) == "") {
			setInhaltAnspr1(daten);
		} else {
			 try {
				   setAnspr1Id(datenid.get(0).get(0));
			} catch (Exception e) {
					setAnspr1Id("0");
			}
			   id = getAnspr1ID();
				daten = getAnsprDaten(id.toString());
				setInhaltAnspr1(daten);
		}
		}
	   }
	   /**
	    * Innere Klasse für das Ausfüllen der Ansprechpartner Felder
	    * @author Barathum
	    *
	    */
	   class AnsprAusfuellListener2 implements ActionListener{ 
		   public void actionPerformed(ActionEvent e) { 
			   Anspr2Ausfuellen();
         }  
	   }
	   public void Anspr2Ausfuellen(){
		   if (getEditAnspr2()==false) {
		   ArrayList<ArrayList<String>> daten = new ArrayList<ArrayList<String>>();
		   ArrayList<ArrayList<String>> datenid = new ArrayList<ArrayList<String>>();
		   Integer id = new Integer(0);
		   datenid = _model.getData("SELECT ID FROM ANSPRECHPARTNER WHERE NN LIKE '" + getNameAnspr2().get(0) + "%' ORDER BY NN;");
		   if (getNameAnspr2().get(0) == "") {
			setInhaltAnspr2(daten);
		} else {
			 try {
				   setAnspr2Id(datenid.get(0).get(0));
			} catch (Exception e) {
					setAnspr2Id("0");
			}
			   id = getAnspr2ID();
				daten = getAnsprDaten(id.toString());
				setInhaltAnspr2(daten);
		}
		}
	   }
	   /**
	    * Innere Klasse für das Ausfüllen der Ansprechpartner Felder
	    * @author Barathum
	    *
	    */
	   class AnsprAusfuellListener3 implements ActionListener{ 
		   public void actionPerformed(ActionEvent e) { 
			   Anspr3Ausfuellen();
         }  
	   }
	   public void Anspr3Ausfuellen(){
		   if (getEditAnspr3()==false) {
		   ArrayList<ArrayList<String>> daten = new ArrayList<ArrayList<String>>();
		   ArrayList<ArrayList<String>> datenid = new ArrayList<ArrayList<String>>();
		   Integer id = new Integer(0);
		   datenid = _model.getData("SELECT ID FROM ANSPRECHPARTNER WHERE NN LIKE '" + getNameAnspr3().get(0) + "%' ORDER BY NN;");
		   if (getNameAnspr3().get(0) == "") {
			setInhaltAnspr3(daten);
		} else {
			 try {
				   setAnspr3Id(datenid.get(0).get(0));
			} catch (Exception e) {
					setAnspr3Id("0");
			}
			   id = getAnspr3ID();
			   daten = getAnsprDaten(id.toString());
				setInhaltAnspr3(daten);
		}
		}
	   }
	   public ArrayList<ArrayList<String>> getAnsprDaten(String id){
		   ArrayList<ArrayList<String>> daten = new ArrayList<ArrayList<String>>();
		   daten = _model.getData("SELECT ID , NN , VN , TELE , MAIL , ABTEILUNG , RNR , ANMERKEINSATZORT FROM ANSPRECHPARTNER WHERE ID LIKE '" + id + "%' ORDER BY NN;");
		   return daten;
	   }
	   
	   
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
		 * Setzt den Inhalt der Ansprechpartner1 Felder entsprechend der Liste
		 * @param liste
		 */
		public void setInhaltAnspr1(ArrayList<ArrayList<String>> liste){
			try {
				idAnspr1 = Integer.parseInt(liste.get(0).get(0));
				comboBox_NameAnsprWoch1.setSelectedItem(liste.get(0).get(1));
				textField_VornameAnsprWoch1.setText(liste.get(0).get(2));
				textField_TelAnsprWoch1.setText(liste.get(0).get(3));
				textField_MailAnsprWoch1.setText(liste.get(0).get(4));
				textField_AbteilAnsprWoch1.setText(liste.get(0).get(5));
				textField_RaumAnsprWoch1.setText(liste.get(0).get(6));
				textArea_EinsatzortAnsprWoche1.setText(liste.get(0).get(7));
			} catch (Exception e) {
				setAnspr1Id("0");
				comboBox_NameAnsprWoch1.setSelectedItem("");
				textField_VornameAnsprWoch1.setText("");
				textField_TelAnsprWoch1.setText("");
				textField_MailAnsprWoch1.setText("");
				textField_AbteilAnsprWoch1.setText("");
				textField_RaumAnsprWoch1.setText("");
				textArea_EinsatzortAnsprWoche1.setText("");
			}
		}
		/**
		 * Setzt den Inhalt der Ansprechpartner2 Felder entsprechend der Liste
		 * @param liste
		 */
		public void setInhaltAnspr2(ArrayList<ArrayList<String>> liste){
			try {
				idAnspr2 = Integer.parseInt(liste.get(0).get(0));
				comboBox_NameAnsprWoch2.setSelectedItem(liste.get(0).get(1));
				textField_VornameAnsprWoch2.setText(liste.get(0).get(2));
				textField_TelAnsprWoch2.setText(liste.get(0).get(3));
				textField_MailAnsprWoch2.setText(liste.get(0).get(4));
				textField_AbteilAnsprWoch2.setText(liste.get(0).get(5));
				textField_RaumAnsprWoch2.setText(liste.get(0).get(6));
				textArea_EinsatzortAnsprWoche2.setText(liste.get(0).get(7));
			} catch (Exception e) {
				setAnspr2Id("0");
				comboBox_NameAnsprWoch2.setSelectedItem("");
				textField_VornameAnsprWoch2.setText("");
				textField_TelAnsprWoch2.setText("");
				textField_MailAnsprWoch2.setText("");
				textField_AbteilAnsprWoch2.setText("");
				textField_RaumAnsprWoch2.setText("");
				textArea_EinsatzortAnsprWoche2.setText("");
			}
		}
		/**
		 * Setzt den Inhalt der Ansprechpartner3 Felder entsprechend der Liste
		 * @param liste
		 */
		public void setInhaltAnspr3(ArrayList<ArrayList<String>> liste){
			try {
				idAnspr3 = Integer.parseInt(liste.get(0).get(0));
				comboBox_NameAnsprWoch3.setSelectedItem(liste.get(0).get(1));
				textField_VornameAnsprWoch3.setText(liste.get(0).get(2));
				textField_TelAnsprWoch3.setText(liste.get(0).get(3));
				textField_MailAnsprWoch3.setText(liste.get(0).get(4));
				textField_AbteilAnsprWoch3.setText(liste.get(0).get(5));
				textField_RaumAnsprWoch3.setText(liste.get(0).get(6));
				textArea_EinsatzortAnsprWoche3.setText(liste.get(0).get(7));
			} catch (Exception e) {
				setAnspr3Id("0");
				comboBox_NameAnsprWoch3.setSelectedItem("");
				textField_VornameAnsprWoch3.setText("");
				textField_TelAnsprWoch3.setText("");
				textField_MailAnsprWoch3.setText("");
				textField_AbteilAnsprWoch3.setText("");
				textField_RaumAnsprWoch3.setText("");
				textArea_EinsatzortAnsprWoche3.setText("");
			}
		}
	/**
	    * Innere Klasse für den Praktikanten Speichern Listener
	    * Prüft ob Praktikanten Id vorhanden und ob die Ansprechpartner IDS vorhanden sind
	    * ruft danach Methode zum schreiben des SQL Befehls auf
	    * Übergibt dann dem Model den SQL Befehl zur Ausführung
	    * @author Barathum
	    *
	    */
	   class PraktSpeichernListener implements ActionListener{ 

		public void actionPerformed(ActionEvent e) { 
            ArrayList<String> datensatz = getInhaltPrakt(); 
            ArrayList<ArrayList<String>> datensatzAnspr = getInhaltAnspr();
            int updateOrInsertAnspr1 = 0;
            int updateOrInsertAnspr2 = 0;
            int updateOrInsertAnspr3 = 0;
//            _model.connectToDatabase("jdbc:sqlite:PraktikantenDB.db");
            if (getEditAnspr1()==true) {
         	   if (datensatzAnspr.get(0).get(0).equals("0") || datensatzAnspr.get(0).get(0) == null) {
         		   		updateOrInsertAnspr1 = 2;
					} else {
						updateOrInsertAnspr1 = 1;
					}
            }
            if (getEditAnspr2()==true) {
         	   if (datensatzAnspr.get(1).get(0).equals("0") || datensatzAnspr.get(1).get(0) == null) {
         		   		updateOrInsertAnspr2 = 2;
					} else {
						updateOrInsertAnspr2 = 1;
					}
            }
            if (getEditAnspr3()==true) {
         	   if (datensatzAnspr.get(2).get(0).equals("0") || datensatzAnspr.get(2).get(0) == null) {
	            		   updateOrInsertAnspr3 = 2;
					} else {
							updateOrInsertAnspr3 = 1;
					}
            }
            String sql;
            sql = schreibeEintragAnsprsql(updateOrInsertAnspr1, datensatzAnspr.get(0));
            _model.insertUpdateDeleteTable(sql);
//            System.out.println(datensatzAnspr.get(0).get(0));
            if (getEditAnspr1()==true) {
         	   if (datensatzAnspr.get(0).get(0).equals("0") || datensatzAnspr.get(0).get(0) == null) {
         		   datensatz.set(27, neueAnsprID);
         		   setAnspr1Id(neueAnsprID);
					}
            }
            sql = schreibeEintragAnsprsql(updateOrInsertAnspr2, datensatzAnspr.get(1));
            _model.insertUpdateDeleteTable(sql);
            if (getEditAnspr2()==true) {
         	   if (datensatzAnspr.get(1).get(0).equals("0") || datensatzAnspr.get(1).get(0) == null) {
         		   datensatz.set(28, neueAnsprID);
         		  setAnspr2Id(neueAnsprID);
					}
            }
            sql = schreibeEintragAnsprsql(updateOrInsertAnspr3, datensatzAnspr.get(2));
            _model.insertUpdateDeleteTable(sql);
            if (getEditAnspr3()==true) {
         	   if (datensatzAnspr.get(2).get(0).equals("0") || datensatzAnspr.get(2).get(0) == null) {
         		   datensatz.set(29, neueAnsprID);
         		  setAnspr3Id(neueAnsprID);
					}
            }
            
            sql = schreibeEintragPraktsql(updateOrInsert, datensatz);
            _model.insertUpdateDeleteTable(sql);
            
            setPraktId(neuePraktID);
            
            /**
             * autocomplete beim speichern
             */
            comboBox_autocomplete();
            /**
             * Ausgewählten Ansprechpartner beibehalten
             */
            String name1 = datensatzAnspr.get(0).get(1);
            String name2 = datensatzAnspr.get(1).get(1);
            String name3 = datensatzAnspr.get(2).get(1);
            comboBox_NameAnsprWoch1.setSelectedItem(name1);
            comboBox_NameAnsprWoch2.setSelectedItem(name2);
            comboBox_NameAnsprWoch3.setSelectedItem(name3);
//            String id1 = datensatzAnspr.get(0).get(0);
//            String id2 = datensatzAnspr.get(1).get(0);
//            String id3 = datensatzAnspr.get(2).get(0);
//            _view.setAnspr1Id(id1);
//            _view.setAnspr2Id(id2);
//            _view.setAnspr3Id(id3);
            
//            HoechstePraktID = getHoechstePraktID();
        } 
	   }
		public Integer getAnspr1ID(){
			return this.idAnspr1;
		}
		public Integer getAnspr2ID(){
			return this.idAnspr2;
		}
		public Integer getAnspr3ID(){
			return this.idAnspr3;
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
		 private void comboBox_autocomplete(){
			   /**
			    * Wohnort
			    */
	       	   ArrayList<ArrayList<String>> daten_comboBoxItemsWohnort = new ArrayList<ArrayList<String>>();
	    	   daten_comboBoxItemsWohnort = _model.getData("SELECT ORT FROM PRAKTIKANTEN WHERE ORT IS NOT NULL AND ORT <> 'null' AND ORT <> '' GROUP BY ORT");
	    	   Object[][] Array = new String[daten_comboBoxItemsWohnort.size()][];
	  			for (int i = 0; i < daten_comboBoxItemsWohnort.size(); i++) {
	  			    ArrayList<String> row = daten_comboBoxItemsWohnort.get(i);
	  			    Array[i] = row.toArray(new String[row.size()]);
	  			}
	  			Vector V1 = new Vector<String>();
	  			for (int i = 0; i < Array.length; i++) {
					V1.add(Array[i][0]);
				}
//	  			System.out.println(V1);
	    	   setComboBoxItems_wohn(V1);
	    	   
	    	   /**
	    	    * Straße
	    	    */
	    	   ArrayList<ArrayList<String>> daten_comboBoxItemsStr= new ArrayList<ArrayList<String>>();
	    	   daten_comboBoxItemsStr = _model.getData("SELECT STR FROM PRAKTIKANTEN WHERE STR IS NOT NULL AND STR <> 'null' AND STR <> '' GROUP BY STR");
	    	   Array = new String[daten_comboBoxItemsStr.size()][];
	  			for (int i = 0; i < daten_comboBoxItemsStr.size(); i++) {
	  			    ArrayList<String> row = daten_comboBoxItemsStr.get(i);
	  			    Array[i] = row.toArray(new String[row.size()]);
	  			}
	  			V1 = new Vector<String>();
	  			for (int i = 0; i < Array.length; i++) {
					V1.add(Array[i][0]);
				}
//	  			System.out.println(V1);
	    	   setComboBoxItems_str(V1);
	    	   
	    	   /**
	    	    * Geburtsort
	    	    */
	    	   ArrayList<ArrayList<String>> daten_comboBoxItemsGeburtsort= new ArrayList<ArrayList<String>>();
	    	   daten_comboBoxItemsGeburtsort = _model.getData("SELECT GO FROM PRAKTIKANTEN WHERE GO IS NOT NULL AND GO <> 'null' AND GO <> '' GROUP BY GO");
	    	   Array = new String[daten_comboBoxItemsGeburtsort.size()][];
	  			for (int i = 0; i < daten_comboBoxItemsGeburtsort.size(); i++) {
	  			    ArrayList<String> row = daten_comboBoxItemsGeburtsort.get(i);
	  			    Array[i] = row.toArray(new String[row.size()]);
	  			}
	  			V1 = new Vector<String>();
	  			for (int i = 0; i < Array.length; i++) {
					V1.add(Array[i][0]);
				}
//	  			System.out.println(V1);
	    	   setComboBoxItems_geburtsort(V1);
	    	   
	    	   /**
	    	    * Schule
	    	    */
	    	   ArrayList<ArrayList<String>> daten_comboBoxItemsSchule= new ArrayList<ArrayList<String>>();
	    	   daten_comboBoxItemsSchule = _model.getData("SELECT SCHULE FROM PRAKTIKANTEN WHERE SCHULE IS NOT NULL AND SCHULE <> 'null' AND SCHULE <> '' GROUP BY SCHULE");
	    	   Array = new String[daten_comboBoxItemsSchule.size()][];
	  			for (int i = 0; i < daten_comboBoxItemsSchule.size(); i++) {
	  			    ArrayList<String> row = daten_comboBoxItemsSchule.get(i);
	  			    Array[i] = row.toArray(new String[row.size()]);
	  			}
	  			V1 = new Vector<String>();
	  			for (int i = 0; i < Array.length; i++) {
					V1.add(Array[i][0]);
				}
//	  			System.out.println(V1);
	    	   setComboBoxItems_schule(V1);
	    	   
	    	   /**
	    	    * Schulform
	    	    */
	    	   ArrayList<ArrayList<String>> daten_comboBoxItemsSchulform= new ArrayList<ArrayList<String>>();
	    	   daten_comboBoxItemsSchulform = _model.getData("SELECT SCHULFORM FROM PRAKTIKANTEN WHERE SCHULFORM IS NOT NULL AND SCHULFORM <> 'null' AND SCHULFORM <> '' GROUP BY SCHULFORM");
	    	   Array = new String[daten_comboBoxItemsSchulform.size()][];
	  			for (int i = 0; i < daten_comboBoxItemsSchulform.size(); i++) {
	  			    ArrayList<String> row = daten_comboBoxItemsSchulform.get(i);
	  			    Array[i] = row.toArray(new String[row.size()]);
	  			}
	  			V1 = new Vector<String>();
	  			for (int i = 0; i < Array.length; i++) {
					V1.add(Array[i][0]);
				}
	  			setComboBoxItems_schulform(V1);
	  			
	  			/**
	  			 * Nachname Ansprechpartner
	  			 */
	  			ArrayList<ArrayList<String>> daten_comboBoxItemsNameAnspr= new ArrayList<ArrayList<String>>();
	  			daten_comboBoxItemsNameAnspr = _model.getData("SELECT NN FROM ANSPRECHPARTNER WHERE NN IS NOT NULL AND NN <> 'null' AND NN <> '' ORDER BY NN");
	     	   Array = new String[daten_comboBoxItemsNameAnspr.size()][];
	   			for (int i = 0; i < daten_comboBoxItemsNameAnspr.size(); i++) {
	   			    ArrayList<String> row = daten_comboBoxItemsNameAnspr.get(i);
	   			    Array[i] = row.toArray(new String[row.size()]);
	   			}
	   			V1 = new Vector<String>();
	   			for (int i = 0; i < Array.length; i++) {
	 				V1.add(Array[i][0]);
	 			}
//	  			System.out.println(V1);
	    	   setComboBoxItems_AnsprNN(V1);
	    	   
		   }
		 /**
			 * Setzt den Inhalt der ComboBox Wohnort
			 * @param v
			 */
			private void setComboBoxItems_wohn(Vector v){
				comboBox_wohn.removeAllItems();
				comboBox_wohn.addItem(""); // leeres Item 
				for (int i = 0; i < v.size(); i++) {
				    comboBox_wohn.addItem(v.get(i));
				}
				AutoCompleteDecorator.decorate(this.comboBox_wohn);
			}
			/**
			 * Setzt den Inhalt der ComboBox Straße
			 * @param v
			 */
			private void setComboBoxItems_str(Vector v){
				comboBox_str.removeAllItems();
				comboBox_str.addItem("");
				for (int i = 0; i < v.size(); i++) {
				    comboBox_str.addItem(v.get(i));
				}
				AutoCompleteDecorator.decorate(this.comboBox_str);
			}
			/**
			 * Setzt den Inhalt der ComboBox Geburtsort
			 * @param v
			 */
			private void setComboBoxItems_geburtsort(Vector v){
				comboBox_geburtsort.removeAllItems();
				comboBox_geburtsort.addItem("");
				for (int i = 0; i < v.size(); i++) {
				    comboBox_geburtsort.addItem(v.get(i));
				}
				AutoCompleteDecorator.decorate(this.comboBox_geburtsort);
			}
			/**
			 * Setzt den Inhalt der ComboBox Schule
			 * @param v
			 */
			private void setComboBoxItems_schule(Vector v){
				comboBox_schule.removeAllItems();
				comboBox_schule.addItem("");
				for (int i = 0; i < v.size(); i++) {
				    comboBox_schule.addItem(v.get(i));
				}
				AutoCompleteDecorator.decorate(this.comboBox_schule);
			}
			/**
			 * Setzt den Inhalt der ComboBox Schulform
			 * @param v
			 */
			private void setComboBoxItems_schulform(Vector v){
				comboBox_schulform.removeAllItems();
				comboBox_schulform.addItem("");
				for (int i = 0; i < v.size(); i++) {
					comboBox_schulform.addItem(v.get(i));
				}
				AutoCompleteDecorator.decorate(this.comboBox_schulform);
			}
			/**
			 * Setzt den Inhalt der ComboBoxen der Ansprechpartner Nachnamen
			 * @param v
			 */
			private void setComboBoxItems_AnsprNN(Vector v){
				comboBox_NameAnsprWoch1.removeAllItems();
				comboBox_NameAnsprWoch2.removeAllItems();
				comboBox_NameAnsprWoch3.removeAllItems();
				comboBox_NameAnsprWoch1.addItem("");
				comboBox_NameAnsprWoch2.addItem("");
				comboBox_NameAnsprWoch3.addItem("");
				for (int i = 0; i < v.size(); i++) {
					comboBox_NameAnsprWoch1.addItem(v.get(i));
					comboBox_NameAnsprWoch2.addItem(v.get(i));
					comboBox_NameAnsprWoch3.addItem(v.get(i));
//					System.out.println(i);
				}
				AutoCompleteDecorator.decorate(this.comboBox_NameAnsprWoch1);
				AutoCompleteDecorator.decorate(this.comboBox_NameAnsprWoch2);
				AutoCompleteDecorator.decorate(this.comboBox_NameAnsprWoch3);
//				comboBox_NameAnsprWoch1.setSelectedIndex(0);
//				comboBox_NameAnsprWoch2.setSelectedIndex(0);
//				comboBox_NameAnsprWoch3.setSelectedIndex(0);
//				comboBox_NameAnsprWoch1.setEditable(false);
//				comboBox_NameAnsprWoch2.setEditable(false);
//				comboBox_NameAnsprWoch3.setEditable(false);
			}
	   private String schreibeEintragPraktsql(int i, ArrayList<String> liste){
			String sql;
			SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss");
			Timestamp time = new Timestamp(System.currentTimeMillis());
			String zeit = sdf.format(time);
			if (i == 0) {
				String info = "Daten geupdatet am " + zeit;
				setInfoPrakt(info);
				liste.set(30, info);
				sql = "UPDATE PRAKTIKANTEN set ANREDE = '" + liste.get(1) + "', NN = '" + liste.get(2) + "', VN = '" + liste.get(3) +
						"', GB = '" + liste.get(4) + "', GO = '" + liste.get(5) + "', STR = '" + liste.get(6) + "', PLZ = '" + liste.get(7) + "', LAND = '" + liste.get(8) + 
						"', TELE = '" + liste.get(9) + "', MAIL = '" + liste.get(10) + "', MOBIL = '" + liste.get(11) + "', HAUSNR = '" + liste.get(12) + "', ORT = '" + liste.get(13) +
						"', GNN = '" + liste.get(14) + "', GVN = '" + liste.get(15) + "', SCHULE = '" + liste.get(16) + "', SCHULFORM = '" + liste.get(17) + "', PARTNERS = '" + liste.get(18) +
						"', ANMERKSCHULE = '" + liste.get(19) + "', MIKI = '" + liste.get(20) + "', GRAD = '" + liste.get(21) + "', ANMERKPERSON = '" + liste.get(22) + "', STARTDATUM = '" + liste.get(23) +
						"', ENDDATUM = '" + liste.get(24) + "', STATUS = '" + liste.get(25) + "', ANMERKPRAKT = '" + liste.get(26) + "', ANSPR1 = '" + liste.get(27) + "', ANSPR2 = '" + liste.get(28) +
						"', ANSPR3 = '" + liste.get(29) + "', INFO = '" + liste.get(30) + "', EDIT = '" + liste.get(31) +"', UNTERLAGENVOLLST = '" + liste.get(32) + "', ANTWORTBIS = '" + liste.get(33) +
						"' WHERE ID = '" + liste.get(0) + "';";
//				System.out.println("update");
			}else if (i == 4) {
				sql = "DELETE from PRAKTIKANTEN where ID='" + liste.get(0) + "';";
			}else{
				hoechstePraktID = _control.getHoechstePraktID();
				hoechstePraktID++;
				neuePraktID = "SP" + hoechstePraktID.toString();
				
				String info = "Daten gespeichert am " + zeit;
				setInfoPrakt(info);
				liste.set(30, info);
				sql = "INSERT INTO PRAKTIKANTEN " +
		                   "VALUES ('" + neuePraktID +"','"+ liste.get(1) +"','"+ liste.get(2) +"','"+ liste.get(3) +"','"+ liste.get(4) +"','"+ liste.get(5) 
		                   +"','"+ liste.get(6) +"','"+ liste.get(7) +"','"+ liste.get(8) +"','"+ liste.get(9) +"','"+ liste.get(10) +"','"+ liste.get(11) +"','"+ liste.get(12)
		                   +"','"+ liste.get(13) +"','"+ liste.get(14) +"','"+ liste.get(15) +"','"+ liste.get(16) +"','"+ liste.get(17) +"','"+ liste.get(18) +"','"+ liste.get(19)
		                   +"','"+ liste.get(20) +"','"+ liste.get(21) +"','"+ liste.get(22) +"','"+ liste.get(23) +"','"+ liste.get(24) +"','"+ liste.get(25) +"','"+ liste.get(26)
		                   +"','"+ liste.get(27) +"','"+ liste.get(28) +"','"+ liste.get(29) +"','"+ liste.get(30) +"','"+ liste.get(31) +"','"+ liste.get(32) +"','"+ liste.get(33) +"');";
//				System.out.println("insert");
			}
			return sql;
		}
		private String schreibeEintragAnsprsql(int i, ArrayList<String> liste){
			String sql;
			SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss");
			Timestamp time = new Timestamp(System.currentTimeMillis());
			String zeit = sdf.format(time);
			if (i == 1) {
				String info = "Daten geupdatet am " + zeit;
				setInfoAnspr(info);
				sql = "UPDATE ANSPRECHPARTNER set NN = '" + liste.get(1) + "', VN = '" + liste.get(2) + "', TELE = '" + liste.get(3) +
						"', MAIL = '" + liste.get(4) + "', ABTEILUNG = '" + liste.get(5) + "', RNR = '" + liste.get(6) + "', ANMERKEINSATZORT = '" + liste.get(7) + "', INFO = '" + info + 
						 "', BLOCKIERENVON = '" + liste.get(8) + "', BLOCKIERENBIS = '" + liste.get(9) + "', ETECH = '" + liste.get(10) + "', KAUFM = '" + liste.get(11) + "', INF = '" + liste.get(12) +"' WHERE ID = '" + liste.get(0) + "';";
//				System.out.println("update");
			} else if (i == 2) {
				hoechsteAnsprID = _control.getHoechsteAnsprID();
				hoechsteAnsprID++;
				neueAnsprID = hoechsteAnsprID.toString();
				String info = "Daten gespeichert am " + zeit;
				setInfoAnspr(info);
				sql = "INSERT INTO ANSPRECHPARTNER " +
						"VALUES ('" + neueAnsprID +"','"+ liste.get(1) +"','"+ liste.get(2) +"','"+ liste.get(3) +"','"+ liste.get(4) +"','"+ liste.get(5) 
		                   +"','"+ liste.get(6) +"','"+ liste.get(7) +"','" + info +"','" + liste.get(8) +"','" + liste.get(9) +"','" + liste.get(10) +"','" + liste.get(11) +"','" + liste.get(12) + "');";
//				System.out.println("insert");
//				System.out.println(liste.get(1));
			}else if ( i== 4) {
				sql = "DELETE from ANSPRECHPARTNER where NN='" + liste.get(0) + "' AND VN ='" + liste.get(1) + "' AND TELE ='" + liste.get(2) + "';";
			} else {
				sql = "";
			}
			return sql;
		}
		public void setInhaltPrakt(ArrayList<ArrayList<String>> daten){
			SimpleDateFormat sdfToDate = new SimpleDateFormat("dd.MM.yyyy");
			textField_id.setText(daten.get(0).get(0));
			comboBox_anrede.setSelectedItem(daten.get(0).get(1));
			textField_nn.setText(daten.get(0).get(2));
			textField_vn.setText(daten.get(0).get(3));
			try {
				datePicker_gb.setDate(sdfToDate.parse(daten.get(0).get(4)));
			} catch (ParseException e) {
				datePicker_gb.setDate(new Date());
			}
			comboBox_geburtsort.setSelectedItem(daten.get(0).get(5));
			comboBox_str.setSelectedItem(daten.get(0).get(6));
			textField_plz.setText(daten.get(0).get(7));
			txtDeutschland.setText(daten.get(0).get(8));
			textField_tele.setText(daten.get(0).get(9));
			textField_mail.setText(daten.get(0).get(10));
			textField_mobil.setText(daten.get(0).get(11));
			textField_haus.setText(daten.get(0).get(12));
			comboBox_wohn.setSelectedItem(daten.get(0).get(13));
			textField_gnn.setText(daten.get(0).get(14));
			textField_gvn.setText(daten.get(0).get(15));
			comboBox_schule.setSelectedItem(daten.get(0).get(16));
			comboBox_schulform.setSelectedItem(daten.get(0).get(17));
			comboBox_partners.setSelectedItem(daten.get(0).get(18));
			textArea_anmerkschule.setText(daten.get(0).get(19));
			comboBox_miki.setSelectedItem(daten.get(0).get(20));
			comboBox_grad.setSelectedItem(daten.get(0).get(21));
			textArea_anmerkperson.setText(daten.get(0).get(22));
			try {
				datePicker_startdatum.setDate(sdfToDate.parse(daten.get(0).get(23)));
			} catch (ParseException e) {
				datePicker_startdatum.setDate(new Date());
			}
			try {
				datePicker_enddatum.setDate(sdfToDate.parse(daten.get(0).get(24)));
			} catch (ParseException e) {
				datePicker_enddatum.setDate(new Date());
			}
			comboBox_status.setSelectedItem(daten.get(0).get(25));
			textArea_anmerkprakt.setText(daten.get(0).get(26));
			setInhaltAnspr1(getAnsprDaten(daten.get(0).get(27)));
			setInhaltAnspr2(getAnsprDaten(daten.get(0).get(28)));
			setInhaltAnspr3(getAnsprDaten(daten.get(0).get(29)));
			textArea_konsole.setText(daten.get(0).get(30));
			if (daten.get(0).get(32).equals("1")) {
				chckbxDatenVollst.setSelected(true);
			}else {
				chckbxDatenVollst.setSelected(false);
			}
			try {
				datePicker_Antwortfrist.setDate(sdfToDate.parse(daten.get(0).get(33)));
			} catch (ParseException e) {
				datePicker_Antwortfrist.setDate(new Date());
			}

		}
		@Override
		public void actionPerformed(ActionEvent evt) {
			Object src = evt.getSource();
			 CardLayout cardLayoutAnspr = (CardLayout) panel_ansprechPartner.getLayout();
			   if (src == button_woche1) {
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
					ansprbearb1gedrueckt = true;
					button_editAnspr1.setEnabled(false);
				} else if (src == button_editAnspr2){
					comboBox_NameAnsprWoch2.setEditable(true);
					textField_VornameAnsprWoch2.setEditable(true);
					textField_TelAnsprWoch2.setEditable(true);
					textField_MailAnsprWoch2.setEditable(true);
					textField_AbteilAnsprWoch2.setEditable(true);
					textField_RaumAnsprWoch2.setEditable(true);
					textArea_EinsatzortAnsprWoche2.setEditable(true);
					ansprbearb2gedrueckt = true;
					button_editAnspr2.setEnabled(false);
				} else if (src == button_editAnspr3){
					comboBox_NameAnsprWoch3.setEditable(true);
					textField_VornameAnsprWoch3.setEditable(true);
					textField_TelAnsprWoch3.setEditable(true);
					textField_MailAnsprWoch3.setEditable(true);
					textField_AbteilAnsprWoch3.setEditable(true);
					textField_RaumAnsprWoch3.setEditable(true);
					textArea_EinsatzortAnsprWoche3.setEditable(true);
					ansprbearb3gedrueckt = true;
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
}
