/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.sql.*;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import sql.Connector;
import util.StrVal;

/**
 *
 * @author Alex Hughes
 */
public class TableParser {

    private static final int idCol = 0;
    private static int cols;
    private static Object[] rowData;
    private static String[] colNames;

    public static void fillTable(String aQuery, JTable aTable, Connector aConnector) throws SQLException {
        ((DefaultTableModel) aTable.getModel()).setRowCount(0);
        ((DefaultTableModel) aTable.getModel()).setColumnCount(0);

        ResultSet results = aConnector.sendQuery(aQuery);
        ResultSetMetaData metadata = results.getMetaData();

        cols = metadata.getColumnCount();

        rowData = new Object[cols];
        colNames = new String[cols];

        for (int i = 0; i < cols; i++) {
            colNames[i] = metadata.getColumnName(i + 1);
        }
        for (int i = 0; i < cols; i++) {
            ((DefaultTableModel) aTable.getModel()).addColumn(colNames[i]);
        }

        while (results.next()) {
            for (int i = 0; i < cols; i++) {
                if (metadata.getColumnName(i + 1).equalsIgnoreCase("DatePosted")) {
                    rowData[i] = StrVal.SqlStringToString(results.getDate(i + 1).toString());
                    rowData[i] += " || ";
                    rowData[i] += results.getTime(i + 1).toString();
                } else
                rowData[i] = results.getString(i + 1);
            }
            ((DefaultTableModel) aTable.getModel()).addRow(rowData);
        }
    }
}
