package praktikantenverwaltung;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

public class PraktikantenVerwaltung_ViewPasswortaendern extends JFrame {

	private JPanel contentPane;
	private JTextField textField_altesPasswort;
	private JTextField textField_neuesPasswort1;
	private JTextField textField_neuesPasswort2;
	private PraktikantenVerwaltung_Modell _model; 
	private PraktikantenVerwaltung_Control _control; 

	

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
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		
		textField_altesPasswort = new JTextField();
		textField_altesPasswort.setColumns(10);
		
		textField_neuesPasswort1 = new JTextField();
		textField_neuesPasswort1.setColumns(10);
		
		textField_neuesPasswort2 = new JTextField();
		textField_neuesPasswort2.setColumns(10);
		
		JButton btnSpeichern = new JButton("Speichern");
		btnSpeichern.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JButton btnAbbrechen = new JButton("Abbrechen");
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
		
		JTextArea textArea = new JTextArea();
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
	}
}
