package praktikantenverwaltung;

import javax.swing.table.AbstractTableModel;

/**
 * 
 * @author Barathum
 * Klasse die das Normale TableModel erweitert
 * n�tig f�r bessere Anpassung
 */
class DefaultTableModel_PraktikantenVerwaltung extends AbstractTableModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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

    public Class<? extends Object> getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }

    /*
     * Don't need to implement this method unless your table's
     * editable.
     */
    public boolean isCellEditable(int row, int col) {
        return false;
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
