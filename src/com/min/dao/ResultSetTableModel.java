package com.min.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author PCName
 */
public class ResultSetTableModel extends AbstractTableModel {

    private final ResultSet rs;

    public ResultSetTableModel(ResultSet rs) {
        this.rs = rs;
    }

    public ResultSetTableModel(AbstractDao dao) throws SQLException {
        rs = dao.select();
    }
    
    @Override
    public String getColumnName(int column) {
        try {
            return rs.getMetaData().getColumnName(column + 1);
        } catch (SQLException ex) {
            return super.getColumnName(column);
        }
    }

    @Override
    public int getRowCount() {
        try {
            rs.last();
            return rs.getRow();
        } catch (SQLException ex) {
            return 0;
        }
    }

    @Override
    public int getColumnCount() {
        try {
            return rs.getMetaData().getColumnCount();
        } catch (SQLException ex) {
            return 0;
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        try {
            rs.absolute(rowIndex + 1);
            return rs.getObject(columnIndex + 1);
        } catch (SQLException ex) {
            return null;
        }
    }
}
