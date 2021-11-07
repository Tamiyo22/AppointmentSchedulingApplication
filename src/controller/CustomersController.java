package controller;

import DAO.AppointmentsDAOImpl;
import DAO.CustomerDAOImpl;
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
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.stage.Stage;
import model.Appointment;
import model.Customer;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;



/**
 * This class has the methods for the Customers.
 * <p>
 * The CustomersController extends the MainMenu class and implements the Initializable inerface.
 *It has methods that allow the user to go delete customers, view weekly and monthly appointments.
 * This class also contains paths that go to  and set up the stages that add appointments, and modify them.
 * </p>
 */

public class CustomersController extends MainMenu implements Initializable {




    @FXML
    private Button addCustomer;

    @FXML
    private TableColumn<Customer, String> customerAddress;

    @FXML
    private TableColumn<Customer, Integer> customerID;

    @FXML
    private TableColumn<Customer, String> customerName;

    @FXML
    private TableView<Customer> customersTable;


    @FXML
    private TableColumn<Customer, String> phone;

    @FXML
    private TableColumn<Customer, String> postalCode;

    @FXML
    Label userGreeting;




    ObservableList<Customer> customers = FXCollections.observableArrayList();



    /**
     * The method loads the AddCustomer stage so the user can add an appointment.
     *<p>
     * This method loads the AddCustomer stage when the user clicked the add customer button.
     *</p>
     * @param event
     */

    @FXML
    void goToAddCustomer(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("view/AddCustomer.fxml"));
            Parent parent = fxmlLoader.load();
            Stage newWindow = new Stage();
            newWindow.setTitle("Add Customer");
            newWindow.setScene(new Scene(parent));
            newWindow.show();
            Stage currentWindow = (Stage) addCustomer.getScene().getWindow();
            currentWindow.close();
        } catch (IOException error) {
            error.printStackTrace();
        }

    }

    /**
     This method loads the selected customer to a new stage to be modified.
     <p>
     This method takes the customer  that has been selected by the user, and loads it onto the Modify Customer
     stage. If the user has not selected a customer , but has still clicked the button, it tells them to select
     an customer.
     </p>
     */

    @FXML
    void goToModifyCustomer(ActionEvent event) throws Exception {

        Customer person = customersTable.getSelectionModel().getSelectedItem();
        if (person != null) {

            try {
                Customer personToModify = CustomerDAOImpl.getCustomer(person.getCustomerName());

                    FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/ModifyCustomer.fxml"));
                    Parent parent = loader.load();
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    Scene scene = new Scene(parent, 800, 600);
                    stage.setTitle("Modify Customer");
                    stage.setScene(scene);
                    ModifyCustomerController controller = loader.getController();
                    controller.setCustomer(personToModify);
                    controller.setLocation(personToModify);

                    stage.show();


            } catch(IOException e){
                errorAlerts("There is an issue with this event.");
                System.out.println(e.getMessage());
            }

                } else if(person == null){

                    errorAlerts("Please select a customer to modify.");
                }
    }


    /**
     * The method returns a boolean based on if the customer has other appointments.
     * <p>
     *  The method calls on Appointments DAO getAllAppointments method and searches each appointment for the provided
     *  customer id. If one of the appointments has that id, the method returns true, else it returns false.
     * </p>
     * @param customer the customer name
     * @return boolean
     */

    public boolean hasAppointments(Customer customer){
        int id = customer.getCustomerID();

        for(Appointment appointment : AppointmentsDAOImpl.getAllAppointments()){
            if(appointment.getCustomerID() == id){
                return true;
            }
        }

        return false;
    }


    /**
     * The method deletes the users selected customer if they have no appointments.
     * <p>
     *This method takes the users selection and then it calls the getCustomer()
     * method from the Customer data access object class which finds the customer in the data base. Then we call the method
     * hasAppointments which checks the database to see if the customer has any associated appointments. If they do
     * the user receives an error informing  them that the customer cannot be removed until its associated appointments
     * are removed. If the customer no longer has any associated appointments, the deleteCustomer method from the
     * from the Customer data access object class which removes the customer from the data base.
     * Then the user is confirmed that the customer with the former customers name has been removed.
     * </p>
     * @throws IOException
     */
    public void deleteCustomer() throws Exception {

        try{
        Customer person = customersTable.getSelectionModel().getSelectedItem();
        Customer personToDelete = CustomerDAOImpl.getCustomer(person.getCustomerName());

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you would like to delete this customer?");
        Optional<ButtonType> result = alert.showAndWait();

        if(hasAppointments(person)) {

            errorAlerts("You cannot delete this customer! " +
                    " Please remove their scheduled appointments first.");

        }

           else if (result.isPresent() && result.get() == ButtonType.OK) {

            CustomerDAOImpl.deleteCustomer(personToDelete.getCustomerID());

            alert.setHeaderText("You have deleted customer "+ personToDelete.getCustomerName()+" from the system.");
            alert.setTitle("You Have Removed a Customer!");
            alert.setContentText(null);
            alert.showAndWait();

            customers.clear();
            loadCustomerTable();
        }

           } catch (NullPointerException e){
            errorAlerts("Please select a customer to delete.");
            return;
        }
       }



    /**
     * The method sets up the table for the user to view.
     * <p>
     *This method calls the getAllCustomers from Customers Data Access Object class and gets all of the
     * customers from the database. Then it takes the fx ids from the stage and sets the values from the data base
     * to them once the stage is loaded.
     * </p>
     */
    public void loadCustomerTable() {

        try {
            customerID.setCellValueFactory(new PropertyValueFactory<>("CustomerID"));
            customerName.setCellValueFactory(new PropertyValueFactory<>("CustomerName"));
            customerAddress.setCellValueFactory(new PropertyValueFactory<>("Address"));
            postalCode.setCellValueFactory(new PropertyValueFactory<>("PostalCode"));
            phone.setCellValueFactory(new PropertyValueFactory<>("Phone"));

            customers.addAll(CustomerDAOImpl.getAllCustomers());

        } catch (Exception e) {
            System.out.println(e);
        }
        customersTable.setItems(customers);
    }

    /**
     * The method initializes the methods for set up.
     * <p>
     *  This method sets up the information needed for the user to add an appointment using the interface Initializable
     *  which helps call the methods after the controller has been initialized after the root element has processed.
     * </p>
     * @param location The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resourceBundle The resources used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL location, ResourceBundle resourceBundle) {
        loadCustomerTable();
        displayName();
    }


}


