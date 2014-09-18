package praktikantenverwaltung;

import java.awt.Color;
import java.awt.Component;
import java.util.HashMap;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class ColorTableCellRenderer implements TableCellRenderer
{
private HashMap<Integer,Color> cellData=new HashMap<Integer,Color>();
public Component getTableCellRendererComponent(JTable table,Object value,boolean isSelected,boolean hasFocus,int row,int column)
    {
    JLabel label=new JLabel((String)value);
    int key=((row+1)*1000)+column;
    label.setOpaque(true);
    if(cellData.containsKey(key))
        {
        label.setBackground(cellData.get(key));
        }
    else //Standardfarbe setzen
        {
        label.setBackground(Color.WHITE);
        }
    return label;
    }
public void setColor(int row,int column,Color color)
    {
    int key=((row+1)*1000)+column;
    cellData.put(key,color);
    }
}