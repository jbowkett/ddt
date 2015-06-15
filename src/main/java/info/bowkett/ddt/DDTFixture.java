package info.bowkett.ddt;

import org.mockito.stubbing.Answer;

import java.sql.*;
import java.util.Calendar;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.mock;
import static org.powermock.api.mockito.PowerMockito.when;

/**
 * Created by jbowkett on 09/06/15.
 */
public class DDTFixture {
  private final ResultSet resultSet;
  private final Connection connection;

  public DDTFixture(Connection connection) throws SQLException {
    this(connection, mock(ResultSet.class));
  }
  
  DDTFixture(Connection connection, ResultSet resultSet){
    this.connection = connection;
    this.resultSet = resultSet;
  }
  
  public void setResultSet(Row... rows) throws SQLException {
    final PreparedStatement preparedStatement = mock(PreparedStatement.class);
    when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
    when(preparedStatement.executeQuery()).thenReturn(resultSet);
    final Answer answerFromResultSet = new SyntheticResultSetAnswer(rows);

    given(resultSet.next()).will(answerFromResultSet);
    given(resultSet.getArray(anyInt())).will(answerFromResultSet);
    given(resultSet.getAsciiStream(anyInt())).will(answerFromResultSet);
    given(resultSet.getBigDecimal(anyInt())).will(answerFromResultSet);
    given(resultSet.getBinaryStream(anyInt())).will(answerFromResultSet);
    given(resultSet.getBlob(anyInt())).will(answerFromResultSet);
    given(resultSet.getBoolean(anyInt())).will(answerFromResultSet);
    given(resultSet.getByte(anyInt())).will(answerFromResultSet);
    given(resultSet.getBytes(anyInt())).will(answerFromResultSet);
    given(resultSet.getCharacterStream(anyInt())).will(answerFromResultSet);
    given(resultSet.getClob(anyInt())).will(answerFromResultSet);
    given(resultSet.getDate(anyInt())).will(answerFromResultSet);
    given(resultSet.getDouble(anyInt())).will(answerFromResultSet);
    given(resultSet.getFloat(anyInt())).will(answerFromResultSet);
    given(resultSet.getInt(anyInt())).will(answerFromResultSet);
    given(resultSet.getLong(anyInt())).will(answerFromResultSet);
    given(resultSet.getNCharacterStream(anyInt())).will(answerFromResultSet);
    given(resultSet.getNClob(anyInt())).will(answerFromResultSet);
    given(resultSet.getNString(anyInt())).will(answerFromResultSet);
    given(resultSet.getObject(anyInt())).will(answerFromResultSet);
    given(resultSet.getObject(anyInt(), anyMap())).will(answerFromResultSet);
    given(resultSet.getObject(anyInt(), any(Class.class))).will(answerFromResultSet);
    given(resultSet.getRef(anyInt())).will(answerFromResultSet);
    given(resultSet.getRowId(anyInt())).will(answerFromResultSet);
    given(resultSet.getShort(anyInt())).will(answerFromResultSet);
    given(resultSet.getSQLXML(anyInt())).will(answerFromResultSet);
    given(resultSet.getString(anyInt())).will(answerFromResultSet);
    given(resultSet.getTime(anyInt())).will(answerFromResultSet);
    given(resultSet.getTime(anyInt(), any(Calendar.class))).will(answerFromResultSet);
    given(resultSet.getTimestamp(anyInt())).will(answerFromResultSet);
    given(resultSet.getTimestamp(anyInt(), any(Calendar.class))).will(answerFromResultSet);
    given(resultSet.getURL(anyInt())).will(answerFromResultSet);
    given(resultSet.getBigDecimal(anyInt(), anyInt())).will(answerFromResultSet);
    given(resultSet.getUnicodeStream(anyInt())).will(answerFromResultSet);
  }
}
