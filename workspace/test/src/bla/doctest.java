package bla;

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

import com.sun.org.apache.bcel.internal.generic.NEW;

public class doctest {
	private static WordprocessingMLPackage getTemplate(String name) throws Docx4JException, FileNotFoundException {
		WordprocessingMLPackage template = WordprocessingMLPackage.load(new FileInputStream(new File(name)));
		return template;
	}
	private static List<Object> getAllElementFromObject(Object obj, Class<?> toSearch) {
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
	private static void replacePlaceholder(WordprocessingMLPackage template, String name, String placeholder ) {
		List<Object> texts = getAllElementFromObject(template.getMainDocumentPart(), Text.class);
 
		for (Object text : texts) {
			Text textElement = (Text) text;
			String textElementString = textElement.getValue();
			if (textElement.getValue().contains(placeholder)) {
				textElementString = textElementString.replace(placeholder, name);
				textElement.setValue(textElementString);
				System.out.println(textElement.getValue());
			}
		}
	}
	private static void writeDocxToStream(WordprocessingMLPackage template, String target) throws IOException, Docx4JException {
		File f = new File(target);
		template.save(f);
	}
	
	public static void main(String[] args) {
		ArrayList<String> placeholders = new ArrayList<String>();
		String placeholderanrede = "<<Anrede>>";
		String placeholdernn = "<<Nachname>>";
		String placeholdervn = "<<Vorname>>";
//		placeholders.add(">><<");
		placeholders.add(placeholderanrede);
		placeholders.add(placeholdernn);
		placeholders.add(placeholdervn);
		
		ArrayList<String> toAdds =  new ArrayList<String>();
		String toAddanrede = "Herr";
        String toAddnn = "Wiechers";
        String toAddvn = "Fabian";
//        toAdds.add("Test");
        toAdds.add(toAddanrede);
        toAdds.add(toAddnn);
        toAdds.add(toAddvn);
        String template = "src/bla/template.docx";
        String target = "Anschreiben.docx";
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
        	 replacePlaceholder(document, toAdds.get(i), placeholders.get(i));
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
