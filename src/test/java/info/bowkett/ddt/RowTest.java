package info.bowkett.ddt;

import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;


/**
 * Created by jbowkett on 10/06/15.
 */
public class RowTest {

  private Row testSubject;
  private Object value;

  @Test
  public void testCallingGetString_1_WhenOnlyOneValueWillReturnTheFirstValue() throws SQLException {
    given_ARowContainingTheValues("VALUE");
    when_CallingGetValue(1);
    then_TheValueReturnedIs("VALUE");
  }

  @Test(expected = SQLException.class)
  public void testCallingGetString_0_WhenOnlyOneValueWillThrowAnSQLException() throws SQLException {
    given_ARowContainingTheValues("VALUE");
    when_CallingGetValue(0);
    then_AnSQLExceptionIsThrown();
  }

  @Test(expected = SQLException.class)
  public void testCallingGetString_2_WhenOnlyOneValueRaiseAnSQLException() throws SQLException {
    given_ARowContainingTheValues("VALUE");
    when_CallingGetValue(2);
    then_AnSQLExceptionIsThrown();
  }

  private void given_ARowContainingTheValues(Object... values) {
    testSubject = new Row(values);
  }

  private void when_CallingGetValue(int index) throws SQLException {
    value = testSubject.getValue(index);
  }

  private void then_AnSQLExceptionIsThrown() {

  }

  private void then_TheValueReturnedIs(Object expectedValue) {
    assertEquals(expectedValue, value);
  }
}
