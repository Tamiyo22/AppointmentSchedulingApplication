package controller;

import DAO.AppointmentsDAOImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import model.Appointment;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

/**
 * This class has the methods for interacting with  the Appointment by Locations report.
 * @author Melissa Aybar
 *
 */

public class ContactAppointReportController extends AppointmentsController implements Initializable {


    @FXML
    private TableColumn<Appointment, Integer> customerID;


    @FXML
    private TableColumn<Appointment, String> appointDescription;

    @FXML
    private TableColumn<Appointment, Integer> appointID;

    @FXML
    private TableColumn<Appointment, String> appointTitle;

    @FXML
    private TableColumn<Appointment, String> appointType;

    @FXML
    private Label contactName;

    @FXML
    private TableView<Appointment> contactTable;

    @FXML
    private TableColumn<Appointment, LocalDateTime> endTime;

    @FXML
    private TableColumn<Appointment, LocalDateTime> startTime;

    @FXML
    private ChoiceBox<String> addContactChoice;


    ObservableList<Appointment> appointments = FXCollections.observableArrayList();
    HashMap<String, Integer> contactStorage = new HashMap<>();
    List<String> contactNames = Arrays.asList("Anika Costa", "Daniel Garcia", "Li Lee");


    /**
     * This takes all of the contacts and adds them into the ComboBox and the storage HashMap.
     */
    public void loadContacts() {
        addContactChoice.getItems().addAll(contactNames);
        contactStorage.put("Anika Costa", 1);
        contactStorage.put("Daniel Garcia", 2);
        contactStorage.put("Li Lee", 3);
    }

    /**
     * This method takes the selected contact and adds them as an argument to the setContact method.
     * @param event on click.
     */
    public void selectContact(ActionEvent event) { setContact(addContactChoice.getValue());}


    /**
     * This method takes a contact name and gets its key value from the Hashmap and loads it as an argument.
     * @param name of the contact.
     */
    public void setContact(String name){
            int contactIdChoice = contactStorage.get(name);
            loadPersonalTable(contactIdChoice);
    }

    /**
     * This method gets all of the appointments with the provided contact id and loads them onto the contact table.
     * @param num The contact id
     */
    public void loadPersonalTable(int num) {

        FilteredList<Appointment> contactAppointment = new FilteredList<>(AppointmentsDAOImpl.getAllAppointments(), appointment -> appointment.getContactID() == num);

        contactTable.setItems(contactAppointment);
        contactName.setText("You are viewing " + contactNames.get(num - 1) + "'s" + " Appointments.");
        String name = contactNames.get(num - 1);
        switch (name) {
            case "Anika Costa":
                contactName.setStyle("-fx-background-color: #EA4335; -fx-text-fill: white;");
                break;
            case "Daniel Garcia":
                contactName.setStyle("-fx-background-color: #34A853; -fx-text-fill: white;");
                break;
            case "Li Lee":
                contactName.setStyle("-fx-background-color: #4285F4; -fx-text-fill: white;");
                break;
        }
    }

    /**
     * The method sets up the table for the user to view.
     * <p>
     *This method calls the getAllContactAppointments() from Appointments Data Access Object class and gets all of the
     * appointments from the database. Then it takes the fx ids from the stage and sets the values from the data base
     * to them once the stage is loaded.
     * </p>
     */

    public void loadContactsTable() {
     try {
         appointID.setCellValueFactory(new PropertyValueFactory<>("AppointmentID"));
         appointTitle.setCellValueFactory(new PropertyValueFactory<>("Title"));
         appointDescription.setCellValueFactory(new PropertyValueFactory<>("Description"));
         appointType.setCellValueFactory(new PropertyValueFactory<>("Type"));
         startTime.setCellValueFactory(new PropertyValueFactory<>("ZonedStart"));
         endTime.setCellValueFactory(new PropertyValueFactory<>("ZonedEnd"));
         customerID.setCellValueFactory(new PropertyValueFactory<>("CustomerID"));

         appointments.addAll(AppointmentsDAOImpl.getAllAppointments());

     } catch (Exception e) {
         System.out.println(e);
     }
        contactTable.setItems(appointments);
    }


    /**
     * The method initializes the methods for set up.
     * @param location The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resourceBundle The resources used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL location, ResourceBundle resourceBundle) {

        loadContactsTable();
        displayName();
        loadContacts();

        addContactChoice.setOnAction(this::selectContact);


    }

}
