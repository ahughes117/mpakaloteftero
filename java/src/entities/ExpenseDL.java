package entities;

import sql.Connector;
import java.sql.*;
import java.util.ArrayList;
import util.StrVal;

/**
 * Class ExpenseDL. This is the data layer class between the desktop application
 * and the database.
 *
 * @author Alex Hughes <alexhughes117@gmail.com>
 */
public class ExpenseDL {

    private Connector c;
    private Expense e;
    private ArrayList<Expense> expenses;

    public ExpenseDL(Connector c) {
        this.c = c;
    }

    /**
     * Fetches a particular expense by ID
     *
     * @return
     * @throws SQLException
     */
    public Expense fetchExpense() throws SQLException {
        String query = ""
                + "SELECT * "
                + "FROM expense "
                + "WHERE expenseID = ? ";

        PreparedStatement ps = c.prepareStatement(query);
        ps.setInt(1, e.getExpenseID());

        ResultSet expenseR = ps.executeQuery();
        e = resultSetToExpenses(expenseR).get(0);

        return e;
    }

    /**
     * Searches the expenses based on multiple parameters. It does not make use
     * of prepared statements because it would be a pain in the ass. Try to do
     * some minimal validation of strings before this layer to avoid unexpected
     * behavior.
     *
     * There is no fear of SQL injection, as the user is already authorised with
     * FULL credentials to the database.
     *
     * @return
     * @throws SQLException
     */
    public ArrayList<Expense> searchExpenses() throws SQLException {
        String query = ""
                + "SELECT * "
                + "FROM expense "
                + "WHERE 1=1 ";

        if (e.getDebiterID() != e.NIL) {
            query += " AND debiterID = " + e.getDebiterID();
        }

        if (e.getCrediterID() != e.NIL) {
            query += " AND crediterID = " + e.getCrediterID();
        }

        if (e.getName() != null || !e.getName().equals("")) {
            query += " AND Name LIKE '%" + e.getName() + "%' ";
        }

        if (e.getDesc() != null || !e.getDesc().equals("")) {
            query += " AND Description LIKE '%" + e.getDesc() + "%' ";
        }

        //yes, price is checked "slack-style"
        if (e.getPrice() != e.NIL) {
            double ceiling = e.getPrice() + 20;
            double floor = e.getPrice() - 20;
            query += " AND (Price <= " + ceiling + " OR Price >= " + floor + ") ";
        }

        if (e.isPaid()) {
            query += " AND Paid = 1 ";
        } else {
            query += " AND Paid = 0 ";
        }

        if (e.isPaidRequest()) {
            query += " AND PaidRequest = 1 ";
        } else {
            query += " AND PaidRequest = 0 ";
        }

        //this is lame, need to figure out a way to do it like the price but it's late.
        if (e.getDateCreated() != null) {
            query += " AND DateCreated = '" + e.getDateCreated().toString() + "' ";
        }

        return expenses;
    }

    /**
     * Helper function that converts an Expense ResultSet to an Expense
     * ArrayList
     *
     * @param expenseR
     * @return
     * @throws SQLException
     */
    private ArrayList<Expense> resultSetToExpenses(ResultSet expenseR) throws SQLException {
        ArrayList<Expense> expenseL = new ArrayList();

        while (expenseR.next()) {
            Expense expense = new Expense(
                    expenseR.getInt("expenseID"),
                    expenseR.getInt("debiterID"),
                    expenseR.getInt("crediterID"),
                    expenseR.getString("Name"),
                    expenseR.getString("Description"),
                    expenseR.getDouble("Price"),
                    StrVal.intToBool(expenseR.getInt("Paid")),
                    StrVal.intToBool(expenseR.getInt("PaidRequest")),
                    expenseR.getTimestamp("DateCreated"),
                    expenseR.getTimestamp("_dateModified"));
            expenseL.add(expense);
        }
        return expenseL;
    }

    /**
     * Inserts a new expense in the database
     *
     * @throws SQLException
     */
    public void insertExpense() throws SQLException {
        String query = ""
                + "INSERT INTO expense (debiterID, crediterID, Name, "
                + "Description, Price, Paid, PaidRequest, DateCreated) VALUES "
                + "(?, ?, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP) ";

        PreparedStatement ps = c.prepareStatement(query);

        ps.setInt(1, e.getDebiterID());
        ps.setInt(2, e.getCrediterID());
        ps.setString(3, e.getName());
        ps.setString(4, e.getDesc());
        ps.setDouble(5, e.getPrice());
        ps.setInt(6, StrVal.boolToInt(e.isPaid()));
        ps.setInt(7, StrVal.boolToInt(e.isPaidRequest()));

        ps.executeUpdate();

    }

    /**
     * Updates an expense in the database
     *
     * @throws SQLException
     */
    public void updateExpense() throws SQLException {
        String query = ""
                + "UPDATE expense "
                + "SET Name = ?, Description = ?, Price = ?, Paid = ?, "
                + "PaidRequest = ?, debiterID = ?, crediterID = ? "
                + "WHERE expenseID = ? ";

        PreparedStatement ps = c.prepareStatement(query);

        ps.setString(1, e.getName());
        ps.setString(2, e.getDesc());
        ps.setDouble(3, e.getPrice());
        ps.setInt(4, StrVal.boolToInt(e.isPaid()));
        ps.setInt(5, StrVal.boolToInt(e.isPaidRequest()));
        ps.setInt(6, e.getDebiterID());
        ps.setInt(7, e.getCrediterID());
        ps.setInt(8, e.getExpenseID());

        ps.executeUpdate();
    }

    /**
     * Deletes an expense from the database
     *
     * @throws SQLException
     */
    public void deleteExpense() throws SQLException {
        String query = ""
                + "DELETE "
                + "FROM expense "
                + "WHERE expenseID = ? ";

        PreparedStatement ps = c.prepareStatement(query);
        ps.setInt(1, e.getExpenseID());
        ps.executeUpdate();
    }

    public void setE(Expense e) {
        this.e = e;
    }

    public ArrayList<Expense> getExpenses() {
        return expenses;
    }
}
