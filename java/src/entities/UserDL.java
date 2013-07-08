package entities;

import sql.Connector;
import java.sql.*;
import java.util.ArrayList;
import util.StrVal;

/**
 * Class UserDL. This is the data layer class between the desktop application
 * and the database.
 *
 * @author Alex Hughes <alexhughes117@gmail.com>
 */
public class UserDL {

    private Connector c;
    private User u;
    private ArrayList<User> users;

    public UserDL(Connector c) {
        this.c = c;
    }

    /**
     * Fetches a particular user from the database by ID
     *
     * @return
     * @throws SQLException
     */
    public User fetchUser() throws SQLException {
        String query = ""
                + "SELECT * "
                + "FROM user "
                + "WHERE userID = ? ";

        PreparedStatement ps = c.prepareStatement(query);
        ps.setInt(1, u.getUserID());

        ResultSet userR = ps.executeQuery();
        u = resultSetToUsers(userR).get(0);

        return u;
    }

    /**
     * Fetches all the users from the database
     *
     * @return
     * @throws SQLException
     */
    public ArrayList<User> fetchUsers() throws SQLException {
        String query = ""
                + "SELECT * "
                + "FROM user ";

        ResultSet userR = c.sendQuery(query);
        users = resultSetToUsers(userR);

        return users;
    }

    /**
     * Inserts a new user in the database. Password hashing is done here, using
     * sha256, as in web app.
     *
     * @throws SQLException
     */
    public void insertUser() throws SQLException {
        String query = ""
                + "INSERT INTO user (Email, Password, Name, Surname, Type, DateCreated) VALUES "
                + "(?, ?, ?, ?, ?, CURRENT_TIMESTAMP) ";

        PreparedStatement ps = c.prepareStatement(query);
        
        ps.setString(1, u.getEmail());
        ps.setString(2, StrVal.sha256(u.getPassword()));
        ps.setString(3, u.getName());
        ps.setString(4, u.getSurname());

        if (u.getType().equals("admin")) {
            ps.setInt(5, 1);
        } else if (u.getType().equals("user")) {
            ps.setInt(5, 0);
        }
        ps.executeUpdate();
    }

    /**
     * Updates a user's details
     *
     * @throws SQLException
     */
    public void updateUser() throws SQLException {
        String query = ""
                + "UPDATE user "
                + "SET Email = ?, Password = ?, Name = ?, Surname = ?, Type = ? "
                + "WHERE userID = ? ";

        PreparedStatement ps = c.prepareStatement(query);
        ps.setString(1, u.getEmail());
        ps.setString(2, StrVal.sha256(u.getPassword()));
        ps.setString(3, u.getName());
        ps.setString(4, u.getSurname());

        if (u.getType().equals("admin")) {
            ps.setInt(5, 1);
        } else if (u.getType().equals("user")) {
            ps.setInt(5, 0);
        }

        ps.setInt(6, u.getUserID());
        ps.executeUpdate();
    }

    /**
     * Helper function that converts a User ResultSet to a User ArrayList
     *
     * @param userR
     * @return
     * @throws SQLException
     */
    public ArrayList<User> resultSetToUsers(ResultSet userR) throws SQLException {
        ArrayList<User> userL = new ArrayList();

        while (userR.next()) {
            User user = new User(
                    userR.getInt("userID"),
                    userR.getString("Email"),
                    userR.getString("Name"),
                    userR.getString("Surname"),
                    Integer.toString(userR.getInt("Type")),
                    userR.getTimestamp("DateCreated"),
                    userR.getTimestamp("LastLogin"),
                    userR.getString("LastIp"),
                    userR.getTimestamp("_dateModified"));
            userL.add(user);
        }
        return userL;
    }

    public User getU() {
        return u;
    }

    public void setU(User u) {
        this.u = u;
    }

    public ArrayList<User> getUsers() {
        return users;
    }
    
    
}
