package praktikantenverwaltung;

import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

public interface AnsichtPraktikant_Interface {

	/**
	 * Der gekapselte Kontruktor, der Alle Felder Anlegt und positioniert
	 */
	public abstract void viewKontrukt();

	/**
	 * Setter f�r den Listener des Speichern Buttons
	 * @param l Der Listener der gesetzt werden soll
	 */
	public abstract void setPraktSpeichernListener(ActionListener l);

	/**
	 * Setter f�r den Listener des Nachricht Senden Buttons
	 * @param l Der zu setzende Listener
	 */
	public abstract void setNachrichtSendenListener(ActionListener l);

	/**
	 * Methode die die Inhalte der Eingabefelder der Praktikantenansicht zur�ck gibt
	 * @return 1D ArrayList vom Typ String die die Eintr�ge beinhaltet
	 */
	public abstract ArrayList<String> getInhaltPrakt();

	/**
	 * Gibt die Inhalte der Ansprechpartner zur�ck
	 * @return 2D ArrayList vom Typ String mit 3 Datens�tzen f�r die 3 Ansprechpartner
	 */
	public abstract ArrayList<ArrayList<String>> getInhaltAnspr();

	/**
	 * Setzt das Feld Praktikanten ID auf den Wert von id
	 * @param id Die id auf welche das textfield gesetzt werden soll
	 */
	public abstract void setPraktId(String id);

	/**
	 * setzt das Praktikanten Info/Konsolenfeld
	 * @param inf Der Text der gesetzt werden soll
	 */
	public abstract void setInfoPrakt(String inf);

	/**
	 * Gibt den Inhalt des Infofeldes/Konsole zur�ck
	 * @return Stringdarstellung des Inhaltes des Infofeldes
	 */
	public abstract String getInfoPrakt();

	/**
	 * Getter f�r die Daten des Ansprechpartners aus der Datenbank
	 * @param id ID des Ansprechpartners
	 * @return 2D ArrayList vom typ String mit dem Datensatz
	 */
	public abstract ArrayList<ArrayList<String>> getAnsprDaten(String id);

	/**
	 * Getter f�r die ausgew�hlte Nachricht
	 * @return
	 */
	public abstract String getNachrichtWahl();

	/**
	 * Methode um die Sql Befehle f�r den Praktikanten zu schreiben
	 * @param i 1 = update; 2 = insert; 4 = delete
	 * @param liste Die Datenliste des Praktikanten
	 * @return String mit dem SQL Befehl der auf die Datenbank angewandt werden kann
	 */
	public abstract String schreibeEintragPraktsql(int i,
			ArrayList<String> liste);

	/**
	 * Methode um die Sql Befehle f�r den Ansprachpartner zu schreiben
	 * @param i 1 = update; 2 = insert; 4 = delete
	 * @param liste Die Datenliste des Ansprechpartners
	 * @return String mit dem SQL Befehl der auf die Datenbank angewandt werden kann
	 */
	public abstract String schreibeEintragAnsprsql(int i,
			ArrayList<String> liste);

	/**
	 * Setter f�r die Inhalte des Praktikanten
	 * @param daten Die zu setzenden Daten
	 * @throws IndexOutOfBoundsException Es fehlen Daten in der Liste
	 */
	public abstract void setInhaltPrakt(ArrayList<ArrayList<String>> daten)
			throws IndexOutOfBoundsException;

	/**
	 * Methode f�r alle Files aus dem Ordnder f
	 * Setzt dann den Inhalt des Ordners in die Liste der Nachrichten
	 * @param f Der Pfad des Ordners in dem gesucht werden soll
	 */
	public abstract void getDocx(File f);

	/**
	 * Updatet den Status des Praktikanten je nach erstellter Nachricht
	 * @param nachricht Die Nachricht die erstellt wurde
	 * @param id Die Id des Praktikanten
	 */
	public abstract void statusupdate(String nachricht, String id);

	public abstract void setVisible(boolean b);

}