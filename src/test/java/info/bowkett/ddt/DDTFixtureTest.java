package info.bowkett.ddt;

import org.junit.Test;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

/**
 * Created by jbowkett on 01/06/15.
 */
public class DDTFixtureTest {

  private Connection connection;
  private ResultSet resultSet;
  private DDTFixture ddtFixture;
  private Row row;
  private Object resultOfGetterCall;

  @Test
  public void testTheResultSetCanBeIteratedAndAreReturnedInOrder() throws SQLException {
    given_AMockConnection();
    given_AMockResultSet();
    given_ADDTFixture();
    final Row[] rows = given_SomeMockRows();
    when_SetResultIsCalledWith(rows);
    then_TheResultSetCanBeIteratedAndRowsAreReturnedInOrder(rows);
  }
  
  @Test
  public void testAStatementObjectCanBeExecutedAndIteratedInOrder() throws SQLException {
    given_AMockConnection();
    given_AMockResultSet();
    given_ADDTFixtureForAStatement();
    final Row[] rows = given_SomeMockRows();
    when_SetResultIsCalledWith(rows);
    then_TheResultSetCanBeIteratedAndRowsAreReturnedInOrder(rows);
  }
  

  private void then_TheResultIs(Object value) {
    assertEquals(value, resultOfGetterCall);
  }

  private void given_ARowContainingTheValues(Object ... values) {
    row = new Row(values);
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
    ddtFixture = new DDTFixture(connection, resultSet, mock(PreparedStatement.class));
  }

  private void given_ADDTFixtureForAStatement() {
    ddtFixture = new DDTFixture(connection, resultSet, mock(Statement.class));
  }

  private void when_SetResultIsCalledWith(Row ... rows) throws SQLException {
    ddtFixture.setResultSet(rows);
  }
  
  /**************************************************************************/
    //public abstract java.lang.Object java.sql.ResultSet.getObject(int,java.util.Map) throws java.sql.SQLException
    @Test
    public void testTheCorrectResultIsReturnedFromGetObjectIntMap() throws SQLException {
      given_AMockConnection();
      given_AMockResultSet();
      given_ADDTFixture();
      final java.lang.Object mock = mock(java.lang.Object.class);
      given_ARowContainingTheValues(mock);
      when_SetResultIsCalledWith(row);
      when_GetObjectMapIsCalledOnColumn(1);
      then_TheResultIs(mock);
    }


    // IGNORING :: public abstract java.lang.Object java.sql.ResultSet.getObject(java.lang.String) throws java.sql.SQLException
    //public abstract java.lang.Object java.sql.ResultSet.getObject(int) throws java.sql.SQLException
    @Test
    public void testTheCorrectResultIsReturnedFromGetObjectInt() throws SQLException {
      given_AMockConnection();
      given_AMockResultSet();
      given_ADDTFixture();
      final java.lang.Object mock = mock(java.lang.Object.class);
      given_ARowContainingTheValues(mock);
      when_SetResultIsCalledWith(row);
      when_GetObjectIsCalledOnColumn(1);
      then_TheResultIs(mock);
    }


    // IGNORING :: public abstract java.lang.Object java.sql.ResultSet.getObject(java.lang.String,java.util.Map) throws java.sql.SQLException
    //public abstract java.lang.Object java.sql.ResultSet.getObject(int,java.lang.Class) throws java.sql.SQLException
    @Test
    public void testTheCorrectResultIsReturnedFromGetObjectIntClass() throws SQLException {
      given_AMockConnection();
      given_AMockResultSet();
      given_ADDTFixture();
      final java.lang.Object mock = mock(java.lang.Object.class);
      given_ARowContainingTheValues(mock);
      when_SetResultIsCalledWith(row);
      when_GetObjectClassIsCalledOnColumn(1);
      then_TheResultIs(mock);
    }


    // IGNORING :: public abstract java.lang.Object java.sql.ResultSet.getObject(java.lang.String,java.lang.Class) throws java.sql.SQLException
    //public abstract boolean java.sql.ResultSet.getBoolean(int) throws java.sql.SQLException
    @Test
    public void testTheCorrectResultIsReturnedFromGetBooleanInt() throws SQLException {
      given_AMockConnection();
      given_AMockResultSet();
      given_ADDTFixture();
      final boolean mock = true;
      given_ARowContainingTheValues(mock);
      when_SetResultIsCalledWith(row);
      when_GetBooleanIsCalledOnColumn(1);
      then_TheResultIs(mock);
    }


    // IGNORING :: public abstract boolean java.sql.ResultSet.getBoolean(java.lang.String) throws java.sql.SQLException
    // IGNORING :: public abstract byte java.sql.ResultSet.getByte(java.lang.String) throws java.sql.SQLException
    //public abstract byte java.sql.ResultSet.getByte(int) throws java.sql.SQLException
    @Test
    public void testTheCorrectResultIsReturnedFromGetByteInt() throws SQLException {
      given_AMockConnection();
      given_AMockResultSet();
      given_ADDTFixture();
      final byte mock = '3';
      given_ARowContainingTheValues(mock);
      when_SetResultIsCalledWith(row);
      when_GetByteIsCalledOnColumn(1);
      then_TheResultIs(mock);
    }


    //public abstract short java.sql.ResultSet.getShort(int) throws java.sql.SQLException
    @Test
    public void testTheCorrectResultIsReturnedFromGetShortInt() throws SQLException {
      given_AMockConnection();
      given_AMockResultSet();
      given_ADDTFixture();
      final short mock = 0;
      given_ARowContainingTheValues(mock);
      when_SetResultIsCalledWith(row);
      when_GetShortIsCalledOnColumn(1);
      then_TheResultIs(mock);
    }


    // IGNORING :: public abstract short java.sql.ResultSet.getShort(java.lang.String) throws java.sql.SQLException
    //public abstract int java.sql.ResultSet.getInt(int) throws java.sql.SQLException
    @Test
    public void testTheCorrectResultIsReturnedFromGetIntInt() throws SQLException {
      given_AMockConnection();
      given_AMockResultSet();
      given_ADDTFixture();
      final int mock = 42;
      given_ARowContainingTheValues(mock);
      when_SetResultIsCalledWith(row);
      when_GetIntIsCalledOnColumn(1);
      then_TheResultIs(mock);
    }


    // IGNORING :: public abstract int java.sql.ResultSet.getInt(java.lang.String) throws java.sql.SQLException
    // IGNORING :: public abstract long java.sql.ResultSet.getLong(java.lang.String) throws java.sql.SQLException
    //public abstract long java.sql.ResultSet.getLong(int) throws java.sql.SQLException
    @Test
    public void testTheCorrectResultIsReturnedFromGetLongInt() throws SQLException {
      given_AMockConnection();
      given_AMockResultSet();
      given_ADDTFixture();
      final long mock = 4200l;
      given_ARowContainingTheValues(mock);
      when_SetResultIsCalledWith(row);
      when_GetLongIsCalledOnColumn(1);
      then_TheResultIs(mock);
    }


    // IGNORING :: public abstract float java.sql.ResultSet.getFloat(java.lang.String) throws java.sql.SQLException
    //public abstract float java.sql.ResultSet.getFloat(int) throws java.sql.SQLException
    @Test
    public void testTheCorrectResultIsReturnedFromGetFloatint() throws SQLException {
      given_AMockConnection();
      given_AMockResultSet();
      given_ADDTFixture();
      final float mock = 3.1415927f;
      given_ARowContainingTheValues(mock);
      when_SetResultIsCalledWith(row);
      when_GetFloatIsCalledOnColumn(1);
      then_TheResultIs(mock);
    }


    // IGNORING :: public abstract double java.sql.ResultSet.getDouble(java.lang.String) throws java.sql.SQLException
    //public abstract double java.sql.ResultSet.getDouble(int) throws java.sql.SQLException
    @Test
    public void testTheCorrectResultIsReturnedFromGetDoubleInt() throws SQLException {
      given_AMockConnection();
      given_AMockResultSet();
      given_ADDTFixture();
      final double mock = 42.0d;
      given_ARowContainingTheValues(mock);
      when_SetResultIsCalledWith(row);
      when_GetDoubleIsCalledOnColumn(1);
      then_TheResultIs(mock);
    }


    //public abstract byte[] java.sql.ResultSet.getBytes(int) throws java.sql.SQLException
    @Test
    public void testTheCorrectResultIsReturnedFromGetBytesInt() throws SQLException {
      given_AMockConnection();
      given_AMockResultSet();
      given_ADDTFixture();
      final byte[] mock = new byte[]{'b'};
      given_ARowContainingTheValues(mock);
      when_SetResultIsCalledWith(row);
      when_GetBytesIsCalledOnColumn(1);
      then_TheResultIs(mock);
    }


    // IGNORING :: public abstract byte[] java.sql.ResultSet.getBytes(java.lang.String) throws java.sql.SQLException
    // IGNORING :: public abstract java.sql.Array java.sql.ResultSet.getArray(java.lang.String) throws java.sql.SQLException
    //public abstract java.sql.Array java.sql.ResultSet.getArray(int) throws java.sql.SQLException
    @Test
    public void testTheCorrectResultIsReturnedFromGetArrayInt() throws SQLException {
      given_AMockConnection();
      given_AMockResultSet();
      given_ADDTFixture();
      final java.sql.Array mock = mock(java.sql.Array.class);
      given_ARowContainingTheValues(mock);
      when_SetResultIsCalledWith(row);
      when_GetArrayIsCalledOnColumn(1);
      then_TheResultIs(mock);
    }


    // IGNORING :: public abstract java.net.URL java.sql.ResultSet.getURL(java.lang.String) throws java.sql.SQLException
    //public abstract java.net.URL java.sql.ResultSet.getURL(int) throws java.sql.SQLException
    @Test
    public void testTheCorrectResultIsReturnedFromGetURLInt() throws SQLException, MalformedURLException {
      given_AMockConnection();
      given_AMockResultSet();
      given_ADDTFixture();
      final java.net.URL mock = new URL("http", "example.com", "index.html");
      given_ARowContainingTheValues(mock);
      when_SetResultIsCalledWith(row);
      when_GetURLIsCalledOnColumn(1);
      then_TheResultIs(mock);
    }


    // IGNORING :: public abstract int java.sql.ResultSet.getType() throws java.sql.SQLException
    // IGNORING :: public abstract java.sql.Ref java.sql.ResultSet.getRef(java.lang.String) throws java.sql.SQLException
    //public abstract java.sql.Ref java.sql.ResultSet.getRef(int) throws java.sql.SQLException
    @Test
    public void testTheCorrectResultIsReturnedFromGetRefInt() throws SQLException {
      given_AMockConnection();
      given_AMockResultSet();
      given_ADDTFixture();
      final java.sql.Ref mock = mock(java.sql.Ref.class);
      given_ARowContainingTheValues(mock);
      when_SetResultIsCalledWith(row);
      when_GetRefIsCalledOnColumn(1);
      then_TheResultIs(mock);
    }


    // IGNORING :: public abstract java.sql.Timestamp java.sql.ResultSet.getTimestamp(java.lang.String,java.util.Calendar) throws java.sql.SQLException
    //public abstract java.sql.Timestamp java.sql.ResultSet.getTimestamp(int) throws java.sql.SQLException
    @Test
    public void testTheCorrectResultIsReturnedFromGetTimestampInt() throws SQLException {
      given_AMockConnection();
      given_AMockResultSet();
      given_ADDTFixture();
      final java.sql.Timestamp mock = mock(java.sql.Timestamp.class);
      given_ARowContainingTheValues(mock);
      when_SetResultIsCalledWith(row);
      when_GetTimestampIsCalledOnColumn(1);
      then_TheResultIs(mock);
    }


    //public abstract java.sql.Timestamp java.sql.ResultSet.getTimestamp(int,java.util.Calendar) throws java.sql.SQLException
    @Test
    public void testTheCorrectResultIsReturnedFromGetTimestampIntCalendar() throws SQLException {
      given_AMockConnection();
      given_AMockResultSet();
      given_ADDTFixture();
      final java.sql.Timestamp mock = mock(java.sql.Timestamp.class);
      given_ARowContainingTheValues(mock);
      when_SetResultIsCalledWith(row);
      when_GetTimestampCalendarIsCalledOnColumn(1);
      then_TheResultIs(mock);
    }


    // IGNORING :: public abstract java.sql.Timestamp java.sql.ResultSet.getTimestamp(java.lang.String) throws java.sql.SQLException
    // IGNORING :: public abstract java.lang.String java.sql.ResultSet.getString(java.lang.String) throws java.sql.SQLException
    //public abstract java.lang.String java.sql.ResultSet.getString(int) throws java.sql.SQLException
    @Test
    public void testTheCorrectResultIsReturnedFromGetStringInt() throws SQLException {
      given_AMockConnection();
      given_AMockResultSet();
      given_ADDTFixture();
      final java.lang.String mock = "java.lang.String";
      given_ARowContainingTheValues(mock);
      when_SetResultIsCalledWith(row);
      when_GetStringIsCalledOnColumn(1);
      then_TheResultIs(mock);
    }


    // IGNORING :: public abstract java.sql.ResultSetMetaData java.sql.ResultSet.getMetaData() throws java.sql.SQLException
    // IGNORING :: public abstract java.sql.SQLWarning java.sql.ResultSet.getWarnings() throws java.sql.SQLException
    // IGNORING :: public abstract int java.sql.ResultSet.getHoldability() throws java.sql.SQLException
    //public abstract java.math.BigDecimal java.sql.ResultSet.getBigDecimal(int,int) throws java.sql.SQLException
    @Test
    public void testTheCorrectResultIsReturnedFromGetBigDecimalIntInt() throws SQLException {
      given_AMockConnection();
      given_AMockResultSet();
      given_ADDTFixture();
      final java.math.BigDecimal mock = mock(java.math.BigDecimal.class);
      given_ARowContainingTheValues(mock);
      when_SetResultIsCalledWith(row);
      when_GetBigDecimalIntIntIsCalledOnColumn(1);
      then_TheResultIs(mock);
    }


    //public abstract java.math.BigDecimal java.sql.ResultSet.getBigDecimal(int) throws java.sql.SQLException
    @Test
    public void testTheCorrectResultIsReturnedFromGetBigDecimalInt() throws SQLException {
      given_AMockConnection();
      given_AMockResultSet();
      given_ADDTFixture();
      final java.math.BigDecimal mock = mock(java.math.BigDecimal.class);
      given_ARowContainingTheValues(mock);
      when_SetResultIsCalledWith(row);
      when_GetBigDecimalIsCalledOnColumn(1);
      then_TheResultIs(mock);
    }


    // IGNORING :: public abstract java.math.BigDecimal java.sql.ResultSet.getBigDecimal(java.lang.String) throws java.sql.SQLException
    // IGNORING :: public abstract java.math.BigDecimal java.sql.ResultSet.getBigDecimal(java.lang.String,int) throws java.sql.SQLException
    //public abstract java.io.InputStream java.sql.ResultSet.getAsciiStream(int) throws java.sql.SQLException
    @Test
    public void testTheCorrectResultIsReturnedFromGetAsciiStreamInt() throws SQLException {
      given_AMockConnection();
      given_AMockResultSet();
      given_ADDTFixture();
      final java.io.InputStream mock = mock(java.io.InputStream.class);
      given_ARowContainingTheValues(mock);
      when_SetResultIsCalledWith(row);
      when_GetAsciiStreamIsCalledOnColumn(1);
      then_TheResultIs(mock);
    }


    // IGNORING :: public abstract java.io.InputStream java.sql.ResultSet.getAsciiStream(java.lang.String) throws java.sql.SQLException
    // IGNORING :: public abstract java.io.InputStream java.sql.ResultSet.getUnicodeStream(java.lang.String) throws java.sql.SQLException
    //public abstract java.io.InputStream java.sql.ResultSet.getUnicodeStream(int) throws java.sql.SQLException
    @Test
    public void testTheCorrectResultIsReturnedFromGetUnicodeStreamInt() throws SQLException {
      given_AMockConnection();
      given_AMockResultSet();
      given_ADDTFixture();
      final java.io.InputStream mock = mock(java.io.InputStream.class);
      given_ARowContainingTheValues(mock);
      when_SetResultIsCalledWith(row);
      when_GetUnicodeStreamIsCalledOnColumn(1);
      then_TheResultIs(mock);
    }


    //public abstract java.io.InputStream java.sql.ResultSet.getBinaryStream(int) throws java.sql.SQLException
    @Test
    public void testTheCorrectResultIsReturnedFromGetBinaryStreamInt() throws SQLException {
      given_AMockConnection();
      given_AMockResultSet();
      given_ADDTFixture();
      final java.io.InputStream mock = mock(java.io.InputStream.class);
      given_ARowContainingTheValues(mock);
      when_SetResultIsCalledWith(row);
      when_GetBinaryStreamIsCalledOnColumn(1);
      then_TheResultIs(mock);
    }


    // IGNORING :: public abstract java.io.InputStream java.sql.ResultSet.getBinaryStream(java.lang.String) throws java.sql.SQLException
    // IGNORING :: public abstract java.lang.String java.sql.ResultSet.getCursorName() throws java.sql.SQLException
    //public abstract java.io.Reader java.sql.ResultSet.getCharacterStream(int) throws java.sql.SQLException
    @Test
    public void testTheCorrectResultIsReturnedFromGetCharacterStreamInt() throws SQLException {
      given_AMockConnection();
      given_AMockResultSet();
      given_ADDTFixture();
      final java.io.Reader mock = mock(java.io.Reader.class);
      given_ARowContainingTheValues(mock);
      when_SetResultIsCalledWith(row);
      when_GetCharacterStreamIsCalledOnColumn(1);
      then_TheResultIs(mock);
    }


    // IGNORING :: public abstract java.io.Reader java.sql.ResultSet.getCharacterStream(java.lang.String) throws java.sql.SQLException
    // IGNORING :: public abstract int java.sql.ResultSet.getRow() throws java.sql.SQLException
    // IGNORING :: public abstract int java.sql.ResultSet.getFetchDirection() throws java.sql.SQLException
    // IGNORING :: public abstract int java.sql.ResultSet.getFetchSize() throws java.sql.SQLException
    // IGNORING :: public abstract int java.sql.ResultSet.getConcurrency() throws java.sql.SQLException
    // IGNORING :: public abstract java.sql.Statement java.sql.ResultSet.getStatement() throws java.sql.SQLException
    // IGNORING :: public abstract java.sql.Blob java.sql.ResultSet.getBlob(java.lang.String) throws java.sql.SQLException
    //public abstract java.sql.Blob java.sql.ResultSet.getBlob(int) throws java.sql.SQLException
    @Test
    public void testTheCorrectResultIsReturnedFromGetBlobInt() throws SQLException {
      given_AMockConnection();
      given_AMockResultSet();
      given_ADDTFixture();
      final java.sql.Blob mock = mock(java.sql.Blob.class);
      given_ARowContainingTheValues(mock);
      when_SetResultIsCalledWith(row);
      when_GetBlobIsCalledOnColumn(1);
      then_TheResultIs(mock);
    }


    //public abstract java.sql.Clob java.sql.ResultSet.getClob(int) throws java.sql.SQLException
    @Test
    public void testTheCorrectResultIsReturnedFromGetClobInt() throws SQLException {
      given_AMockConnection();
      given_AMockResultSet();
      given_ADDTFixture();
      final java.sql.Clob mock = mock(java.sql.Clob.class);
      given_ARowContainingTheValues(mock);
      when_SetResultIsCalledWith(row);
      when_GetClobIsCalledOnColumn(1);
      then_TheResultIs(mock);
    }


    // IGNORING :: public abstract java.sql.Clob java.sql.ResultSet.getClob(java.lang.String) throws java.sql.SQLException
    // IGNORING :: public abstract java.sql.RowId java.sql.ResultSet.getRowId(java.lang.String) throws java.sql.SQLException
    //public abstract java.sql.RowId java.sql.ResultSet.getRowId(int) throws java.sql.SQLException
    @Test
    public void testTheCorrectResultIsReturnedFromGetRowIdInt() throws SQLException {
      given_AMockConnection();
      given_AMockResultSet();
      given_ADDTFixture();
      final java.sql.RowId mock = mock(java.sql.RowId.class);
      given_ARowContainingTheValues(mock);
      when_SetResultIsCalledWith(row);
      when_GetRowIdIsCalledOnColumn(1);
      then_TheResultIs(mock);
    }


    //public abstract java.sql.NClob java.sql.ResultSet.getNClob(int) throws java.sql.SQLException
    @Test
    public void testTheCorrectResultIsReturnedFromGetNClobInt() throws SQLException {
      given_AMockConnection();
      given_AMockResultSet();
      given_ADDTFixture();
      final java.sql.NClob mock = mock(java.sql.NClob.class);
      given_ARowContainingTheValues(mock);
      when_SetResultIsCalledWith(row);
      when_GetNClobIsCalledOnColumn(1);
      then_TheResultIs(mock);
    }


    // IGNORING :: public abstract java.sql.NClob java.sql.ResultSet.getNClob(java.lang.String) throws java.sql.SQLException
    //public abstract java.sql.SQLXML java.sql.ResultSet.getSQLXML(int) throws java.sql.SQLException
    @Test
    public void testTheCorrectResultIsReturnedFromGetSQLXMLInt() throws SQLException {
      given_AMockConnection();
      given_AMockResultSet();
      given_ADDTFixture();
      final java.sql.SQLXML mock = mock(java.sql.SQLXML.class);
      given_ARowContainingTheValues(mock);
      when_SetResultIsCalledWith(row);
      when_GetSQLXMLIsCalledOnColumn(1);
      then_TheResultIs(mock);
    }


    // IGNORING :: public abstract java.sql.SQLXML java.sql.ResultSet.getSQLXML(java.lang.String) throws java.sql.SQLException
    // IGNORING :: public abstract java.lang.String java.sql.ResultSet.getNString(java.lang.String) throws java.sql.SQLException
    //public abstract java.lang.String java.sql.ResultSet.getNString(int) throws java.sql.SQLException
    @Test
    public void testTheCorrectResultIsReturnedFromGetNStringInt() throws SQLException {
      given_AMockConnection();
      given_AMockResultSet();
      given_ADDTFixture();
      final java.lang.String mock = "java.lang.String";
      given_ARowContainingTheValues(mock);
      when_SetResultIsCalledWith(row);
      when_GetNStringIsCalledOnColumn(1);
      then_TheResultIs(mock);
    }


    //public abstract java.io.Reader java.sql.ResultSet.getNCharacterStream(int) throws java.sql.SQLException
    @Test
    public void testTheCorrectResultIsReturnedFromGetNCharacterStreamInt() throws SQLException {
      given_AMockConnection();
      given_AMockResultSet();
      given_ADDTFixture();
      final java.io.Reader mock = mock(java.io.Reader.class);
      given_ARowContainingTheValues(mock);
      when_SetResultIsCalledWith(row);
      when_GetNCharacterStreamIsCalledOnColumn(1);
      then_TheResultIs(mock);
    }


    // IGNORING :: public abstract java.io.Reader java.sql.ResultSet.getNCharacterStream(java.lang.String) throws java.sql.SQLException
    // IGNORING :: public abstract java.sql.Time java.sql.ResultSet.getTime(java.lang.String) throws java.sql.SQLException
    // IGNORING :: public abstract java.sql.Time java.sql.ResultSet.getTime(java.lang.String,java.util.Calendar) throws java.sql.SQLException
    //public abstract java.sql.Time java.sql.ResultSet.getTime(int,java.util.Calendar) throws java.sql.SQLException
    @Test
    public void testTheCorrectResultIsReturnedFromGetTimeIntCalendar() throws SQLException {
      given_AMockConnection();
      given_AMockResultSet();
      given_ADDTFixture();
      final java.sql.Time mock = mock(java.sql.Time.class);
      given_ARowContainingTheValues(mock);
      when_SetResultIsCalledWith(row);
      when_GetTimeIntCalendarIsCalledOnColumn(1);
      then_TheResultIs(mock);
    }


    //public abstract java.sql.Time java.sql.ResultSet.getTime(int) throws java.sql.SQLException
    @Test
    public void testTheCorrectResultIsReturnedFromGetTimeInt() throws SQLException {
      given_AMockConnection();
      given_AMockResultSet();
      given_ADDTFixture();
      final java.sql.Time mock = mock(java.sql.Time.class);
      given_ARowContainingTheValues(mock);
      when_SetResultIsCalledWith(row);
      when_GetTimeIsCalledOnColumn(1);
      then_TheResultIs(mock);
    }


    //public abstract java.sql.Date java.sql.ResultSet.getDate(int) throws java.sql.SQLException
    @Test
    public void testTheCorrectResultIsReturnedFromGetDateInt() throws SQLException {
      given_AMockConnection();
      given_AMockResultSet();
      given_ADDTFixture();
      final java.sql.Date mock = mock(java.sql.Date.class);
      given_ARowContainingTheValues(mock);
      when_SetResultIsCalledWith(row);
      when_GetDateIsCalledOnColumn(1);
      then_TheResultIs(mock);
    }


    // IGNORING :: public abstract java.sql.Date java.sql.ResultSet.getDate(java.lang.String) throws java.sql.SQLException
    // IGNORING :: public abstract java.sql.Date java.sql.ResultSet.getDate(java.lang.String,java.util.Calendar) throws java.sql.SQLException
    //public abstract java.sql.Date java.sql.ResultSet.getDate(int,java.util.Calendar) throws java.sql.SQLException
    @Test
    public void testTheCorrectResultIsReturnedFromGetDateIntCalendar() throws SQLException {
      given_AMockConnection();
      given_AMockResultSet();
      given_ADDTFixture();
      final java.sql.Date mock = mock(java.sql.Date.class);
      given_ARowContainingTheValues(mock);
      when_SetResultIsCalledWith(row);
      when_GetDateIsCalledOnColumn(1);
      then_TheResultIs(mock);
    }


    private void when_GetBooleanIsCalledOnColumn(int index) throws SQLException {
      while(resultSet.next()){
        resultOfGetterCall = resultSet.getBoolean(index);
      }
    }


    private void when_GetTimeIsCalledOnColumn(int index) throws SQLException {
      while(resultSet.next()){
        resultOfGetterCall = resultSet.getTime(index);
      }
    }

    private void when_GetTimeIntCalendarIsCalledOnColumn(int index) throws SQLException {
      while(resultSet.next()){
        resultOfGetterCall = resultSet.getTime(index, new GregorianCalendar());
      }
    }


    private void when_GetNStringIsCalledOnColumn(int index) throws SQLException {
      while(resultSet.next()){
        resultOfGetterCall = resultSet.getNString(index);
      }
    }


    private void when_GetClobIsCalledOnColumn(int index) throws SQLException {
      while(resultSet.next()){
        resultOfGetterCall = resultSet.getClob(index);
      }
    }


    private void when_GetShortIsCalledOnColumn(int index) throws SQLException {
      while(resultSet.next()){
        resultOfGetterCall = resultSet.getShort(index);
      }
    }


    private void when_GetNCharacterStreamIsCalledOnColumn(int index) throws SQLException {
      while(resultSet.next()){
        resultOfGetterCall = resultSet.getNCharacterStream(index);
      }
    }


    private void when_GetFloatIsCalledOnColumn(int index) throws SQLException {
      while(resultSet.next()){
        resultOfGetterCall = resultSet.getFloat(index);
      }
    }


    private void when_GetDateIsCalledOnColumn(int index) throws SQLException {
      while(resultSet.next()){
        resultOfGetterCall = resultSet.getDate(index);
      }
    }


    private void when_GetBlobIsCalledOnColumn(int index) throws SQLException {
      while(resultSet.next()){
        resultOfGetterCall = resultSet.getBlob(index);
      }
    }


    private void when_GetArrayIsCalledOnColumn(int index) throws SQLException {
      while(resultSet.next()){
        resultOfGetterCall = resultSet.getArray(index);
      }
    }


    private void when_GetRefIsCalledOnColumn(int index) throws SQLException {
      while(resultSet.next()){
        resultOfGetterCall = resultSet.getRef(index);
      }
    }


    private void when_GetStringIsCalledOnColumn(int index) throws SQLException {
      while(resultSet.next()){
        resultOfGetterCall = resultSet.getString(index);
      }
    }


    private void when_GetIntIsCalledOnColumn(int index) throws SQLException {
      while(resultSet.next()){
        resultOfGetterCall = resultSet.getInt(index);
      }
    }

    private void when_GetCharacterStreamIsCalledOnColumn(int index) throws SQLException {
      while(resultSet.next()){
        resultOfGetterCall = resultSet.getCharacterStream(index);
      }
    }


    private void when_GetSQLXMLIsCalledOnColumn(int index) throws SQLException {
      while(resultSet.next()){
        resultOfGetterCall = resultSet.getSQLXML(index);
      }
    }


    private void when_GetBytesIsCalledOnColumn(int index) throws SQLException {
      while(resultSet.next()){
        resultOfGetterCall = resultSet.getBytes(index);
      }
    }


    private void when_GetObjectIsCalledOnColumn(int index) throws SQLException {
      while(resultSet.next()){
        resultOfGetterCall = resultSet.getObject(index);
      }
    }
    private void when_GetObjectClassIsCalledOnColumn(int index) throws SQLException {
      while(resultSet.next()){
        resultOfGetterCall = resultSet.getObject(index, String.class);
      }
    }

  private void when_GetObjectMapIsCalledOnColumn(int index) throws SQLException {
    while(resultSet.next()){
      final Map<String, Class<?>> map = new HashMap<>();
      resultOfGetterCall = resultSet.getObject(index, map);
    }
  }


    private void when_GetByteIsCalledOnColumn(int index) throws SQLException {
      while(resultSet.next()){
        resultOfGetterCall = resultSet.getByte(index);
      }
    }


    private void when_GetLongIsCalledOnColumn(int index) throws SQLException {
      while(resultSet.next()){
        resultOfGetterCall = resultSet.getLong(index);
      }
    }


    private void when_GetNClobIsCalledOnColumn(int index) throws SQLException {
      while(resultSet.next()){
        resultOfGetterCall = resultSet.getNClob(index);
      }
    }


    private void when_GetAsciiStreamIsCalledOnColumn(int index) throws SQLException {
      while(resultSet.next()){
        resultOfGetterCall = resultSet.getAsciiStream(index);
      }
    }


    private void when_GetBigDecimalIsCalledOnColumn(int index) throws SQLException {
      while(resultSet.next()){
        resultOfGetterCall = resultSet.getBigDecimal(index);
      }
    }

    private void when_GetBigDecimalIntIntIsCalledOnColumn(int index) throws SQLException {
      while(resultSet.next()){
        final int scale = 5;
        resultOfGetterCall = resultSet.getBigDecimal(index, scale);
      }
    }


    private void when_GetBinaryStreamIsCalledOnColumn(int index) throws SQLException {
      while(resultSet.next()){
        resultOfGetterCall = resultSet.getBinaryStream(index);
      }
    }


    private void when_GetUnicodeStreamIsCalledOnColumn(int index) throws SQLException {
      while(resultSet.next()){
        resultOfGetterCall = resultSet.getUnicodeStream(index);
      }
    }


    private void when_GetDoubleIsCalledOnColumn(int index) throws SQLException {
      while(resultSet.next()){
        resultOfGetterCall = resultSet.getDouble(index);
      }
    }


    private void when_GetRowIdIsCalledOnColumn(int index) throws SQLException {
      while(resultSet.next()){
        resultOfGetterCall = resultSet.getRowId(index);
      }
    }


    private void when_GetTimestampIsCalledOnColumn(int index) throws SQLException {
      while(resultSet.next()){
        resultOfGetterCall = resultSet.getTimestamp(index);
      }
    }

    private void when_GetTimestampCalendarIsCalledOnColumn(int index) throws SQLException {
      while(resultSet.next()){
        resultOfGetterCall = resultSet.getTimestamp(index, new GregorianCalendar());
      }
    }


    private void when_GetURLIsCalledOnColumn(int index) throws SQLException {
      while(resultSet.next()){
        resultOfGetterCall = resultSet.getURL(index);
      }
    }



  /**************************************************************************/

//  @Test
  public void printTestMethodsForGetters(){
    final Method[] methods = ResultSet.class.getMethods();
    final Set<String> whenMethods = new HashSet<>();
    Arrays.stream(methods)
        .filter(m -> m.getName().startsWith("get"))
        .forEach((method) -> {


          final String methodName = method.getName();
          final String methodNameStartingWithCapital = Character.toUpperCase(methodName.charAt(0)) + methodName.substring(1);
          final String paramTypes =
              Arrays.stream(method.getParameterTypes())
                  .map(Class::getSimpleName)
                  .collect(Collectors.joining());
          if (!paramTypes.startsWith("int")) {
            System.out.println("  // IGNORING :: "+method);
          }
          else {
            System.out.println("  //"+method);
            final String testMethodName = methodNameStartingWithCapital + paramTypes;
            final String returnTypeName = method.getReturnType().getName();
            final String mockReturnType = "final " + returnTypeName + " mock = mock(" + returnTypeName + ".class);\n";
            final String testMethod = "" +
                "  @Test\n" +
                "  public void testTheCorrectResultIsReturnedFrom" + testMethodName + "() throws SQLException {\n" +
                "    given_AMockConnection();\n" +
                "    given_AMockResultSet();\n" +
                "    given_ADDTFixture();\n" +
                "    " + mockReturnType +
                "    given_ARowContainingTheValues(mock);\n" +
                "    when_SetResultIsCalledWith(row);\n" +
                "    when_" + methodNameStartingWithCapital + "IsCalledOnColumn(1);\n" +
                "    then_TheResultIs(mock);\n" +
                "  }\n\n";

            System.out.println(testMethod);

            final String whenMethod = "" +
                "  private void when_" + methodNameStartingWithCapital + "IsCalledOnColumn(int index) throws SQLException {\n" +
                "    while(resultSet.next()){\n" +
                "      resultOfGetterCall = resultSet." + methodName + "(index);\n" +
                "    }\n" +
                "  }\n\n";
            whenMethods.add(whenMethod);
          }
        });
    whenMethods.stream().forEach(System.out::println);
  }
}


