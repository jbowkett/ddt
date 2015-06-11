package info.bowkett.ddt;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by jbowkett on 09/06/15.
 */
@RunWith(MockitoJUnitRunner.class)
public class SyntheticResultSetAnswerTest {

  private List<Row> rows;
  private SyntheticResultSetAnswer underTest;
  private Object answerFromMethodCall;

  @Before
  public void runBeforeEachTestCase(){
    rows = new ArrayList<>();
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

  private void given_AnAnswerUnderTest() {
    underTest = new SyntheticResultSetAnswer(rows.toArray(new Row[rows.size()]));
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
