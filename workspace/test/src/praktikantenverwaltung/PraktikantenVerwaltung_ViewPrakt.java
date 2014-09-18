package praktikantenverwaltung;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.JTextComponent;

import org.jdesktop.swingx.JXDatePicker;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import org.jdesktop.swingx.calendar.DatePickerFormatter;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import javax.swing.JCheckBox;

/**
 * 
 * @author Barathum
 * Klasse die f�r die Praktikanten Ansicht zust�ndig ist
 */
public class PraktikantenVerwaltung_ViewPrakt extends JFrame implements ActionListener, AnsichtPraktikant_Interface{
	/**
	 * erstellen der Fields
	 */
	private static final long serialVersionUID = 1L;
	private PraktikantenVerwaltung_Modell _model; 
	private PraktikantenVerwaltung_Control _control; 
	private JFrame fenster=this;
	private PlatzhalterReplacerUndDokumentWriter _replacer;
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
	private JTextComponent textfield_schule;
	private JTextComponent textfield_schulform;
	private JTextField txtDeutschland;
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
	private JPanel panel_ansprechPartner;
	private JPanel panel_AnsprWoche1;
	private JPanel panel_AnsprWoche2;
	private JPanel panel_AnsprWoche3;
	private JButton button_woche1;
	private JButton button_woche2;
	private JButton button_woche3;
	private JTextComponent textfield_anrede;
	private JTextComponent textfield_partners;
	private JTextArea textArea_anmerkschule;
	private JTextComponent textfield_miki;
	private JTextComponent textfield_grad;
	private JTextComponent textfield_NameAnsprWoch1;
	private JTextArea textArea_EinsatzortAnsprWoche1;
	private JTextComponent textfield_NameAnsprWoch2;
	private JTextArea textArea_EinsatzortAnsprWoche2;
	private JTextField textField_TelAnsprWoch3;
	private JTextArea textArea_EinsatzortAnsprWoche3;
	private JTextArea textArea_anmerkperson;
	private SteppedComboBox comboBox_status;
	private Integer idAnspr1 = new Integer(0);
	private Integer idAnspr2 = new Integer(0);
	private Integer idAnspr3 = new Integer(0);
	private JTextComponent textfield_NameAnsprWoch3;
	private JButton button_editAnspr2;
	private JButton button_editAnspr1;
	private JButton button_editAnspr3;
	private boolean ansprbearb1gedrueckt = false;
	private boolean ansprbearb2gedrueckt = false;
	private boolean ansprbearb3gedrueckt = false;
	private Integer hoechstePraktID = 100000;
	private Integer hoechsteAnsprID = 100000;
	private String neuePraktID = "";
	private String neueAnsprID = "0";
	private JXDatePicker datePicker_Antwortfrist;
	private JTextArea textArea_anmerkprakt;
	private JCheckBox chckbxDatenVollst;
	private int updateOrInsert;
	private JTextComponent textField_geburtsort;
	private ArrayList<String> Geburtsortlist;
	private JTextComponent textfield_str;
	private ArrayList<String> Strasselist;
	private JTextComponent textfield_wohn;
	private ArrayList<String> Wohnortlist;
	private ArrayList<String> partnersList;
	private ArrayList<String> schuleList;
	private ArrayList<String> schulformList;
	private ArrayList<String> mikiList;
	private ArrayList<String> gradList;
	private ArrayList<String> anredeList;
	private ArrayList<String> ansprList;
	private ArrayList<ArrayList<String>> alleAnsprDaten = new ArrayList<ArrayList<String>>();
	private ArrayList<ArrayList<String>> allePraktDaten = new ArrayList<ArrayList<String>>();
	private Vector<String> comboBoxNachrichtenItems = new Vector<String>();
	File f = new File("templates");
	private SteppedComboBox comboBox_NachrichtWahl;
	private String tempFolder = new String("temp");
	private String templateFolder = new String("templates");
	private JButton btnNachrichtErstellen;
	ImageIcon IconEdit = new ImageIcon(new ImageIcon("img/editIcon.png").getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH));
	Color coolBlue = new Color(4, 122, 201);
	
	/**
	 * Kontruktor ohne Daten f�r einen Neuen Praktikanten
	 * @param control Die mitgegebene Control
	 * @param model Das Modell welches mitgegeben wird
	 * @wbp.parser.constructor
	 */
	public PraktikantenVerwaltung_ViewPrakt(PraktikantenVerwaltung_Control control , PraktikantenVerwaltung_Modell model){
		  this._model = model;
		  this._control = control;
		  updateOrInsert = 2;
		  viewKontrukt();
		  comboBox_autocomplete();
		  this.setTitle("Neuer Praktikant");
	}
	/**
	 * Kontruktor mit Daten um einen Praktikanten zu bearbeiten
	 * @param control Die mitgegebene Control
	 * @param model Das Modell welches mitgegeben wird
	 * @param Praktikanteneintrag Die mitgegeben Daten des Praktikanten
	 * @throws IndexOutOfBoundsException Datenliste zu kurz
	 */
	public PraktikantenVerwaltung_ViewPrakt(PraktikantenVerwaltung_Control control , PraktikantenVerwaltung_Modell model , ArrayList<ArrayList<String>> Praktikanteneintrag) throws IndexOutOfBoundsException{
		  this._model = model;
		  this._control = control;
		  updateOrInsert = 1;
		  viewKontrukt();
		  comboBox_autocomplete();
		  setInhaltPrakt(Praktikanteneintrag);
		  this.setTitle(Praktikanteneintrag.get(0).get(3) + " " + Praktikanteneintrag.get(0).get(2) + " bearbeiten");
	}
	/* (non-Javadoc)
	 * @see praktikantenverwaltung.AnsichtPraktikant#viewKontrukt()
	 */
	@Override
	public void viewKontrukt(){
		/**
		 * Frame mit allen Panels usw. erstellen
		 */
			allePraktDaten = _model.getData("SELECT * FROM PRAKTIKANTEN Order By NN");
			alleAnsprDaten = _model.getData("SELECT * FROM ANSPRECHPARTNER Order By NN");
			this._replacer = new PlatzhalterReplacerUndDokumentWriter();
			this.setIconImage(_control.getImg().getImage());
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
			
			
			textfield_wohn = new JTextField();
			Wohnortlist = new ArrayList<String>();
			AutoCompleteDecorator.decorate(textfield_wohn, Wohnortlist, false);
			
			textfield_str = new JTextField();
			Strasselist = new ArrayList<String>();
			AutoCompleteDecorator.decorate(textfield_str, Strasselist, false);
			
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
											.addComponent(textfield_str, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)
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
											.addComponent(textfield_wohn, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)
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
							.addComponent(textfield_str, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_panel_4.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblHausnummer)
							.addComponent(lblMailadresse)
							.addComponent(textfield_wohn, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
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
			
			textfield_partners = new JTextField();
			partnersList = new ArrayList<String>();
			partnersList.add("Nein");
			partnersList.add("Ja");
			AutoCompleteDecorator.decorate(textfield_partners, partnersList, true);
			
			JScrollPane scrollPane = new JScrollPane();
		    
			textfield_schule = new JTextField();
			schuleList = new ArrayList<String>();
			AutoCompleteDecorator.decorate(textfield_schule, schuleList, false);
			
			textfield_schulform = new JTextField();
			schulformList = new ArrayList<String>();
			AutoCompleteDecorator.decorate(textfield_schulform, schulformList, false);
			
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
								.addComponent(textfield_schule, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_panel_5.createSequentialGroup()
								.addComponent(lblSchulform)
								.addPreferredGap(ComponentPlacement.RELATED, 138, Short.MAX_VALUE)
								.addComponent(textfield_schulform, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_panel_5.createSequentialGroup()
								.addComponent(lblSiemensprtnerschule)
								.addPreferredGap(ComponentPlacement.RELATED, 76, Short.MAX_VALUE)
								.addComponent(textfield_partners, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE))
							.addComponent(lblAnmerkungen))
						.addContainerGap())
			);
			gl_panel_5.setVerticalGroup(
				gl_panel_5.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panel_5.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_panel_5.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblNameDerSchule)
							.addComponent(textfield_schule, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_panel_5.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblSchulform)
							.addComponent(textfield_schulform, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_panel_5.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblSiemensprtnerschule)
							.addComponent(textfield_partners, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
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
			
			textfield_miki = new JTextField();
			mikiList = new ArrayList<String>();
			mikiList.add("Nein");
			mikiList.add("Ja");
			AutoCompleteDecorator.decorate(textfield_miki, mikiList, true);
			
			textfield_grad = new JTextField();
			gradList = new ArrayList<String>();
			gradList.add("Eltern");
			gradList.add("Geschwister");
			gradList.add("Onkel/Tante");
			gradList.add("Gro�eltern");
			gradList.add("siehe Anmerkungen");
			AutoCompleteDecorator.decorate(textfield_grad, gradList, false);
			
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
							.addComponent(textfield_miki, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)
							.addComponent(textfield_grad, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE))
						.addContainerGap(26, Short.MAX_VALUE))
			);
			gl_panel_6.setVerticalGroup(
				gl_panel_6.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panel_6.createSequentialGroup()
						.addGap(20)
						.addGroup(gl_panel_6.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblMitarbeiterkind)
							.addComponent(textfield_miki, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(gl_panel_6.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblGrad)
							.addComponent(textfield_grad, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
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

			            /**
						 * 
						 */
						private static final long serialVersionUID = 1L;

						@Override
			            public String valueToString(Object value) throws ParseException {
			                if (value == null) return null;
			                return getFormats()[1].format(value);
			            }
			        } ;
			DefaultFormatterFactory factory = new DefaultFormatterFactory(formatter );
			datePicker_startdatum = new JXDatePicker();
			datePicker_startdatum.setDate(null);
			datePicker_startdatum.setFormats(new SimpleDateFormat("dd.MM.yyyy"));
			datePicker_startdatum.getEditor().setFormatterFactory(factory);

			datePicker_enddatum = new JXDatePicker();
			datePicker_enddatum.setDate(null);
			datePicker_enddatum.setFormats(new SimpleDateFormat("dd.MM.yyyy"));
			datePicker_enddatum.getEditor().setFormatterFactory(factory);
			
			JLabel lblStatus = new JLabel("Status:");
			
			String comboBoxListe_state[] = {"leer", "Eingangsbest�tigung", "Bewerbung unvollst�ndig" ,  "Zusage", "Selbstabsage", "Absage", "anwesend", "abgeschlossen"};
			comboBox_status = new SteppedComboBox(comboBoxListe_state);
		    Dimension d = comboBox_status.getPreferredSize();
		    comboBox_status.setPreferredSize(new Dimension(50, d.height));
		    comboBox_status.setPopupWidth(d.width);
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
			button_woche1.setBackground(coolBlue);
			
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
			
			JLabel lblAnmerkungenZumEinsatzort = new JLabel("Anmerkungen zum Einsatzort");
			lblAnmerkungenZumEinsatzort.setFont(new Font("Tahoma", Font.BOLD, 12));
			
			btnSpeichern = new JButton("Speichern");
			
			JScrollPane scrollPane_5 = new JScrollPane();
			
			JLabel lblStatus_1 = new JLabel("Info");
			lblStatus_1.setFont(new Font("Tahoma", Font.BOLD, 12));
			
			btnNachrichtErstellen = new JButton("Nachricht erstellen");
			
			panel_ansprechPartner = new JPanel();
			
			JPanel panel_1 = new JPanel();
			panel_1.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
			
			String[] str = {
				      "xxxxxxxxxxxxxxxxxxxxxxxxxxxxx"
				    };
			comboBox_NachrichtWahl = new SteppedComboBox(str);
		    d = comboBox_NachrichtWahl.getPreferredSize();
		    comboBox_NachrichtWahl.setPreferredSize(new Dimension(50, d.height));
		    comboBox_NachrichtWahl.setPopupWidth(d.width);
			GroupLayout gl_panel_Steckbrief = new GroupLayout(panel_Steckbrief);
			gl_panel_Steckbrief.setHorizontalGroup(
				gl_panel_Steckbrief.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panel_Steckbrief.createSequentialGroup()
						.addGap(20)
						.addGroup(gl_panel_Steckbrief.createParallelGroup(Alignment.LEADING)
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
												.addPreferredGap(ComponentPlacement.RELATED, 100, Short.MAX_VALUE)
												.addGroup(gl_panel_Steckbrief.createParallelGroup(Alignment.LEADING)
													.addComponent(lblDatenZumPraktikum)
													.addComponent(panel_7, GroupLayout.PREFERRED_SIZE, 236, GroupLayout.PREFERRED_SIZE))
												.addGap(30))
											.addGroup(gl_panel_Steckbrief.createSequentialGroup()
												.addGap(71)
												.addComponent(lblAnmerkungenZumEinsatzort, GroupLayout.PREFERRED_SIZE, 211, GroupLayout.PREFERRED_SIZE)))))
								.addGap(3)
								.addGroup(gl_panel_Steckbrief.createParallelGroup(Alignment.TRAILING)
									.addGroup(gl_panel_Steckbrief.createSequentialGroup()
										.addGroup(gl_panel_Steckbrief.createParallelGroup(Alignment.LEADING)
											.addGroup(gl_panel_Steckbrief.createParallelGroup(Alignment.TRAILING, false)
												.addComponent(btnNachrichtErstellen, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(btnSpeichern, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE))
											.addComponent(comboBox_NachrichtWahl, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE))
										.addGap(18)
										.addGroup(gl_panel_Steckbrief.createParallelGroup(Alignment.LEADING)
											.addComponent(lblStatus_1, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
											.addComponent(scrollPane_5, GroupLayout.PREFERRED_SIZE, 246, GroupLayout.PREFERRED_SIZE)))
									.addGroup(gl_panel_Steckbrief.createSequentialGroup()
										.addComponent(lblAnmerkungenZumPraktikum, GroupLayout.PREFERRED_SIZE, 270, GroupLayout.PREFERRED_SIZE)
										.addGap(112))
									.addGroup(gl_panel_Steckbrief.createSequentialGroup()
										.addGap(25)
										.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 370, Short.MAX_VALUE)
										.addGap(2)))))
						.addGap(49))
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
								.addGap(50)
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
												.addPreferredGap(ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
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
										.addGap(74)
										.addComponent(lblStatus_1, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
										.addGroup(gl_panel_Steckbrief.createParallelGroup(Alignment.TRAILING)
											.addGroup(gl_panel_Steckbrief.createSequentialGroup()
												.addComponent(comboBox_NachrichtWahl, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.UNRELATED)
												.addComponent(btnNachrichtErstellen, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(btnSpeichern, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
											.addComponent(scrollPane_5, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE))
										.addGap(26))))
							.addGroup(gl_panel_Steckbrief.createSequentialGroup()
								.addGap(304)
								.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
								.addGap(196))))
			);
			
			JScrollPane scrollPane_2 = new JScrollPane();
			
			datePicker_Antwortfrist = new JXDatePicker();
			datePicker_Antwortfrist.setDate(null);
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
			
			textfield_NameAnsprWoch1 = new JTextField();
			
			button_editAnspr1 = new JButton(IconEdit);
			button_editAnspr1.setBorder(BorderFactory.createEmptyBorder());
			
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
							.addComponent(textfield_NameAnsprWoch1, GroupLayout.PREFERRED_SIZE, 239, GroupLayout.PREFERRED_SIZE))
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
									.addComponent(textfield_NameAnsprWoch1, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
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
			
			textfield_NameAnsprWoch2 = new JTextField();
			
			button_editAnspr2 = new JButton(IconEdit);
			button_editAnspr2.setBorder(BorderFactory.createEmptyBorder());
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
							.addComponent(textfield_NameAnsprWoch2, 0, 239, Short.MAX_VALUE))
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
									.addComponent(textfield_NameAnsprWoch2, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
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
			
			textfield_NameAnsprWoch3 = new JTextField();
			
			button_editAnspr3 = new JButton(IconEdit);
			button_editAnspr3.setBorder(BorderFactory.createEmptyBorder());
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
							.addComponent(textfield_NameAnsprWoch3, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 239, GroupLayout.PREFERRED_SIZE))
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
									.addComponent(textfield_NameAnsprWoch3, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
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
			textArea_konsole.setDisabledTextColor(Color.blue);
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
			datePicker_gb.setDate(null);
			datePicker_gb.setFormats(new SimpleDateFormat("dd.MM.yyyy"));
			
			datePicker_gb.getEditor().setFormatterFactory(factory);
			
			textfield_anrede = new JTextField();
			anredeList = new ArrayList<String>();
			anredeList.add("Herr");
			anredeList.add("Frau");
			AutoCompleteDecorator.decorate(textfield_anrede, anredeList, false);
			
			textField_geburtsort = new JTextField();
			Geburtsortlist = new ArrayList<String>();
//			lelist.add("yolo");
//			lelist.add("swag");
			AutoCompleteDecorator.decorate(textField_geburtsort, Geburtsortlist, false);
			GroupLayout gl_panel = new GroupLayout(panel);
			gl_panel.setHorizontalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panel.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
							.addGroup(gl_panel.createSequentialGroup()
								.addComponent(lblNewLabel_1)
								.addPreferredGap(ComponentPlacement.RELATED, 94, Short.MAX_VALUE)
								.addComponent(textField_id, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_panel.createSequentialGroup()
								.addComponent(lblAnrede)
								.addPreferredGap(ComponentPlacement.RELATED, 70, Short.MAX_VALUE)
								.addComponent(textfield_anrede, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_panel.createSequentialGroup()
								.addComponent(lblNachname)
								.addPreferredGap(ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
								.addComponent(textField_nn, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_panel.createSequentialGroup()
								.addComponent(lblVorname)
								.addPreferredGap(ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
								.addComponent(textField_vn, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_panel.createSequentialGroup()
								.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
									.addComponent(lblGeburtstag)
									.addComponent(lblGeburtsort))
								.addPreferredGap(ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
								.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
									.addComponent(textField_geburtsort)
									.addComponent(datePicker_gb, GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE))))
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
							.addComponent(textfield_anrede, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
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
							.addComponent(textField_geburtsort, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addContainerGap(37, Short.MAX_VALUE))
			);
			panel.setLayout(gl_panel);
			panel_Steckbrief.setLayout(gl_panel_Steckbrief);
			
			
			/**
			 * Hier werden die Listener gesetzt
			 */
			this.addWindowListener(new WindowAdapter() {
				@Override
				public void windowOpened(WindowEvent e) {
					textfield_anrede.requestFocus();
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
				}
				@Override
				public void windowClosed(WindowEvent e) {
					
				}
				@Override
				public void windowActivated(WindowEvent e) {
//					textfield_anrede.requestFocus();
				}
			});
			this.setPraktSpeichernListener(new PraktSpeichernListener());
			this.setAnsprAusfuellListener1(new AnsprAusfuellListener1());
			
			this.setAnsprAusfuellListener1VN(new AnsprAusfuellListener1VN());
			textField_VornameAnsprWoch1.addFocusListener(new VN1FocusListener());
			
			this.setAnsprAusfuellListener2VN(new AnsprAusfuellListener2VN());
			textField_VornameAnsprWoch2.addFocusListener(new VN2FocusListener());
			
			this.setAnsprAusfuellListener3VN(new AnsprAusfuellListener3VN());
			textField_VornameAnsprWoch3.addFocusListener(new VN3FocusListener());
			
			this.setAnsprAusfuellListener2(new AnsprAusfuellListener2());
			this.setAnsprAusfuellListener3(new AnsprAusfuellListener3());
			this.setSchulformAusfuellListener(new SchulformAusfuellListener());
			this.setMikiAusfuellListener(new MikiAusfuellListener());
			this.setNachrichtSendenListener(new NachrichtSendenListener());
			button_editAnspr1.addActionListener(this);
		    button_editAnspr2.addActionListener(this);
		    button_editAnspr3.addActionListener(this);
		    button_woche1.addActionListener(this);
		    button_woche2.addActionListener(this);
		    button_woche3.addActionListener(this);
			textField_nn.addActionListener(enterAction);
			textField_vn.addActionListener(enterAction);
			datePicker_gb.addActionListener(enterAction);
			textField_plz.addActionListener(enterAction);
			
			txtDeutschland.addActionListener(enterAction);
			textField_tele.addActionListener(enterAction);
			textField_mail.addActionListener(enterAction);
			textField_mobil.addActionListener(enterAction);
			textField_haus.addActionListener(enterAction);
			textField_gnn.addActionListener(enterAction);
			textField_gvn.addActionListener(enterAction);
			datePicker_startdatum.addActionListener(enterAction);
			datePicker_enddatum.addActionListener(enterAction);
			datePicker_Antwortfrist.addActionListener(enterAction);
		    textField_VornameAnsprWoch1.addActionListener(enterAction);
		    textField_TelAnsprWoch1.addActionListener(enterAction);
		    textField_AbteilAnsprWoch1.addActionListener(enterAction);
		    textField_MailAnsprWoch1.addActionListener(enterAction);
		    textField_RaumAnsprWoch1.addActionListener(enterAction);
		    textField_VornameAnsprWoch2.addActionListener(enterAction);
		    textField_TelAnsprWoch2.addActionListener(enterAction);
		    textField_AbteilAnsprWoch2.addActionListener(enterAction);
		    textField_MailAnsprWoch2.addActionListener(enterAction);
		    textField_RaumAnsprWoch2.addActionListener(enterAction);
		    textField_VornameAnsprWoch3.addActionListener(enterAction);
		    textField_TelAnsprWoch3.addActionListener(enterAction);
		    textField_AbteilAnsprWoch3.addActionListener(enterAction);
		    textField_MailAnsprWoch3.addActionListener(enterAction);
		    textField_RaumAnsprWoch3.addActionListener(enterAction);
		    textField_geburtsort.addKeyListener(enterKeylistener);
		    textfield_anrede.addKeyListener(enterKeylistener);
		    textfield_grad.addKeyListener(enterKeylistener);
		    textfield_miki.addKeyListener(enterKeylistener);
		    textfield_partners.addKeyListener(enterKeylistener);
		    textfield_schule.addKeyListener(enterKeylistener);
		    textfield_schulform.addKeyListener(enterKeylistener);
		    textfield_str.addKeyListener(enterKeylistener);
		    textfield_wohn.addKeyListener(enterKeylistener);
		    textfield_NameAnsprWoch1.addKeyListener(enterKeylistener);
		    textfield_NameAnsprWoch2.addKeyListener(enterKeylistener);
		    textfield_NameAnsprWoch3.addKeyListener(enterKeylistener);
		    textArea_anmerkschule.addKeyListener(textAreaEnter);
		    textArea_anmerkperson.addKeyListener(textAreaEnter);
		    textArea_anmerkprakt.addKeyListener(textAreaEnter);
		    textArea_EinsatzortAnsprWoche1.addKeyListener(textAreaEnter);
		    textArea_EinsatzortAnsprWoche2.addKeyListener(textAreaEnter);
		    textArea_EinsatzortAnsprWoche3.addKeyListener(textAreaEnter);
		    getDocx(f);
			setComboBoxItems_Nachricht(comboBoxNachrichtenItems);
	}
	/**
	 * Der KeyAdapter f�r die Textareas, sodass man mit ENTER ins n�chste Feld springt SHIFT/STRG/ALT+ENTER
	 * bewirken Absatz
	 */
	public KeyAdapter textAreaEnter = new KeyAdapter() {
        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                if (e.getModifiers() > 0) {
//                    textArea_anmerkschule.transferFocusBackward();
                	if (e.getSource().equals(textArea_anmerkschule)) {
                		textArea_anmerkschule.append("\n");
					}else if (e.getSource().equals(textArea_anmerkperson)) {
						textArea_anmerkperson.append("\n");
					}else if (e.getSource().equals(textArea_anmerkprakt)) {
						textArea_anmerkprakt.append("\n");
					}else if (e.getSource().equals(textArea_EinsatzortAnsprWoche1)) {
						textArea_EinsatzortAnsprWoche1.append("\n");
					}else if (e.getSource().equals(textArea_EinsatzortAnsprWoche2)) {
						textArea_EinsatzortAnsprWoche2.append("\n");
					}else if (e.getSource().equals(textArea_EinsatzortAnsprWoche3)) {
						textArea_EinsatzortAnsprWoche3.append("\n");
					}
                	
                } else {
                	if (e.getSource().equals(textArea_anmerkschule)) {
                		textArea_anmerkschule.transferFocus();
					}else if (e.getSource().equals(textArea_anmerkperson)) {
						textArea_anmerkperson.transferFocus();
					}else if (e.getSource().equals(textArea_anmerkprakt)) {
						CardLayout cardLayoutAnspr = (CardLayout) panel_ansprechPartner.getLayout();
						cardLayoutAnspr.show(panel_ansprechPartner,"card_woche1");
						button_woche1.setBackground(coolBlue);
						button_woche2.setBackground(null);
						button_woche3.setBackground(null);
						textfield_NameAnsprWoch1.requestFocus();
					}else if (e.getSource().equals(textArea_EinsatzortAnsprWoche1)) {
						CardLayout cardLayoutAnspr = (CardLayout) panel_ansprechPartner.getLayout();
						cardLayoutAnspr.show(panel_ansprechPartner,"card_woche2");
						button_woche1.setBackground(null);
						button_woche2.setBackground(coolBlue);
						button_woche3.setBackground(null);
						textfield_NameAnsprWoch2.requestFocus();
					}else if (e.getSource().equals(textArea_EinsatzortAnsprWoche2)) {
						CardLayout cardLayoutAnspr = (CardLayout) panel_ansprechPartner.getLayout();
						cardLayoutAnspr.show(panel_ansprechPartner,"card_woche3");
						button_woche1.setBackground(null);
						button_woche2.setBackground(null);
						button_woche3.setBackground(coolBlue);
						textfield_NameAnsprWoch3.requestFocus();
					}else if (e.getSource().equals(textArea_EinsatzortAnsprWoche3)) {
						textArea_EinsatzortAnsprWoche3.transferFocus();
					}
                	
                }
                e.consume();
            }
        }
	};
	/**
	 * KeyListener der f�r das togglen der Wochenfarben zust�ndig ist
	 */
	public KeyListener enterKeylistener = new KeyListener() {
		@Override
		public void keyTyped(KeyEvent e) {
//			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
//				KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
//		        manager.getFocusOwner().transferFocus();
//			}
		}
		@Override
		public void keyReleased(KeyEvent e) {
//			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
//				KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
//		        manager.getFocusOwner().transferFocus();
//			}
		}
		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				if (e.getSource() == textfield_NameAnsprWoch1) {
					if (getEditAnspr1() == false) {
						if(textField_VornameAnsprWoch1.isEditable()){
							textField_VornameAnsprWoch1.requestFocus();
						}else{
							CardLayout cardLayoutAnspr = (CardLayout) panel_ansprechPartner.getLayout();
							cardLayoutAnspr.show(panel_ansprechPartner,"card_woche2");
							textfield_NameAnsprWoch2.requestFocus();
							button_woche1.setBackground(null);
							button_woche2.setBackground(coolBlue);
							button_woche3.setBackground(null);
						}
					}else{
						KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
				        manager.getFocusOwner().transferFocus();
					}
				}else if (e.getSource() == textfield_NameAnsprWoch2) {
					if (getEditAnspr2() == false) {
						if(textField_VornameAnsprWoch2.isEditable()){
							textField_VornameAnsprWoch2.requestFocus();
						}else{
							CardLayout cardLayoutAnspr = (CardLayout) panel_ansprechPartner.getLayout();
							cardLayoutAnspr.show(panel_ansprechPartner,"card_woche3");
							textfield_NameAnsprWoch3.requestFocus();
							button_woche1.setBackground(null);
							button_woche2.setBackground(null);
							button_woche3.setBackground(coolBlue);
						}
					}else{
						KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
				        manager.getFocusOwner().transferFocus();
					}
				}else if (e.getSource() == textfield_NameAnsprWoch3) {
					if (getEditAnspr3() == false && textField_VornameAnsprWoch3.isEditable()) {
							textField_VornameAnsprWoch3.requestFocus();
					}else{
						KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
				        manager.getFocusOwner().transferFocus();
					}
				}else {
					KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
			        manager.getFocusOwner().transferFocus();
				}
			}
		}
	};
	/**
	 * ENTER Listener f�r normale Felder
	 * Reihenfolge f�r bestimmte Felder ge�ndert
	 */
	public Action enterAction = new AbstractAction()
		{
			private static final long serialVersionUID = 1L;

			@Override
		    public void actionPerformed(ActionEvent e)
		    {
				if (e.getSource() == txtDeutschland ) {
					textField_tele.requestFocus();
				}else if (e.getSource() == textField_tele) {
					textField_mail.requestFocus();
				}else if (e.getSource() == textField_mobil) {
					textField_gnn.requestFocus();
				}else if (e.getSource() == textField_gnn) {
					textField_gvn.requestFocus();
				}else if (e.getSource() == textField_gvn) {
					textfield_schule.requestFocus();
				}else if (e.getSource() == datePicker_enddatum) {
					datePicker_Antwortfrist.requestFocus();
				}else if (e.getSource() == textField_VornameAnsprWoch1) {
					if(textField_TelAnsprWoch1.isEditable()){
						textField_TelAnsprWoch1.requestFocus();
					}else{
						CardLayout cardLayoutAnspr = (CardLayout) panel_ansprechPartner.getLayout();
						cardLayoutAnspr.show(panel_ansprechPartner,"card_woche2");
						textfield_NameAnsprWoch2.requestFocus();
						button_woche1.setBackground(null);
						button_woche2.setBackground(coolBlue);
						button_woche3.setBackground(null);
					}
				}else if (e.getSource() == textField_VornameAnsprWoch2) {
					if(textField_TelAnsprWoch2.isEditable()){
						textField_TelAnsprWoch2.requestFocus();
					}else{
						CardLayout cardLayoutAnspr = (CardLayout) panel_ansprechPartner.getLayout();
						cardLayoutAnspr.show(panel_ansprechPartner,"card_woche3");
						textfield_NameAnsprWoch3.requestFocus();
						button_woche1.setBackground(null);
						button_woche2.setBackground(null);
						button_woche3.setBackground(coolBlue);
					}
				}else if (e.getSource() == textField_VornameAnsprWoch3 && textField_TelAnsprWoch3.isEditable()) {
						textField_TelAnsprWoch3.requestFocus();
				}else{
					KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
			        manager.getFocusOwner().transferFocus();
				}
		    }
	};
	/* (non-Javadoc)
	 * @see praktikantenverwaltung.AnsichtPraktikant#setPraktSpeichernListener(java.awt.event.ActionListener)
	 */
	@Override
	public void setPraktSpeichernListener(ActionListener l){ 
        this.btnSpeichern.addActionListener(l); 
	}
	/* (non-Javadoc)
	 * @see praktikantenverwaltung.AnsichtPraktikant#setNachrichtSendenListener(java.awt.event.ActionListener)
	 */
	@Override
	public void setNachrichtSendenListener(ActionListener l){
		this.btnNachrichtErstellen.addActionListener(l);
	}
	/**
	 * Setzt einen Listener auf das Textfield Nachname Anspr1
	 * @param l Listener der �bergeben wird
	 */
	public void setAnsprAusfuellListener1(DocumentListener l){
		this.textfield_NameAnsprWoch1.getDocument().addDocumentListener(l);
	}
	/**
	 * Setzt einen Listener auf das Textfield VornameNachname Anspr1
	 * @param l Listener der �bergeben wird
	 */
	public void setAnsprAusfuellListener1VN(DocumentListener l){
		this.textField_VornameAnsprWoch1.getDocument().addDocumentListener(l);
	}
	/**
	 * Setzt einen Listener auf das Textfield Nachname Anspr2
	 * @param l Listener der �bergeben wird
	 */
	public void setAnsprAusfuellListener2(DocumentListener l){
		this.textfield_NameAnsprWoch2.getDocument().addDocumentListener(l);
	}
	/**
	 * Setzt einen Listener auf das Textfield VornameNachname Anspr2
	 * @param l Listener der �bergeben wird
	 */
	public void setAnsprAusfuellListener2VN(DocumentListener l){
		this.textField_VornameAnsprWoch2.getDocument().addDocumentListener(l);
	}
	/**
	 * Setzt einen Listener auf das Textfield Nachname Anspr3
	 * @param l Listener der �bergeben wird
	 */
	public void setAnsprAusfuellListener3(DocumentListener l){
		this.textfield_NameAnsprWoch3.getDocument().addDocumentListener(l);
	}
	/**
	 * Setzt einen Listener auf das Textfield VornameNachname Anspr3
	 * @param l Listener der �bergeben wird
	 */
	public void setAnsprAusfuellListener3VN(DocumentListener l){
		this.textField_VornameAnsprWoch3.getDocument().addDocumentListener(l);
	}
	
	
	/**
	 * Setzt einen Listener auf das Textfield Schule
	 * @param l Listener der �bergeben wird
	 */
	public void setSchulformAusfuellListener(DocumentListener l){
		this.textfield_schule.getDocument().addDocumentListener(l);
	}
	/**
	 * Setzt einen Listener auf das Textfield MiKi
	 * @param l Listener der �bergeben wird
	 */
	public void setMikiAusfuellListener(DocumentListener l){
		this.textfield_miki.getDocument().addDocumentListener(l);
	}
	
	/* (non-Javadoc)
	 * @see praktikantenverwaltung.AnsichtPraktikant#getInhaltPrakt()
	 */
	@Override
	public ArrayList<String> getInhaltPrakt(){
		SimpleDateFormat sdfToDate = new SimpleDateFormat("dd.MM.yyyy");
		ArrayList<String> liste = new ArrayList<String>();
			liste.add((String) textField_id.getText());
			if (textfield_anrede.getText().trim().length() > 0){liste.add((String) textfield_anrede.getText().trim());}else{liste.add("");}
			if (textField_nn.getText().trim().length() > 0){liste.add((String) textField_nn.getText().trim());}else{liste.add("");}
			if (textField_vn.getText().trim().length() > 0){liste.add((String) textField_vn.getText().trim());}else{liste.add("");}
			try {
				if (sdfToDate.format(datePicker_gb.getDate()).trim().length() > 0){liste.add((String) sdfToDate.format(datePicker_gb.getDate()).trim());}else{liste.add("");}
			} catch (Exception e) {
				liste.add("");
			}
			if (textField_geburtsort.getText().trim().length() > 0){liste.add((String) textField_geburtsort.getText().trim());}else{liste.add("");}
			if (textfield_str.getText().trim().length() > 0){liste.add((String) textfield_str.getText().trim());}else{liste.add("");}
			if (textField_plz.getText().trim().length() > 0){liste.add((String) textField_plz.getText().trim());}else{liste.add("");}
			if (txtDeutschland.getText().trim().length() > 0){liste.add((String) txtDeutschland.getText().trim());}else{liste.add("");}
			if (textField_tele.getText().trim().length() > 0){liste.add((String) textField_tele.getText().trim());}else{liste.add("");}
			if (textField_mail.getText().trim().length() > 0){liste.add((String) textField_mail.getText().trim());}else{liste.add("");}
			if (textField_mobil.getText().trim().length() > 0){liste.add((String) textField_mobil.getText().trim());}else{liste.add("");}
			if (textField_haus.getText().trim().length() > 0){liste.add((String) textField_haus.getText().trim());}else{liste.add("");}
			if (textfield_wohn.getText().trim().length() > 0){liste.add((String) textfield_wohn.getText().trim());}else{liste.add("");}
			if (textField_gnn.getText().trim().length() > 0){liste.add((String) textField_gnn.getText().trim());}else{liste.add("");}
			if (textField_gvn.getText().trim().length() > 0){liste.add((String) textField_gvn.getText().trim());}else{liste.add("");}
			if (textfield_schule.getText().trim().length() > 0){liste.add((String) textfield_schule.getText().trim());}else{liste.add("");}
			if (textfield_schulform.getText().trim().length() > 0){liste.add((String) textfield_schulform.getText().trim());}else{liste.add("");}
			if (textfield_partners.getText().trim().length() > 0){liste.add((String) textfield_partners.getText().trim());}else{liste.add("");}
			if (textArea_anmerkschule.getText().trim().length() > 0){liste.add((String) textArea_anmerkschule.getText().trim());}else{liste.add("");}
			if (textfield_miki.getText().trim().length() > 0){liste.add((String) textfield_miki.getText().trim());}else{liste.add("");}
			if (textfield_grad.getText().trim().length() > 0){liste.add((String) textfield_grad.getText().trim());}else{liste.add("");}
			if (textArea_anmerkperson.getText().trim().length() > 0){liste.add((String) textArea_anmerkperson.getText().trim());}else{liste.add("");}
			try{
				if (sdfToDate.format(datePicker_startdatum.getDate()).trim().length() > 0){liste.add((String) sdfToDate.format(datePicker_startdatum.getDate()).trim());}else{liste.add("");}
			} catch (Exception e) {
				liste.add("");
			}
			try{
				if (sdfToDate.format(datePicker_enddatum.getDate()).trim().length() > 0){liste.add((String) sdfToDate.format(datePicker_enddatum.getDate()).trim());}else{liste.add("");}
			} catch (Exception e) {
				liste.add("");
			}
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
			try{
				if (sdfToDate.format(datePicker_Antwortfrist.getDate()).trim().length() > 0){liste.add((String) sdfToDate.format(datePicker_Antwortfrist.getDate()).trim());}else{liste.add("");}
			} catch (Exception e) {
				liste.add("");
			}
			return liste;
	}
	/* (non-Javadoc)
	 * @see praktikantenverwaltung.AnsichtPraktikant#getInhaltAnspr()
	 */
	@Override
	public ArrayList<ArrayList<String>> getInhaltAnspr(){
		new SimpleDateFormat("dd.MM.yyyy");
		ArrayList<ArrayList<String>> liste = new ArrayList<ArrayList<String>>();
			ArrayList<String> eintrag = new ArrayList<String>();
			eintrag.add((String) idAnspr1.toString());
			if (textfield_NameAnsprWoch1.getText().trim().length() > 0){eintrag.add((String) textfield_NameAnsprWoch1.getText().trim());}else{eintrag.add("");}
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
			if (textfield_NameAnsprWoch2.getText().trim().length() > 0){eintrag.add((String) textfield_NameAnsprWoch2.getText().trim());}else{eintrag.add("");}
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
			if (textfield_NameAnsprWoch3.getText().trim().length() > 0){eintrag.add((String) textfield_NameAnsprWoch3.getText().trim());}else{eintrag.add("");}
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
	/* (non-Javadoc)
	 * @see praktikantenverwaltung.AnsichtPraktikant#setPraktId(java.lang.String)
	 */
	@Override
	public void setPraktId(String id){
		textField_id.setText(id);
	}
	/* (non-Javadoc)
	 * @see praktikantenverwaltung.AnsichtPraktikant#setInfoPrakt(java.lang.String)
	 */
	@Override
	public void setInfoPrakt(String inf){
		this.textArea_konsole.setText(inf);
	}
	/* (non-Javadoc)
	 * @see praktikantenverwaltung.AnsichtPraktikant#getInfoPrakt()
	 */
	@Override
	public String getInfoPrakt(){
		return this.textArea_konsole.getText();
	}
	/**
	    * Innere Klasse f�r das Ausf�llen der Ansprechpartner Felder von Anspr 1
	    * @author Barathum
	    *
	    */
	   class AnsprAusfuellListener1 implements DocumentListener{ 
		@Override
		public void changedUpdate(DocumentEvent e) {
			Anspr1Ausfuellen();
		}
		@Override
		public void insertUpdate(DocumentEvent e) {
			Anspr1Ausfuellen();
		}
		@Override
		public void removeUpdate(DocumentEvent e) {
			Anspr1Ausfuellen();
		}  
	   }
	   /**
	    * Methode die pr�ft ob mehr als 1 Eintrag unter dem eingegebenen Nachnamen vorhanden ist
	    * Wenn nein werden die Inhalte der anderen Felder gesetzt
	    * sonst wird das Vornamen Feld freigeschaltet
	    */
	   public void Anspr1Ausfuellen (){
		   if (getEditAnspr1()==false) {
			 ArrayList<ArrayList<String>> daten = new ArrayList<ArrayList<String>>();
			 ArrayList<ArrayList<String>> datenid = new ArrayList<ArrayList<String>>(alleAnsprDaten);
			 for (int i = 0; i < datenid.size(); i++) {
				if (!(datenid.get(i).get(1).matches(getNameAnspr1().get(0) + ".*"))) {
					datenid.remove(i);
					i--;
				}
			}
			 if (datenid.size() == 1 || datenid.size() == 0) {
				 textField_VornameAnsprWoch1.setEditable(false);
				 try {
					 for (int i = 0; i < datenid.size(); i++) {
						 ArrayList<String> daten1d = new ArrayList<String>();
						 daten1d.add(datenid.get(i).get(0));
						 daten1d.add(datenid.get(i).get(1));
						 daten1d.add(datenid.get(i).get(2));
						 daten1d.add(datenid.get(i).get(3));
						 daten1d.add(datenid.get(i).get(4));
						 daten1d.add(datenid.get(i).get(5));
						 daten1d.add(datenid.get(i).get(6));
						 daten1d.add(datenid.get(i).get(7));
						 daten.add(daten1d);
					}
				} catch (Exception e) {
					daten.add(new ArrayList<String>());
				}
				 setInhaltAnspr1(0 , daten);
			} else {
				textField_VornameAnsprWoch1.setEditable(true);
				setInhaltAnspr1(0 , new ArrayList<ArrayList<String>>());
			}
		   }
		}
	   /**
	    * Listener f�r das Vornamen Textfield
	    * @author Barathum
	    *
	    */
	   class AnsprAusfuellListener1VN implements DocumentListener{ 
			@Override
			public void changedUpdate(DocumentEvent e) {
				Anspr1AusfuellenVN();
			}
			@Override
			public void insertUpdate(DocumentEvent e) {
				Anspr1AusfuellenVN();
			}
			@Override
			public void removeUpdate(DocumentEvent e) {
				Anspr1AusfuellenVN();
			}  
		   }
	   /**
	    * F�llt die Felder entsprechend Vor und Nachname
	    */
		   public void Anspr1AusfuellenVN (){
			   if (getEditAnspr1()==false) {
					 ArrayList<ArrayList<String>> daten = new ArrayList<ArrayList<String>>();
					 ArrayList<ArrayList<String>> datenid = new ArrayList<ArrayList<String>>(alleAnsprDaten);
					 for (int i = 0; i < datenid.size(); i++) {
						if (!(datenid.get(i).get(1).matches(getNameAnspr1().get(0) + ".*")) ||!(datenid.get(i).get(2).matches(getNameAnspr1().get(1))) ) {
							datenid.remove(i);
							i--;
						}
					}
					 try {
						 for (int i = 0; i < datenid.size(); i++) {
							 ArrayList<String> daten1d = new ArrayList<String>();
							 daten1d.add(datenid.get(i).get(0));
							 daten1d.add(datenid.get(i).get(1));
							 daten1d.add(datenid.get(i).get(2));
							 daten1d.add(datenid.get(i).get(3));
							 daten1d.add(datenid.get(i).get(4));
							 daten1d.add(datenid.get(i).get(5));
							 daten1d.add(datenid.get(i).get(6));
							 daten1d.add(datenid.get(i).get(7));
							 daten.add(daten1d);
						}
					} catch (Exception e) {
						daten.add(new ArrayList<String>());
					}
					 setInhaltAnspr1(-1 , daten);
			   }
			}
		   /**
		    * Listener f�r den Focus auf das Vornamen Feld
		    * wird ben�tigt, um eine Autovervollst�ndigung zu erzielen
		    * @author Barathum
		    *
		    */
		   class VN1FocusListener implements FocusListener{

			@Override
			public void focusGained(FocusEvent e) {
				if (getEditAnspr1() == false) {
					ArrayList<ArrayList<String>> datenvn = new ArrayList<ArrayList<String>>(alleAnsprDaten);
					for (int i = 0; i < datenvn.size(); i++) {
						if (!(datenvn.get(i).get(1).matches(getNameAnspr1().get(0) + ".*"))) {
							datenvn.remove(i);
							i--;
						}
					}
//					datenvn = _model.getData("SELECT VN FROM ANSPRECHPARTNER WHERE NN LIKE '" + getNameAnspr1().get(0) + "%' ORDER BY VN;");
		       	   ArrayList<String> Liste_DatenVN = new ArrayList<String>();
		    	   for (int i = 0; i < datenvn.size(); i++) {
		    		   Liste_DatenVN.add(datenvn.get(i).get(2));
			    	   }
					AutoCompleteDecorator.decorate(textField_VornameAnsprWoch1, Liste_DatenVN, false);
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				
			}
			   
		   }
	   /**
	    * Innere Klasse f�r das Ausf�llen der Ansprechpartner Felder von Anspr 2
	    * @author Barathum
	    *
	    */
	   class AnsprAusfuellListener2 implements DocumentListener{ 
		@Override
		public void changedUpdate(DocumentEvent e) {
			Anspr2Ausfuellen();
		}
		@Override
		public void insertUpdate(DocumentEvent e) {
			Anspr2Ausfuellen();
		}
		@Override
		public void removeUpdate(DocumentEvent e) {
			Anspr2Ausfuellen();
		}  
	   }
	   /**
	    * Methode die pr�ft ob mehr als 1 Eintrag unter dem eingegebenen Nachnamen vorhanden ist
	    * Wenn nein werden die Inhalte der anderen Felder gesetzt
	    * sonst wird das Vornamen Feld freigeschaltet
	    */
	   public void Anspr2Ausfuellen (){
		   if (getEditAnspr2()==false) {
			 ArrayList<ArrayList<String>> daten = new ArrayList<ArrayList<String>>();
			 ArrayList<ArrayList<String>> datenid = new ArrayList<ArrayList<String>>(alleAnsprDaten);
			 for (int i = 0; i < datenid.size(); i++) {
				if (!(datenid.get(i).get(1).matches(getNameAnspr2().get(0) + ".*"))) {
					datenid.remove(i);
					i--;
				}
			}
			 if (datenid.size() == 1 || datenid.size() == 0) {
				 textField_VornameAnsprWoch2.setEditable(false);
				 try {
					 for (int i = 0; i < datenid.size(); i++) {
						 ArrayList<String> daten1d = new ArrayList<String>();
						 daten1d.add(datenid.get(i).get(0));
						 daten1d.add(datenid.get(i).get(1));
						 daten1d.add(datenid.get(i).get(2));
						 daten1d.add(datenid.get(i).get(3));
						 daten1d.add(datenid.get(i).get(4));
						 daten1d.add(datenid.get(i).get(5));
						 daten1d.add(datenid.get(i).get(6));
						 daten1d.add(datenid.get(i).get(7));
						 daten.add(daten1d);
					}
				} catch (Exception e) {
					daten.add(new ArrayList<String>());
				}
				 setInhaltAnspr2(0 , daten);
			} else {
				textField_VornameAnsprWoch2.setEditable(true);
				setInhaltAnspr2(0 , new ArrayList<ArrayList<String>>());
			}
		   }
		}
	   class AnsprAusfuellListener2VN implements DocumentListener{ 
			@Override
			public void changedUpdate(DocumentEvent e) {
				Anspr2AusfuellenVN();
			}
			@Override
			public void insertUpdate(DocumentEvent e) {
				Anspr2AusfuellenVN();
			}
			@Override
			public void removeUpdate(DocumentEvent e) {
				Anspr2AusfuellenVN();
			}  
		   }
	   public void Anspr2AusfuellenVN (){
		   if (getEditAnspr2()==false) {
				 ArrayList<ArrayList<String>> daten = new ArrayList<ArrayList<String>>();
				 ArrayList<ArrayList<String>> datenid = new ArrayList<ArrayList<String>>(alleAnsprDaten);
				 for (int i = 0; i < datenid.size(); i++) {
					if (!(datenid.get(i).get(1).matches(getNameAnspr2().get(0) + ".*")) ||!(datenid.get(i).get(2).matches(getNameAnspr2().get(1))) ) {
						datenid.remove(i);
						i--;
					}
				}
				 try {
					 for (int i = 0; i < datenid.size(); i++) {
						 ArrayList<String> daten1d = new ArrayList<String>();
						 daten1d.add(datenid.get(i).get(0));
						 daten1d.add(datenid.get(i).get(1));
						 daten1d.add(datenid.get(i).get(2));
						 daten1d.add(datenid.get(i).get(3));
						 daten1d.add(datenid.get(i).get(4));
						 daten1d.add(datenid.get(i).get(5));
						 daten1d.add(datenid.get(i).get(6));
						 daten1d.add(datenid.get(i).get(7));
						 daten.add(daten1d);
					}
				} catch (Exception e) {
					daten.add(new ArrayList<String>());
				}
				 setInhaltAnspr2(-1 , daten);
		   }
		}
	   class VN2FocusListener implements FocusListener{

			@Override
			public void focusGained(FocusEvent e) {
				if (getEditAnspr2() == false) {
					ArrayList<ArrayList<String>> datenvn = new ArrayList<ArrayList<String>>(alleAnsprDaten);
					for (int i = 0; i < datenvn.size(); i++) {
						if (!(datenvn.get(i).get(1).matches(getNameAnspr2().get(0) + ".*"))) {
							datenvn.remove(i);
							i--;
						}
					}
//					datenvn = _model.getData("SELECT VN FROM ANSPRECHPARTNER WHERE NN LIKE '" + getNameAnspr1().get(0) + "%' ORDER BY VN;");
		       	   ArrayList<String> Liste_DatenVN = new ArrayList<String>();
		    	   for (int i = 0; i < datenvn.size(); i++) {
		    		   Liste_DatenVN.add(datenvn.get(i).get(2));
			    	   }
					AutoCompleteDecorator.decorate(textField_VornameAnsprWoch2, Liste_DatenVN, false);
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				
			}
			   
		   }
	   /**
	    * Innere Klasse f�r das Ausf�llen der Ansprechpartner Felder von Anspr 3
	    * @author Barathum
	    *
	    */
	   class AnsprAusfuellListener3 implements DocumentListener{ 
		@Override
		public void changedUpdate(DocumentEvent e) {
			Anspr3Ausfuellen();
		}
		@Override
		public void insertUpdate(DocumentEvent e) {
			Anspr3Ausfuellen();
		}
		@Override
		public void removeUpdate(DocumentEvent e) {
			Anspr3Ausfuellen();
		}  
	   }
	   /**
	    * Methode die pr�ft ob mehr als 1 Eintrag unter dem eingegebenen Nachnamen vorhanden ist
	    * Wenn nein werden die Inhalte der anderen Felder gesetzt
	    * sonst wird das Vornamen Feld freigeschaltet
	    */
	   public void Anspr3Ausfuellen (){
		   if (getEditAnspr3()==false) {
			 ArrayList<ArrayList<String>> daten = new ArrayList<ArrayList<String>>();
			 ArrayList<ArrayList<String>> datenid = new ArrayList<ArrayList<String>>(alleAnsprDaten);
			 for (int i = 0; i < datenid.size(); i++) {
				if (!(datenid.get(i).get(1).matches(getNameAnspr3().get(0) + ".*"))) {
					datenid.remove(i);
					i--;
				}
			}
			 if (datenid.size() == 1 || datenid.size() == 0) {
				 textField_VornameAnsprWoch3.setEditable(false);
				 try {
					 for (int i = 0; i < datenid.size(); i++) {
						 ArrayList<String> daten1d = new ArrayList<String>();
						 daten1d.add(datenid.get(i).get(0));
						 daten1d.add(datenid.get(i).get(1));
						 daten1d.add(datenid.get(i).get(2));
						 daten1d.add(datenid.get(i).get(3));
						 daten1d.add(datenid.get(i).get(4));
						 daten1d.add(datenid.get(i).get(5));
						 daten1d.add(datenid.get(i).get(6));
						 daten1d.add(datenid.get(i).get(7));
						 daten.add(daten1d);
					}
				} catch (Exception e) {
					daten.add(new ArrayList<String>());
				}
				 setInhaltAnspr3(0 , daten);
			} else {
				textField_VornameAnsprWoch3.setEditable(true);
				setInhaltAnspr3(0 , new ArrayList<ArrayList<String>>());
			}
		   }
		}
	   class AnsprAusfuellListener3VN implements DocumentListener{ 
			@Override
			public void changedUpdate(DocumentEvent e) {
				Anspr3AusfuellenVN();
			}
			@Override
			public void insertUpdate(DocumentEvent e) {
				Anspr3AusfuellenVN();
			}
			@Override
			public void removeUpdate(DocumentEvent e) {
				Anspr3AusfuellenVN();
			}  
		   }
	   public void Anspr3AusfuellenVN (){
		   if (getEditAnspr3()==false) {
				 ArrayList<ArrayList<String>> daten = new ArrayList<ArrayList<String>>();
				 ArrayList<ArrayList<String>> datenid = new ArrayList<ArrayList<String>>(alleAnsprDaten);
				 for (int i = 0; i < datenid.size(); i++) {
					if (!(datenid.get(i).get(1).matches(getNameAnspr3().get(0) + ".*")) ||!(datenid.get(i).get(2).matches(getNameAnspr3().get(1))) ) {
						datenid.remove(i);
						i--;
					}
				}
				 try {
					 for (int i = 0; i < datenid.size(); i++) {
						 ArrayList<String> daten1d = new ArrayList<String>();
						 daten1d.add(datenid.get(i).get(0));
						 daten1d.add(datenid.get(i).get(1));
						 daten1d.add(datenid.get(i).get(2));
						 daten1d.add(datenid.get(i).get(3));
						 daten1d.add(datenid.get(i).get(4));
						 daten1d.add(datenid.get(i).get(5));
						 daten1d.add(datenid.get(i).get(6));
						 daten1d.add(datenid.get(i).get(7));
						 daten.add(daten1d);
					}
				} catch (Exception e) {
					daten.add(new ArrayList<String>());
				}
				 setInhaltAnspr3(-1 , daten);
		   }
		}
	   class VN3FocusListener implements FocusListener{

			@Override
			public void focusGained(FocusEvent e) {
				if (getEditAnspr3() == false) {
					ArrayList<ArrayList<String>> datenvn = new ArrayList<ArrayList<String>>(alleAnsprDaten);
					for (int i = 0; i < datenvn.size(); i++) {
						if (!(datenvn.get(i).get(1).matches(getNameAnspr3().get(0) + ".*"))) {
							datenvn.remove(i);
							i--;
						}
					}
//					datenvn = _model.getData("SELECT VN FROM ANSPRECHPARTNER WHERE NN LIKE '" + getNameAnspr1().get(0) + "%' ORDER BY VN;");
		       	   ArrayList<String> Liste_DatenVN = new ArrayList<String>();
		    	   for (int i = 0; i < datenvn.size(); i++) {
		    		   Liste_DatenVN.add(datenvn.get(i).get(2));
			    	   }
//		    	   System.out.println(Liste_DatenVN.toString());
					AutoCompleteDecorator.decorate(textField_VornameAnsprWoch3, Liste_DatenVN, false);
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				
			}
			   
		   }
		   /* (non-Javadoc)
		 * @see praktikantenverwaltung.AnsichtPraktikant#getAnsprDaten(java.lang.String)
		 */
	   @Override
	public ArrayList<ArrayList<String>> getAnsprDaten(String id){
		   ArrayList<ArrayList<String>> daten = new ArrayList<ArrayList<String>>();
		   ArrayList<ArrayList<String>> datenid = new ArrayList<ArrayList<String>>(alleAnsprDaten);
			 for (int i = 0; i < datenid.size(); i++) {
				if (!(datenid.get(i).get(0).matches(id)) ) {
					datenid.remove(i);
					i--;
				}
			}
		   for (int i = 0; i < datenid.size(); i++) {
				 ArrayList<String> daten1d = new ArrayList<String>();
				 daten1d.add(datenid.get(i).get(0));
				 daten1d.add(datenid.get(i).get(1));
				 daten1d.add(datenid.get(i).get(2));
				 daten1d.add(datenid.get(i).get(3));
				 daten1d.add(datenid.get(i).get(4));
				 daten1d.add(datenid.get(i).get(5));
				 daten1d.add(datenid.get(i).get(6));
				 daten1d.add(datenid.get(i).get(7));
				 daten.add(daten1d);
			}
		   return daten;
	   }
	   
	   /**
	    * Getter f�r den Inhalt der textfields Vorname und Nachname Anspr1
	    * @return 1d ArrayList mit den beiden Eingaben
	    */
		public ArrayList<String>  getNameAnspr1(){
			ArrayList<String>  s = new ArrayList<String> ();
			s.add((String) this.textfield_NameAnsprWoch1.getText());
			s.add((String) this.textField_VornameAnsprWoch1.getText());
			return s;
		}
		/**
		    * Getter f�r den Inhalt der textfields Vorname und Nachname Anspr2
		    * @return 1d ArrayList mit den beiden Eingaben
		    */
		public ArrayList<String>  getNameAnspr2(){
			ArrayList<String>  s = new ArrayList<String> ();
			s.add((String) this.textfield_NameAnsprWoch2.getText());
			s.add((String) this.textField_VornameAnsprWoch2.getText());
			return s;
		}
		/**
		    * Getter f�r den Inhalt der textfields Vorname und Nachname Anspr3
		    * @return 1d ArrayList mit den beiden Eingaben
		    */
		public ArrayList<String>  getNameAnspr3(){
			ArrayList<String>  s = new ArrayList<String> ();
			s.add((String) this.textfield_NameAnsprWoch3.getText());
			s.add((String) this.textField_VornameAnsprWoch3.getText());
			return s;
		}
		/**
		 * Innere Klasse f�r den MIKI AusfuellListener
		 * @author Barathum
		 *
		 */
		class MikiAusfuellListener implements DocumentListener{ 
				
			   @Override
				public void changedUpdate(DocumentEvent e) {
				   if (textfield_miki.getText().equals("Nein")) {
						textfield_grad.setText("");
						textfield_grad.setEnabled(false);
					}else{
						textfield_grad.setEnabled(true);
					}
				}
			
				@Override
				public void insertUpdate(DocumentEvent e) {
					if (textfield_miki.getText().equals("Nein")) {
						textfield_grad.setText("");
						textfield_grad.setEnabled(false);
					}else{
						textfield_grad.setEnabled(true);
					}
				}

				@Override
				public void removeUpdate(DocumentEvent e) {
					if (textfield_miki.getText().equals("Nein")) {
						textfield_grad.setText("");
						textfield_grad.setEnabled(false);
					}else{
						textfield_grad.setEnabled(true);
					}
				}
		   }
		/**
		 * Innere Klasse f�r den Schule AusfuellListener
		 * @author Barathum
		 *
		 */
		class SchulformAusfuellListener implements DocumentListener{ 

			   @Override
				public void changedUpdate(DocumentEvent e) {
					fillSchulform();
				}
			
				@Override
				public void insertUpdate(DocumentEvent e) {
					fillSchulform();
				}

				@Override
				public void removeUpdate(DocumentEvent e) {
					fillSchulform();
				}
		   }
		/**
		 * Methode Die die Schulfelder abh�ngig vom Schulnamen ausf�llt
		 */
		public void fillSchulform (){
			 ArrayList<ArrayList<String>> daten = new ArrayList<ArrayList<String>>();
			   ArrayList<String> sname = new ArrayList<String>();
			   sname = getNameSchule();
			   ArrayList<ArrayList<String>> datenschule = new ArrayList<ArrayList<String>>(allePraktDaten);
				 for (int i = 0; i < datenschule.size(); i++) {
					if (!(datenschule.get(i).get(16).matches(sname.get(0) + ".*")) ) {
						datenschule.remove(i);
						i--;
					}
				}
			   for (int i = 0; i < datenschule.size(); i++) {
					 ArrayList<String> daten1d = new ArrayList<String>();
					 daten1d.add(datenschule.get(i).get(17));
					 daten1d.add(datenschule.get(i).get(18));
					 daten.add(daten1d);
				}
//     			daten = _model.getData("SELECT SCHULFORM FROM PRAKTIKANTEN WHERE SCHULE LIKE '" + sname.get(0) + "' AND SCHULFORM IS NOT NULL AND SCHULFORM <> '' AND SCHULFORM <> 'null';");
     			try {
     				setInhaltSchulform(daten.get(daten.size()-1));
				} catch (Exception e) {
					textfield_schulform.setText("");
				}
//     			daten = _model.getData("SELECT PARTNERS FROM PRAKTIKANTEN WHERE SCHULE LIKE '" + sname.get(0) + "' AND PARTNERS IS NOT NULL AND PARTNERS <> '' AND PARTNERS <> 'null';");
     			try{
     				setInhaltPartners(daten.get(daten.size()-1));
    			} catch (Exception e) {
    				textfield_partners.setText("Nein");
    			}
		}
		/**
		 * Getter f�r den Schulnamen
		 * @return 1D ArrayList mit dem Schulnamen
		 */
		public ArrayList<String>  getNameSchule(){
			ArrayList<String> s = new ArrayList<String>();
			s.add((String) this.textfield_schule.getText());
			return s;
		}
		/**
		 * Setter f�r das Schulform Textfield
		 * @param liste Der zu setzende Inhalt
		 */
		public void setInhaltSchulform(ArrayList<String> liste){
			try {
				textfield_schulform.setText(liste.get(0));
			} catch (Exception e) {
				textfield_schulform.setText("");
			}
		}
		/**
		 * Setter f�r das Partnerschule Textfield
		 * @param liste Der zu setzende Inhalt
		 */
		public void setInhaltPartners(ArrayList<String> liste){
			try{
				textfield_partners.setText(liste.get(1));
			} catch (Exception e) {
				textfield_partners.setText("Nein");
			}
		}
		/**
		 * Setzt den Inhalt der Ansprechpartner1 Felder entsprechend der Liste
		 * @param liste Die zu setzenden Inhalte
		 */
		public void setInhaltAnspr1(int i , ArrayList<ArrayList<String>> liste){
			if (i>0) {
				try {
					idAnspr1 = Integer.parseInt(liste.get(0).get(0));
					textfield_NameAnsprWoch1.setText(liste.get(0).get(1));
					textField_VornameAnsprWoch1.setText(liste.get(0).get(2));
					textField_TelAnsprWoch1.setText(liste.get(0).get(3));
					textField_MailAnsprWoch1.setText(liste.get(0).get(4));
					textField_AbteilAnsprWoch1.setText(liste.get(0).get(5));
					textField_RaumAnsprWoch1.setText(liste.get(0).get(6));
					textArea_EinsatzortAnsprWoche1.setText(liste.get(0).get(7));
				} catch (Exception e) {
					setAnspr1Id("0");
					textfield_NameAnsprWoch1.setText("");
					textField_VornameAnsprWoch1.setText("");
					textField_TelAnsprWoch1.setText("");
					textField_MailAnsprWoch1.setText("");
					textField_AbteilAnsprWoch1.setText("");
					textField_RaumAnsprWoch1.setText("");
					textArea_EinsatzortAnsprWoche1.setText("");
				}
			}else if (i < 0) {
				try {
					idAnspr1 = Integer.parseInt(liste.get(0).get(0));
//					textfield_NameAnsprWoch1.setText(liste.get(0).get(1));
//					textField_VornameAnsprWoch1.setText(liste.get(0).get(2));
					textField_TelAnsprWoch1.setText(liste.get(0).get(3));
					textField_MailAnsprWoch1.setText(liste.get(0).get(4));
					textField_AbteilAnsprWoch1.setText(liste.get(0).get(5));
					textField_RaumAnsprWoch1.setText(liste.get(0).get(6));
					textArea_EinsatzortAnsprWoche1.setText(liste.get(0).get(7));
				} catch (Exception e) {
					setAnspr1Id("0");
//					textfield_NameAnsprWoch1.setText("");
//					textField_VornameAnsprWoch1.setText("");
					textField_TelAnsprWoch1.setText("");
					textField_MailAnsprWoch1.setText("");
					textField_AbteilAnsprWoch1.setText("");
					textField_RaumAnsprWoch1.setText("");
					textArea_EinsatzortAnsprWoche1.setText("");
				}
			}
			else {
				try {
					idAnspr1 = Integer.parseInt(liste.get(0).get(0));
					textField_VornameAnsprWoch1.setText(liste.get(0).get(2));
					textField_TelAnsprWoch1.setText(liste.get(0).get(3));
					textField_MailAnsprWoch1.setText(liste.get(0).get(4));
					textField_AbteilAnsprWoch1.setText(liste.get(0).get(5));
					textField_RaumAnsprWoch1.setText(liste.get(0).get(6));
					textArea_EinsatzortAnsprWoche1.setText(liste.get(0).get(7));
				} catch (Exception e) {
					setAnspr1Id("0");
					textField_VornameAnsprWoch1.setText("");
					textField_TelAnsprWoch1.setText("");
					textField_MailAnsprWoch1.setText("");
					textField_AbteilAnsprWoch1.setText("");
					textField_RaumAnsprWoch1.setText("");
					textArea_EinsatzortAnsprWoche1.setText("");
				}
			}
		}
		/**
		 * Setzt den Inhalt der Ansprechpartner2 Felder entsprechend der Liste
		 * @param liste Die zu setzenden Inhalte
		 */
		public void setInhaltAnspr2(int i , ArrayList<ArrayList<String>> liste){
			if (i>0) {
				try {
					idAnspr2 = Integer.parseInt(liste.get(0).get(0));
					textfield_NameAnsprWoch2.setText(liste.get(0).get(1));
					textField_VornameAnsprWoch2.setText(liste.get(0).get(2));
					textField_TelAnsprWoch2.setText(liste.get(0).get(3));
					textField_MailAnsprWoch2.setText(liste.get(0).get(4));
					textField_AbteilAnsprWoch2.setText(liste.get(0).get(5));
					textField_RaumAnsprWoch2.setText(liste.get(0).get(6));
					textArea_EinsatzortAnsprWoche2.setText(liste.get(0).get(7));
				} catch (Exception e) {
					setAnspr2Id("0");
					textfield_NameAnsprWoch2.setText("");
					textField_VornameAnsprWoch2.setText("");
					textField_TelAnsprWoch2.setText("");
					textField_MailAnsprWoch2.setText("");
					textField_AbteilAnsprWoch2.setText("");
					textField_RaumAnsprWoch2.setText("");
					textArea_EinsatzortAnsprWoche2.setText("");
				}
			}else if (i < 0) {
				try {
					idAnspr2 = Integer.parseInt(liste.get(0).get(0));
//					textfield_NameAnsprWoch2.setText(liste.get(0).get(1));
//					textField_VornameAnsprWoch2.setText(liste.get(0).get(2));
					textField_TelAnsprWoch2.setText(liste.get(0).get(3));
					textField_MailAnsprWoch2.setText(liste.get(0).get(4));
					textField_AbteilAnsprWoch2.setText(liste.get(0).get(5));
					textField_RaumAnsprWoch2.setText(liste.get(0).get(6));
					textArea_EinsatzortAnsprWoche2.setText(liste.get(0).get(7));
				} catch (Exception e) {
					setAnspr2Id("0");
//					textfield_NameAnsprWoch2.setText("");
//					textField_VornameAnsprWoch2.setText("");
					textField_TelAnsprWoch2.setText("");
					textField_MailAnsprWoch2.setText("");
					textField_AbteilAnsprWoch2.setText("");
					textField_RaumAnsprWoch2.setText("");
					textArea_EinsatzortAnsprWoche2.setText("");
				}
			}
			else {
				try {
					idAnspr2 = Integer.parseInt(liste.get(0).get(0));
					textField_VornameAnsprWoch2.setText(liste.get(0).get(2));
					textField_TelAnsprWoch2.setText(liste.get(0).get(3));
					textField_MailAnsprWoch2.setText(liste.get(0).get(4));
					textField_AbteilAnsprWoch2.setText(liste.get(0).get(5));
					textField_RaumAnsprWoch2.setText(liste.get(0).get(6));
					textArea_EinsatzortAnsprWoche2.setText(liste.get(0).get(7));
				} catch (Exception e) {
					setAnspr2Id("0");
					textField_VornameAnsprWoch2.setText("");
					textField_TelAnsprWoch2.setText("");
					textField_MailAnsprWoch2.setText("");
					textField_AbteilAnsprWoch2.setText("");
					textField_RaumAnsprWoch2.setText("");
					textArea_EinsatzortAnsprWoche2.setText("");
				}
			}
		}
		/**
		 * Setzt den Inhalt der Ansprechpartner3 Felder entsprechend der Liste
		 * @param liste Die zu setzenden Inhalte
		 */
		public void setInhaltAnspr3(int i , ArrayList<ArrayList<String>> liste){
			if (i>0) {
				try {
					idAnspr3 = Integer.parseInt(liste.get(0).get(0));
					textfield_NameAnsprWoch3.setText(liste.get(0).get(1));
					textField_VornameAnsprWoch3.setText(liste.get(0).get(2));
					textField_TelAnsprWoch3.setText(liste.get(0).get(3));
					textField_MailAnsprWoch3.setText(liste.get(0).get(4));
					textField_AbteilAnsprWoch3.setText(liste.get(0).get(5));
					textField_RaumAnsprWoch3.setText(liste.get(0).get(6));
					textArea_EinsatzortAnsprWoche3.setText(liste.get(0).get(7));
				} catch (Exception e) {
					setAnspr3Id("0");
					textfield_NameAnsprWoch3.setText("");
					textField_VornameAnsprWoch3.setText("");
					textField_TelAnsprWoch3.setText("");
					textField_MailAnsprWoch3.setText("");
					textField_AbteilAnsprWoch3.setText("");
					textField_RaumAnsprWoch3.setText("");
					textArea_EinsatzortAnsprWoche3.setText("");
				}
			}else if (i < 0) {
				try {
					idAnspr3 = Integer.parseInt(liste.get(0).get(0));
//					textfield_NameAnsprWoch3.setText(liste.get(0).get(1));
//					textField_VornameAnsprWoch3.setText(liste.get(0).get(2));
					textField_TelAnsprWoch3.setText(liste.get(0).get(3));
					textField_MailAnsprWoch3.setText(liste.get(0).get(4));
					textField_AbteilAnsprWoch3.setText(liste.get(0).get(5));
					textField_RaumAnsprWoch3.setText(liste.get(0).get(6));
					textArea_EinsatzortAnsprWoche3.setText(liste.get(0).get(7));
				} catch (Exception e) {
					setAnspr3Id("0");
//					textfield_NameAnsprWoch3.setText("");
//					textField_VornameAnsprWoch3.setText("");
					textField_TelAnsprWoch3.setText("");
					textField_MailAnsprWoch3.setText("");
					textField_AbteilAnsprWoch3.setText("");
					textField_RaumAnsprWoch3.setText("");
					textArea_EinsatzortAnsprWoche3.setText("");
				}
			}
			else {
				try {
					idAnspr3 = Integer.parseInt(liste.get(0).get(0));
					textField_VornameAnsprWoch3.setText(liste.get(0).get(2));
					textField_TelAnsprWoch3.setText(liste.get(0).get(3));
					textField_MailAnsprWoch3.setText(liste.get(0).get(4));
					textField_AbteilAnsprWoch3.setText(liste.get(0).get(5));
					textField_RaumAnsprWoch3.setText(liste.get(0).get(6));
					textArea_EinsatzortAnsprWoche3.setText(liste.get(0).get(7));
				} catch (Exception e) {
					setAnspr3Id("0");
					textField_VornameAnsprWoch3.setText("");
					textField_TelAnsprWoch3.setText("");
					textField_MailAnsprWoch3.setText("");
					textField_AbteilAnsprWoch3.setText("");
					textField_RaumAnsprWoch3.setText("");
					textArea_EinsatzortAnsprWoche3.setText("");
				}
			}
		}
	/**
	    * Innere Klasse f�r den Praktikanten Speichern Listener
	    * Pr�ft ob Praktikanten Id vorhanden und ob die Ansprechpartner IDS vorhanden sind
	    * ruft danach Methode zum schreiben des SQL Befehls auf
	    * �bergibt dann dem Model den SQL Befehl zur Ausf�hrung
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
            
            /**
             * Hier Abfrage ob Praktikant 1 2 oder 3 Wochen vorort
             * Bei 1 Woche nichts tun bei 2 oder 3 Wochen pr�fen ob Anspr Woche 2 und oder 3 leer
             * Wenn 1 gef�llt 2 und 3 leer und dauer Praktikum 3 Wochen dann Anspr 1 auf Woche 2 und 3 �bertragen
             * usw.
             */
            String idAnspr1Datensatz = datensatz.get(27);
            String idAnspr2Datensatz = datensatz.get(28);
            String idAnspr3Datensatz = datensatz.get(29);
            DateTimeFormatter dateStringFormat = DateTimeFormat.forPattern("dd.MM.yyyy");
            String timeStart = datensatz.get(23);
            try {
            	DateTime dateStart = dateStringFormat.parseDateTime(timeStart);
                String timeEnd = datensatz.get(24);
                DateTime dateEnd = dateStringFormat.parseDateTime(timeEnd);
                int anzahlTage = Days.daysBetween(dateStart.withTimeAtStartOfDay() , dateEnd.withTimeAtStartOfDay() ).getDays() + 1 ;
                if (anzahlTage > 7) {
                	if (idAnspr1Datensatz.equals("0")) {
    					datensatz.set(27, datensatz.get(28));
    					idAnspr1Datensatz = datensatz.get(27);
    				}
                	if (idAnspr2Datensatz.equals("0")) {
    					datensatz.set(28, datensatz.get(27));
    					idAnspr1Datensatz = datensatz.get(28);
    				}
    				if (anzahlTage > 14) {
    					if (idAnspr1Datensatz.equals("0")) {
    						datensatz.set(27, datensatz.get(29));
    					}
    	            	if (idAnspr2Datensatz.equals("0")) {
    						datensatz.set(28, datensatz.get(29));
    					}
    					if (idAnspr3Datensatz.equals("0")) {
    						datensatz.set(29, datensatz.get(28));
    					}
    				}
    			}
			} catch (IllegalArgumentException e2) {
				
			}
            fenster.setTitle(datensatz.get(3) + " " + datensatz.get(2) + " bearbeiten");
            
            sql = schreibeEintragPraktsql(updateOrInsert, datensatz);
            _model.insertUpdateDeleteTable(sql);
            updateOrInsert = 1;// sollte der Praktikant noch nicht existiert haben ist dies nun der Fall daher updateOrInsert auf 1 f�r update
            
            setPraktId(neuePraktID);
            
            allePraktDaten = _model.getData("SELECT * FROM PRAKTIKANTEN");
  		  	alleAnsprDaten = _model.getData("SELECT * FROM ANSPRECHPARTNER");
            /**
             * autocomplete beim speichern
             */
            comboBox_autocomplete();
        } 
	   }
	   /**
	    * Innere Klasse f�rs Nachricht senden
	    * erstellt Liste mit platzhaltern �bergibt dannm platzhalter und Inhalte dem Document writer
	    * @author Barathum
	    *
	    */
	   class NachrichtSendenListener implements ActionListener{ 
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ArrayList<String> platzhalter = new ArrayList<String>();
	            platzhalter.add("<<ID>>");
	            platzhalter.add("<<Anrede>>");
	            platzhalter.add("<<Nachname>>");
	            platzhalter.add("<<Vorname>>");
	            platzhalter.add("<<Geburtsdatum>>");
	            platzhalter.add("<<Geburtsort>>");
	            platzhalter.add("<<Stra�e>>");
	            platzhalter.add("<<PLZ>>");
	            platzhalter.add("<<Land>>");
	            platzhalter.add("<<Telefon>>");
	            platzhalter.add("<<Mail>>");
	            platzhalter.add("<<Mobil>>");
	            platzhalter.add("<<Hausnummer>>");
	            platzhalter.add("<<Ort>>");
	            platzhalter.add("<<VertreterNachname>>");
	            platzhalter.add("<<VertreterVorname>>");
	            platzhalter.add("<<Schule>>");
	            platzhalter.add("<<Schulform>>");
	            platzhalter.add("<<Partnerschule>>");
	            platzhalter.add("<<AnmerkungenSchule>>");
	            platzhalter.add("<<Miki>>");
	            platzhalter.add("<<Miki-Grad>>");
	            platzhalter.add("<<AnmerkungenPerson>>");
	            platzhalter.add("<<Startdatum>>");
	            platzhalter.add("<<Enddatum>>");
	            platzhalter.add("<<Status>>");
	            platzhalter.add("<<AnmerkungenPraktikum>>");
	            platzhalter.add("<<Anspr1>>");
	            platzhalter.add("<<Anspr2>>");
	            platzhalter.add("<<Anspr3>>");
	            platzhalter.add("<<Info>>");
	            platzhalter.add("<<Edit>>");
	            platzhalter.add("<<Unterlagenvollst>>");
	            platzhalter.add("<<AntwortBis>>");
	            platzhalter.add("<<AnredePostfix>>");
	            platzhalter.add("<<AnsprID1>>");
	            platzhalter.add("<<NachnameAnspr>>");
	            platzhalter.add("<<VornameAnspr>>");
	            platzhalter.add("<<TeleAnspr>>");
	            platzhalter.add("<<MailAnspr>>");
	            platzhalter.add("<<AbteilungAnspr>>");
	            platzhalter.add("<<Raumnummer>>");
	            ArrayList<ArrayList<String>> daten = new ArrayList<ArrayList<String>>();
	            ArrayList<String> daten1d = new ArrayList<String>();
	            daten1d.addAll(getInhaltPrakt());
		        /**
	             * Postfix f�r Anrede hinzuf�gen
	             * Wenn Herr dann r sonst nichts
	             */
	            if (daten1d.get(1).equals("Herr")) {
	                daten1d.add("r");
				} else {
					daten1d.add("");
				}
	            daten1d.addAll(getInhaltAnspr().get(0));
	            daten.add(daten1d);
            	String nachricht = getNachrichtWahl();
	            
               _replacer.schreibeNeuesWordDokumentVonTemplate(templateFolder + "/" + nachricht + ".docx", 
    		   tempFolder + "/" + daten.get(0).get(2) + daten.get(0).get(3) + "-" + nachricht + ".docx",
    		   platzhalter, daten.get(0));
               
               statusupdate(nachricht , daten.get(0).get(0));
			}
		}
	   /* (non-Javadoc)
	 * @see praktikantenverwaltung.AnsichtPraktikant#getNachrichtWahl()
	 */
	   @Override
	public String getNachrichtWahl(){
			return comboBox_NachrichtWahl.getSelectedItem().toString();
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
		/**
		 * Methode die die Daten f�rs Autocomplete setzt
		 */
		public void comboBox_autocomplete(){
			 ArrayList<ArrayList<String>> daten = new ArrayList<ArrayList<String>>(allePraktDaten);
			 for (int i = 0; i < daten.size(); i++) {
				if (!(daten.get(i).get(13).matches(".*")) ) {
					daten.remove(i);
					i--;
				}else {
					
				}
			}
			   /**
			    * Wohnort
			    */
	       	   ArrayList<ArrayList<String>> daten_ListItemsWohnort = new ArrayList<ArrayList<String>>();
	       	   ArrayList<String> Liste_DatenWohnort = new ArrayList<String>();
		   for (int i = 0; i < daten.size(); i++) {
				 ArrayList<String> daten1d = new ArrayList<String>();
				 try {
					 daten1d.add(daten.get(i).get(13));
					 daten_ListItemsWohnort.add(daten1d);
				} catch (Exception e) {
					daten_ListItemsWohnort.add(new ArrayList<String>());
				}
			}
//	    	   daten_ListItemsWohnort = _model.getData("SELECT ORT FROM PRAKTIKANTEN WHERE ORT IS NOT NULL AND ORT <> 'null' AND ORT <> '' GROUP BY ORT");
		   for (int i = 0; i < daten_ListItemsWohnort.size(); i++) {
	    		   Liste_DatenWohnort.add(daten_ListItemsWohnort.get(i).get(0));
		    	   }
	    	   setListItems_wohn(Liste_DatenWohnort);
	    	   
	    	   /**
	    	    * Stra�e
	    	    */
	    	   ArrayList<ArrayList<String>> daten_ListItemsStr= new ArrayList<ArrayList<String>>();
	    	   ArrayList<String> Liste_DatenStrasse = new ArrayList<String>();
	    	   
	    	   for (int i = 0; i < daten.size(); i++) {
					 ArrayList<String> daten1d = new ArrayList<String>();
					 try {
						 daten1d.add(daten.get(i).get(6));
						 daten_ListItemsStr.add(daten1d);
					} catch (Exception e) {
						daten_ListItemsStr.add(new ArrayList<String>());
					}
				}
//	    	   daten_ListItemsStr = _model.getData("SELECT STR FROM PRAKTIKANTEN WHERE STR IS NOT NULL AND STR <> 'null' AND STR <> '' GROUP BY STR");
	    	   for (int i = 0; i < daten_ListItemsStr.size(); i++) {
	    		   Liste_DatenStrasse.add(daten_ListItemsStr.get(i).get(0));
		    	   }
	    	   setListItems_str(Liste_DatenStrasse);
	    	   
	    	   /**
	    	    * Geburtsort
	    	    */
	    	   ArrayList<ArrayList<String>> daten_ListItemsGeburtsort= new ArrayList<ArrayList<String>>();
	    	   ArrayList<String> Liste_DatenGeburtsort = new ArrayList<String>();
	    	   
	    	   for (int i = 0; i < daten.size(); i++) {
					 ArrayList<String> daten1d = new ArrayList<String>();
					 try {
						 daten1d.add(daten.get(i).get(5));
						 daten_ListItemsGeburtsort.add(daten1d);
					} catch (Exception e) {
						daten_ListItemsGeburtsort.add(new ArrayList<String>());
					}
				}
//	    	   daten_ListItemsGeburtsort = _model.getData("SELECT GO FROM PRAKTIKANTEN WHERE GO IS NOT NULL AND GO <> 'null' AND GO <> '' GROUP BY GO");
	    	   for (int i = 0; i < daten_ListItemsGeburtsort.size(); i++) {
				Liste_DatenGeburtsort.add(daten_ListItemsGeburtsort.get(i).get(0));
	    	   }
	    	   setListItems_geburtsort(Liste_DatenGeburtsort);
	    	   
	    	   /**
	    	    * Schule
	    	    */
	    	   ArrayList<ArrayList<String>> daten_ListItemsSchule= new ArrayList<ArrayList<String>>();
	    	   ArrayList<String> Liste_DatenSchule = new ArrayList<String>();
	    	   
	    	   for (int i = 0; i < daten.size(); i++) {
					 ArrayList<String> daten1d = new ArrayList<String>();
					 try {
						 daten1d.add(daten.get(i).get(16));
						 daten_ListItemsSchule.add(daten1d);
					} catch (Exception e) {
						daten_ListItemsSchule.add(new ArrayList<String>());
					}
				}
//	    	   daten_ListItemsSchule = _model.getData("SELECT SCHULE FROM PRAKTIKANTEN WHERE SCHULE IS NOT NULL AND SCHULE <> 'null' AND SCHULE <> '' GROUP BY SCHULE");
	  			for (int i = 0; i < daten_ListItemsSchule.size(); i++) {
	  				Liste_DatenSchule.add(daten_ListItemsSchule.get(i).get(0));
		    	   }
	    	   setComboBoxItems_schule(Liste_DatenSchule);
	    	   
	    	   /**
	    	    * Schulform
	    	    */
	    	   ArrayList<ArrayList<String>> daten_ListItemsSchulform= new ArrayList<ArrayList<String>>();
	    	   ArrayList<String> Liste_DatenSchulform = new ArrayList<String>();
	    	   
	    	   for (int i = 0; i < daten.size(); i++) {
					 ArrayList<String> daten1d = new ArrayList<String>();
					 try {
						 daten1d.add(daten.get(i).get(17));
						 daten_ListItemsSchulform.add(daten1d);
					} catch (Exception e) {
						daten_ListItemsSchulform.add(new ArrayList<String>());
					}
				}
//	    	   daten_ListItemsSchulform = _model.getData("SELECT SCHULFORM FROM PRAKTIKANTEN WHERE SCHULFORM IS NOT NULL AND SCHULFORM <> 'null' AND SCHULFORM <> '' GROUP BY SCHULFORM");
	    	   for (int i = 0; i < daten_ListItemsSchulform.size(); i++) {
	    		   Liste_DatenSchulform.add(daten_ListItemsSchulform.get(i).get(0));
		    	   }
	    	   setComboBoxItems_schulform(Liste_DatenSchulform);
	  			
	  			/**
	  			 * Nachname Ansprechpartner
	  			 */
	    	   daten = new ArrayList<ArrayList<String>>(alleAnsprDaten);
				 for (int i = 0; i < daten.size(); i++) {
					if (!(daten.get(i).get(13).matches(".*")) ) {
						daten.remove(i);
						i--;
					}else {
						
					}
				}
				 
	  			ArrayList<ArrayList<String>> daten_ListItemsNameAnspr= new ArrayList<ArrayList<String>>();
	  			ArrayList<String> Liste_DatenAnspr = new ArrayList<String>();
	  			
	  			for (int i = 0; i < daten.size(); i++) {
					 ArrayList<String> daten1d = new ArrayList<String>();
					 try {
						 daten1d.add(daten.get(i).get(1));
						 daten_ListItemsNameAnspr.add(daten1d);
					} catch (Exception e) {
						daten_ListItemsNameAnspr.add(new ArrayList<String>());
					}
				}
//	  			daten_ListItemsNameAnspr = _model.getData("SELECT NN FROM ANSPRECHPARTNER WHERE NN IS NOT NULL AND NN <> 'null' AND NN <> '' ORDER BY NN");
	   			for (int i = 0; i < daten_ListItemsNameAnspr.size(); i++) {
	   				Liste_DatenAnspr.add(daten_ListItemsNameAnspr.get(i).get(0));
			    }
	    	   setComboBoxItems_AnsprNN(Liste_DatenAnspr);
	    	   
		   }
		 /**
			 * Setzt den Inhalt der ComboBox Wohnort
			 * @param liste Die Liste der Wohnorte
			 */
		 	public void setListItems_wohn(ArrayList<String> liste){
				Wohnortlist = liste;
				AutoCompleteDecorator.decorate(textfield_wohn, Wohnortlist, false);
			}
			/**
			 * Setzt den Inhalt der ComboBox Stra�e
			 * @param liste Die Liste der Stra�en
			 */
		 	public void setListItems_str(ArrayList<String> liste){
				Strasselist = liste;
				AutoCompleteDecorator.decorate(textfield_str, Strasselist, false);
			}
			/**
			 * Setzt den Inhalt der ComboBox Geburtsort
			 * @param liste Die Liste der Geburtsorte
			 */
		 	public void setListItems_geburtsort(ArrayList<String> liste){
				Geburtsortlist = liste;
				AutoCompleteDecorator.decorate(textField_geburtsort, Geburtsortlist, false);
			}
			/**
			 * Setzt den Inhalt der ComboBox Schule
			 * @param liste Die Liste der Schulen
			 */
			
		 	public void setComboBoxItems_schule(ArrayList<String> liste){
				schuleList = liste;
				AutoCompleteDecorator.decorate(textfield_schule, schuleList, false);
			}
			/**
			 * Setzt den Inhalt der ComboBox Schulform
			 * @param liste Die Liste der Schulform
			 */
		 	public void setComboBoxItems_schulform(ArrayList<String> liste){
				schulformList = liste;
				AutoCompleteDecorator.decorate(textfield_schulform, schulformList, false);
			}
			/**
			 * Setzt den Inhalt der ComboBoxen der Ansprechpartner Nachnamen
			 * @param liste Die Liste der Nachnamen der Ansprechpartner
			 */
		 	public void setComboBoxItems_AnsprNN(ArrayList<String> liste){
				ansprList = liste;
				AutoCompleteDecorator.decorate(textfield_NameAnsprWoch1, ansprList, false);
				AutoCompleteDecorator.decorate(textfield_NameAnsprWoch2, ansprList, false);
				AutoCompleteDecorator.decorate(textfield_NameAnsprWoch3, ansprList, false);
			}
			/* (non-Javadoc)
			 * @see praktikantenverwaltung.AnsichtPraktikant#schreibeEintragPraktsql(int, java.util.ArrayList)
			 */
	   @Override
	public String schreibeEintragPraktsql(int i, ArrayList<String> liste){
			String sql;
			SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss");
			Timestamp time = new Timestamp(System.currentTimeMillis());
			String zeit = sdf.format(time);
			if (i == 1) {
				String info;
				info = liste.get(30);
				info = info + "\n" + "Daten geupdatet am " + zeit;
				setInfoPrakt(info);
				liste.set(30, info);
				neuePraktID = liste.get(0);
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
			}else if (i == 2){
				String info;
				hoechstePraktID = _control.getHoechstePraktID();
				hoechstePraktID++;
				neuePraktID = "SP" + hoechstePraktID.toString();
				if (getInfoPrakt().equals("")) {
					info = "Daten gespeichert am " + zeit;
				} else {
					info = getInfoPrakt() + "\n" + "Daten gespeichert am " + zeit;
				}
				setInfoPrakt(info);
				liste.set(30, info);
				sql = "INSERT INTO PRAKTIKANTEN " +
		                   "VALUES ('" + neuePraktID +"','"+ liste.get(1) +"','"+ liste.get(2) +"','"+ liste.get(3) +"','"+ liste.get(4) +"','"+ liste.get(5) 
		                   +"','"+ liste.get(6) +"','"+ liste.get(7) +"','"+ liste.get(8) +"','"+ liste.get(9) +"','"+ liste.get(10) +"','"+ liste.get(11) +"','"+ liste.get(12)
		                   +"','"+ liste.get(13) +"','"+ liste.get(14) +"','"+ liste.get(15) +"','"+ liste.get(16) +"','"+ liste.get(17) +"','"+ liste.get(18) +"','"+ liste.get(19)
		                   +"','"+ liste.get(20) +"','"+ liste.get(21) +"','"+ liste.get(22) +"','"+ liste.get(23) +"','"+ liste.get(24) +"','"+ liste.get(25) +"','"+ liste.get(26)
		                   +"','"+ liste.get(27) +"','"+ liste.get(28) +"','"+ liste.get(29) +"','"+ liste.get(30) +"','"+ liste.get(31) +"','"+ liste.get(32) +"','"+ liste.get(33) +"');";
//				System.out.println("insert");
			} else{
				sql = "";
			}
			return sql;
		}
	   /* (non-Javadoc)
	 * @see praktikantenverwaltung.AnsichtPraktikant#schreibeEintragAnsprsql(int, java.util.ArrayList)
	 */
		@Override
		public String schreibeEintragAnsprsql(int i, ArrayList<String> liste){
			String sql;
			SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss");
			Timestamp time = new Timestamp(System.currentTimeMillis());
			String zeit = sdf.format(time);
			
			ArrayList<ArrayList<String>> daten = new ArrayList<ArrayList<String>>(alleAnsprDaten);
			 for (int j = 0; j < daten.size(); j++) {
				if (!(daten.get(j).get(13).matches(liste.get(0))) ) {
					daten.remove(j);
					j--;
				}
			}
			 ArrayList<ArrayList<String>> infoAnspr = new ArrayList<ArrayList<String>>();
			 for (int j = 0; j < daten.size(); j++) {
				 ArrayList<String> daten1d = new ArrayList<String>();
				 daten1d.add(daten.get(j).get(8));
				 infoAnspr.add(daten1d);
			}
			if (i == 1) {
//				ArrayList<ArrayList<String>> infoAnspr = _model.getData("Select INFO From ANSPRECHPARTNER WHERE ID = '" + liste.get(0) + "';");
				String info;
				info = infoAnspr.get(0).get(0);
				info = info + "\n" + "Daten geupdatet am " + zeit;
				sql = "UPDATE ANSPRECHPARTNER set NN = '" + liste.get(1) + "', VN = '" + liste.get(2) + "', TELE = '" + liste.get(3) +
						"', MAIL = '" + liste.get(4) + "', ABTEILUNG = '" + liste.get(5) + "', RNR = '" + liste.get(6) + "', ANMERKEINSATZORT = '" + liste.get(7) + "', INFO = '" + info + 
						 "', BLOCKIERENVON = '" + liste.get(8) + "', BLOCKIERENBIS = '" + liste.get(9) + "', ETECH = '" + liste.get(10) + "', KAUFM = '" + liste.get(11) + "', INF = '" + liste.get(12) +"' WHERE ID = '" + liste.get(0) + "';";
//				System.out.println("update");
			} else if (i == 2) {
				hoechsteAnsprID = _control.getHoechsteAnsprID();
				hoechsteAnsprID++;
				neueAnsprID = hoechsteAnsprID.toString();
				String info = "Daten gespeichert am " + zeit;
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
		/* (non-Javadoc)
		 * @see praktikantenverwaltung.AnsichtPraktikant#setInhaltPrakt(java.util.ArrayList)
		 */
		@Override
		public void setInhaltPrakt(ArrayList<ArrayList<String>> daten) throws IndexOutOfBoundsException{
			SimpleDateFormat sdfToDate = new SimpleDateFormat("dd.MM.yyyy");
				textField_id.setText(daten.get(0).get(0));
				textfield_anrede.setText(daten.get(0).get(1));
				textField_nn.setText(daten.get(0).get(2));
				textField_vn.setText(daten.get(0).get(3));
				try {
					datePicker_gb.setDate(sdfToDate.parse(daten.get(0).get(4)));
				} catch (ParseException e) {
					datePicker_gb.setDate(null);
				}
				textField_geburtsort.setText(daten.get(0).get(5));
				textfield_str.setText(daten.get(0).get(6));
				textField_plz.setText(daten.get(0).get(7));
				txtDeutschland.setText(daten.get(0).get(8));
				textField_tele.setText(daten.get(0).get(9));
				textField_mail.setText(daten.get(0).get(10));
				textField_mobil.setText(daten.get(0).get(11));
				textField_haus.setText(daten.get(0).get(12));
				textfield_wohn.setText(daten.get(0).get(13));
				textField_gnn.setText(daten.get(0).get(14));
				textField_gvn.setText(daten.get(0).get(15));
				textfield_schule.setText(daten.get(0).get(16));
				textfield_schulform.setText(daten.get(0).get(17));
				textfield_partners.setText(daten.get(0).get(18));
				textArea_anmerkschule.setText(daten.get(0).get(19));
				textfield_miki.setText(daten.get(0).get(20));
				textfield_grad.setText(daten.get(0).get(21));
				textArea_anmerkperson.setText(daten.get(0).get(22));
				try {
					datePicker_startdatum.setDate(sdfToDate.parse(daten.get(0).get(23)));
				} catch (ParseException e) {
					datePicker_startdatum.setDate(null);
				}
				try {
					datePicker_enddatum.setDate(sdfToDate.parse(daten.get(0).get(24)));
				} catch (ParseException e) {
					datePicker_enddatum.setDate(null);
				}
				comboBox_status.setSelectedItem(daten.get(0).get(25));
				textArea_anmerkprakt.setText(daten.get(0).get(26));
				setInhaltAnspr1(1 , getAnsprDaten(daten.get(0).get(27)));
				setInhaltAnspr2(1 , getAnsprDaten(daten.get(0).get(28)));
				setInhaltAnspr3(1 , getAnsprDaten(daten.get(0).get(29)));
				textArea_konsole.setText(daten.get(0).get(30));
				if (daten.get(0).get(32).equals("1")) {
					chckbxDatenVollst.setSelected(true);
				}else {
					chckbxDatenVollst.setSelected(false);
				}
				try {
					datePicker_Antwortfrist.setDate(sdfToDate.parse(daten.get(0).get(33)));
				} catch (ParseException e) {
					datePicker_Antwortfrist.setDate(null);
				}
			

		}
		@Override
		/**
		 * AktionListener abfangen
		 */
		public void actionPerformed(ActionEvent evt) {
			Object src = evt.getSource();
			 CardLayout cardLayoutAnspr = (CardLayout) panel_ansprechPartner.getLayout();
			   if (src == button_woche1) {
					cardLayoutAnspr.show(panel_ansprechPartner,"card_woche1");
					button_woche1.setBackground(coolBlue);
					button_woche2.setBackground(null);
					button_woche3.setBackground(null);
					textfield_NameAnsprWoch1.requestFocus();
				} else if (src == button_woche2) {
					cardLayoutAnspr.show(panel_ansprechPartner,"card_woche2");
					button_woche1.setBackground(null);
					button_woche2.setBackground(coolBlue);
					button_woche3.setBackground(null);
					textfield_NameAnsprWoch2.requestFocus();
				} else if (src == button_woche3) {
					cardLayoutAnspr.show(panel_ansprechPartner,"card_woche3");
					button_woche1.setBackground(null);
					button_woche2.setBackground(null);
					button_woche3.setBackground(coolBlue);
					textfield_NameAnsprWoch3.requestFocus();
				} else if (src == button_editAnspr1){
					textfield_NameAnsprWoch1.setEditable(true);
					textField_VornameAnsprWoch1.setEditable(true);
					textField_TelAnsprWoch1.setEditable(true);
					textField_MailAnsprWoch1.setEditable(true);
					textField_AbteilAnsprWoch1.setEditable(true);
					textField_RaumAnsprWoch1.setEditable(true);
					textArea_EinsatzortAnsprWoche1.setEditable(true);
					ansprbearb1gedrueckt = true;
					button_editAnspr1.setEnabled(false);
				} else if (src == button_editAnspr2){
					textfield_NameAnsprWoch2.setEditable(true);
					textField_VornameAnsprWoch2.setEditable(true);
					textField_TelAnsprWoch2.setEditable(true);
					textField_MailAnsprWoch2.setEditable(true);
					textField_AbteilAnsprWoch2.setEditable(true);
					textField_RaumAnsprWoch2.setEditable(true);
					textArea_EinsatzortAnsprWoche2.setEditable(true);
					ansprbearb2gedrueckt = true;
					button_editAnspr2.setEnabled(false);
				} else if (src == button_editAnspr3){
					textfield_NameAnsprWoch3.setEditable(true);
					textField_VornameAnsprWoch3.setEditable(true);
					textField_TelAnsprWoch3.setEditable(true);
					textField_MailAnsprWoch3.setEditable(true);
					textField_AbteilAnsprWoch3.setEditable(true);
					textField_RaumAnsprWoch3.setEditable(true);
					textArea_EinsatzortAnsprWoche3.setEditable(true);
					ansprbearb3gedrueckt = true;
					button_editAnspr3.setEnabled(false);
				} 
		}
		/* (non-Javadoc)
		 * @see praktikantenverwaltung.AnsichtPraktikant#getDocx(java.io.File)
		 */
		@Override
		public void getDocx(File f){
			File[] files = f.listFiles();
			if (files != null) {
				for (int i = 0; i < files.length; i++) {
					if(files[i].getName().endsWith( ".docx" ))
					{
						comboBoxNachrichtenItems.add(files[i].getName().substring(0, (files[i].getName().length()-5)));
					}
					
				}
			}
		}
		public void setComboBoxItems_Nachricht(Vector<String> v){
			comboBox_NachrichtWahl.removeAllItems();
			for (int i = 0; i < v.size(); i++) {
				comboBox_NachrichtWahl.addItem(v.get(i));
			}
			AutoCompleteDecorator.decorate(this.comboBox_NachrichtWahl);
		}
		/* (non-Javadoc)
		 * @see praktikantenverwaltung.AnsichtPraktikant#statusupdate(java.lang.String, java.lang.String)
		 */
		@Override
		public void statusupdate(String nachricht , String id){
			String sql = "";
			SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss");
			Timestamp time = new Timestamp(System.currentTimeMillis());
			String zeit = sdf.format(time);
			String info;
			if (getInfoPrakt().equals("")) {
				info = "Nachricht " + nachricht + " erstellt am " + zeit;
			} else {
				info = getInfoPrakt() + "\n" + "Nachricht " + nachricht + " erstellt am " + zeit;
			}
			if (id.startsWith("SP")) {
				info = _model.getData("Select INFO From PRAKTIKANTEN WHERE ID = '" + id + "';").get(0).get(0);
				info = info + "\n" + "Nachricht " + nachricht + " erstellt am " + zeit;
				if (nachricht.equals("Absage_freiwilliges_Praktikum") || nachricht.equals("Absage_Vorlage")) {
					sql = "UPDATE PRAKTIKANTEN set STATUS = 'Absage', INFO = '" + info + "' WHERE ID = '" + id + "';";
					setInfoPrakt(info);
				}else if (nachricht.equals("Eingangsbescheid")) {
					sql = "UPDATE PRAKTIKANTEN set STATUS = 'Eingangsbest�tigung', INFO = '" + info + "' WHERE ID = '" + id + "';";
					setInfoPrakt(info);
				}else if (nachricht.equals("Praktikumsbest�tigung")) {
					sql = "UPDATE PRAKTIKANTEN set STATUS = 'abgeschlossen', INFO = '" + info + "' WHERE ID = '" + id + "';";
					setInfoPrakt(info);
				}else if (nachricht.equals("R�cksendung_Unterlagen")) {
					sql = "UPDATE PRAKTIKANTEN set STATUS = 'Zusage', INFO = '" + info + "' WHERE ID = '" + id + "';";
					setInfoPrakt(info);
				}else if (nachricht.equals("Selbstabsage_Vorlage")) {
					sql = "UPDATE PRAKTIKANTEN set STATUS = 'Selbstabsage', INFO = '" + info + "' WHERE ID = '" + id + "';";
					setInfoPrakt(info);
				}else if (nachricht.equals("Unterlagen_vervollst�ndigen")) {
					sql = "UPDATE PRAKTIKANTEN set STATUS = 'Bewerbung unvollst�ndig', INFO = '" + info + "' , UNTERLAGENVOLLST = '0' WHERE ID = '" + id + "';";
					setInfoPrakt(info);
				}else if (nachricht.equals("Zusage") || nachricht.equals("Zusageanschreiben")) {
					sql = "UPDATE PRAKTIKANTEN set STATUS = 'Zusage', INFO = '" + info + "' WHERE ID = '" + id + "';";
					setInfoPrakt(info);
				}else {
					sql = "";
				}
			}
			_model.insertUpdateDeleteTable(sql);
			
			if (nachricht.equals("Absage_freiwilliges_Praktikum") || nachricht.equals("Absage_Vorlage")) {
				comboBox_status.setSelectedItem("Absage");
				setInfoPrakt(info);
			}else if (nachricht.equals("Eingangsbescheid")) {
				comboBox_status.setSelectedItem("Eingangsbest�tigung");
				setInfoPrakt(info);
			}else if (nachricht.equals("Praktikumsbest�tigung")) {
				comboBox_status.setSelectedItem("abgeschlossen");
				setInfoPrakt(info);
			}else if (nachricht.equals("R�cksendung_Unterlagen")) {
				comboBox_status.setSelectedItem("Zusage");
				setInfoPrakt(info);
			}else if (nachricht.equals("Selbstabsage_Vorlage")) {
				comboBox_status.setSelectedItem("Selbstabsage");
				setInfoPrakt(info);
			}else if (nachricht.equals("Unterlagen_vervollst�ndigen")) {
				comboBox_status.setSelectedItem("Bewerbung unvollst�ndig");
				setInfoPrakt(info);
			}else if (nachricht.equals("Zusage") || nachricht.equals("Zusageanschreiben")) {
				comboBox_status.setSelectedItem("Zusage");
				setInfoPrakt(info);
			}else {
				sql = "";
				comboBox_status.setSelectedItem("leer");
			}
			
		}
}
