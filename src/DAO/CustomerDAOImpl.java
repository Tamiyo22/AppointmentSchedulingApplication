package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Customer;
import model.Division;
import model.Times;

import java.sql.*;
import java.time.LocalDateTime;

/**
 * this is a Customer Data Access Object Implementation that has database manipulation methods.
 */
public class CustomerDAOImpl {

    public static ObservableList<Customer> customers = FXCollections.observableArrayList();


    /**
     * The method returns the customers country.
     * Gets customer country using their divisionID
     * @param divisionID id
     * @return country
     * @throws SQLException e
     * @throws Exception e
     */
    public static String customerCountry(int divisionID) throws SQLException {
        String currentCountry= "";

    try{
        String sqlStatement = "SELECT COUNTRY_ID FROM first_level_divisions WHERE Division_ID = '" + divisionID + "';";
        Query.makeQuery(sqlStatement);
        ResultSet result = Query.getResult();


        while (result.next()) {

            int customerResult = result.getInt("COUNTRY_ID");

            if(customerResult == 1){
                currentCountry = "U.S";
            } else if(customerResult == 2) {
                currentCountry = "UK";
            } else if(customerResult == 3) {
                currentCountry = "Canada";
            }

        }

      } catch(SQLException e){

           e.printStackTrace();
    }
        System.out.println(currentCountry);
        return currentCountry;
    }


    /**
     * The method returns all of the customers in the database.
     * This method returns an ObservableList of all the customers from the database.
     *
     * @return ObservableList
     */
    public static ObservableList<Customer> getAllCustomers()  {
       ObservableList<Customer> allCustomers = FXCollections.observableArrayList();

        try {
            String sqlStatement = "SELECT * FROM customers";
            Query.makeQuery(sqlStatement);
            Customer customerResult;
            ResultSet result = Query.getResult();

            while (result.next()) {
                int customerId = result.getInt("Customer_ID");
                String name = result.getString("Customer_Name");
                String address = result.getString("Address");
                String postal = result.getString("Postal_Code");
                String phone = result.getString("Phone");

                customerResult = new Customer(customerId, name, address, postal, phone);
                allCustomers.add(customerResult);
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
        return allCustomers;

    }

    /**
     * The method returns all of the customers id in the database.
     * This method returns an ObservableList of all the customers id from the database.
     *
     * @return ObservableList
     */
    public static ObservableList<Integer> getAllIDs() {
        ObservableList<Integer> allIDs = FXCollections.observableArrayList();

        try {
            String sqlStatement = "SELECT Customer_ID FROM customers";

            Query.makeQuery(sqlStatement);
            int idResult;
            ResultSet result = Query.getResult();

            while (result.next()) {
                int currentID = result.getInt("Customer_ID");

                idResult = currentID;
                allIDs.add(idResult);
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
        return allIDs;

    }


    /**
     * The method updates the customer
     * Using the sql command SET, we set the database with updated data.
     * @param customerID customerID
     * @param customerName customerName
     * @param address address
     * @param postalCode postalCode
     * @param phone phone
     * @param lastUpdate lastUpdate
     * @param lastUpdateBy lastUpdateBy
     * @param divisionID divisionID
     */
    public static void updateCustomer(int customerID, String customerName, String address, String postalCode, String phone, LocalDateTime lastUpdate, String lastUpdateBy, int divisionID){
        try{

            String sqlStatement = "UPDATE customers SET  Customer_Name = ? , Address = ? , Postal_Code = ? , Phone = ? , Last_Update = ? , Last_Updated_By =? , Division_ID = ? WHERE Customer_ID = '" + customerID+ "'";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sqlStatement);


            ps.setString(1,customerName);
            ps.setString(2,address);
            ps.setString(3,postalCode);
            ps.setString(4,phone);
            ps.setTimestamp(5,Timestamp.valueOf(Times.convertToUTC(lastUpdate)));
            ps.setString(6,lastUpdateBy);
            ps.setInt(7,divisionID);

            ps.execute();


        }catch (SQLException exception) {
            exception.printStackTrace();
        }

    }


    /**
     * This method returns a customer by its name input.
     * In this method we select the customer by its name from the database and return all of its information.
     * @param customerName input string
     * @return customer
     *
     */
    public static Customer getCustomer(String customerName) {
      JDBC.getConnection();
      String sqlStatement="select * FROM customers WHERE Customer_Name = '" + customerName+ "'";
      Query.makeQuery(sqlStatement);
      Customer customerResult = null;
        ResultSet result = Query.getResult();

        try {
            while (result.next()) {
                int customerId = result.getInt("Customer_ID");
                String name = result.getString("Customer_Name");
                String address = result.getString("Address");
                String postal = result.getString("Postal_Code");
                String phone = result.getString("Phone");
                int divisionId = result.getInt("Division_ID");

                customerResult = new Customer(customerId, name, address, postal, phone, divisionId);
            }
        } catch(Exception e) {
                e.printStackTrace();
            }
            return customerResult;
    }


    /**
     *
     * The method add a customer.
     * Using the sql command INSERT INTO, we set the database with updated data.
     * @param customerName customerName
     * @param address address
     * @param postalCode postalCode
     * @param phone phone
     * @param createDate createDate
     * @param createdBy createdBy
     * @param lastUpdate lastUpdate
     * @param lastUpdateBy lastUpdateBy
     * @param divisionID divisionID
     */
    public static void addCustomer( String customerName, String address, String postalCode, String phone, LocalDateTime createDate, String createdBy, LocalDateTime lastUpdate, String lastUpdateBy, int divisionID){
        try{

            String sqlStatement = "INSERT INTO customers VALUES(NULL,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sqlStatement, Statement.RETURN_GENERATED_KEYS);

            //ps.setInt(1,customerID);
            ps.setString(1,customerName);
            ps.setString(2,address);
            ps.setString(3,postalCode);
            ps.setString(4,phone);
            ps.setTimestamp(5, Timestamp.valueOf(Times.convertToUTC(createDate)));
            ps.setString(6,createdBy);
            ps.setTimestamp(7,Timestamp.valueOf(Times.convertToUTC(lastUpdate)));
            ps.setString(8,lastUpdateBy);
            ps.setInt(9,divisionID);

            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();
            rs.next();


        }catch (SQLException exception) {
            exception.printStackTrace();
        }

    }

    /**
     * This method deletes a customer from the data base.
     * I take the selected customer via our customers stage using our customerController, and
     * load that information here in order to delete the proper customer from the database.
     * @param customerID id
     */
    public static void deleteCustomer(int customerID){
        try{

            String sqlStatement = "DELETE FROM customers  WHERE Customer_ID = '" + customerID+ "'";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sqlStatement);


            ps.execute();


        }catch (SQLException exception) {
            exception.printStackTrace();
        }



    }

}
