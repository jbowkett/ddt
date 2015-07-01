package info.bowkett.ddt;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import static org.junit.Assert.assertEquals;

/**
 * Created by jbowkett on 09/06/15.
 */
@RunWith(MockitoJUnitRunner.class)
public class SyntheticResultSetAnswerTest {

  private List<Row> rows;
  private SyntheticResultSetAnswer underTest;
  private Object answerFromMethodCall;
  private ConcurrentHashMap<String, Integer> columnMap;

  @Before
  public void runBeforeEachTestCase(){
    rows = new ArrayList<Row>();
    columnMap = new ConcurrentHashMap<String, Integer>();
  }
  

  @Test
  public void testCallingNextTwiceWhenOnlyTwoResultsWillYieldTrueEachTime() throws SQLException {
    given_ARowContainingTheValues("Row 1 VALUE");
    given_ARowContainingTheValues("Another Row VALUE");
    given_AnAnswerUnderTest();
    when_NextIsCalled();
    then_NextReturnsTrue();
    when_NextIsCalled();
    then_NextReturnsTrue();
  }

  @Test
  public void testCallingNextTwiceWhenOnlyOneResultWillYieldTrueThenFalse() throws SQLException {
    given_ARowContainingTheValues("Row 1 VALUE");
    given_AnAnswerUnderTest();
    when_NextIsCalled();
    when_NextIsCalled();
    then_NextReturnsFalse();
  }

  @Test(expected = SQLException.class)
  public void testCallingGetString_1_BeforeCallingNextWillRaiseAnSQLException() throws SQLException {
    given_AnAnswerUnderTest();
    when_CallingGetValue(1);
    then_AnSQLExceptionIsThrown();
  }

  @Test
  public void testCallingGetString_1_AfterCallingNextWillCallThroughToTheFirstRow() throws SQLException {
    given_ARowContainingTheValues("Row 1 VALUE");
    given_AnAnswerUnderTest();
    when_NextIsCalled();
    when_CallingGetValue(1);
    then_TheValueReturnedIs("Row 1 VALUE");
  }

  @Test
  public void testCallingGetString_name_AfterCallingNextWillCallThroughToTheFirstRowWhenAValidNameIsPresent() throws SQLException {
    given_ARowContainingTheValues("Row 1 VALUE");
    given_a_ColumnMapOf("name");
    given_AnAnswerUnderTestWithNamedColumns();
    when_NextIsCalled();
    when_CallingGetValue("name");
    then_TheValueReturnedIs("Row 1 VALUE");
  }

  @Test(expected=SQLException.class)
  public void testCallingGetString_name_AfterCallingNextWillCallThroughToTheFirstRowWhenNoValidNamesPresentWillThrow() throws SQLException {
    given_ARowContainingTheValues("Row 1 VALUE");
    given_AnAnswerUnderTestWithNamedColumns();
    when_NextIsCalled();
    when_CallingGetValue("name");
    then_AnSQLExceptionIsThrown();
  }

  private void given_a_ColumnMapOf(String... names) {
    for (int i = 0; i < names.length; i++) {
      final String name = names[i];
      final int jdbcColumnIndex = i+1;
      columnMap.put(name, jdbcColumnIndex);
    }
  }

  private void given_AnAnswerUnderTest() {
    underTest = new SyntheticResultSetAnswer(columnMap, rows.toArray(new Row[rows.size()]));
  }

  private void given_AnAnswerUnderTestWithNamedColumns() {
    underTest = new SyntheticResultSetAnswer(columnMap, rows.toArray(new Row[rows.size()]));
  }

  private void given_ARowContainingTheValues(Object... values) {
    rows.add(new Row(values));
  }

  private void when_NextIsCalled() throws SQLException {
    answerFromMethodCall = underTest.answer("next");
  }

  private void when_CallingGetValue(int index) throws SQLException {
    answerFromMethodCall = underTest.answer("getString", index);
  }

  private void when_CallingGetValue(String name) throws SQLException {
    answerFromMethodCall = underTest.answer("getString", name);
  }

  private void then_NextReturnsTrue() {
    assertEquals(true, answerFromMethodCall);
  }

  private void then_NextReturnsFalse() {
    assertEquals(false, answerFromMethodCall);
  }

  private void then_AnSQLExceptionIsThrown() {

  }

  private void then_TheValueReturnedIs(Object s) {
    assertEquals(s, answerFromMethodCall);
  }
}
