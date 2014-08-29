package praktikantenverwaltung;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.KeyboardFocusManager;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JCheckBox;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class PraktikantenVerwaltung_ViewTabelleAnspr extends JFrame implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	private JPanel mainPanel;
	private JTable table_anspr = new JTable();
	private JScrollPane scrollPane_SuchlisteAnspr;
	private String[] spaltennamenansprech = {
            "Nachname",
            "Vorname",
            "Telefonnummer",
            "E-Mail-Adresse", 
            "Abteilung", 
            "Raumnummer", 
            "Blockiert ab",
            "Blockiert bis"};
	private Object[][] datenansprech;
	private JButton buttonAnsprBearb;
	private JButton buttonAnsprLoesch;
	private PraktikantenVerwaltung_Modell _model;
	private PraktikantenVerwaltung_Control _control;
	private JPanel panel;
	private JTextField textField_tabelleNN;
	private JTextField textField_tabelleVN;
	private JTextField textField_tabelleTele;
	private JTextField textField_tabelleMail;
	private JTextField textField_tabelleAbt;
	private JTextField textField_tabelleRaum;
	private JTextField textField_tabelleBlockVon;
	private JTextField textField_tabelleBlockBis;
	private JButton button_aktualisieren;
	private JCheckBox chckbxEtechnik;
	private JCheckBox chckbxInformatik;
	private JCheckBox chckbxKaufmnnisch;
	ImageIcon iconAktualisieren = new ImageIcon(new ImageIcon("img/aktualisierenIcon.png").getImage().getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH));

	/**
	 * Create the frame.
	 */
	public PraktikantenVerwaltung_ViewTabelleAnspr(PraktikantenVerwaltung_Control control , PraktikantenVerwaltung_Modell model , ArrayList<ArrayList<String>> Tabellen_Eintraege) {
		this._model = model;
		this._control = control;
		setDatenAnspr(_control.ArrayListtoArray(Tabellen_Eintraege));
		setResizable(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(20, 20, 1280, 720);
		
		
		mainPanel = new JPanel();
		getContentPane().add(mainPanel, BorderLayout.CENTER);
		mainPanel.setLayout(new CardLayout(0, 0));
		
		 
		 JPanel panel_tabellenAnspr = new JPanel();
		 mainPanel.add(panel_tabellenAnspr, "card_6");
		 panel_tabellenAnspr.setLayout(new BorderLayout(0, 0));
		 
		 panel = new JPanel();
		 panel_tabellenAnspr.add(panel, BorderLayout.NORTH);
		 
		 textField_tabelleNN = new JTextField();
		 textField_tabelleNN.setColumns(10);
		 
		 textField_tabelleVN = new JTextField();
		 textField_tabelleVN.setColumns(10);
		 
		 textField_tabelleTele = new JTextField();
		 textField_tabelleTele.setColumns(10);
		 
		 textField_tabelleMail = new JTextField();
		 textField_tabelleMail.setColumns(10);
		 
		 textField_tabelleAbt = new JTextField();
		 textField_tabelleAbt.setColumns(10);
		 
		 textField_tabelleRaum = new JTextField();
		 textField_tabelleRaum.setColumns(10);
		 
		 textField_tabelleBlockVon = new JTextField();
		 textField_tabelleBlockVon.setColumns(10);
		 
		 textField_tabelleBlockBis = new JTextField();
		 textField_tabelleBlockBis.setColumns(10);
		 
		 button_aktualisieren = new JButton(iconAktualisieren);
		 button_aktualisieren.setBorder(BorderFactory.createEmptyBorder());
		 GroupLayout gl_panel = new GroupLayout(panel);
		 gl_panel.setHorizontalGroup(
		 	gl_panel.createParallelGroup(Alignment.LEADING)
		 		.addGroup(gl_panel.createSequentialGroup()
		 			.addComponent(textField_tabelleNN, GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
		 			.addGap(1)
		 			.addComponent(textField_tabelleVN, GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
		 			.addGap(1)
		 			.addComponent(textField_tabelleTele, GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
		 			.addGap(1)
		 			.addComponent(textField_tabelleMail, GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
		 			.addGap(1)
		 			.addComponent(textField_tabelleAbt, GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
		 			.addGap(1)
		 			.addComponent(textField_tabelleRaum, GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
		 			.addGap(1)
		 			.addComponent(textField_tabelleBlockVon, GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
		 			.addGap(1)
		 			.addComponent(textField_tabelleBlockBis, GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
		 			.addGap(1)
		 			.addComponent(button_aktualisieren, GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
		 			.addGap(3))
		 );
		 gl_panel.setVerticalGroup(
		 	gl_panel.createParallelGroup(Alignment.LEADING)
		 		.addComponent(textField_tabelleNN, GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
		 		.addComponent(textField_tabelleVN, GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
		 		.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
		 			.addComponent(textField_tabelleTele, GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
		 			.addComponent(textField_tabelleMail, GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
		 			.addComponent(textField_tabelleAbt, GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
		 			.addComponent(textField_tabelleRaum, GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE))
		 		.addComponent(textField_tabelleBlockVon, GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
		 		.addComponent(textField_tabelleBlockBis, GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
		 		.addGroup(gl_panel.createSequentialGroup()
		 			.addGap(1)
		 			.addComponent(button_aktualisieren, GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
		 			.addGap(1))
		 );
		 panel.setLayout(gl_panel);
		 
		 scrollPane_SuchlisteAnspr = new JScrollPane();
		 panel_tabellenAnspr.add(scrollPane_SuchlisteAnspr);
		 scrollPane_SuchlisteAnspr.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		 
		 JPanel panel_9 = new JPanel();
		 panel_tabellenAnspr.add(panel_9, BorderLayout.SOUTH);
		 
		 buttonAnsprBearb = new JButton("Bearbeiten");
		 
		 buttonAnsprLoesch = new JButton("L\u00F6schen");
		 
		 chckbxEtechnik = new JCheckBox("E-Technik");
		 
		 chckbxInformatik = new JCheckBox("Informatik");
		 
		 chckbxKaufmnnisch = new JCheckBox("Kaufm\u00E4nnisch");
		 
		 GroupLayout gl_panel_9 = new GroupLayout(panel_9);
		 gl_panel_9.setHorizontalGroup(
		 	gl_panel_9.createParallelGroup(Alignment.LEADING)
		 		.addGroup(gl_panel_9.createSequentialGroup()
		 			.addGap(20)
		 			.addComponent(buttonAnsprBearb)
		 			.addGap(18)
		 			.addComponent(buttonAnsprLoesch, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
		 			.addGap(73)
		 			.addComponent(chckbxEtechnik, GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
		 			.addPreferredGap(ComponentPlacement.RELATED)
		 			.addComponent(chckbxInformatik, GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
		 			.addPreferredGap(ComponentPlacement.RELATED)
		 			.addComponent(chckbxKaufmnnisch, GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
		 			.addGap(712))
		 );
		 gl_panel_9.setVerticalGroup(
		 	gl_panel_9.createParallelGroup(Alignment.LEADING)
		 		.addGroup(gl_panel_9.createSequentialGroup()
		 			.addGap(9)
		 			.addGroup(gl_panel_9.createParallelGroup(Alignment.BASELINE)
		 				.addComponent(buttonAnsprBearb)
		 				.addComponent(buttonAnsprLoesch)
		 				.addComponent(chckbxEtechnik)
		 				.addComponent(chckbxInformatik)
		 				.addComponent(chckbxKaufmnnisch))
		 			.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		 );
		 panel_9.setLayout(gl_panel_9);
		 
			this.setTabelleFilterListener(new TabelleFilterListener());
			this.setTabelleFilterListenerCheckBox(new TabelleFilterListener());
			this.setLoeschenListener(new AnsprLoeschenListener());
			this.setBearbeitenListener(new AnsprBearbeitenListener());
			button_aktualisieren.addActionListener(this);
			textField_tabelleNN.addActionListener(enterAction);
			textField_tabelleVN.addActionListener(enterAction);
			textField_tabelleTele.addActionListener(enterAction);
			textField_tabelleMail.addActionListener(enterAction);
			textField_tabelleAbt.addActionListener(enterAction);
			textField_tabelleRaum.addActionListener(enterAction);
			textField_tabelleBlockVon.addActionListener(enterAction);
			textField_tabelleBlockBis.addActionListener(enterAction);
			updateTable();
	}
	Action enterAction = new AbstractAction()
	{
		private static final long serialVersionUID = 1L;

		@Override
	    public void actionPerformed(ActionEvent e)
	    {
//	    	try {
	    		KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
		        manager.getFocusOwner().transferFocus();
//			} catch (Exception e2) {
//				// TODO: handle exception
//			}
	    }
	};
	public void setDatenAnspr(Object[][] daten){
		this.datenansprech = daten;
	}
	public void updateTable(){
		table_anspr = new JTable(new DefaultTableModel_PraktikantenVerwaltung(spaltennamenansprech, datenansprech));
		table_anspr.addMouseListener(new MouseAdapter() {
			   public void mouseClicked(MouseEvent e) {
			      if (e.getClickCount() == 2) {
			    	  refresh();
			    	  ArrayList<ArrayList<String>> daten = new ArrayList<ArrayList<String>>();
			    	  JTable target = getTable();
			            int markierteReiheNR =  target.getSelectedRow();
			            ArrayList<String> liste = new ArrayList<String>();
			            if(markierteReiheNR >= 0){
				            String nn = (String) target.getValueAt(markierteReiheNR, 0);
				            String vn = (String) target.getValueAt(markierteReiheNR, 1);
				            String tele = (String) target.getValueAt(markierteReiheNR, 2);
				            liste.add(nn);
				            liste.add(vn);
				            liste.add(tele);
				            String sql;
				            sql = getEintragAnspr(liste);
				            daten = _model.getData(sql);
				            try {
				            	PraktikantenVerwaltung_ViewAnspr _viewanspr = new PraktikantenVerwaltung_ViewAnspr(_control, _model , daten);
						      	   _viewanspr.setVisible(true);
							} catch (IndexOutOfBoundsException e2) {
								return;
							}
				            
			            }
			         }
			   }
			});
		scrollPane_SuchlisteAnspr.setViewportView(table_anspr);
	}
	private void setLoeschenListener(ActionListener l){
		this.buttonAnsprLoesch.addActionListener(l);
	}
	private void setBearbeitenListener(ActionListener l){
		this.buttonAnsprBearb.addActionListener(l);
	}
	private void setTabelleFilterListener(DocumentListener l){
	        this.textField_tabelleNN.getDocument().addDocumentListener(l);
	        this.textField_tabelleVN.getDocument().addDocumentListener(l);
	        this.textField_tabelleTele.getDocument().addDocumentListener(l);
	        this.textField_tabelleMail.getDocument().addDocumentListener(l);
	        this.textField_tabelleAbt.getDocument().addDocumentListener(l);
	        this.textField_tabelleRaum.getDocument().addDocumentListener(l);
	        this.textField_tabelleBlockVon.getDocument().addDocumentListener(l);
	        this.textField_tabelleBlockBis.getDocument().addDocumentListener(l);
	}
	private void setTabelleFilterListenerCheckBox(ActionListener l){
		this.chckbxEtechnik.addActionListener(l);
		this.chckbxInformatik.addActionListener(l);
		this.chckbxKaufmnnisch.addActionListener(l);
	}
	class AnsprLoeschenListener implements ActionListener{ 
        public void actionPerformed(ActionEvent e) { 
     	   JTable table = getTable();
     	   ArrayList<ArrayList<String>> daten = new ArrayList<ArrayList<String>>();
            int[] markierteReiheNR =  table.getSelectedRows();
            for (int i = 0; i < markierteReiheNR.length; i++) {
            	ArrayList<String> liste = new ArrayList<String>();
	            String nn = (String) table.getValueAt(markierteReiheNR[i], 0);
	            String vn = (String) table.getValueAt(markierteReiheNR[i], 1);
	            String tele = (String) table.getValueAt(markierteReiheNR[i], 2);
	            liste.add(nn);
	            liste.add(vn);
	            liste.add(tele);
	            String sql;
	            sql = "SELECT ID FROM ANSPRECHPARTNER where NN='" + liste.get(0) + "' AND VN ='" + liste.get(1) + "' AND TELE ='" + liste.get(2) + "';";
	            daten = _model.getData(sql);
	            sql = "UPDATE PRAKTIKANTEN set ANSPR1 = '0' WHERE ANSPR1 = '" + daten.get(0).get(0) + "';";
	            _model.insertUpdateDeleteTable(sql);
	            sql = "UPDATE PRAKTIKANTEN set ANSPR2 = '0' WHERE ANSPR2 = '" + daten.get(0).get(0) + "';";
	            _model.insertUpdateDeleteTable(sql);
	            sql = "UPDATE PRAKTIKANTEN set ANSPR3 = '0' WHERE ANSPR3 = '" + daten.get(0).get(0) + "';";
	            _model.insertUpdateDeleteTable(sql);
	            sql = loescheEintragAnspr(liste);
	            _model.insertUpdateDeleteTable(sql);
			}
	         filter();
            }
	   }
	class AnsprBearbeitenListener implements ActionListener{ 
        public void actionPerformed(ActionEvent e) { 
     	   ArrayList<ArrayList<String>> daten = new ArrayList<ArrayList<String>>();
     	  refresh();
     	   JTable table = getTable();
     	  int[] markierteReiheNR =  table.getSelectedRows();
          for (int i = 0; i < markierteReiheNR.length; i++) {
          	ArrayList<String> liste = new ArrayList<String>();
	            String nn = (String) table.getValueAt(markierteReiheNR[i], 0);
	            String vn = (String) table.getValueAt(markierteReiheNR[i], 1);
	            String tele = (String) table.getValueAt(markierteReiheNR[i], 2);
	            liste.add(nn);
	            liste.add(vn);
	            liste.add(tele);
	            String sql;
	            sql = getEintragAnspr(liste);
	            daten = _model.getData(sql);
	            try {
	            	PraktikantenVerwaltung_ViewAnspr _viewanspr = new PraktikantenVerwaltung_ViewAnspr(_control, _model , daten);
			      	   _viewanspr.setVisible(true);
				} catch (IndexOutOfBoundsException e2) {
					return;
				}
            }
         } 
	   }
	private JTable getTable(){
		return table_anspr;
	}
	private String loescheEintragAnspr(ArrayList<String> liste){
		String sql;
		sql = "DELETE from ANSPRECHPARTNER where NN='" + liste.get(0) + "' AND VN ='" + liste.get(1) + "' AND TELE ='" + liste.get(2) + "';";
		return sql;
	}
	public String getEintragAnspr(ArrayList<String> liste){
		String sql;
		sql = "SELECT * from ANSPRECHPARTNER where NN='" + liste.get(0) + "' AND VN ='" + liste.get(1) + "' AND TELE ='" + liste.get(2) + "';";
		return sql;
	}
	class TabelleFilterListener implements DocumentListener, ActionListener{ 

		@Override
		public void changedUpdate(DocumentEvent e) {
			filter();
		}

		@Override
		public void insertUpdate(DocumentEvent e) {
			filter();
		}

		@Override
		public void removeUpdate(DocumentEvent e) {
			filter();
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			filter();
		}
	}
	public void filter() { 
		   ArrayList<ArrayList<String>> daten = new ArrayList<ArrayList<String>>();
		   ArrayList<String> datenTextfields = new ArrayList<String>();
		   datenTextfields = getInhaltSuchFelders();
		   /**
		    * Hier kommen die if abfragen
		    */
		   if (datenTextfields.get(6).trim().startsWith("<") || datenTextfields.get(6).trim().startsWith(">")
				   || datenTextfields.get(7).trim().startsWith("<") || datenTextfields.get(7).trim().startsWith(">")) {
			   DateTimeFormatter dateStringFormat = DateTimeFormat.forPattern("dd.MM.yyyy");
			   SimpleDateFormat sdfToDate = new SimpleDateFormat("dd.MM.yy");
			   Calendar cal = Calendar.getInstance();
			   cal.add(Calendar.YEAR, -50);
			   sdfToDate.set2DigitYearStart(cal.getTime());
			   
			   daten = _model.getData("SELECT NN , VN , TELE , MAIL , ABTEILUNG , RNR , BLOCKIERENVON , BLOCKIERENBIS FROM ANSPRECHPARTNER WHERE NN LIKE '%" + datenTextfields.get(0) + "%' AND VN LIKE '%" + datenTextfields.get(1) + "%' "
				   		+ "AND TELE LIKE '%" + datenTextfields.get(2) + "%' AND MAIL LIKE '%" + datenTextfields.get(3) + "%' AND ABTEILUNG LIKE '%" + datenTextfields.get(4) + "%' AND RNR LIKE '%" + datenTextfields.get(5)  + "%' AND ETECH LIKE '%" + datenTextfields.get(8) + "%' AND KAUFM LIKE '%" + datenTextfields.get(9) + "%' AND INF LIKE '%" + datenTextfields.get(10) + "%' ORDER BY NN;");
			   char startVonBlockiertVon;
			   char startVonBlockiertBis;
			   try {
				   startVonBlockiertBis = datenTextfields.get(7).trim().charAt(0);
				} catch (StringIndexOutOfBoundsException e) {
					startVonBlockiertBis = ' ';
				}
			   
				try {
				   startVonBlockiertVon = datenTextfields.get(6).trim().charAt(0);
				} catch (StringIndexOutOfBoundsException e) {
					startVonBlockiertVon = ' ';
				}
				
				String blockierenVonAusEingabe = datenTextfields.get(6).trim();
				String blockierenBisAusEingabe = datenTextfields.get(7).trim();
				
				if (startVonBlockiertVon == '>') {
					try {
						Date blockierenVonAusEingabeDatesdf = null;
						blockierenVonAusEingabe = blockierenVonAusEingabe.substring(1).trim();
						try {
							blockierenVonAusEingabeDatesdf = sdfToDate.parse(blockierenVonAusEingabe);
							System.out.println(blockierenVonAusEingabeDatesdf.toString());
						} catch (ParseException e1) {
							
						}
						DateTime startDatumAusEingabeDate = new DateTime(blockierenVonAusEingabeDatesdf);
//						DateTime startDatumAusEingabeDate = dateStringFormat.parseDateTime(startDatumAusEingabe);
						for (int i = 0; i < daten.size(); i++) {
							String blockierenVonString = daten.get(i).get(6);
							DateTime blockierenVonDate;
							try {
								blockierenVonDate = dateStringFormat.parseDateTime(blockierenVonString);
							} catch (Exception e) {
								blockierenVonDate = new DateTime();
							}
					        if (startDatumAusEingabeDate.isAfter(blockierenVonDate)) {
					        	daten.remove(i);
								i--;
							}
						}
					} catch (IllegalArgumentException e) {
		//					e.printStackTrace();
					}
					   
				}
				   /**
				    * Hier ist der einzige unterschied eine vertauschung von eingabe und listen Datum um alle zu filtern
				    * die vor der Eingabe liegen
				    */
				else if (startVonBlockiertVon == '<') {
					try {
						Date blockierenVonAusEingabeDatesdf = null;
						blockierenVonAusEingabe = blockierenVonAusEingabe.substring(1).trim();
						try {
							blockierenVonAusEingabeDatesdf = sdfToDate.parse(blockierenVonAusEingabe);
						} catch (ParseException e1) {
								
						}
						DateTime blockierenVonAusEingabeDate = new DateTime(blockierenVonAusEingabeDatesdf);
//						DateTime startDatumAusEingabeDate = dateStringFormat.parseDateTime(startDatumAusEingabe);
						for (int i = 0; i < daten.size(); i++) {
							String blockierenVonString = daten.get(i).get(6);
							DateTime blockiertVonDate;
							try {
								blockiertVonDate = dateStringFormat.parseDateTime(blockierenVonString);
							} catch (Exception e) {
								blockiertVonDate = new DateTime();
							}
					        if (blockiertVonDate.isAfter(blockierenVonAusEingabeDate)) {
								daten.remove(i);
								i--;
							}
						}
					} catch (IllegalArgumentException e) {
//						e.printStackTrace();
					}
				} else {
					try {
						for (int i = 0; i < daten.size(); i++) {
							String blockierenVonString = daten.get(i).get(6);
				            if (blockierenVonString.matches(".*" + blockierenVonAusEingabe + ".*") == false) {
								daten.remove(i);
								i--;
							}
						}
					} catch (IllegalArgumentException e) {
//						e.printStackTrace();
					}
				}
				if (startVonBlockiertBis == '>') {
					try {
						Date blockierenBisAusEingabeDatesdf = null;
						blockierenBisAusEingabe = blockierenBisAusEingabe.substring(1).trim();
						try {
							blockierenBisAusEingabeDatesdf = sdfToDate.parse(blockierenBisAusEingabe);
							System.out.println(blockierenBisAusEingabeDatesdf.toString());
						} catch (ParseException e1) {
							
						}
						DateTime blockiertBisAusEingabeDate = new DateTime(blockierenBisAusEingabeDatesdf);
//						DateTime startDatumAusEingabeDate = dateStringFormat.parseDateTime(startDatumAusEingabe);
						for (int i = 0; i < daten.size(); i++) {
							String blockierenBisString = daten.get(i).get(7);
							DateTime blockierenBisDate;
							try {
								blockierenBisDate = dateStringFormat.parseDateTime(blockierenBisString);
							} catch (Exception e) {
								blockierenBisDate = new DateTime();
							}
					        if (blockiertBisAusEingabeDate.isAfter(blockierenBisDate)) {
					        	daten.remove(i);
								i--;
							}
						}
					} catch (IllegalArgumentException e) {
		//					e.printStackTrace();
					}
					   
				}
				   /**
				    * Hier ist der einzige unterschied eine vertauschung von eingabe und listen Datum um alle zu filtern
				    * die vor der Eingabe liegen
				    */
				else if (startVonBlockiertBis == '<') {
					try {
						Date blockierenBisAusEingabeDatesdf = null;
						blockierenBisAusEingabe = blockierenBisAusEingabe.substring(1).trim();
						try {
							blockierenBisAusEingabeDatesdf = sdfToDate.parse(blockierenBisAusEingabe);
						} catch (ParseException e1) {
								
						}
						DateTime blockierenBisAusEingabeDate = new DateTime(blockierenBisAusEingabeDatesdf);
//						DateTime startDatumAusEingabeDate = dateStringFormat.parseDateTime(startDatumAusEingabe);
						for (int i = 0; i < daten.size(); i++) {
							String blockierenBisString = daten.get(i).get(7);
							DateTime blockiertBisDate;
							try {
								blockiertBisDate = dateStringFormat.parseDateTime(blockierenBisString);
							} catch (Exception e) {
								blockiertBisDate = new DateTime();
							}
					        if (blockiertBisDate.isAfter(blockierenBisAusEingabeDate)) {
								daten.remove(i);
								i--;
							}
						}
					} catch (IllegalArgumentException e) {
//						e.printStackTrace();
					}
				} else {
					try {
						for (int i = 0; i < daten.size(); i++) {
							String blockierenBisString = daten.get(i).get(7);
				            if (blockierenBisString.matches(".*" + blockierenBisAusEingabe + ".*") == false) {
								daten.remove(i);
								i--;
							}
						}
					} catch (IllegalArgumentException e) {
//						e.printStackTrace();
					}
				}
				 
			} else {
				daten = _model.getData("SELECT NN , VN , TELE , MAIL , ABTEILUNG , RNR , BLOCKIERENVON , BLOCKIERENBIS FROM ANSPRECHPARTNER WHERE NN LIKE '%" + datenTextfields.get(0) + "%' AND VN LIKE '%" + datenTextfields.get(1) + "%' "
				   		+ "AND TELE LIKE '%" + datenTextfields.get(2) + "%' AND MAIL LIKE '%" + datenTextfields.get(3) + "%' AND ABTEILUNG LIKE '%" + datenTextfields.get(4) + "%' AND RNR LIKE '%" + datenTextfields.get(5) + "%' AND BLOCKIERENVON LIKE '%" + datenTextfields.get(6) + "%'"
				   		+ "AND BLOCKIERENBIS LIKE '%" + datenTextfields.get(7) + "%' AND ETECH LIKE '%" + datenTextfields.get(8) + "%' AND KAUFM LIKE '%" + datenTextfields.get(9) + "%' AND INF LIKE '%" + datenTextfields.get(10) + "%' ORDER BY NN;");
			}
		   
		   setDatenAnspr(_control.ArrayListtoArray(daten));
		   updateTable();
	}
	private void refresh(){
		ListSelectionModel model = getTable().getSelectionModel();
		int[] rows = getTable().getSelectedRows();
		model.clearSelection();
		filter();
		for (int i = 0; i < rows.length; i++) {
			model.addSelectionInterval(rows[i], rows[i]);
		}
		table_anspr.setSelectionModel(model);
	}
	private ArrayList<String> getInhaltSuchFelders(){
		ArrayList<String> daten = new ArrayList<String>();
		daten.add(this.textField_tabelleNN.getText());
		daten.add(this.textField_tabelleVN.getText());
		daten.add(this.textField_tabelleTele.getText());
		daten.add(this.textField_tabelleMail.getText());
		daten.add(this.textField_tabelleAbt.getText());
		daten.add(this.textField_tabelleRaum.getText());
		daten.add(this.textField_tabelleBlockVon.getText());
		daten.add(this.textField_tabelleBlockBis.getText());
		if (chckbxEtechnik.isSelected()) {
			daten.add("1");
		}else {
			daten.add("");
		}
		if (chckbxKaufmnnisch.isSelected()) {
			daten.add("1");
		}else {
			daten.add("");
		}
		if (chckbxInformatik.isSelected()) {
			daten.add("1");
		}else {
			daten.add("");
		}
	    return daten;
	}
	public void actionPerformed(ActionEvent evt) {
		Object src = evt.getSource();
		   if (src == button_aktualisieren) {
			   filter();
			}
	}
}
