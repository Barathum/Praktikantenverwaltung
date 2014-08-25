package praktikantenverwaltung;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBElement;

import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.wml.ContentAccessor;
import org.docx4j.wml.Text;

/**
 * Klasse zum erstellen eines DocX Dokuments
 * @author Barathum
 *
 */
public class PlatzhalterReplacerUndDokumentWriter {
		/**
		 * Methode, die Bei gegebenem Pfad eine WordprocessingMLPackage Darstellung der docx Datei zurück gibt
		 * @param name Pfad der Template- Datei
		 * @return gibt das Template in WordprocessingMLPackage Darstellung zurück
		 * @throws Docx4JException
		 * @throws FileNotFoundException
		 */
		private WordprocessingMLPackage getTemplate(String name) throws Docx4JException, FileNotFoundException {
			WordprocessingMLPackage template = WordprocessingMLPackage.load(new FileInputStream(new File(name)));
			return template;
		}
		/**
		 * Methode die aus gegebenem Object eine Liste von Child Objecten zurück gibt
		 * @param obj Das zu durchsuchende Element
		 * @param toSearch Die zu suchenden Unter Elemente (z.B. Text.class)
		 * @return Liste der Child Elemente von Objekt, die auf toSearch passen
		 */
		private List<Object> getAllElementFromObject(Object obj, Class<?> toSearch) {
			List<Object> result = new ArrayList<Object>();
			if (obj instanceof JAXBElement) obj = ((JAXBElement<?>) obj).getValue();
	 
			if (obj.getClass().equals(toSearch))
				result.add(obj);
			else if (obj instanceof ContentAccessor) {
				List<?> children = ((ContentAccessor) obj).getContent();
				for (Object child : children) {
					result.addAll(getAllElementFromObject(child, toSearch));
				}
	 
			}
			return result;
		}
		/**
		 * Methode, die den Platzhalter im Template Dokument durch den gewünschten Text ersetzt
		 * @param template Das Vorlagen Dokument
		 * @param name Der gewünschte Text
		 * @param placeholder Der Platzhalter
		 */
		private void replacePlaceholder(WordprocessingMLPackage template, String name, String placeholder ) {
			List<Object> texts = getAllElementFromObject(template.getMainDocumentPart(), Text.class);
	 
			for (Object text : texts) {
				Text textElement = (Text) text;
				String textElementString = textElement.getValue();
//				System.out.println(textElementString);
				if (textElement.getValue().contains(placeholder)) {
					textElementString = textElementString.replace(placeholder, name);
					textElement.setValue(textElementString);
//					System.out.println(textElement.getValue());
				}
			}
		}
		/**
		 * Speichert template unter dem Pfad target
		 * @param template Das zu speichernde Dokument
		 * @param target Der Zielpfad
		 * @throws IOException 
		 * @throws Docx4JException
		 */
		private void writeDocxToStream(WordprocessingMLPackage template, String target) throws IOException, Docx4JException {
			File f = new File(target);
			template.save(f);
		}
		/**
		 * Hauptmethode der Klasse, die alle anderen Methoden dazu verwendet das template
		 * zu laden alle Platzhalter placeholders mit dem gewünschten Text realText zu ersetzen und das Ergebnis Dukoment unter
		 * dem Pfad target zu speichern
		 * @param template Das Vorlagen Dokument
		 * @param target Zielpfad für fertiges Dokument
		 * @param placeholders Platzhalter
		 * @param realText Der gewünschte Text
		 */
		public void schreibeNeuesWordDokumentVonTemplate(String template, String target,
				ArrayList<String> placeholders, ArrayList<String> realText){
			
	        WordprocessingMLPackage document = null;
	        try {
				document = getTemplate(template);
			} catch (FileNotFoundException e) {
				System.out.println("Die angegebene Datei " + template + " konnte nicht gefunden werden");
				return;
			} catch (Docx4JException e) {
				e.printStackTrace();
				return;
			}
	        for (int i = 0; i < placeholders.size(); i++) {
	        	 replacePlaceholder(document, realText.get(i), placeholders.get(i));
			}
	        try {
				writeDocxToStream(document, target);
			} catch (IOException e) {
				e.printStackTrace();
				return;
			} catch (Docx4JException e) {
				e.printStackTrace();
				return;
			}
		}
		

}