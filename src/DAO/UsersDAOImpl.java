package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.User;


import java.sql.*;

/**
 * This is the User Data Access Object Implementation that has database manipulation methods.
 * The User Data Access Object or Appointment DAO allows us to have data operations without exposing the
 * details of the database. The data operations here assist with our user data.
 */
public class UsersDAOImpl {

    private static final Connection connection = JDBC.getConnection();

    static Statement stmt = null;
    static String allUsersQuery = "SELECT * FROM users";
    public static ObservableList<User> users = FXCollections.observableArrayList();

    /**
     * The method returns all of the user in the database.
     * This method returns an ObservableList of all the users from the database.
     *
     * @return ObservableList
     */
    public static void getUsers() {
        try {
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(allUsersQuery);

            while (rs.next()) {
                String userName = rs.getString("User_Name");
                String password = rs.getString("Password");

                User user = new User();
                user.setUserName(userName);
                user.setPassword(password);

                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    /**
     * This method returns a user id by its name input.
     * In this method we select the user by its name from the database and return its id.
     * @param user name
     * @return user id

     */
    public static int getUserId(String user){
        int userId = 0;
        try {
            String sqlStatement = "SELECT User_ID FROM users WHERE User_Name = '" + user+ "'";
            Query.makeQuery(sqlStatement);

            ResultSet result = Query.getResult();

            while (result.next()) {
                int currentID = result.getInt("User_ID");

                userId = currentID;

            }
        } catch(SQLException e){
            e.printStackTrace();
        }
        return userId;

    }


}

