package praktikantenverwaltung;
import java.awt.*;
import javax.swing.plaf.metal.*;
import javax.swing.plaf.basic.*;

/**
 * @version 1.0 12/12/98
 */
class SteppedComboBoxUI extends MetalComboBoxUI {
  protected ComboPopup createPopup() {
    BasicComboPopup popup = new BasicComboPopup( comboBox ) {
        
      public void show() {
        Dimension popupSize = ((SteppedComboBox)comboBox).getPopupSize();
        popupSize.setSize( popupSize.width,
          getPopupHeightForRowCount( comboBox.getMaximumRowCount() ) );
        Rectangle popupBounds = computePopupBounds( 0,
          comboBox.getBounds().height, popupSize.width, popupSize.height);
        scroller.setMaximumSize( popupBounds.getSize() );
        scroller.setPreferredSize( popupBounds.getSize() );
        scroller.setMinimumSize( popupBounds.getSize() );
        list.invalidate();            
        int selectedIndex = comboBox.getSelectedIndex();
        if ( selectedIndex == -1 ) {
          list.clearSelection();
        } else {
          list.setSelectedIndex( selectedIndex );
        }            
        list.ensureIndexIsVisible( list.getSelectedIndex() );
        setLightWeightPopupEnabled( comboBox.isLightWeightPopupEnabled() );

        show( comboBox, popupBounds.x, popupBounds.y );
      }
    };
    popup.getAccessibleContext().setAccessibleParent(comboBox);
    return popup;
  }
}