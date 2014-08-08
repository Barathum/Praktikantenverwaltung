package testpack;

import java.awt.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;

public class PraktikantenVerwaltung_Modell {
	
	
//	 public static void main( String args[] )
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
	
	 public static Connection connectToDatabase(String d)
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
	 
	 public static void createTable(String sql){
		    Connection c = null;
		    Statement stmt = null;
		    try {
		      c = connectToDatabase("jdbc:sqlite:PraktikantenDB.db");
		      
		      stmt = c.createStatement();
		      stmt.executeUpdate(sql);
		      stmt.close();
		      c.close();
		    } catch ( Exception e ) {
		      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		      System.exit(0);
		    }
		    System.out.println("Tabelle erfolgreich erstellt");
	 }
	 
	 public static void insertUpdateDeleteTable(String sql){
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
	 
	 public void getData(String sql){
		  {
			    Connection c = null;
			    Statement stmt = null;
			    ArrayList<ArrayList<String>> daten = new ArrayList<ArrayList<String>>();
			    ArrayList<String> datensatz = new ArrayList<String>();
			    try {
				  c = connectToDatabase("jdbc:sqlite:PraktikantenDB.db");
			      c.setAutoCommit(false);
			      
			      stmt = c.createStatement();
			      ResultSet rs = stmt.executeQuery(sql);
			      ResultSetMetaData rsmd = rs.getMetaData();
			      while ( rs.next() ) {
			    	  for (int i = 0; i < rsmd.getColumnCount(); i++) {
				    	  datensatz.add(rs.getString(i));
					}
			    	  daten.add(datensatz);
			    	  datensatz.clear();
			      }
			      rs.close();
			      stmt.close();
			      c.close();
			    } catch ( Exception e ) {
			      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			      System.exit(0);
			    }
			    System.out.println("Daten geholt");
			  }
	 }
}
