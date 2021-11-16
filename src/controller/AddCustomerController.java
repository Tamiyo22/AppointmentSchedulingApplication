package controller;

import DAO.CountryDAOImpl;
import DAO.CustomerDAOImpl;
import DAO.DivisionDAOImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Customer;
import model.Division;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

/**
 * This class extends the MainMenu class and implements the Initializable inerface.
 * <p>
 * The AddCustomerController class inherits the methods of the MainMenu class and the Initializable interface. By inherited from the MainMenu the user
 * can navigate to other stages as they could with the MainMenu. The Initializable class will run the methods needed to run
 * as soon as the stage root is processed. The AddCustomerController class sets the elements needed for the uer to see
 * and then gets the data from the user to put into the database and create a new customer.
 * </p>
 */

public class AddCustomerController extends MainMenu implements Initializable {
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

    @FXML
    private Label provinceSelection;

    @FXML
    private Label constituentSelection;

    @FXML
    private Label stateSelection;


    ObservableList<String> countries = FXCollections.observableArrayList();
    ObservableList<Division> states = FXCollections.observableArrayList();
    ObservableList<Division> constituents = FXCollections.observableArrayList();
    ObservableList<Division> provinces = FXCollections.observableArrayList();
    int currentDivision = -1;

    /**
     * The method sets the current location of the customer.
     * <p>
     *  This method calls the getDivisionID method from the Customer Data Access Object (DAO) to get the current
     *  customers location from the database. Then this method sets that value to the variable currentDivision.
     *  </p>
     * @param event
     */

    public void getState(ActionEvent event){
        String currentLocation = addCustomerState.getValue().toString();

        currentDivision = DivisionDAOImpl.getDivisionID(currentLocation);

    }


    /**
     * The method gets the Constituent of a customer and sets their current division.
     * <p>
     *  This method takes in the selected division name and sets its id based on the name. It calls the getDivisionID method
     *  from the Division Data Access Object (DAO) to get the id match from the database.
     * </p>
     * @param event
     */

    public void getConstituent(ActionEvent event){
        String currentLocation = addCustomerConstituent.getValue().toString();
        currentDivision =  DivisionDAOImpl.getDivisionID(currentLocation);

    }


    /**
     * The method gets the province of a customer and sets their current division.
     * <p>
     *  This method takes in the selected division name and sets its id based on the name. It calls the getDivisionID method
     *  from the Division Data Access Object (DAO) to get the id match from the database.
     * </p>
     * @param event
     */

    public void getProvince(ActionEvent event) {
        String currentLocation = addCustomerProvince.getValue().toString();
        currentDivision = DivisionDAOImpl.getDivisionID(currentLocation);

    }
    /**
     * The method takes the countries from the database and sets them as location selections.
     * <p>
     * This method calls the getAllCountries() from the Division DAO and uses it to load all of the countries
     * to the countries ObservableList and then set them to the addCustomerCountry combo box.
     * </p>
     */


    public void loadCountries() {
        try {
            countries.addAll(CountryDAOImpl.getAllCountries());

        } catch (Exception e) {
            System.out.println(e);
        }
        addCustomerCountry.setItems(countries);

    }

    /**
     * The method takes the US States from the database and sets them as location selections.
     * <p>
     * This method calls the getAllUSDivisions() from the Division DAO and uses it to load all of the states
     * to the states ObservableList and then set them to the addCustomerState combo box.
     * </p>
     */

    public void loadUsStates() {
        try {
            states.addAll(DivisionDAOImpl.getAllUSDivisions());

        } catch (Exception e) {
            System.out.println(e);
        }
        addCustomerState.setItems(states);

    }

    /**
     * The method takes the Uk Divisions from the database and sets them as location selections.
     * <p>
     * This method calls the getAllUKDivisions() from the Division DAO and uses it to load all of the constituents
     * to the constituents ObservableList and then set them to the addCustomerConstituent combo box.
     * </p>
     */

    public void loadUk() {
        try {
            constituents.addAll(DivisionDAOImpl.getAllUKDivisions());

        } catch (Exception e) {
            System.out.println(e);
        }
        addCustomerConstituent.setItems(constituents);

    }

    /**
     * The method takes the Canada provinces from the database and sets them as location selections.
     * <p>
     * This method calls the getAllCanadaDivisions() from the Division DAO and uses it to load all of the provinces
     * to the provinces ObservableList and then set them to the addCustomerProvince combo box.
     * </p>
     */

    public void loadCanada() {
        try {
            provinces.addAll(DivisionDAOImpl.getAllCanadaDivisions());

        } catch (Exception e) {
            System.out.println(e);
        }
        addCustomerProvince.setItems(provinces);

    }


    /**
     *
     * The method sets the locations as hidden.
     * <p>
     *  This method uses the built in setStyle DOM method to set the visibility as hidden when the page is first loaded.
     *  visible
     * </p>
     *
     */

    public void loadLocations(){
        addCustomerConstituent.setStyle("visibility: hidden");
        addCustomerProvince.setStyle("visibility: hidden");
        addCustomerState.setStyle("visibility: hidden");
        constituentSelection.setStyle("visibility: hidden");
        stateSelection.setStyle("visibility: hidden");
        provinceSelection.setStyle("visibility: hidden");

    }

    /**
     *
     * The method shows and hides locations based on country selection.
     * <p>
     *  This method uses the built in setStyle DOM method to coordinate a dance between elements without every changing
     *  their positions. Instead it sets elements to visibility: visible or visibility: hidden based on country selection.
     *  visible
     * </p>
     *
     * @param event
     */

    public void selectLocations(ActionEvent event){

        String currentCountry = addCustomerCountry.getValue();
        try {
                if (currentCountry.equals("U.S")) {

                    //hide non US
                    addCustomerConstituent.setStyle("visibility: hidden");
                    addCustomerProvince.setStyle("visibility: hidden");
                    constituentSelection.setStyle("visibility: hidden");
                    provinceSelection.setStyle("visibility: hidden");

                   //show US
                    addCustomerState.setStyle("visibility: visible");
                    stateSelection.setStyle("visibility: visible");
                }

                if (currentCountry.equals("UK")) {

                    //hide non UK
                    addCustomerState.setStyle("visibility: hidden");
                    stateSelection.setStyle("visibility: hidden");
                    addCustomerProvince.setStyle("visibility: hidden");
                    provinceSelection.setStyle("visibility: hidden");

                    //show uk
                    addCustomerConstituent.setStyle("visibility: visible");
                    constituentSelection.setStyle("visibility: visible");

                }
                if (currentCountry .equals("Canada")) {

                    //hide non Canada
                    constituentSelection.setStyle("visibility: hidden");
                    addCustomerConstituent.setStyle("visibility: hidden");
                    addCustomerState.setStyle("visibility: hidden");
                    stateSelection.setStyle("visibility: hidden");

                    //show only Canada
                    provinceSelection.setStyle("visibility: visible");
                    addCustomerProvince.setStyle("visibility: visible");
                }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * The method saves the new customer information.
     * <p>
     * This method takes all of the inputted values, sets and checks them before calling the addCustomer method
     * from the Customer DOA. If a value isn't entered, then we notify the user, else we set and enter the values
     * to be placed into the data base by the Customer DOA addCustomer method.
     * </p>
     * @param event
     * @throws Exception
     */

    public void saveCustomer(ActionEvent event) throws Exception {

        try {

            int divisionID = currentDivision;
            String address = addCustomerAddress.getText();
            String postalCode = addCustomerPostal.getText();
            String customerName = addCustomerName.getText();
            String phone = addCustomerNumber.getText();
            LocalDateTime createDate = LocalDateTime.now();
            String createdBy = loggedInUser;

            String lastUpdateBy = loggedInUser;
            LocalDateTime lastUpdate = LocalDateTime.now();

            if(divisionID == -1){
                errorAlert("Please fill out all fields for this customer!");
                return;
            }

            if(customerName == null || address == null|| postalCode == null || phone == null  || createdBy == null){
                errorAlert("Please fill out all fields for this customer!");
                return;
            }



            System.out.println(customerName);
            CustomerDAOImpl.addCustomer(customerName, address, postalCode, phone, createDate, createdBy, lastUpdate, lastUpdateBy, divisionID);
        } catch(NullPointerException e){
            errorAlert("Please fill out all fields for this customer!");
            return;
        }


            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Added New Customer!");
            alert.setHeaderText("You Have Successfully Added a New Customer!");
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
     *  This method sets up the information needed for the user to add a customer using the interface Initializable
     *  which helps call the methods after the controller has been initialized after the root element has processed.
     * </p>
     * @param location The location used to resolve relative paths for the root object, or null if the location is not known.
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
