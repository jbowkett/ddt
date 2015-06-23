package info.bowkett.ddt;

import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jbowkett on 09/06/15.
 */
public class SyntheticResultSetAnswer implements Answer {

  private final Map<String, Integer> columnMap;
  private final Row[] rows;
  private int index = -1;
  private Row row = null;
  
  public SyntheticResultSetAnswer(Row[] rows){
    this(new HashMap<>(), rows);
  }
  
  public SyntheticResultSetAnswer(Map<String, Integer> columnMap, Row[] rows){
    this.columnMap = columnMap;
    this.rows = rows;
  }
  
  public Object answer(InvocationOnMock invocation) throws SQLException {
    final String methodName = invocation.getMethod().getName();
    final Object[] arguments = invocation.getArguments();
    return answer(methodName, arguments);
  }

  Object answer(String methodName, Object... arguments) throws SQLException {
    if(methodName.equals("next")){
      return moveNext();
    }
    else {
      throwIfNextWasNotCalled();
      return valueForRow(arguments[0]);
    }
  }

  private Object valueForRow(Object argument) throws SQLException {
    final int index;
    final boolean isGetterCallWithColumnIndexArgument = argument instanceof Integer;
    if(isGetterCallWithColumnIndexArgument) {
      index = (Integer) argument;
    }
    else{
      index = handleGetterCallWithColumnNameArgument((String) argument);
    }
    return row.getValue(index);
  }

  private int handleGetterCallWithColumnNameArgument(String argument) throws SQLException {
    final Integer jdbcIndex = columnMap.get(argument);
    if(jdbcIndex == null){
      throw new SQLException("Unknown named column : ["+argument+"]");
    }
    return jdbcIndex;
  }

  private Object moveNext() {
    index++;
    final boolean hasNext = index < rows.length;
    row = hasNext ?  rows[index] : null;
    return hasNext;
  }

  private void throwIfNextWasNotCalled() throws SQLException {
    if(row == null){
      throw new SQLException("Invalid state (must call next before iterating ResultSet)");
    }
  }
}
