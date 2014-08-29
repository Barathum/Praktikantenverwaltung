package praktikantenverwaltung;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.GroupLayout;
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
import javax.swing.text.DefaultFormatterFactory;

import org.jdesktop.swingx.JXDatePicker;
import org.jdesktop.swingx.calendar.DatePickerFormatter;

import javax.swing.JCheckBox;

public class PraktikantenVerwaltung_ViewAnspr extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * erstellen der Fields
	 */
	private PraktikantenVerwaltung_Modell _model; 
	private PraktikantenVerwaltung_Control _control; 
	private JPanel mainPanel;
	private JTextField textField_RaumAnsprBearb;
	private JTextField textField_AbteilAnsprBearb;
	private JTextField textField_MailAnsprBearb;
	private JTextField textField_TelAnsprBearb;
	private JTextField textField_VornameAnsprBearb;
	private JTextField textField_NameAnsprBearb;
	private JTextArea textArea_InfoAnspr;
	private JTextArea textArea_AnmerkOrtBearb;
	private JLabel lblBearbeiteAnsprechpartner;
	private JButton button_SpeichernAnspr;
	private JXDatePicker datePicker_blockierenVon;
	private JXDatePicker datePicker_blockierenBis;
	private int updateorinsert;
	private Integer HoechsteAnsprID = 100000;
	private String neueAnsprID = "0";
	private JCheckBox chckbxEtechnik;
	private JCheckBox chckbxKaufm;
	private JCheckBox chckbxInf;

	/**
	 * Create the frame.
	 */
	public PraktikantenVerwaltung_ViewAnspr(PraktikantenVerwaltung_Control control , PraktikantenVerwaltung_Modell model) {
		this._model = model;
		this._control = control;
		updateorinsert = 2;
		viewKontrukt();
		
	}
	public PraktikantenVerwaltung_ViewAnspr(PraktikantenVerwaltung_Control control , PraktikantenVerwaltung_Modell model , ArrayList<ArrayList<String>> Ansprechpartnereintrag) throws IndexOutOfBoundsException{
		this._model = model;
		this._control = control;
		updateorinsert = 1;
		viewKontrukt();
		setInhaltAnsprBearb(Ansprechpartnereintrag);
	}
	private void viewKontrukt(){
		setResizable(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(20, 20, 958, 556);
		
		
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
					private static final long serialVersionUID = 1L;

					@Override
		            public String valueToString(Object value) throws ParseException {
		                if (value == null) return null;
		                return getFormats()[1].format(value);
		            }
		        } ;
		DefaultFormatterFactory factory = new DefaultFormatterFactory(formatter );
		datePicker_blockierenVon = new JXDatePicker();
		datePicker_blockierenVon.setDate(null);
		datePicker_blockierenVon.setFormats(new SimpleDateFormat("dd.MM.yyyy"));
		datePicker_blockierenVon.getEditor().setFormatterFactory(factory);

		datePicker_blockierenBis = new JXDatePicker();
		datePicker_blockierenBis.setDate(null);
		datePicker_blockierenBis.setFormats(new SimpleDateFormat("dd.MM.yyyy"));
		datePicker_blockierenBis.getEditor().setFormatterFactory(factory);
		
		JLabel lblFachrichtung = new JLabel("Fachrichtung:");
		
		chckbxEtechnik = new JCheckBox("E-Technik");
		
		chckbxKaufm = new JCheckBox("Kaufm\u00E4nnisch");
		
		chckbxInf = new JCheckBox("Informatik");

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
		textArea_InfoAnspr.setBackground(UIManager.getColor("Button.background"));
		textArea_InfoAnspr.setLineWrap(true);
		textArea_InfoAnspr.setEnabled(false);
		textArea_InfoAnspr.setEditable(false);
		textArea_InfoAnspr.setDisabledTextColor(Color.blue);
		scrollPane_6.setViewportView(textArea_InfoAnspr);
		
		textArea_AnmerkOrtBearb = new JTextArea();
		scrollPane_3.setViewportView(textArea_AnmerkOrtBearb);
		panel_ansprbearb.setLayout(gl_panel_ansprbearb);
		
		this.setAnsprSpeichernListener(new AnsprSpeichernListener());
		textField_NameAnsprBearb.addActionListener(enterAction);
		textField_VornameAnsprBearb.addActionListener(enterAction);
		textField_TelAnsprBearb.addActionListener(enterAction);
		textField_MailAnsprBearb.addActionListener(enterAction);
		textField_AbteilAnsprBearb.addActionListener(enterAction);
		textField_RaumAnsprBearb.addActionListener(enterAction);
		datePicker_blockierenVon.addActionListener(enterAction);
		datePicker_blockierenBis.addActionListener(enterAction);
		
	}
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
	public void setInfoAnspr(String inf){
		this.textArea_InfoAnspr.setText(inf);
	}
	/**
	 * Setzt einen Listener auf den Speicher Button in der Ansprechpartner erstellen Ansicht
	 * @param l Listener der übergeben wird
	 */
	public void setAnsprSpeichernListener(ActionListener l){
		this.button_SpeichernAnspr.addActionListener(l);
	}
	   class AnsprSpeichernListener implements ActionListener{ 
		public void actionPerformed(ActionEvent e) { 
			   ArrayList<String> datensatzAnspr = getInhaltAnspr();
               String sql;
               sql = schreibeEintragAnsprsql(updateorinsert, datensatzAnspr);
               _model.insertUpdateDeleteTable(sql);
           }  
	   }
	   private String schreibeEintragAnsprsql(int i, ArrayList<String> liste){
			String sql;
			SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss");
			Timestamp time = new Timestamp(System.currentTimeMillis());
			String zeit = sdf.format(time);
			if (i == 1) {
				String info;
				info = liste.get(8);
				info = info + "\n" + "Daten geupdatet am " + zeit;
				setInfoAnspr(info);
				liste.set(8, info);
				sql = "UPDATE ANSPRECHPARTNER set NN = '" + liste.get(1) + "', VN = '" + liste.get(2) + "', TELE = '" + liste.get(3) +
						"', MAIL = '" + liste.get(4) + "', ABTEILUNG = '" + liste.get(5) + "', RNR = '" + liste.get(6) + "', ANMERKEINSATZORT = '" + liste.get(7) + "', INFO = '" + liste.get(8) + 
						 "', BLOCKIERENVON = '" + liste.get(9) + "', BLOCKIERENBIS = '" + liste.get(10) + "', ETECH = '" + liste.get(11) + "', KAUFM = '" + liste.get(12) + "', INF = '" + liste.get(13) +"' WHERE ID = '" + liste.get(0) + "';";
			
			} else if (i == 2) {
				HoechsteAnsprID = _control.getHoechsteAnsprID();
				HoechsteAnsprID++;
				neueAnsprID = HoechsteAnsprID.toString();
				updateorinsert = 1;
				String info = "Daten gespeichert am " + zeit;
				setInfoAnspr(info);
				sql = "INSERT INTO ANSPRECHPARTNER " +
						"VALUES ('" + neueAnsprID +"','"+ liste.get(1) +"','"+ liste.get(2) +"','"+ liste.get(3) +"','"+ liste.get(4) +"','"+ liste.get(5) 
		                   +"','"+ liste.get(6) +"','"+ liste.get(7) +"','" + info +"','" + liste.get(9) +"','" + liste.get(10) +"','" + liste.get(11) +"','" + liste.get(12) + "','" + liste.get(13) +"');";
			} else {
				sql = "";
			}
			return sql;
		}
	   private ArrayList<String> getInhaltAnspr(){
		   SimpleDateFormat sdfToDate = new SimpleDateFormat("dd.MM.yyyy");
			ArrayList<String> liste = new ArrayList<String>();
			liste.add((String) neueAnsprID.toString());
			if (textField_NameAnsprBearb.getText().trim().length() > 0){liste.add((String) textField_NameAnsprBearb.getText().trim());}else{liste.add("");}
			if (textField_VornameAnsprBearb.getText().trim().length() > 0){liste.add((String) textField_VornameAnsprBearb.getText().trim());}else{liste.add("");}
			if (textField_TelAnsprBearb.getText().trim().length() > 0){liste.add((String) textField_TelAnsprBearb.getText().trim());}else{liste.add("");}
			if (textField_MailAnsprBearb.getText().trim().length() > 0){liste.add((String) textField_MailAnsprBearb.getText().trim());}else{liste.add("");}
			if (textField_AbteilAnsprBearb.getText().trim().length() > 0){liste.add((String) textField_AbteilAnsprBearb.getText().trim());}else{liste.add("");}
			if (textField_RaumAnsprBearb.getText().trim().length() > 0){liste.add((String) textField_RaumAnsprBearb.getText().trim());}else{liste.add("");}
			if (textArea_AnmerkOrtBearb.getText().trim().length() > 0){liste.add((String) textArea_AnmerkOrtBearb.getText().trim());}else{liste.add("");}
			liste.add((String) textArea_InfoAnspr.getText());
			try{
				if (sdfToDate.format(datePicker_blockierenVon.getDate()).trim().length() > 0){liste.add((String) sdfToDate.format(datePicker_blockierenVon.getDate()).trim());}else{liste.add("");}
			} catch (Exception e) {
				liste.add("");
			}
			try{
				if (sdfToDate.format(datePicker_blockierenBis.getDate()).trim().length() > 0){liste.add((String) sdfToDate.format(datePicker_blockierenBis.getDate()).trim());}else{liste.add("");}
			} catch (Exception e) {
				liste.add("");
			}
			if (chckbxEtechnik.isSelected()) {
				liste.add("1");
			}else {
				liste.add("0");
			}
			if (chckbxKaufm.isSelected()) {
				liste.add("1");
			}else {
				liste.add("0");
			}
			if (chckbxInf.isSelected()) {
				liste.add("1");
			}else {
				liste.add("0");
			}
		return liste;
		}
	   private void setInhaltAnsprBearb(ArrayList<ArrayList<String>> daten) throws IndexOutOfBoundsException{
		   SimpleDateFormat sdfToDate = new SimpleDateFormat("dd.MM.yyyy");
			neueAnsprID = daten.get(0).get(0);
			textField_NameAnsprBearb.setText(daten.get(0).get(1));
			textField_VornameAnsprBearb.setText(daten.get(0).get(2));
			textField_TelAnsprBearb.setText(daten.get(0).get(3));
			textField_MailAnsprBearb.setText(daten.get(0).get(4));
			textField_AbteilAnsprBearb.setText(daten.get(0).get(5));
			textField_RaumAnsprBearb.setText(daten.get(0).get(6));
			textArea_AnmerkOrtBearb.setText(daten.get(0).get(7));
			textArea_InfoAnspr.setText(daten.get(0).get(8));
			try {
				datePicker_blockierenVon.setDate(sdfToDate.parse(daten.get(0).get(9)));
			} catch (ParseException e) {
				datePicker_blockierenVon.setDate(null);
			}
			try {
				datePicker_blockierenBis.setDate(sdfToDate.parse(daten.get(0).get(10)));
			} catch (ParseException e) {
				datePicker_blockierenBis.setDate(null);
			}
			if (daten.get(0).get(11).equals("1")) {
				chckbxEtechnik.setSelected(true);
			}else {
				chckbxEtechnik.setSelected(false);
			}
			if (daten.get(0).get(12).equals("1")) {
				chckbxKaufm.setSelected(true);
			}else {
				chckbxKaufm.setSelected(false);
			}
			if (daten.get(0).get(13).equals("1")) {
				chckbxInf.setSelected(true);
			}else {
				chckbxInf.setSelected(false);
			}
		}
}
