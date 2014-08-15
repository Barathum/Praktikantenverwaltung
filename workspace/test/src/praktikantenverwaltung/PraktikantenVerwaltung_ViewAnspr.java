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

public class PraktikantenVerwaltung_ViewAnspr extends JFrame {
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
	private JTextField textField_RaumAnsprBearb;
	private JTextField textField_AbteilAnsprBearb;
	private JTextField textField_MailAnsprBearb;
	private JTextField textField_TelAnsprBearb;
	private JTextField textField_VornameAnsprBearb;
	private JTextField textField_NameAnsprBearb;
	private JButton button_SpeichernAnspr;
	private JTextArea textArea_InfoAnspr;
	private Integer idAnspr1 = new Integer(0);
	private Integer idAnspr2 = new Integer(0);
	private Integer idAnspr3 = new Integer(0);
	private Integer idAnsprBearb = new Integer(0);
	private String letzteAenderung;
	private boolean Ansprbearb1gedrueckt = false;
	private boolean Ansprbearb2gedrueckt = false;
	private boolean Ansprbearb3gedrueckt = false;
	private Vector comboBoxItems_wohn;
	private JTextArea textArea_AnmerkOrtBearb;
	private JLabel lblBearbeiteAnsprechpartner;

	/**
	 * Create the frame.
	 */
	public PraktikantenVerwaltung_ViewAnspr() {
		setResizable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(5, 5, 1280, 720);
		
		
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
		
	}

}
