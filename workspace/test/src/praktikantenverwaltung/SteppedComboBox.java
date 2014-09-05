package praktikantenverwaltung;
import java.awt.*;
import java.util.*;
import javax.swing.*;
/**
 * 
 * @author Barathum
 *
 */
public class SteppedComboBox extends JComboBox {
	  protected int popupWidth;
	  
	  public SteppedComboBox(ComboBoxModel aModel) {
	    super(aModel);
	    setUI(new SteppedComboBoxUI());
	    popupWidth = 0;
	  }

	  public SteppedComboBox(final Object[] items) {
	    super(items);
	    setUI(new SteppedComboBoxUI());
	    popupWidth = 0;
	  }
	  
	  public SteppedComboBox(Vector items) {
	    super(items);
	    setUI(new SteppedComboBoxUI());
	    popupWidth = 0;
	  }
	  
	  
	  public void setPopupWidth(int width) {
	    popupWidth = width;
	  }
	  
	  public Dimension getPopupSize() {
	    Dimension size = getSize();
	    if (popupWidth < 1) popupWidth = size.width;
	    return new Dimension(popupWidth, size.height);
	  }
	}