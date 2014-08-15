package praktikantenverwaltung;
/**
 * Startklasse
 * @author Barathum
 *
 */
public class Praktikantenverwaltung_Main {
	static PraktikantenVerwaltung_Control controller;
    public static void main(String [] args){ 
         controller = new PraktikantenVerwaltung_Control(); 
         controller.showView(); 
    } 
}
