package info.bowkett.ddt;

import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by jbowkett on 09/06/15.
 */
public class SyntheticResultSetAnswer implements Answer {

  private final Row[] rows;
  private int index = -1;
  private Row row = null;
  
  public SyntheticResultSetAnswer(Row [] rows){
    this.rows = rows;
  }
  
  public Object answer(InvocationOnMock invocation) throws SQLException {
    final String methodName = invocation.getMethod().getName();
    final Object[] arguments = invocation.getArguments();
    return answer(methodName, arguments);
  }

  Object answer(String methodName, Object... arguments) throws SQLException {
    if(methodName.equals("next")){
      index++;
      final boolean hasNext = index < rows.length;
      row = hasNext ?  rows[index] : null;
      return hasNext;
    }
    else {
      throwIfNextWasNotCalled();
      final int index = (Integer)arguments[0];
      return row.getValue(index);
    }
  }

  private void throwIfNextWasNotCalled() throws SQLException {
    if(row == null){
      throw new SQLException("Invalid state (must call next before iterating ResultSet)");
    }
  }
}
