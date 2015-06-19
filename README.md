# ddt
## Devious Database Testing

A library to make Database testing a little easier.  It currently comprises:
 * A DDT Fixture to intercept calls to a database and insert test values without 
   the need for a database.
 * Prepared Statement Spy - this will wrap a prepared statement instance and spy
   on the setter calls, to allow you to retrieve the SQL with the parameters
   pre-formatted within the original SQL.  
   
   
### DDTFixture
The problem is that code of the form:
```` java
  final Connection connection = DriverManager.getConnection("connection details");
  final PreparedStatement preparedStatement = connection.prepareStatement("select column_1, column_2, column_3 from some_table");
  final ResultSet rs = preparedStatement.executeQuery();
  while(rs.next()){
    final String columnOne = rs.getString(1);
    final int columnTwo = rs.getInt(2);
    final double columnThree = rs.getDouble(3);
  }
````
Cannot be tested without database setup and tear down scripts, and are unlikely 
to be run outside of a CI environment (which may also become a little brittle to 
setup without some investment in automation). 

`DDTFixture` allows you to specify (with minimal boilerplate) values that should 
be iterated when a class under test calls through to the database using
`DriverManager`.  

There is an end-to-end example in `info.bowkett.ddt.examples.DDTFixtureUsage`
But at the bare minimum you specify:
 ```` java
    @RunWith(PowerMockRunner.class)
    @PrepareForTest(DDTFixtureUsage.SomeClassThatReferencesDriverManager.class)
 ````
 At the top of your unit test.
 Then at the start of your test case:
 ```` java
    final Connection connection = mock(Connection.class);
    PowerMockito.mockStatic(DriverManager.class);
    when(DriverManager.getConnection(anyString())).thenReturn(connection);
    ddtFixture = DDTFixture.forPreparedStatement(connection);
 ````
Then before your class under test queries the database/constructs its 
Statement/PreparedStatement/CallableStatement (all 3 are supported), use the 
following code:
 ```` java
    final Row row1 = new Row("A String", 3.1411d, 42);
    final Row row2 = new Row("String",   3.141d,  42);
    ddtFixture.setResultSet(row1, row2);
 ````

   
### PreparedStatementSpy   
The intent is to allow access to the
SQL and params (in many implementations these are often obscured by the
driver) such that the SQL could just be taken and run against the database.
This class will add some overhead at runtime due to storing the parameters,
so you may only wish to use it when an exception is thrown, see example code:

```` java
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
````

