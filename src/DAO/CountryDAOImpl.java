package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * This is the Country Data Access Object Implementation that has database manipulation methods.
 * The Country Data Access Object or Country DAO allows us to have data operations without exposing the
 * details of the database. The data operations here assist with our country data.
 */
public class CountryDAOImpl {

    /**
     * The method returns all of the countries in the database.
     * This method returns an ObservableList of all the countries from the database.
     *
     * @return ObservableList
     */
    public static ObservableList<String> getAllCountries() {
        ObservableList<String> allCountries = FXCollections.observableArrayList();

        try {
            String sqlStatement = "SELECT Country FROM countries;";

            Query.makeQuery(sqlStatement);
            String countryResult;
            ResultSet result = Query.getResult();

            while (result.next()) {
                String country = result.getString("Country");

                countryResult = country;
                allCountries.add(countryResult);
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
        return allCountries;

    }

}
