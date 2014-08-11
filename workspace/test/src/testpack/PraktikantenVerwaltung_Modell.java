package testpack;

import java.awt.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;

public class PraktikantenVerwaltung_Modell {
	
	
//	 public void main( String args[] )
//	  {
//	    connectToDatabase("jdbc:sqlite:test.db");
////	    String sql = "CREATE TABLE PRAKTIKANTEN " +
////                "(ID INT PRIMARY KEY     NOT NULL," +
////                " NAME           TEXT, " + 
////                " AGE            INT, " + 
////                " ADDRESS        CHAR(50), " + 
////                " SALARY         REAL)"; 
////	    createTable(sql);
//	    String sql = "INSERT INTO PRAKTIKANTEN (ID,NAME,AGE,ADDRESS,SALARY) " +
//                "VALUES (2, 'Paul', 32, 'California', 20000.00 );";
//	    insertUpdateDeleteTable(sql);
////	    sdasdasdadsadasd
//	    
//	  }
	
	 public Connection connectToDatabase(String d)
	  {
	    Connection c = null;
	    try {
	      Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection(d);
	      return c;
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
	    System.out.println("Datenbank verbunden");
	    return c;
	  }
	 
	 public void createTables(){
		    Connection c = null;
		    Statement stmt = null;
		    try {
		      c = connectToDatabase("jdbc:sqlite:PraktikantenDB.db");
		      stmt = c.createStatement();
		      String sql = "CREATE TABLE PRAKTIKANTEN " +
	                   "(ID TEXT," +
	                   " ANREDE TEXT, " + 
	                   " NN TEXT, " + 
	                   " VN TEXT, " + 
	                   " GB TEXT, " +
	                   " GO TEXT, " +
	                   " STR TEXT, " +
	                   " PLZ TEXT, " +
	                   " LAND TEXT, " +
	                   " TELE TEXT, " +
	                   " MAIL TEXT, " +
	                   " MOBIL TEXT, " +
	                   " HAUSNR TEXT, " +
	                   " ORT TEXT, " +
	                   " GNN TEXT, " +
	                   " GVN TEXT, " +
	                   " SCHULE TEXT, " +
	                   " SCHULFORM TEXT, " +
	                   " PARTNERS TEXT, " +
	                   " ANMERKSCHULE TEXT, " +
	                   " MIKI TEXT, " +
	                   " GRAD TEXT, " +
	                   " ANMERKPERSON TEXT, " +
	                   " STARTDATUM TEXT, " +
	                   " ENDDATUM TEXT, " +
	                   " STATUS TEXT, " +
	                   " ANMERKPRAKT TEXT, " +
	                   " ANSPR1 TEXT, " +
	                   " ANSPR2 TEXT, " +
	                   " ANSPR3 TEXT, " +
	                   " INFO TEXT, " +
	                   " EDIT TEXT)"; 
		      stmt.executeUpdate(sql);
		      sql = "CREATE TABLE ANSPRECHPARTNER " +
	                   "(ID TEXT," +
	                   " NN TEXT, " + 
	                   " VN TEXT, " + 
	                   " TELE TEXT, " + 
	                   " MAIL TEXT, " +
	                   " ABTEILUNG TEXT, " +
	                   " RNR TEXT, " +
	                   " ANMERKEINSATZORT TEXT, " +
	                   " INFO TEXT)";
		      stmt.executeUpdate(sql);
		      stmt.close();
		      c.close();
		    } catch ( Exception e ) {
		      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		      System.exit(0);
		    }
		    System.out.println("Tabelle erfolgreich erstellt");
	 }
	 
	 public void insertUpdateDeleteTable(String sql){
		 {
			    Connection c = null;
			    Statement stmt = null;
			    try {
			      c = connectToDatabase("jdbc:sqlite:PraktikantenDB.db");
			      c.setAutoCommit(false);
			      
			      stmt = c.createStatement();
			      stmt.executeUpdate(sql);
			      stmt.close();
			      c.commit();
			      c.close();
			    } catch ( Exception e ) {
			      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			      System.exit(0);
			    }
			    System.out.println("Daten erfolgreich eingefügt/geändert/gelöscht");
			  }

	 }
	 
	 public ArrayList<ArrayList<String>> getData(String sql){
		  {
			    Connection c = null;
			    Statement stmt = null;
			    ArrayList<ArrayList<String>> daten = new ArrayList<ArrayList<String>>();
			    try {
				  c = connectToDatabase("jdbc:sqlite:PraktikantenDB.db");
			      c.setAutoCommit(false);
			      
			      stmt = c.createStatement();
			      ResultSet rs = stmt.executeQuery(sql);
			      int numcols = rs.getMetaData().getColumnCount();
			      while (rs.next()) {
			          ArrayList<String> row = new ArrayList<>(numcols);
			          int i = 1;
			          while (i <= numcols) {
			              row.add(rs.getString(i++));
			          }
			          daten.add(row);
			      }
			      rs.close();
			      stmt.close();
			      c.close();
			    } catch ( Exception e ) {
			      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			      System.exit(0);
			    }
			    System.out.println("Daten geholt");
			    return daten;
			  }
	 }
}
