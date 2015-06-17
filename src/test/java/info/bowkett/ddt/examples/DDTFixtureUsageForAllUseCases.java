package info.bowkett.ddt.examples;

import info.bowkett.ddt.DDTFixture;
import info.bowkett.ddt.Row;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.sql.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.powermock.api.mockito.PowerMockito.when;

/**
 * Created by jbowkett on 03/06/15.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(DDTFixtureUsageForAllUseCases.SomeClassThatReferencesDriverManager.class)
public class DDTFixtureUsageForAllUseCases {

  private Connection connection;
  private SomeClassThatReferencesDriverManager testSubject;
  private DDTFixture ddtFixture;

  private void given_theDDTBoilerplate() throws SQLException {
    connection = mock(Connection.class);
    PowerMockito.mockStatic(DriverManager.class);
    when(DriverManager.getConnection(anyString())).thenReturn(connection);
  }

  @Test
  public void testVanillaStatementsWithSimpleArgumentsAreIntercepted() throws SQLException {
    given_theDDTBoilerplate();
    given_aStatementFixture(connection);
    given_someObjectUnderTest();
    given_aResultSetWithTwoRecords();
    when_theDatabaseIsQueriedUsingAVanillaStatement();
    then_recordCountIsTwo();
    then_columnThreeTallyIs(84.0);
  }

  @Test
  public void testVanillaStatementsWithComplexArgumentsAreIntercepted() throws SQLException {
    given_theDDTBoilerplate();
    given_aStatementFixture(connection);
    given_someObjectUnderTest();
    given_aResultSetWithTwoRecords();
    when_theDatabaseIsQueriedUsingAVanillaStatementWithComplexArgs();
    then_recordCountIsTwo();
    then_columnThreeTallyIs(84.0);
  }

  @Test
  public void testVanillaStatementsWithHoldabilityArgumentsAreIntercepted() throws SQLException {
    given_theDDTBoilerplate();
    given_aStatementFixture(connection);
    given_someObjectUnderTest();
    given_aResultSetWithTwoRecords();
    when_theDatabaseIsQueriedUsingAVanillaStatementWithHoldabilityArgs();
    then_recordCountIsTwo();
    then_columnThreeTallyIs(84.0);
  }

  @Test
  public void testPreparedStatementsAreIntercepted() throws SQLException {
    given_theDDTBoilerplate();
    given_aPreparedStatementFixture(connection);
    given_someObjectUnderTest();
    given_aResultSetWithTwoRecords();
    when_theDatabaseIsQueriedUsingASimplePreparedStatement();
    then_recordCountIsTwo();
    then_columnThreeTallyIs(84.0);
  }

  @Test
  public void testPreparedStatementsWithResultSetTypeAndResultSetConcurrencyAreIntercepted() throws SQLException {
    given_theDDTBoilerplate();
    given_aPreparedStatementFixture(connection);
    given_someObjectUnderTest();
    given_aResultSetWithTwoRecords();
    when_theDatabaseIsQueriedUsingAPreparedStatementWithResultSetTypeAndResultSetConcurrency();
    then_recordCountIsTwo();
    then_columnThreeTallyIs(84.0);
  }

  @Test
  public void testPreparedStatementsWithResultSetTypeResultSetConcurrencyAndResultSetHoldabilityAreIntercepted() throws SQLException {
    given_theDDTBoilerplate();
    given_aPreparedStatementFixture(connection);
    given_someObjectUnderTest();
    given_aResultSetWithTwoRecords();
    when_theDatabaseIsQueriedUsingAPreparedStatementWithResultSetTypeResultSetConcurrencyAndResultSetHoldability();
    then_recordCountIsTwo();
    then_columnThreeTallyIs(84.0);
  }

  @Test
  public void testPreparedStatementsWithReturnGeneratedKeysAreIntercepted() throws SQLException {
    given_theDDTBoilerplate();
    given_aPreparedStatementFixture(connection);
    given_someObjectUnderTest();
    given_aResultSetWithTwoRecords();
    when_theDatabaseIsQueriedUsingAPreparedStatementWithReturnGeneratedKeys();
    then_recordCountIsTwo();
    then_columnThreeTallyIs(84.0);
  }

  @Test
  public void testPreparedStatementsWithIntArrayAreIntercepted() throws SQLException {
    given_theDDTBoilerplate();
    given_aPreparedStatementFixture(connection);
    given_someObjectUnderTest();
    given_aResultSetWithTwoRecords();
    when_theDatabaseIsQueriedUsingAPreparedStatementWithIntArray();
    then_recordCountIsTwo();
    then_columnThreeTallyIs(84.0);
  }

  @Test
  public void testPreparedStatementsWithStringArrayAreIntercepted() throws SQLException {
    given_theDDTBoilerplate();
    given_aPreparedStatementFixture(connection);
    given_someObjectUnderTest();
    given_aResultSetWithTwoRecords();
    when_theDatabaseIsQueriedUsingAPreparedStatementWithStringArray();
    then_recordCountIsTwo();
    then_columnThreeTallyIs(84.0);
  }

  @Test
  public void testCallableStatementsNoArgsAreIntercepted() throws SQLException {
    given_theDDTBoilerplate();
    given_aCallableStatementFixture(connection);
    given_someObjectUnderTest();
    given_aResultSetWithTwoRecords();
    when_theDatabaseIsQueriedUsingAVanillaCallableStatement();
    then_recordCountIsTwo();
    then_columnThreeTallyIs(84.0);
  }

  @Test
  public void testCallableStatementsNoArgsAreInterceptedWithScrollTypeAndConcurrencyType() throws SQLException {
    given_theDDTBoilerplate();
    given_aCallableStatementFixture(connection);
    given_someObjectUnderTest();
    given_aResultSetWithTwoRecords();
    when_theDatabaseIsQueriedUsingACallableStatementWithScrollTypeAndConcurrencyType();
    then_recordCountIsTwo();
    then_columnThreeTallyIs(84.0);
  }

  @Test
  public void testCallableStatementsNoArgsAreInterceptedWithScrollTypeConcurrencyTypeAndCommitType() throws SQLException {
    given_theDDTBoilerplate();
    given_aCallableStatementFixture(connection);
    given_someObjectUnderTest();
    given_aResultSetWithTwoRecords();
    when_theDatabaseIsQueriedUsingACallableStatementWithScrollTypeConcurrencyTypeAndCommitType();
    then_recordCountIsTwo();
    then_columnThreeTallyIs(84.0);
  }

  private void given_aStatementFixture(Connection connection) {
    ddtFixture = DDTFixture.forStatement(connection);
  }

  private void given_aPreparedStatementFixture(Connection connection) {
    ddtFixture = DDTFixture.forPreparedStatement(connection);
  }

  private void given_aCallableStatementFixture(Connection connection) {
    ddtFixture = DDTFixture.forCallableStatement(connection);
  }

  private void given_someObjectUnderTest() throws SQLException {
    testSubject = new SomeClassThatReferencesDriverManager();
  }

  private void given_aResultSetWithTwoRecords() throws SQLException {
    final Row row1 = new Row("A String", 3.1411d, 42);
    final Row row2 = new Row("String", 3.141d, 42);
    ddtFixture.setResultSet(row1, row2);
  }

  private void when_theDatabaseIsQueriedUsingAVanillaStatement() throws SQLException {
    testSubject.queryTheDatabaseAndDoSomethingUsingAVanillaStatement();
  }

  private void when_theDatabaseIsQueriedUsingAVanillaStatementWithComplexArgs() throws SQLException {
    testSubject.queryTheDatabaseAndDoSomethingUsingAVanillaStatementWithComplexArgs();
  }

  private void when_theDatabaseIsQueriedUsingAVanillaStatementWithHoldabilityArgs() throws SQLException {
    testSubject.queryTheDatabaseAndDoSomethingUsingAVanillaStatementWithHoldabilityArgs();
  }

  private void when_theDatabaseIsQueriedUsingASimplePreparedStatement() throws SQLException {
    testSubject.queryTheDatabaseAndDoSomethingUsingASimplePreparedStatement();
  }

  private void when_theDatabaseIsQueriedUsingAPreparedStatementWithResultSetTypeAndResultSetConcurrency() throws SQLException {
    testSubject.queryTheDatabaseAndDoSomethingUsingAPreparedStatementWithResultSetTypeAndResultSetConcurrency();
  }

  private void when_theDatabaseIsQueriedUsingAPreparedStatementWithResultSetTypeResultSetConcurrencyAndResultSetHoldability() throws SQLException {
    testSubject.queryTheDatabaseAndDoSomethingUsingAPreparedStatementWithResultSetTypeResultSetConcurrencyAndResultSetHoldability();
  }

  private void when_theDatabaseIsQueriedUsingAPreparedStatementWithReturnGeneratedKeys() throws SQLException {
    testSubject.queryTheDatabaseAndDoSomethingUsingAPreparedStatementWithReturnGeneratedKeys();
  }

  private void when_theDatabaseIsQueriedUsingAPreparedStatementWithIntArray() throws SQLException {
    testSubject.queryTheDatabaseAndDoSomethingUsingAPreparedStatementWithIntArray();
  }

  private void when_theDatabaseIsQueriedUsingAPreparedStatementWithStringArray() throws SQLException {
    testSubject.queryTheDatabaseAndDoSomethingUsingAPreparedStatementWithStringArray();
  }

  public void when_theDatabaseIsQueriedUsingAVanillaCallableStatement() throws SQLException {
    testSubject.queryTheDatabaseAndDoSomethingUsingACallableStatement();
  }

  public void when_theDatabaseIsQueriedUsingACallableStatementWithScrollTypeAndConcurrencyType() throws SQLException {
    testSubject.queryTheDatabaseAndDoSomethingUsingACallableStatementWithScrollTypeAndConcurrencyType();
  }

  public void when_theDatabaseIsQueriedUsingACallableStatementWithScrollTypeConcurrencyTypeAndCommitType() throws SQLException {
    testSubject.queryTheDatabaseAndDoSomethingUsingACallableStatementWithScrollTypeConcurrencyTypeAndCommitType();
  }


  private void then_recordCountIsTwo() {
    assertEquals(2, testSubject.getRecordCount());
  }

  private void then_columnThreeTallyIs(double value) {
    assertEquals(value, testSubject.getColumnThreeTally(), 0.0);
  }

  public static class SomeClassThatReferencesDriverManager {
    private final Connection connection;
    private int recordCount = 0;
    private double columnThreeTally = 0.0;

    public SomeClassThatReferencesDriverManager() throws SQLException {
      connection = DriverManager.getConnection("connection details");
    }

    public void queryTheDatabaseAndDoSomethingUsingAVanillaStatement() throws SQLException {
      final Statement statement = connection.createStatement();
      final ResultSet rs = statement.executeQuery("select column_1, column_2, column_3 from some_table");
      iterateResults(rs);
    }

    public void queryTheDatabaseAndDoSomethingUsingAVanillaStatementWithComplexArgs() throws SQLException {
      final Statement statement = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
      final ResultSet rs = statement.executeQuery("select column_1, column_2, column_3 from some_table");
      iterateResults(rs);
    }

    public void queryTheDatabaseAndDoSomethingUsingAVanillaStatementWithHoldabilityArgs() throws SQLException {
      final Statement statement = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY, ResultSet.HOLD_CURSORS_OVER_COMMIT);
      final ResultSet rs = statement.executeQuery("select column_1, column_2, column_3 from some_table");
      iterateResults(rs);
    }

    public void queryTheDatabaseAndDoSomethingUsingASimplePreparedStatement() throws SQLException {
      final PreparedStatement statement = connection.prepareStatement("select column_1, column_2, column_3 from some_table where x = ?");
      statement.setString(1, "some arg");
      final ResultSet rs = statement.executeQuery();
      iterateResults(rs);
    }

    public void queryTheDatabaseAndDoSomethingUsingAPreparedStatementWithResultSetTypeAndResultSetConcurrency() throws SQLException {
      final PreparedStatement statement = connection.prepareStatement("select column_1, column_2, column_3 from some_table where x = ?", ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
      statement.setString(1, "some arg");
      final ResultSet rs = statement.executeQuery();
      iterateResults(rs);
    }

    public void queryTheDatabaseAndDoSomethingUsingAPreparedStatementWithResultSetTypeResultSetConcurrencyAndResultSetHoldability() throws SQLException {
      final PreparedStatement statement = connection.prepareStatement("select column_1, column_2, column_3 from some_table where x = ?", ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY, ResultSet.HOLD_CURSORS_OVER_COMMIT);
      statement.setString(1, "some arg");
      final ResultSet rs = statement.executeQuery();
      iterateResults(rs);
    }

    public void queryTheDatabaseAndDoSomethingUsingAPreparedStatementWithReturnGeneratedKeys() throws SQLException {
      final PreparedStatement statement = connection.prepareStatement("select column_1, column_2, column_3 from some_table where x = ?", Statement.RETURN_GENERATED_KEYS);
      statement.setString(1, "some arg");
      final ResultSet rs = statement.executeQuery();
      iterateResults(rs);
    }

    public void queryTheDatabaseAndDoSomethingUsingAPreparedStatementWithIntArray() throws SQLException {
      final PreparedStatement statement = connection.prepareStatement("select column_1, column_2, column_3 from some_table where x = ?", new int[10]);
      statement.setString(1, "some arg");
      final ResultSet rs = statement.executeQuery();
      iterateResults(rs);
    }

    public void queryTheDatabaseAndDoSomethingUsingAPreparedStatementWithStringArray() throws SQLException {
      final PreparedStatement statement = connection.prepareStatement("select column_1, column_2, column_3 from some_table where x = ?", new String[10]);
      statement.setString(1, "some arg");
      final ResultSet rs = statement.executeQuery();
      iterateResults(rs);
    }

    public void queryTheDatabaseAndDoSomethingUsingACallableStatement() throws SQLException {
      final CallableStatement statement = connection.prepareCall("select column_1, column_2, column_3 from some_table where x = ?");
      statement.setString(1, "some arg");
      final ResultSet rs = statement.executeQuery();
      iterateResults(rs);
    }

    public void queryTheDatabaseAndDoSomethingUsingACallableStatementWithScrollTypeAndConcurrencyType() throws SQLException {
      final CallableStatement statement = connection.prepareCall("select column_1, column_2, column_3 from some_table where x = ?", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
      statement.setString(1, "some arg");
      final ResultSet rs = statement.executeQuery();
      iterateResults(rs);
    }

    public void queryTheDatabaseAndDoSomethingUsingACallableStatementWithScrollTypeConcurrencyTypeAndCommitType() throws SQLException {
      final CallableStatement statement = connection.prepareCall("select column_1, column_2, column_3 from some_table where x = ?", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.CLOSE_CURSORS_AT_COMMIT);
      statement.setString(1, "some arg");
      final ResultSet rs = statement.executeQuery();
      iterateResults(rs);
    }

    private void iterateResults(ResultSet rs) throws SQLException {
      while (rs.next()) {
        final String columnOne = rs.getString(1);
        final int columnTwo = rs.getInt(2);
        final double columnThree = rs.getDouble(3);
        columnThreeTally += columnThree;
        recordCount++;
      }
    }

    public int getRecordCount() {
      return recordCount;
    }

    public double getColumnThreeTally() {
      return columnThreeTally;
    }
  }
}
