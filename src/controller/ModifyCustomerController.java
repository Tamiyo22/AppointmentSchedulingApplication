package controller;

import DAO.CustomerDAOImpl;
import DAO.DivisionDAOImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Customer;
import model.Division;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import static DAO.CustomerDAOImpl.customerCountry;


/**
 * This class has the methods for Modifying Customers.
 * <p>
 * The AppointmentsController extends the AddCustomerController class.
 * It has methods that allow the user to change and update customer elements.
 * </p>
 */
public class ModifyCustomerController extends AddCustomerController{

    @FXML
    public Button saveCustomer;
    @FXML
    public TextField addCustomerID;
    @FXML
    private TextArea addCustomerAddress;

    @FXML
    private TextField addCustomerPostal;

    @FXML
    public ChoiceBox<String> addCustomerCountry;

    @FXML
    private ChoiceBox<Division> addCustomerState;

    @FXML
    private ChoiceBox<Division> addCustomerConstituent;

    @FXML
    private ChoiceBox<Division> addCustomerProvince;

    @FXML
    private TextField addCustomerName;

    @FXML
    private TextField addCustomerNumber;



Customer person;


    /**
     * The method sets up the current customers location.
     * This method takes the customer that we are modifying and sets up their current location and their areas.
     * @param person customer
     * @throws Exception error
     */
    public void setLocation(Customer person) throws Exception {
        String currentCountry = addCustomerCountry.getValue();
        int divisionID = person.getDivisionID();
        String location = DivisionDAOImpl.customerLocation(divisionID);
        Division place = new Division(location);


            if (currentCountry.equals("U.S")) {

                for (Division state : states) {
                    if (state.toString().equals(place.toString())) {

                        addCustomerState.getSelectionModel().select(state);
                    }
                }

            }

            if (currentCountry.equals("UK")) {
                for (Division constituent : constituents) {
                    if (constituent.toString().equals(place.toString())) {
                        addCustomerConstituent.setValue(constituent);
                    }
                }

            }
            if (currentCountry .equals("Canada")) {

                for (Division province : provinces) {
                    if (province.toString().equals(place.toString())) {
                        addCustomerProvince.setValue(province);
                    }
                }

            }
    }


    /**
     * The method sets the stage with the selected customer from the prior stage.
     * This method makes sure that all of the proper fx ids are filled with the proper customer information.
     * @param person customer
     */
    public void setCustomer(Customer person) {

        this.person = person;

        try {
            int divisionID = person.getDivisionID();
            String country = customerCountry(divisionID);

            addCustomerID.setText(Integer.toString(person.getCustomerID()));
            addCustomerAddress.setText(person.getAddress());
            addCustomerName.setText(person.getCustomerName());
            addCustomerPostal.setText(person.getPostalCode());
            addCustomerNumber.setText(person.getPhone());
            addCustomerCountry.setValue(country);

        } catch (SQLException throwables) {
            throwables.printStackTrace();

        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }


    /**
     *
     *The method updates the appointment.
     * This method does time checks to make that the inputs are correct and filled out. It
     * checks all input and then calls the updateCustomer from Customer DAO and loads the updated
     * customer into the database.
     * @param event on click
     */
    public void updateCustomer(ActionEvent event){

  try {
      int customerID = person.getCustomerID();
      int divisionID = currentDivision;
      String address = addCustomerAddress.getText();
      String postalCode = addCustomerPostal.getText();
      String customerName = addCustomerName.getText();
      String phone = addCustomerNumber.getText();
      LocalDateTime lastUpdate = LocalDateTime.now();
      String lastUpdateBy = loggedInUser;

      CustomerDAOImpl.updateCustomer(customerID, customerName, address, postalCode, phone, lastUpdate, lastUpdateBy, divisionID);
  }catch (NullPointerException e){
      errorAlert("Please make sure all the fields are filled.");
      return;
  }
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Updated Customer");
    alert.setHeaderText("You Have Successfully Updated a Customer!");
    alert.setContentText(null);
    alert.showAndWait();

    try {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/Customers.fxml"));
        Parent parent = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(parent, 1200, 1000);
        stage.setTitle("Customers");
        stage.setScene(scene);
        stage.show();

    } catch (IOException error) {
        error.printStackTrace();
    }
        }


    /**
     * The method initializes the methods for set up.
     * <p>
     * This method sets up the information needed for the user to add an appointment using the interface Initializable
     *  which helps call the methods after the controller has been initialized after the root element has processed.
     *  </p>
     * @param location The url used to resolve relative paths for the root object, or null if the url is not known.
     * @param resourceBundle The resources used to localize the root object, or null if the root object was not localized.
     */

    @Override
    public void initialize(URL location, ResourceBundle resourceBundle) {
        loadCountries();
        loadUsStates();
        loadUk();
        loadCanada();
        loadLocations();
        addCustomerCountry.setOnAction(this::selectLocations);
        addCustomerProvince.setOnAction(this::getProvince);
        addCustomerState.setOnAction(this::getState);
        addCustomerConstituent.setOnAction(this::getConstituent);



    }



}
