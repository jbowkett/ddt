package info.bowkett.ddt;

import java.sql.SQLException;
import java.util.Arrays;

/**
 * Created by jbowkett on 08/06/15.
 */
public class Row {
  private Object[] values;

  public Row(Object... values) {
    this.values = values;
  }

  public Object getValue(int jdbcResultColumnIndex) throws SQLException {
    final int arrayIndex = jdbcResultColumnIndex - 1;
    throwIfNotInBounds(arrayIndex);
    return values[arrayIndex];
  }

  private void throwIfNotInBounds(int arrayIndex) throws SQLException {
    if(arrayIndex < 0 || arrayIndex >= values.length){
      throw new SQLException("Invalid column index.  (Valid value for getValue are 1 through to " + values.length + " Inclusive.)");
    }
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) return true;
    if (other == null || getClass() != other.getClass()) return false;

    final Row otherRow = (Row) other;

    return Arrays.equals(values, otherRow.values);
  }

  @Override
  public int hashCode() {
    return Arrays.hashCode(values);
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("Row[");
    sb.append(Arrays.toString(values)).append(']');
    return sb.toString();
  }
}
