package praktikantenverwaltung;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.BoxLayout;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;

import java.awt.Color;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.SwingConstants;

import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
import org.joda.time.IllegalFieldValueException;
import org.joda.time.Interval;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class PraktikantenVerwaltung_AuslastDiagramme extends JFrame implements ActionListener{

	/**
	 * erstellen der Fields
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panel_AnsprechAuslast;
	private JTextField textField_filterAnspr;
	private JPanel panel_MonatAuslast;
	private JTextField textField_filterMonat;
	private JScrollPane scrollPane_AnsprechAuslast;
	private JScrollPane scrollPane_MonatAuslast;
	private JPanel panel_tableAnsprAuslast;
	private JPanel panel_tableMonatAuslast;
	private JScrollPane panel;
	private JScrollPane panelm;
	private JTable table = new JTable();
	private JTable tablem = new JTable();
//	private PraktikantenVerwaltung_Modell _model;
	private PraktikantenVerwaltung_Control _control;
	private JPanel mainPanel;
	private JPanel panel_auslast;
	private JButton btnAnsprechpartneransicht;
	private JButton btnMonatsansicht;
	private JPanel card_Ansprechauslast;
	private JPanel card_Monatauslast;
	private String[] spaltenTage = {
			"AnsprName",
            "1",
            "2",
            "3",
            "4",
            "5", 
            "6",
            "7",
            "8",
            "9",
            "10",
            "11",
            "12",
            "13",
            "14",
            "15",
            "16",
            "17",
            "18",
            "19",
            "20",
            "21",
            "22",
            "23",
            "24",
            "25",
            "26",
            "27",
            "28",
            "29",
            "30",
            "31"};
	private Object[][] datenanspr;
	private Object[][] datenmonat;
	private JPanel panel_1;
	private JPanel panel_2;
	private JButton btnDrucken;
	private JButton btnDruckenm;
	private ArrayList<ArrayList<String>> alleDatenAnspr;
	private ArrayList<ArrayList<String>> alleDatenPrakt;
	private ArrayList<ArrayList<String>> alleDatenAnsprFilter;
	private ArrayList<ArrayList<String>> alleDatenPraktFilter;
	
	
	public PraktikantenVerwaltung_AuslastDiagramme(PraktikantenVerwaltung_Control control , PraktikantenVerwaltung_Modell model , ArrayList<ArrayList<String>> alleDatenAnspr , ArrayList<ArrayList<String>> alleDatenPrakt){
		this._control = control;
		this.alleDatenAnspr = alleDatenAnspr;
		this.alleDatenPrakt = alleDatenPrakt;
		setAlleDatenAnsprFilter(alleDatenAnspr);
		setAlleDatenPraktFilter(alleDatenPrakt);
//		this._model = model;
		
		fillDatenMonat();
		fillDatenAnspr();
		
		setResizable(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(20, 20, 1280, 720);
		this.setTitle("Auslastungsdiagramm");
		this.setIconImage(_control.getImg().getImage());
		
		mainPanel = new JPanel();
		getContentPane().add(mainPanel, BorderLayout.CENTER);
		mainPanel.setLayout(null);
		
		JPanel panel_toggle = new JPanel();
		panel_toggle.setBounds(0, 0, 393, 27);
		mainPanel.add(panel_toggle);
		
		btnAnsprechpartneransicht = new JButton("Ansprechpartneransicht");
		btnAnsprechpartneransicht.addActionListener(this);
		
		btnMonatsansicht = new JButton("Monatsansicht");
		btnMonatsansicht.addActionListener(this);
		
		GroupLayout gl_panel_toggle = new GroupLayout(panel_toggle);
		gl_panel_toggle.setHorizontalGroup(
			gl_panel_toggle.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_toggle.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnAnsprechpartneransicht)
					.addGap(29)
					.addComponent(btnMonatsansicht)
					.addContainerGap(118, Short.MAX_VALUE))
		);
		gl_panel_toggle.setVerticalGroup(
			gl_panel_toggle.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_toggle.createSequentialGroup()
					.addGroup(gl_panel_toggle.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAnsprechpartneransicht)
						.addComponent(btnMonatsansicht))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel_toggle.setLayout(gl_panel_toggle);
		
		panel_auslast = new JPanel();
		panel_auslast.setBounds(0, 27, 1264, 655);
		mainPanel.add(panel_auslast);
		panel_auslast.setLayout(new CardLayout(0, 0));
		
		card_Ansprechauslast = new JPanel();
		panel_auslast.add(card_Ansprechauslast, "card_anspr");
		card_Ansprechauslast.setLayout(null);
		
		panel_AnsprechAuslast = new JPanel();
		panel_AnsprechAuslast.setBounds(0, 0, 1264, 655);
		card_Ansprechauslast.add(panel_AnsprechAuslast);
		
		textField_filterAnspr = new JTextField();
		textField_filterAnspr.setColumns(10);
		
		scrollPane_AnsprechAuslast = new JScrollPane();
		scrollPane_AnsprechAuslast.setHorizontalScrollBarPolicy(
			JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane_AnsprechAuslast.setVerticalScrollBarPolicy(
			JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); 
		
		GroupLayout gl_panel_AnsprechAuslast = new GroupLayout(panel_AnsprechAuslast);
		gl_panel_AnsprechAuslast.setHorizontalGroup(
			gl_panel_AnsprechAuslast.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_AnsprechAuslast.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_AnsprechAuslast.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane_AnsprechAuslast, GroupLayout.DEFAULT_SIZE, 1244, Short.MAX_VALUE)
						.addComponent(textField_filterAnspr, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		gl_panel_AnsprechAuslast.setVerticalGroup(
			gl_panel_AnsprechAuslast.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_AnsprechAuslast.createSequentialGroup()
					.addContainerGap()
					.addComponent(textField_filterAnspr, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane_AnsprechAuslast, GroupLayout.DEFAULT_SIZE, 607, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		panel_tableAnsprAuslast = new JPanel();
		scrollPane_AnsprechAuslast.setViewportView(panel_tableAnsprAuslast);
		panel_tableAnsprAuslast.setLayout(new BoxLayout(panel_tableAnsprAuslast, BoxLayout.Y_AXIS));
		
		panel_AnsprechAuslast.setLayout(gl_panel_AnsprechAuslast);
		
		card_Monatauslast = new JPanel();
		panel_auslast.add(card_Monatauslast, "card_monat");
		card_Monatauslast.setLayout(null);
		
		panel_MonatAuslast = new JPanel();
		panel_MonatAuslast.setBounds(0, 0, 1264, 655);
		card_Monatauslast.add(panel_MonatAuslast);
		
		textField_filterMonat = new JTextField();
		textField_filterMonat.setColumns(10);
		
		scrollPane_MonatAuslast = new JScrollPane();
		scrollPane_MonatAuslast.setHorizontalScrollBarPolicy(
			JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane_MonatAuslast.setVerticalScrollBarPolicy(
			JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); 
				
		GroupLayout gl_panel_MonatAuslast = new GroupLayout(panel_MonatAuslast);
		gl_panel_MonatAuslast.setHorizontalGroup(
			gl_panel_MonatAuslast.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_MonatAuslast.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_MonatAuslast.createParallelGroup(Alignment.LEADING)
						.addComponent(textField_filterMonat, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
						.addComponent(scrollPane_MonatAuslast, GroupLayout.DEFAULT_SIZE, 1243, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_panel_MonatAuslast.setVerticalGroup(
			gl_panel_MonatAuslast.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_MonatAuslast.createSequentialGroup()
					.addContainerGap()
					.addComponent(textField_filterMonat, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane_MonatAuslast, GroupLayout.DEFAULT_SIZE, 605, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		panel_tableMonatAuslast = new JPanel();
		scrollPane_MonatAuslast.setViewportView(panel_tableMonatAuslast);
		panel_tableMonatAuslast.setLayout(new BoxLayout(panel_tableMonatAuslast, BoxLayout.Y_AXIS));
		
		panel_MonatAuslast.setLayout(gl_panel_MonatAuslast);
		
		createTableAnspr();
		createTableMonat();
		
	}
	
	
	
	//Sorgt dafür das beim Drücken der Enter-Taste weiter geschalten wird(neues Feld)
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
	
	public void setDatenMonat(Object[][] daten){
		this.datenmonat = daten;
	}
	
	public void setDatenAnspr(Object[][] daten){
		this.datenanspr = daten;
	}
	
	public void actionPerformed(ActionEvent evt) {
		Object src = evt.getSource();
		CardLayout cardLayout = (CardLayout) panel_auslast.getLayout();
		if (src == btnAnsprechpartneransicht) {
			cardLayout.show(panel_auslast,"card_anspr");
		}else if (src == btnMonatsansicht) {
			cardLayout.show(panel_auslast,"card_monat");
		}
	}
	public void setNameAnsprTable(String s){
		spaltenTage[0] = s;
	}
	
	public void setNameMonatTable(String s){
		spaltenTage[0] = s;
	}
	
	public void setAlleDatenAnsprFilter(ArrayList<ArrayList<String>> alleDatenAnspr){
		this.alleDatenAnsprFilter = new ArrayList<ArrayList<String>>(alleDatenAnspr);
	}
	
	public void setAlleDatenPraktFilter(ArrayList<ArrayList<String>> alleDatenPrakt){
		this.alleDatenPraktFilter = new ArrayList<ArrayList<String>>(alleDatenPrakt);
	}
	
	public void fillDatenMonat(){
		ArrayList<ArrayList<String>> datenm = new ArrayList<ArrayList<String>>();
		for (int i = 0; i < alleDatenAnsprFilter.size(); i++) {
			ArrayList<String> daten1D = new ArrayList<String>();
			daten1D.add(alleDatenAnsprFilter.get(i).get(1));
			for (int j = 0; j < 31; j++) {
				daten1D.add("");
			}
			datenm.add(daten1D);
		}
		setDatenMonat(_control.ArrayListtoArray(datenm));
	}
	public void fillDatenAnspr(){
		ArrayList<ArrayList<String>> daten = new ArrayList<ArrayList<String>>();
		for (int i = 0; i < 12; i++) {
			ArrayList<String> daten1D = new ArrayList<String>();
			switch (i) {
			case 0:
				daten1D.add("Januar");
				break;
			case 1:
				daten1D.add("Februar");
				break;
			case 2:
				daten1D.add("März");
				break;
			case 3:
				daten1D.add("April");
				break;
			case 4:
				daten1D.add("Mai");
				break;
			case 5:
				daten1D.add("Juni");
				break;
			case 6:
				daten1D.add("Juli");
				break;
			case 7:
				daten1D.add("August");
				break;
			case 8:
				daten1D.add("September");
				break;
			case 9:
				daten1D.add("Oktober");
				break;
			case 10:
				daten1D.add("November");
				break;
			case 11:
				daten1D.add("Dezember");
				break;
			default:
				daten1D.add("Monat");
				break;
			}
			for (int j = 0; j < 31; j++) {
				daten1D.add("");
			}
			daten.add(daten1D);
		}
		setDatenAnspr(_control.ArrayListtoArray(daten));
	}
	public void createTableAnspr(){
		for (int i = 0; i < alleDatenAnsprFilter.size(); i++) {
			setNameAnsprTable(alleDatenAnsprFilter.get(i).get(1));
			panel_1 = new JPanel();
			panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));
			panel_1.setPreferredSize(new Dimension(panel_AnsprechAuslast.getSize().width - 100, (int) (datenanspr.length * 16.5) + 60));
			
			Border blackBorder = BorderFactory.createLineBorder(Color.BLACK, 2);
		    Border emptyBorder = BorderFactory.createEmptyBorder(0, 0, 10, 0);
		    Border twoBorder = new CompoundBorder(emptyBorder, blackBorder);
			panel_1.setBorder(twoBorder);
			
			panel = new JScrollPane();
			
			table = new JTable(new DefaultTableModel_PraktikantenVerwaltung(spaltenTage, datenanspr));
			table.setColumnSelectionAllowed(false);
			table.setRowSelectionAllowed(false);
			table.getColumnModel().getColumn(0).setPreferredWidth(200);
			
			ColorTableCellRenderer ctr=new ColorTableCellRenderer();
			
			DateTimeFormatter dateStringFormat = DateTimeFormat.forPattern("dd.MM.yyyy");
			SimpleDateFormat sdfToDate = new SimpleDateFormat("dd.MM.yy");
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.YEAR, -50);
			sdfToDate.set2DigitYearStart(cal.getTime());
			
			DateTime today = new DateTime();
				
			Date blockiertVonDatabase = null;
			Date blockiertBisDatabase = null;
		   try {
			   blockiertVonDatabase = sdfToDate.parse(alleDatenAnsprFilter.get(i).get(9));
			   blockiertBisDatabase = sdfToDate.parse(alleDatenAnsprFilter.get(i).get(10));
		   } catch (ParseException e1) {
			   
		   }
		   DateTime blockiertVon = new DateTime(blockiertVonDatabase);
		   DateTime blockiertBis = new DateTime(blockiertBisDatabase);
		   Interval dauerblockiert = new Interval(blockiertVon, blockiertBis.plusHours(1));
		   
			for (int j = 0; j < table.getColumnCount(); j++) {
				table.getColumnModel().getColumn(j).setCellRenderer(ctr);
				for (int j2 = 0; j2 < table.getRowCount(); j2++) {
					//versucht die Eintrag Zeit zu schreiben
					DateTime eintragTime = new DateTime();
					try {
						eintragTime = new DateTime(today.getYear(), j2 + 1, j + 1, 0, 0);
					} catch (IllegalFieldValueException e) {
						// in dem angegebenen Monat j2 + 1 gibt es keinen tag nr j + 1 also setze die eintragszeit auf jahr 1 sodass der eintrag
						//sicher nicht im interval dauerblockiert oder praktikant vorhanden ist
						eintragTime = new DateTime(1, 1, 2, 0, 0);
						ctr.setColor(j2,j + 1,Color.GRAY);
					}
					//ist der eintrag im blockiert interval, wenn ja setze die zelle auf rot
					if (dauerblockiert.contains(eintragTime)) {
						ctr.setColor(j2,j + 1,Color.RED);
					}
				}
			}
			
			String AnsprId = alleDatenAnsprFilter.get(i).get(0);
			ArrayList<ArrayList<String>> alleDatenPraktWoche1 = new ArrayList<ArrayList<String>>(alleDatenPraktFilter);
			for (int j = 0; j < alleDatenPraktWoche1.size(); j++) {
				if (!(alleDatenPraktWoche1.get(j).get(27).matches(AnsprId))){
						alleDatenPraktWoche1.remove(j);
						j--;
					}
			}
			ArrayList<ArrayList<String>> alleDatenPraktWoche2 = new ArrayList<ArrayList<String>>(alleDatenPraktFilter);
			for (int j = 0; j < alleDatenPraktWoche2.size(); j++) {
				if (!(alleDatenPraktWoche2.get(j).get(28).matches(AnsprId))){
					alleDatenPraktWoche2.remove(j);
						j--;
					}
			}
			ArrayList<ArrayList<String>> alleDatenPraktWoche3 = new ArrayList<ArrayList<String>>(alleDatenPraktFilter);
			for (int j = 0; j < alleDatenPraktWoche3.size(); j++) {
				if (!(alleDatenPraktWoche3.get(j).get(29).matches(AnsprId))){
					alleDatenPraktWoche3.remove(j);
						j--;
					}
			}
			for (int l = 0; l < alleDatenPraktWoche1.size(); l++) {
				Date startDateDatabase = null;
				Date endDateDatabase = null;
			   try {
				   startDateDatabase = sdfToDate.parse(alleDatenPraktWoche1.get(l).get(23));
				   endDateDatabase = sdfToDate.parse(alleDatenPraktWoche1.get(l).get(24));
			   } catch (ParseException e1) {
				   e1.printStackTrace();
			   }
			   DateTime startDate = new DateTime(startDateDatabase);
			   DateTime endDate = new DateTime(endDateDatabase);
			   Interval daueranwesend = new Interval(startDate, startDate.withDayOfWeek(DateTimeConstants.SUNDAY));
			   for (int j = 0; j < table.getColumnCount(); j++) {
					for (int j2 = 0; j2 < table.getRowCount(); j2++) {
						//versucht die Eintrag Zeit zu schreiben
						DateTime eintragTime = new DateTime();
						try {
							eintragTime = new DateTime(today.getYear(), j2 + 1, j + 1, 0, 0);
						} catch (IllegalFieldValueException e) {
							// in dem angegebenen Monat j2 + 1 gibt es keinen tag nr j + 1 also setze die eintragszeit auf jahr 1 sodass der eintrag
							//sicher nicht im interval dauerblockiert oder praktikant vorhanden ist
							eintragTime = new DateTime(1, 1, 2, 0, 0);
						}
						//ist der eintrag im blockiert interval, wenn ja setze die zelle auf rot
						if (daueranwesend.contains(eintragTime) && ctr.getTableCellBackground(table, j2, j + 1).equals(Color.WHITE)) {
							ctr.setColor(j2,j + 1,Color.YELLOW);
						} else if(daueranwesend.contains(eintragTime) && ctr.getTableCellBackground(table, j2, j + 1).equals(Color.YELLOW)){
							ctr.setColor(j2,j + 1,Color.GREEN);
						} else if(daueranwesend.contains(eintragTime) && ctr.getTableCellBackground(table, j2, j + 1).equals(Color.GREEN)){
							ctr.setColor(j2,j + 1,Color.BLUE);
						}
					}
				}
			}
			for (int l = 0; l < alleDatenPraktWoche2.size(); l++) {
				Date startDateDatabase = null;
				Date endDateDatabase = null;
			   try {
				   startDateDatabase = sdfToDate.parse(alleDatenPraktWoche2.get(l).get(23));
				   endDateDatabase = sdfToDate.parse(alleDatenPraktWoche2.get(l).get(24));
			   } catch (ParseException e1) {
				   e1.printStackTrace();
			   }
			   DateTime startDate = new DateTime(startDateDatabase);
			   DateTime endDate = new DateTime(endDateDatabase);
			   Interval daueranwesend = new Interval(startDate.plusWeeks(1).withDayOfWeek(DateTimeConstants.MONDAY), startDate.plusWeeks(1).withDayOfWeek(DateTimeConstants.SUNDAY));
			   for (int j = 0; j < table.getColumnCount(); j++) {
					for (int j2 = 0; j2 < table.getRowCount(); j2++) {
						//versucht die Eintrag Zeit zu schreiben
						DateTime eintragTime = new DateTime();
						try {
							eintragTime = new DateTime(today.getYear(), j2 + 1, j + 1, 0, 0);
						} catch (IllegalFieldValueException e) {
							// in dem angegebenen Monat j2 + 1 gibt es keinen tag nr j + 1 also setze die eintragszeit auf jahr 1 sodass der eintrag
							//sicher nicht im interval dauerblockiert oder praktikant vorhanden ist
							eintragTime = new DateTime(1, 1, 2, 0, 0);
						}
						//ist der eintrag im blockiert interval, wenn ja setze die zelle auf rot
						if (daueranwesend.contains(eintragTime) && ctr.getTableCellBackground(table, j2, j + 1).equals(Color.WHITE)) {
							ctr.setColor(j2,j + 1,Color.YELLOW);
						} else if(daueranwesend.contains(eintragTime) && ctr.getTableCellBackground(table, j2, j + 1).equals(Color.YELLOW)){
							ctr.setColor(j2,j + 1,Color.GREEN);
						} else if(daueranwesend.contains(eintragTime) && ctr.getTableCellBackground(table, j2, j + 1).equals(Color.GREEN)){
							ctr.setColor(j2,j + 1,Color.BLUE);
						}
					}
				}
			}
			for (int l = 0; l < alleDatenPraktWoche3.size(); l++) {
				Date startDateDatabase = null;
				Date endDateDatabase = null;
			   try {
				   startDateDatabase = sdfToDate.parse(alleDatenPraktWoche3.get(l).get(23));
				   endDateDatabase = sdfToDate.parse(alleDatenPraktWoche3.get(l).get(24));
			   } catch (ParseException e1) {
				   e1.printStackTrace();
			   }
			   DateTime startDate = new DateTime(startDateDatabase);
			   DateTime endDate = new DateTime(endDateDatabase);
			   Interval daueranwesend = new Interval(startDate.plusWeeks(2).withDayOfWeek(DateTimeConstants.MONDAY), startDate.plusWeeks(2).withDayOfWeek(DateTimeConstants.SUNDAY));
			   for (int j = 0; j < table.getColumnCount(); j++) {
					for (int j2 = 0; j2 < table.getRowCount(); j2++) {
						//versucht die Eintrag Zeit zu schreiben
						DateTime eintragTime = new DateTime();
						try {
							eintragTime = new DateTime(today.getYear(), j2 + 1, j + 1, 0, 0);
						} catch (IllegalFieldValueException e) {
							// in dem angegebenen Monat j2 + 1 gibt es keinen tag nr j + 1 also setze die eintragszeit auf jahr 1 sodass der eintrag
							//sicher nicht im interval dauerblockiert oder praktikant vorhanden ist
							eintragTime = new DateTime(1, 1, 2, 0, 0);
						}
						//ist der eintrag im blockiert interval, wenn ja setze die zelle auf rot
						if (daueranwesend.contains(eintragTime) && ctr.getTableCellBackground(table, j2, j + 1).equals(Color.WHITE)) {
							ctr.setColor(j2,j + 1,Color.YELLOW);
						} else if(daueranwesend.contains(eintragTime) && ctr.getTableCellBackground(table, j2, j + 1).equals(Color.YELLOW)){
							ctr.setColor(j2,j + 1,Color.GREEN);
						} else if(daueranwesend.contains(eintragTime) && ctr.getTableCellBackground(table, j2, j + 1).equals(Color.GREEN)){
							ctr.setColor(j2,j + 1,Color.BLUE);
						}
					}
				}
			}
			panel.setViewportView(table);
			panel.setPreferredSize(new Dimension(panel_AnsprechAuslast.getSize().width - 100, (int) (datenanspr.length * 16.5) + 27));
			
			btnDrucken = new JButton("Drucken");
			btnDrucken.setHorizontalAlignment(SwingConstants.LEFT);
			
			panel_1.add(btnDrucken);
			panel_1.add(panel);
			panel_tableAnsprAuslast.add(panel_1);
		}
	}
	
	public void createTableMonat(){
		for (int i = 0; i < 12; i++) {
			switch (i) {
			case 0:
				setNameMonatTable("Januar");
				break;
			case 1:
				setNameMonatTable("Februar");
				break;
			case 2:
				setNameMonatTable("März");
				break;
			case 3:
				setNameMonatTable("April");
				break;
			case 4:
				setNameMonatTable("Mai");
				break;
			case 5:
				setNameMonatTable("Juni");
				break;
			case 6:
				setNameMonatTable("Juli");
				break;
			case 7:
				setNameMonatTable("August");
				break;
			case 8:
				setNameMonatTable("September");
				break;
			case 9:
				setNameMonatTable("Oktober");
				break;
			case 10:
				setNameMonatTable("November");
				break;
			case 11:
				setNameMonatTable("Dezember");
				break;
			default:
				setNameMonatTable("Monat");
				break;
			}
			panel_2 = new JPanel();
			panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.Y_AXIS));
			panel_2.setPreferredSize(new Dimension(panel_MonatAuslast.getSize().width - 100, (int) (datenmonat.length * 16.1) + 60));
			panel_2.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
			
			Border blackBorder = BorderFactory.createLineBorder(Color.BLACK, 2);
		    Border emptyBorder = BorderFactory.createEmptyBorder(0, 0, 10, 0);
		    Border twoBorder = new CompoundBorder(emptyBorder, blackBorder);
			panel_2.setBorder(twoBorder);
			
			panelm = new JScrollPane();
			
			tablem = new JTable(new DefaultTableModel_PraktikantenVerwaltung(spaltenTage, datenmonat));
			tablem.setColumnSelectionAllowed(false);
			tablem.setRowSelectionAllowed(false);
			tablem.getColumnModel().getColumn(0).setPreferredWidth(230);
			
			ColorTableCellRenderer ctr=new ColorTableCellRenderer();
			
			DateTimeFormatter dateStringFormat = DateTimeFormat.forPattern("dd.MM.yyyy");
			SimpleDateFormat sdfToDate = new SimpleDateFormat("dd.MM.yy");
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.YEAR, -50);
			sdfToDate.set2DigitYearStart(cal.getTime());
			
			DateTime today = new DateTime();
				
			Date blockiertVonDatabase = null;
			Date blockiertBisDatabase = null;
		   
			for (int j = 0; j < tablem.getRowCount(); j++) {
				for (int j2 = 0; j2 < tablem.getColumnCount(); j2++) {
					tablem.getColumnModel().getColumn(j2).setCellRenderer(ctr);
					DateTime blockiertVon;
					DateTime blockiertBis;
					try {
						   blockiertVonDatabase = sdfToDate.parse(alleDatenAnsprFilter.get(j).get(9));
						   blockiertBisDatabase = sdfToDate.parse(alleDatenAnsprFilter.get(j).get(10));
						   blockiertVon = new DateTime(blockiertVonDatabase);
						   blockiertBis = new DateTime(blockiertBisDatabase);
					   } catch (ParseException e1) {
						   blockiertVon  = new DateTime(1, 1, 1, 0, 0);
						   blockiertBis  = new DateTime(1, 1, 1, 0, 0);
					   }
					Interval dauerblockiert = new Interval(blockiertVon, blockiertBis.plusHours(1));
					DateTime eintragTime = new DateTime();
					try {
						eintragTime = new DateTime(today.getYear(), i + 1, j2 + 1, 0, 0);
					} catch (IllegalFieldValueException e) {
//						e.printStackTrace();
						eintragTime = new DateTime(1, 1, 2, 0, 0);
						ctr.setColor(j,j2 + 1,Color.GRAY);
					}
					if (dauerblockiert.contains(eintragTime)) {
						ctr.setColor(j,j2 + 1,Color.RED);
					}
				}
			}
			
			for (int j = 0; j < tablem.getRowCount(); j++) {
				String AnsprId = alleDatenAnsprFilter.get(j).get(0);
				ArrayList<ArrayList<String>> alleDatenPraktWoche1 = new ArrayList<ArrayList<String>>(alleDatenPraktFilter);
				for (int x = 0; x < alleDatenPraktWoche1.size(); x++) {
					if (!(alleDatenPraktWoche1.get(x).get(27).matches(AnsprId))){
							alleDatenPraktWoche1.remove(x);
							x--;
						}
				}
				for (int l = 0; l < alleDatenPraktWoche1.size(); l++) {
					Date startDateDatabase = null;
					Date endDateDatabase = null;
					DateTime startDate = new DateTime(1,1,2,0,0);
					DateTime endDate = new DateTime(1,1,2,0,0);
				   try {
					   startDateDatabase = sdfToDate.parse(alleDatenPraktWoche1.get(l).get(23));
					   endDateDatabase = sdfToDate.parse(alleDatenPraktWoche1.get(l).get(24));
					   startDate = new DateTime(startDateDatabase);
					   endDate = new DateTime(endDateDatabase);
				   } catch (ParseException e1) {
					   
				   }
				   Interval daueranwesend = new Interval(startDate, startDate.withDayOfWeek(DateTimeConstants.SUNDAY));
					for (int j2 = 0; j2 < tablem.getColumnCount(); j2++) {
						//versucht die Eintrag Zeit zu schreiben
						DateTime eintragTime = new DateTime();
						try {
							eintragTime = new DateTime(today.getYear(), i + 1, j2 + 1, 0, 0);
						} catch (IllegalFieldValueException e) {
							// in dem angegebenen Monat j2 + 1 gibt es keinen tag nr j + 1 also setze die eintragszeit auf jahr 1 sodass der eintrag
							//sicher nicht im interval dauerblockiert oder praktikant vorhanden ist
							eintragTime = new DateTime(1, 1, 2, 0, 0);
						}
						//ist der eintrag im blockiert interval, wenn ja setze die zelle auf rot
						if (daueranwesend.contains(eintragTime) && ctr.getTableCellBackground(tablem, j,j2 + 1).equals(Color.WHITE)) {
							ctr.setColor(j,j2 + 1,Color.YELLOW);
						} else if(daueranwesend.contains(eintragTime) && ctr.getTableCellBackground(tablem, j,j2 + 1).equals(Color.YELLOW)){
							ctr.setColor(j,j2 + 1,Color.GREEN);
						} else if(daueranwesend.contains(eintragTime) && ctr.getTableCellBackground(tablem, j,j2 + 1).equals(Color.GREEN)){
							ctr.setColor(j,j2 + 1,Color.BLUE);
						} else if(daueranwesend.contains(eintragTime) && ctr.getTableCellBackground(tablem, j,j2 + 1).equals(Color.BLUE)){
							ctr.setColor(j,j2 + 1,Color.BLUE);
						}
					}
				}
			}
			for (int j = 0; j < tablem.getRowCount(); j++) {
				String AnsprId = alleDatenAnsprFilter.get(j).get(0);
				ArrayList<ArrayList<String>> alleDatenPraktWoche2 = new ArrayList<ArrayList<String>>(alleDatenPraktFilter);
				for (int x = 0; x < alleDatenPraktWoche2.size(); x++) {
					if (!(alleDatenPraktWoche2.get(x).get(28).matches(AnsprId))){
						alleDatenPraktWoche2.remove(x);
							x--;
						}
				}
				for (int l = 0; l < alleDatenPraktWoche2.size(); l++) {
					Date startDateDatabase = null;
					Date endDateDatabase = null;
					DateTime startDate = new DateTime(1,1,2,0,0);
					   DateTime endDate = new DateTime(1,1,2,0,0);
				   try {
					   startDateDatabase = sdfToDate.parse(alleDatenPraktWoche2.get(l).get(23));
					   endDateDatabase = sdfToDate.parse(alleDatenPraktWoche2.get(l).get(24));
					   startDate = new DateTime(startDateDatabase);
					   endDate = new DateTime(endDateDatabase);
				   } catch (ParseException e1) {
					   
				   }
				   Interval daueranwesend = new Interval(startDate.plusWeeks(1).withDayOfWeek(DateTimeConstants.MONDAY), startDate.plusWeeks(1).withDayOfWeek(DateTimeConstants.SUNDAY));
					for (int j2 = 0; j2 < tablem.getColumnCount(); j2++) {
						//versucht die Eintrag Zeit zu schreiben
						DateTime eintragTime = new DateTime();
						try {
							eintragTime = new DateTime(today.getYear(), i + 1, j2 + 1, 0, 0);
						} catch (IllegalFieldValueException e) {
							// in dem angegebenen Monat j2 + 1 gibt es keinen tag nr j + 1 also setze die eintragszeit auf jahr 1 sodass der eintrag
							//sicher nicht im interval dauerblockiert oder praktikant vorhanden ist
							eintragTime = new DateTime(1, 1, 2, 0, 0);
						}
						//ist der eintrag im blockiert interval, wenn ja setze die zelle auf rot
						if (daueranwesend.contains(eintragTime) && ctr.getTableCellBackground(tablem, j,j2 + 1).equals(Color.WHITE)) {
							ctr.setColor(j,j2 + 1,Color.YELLOW);
						} else if(daueranwesend.contains(eintragTime) && ctr.getTableCellBackground(tablem, j,j2 + 1).equals(Color.YELLOW)){
							ctr.setColor(j,j2 + 1,Color.GREEN);
						} else if(daueranwesend.contains(eintragTime) && ctr.getTableCellBackground(tablem, j,j2 + 1).equals(Color.GREEN)){
							ctr.setColor(j,j2 + 1,Color.BLUE);
						} else if(daueranwesend.contains(eintragTime) && ctr.getTableCellBackground(tablem, j,j2 + 1).equals(Color.BLUE)){
							ctr.setColor(j,j2 + 1,Color.BLUE);
						}
					}
				}
			}
			for (int j = 0; j < tablem.getRowCount(); j++) {
				String AnsprId = alleDatenAnsprFilter.get(j).get(0);
				ArrayList<ArrayList<String>> alleDatenPraktWoche3 = new ArrayList<ArrayList<String>>(alleDatenPraktFilter);
				for (int x = 0; x < alleDatenPraktWoche3.size(); x++) {
					if (!(alleDatenPraktWoche3.get(x).get(29).matches(AnsprId))){
						alleDatenPraktWoche3.remove(x);
							x--;
						}
				}
					for (int l = 0; l < alleDatenPraktWoche3.size(); l++) {
					Date startDateDatabase = null;
					Date endDateDatabase = null;
					DateTime startDate = new DateTime(1,1,2,0,0);
					DateTime endDate = new DateTime(1,1,2,0,0);
				   try {
					   startDateDatabase = sdfToDate.parse(alleDatenPraktWoche3.get(l).get(23));
					   endDateDatabase = sdfToDate.parse(alleDatenPraktWoche3.get(l).get(24));
					   startDate = new DateTime(startDateDatabase);
					   endDate = new DateTime(endDateDatabase);
				   } catch (ParseException e1) {
					   
				   }
				   Interval daueranwesend = new Interval(startDate.plusWeeks(2).withDayOfWeek(DateTimeConstants.MONDAY), startDate.plusWeeks(2).withDayOfWeek(DateTimeConstants.SUNDAY));
					for (int j2 = 0; j2 < tablem.getColumnCount(); j2++) {
						//versucht die Eintrag Zeit zu schreiben
						DateTime eintragTime = new DateTime();
						try {
							eintragTime = new DateTime(today.getYear(), i + 1, j2 + 1, 0, 0);
						} catch (IllegalFieldValueException e) {
							// in dem angegebenen Monat j2 + 1 gibt es keinen tag nr j + 1 also setze die eintragszeit auf jahr 1 sodass der eintrag
							//sicher nicht im interval dauerblockiert oder praktikant vorhanden ist
							eintragTime = new DateTime(1, 1, 2, 0, 0);
						}
						//ist der eintrag im blockiert interval, wenn ja setze die zelle auf rot
						if (daueranwesend.contains(eintragTime) && ctr.getTableCellBackground(tablem, j,j2 + 1).equals(Color.WHITE)) {
							ctr.setColor(j,j2 + 1,Color.YELLOW);
						} else if(daueranwesend.contains(eintragTime) && ctr.getTableCellBackground(tablem, j,j2 + 1).equals(Color.YELLOW)){
							ctr.setColor(j,j2 + 1,Color.GREEN);
						} else if(daueranwesend.contains(eintragTime) && ctr.getTableCellBackground(tablem, j,j2 + 1).equals(Color.GREEN)){
							ctr.setColor(j,j2 + 1,Color.BLUE);
						} else if(daueranwesend.contains(eintragTime) && ctr.getTableCellBackground(tablem, j,j2 + 1).equals(Color.BLUE)){
							ctr.setColor(j,j2 + 1,Color.BLUE);
						}
					}
				}
			}
			
			panelm.setViewportView(tablem);
			panelm.setPreferredSize(new Dimension(panel_MonatAuslast.getSize().width - 100, (int) (datenmonat.length * 16.1) + 27));
			
			btnDruckenm = new JButton("Drucken");
			btnDruckenm.setHorizontalAlignment(SwingConstants.LEFT);
			
			panel_2.add(btnDruckenm);
			panel_2.add(panelm);
			panel_tableMonatAuslast.add(panel_2);
		}
	}
	
	
}
