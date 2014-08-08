package testpack;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;


class DefaultTableModel_PraktikantenVerwaltung extends AbstractTableModel {
	private String[] spalten;
	private Object[][] daten;
	public DefaultTableModel_PraktikantenVerwaltung(String[] spaltennamen, Object[][] daten){
		this.spalten = spaltennamen;
		this.daten = daten;
	}
    public int getColumnCount() {
        return spalten.length;
    }

    public int getRowCount() {
        return daten.length;
    }

    public String getColumnName(int col) {
        return spalten[col];
    }

    public Object getValueAt(int row, int col) {
        return daten[row][col];
    }

    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }

    /*
     * Don't need to implement this method unless your table's
     * editable.
     */
    public boolean isCellEditable(int row, int col) {
        //Note that the data/cell address is constant,
        //no matter where the cell appears onscreen.
        if (true) {
            return false;
        } else {
            return true;
        }
    }

    /*
     * Don't need to implement this method unless your table's
     * data can change.
     */
    public void setValueAt(Object value, int row, int col) {
        daten[row][col] = value;
        fireTableCellUpdated(row, col);
    }
}
