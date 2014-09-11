package praktikantenverwaltung;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPasswordField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Arrays;

import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

import org.apache.commons.io.FileUtils;

/**
 * Klasse für das Passwortändern Fenster
 * @author Barathum
 *
 */
public class PraktikantenVerwaltung_ViewPasswortaendern extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPasswordField textField_altesPasswort;
	private JPasswordField textField_neuesPasswort1;
	private JPasswordField textField_neuesPasswort2;
	private PraktikantenVerwaltung_Modell _model; 
	private PraktikantenVerwaltung_Control _control;
	private JButton btnSpeichern;
	private JButton btnAbbrechen;
	private JTextArea textArea; 
	private JFrame fenster = this;

	/**
	 * Create the frame.
	 */
	public PraktikantenVerwaltung_ViewPasswortaendern(PraktikantenVerwaltung_Control control , PraktikantenVerwaltung_Modell model) {
		this._model = model;
		this._control = control;
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 229);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		this.setTitle("Passwort ändern");
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		
		textField_altesPasswort = new JPasswordField();
		textField_altesPasswort.setColumns(10);
		
		textField_neuesPasswort1 = new JPasswordField();
		textField_neuesPasswort1.setColumns(10);
		
		textField_neuesPasswort2 = new JPasswordField();
		textField_neuesPasswort2.setColumns(10);
		
		btnSpeichern = new JButton("Speichern");
		btnSpeichern.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		btnAbbrechen = new JButton("Abbrechen");
		btnAbbrechen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JLabel lblAltesPasswort = new JLabel("altes Passwort:");
		
		JLabel lblNeuesPasswort = new JLabel("neues Passwort:");
		
		JLabel label = new JLabel("neues Passwort:");
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(label, GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
						.addComponent(lblNeuesPasswort, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblAltesPasswort, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(41)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(textField_neuesPasswort2)
						.addComponent(textField_neuesPasswort1)
						.addComponent(textField_altesPasswort, GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE))
					.addContainerGap(21, Short.MAX_VALUE))
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap(22, Short.MAX_VALUE)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 196, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnSpeichern)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnAbbrechen)
					.addGap(18))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(21)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField_altesPasswort, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblAltesPasswort))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNeuesPasswort)
						.addComponent(textField_neuesPasswort1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(label)
						.addComponent(textField_neuesPasswort2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(18)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnAbbrechen)
								.addComponent(btnSpeichern)))
						.addGroup(gl_panel.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setForeground(Color.BLUE);
		textArea.setBackground(UIManager.getColor("Button.background"));
		textArea.setEnabled(false);
		textArea.setEditable(false);
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addComponent(textArea, GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addComponent(textArea, GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
		);
		panel_1.setLayout(gl_panel_1);
		panel.setLayout(gl_panel);
		setSpeichernListener(new SpeichernListener());
		btnAbbrechen.addActionListener(this);
	}
	/**
	 * Setzt den Actionlistener l auf den Speichern Button im Passwortändern Fenster
	 * @param l
	 */
	private void setSpeichernListener(ActionListener l){
		this.btnSpeichern.addActionListener(l);
	}
	/**
	 * Innere Klasse die den ActionListener zum Passwortändern realisiert
	 * @author Barathum
	 *
	 */
	class SpeichernListener implements ActionListener{ 
		private Cryptor _crypt = new Cryptor();
    	private SHAtoTXTFile _sha = new SHAtoTXTFile();
        public void actionPerformed(ActionEvent e) { 
        	if (Arrays.equals(textField_neuesPasswort1.getPassword(), textField_neuesPasswort2.getPassword())) {
        			String oldpw = _sha.generateHash(new String(textField_altesPasswort.getPassword()));
        			String newpw = _sha.generateHash(new String(textField_neuesPasswort1.getPassword()));
        			try {
                	_crypt.decryptFile( "db/PraktikantenDB.db", oldpw  , false);
        			} catch (GeneralSecurityException e2) {
        				textArea.setText("Altes Passwort ist falsch");
        			} catch (IOException e2) {
        				textArea.setText("Datenbank nicht gefunden");
					}
        			try {
        				_crypt.decryptFile("db/keyfile.txt", "123", true);
					} catch (GeneralSecurityException e2) {
						textArea.setText("Master Passwort falsch");
					} catch (IOException e1) {
						textArea.setText("Datei KeyFile fehlt");
					}
        			try {
        				_crypt.encryptFile( "db/PraktikantenDB.db", newpw , false);
                        FileUtils.forceDelete(new File("db/PraktikantenDB.db.decrypted.db"));
            	        	 _sha.keyToFile(_sha.generateHash(newpw));
            				_crypt.encryptFile( "db/keyfile.txt", "123" , true);
//            				textArea.setText("Passwort erfolgreich geändert");
            				_model.setPasswort(newpw);
            				fenster.dispose();
            	          FileUtils.forceDelete(new File("db/keyfile.txt.decrypted.txt"));
            	          FileUtils.forceDelete(new File("db/keyfile.txt"));
					} catch (Exception e2) {
						textArea.setText("Error");
					}
			}else {
				textArea.setText("Neues Passwort stimmt nicht überein");
			}
         } 
	   }

	@Override
	/**
	 * ActionEvents abfangen
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnAbbrechen)) {
			this.dispose();
		}
	}
}
