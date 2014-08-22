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


public class PlatzhalterReplacerUndDokumentWriter {
		private WordprocessingMLPackage getTemplate(String name) throws Docx4JException, FileNotFoundException {
			WordprocessingMLPackage template = WordprocessingMLPackage.load(new FileInputStream(new File(name)));
			return template;
		}
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
		private void writeDocxToStream(WordprocessingMLPackage template, String target) throws IOException, Docx4JException {
			File f = new File(target);
			template.save(f);
		}
		public void schreibeNeuesWordDokumentVonTemplate(String template, String target,
				ArrayList<String> placeholders, ArrayList<String> realText){
			
	        WordprocessingMLPackage document = null;
	        try {
				document = getTemplate(template);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Docx4JException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        for (int i = 0; i < placeholders.size(); i++) {
	        	 replacePlaceholder(document, realText.get(i), placeholders.get(i));
			}
	        try {
				writeDocxToStream(document, target);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Docx4JException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		

}