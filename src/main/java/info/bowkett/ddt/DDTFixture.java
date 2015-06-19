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
  private CallableStatement callableStatement;
  private Statement statement;
  private PreparedStatement preparedStatement;

  public DDTFixture(Connection connection, ResultSet resultSet, Statement statement) {
    this(connection, resultSet, mock(PreparedStatement.class), statement, mock(CallableStatement.class));
  }

  public DDTFixture(Connection connection, ResultSet resultSet, PreparedStatement preparedStatement) {
    this(connection, resultSet, preparedStatement, mock(Statement.class), mock(CallableStatement.class));
  }

  public DDTFixture(Connection connection, ResultSet resultSet, CallableStatement callableStatement) {
    this(connection, resultSet, mock(PreparedStatement.class), mock(Statement.class), callableStatement);
  }

  private DDTFixture(Connection connection, ResultSet resultSet, PreparedStatement preparedStatement, Statement statement, CallableStatement callableStatement) {
    this.connection = connection;
    this.resultSet = resultSet;
    this.preparedStatement = preparedStatement;
    this.statement = statement;
    this.callableStatement = callableStatement;
    wireInStatements();
  }

  public static DDTFixture forPreparedStatement(Connection connection) {
    return new DDTFixture(connection, mock(ResultSet.class), mock(PreparedStatement.class));
  }

  public static DDTFixture forStatement(Connection connection) {
    return new DDTFixture(connection, mock(ResultSet.class), mock(Statement.class));
  }

  public static DDTFixture forCallableStatement(Connection connection) {
    return new DDTFixture(connection, mock(ResultSet.class), mock(CallableStatement.class));
  }

  private void wireInStatements() {
    try {
      wireInStatement(connection, resultSet, statement);
      wireInPreparedStatement(connection, resultSet, preparedStatement);
      wireInCallableStatement(connection, resultSet, callableStatement);
    }
    catch (SQLException e) {
      e.printStackTrace();
      throw new RuntimeException("Cannot wire in Statement.  Please report this as a bug.");
    }
  }

  private void wireInPreparedStatement(Connection connection, ResultSet resultSet, PreparedStatement preparedStatement) throws SQLException {
    when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
    when(connection.prepareStatement(anyString(), anyInt())).thenReturn(preparedStatement);
    when(connection.prepareStatement(anyString(), anyInt(), anyInt())).thenReturn(preparedStatement);
    when(connection.prepareStatement(anyString(), anyInt(), anyInt(), anyInt())).thenReturn(preparedStatement);
    when(connection.prepareStatement(anyString(), any(new int[0].getClass()))).thenReturn(preparedStatement);
    when(connection.prepareStatement(anyString(), any(new String[0].getClass()))).thenReturn(preparedStatement);
    when(preparedStatement.executeQuery()).thenReturn(resultSet);
    when(preparedStatement.executeQuery(anyString())).thenReturn(resultSet);
  }

  private void wireInStatement(Connection connection, ResultSet resultSet, Statement statement) throws SQLException {
    when(connection.createStatement()).thenReturn(statement);
    when(connection.createStatement(anyInt(), anyInt())).thenReturn(statement);
    when(connection.createStatement(anyInt(), anyInt(), anyInt())).thenReturn(statement);
    when(statement.executeQuery(anyString())).thenReturn(resultSet);
  }

  private void wireInCallableStatement(Connection connection, ResultSet resultSet, CallableStatement callableStatement) throws SQLException {
    when(connection.prepareCall(anyString())).thenReturn(callableStatement);
    when(connection.prepareCall(anyString(), anyInt(), anyInt())).thenReturn(callableStatement);
    when(connection.prepareCall(anyString(), anyInt(), anyInt(), anyInt())).thenReturn(callableStatement);
    when(callableStatement.executeQuery()).thenReturn(resultSet);
    when(callableStatement.executeQuery(anyString())).thenReturn(resultSet);
  }

  public void setResultSet(Row... rows) throws SQLException {
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
