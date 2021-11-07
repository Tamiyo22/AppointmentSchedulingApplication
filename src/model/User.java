package model;

import java.time.LocalDateTime;

/**
 * The model class for all users.
 * This is the user class. It models the base of what every user should be capable of having.
 */
public class User {


    private int userID;
    private String userName;
    private String password;
    private static String loggedUser;

    /**
    Creates basic user.
     */
    public User() {
    }

    /**
     * gets user id.
     * @return user id
     */
    public int getUserID() {
        return userID;
    }

    /**
     * sets user id.
     * @param userID id to set
     */
    public void setUserID(int userID) {
        this.userID = userID;
    }

    /**
     * gets user name.
     * @return user name
     */
    public String getUserName() {
        return userName;
    }

    /**
     * sets user name.
     * @param userName name to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * gets user password.
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * sets user password.
     * @param password password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * gets logged in user.
     * @return logged in user
     */
    public static String getLoggedUser() {
        return loggedUser;
    }


}
