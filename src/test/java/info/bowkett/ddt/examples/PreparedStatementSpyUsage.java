package info.bowkett.ddt.examples;

import info.bowkett.ddt.PreparedStatementSpy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by jbowkett on 03/06/15.
 */
public class PreparedStatementSpyUsage {


  public static void main(String[] args) throws SQLException {
    final Connection connection = DriverManager.getConnection("some.url");
    final PreparedStatement pstmt = connection.prepareStatement(getSql());
    try{
      // decorateStatement is some method of yours that sets the SQL params on the PreparedStatement
      decorateStatement(pstmt);
     }
     catch(SQLException e){
       System.err.println("Cannot execute SQL:");
       final PreparedStatement stmtSpy = new PreparedStatementSpy(pstmt, getSql());
       decorateStatement(stmtSpy);
       System.err.println("Problematic SQL:"+stmtSpy);
     }
  }

  private static void decorateStatement(PreparedStatement pstmt) throws SQLException {
    pstmt.setString(1, "Some parameter value");
  }

  private static String getSql() {
    return "select * from example_table where column_1 = ?";
  }
}
