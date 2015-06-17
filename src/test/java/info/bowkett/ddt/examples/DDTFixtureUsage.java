package info.bowkett.ddt.examples;

import info.bowkett.ddt.DDTFixture;
import info.bowkett.ddt.Row;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.sql.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.powermock.api.mockito.PowerMockito.when;

import org.powermock.api.mockito.PowerMockito;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.core.classloader.annotations.PrepareForTest;

/**
 * Created by jbowkett on 03/06/15.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(DDTFixtureUsage.SomeClassThatReferencesDriverManager.class)
public class DDTFixtureUsage {

  private void given_theDDTBoilerplate() throws SQLException {
    final Connection connection = mock(Connection.class);
    PowerMockito.mockStatic(DriverManager.class);
    when(DriverManager.getConnection(anyString())).thenReturn(connection);
    ddtFixture = DDTFixture.forPreparedStatement(connection);
  }
  
  private SomeClassThatReferencesDriverManager testSubject;

  private DDTFixture ddtFixture;

  @Test
  public void testSomethingDoesSomethingWhenSomeKindOfResultIsReturnedFromTheDB() throws SQLException {
    given_theDDTBoilerplate();
    given_someObjectUnderTest();
    given_aResultSetWithTwoRecords();
    when_theDatabaseIsQueried();
    then_recordCountIsTwo();
    then_columnThreeTallyIs(84.0);
  }

  private void given_someObjectUnderTest() {
    testSubject = new SomeClassThatReferencesDriverManager();
  }

  private void given_aResultSetWithTwoRecords() throws SQLException {
    final Row row1 = new Row("A String", 3.1411d, 42);
    final Row row2 = new Row("String",   3.141d,  42);
    ddtFixture.setResultSet(row1, row2);
  }

  private void when_theDatabaseIsQueried() throws SQLException {
    testSubject.queryTheDatabaseAndDoSomething();
  }

  private void then_recordCountIsTwo() {
    assertEquals(2, testSubject.getRecordCount());
  }

  private void then_columnThreeTallyIs(double value) {
    assertEquals(value, testSubject.getColumnThreeTally(), 0.0);
  }

  public static class SomeClassThatReferencesDriverManager {
    private int recordCount = 0;
    private double columnThreeTally = 0.0;

    public void queryTheDatabaseAndDoSomething() throws SQLException {
      final Connection connection = DriverManager.getConnection("connection details");
      final PreparedStatement preparedStatement = connection.prepareStatement("select column_1, column_2, column_3 from some_table");
      final ResultSet rs = preparedStatement.executeQuery();
      while(rs.next()){
        final String columnOne = rs.getString(1);
        final int columnTwo = rs.getInt(2);
        final double columnThree = rs.getDouble(3);
        columnThreeTally += columnThree;
        System.out.println("columnOne = " + columnOne);
        System.out.println("columnTwo = " + columnTwo);
        System.out.println("columnThree = " + columnThree);
        recordCount++;
      }
    }
    public int getRecordCount(){
      return recordCount;
    }

    public double getColumnThreeTally() {
      return columnThreeTally;
    }
  }
}
