package praktikantenverwaltung;
import java.awt.*;
import java.util.*;

import javax.swing.*;
/**
 * Spezielle Combobox die die Gr��e individuell an die Items anpasst
 * @author 
 *
 */
public class SteppedComboBox extends JComboBox<Object> {
	private static final long serialVersionUID = 1L;
	protected int popupWidth;
	  
	  public SteppedComboBox(ComboBoxModel<?> aModel) {
	    super();
	    setUI(new SteppedComboBoxUI());
	    popupWidth = 0;
	  }

	  public SteppedComboBox(final Object[] items) {
	    super(items);
	    setUI(new SteppedComboBoxUI());
	    popupWidth = 0;
	  }
	  
	  public SteppedComboBox(Vector<?> items) {
	    super();
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