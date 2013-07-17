
package util;

import entities.User;
import entities.UserDL;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * A Class that contains cached items that don't change often.
 * 
 * @author ahughes
 */
public class Library {
    
    private static ArrayList<User> users;
    
    public static ArrayList<User> fetchUsers(UserDL aUserDL) throws SQLException {
        users = aUserDL.fetchUsers();
        
        return users;
    }

    public static ArrayList<User> getUsers() {
        return users;
    }
    
}
