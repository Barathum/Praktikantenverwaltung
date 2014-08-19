package praktikantenverwaltung;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
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

import javax.swing.JCheckBox;

public class PraktikantenVerwaltung_ViewAnspr extends JFrame {
	/**
	 * erstellen der Fields
	 */
	private PraktikantenVerwaltung_Modell _model; 
	private PraktikantenVerwaltung_Control _control; 
	private JPanel contentPane;
	private JPanel mainPanel;
	private JTextField textField_RaumAnsprBearb;
	private JTextField textField_AbteilAnsprBearb;
	private JTextField textField_MailAnsprBearb;
	private JTextField textField_TelAnsprBearb;
	private JTextField textField_VornameAnsprBearb;
	private JTextField textField_NameAnsprBearb;
	private JTextArea textArea_InfoAnspr;
	private Integer idAnsprBearb = new Integer(0);
	private String letzteAenderung;
	private JTextArea textArea_AnmerkOrtBearb;
	private JLabel lblBearbeiteAnsprechpartner;
	private JButton button_SpeichernAnspr;
	private JXDatePicker datePicker_blockierenVon;
	private JXDatePicker datePicker_blockierenBis;

	/**
	 * Create the frame.
	 */
	public PraktikantenVerwaltung_ViewAnspr() {
		this._model = new PraktikantenVerwaltung_Modell();
		this._control = new PraktikantenVerwaltung_Control();
		viewKontrukt();
		
	}
	public PraktikantenVerwaltung_ViewAnspr(ArrayList<ArrayList<String>> Ansprechpartnereintrag) {
		this._model = new PraktikantenVerwaltung_Modell();
		this._control = new PraktikantenVerwaltung_Control();
		viewKontrukt();
		
	}
	private void viewKontrukt(){
		setResizable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(5, 5, 958, 556);
		
		
		mainPanel = new JPanel();
		getContentPane().add(mainPanel, BorderLayout.CENTER);
		mainPanel.setLayout(new CardLayout(0, 0));
		
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
		
		JLabel lblAnsprechpartnerBlockierenVon = new JLabel("Ansprechpartner blockieren von:");
		
		JLabel lblAnsprechpartnerBlockierenBis = new JLabel("Ansprechpartner blockieren bis:");
		
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
		datePicker_blockierenVon = new JXDatePicker();
		datePicker_blockierenVon.setDate(Calendar.getInstance().getTime());
		datePicker_blockierenVon.setFormats(new SimpleDateFormat("dd.MM.yyyy"));
		datePicker_blockierenVon.getEditor().setFormatterFactory(factory);

		datePicker_blockierenBis = new JXDatePicker();
		datePicker_blockierenBis.setDate(Calendar.getInstance().getTime());
		datePicker_blockierenBis.setFormats(new SimpleDateFormat("dd.MM.yyyy"));
		datePicker_blockierenBis.getEditor().setFormatterFactory(factory);
		
		JLabel lblFachrichtung = new JLabel("Fachrichtung:");
		
		JCheckBox chckbxEtechnik = new JCheckBox("E-Technik");
		
		JCheckBox chckbxKaufm = new JCheckBox("Kaufm\u00E4nnisch");
		
		JCheckBox chckbxInf = new JCheckBox("Informatik");

		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING, false)
								.addComponent(label_4)
								.addComponent(label_5, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(label_6, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(label_7, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(label_8, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
								.addComponent(label_9, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE))
							.addGap(39)
							.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING, false)
								.addComponent(textField_RaumAnsprBearb)
								.addComponent(textField_AbteilAnsprBearb)
								.addComponent(textField_MailAnsprBearb)
								.addComponent(textField_TelAnsprBearb)
								.addComponent(textField_VornameAnsprBearb)
								.addComponent(textField_NameAnsprBearb, GroupLayout.PREFERRED_SIZE, 293, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_panel_2.createSequentialGroup()
							.addComponent(lblFachrichtung, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
							.addGap(111)
							.addComponent(chckbxEtechnik)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(chckbxKaufm)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(chckbxInf))
						.addGroup(gl_panel_2.createSequentialGroup()
							.addGroup(gl_panel_2.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(lblAnsprechpartnerBlockierenBis, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblAnsprechpartnerBlockierenVon, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE))
							.addGap(35)
							.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
								.addComponent(datePicker_blockierenBis, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)
								.addComponent(datePicker_blockierenVon, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(51, Short.MAX_VALUE))
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
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAnsprechpartnerBlockierenVon)
						.addComponent(datePicker_blockierenVon, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAnsprechpartnerBlockierenBis)
						.addComponent(datePicker_blockierenBis, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addGap(18)
							.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblFachrichtung)
								.addComponent(chckbxKaufm)
								.addComponent(chckbxInf)))
						.addGroup(gl_panel_2.createSequentialGroup()
							.addGap(17)
							.addComponent(chckbxEtechnik)))
					.addContainerGap(22, Short.MAX_VALUE))
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
					.addGap(45)
					.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 538, GroupLayout.PREFERRED_SIZE)
					.addGap(32)
					.addGroup(gl_panel_ansprbearb.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane_3, GroupLayout.PREFERRED_SIZE, 296, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_10, GroupLayout.PREFERRED_SIZE, 211, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(31, Short.MAX_VALUE))
				.addGroup(Alignment.LEADING, gl_panel_ansprbearb.createSequentialGroup()
					.addGap(261)
					.addComponent(button_SpeichernAnspr, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE)
					.addGap(33)
					.addComponent(scrollPane_6, GroupLayout.PREFERRED_SIZE, 308, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(194, Short.MAX_VALUE))
				.addGroup(Alignment.LEADING, gl_panel_ansprbearb.createSequentialGroup()
					.addGap(441)
					.addComponent(lblInfo, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(457, Short.MAX_VALUE))
				.addGroup(gl_panel_ansprbearb.createSequentialGroup()
					.addContainerGap(395, Short.MAX_VALUE)
					.addComponent(lblBearbeiteAnsprechpartner, GroupLayout.PREFERRED_SIZE, 216, GroupLayout.PREFERRED_SIZE)
					.addGap(331))
		);
		gl_panel_ansprbearb.setVerticalGroup(
			gl_panel_ansprbearb.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_ansprbearb.createSequentialGroup()
					.addGap(35)
					.addComponent(lblBearbeiteAnsprechpartner)
					.addGap(47)
					.addGroup(gl_panel_ansprbearb.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_ansprbearb.createSequentialGroup()
							.addComponent(label_10, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(scrollPane_3, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE))
						.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 282, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_panel_ansprbearb.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_ansprbearb.createSequentialGroup()
							.addGap(21)
							.addComponent(lblInfo)
							.addGap(8)
							.addComponent(scrollPane_6, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(Alignment.TRAILING, gl_panel_ansprbearb.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(button_SpeichernAnspr, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
							.addGap(45))))
		);
		
		textArea_InfoAnspr = new JTextArea();
		textArea_InfoAnspr.setFont(new Font("Monospaced", Font.PLAIN, 13));
		textArea_InfoAnspr.setForeground(Color.BLACK);
		textArea_InfoAnspr.setEditable(false);
		scrollPane_6.setViewportView(textArea_InfoAnspr);
		
		textArea_AnmerkOrtBearb = new JTextArea();
		scrollPane_3.setViewportView(textArea_AnmerkOrtBearb);
		panel_ansprbearb.setLayout(gl_panel_ansprbearb);
	}
	public void setInfoAnspr(String inf){
		this.textArea_InfoAnspr.setText(inf);
	}
}
