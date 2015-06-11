package info.bowkett.ddt;

import org.hamcrest.Matcher;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

/**
 * Created by jbowkett on 01/06/15.
 */
public class DDTFixtureTest {

  private Connection connection;
  private ResultSet resultSet;
  private DDTFixture ddtFixture;

  @Test
  public void testTheResultSetCanBeIteratedAndAreReturnedInOrder() throws SQLException {
    given_AMockConnection();
    given_AMockResultSet();
    given_ADDTFixture();
    final Row[] rows = given_SomeMockRows();
    when_SetResultIsCalledWith(rows);
    then_TheResultSetCanBeIteratedAndRowsAreReturnedInOrder(rows);
  }

  private void then_TheResultSetCanBeIteratedAndRowsAreReturnedInOrder(Row[] rowsPassedToFixture) throws SQLException {
    int index = 0;
    boolean equivalent = true;
    boolean seenAtLeastOneRow = false;
    while(resultSet.next()){
      final int columnOne = resultSet.getInt(1);
      final int columnTwo = resultSet.getInt(2);
      final int columnThree = resultSet.getInt(3);
      final Row rowFromResultSet = new Row(columnOne, columnTwo, columnThree);
      final Row rowPassedToFixture = rowsPassedToFixture[index++];
      equivalent &= rowPassedToFixture.equals(rowFromResultSet);
      seenAtLeastOneRow = true;
    }
    assertTrue("Iterated ResultSet and fixture rows were not equivalent", equivalent && seenAtLeastOneRow);
  }

  private Row[] given_SomeMockRows() {
    return new Row[]{new Row(1, 2, 3), new Row(4, 5, 6)};
  }

  private void given_AMockConnection() {
    connection = mock(Connection.class);
  }

  private void given_AMockResultSet() {
    resultSet = mock(ResultSet.class);
  }

  private void given_ADDTFixture() {
    ddtFixture = new DDTFixture(connection, resultSet);
  }

  private void when_SetResultIsCalledWith(Row... rows) throws SQLException {
    ddtFixture.setResultSet(rows);
  }

}
