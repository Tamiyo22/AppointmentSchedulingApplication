package controller;


import DAO.AppointmentsDAOImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import model.Appointment;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

/**
 * This class has the methods for the Appointment by Locations report.
 * <p>
 * The AppointmentByLocationReport extends the AppointmentsController class and implements the Initializable inerface.
 * By inheriting from the  AppointmentsController the user can navigate to other stages as they could with the MainMenu, and have
 * access to all of the AppointmentsController methods.The AppointmentByLocationReport class sets the elements needed for the user to
 * view the appointment data from the locations on click of the location.
 * </p>
 */

    public class AppointmentByLocationReport  extends AppointmentsController implements Initializable {


        @FXML
        private TableColumn<Appointment, String> appointDescription;


        @FXML
        private TableColumn<Appointment, Integer> appointID;

        @FXML
        private TableColumn<Appointment, String> appointTitle;

        @FXML
        private TableColumn<Appointment, String> appointType;

        @FXML
        private Label locationName;

        @FXML
        private TableView<Appointment> locationTable;

        @FXML
        private TableColumn<Appointment, LocalDateTime> endTime;

        @FXML
        private TableColumn<Appointment, LocalDateTime> startTime;

        @FXML
        private TableColumn<Appointment, Integer> customerID;





        ObservableList<Appointment> appointments = FXCollections.observableArrayList();

        ObservableList<Appointment> Phoenix = FXCollections.observableArrayList();
        ObservableList<Appointment> WhitePlains = FXCollections.observableArrayList();
        ObservableList<Appointment> Montreal = FXCollections.observableArrayList();
        ObservableList<Appointment> London = FXCollections.observableArrayList();
        ObservableList<Appointment> allAppointments = AppointmentsDAOImpl.getAllAppointments();


    /**
     * The method adds appointments from the Phoenix, Arizona area to the table to view.
     * <p>
     * This method calls the getAllAppointments() method from the Appointment Data Access Object, which
     * loads all of the appointments. Then we use the forEach lambda method  to select the ones that have the location
     * of Phoenix and set it to the location table. I choose to use forEach here since we are iterating through
     * a large amount of data. I thought it would be an clearer way of showing the work being done.
     * </p>
     * @param event
     */
    public void loadPhoenixTable(ActionEvent event)  {

        Phoenix.clear();
        allAppointments.forEach(appointment -> {
            if (appointment.getLocation().contains("Phoenix")) {
                Phoenix.add(appointment);
            }});

            locationTable.setItems(Phoenix);
            locationName.setText(" You are viewing appointments in Phoenix, Arizona.");
        }

    /**
     * The method adds appointments from the White Plains, New York area to the table to view.
     * <p>
     * This method calls the getAllAppointments() method from the Appointment Data Access Object, which
     * loads all of the appointments.Then we use the forEach lambda method  to select the ones that have the location
     * of New York and set it to the location table. I choose to use forEach here since we are iterating through
     *  a large amount of data. I thought it would be an clearer way of showing the work being done.
     *
     * </p>
     * @param event
     */
    public void loadWhitePlainsTable(ActionEvent event)  {

        WhitePlains.clear();

          allAppointments.forEach(appointment -> {
            if (appointment.getLocation().contains("New York")) {
                WhitePlains.add(appointment);
            }});


            locationTable.setItems(WhitePlains);
            locationName.setText(" You are viewing appointments White Plains, New York.");
        }

    /**
     * The method adds appointments from the Montreal, Canada area to the table to view.
     * <p>
     * This method calls the getAllAppointments() method from the Appointment Data Access Object, which
     * loads all of the appointments. Then we use the forEach lambda method  to select the ones that have the location
     *  of Montreal and set it to the location table. I choose to use forEach here since we are iterating through
     *  a large amount of data. I thought it would be an clearer way of showing the work being done.
     * </p>
     * @param event
     */
        public void loadMontrealTable(ActionEvent event)  {

                Montreal.clear();

               allAppointments.forEach(appointment -> {
                if (appointment.getLocation().contains("Montreal")) {
                    Montreal.add(appointment);
                }});

            locationTable.setItems(Montreal);
            locationName.setText(" You are viewing appointments in Montreal, Canada");

        }

    /**
     * The method adds appointments from the London, England area to the table to view.
     * <p>
     * This method calls the getAllAppointments() method from the Appointment Data Access Object, which
     * loads all of the appointments. Then we use the forEach lambda method  to select the ones that have the location
     * of London and set it to the location table. I choose to use forEach here since we are iterating through
     * a large amount of data. I thought it would be an clearer way of showing the work being done.
     * </p>
     * @param event
     */
        public void loadLondonTable(ActionEvent event)  {

             London.clear();

               allAppointments.forEach(appointment -> {
                if (appointment.getLocation().contains("London")) {
                    London.add(appointment);
                }});


            locationTable.setItems(London);
            locationName.setText(" You are viewing appointments in London, England");
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

                locationName.setText("Welcome! Please select a location.");
                appointID.setCellValueFactory(new PropertyValueFactory<>("AppointmentID"));
                appointTitle.setCellValueFactory(new PropertyValueFactory<>("Title"));
                appointDescription.setCellValueFactory(new PropertyValueFactory<>("Description"));
                appointType.setCellValueFactory(new PropertyValueFactory<>("Type"));
                startTime.setCellValueFactory(new PropertyValueFactory<>("Start"));
                endTime.setCellValueFactory(new PropertyValueFactory<>("End"));
                customerID.setCellValueFactory(new PropertyValueFactory<>("CustomerID"));

                appointments.addAll(AppointmentsDAOImpl.getAllContactAppointments());


            } catch (Exception e) {
                System.out.println(e);

            }
            locationTable.setItems(appointments);

        }


    /**
     * The method initializes the methods for set up.
     * <p>
     *  This method sets up the information needed for the user to view the location reports using the interface Initializable
     *  which helps call the methods after the controller has been initialized after the root element has processed.
     * </p>
     * @param location The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resourceBundle The resources used to localize the root object, or null if the root object was not localized.
     */
    @Override
        public void initialize(URL location, ResourceBundle resourceBundle) {


            loadContactsTable();
            displayName();


        }

    }
