package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * This is the Contact Data Access Object Implementation that has database manipulation methods.
 * The Contact Data Access Object or Contact DAO allows us to have data operations without exposing the
 * details of the database. The data operations here assist with our contact data.
 */

public class ContactDAOImpl {


    /**
     * The method returns all of the contacts in the database contacts.
     * This method returns an ObservableList of all the contacts from the database.
     * Even though there are not many contacts, I thought this would be best practice in case the
     * contact list ever grew.
     * @return ObservableList
     */
    public static ObservableList<String> getAllContacts() throws SQLException, Exception {
        ObservableList<String> allContacts = FXCollections.observableArrayList();

        try {
            String sqlStatement = "SELECT Contact_Name FROM contacts";

            Query.makeQuery(sqlStatement);
            String contactResult;
            ResultSet result = Query.getResult();

            while (result.next()) {
                String contact = result.getString("Contact_Name");

                contactResult = contact;
                allContacts.add(contactResult);
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
        return allContacts;

    }


    /**
     * This method returns a contact id by its name input.
     * In this method we select the contact by its name from the database and return its id.
     * @param contactName
     * @return contact id

     */
    public static int getContactID(String contactName ){

        int idResult=-1;
        try {
            String sqlStatement = "SELECT Contact_ID FROM contacts WHERE Contact_Name='" + contactName+ "'";

            Query.makeQuery(sqlStatement);

            ResultSet result = Query.getResult();

            while (result.next()) {
                int currentID = result.getInt("Contact_ID");

                idResult = currentID;

            }
        } catch(SQLException e){
            e.printStackTrace();
        }
        return idResult;

    }




    /**
     * This method returns a contact name by its id input.
     * In this method we select the contact by its id from the database and return its id.
     * @param contactID
     * @return contact
     *
     */

    public static String getContactName(int contactID )  {

       String nameResult=" reassign error ";
        try {
            String sqlStatement = "SELECT Contact_Name FROM contacts WHERE Contact_ID='" + contactID+ "'";

            Query.makeQuery(sqlStatement);

            ResultSet result = Query.getResult();

            while (result.next()) {
                String currentName = result.getString("Contact_Name");

                nameResult = currentName;

            }
        } catch(SQLException e){
            e.printStackTrace();
        }
        return nameResult;

    }


}



