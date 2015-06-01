# ddt
## Devious Database Testing

A library to make Database testing a little easier.  It currently comprises:
 * Prepared Statement Spy - this will wrap a prepared statement instance and spy 
   on the setter calls, to allow you to retrieve the SQL with the parameters 
   pre-formatted within the original SQL.  The intent is to allow access to the 
   SQL and params (in many implementations these are often obscured by the 
   driver) such that the SQL could just be taken and run against the database.  
   This class will add some overhead at runtime due to storing the parameters, 
   so you may only wish to use it when an exception is thrown, see example code:
   
   ```` java
   final PreparedStatement pstmt = Connection.prepareStatement(this.sql);
   try{
     decorateStatement(pstmt);
    }
    catch(SQLException e){
      System.err.println("Cannot execute SQL:");
      final PreparedStatement stmtSpy = new PreparedStatementSpy(pstmt);
      decorateStatement(stmtSpy);
      System.err.println("Problematic SQL:"+stmtSpy)
    }
   ````
   