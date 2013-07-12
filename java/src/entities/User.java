
package entities;

import java.sql.Timestamp;

/**
 * Class User
 * The entity class for the user
 * 
 * @author Alex Hughes <alexhughes117@gmail.com>
 */
public class User {
    
    private int userID;
    private String email;
    private String password;
    private String name;
    private String surname;
    private String type;
    private Timestamp dateCreated;
    private Timestamp lastLogin;
    private String lastIp;
    private Timestamp dateModified;

    /**
     * Constructor for inserting a new user in the database
     * 
     * @param email
     * @param password
     * @param name
     * @param surname
     * @param type 
     */
    public User(String email, String password, String name, String surname, String type) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.type = type;
    }

    /**
     * Full Constructor for fetching a user from the database
     * 
     * @param userID
     * @param email
     * @param name
     * @param surname
     * @param type
     * @param dateCreated
     * @param lastLogin
     * @param lastIp
     * @param dateModified 
     */
    public User(int userID, String email, String name, String surname, String type, Timestamp dateCreated, Timestamp lastLogin, String lastIp, Timestamp dateModified) {
        this.userID = userID;
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.type = type;
        this.dateCreated = dateCreated;
        this.lastLogin = lastLogin;
        this.lastIp = lastIp;
        this.dateModified = dateModified;
    }
    
    /**
     * Empty Constructor
     */
    public User() {
        
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Timestamp getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Timestamp dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Timestamp getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Timestamp lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getLastIp() {
        return lastIp;
    }

    public void setLastIp(String lastIp) {
        this.lastIp = lastIp;
    }

    public Timestamp getDateModified() {
        return dateModified;
    }

    public void setDateModified(Timestamp dateModified) {
        this.dateModified = dateModified;
    }
}
