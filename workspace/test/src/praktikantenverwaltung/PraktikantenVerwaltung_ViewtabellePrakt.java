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

public class PraktikantenVerwaltung_ViewtabellePrakt extends JFrame {

	/**
	 * erstellen der Fields
	 */
	private JPanel contentPane;
	private JPanel mainPanel;
	private JTable table_suche;
	private JScrollPane scrollPane_Suchliste;
	private String[] spaltennamenprak = {"ID",
            "Nachname",
            "Vorname",
            "Status",
            "Startdatum",
            "Enddatum", 
            "Anmerkung",
            "Letzte Änderung"};
	private Object[][] datenprak;
	private JButton btn_praktbearbeiten;
	private JButton btn_praktloeschen;
	private JButton btn_praktNachrichtSchreiben;
	
	public PraktikantenVerwaltung_ViewtabellePrakt(){
		setResizable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(5, 5, 1280, 720);
		
		
		mainPanel = new JPanel();
		getContentPane().add(mainPanel, BorderLayout.CENTER);
		mainPanel.setLayout(new CardLayout(0, 0));
		
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
		
		btn_praktNachrichtSchreiben = new JButton("Nachricht erstellen");
		GroupLayout gl_panel_10 = new GroupLayout(panel_10);
		gl_panel_10.setHorizontalGroup(
			gl_panel_10.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_10.createSequentialGroup()
					.addGap(20)
					.addComponent(btn_praktbearbeiten)
					.addGap(18)
					.addComponent(btn_praktloeschen, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
					.addGap(47)
					.addComponent(btn_praktNachrichtSchreiben)
					.addContainerGap(888, Short.MAX_VALUE))
		);
		gl_panel_10.setVerticalGroup(
			gl_panel_10.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_10.createSequentialGroup()
					.addGap(9)
					.addGroup(gl_panel_10.createParallelGroup(Alignment.BASELINE)
						.addComponent(btn_praktbearbeiten)
						.addComponent(btn_praktloeschen)
						.addComponent(btn_praktNachrichtSchreiben))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel_10.setLayout(gl_panel_10);
	}

}
