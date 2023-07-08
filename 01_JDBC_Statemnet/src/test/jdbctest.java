package src.test;

import java.sql.*; 

class Test
{ 
  public static void main (String args []) throws SQLException 
  { 
    DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
    Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.1.181:1521:ORA19", "scott", "tiger"); 
 
    Statement stmt = conn.createStatement ();
    ResultSet rset = stmt.executeQuery ("select '  --> ' || username from ALL_USERS");
    while (rset.next ()) {
      System.out.println (rset.getString (1));
    }
 
    rset.close();
    stmt.close();
    conn.close();
  }
}
