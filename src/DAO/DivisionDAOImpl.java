package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Division;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This is the Division Data Access Object Implementation that has database manipulation methods.
 * The Division Data Access Object or Division DAO allows us to have data operations without exposing the
 * details of the database. The data operations here assist with our Division data.
 */

public class DivisionDAOImpl {


    /**
     * The methods returns the division of the customer based on their division id.
     * This method selects the division from first_level_divisions table of our database based on the customers
     * current division id.
     * @param divisionID int input
     * @return customers location
     */
    public static String customerLocation(int divisionID) {
        String current= null;

        try{
            String sqlStatement = "SELECT Division FROM first_level_divisions WHERE Division_ID = '" + divisionID + "';";
            Query.makeQuery(sqlStatement);
            ResultSet result = Query.getResult();
            String customerResult = null;


            while (result.next()) {
                customerResult = result.getString("Division");
            }

            current= customerResult;

        } catch(SQLException e){

            e.printStackTrace();
        }

        return current;

    }


    /**
     * This method returns a division id by its name input.
     * In this method we select the division by its name from the database and return its id.
     * @param divisionName name of division
     * @return division id
     */
    public static Integer getDivisionID( String divisionName)  {
       int response = -1;

        try {

            String sqlStatement = "SELECT Division_ID FROM first_level_divisions WHERE Division = '" + divisionName+ "'";
            Query.makeQuery(sqlStatement);
            ResultSet result = Query.getResult();


            while (result.next()) {
                int divisionID = result.getInt("Division_ID");
                response= divisionID;
            }


        } catch(SQLException e){
            e.printStackTrace();
        }
        return response;

    }
    /**
     * The method returns all of the US Divisions in the database contacts.
     * This method returns an ObservableList of all the US Divisions from the first_level_divisions table
     * of our database.
     *
     * @return ObservableList
     */

    public static ObservableList<Division> getAllUSDivisions() throws SQLException, Exception {
        ObservableList<Division> allUSDivisions = FXCollections.observableArrayList();

        try {
            String sqlStatement = "SELECT * FROM first_level_divisions WHERE Country_ID = 1 ;";

            Query.makeQuery(sqlStatement);
            Division divisionResult;
            ResultSet result = Query.getResult();

            while (result.next()) {

                String division = result.getString("Division");


                divisionResult = new Division(division);
                allUSDivisions.add(divisionResult);
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
        return allUSDivisions;

    }

    /**
     * The method returns all of the UK Divisions in the database contacts.
     * This method returns an ObservableList of all the UK Divisions from the first_level_divisions table
     * of our database.
     *
     * @return ObservableList
     */
    public static ObservableList<Division> getAllUKDivisions() throws SQLException, Exception {
        ObservableList<Division> allUKDivisions = FXCollections.observableArrayList();

        try {
            String sqlStatement = "SELECT * FROM first_level_divisions WHERE Country_ID = 2 ;";
            Query.makeQuery(sqlStatement);
            Division divisionResult;
            ResultSet result = Query.getResult();

            while (result.next()) {

                String division = result.getString("Division");


                divisionResult = new Division(division);
                allUKDivisions.add(divisionResult);
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
        return allUKDivisions;

    }

    /**
     * The method returns all of the Canada Divisions in the database contacts.
     * This method returns an ObservableList of all the Canada Divisions from the first_level_divisions table
     * of our database.
     *
     * @return ObservableList
     */
    public static ObservableList<Division> getAllCanadaDivisions() throws SQLException, Exception {
        ObservableList<Division> allCanadaDivisions = FXCollections.observableArrayList();

        try {
            String sqlStatement = "SELECT * FROM first_level_divisions WHERE Country_ID = 3 ;";
            Query.makeQuery(sqlStatement);
            Division divisionResult;
            ResultSet result = Query.getResult();

            while (result.next()) {

                String division = result.getString("Division");


                divisionResult = new Division(division);
                allCanadaDivisions.add(divisionResult);
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
        return allCanadaDivisions;

    }

}
