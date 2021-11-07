package controller;

import DAO.AppointmentsDAOImpl;
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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Appointment;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * This class has the methods for the Appointments.
 * <p>
 * The AppointmentsController extends the MainMenu class and implements the Initializable interface.
 *It has methods that allow the user to go delete appointments, view weekly and monthly appointments.
 * This class also contains paths that go to  and set up the stages that add appointments, and modify them.
 * </p>
 */

public class AppointmentsController extends MainMenu implements Initializable {

    @FXML
    private TableView<Appointment> appointmentTable;

    @FXML
    private TableColumn<Appointment, Integer> appointID;

    @FXML
    private TableColumn<Appointment, String> appointTitle;

    @FXML
    private TableColumn<Appointment, String> appointDescription;

    @FXML
    private TableColumn<Appointment, String> location;

    @FXML
    private TableColumn<Appointment, Integer> contactID;

    @FXML
    private TableColumn<Appointment, String> appointType;

    @FXML
    private TableColumn<Appointment, LocalDateTime> startTime;

    @FXML
    private TableColumn<Appointment, LocalDateTime> endTime;

    @FXML
    private TableColumn<Appointment, Integer> customerID;

    @FXML
    private TableColumn<Appointment, Integer> userID;

    @FXML
    private Button modifyAppointButton;

    @FXML
    private AnchorPane Navigation;

    @FXML
    private Button Logout;

    @FXML
    private Button addAppointButton;

    @FXML
    private Button Home;

    @FXML
    private Button AppointmentsButton;

    @FXML
    private Button CustomerButton;

    @FXML
    Label userGreeting;

    @FXML
    private Label Time;

    @FXML
    private Label Date;

    @FXML
    private Label Zone;

    @FXML
    private Button weekly;

    @FXML
    private Button monthly;

    ObservableList<Appointment> appointments = FXCollections.observableArrayList();
    ObservableList<Appointment> weeklyAppointmentList = FXCollections.observableArrayList();
    ObservableList<Appointment> monthlyAppointmentList = FXCollections.observableArrayList();


    /**
     * The method sets the appointments table to show the upcoming appointments of the week,
     * <p>
     *  This method gets the time stamp of today, and the time 7 days from today. Then it calls the getAllAppointments()
     *  method from the Appointment data access object class which gets the appointments from today through 6 days from now, making it
     *  a total of one week, 7 total days of appointments. It then clears the currently shown appointments and sets them
     *  to the appointments that fall withing the specified timeline.
     * </p>
     * @param event
     * @throws SQLException
     */

    @FXML
    void getWeeklyAppointments(ActionEvent event) throws SQLException {
        weeklyAppointmentList.clear();
        Timestamp today = Timestamp.valueOf(LocalDateTime.now());
        Timestamp future = Timestamp.valueOf(today.toLocalDateTime().plusDays(7));

        for(Appointment appointment : AppointmentsDAOImpl.getAllAppointments()){
            if( appointment.getStart().isEqual(today.toLocalDateTime())  ||
                    ( appointment.getStart().isAfter(today.toLocalDateTime())
                            &&  appointment.getStart().isBefore(future.toLocalDateTime()))){
                weeklyAppointmentList.add(appointment);
            }
        }

        appointments.clear();
        appointmentTable.setItems(appointments);
        appointmentTable.setItems(weeklyAppointmentList);

        }

    /**
     * The method shows all of the appointments for the upcoming month.
     * <p>
     *This method gets the time stamp of today, and the time 30 days from today. Then it calls the getAllAppointments()
     * method from the Appointment data access object class which gets the appointments from today through 30 days from now, making it
     * a total of one month, 30 total days of appointments. It then clears the currently shown appointments and sets them
     * to the appointments that fall withing the specified timeline.
     * </p>
     * @param event
     * @throws SQLException
     */

    @FXML
    void getMonthlyAppointments(ActionEvent event) throws SQLException {
        monthlyAppointmentList.clear();
        Timestamp today = Timestamp.valueOf(LocalDateTime.now());
        Timestamp future = Timestamp.valueOf(today.toLocalDateTime().plusMonths(1));


        for(Appointment appointment : AppointmentsDAOImpl.getAllAppointments()){
            if(  appointment.getStart().isEqual(today.toLocalDateTime()) ||
                    (appointment.getStart().isAfter(today.toLocalDateTime()) &&
                            appointment.getStart().isBefore(future.toLocalDateTime()))){
                monthlyAppointmentList.add(appointment);
            }
        }

        appointments.clear();
        appointmentTable.setItems(appointments);
        appointmentTable.setItems(monthlyAppointmentList);

    }

/**
This method loads the selected appointment to a new stage to be modified.
 <p>
 This method takes the appointment that has been selected by the user, and loads it onto the Modify Appointment
 stage. If the user has not selected a appointment, but has still clicked the button, it tells them to select
 an appointment.
 </p>
 */
    @FXML
    void goToModifyAppointment(ActionEvent event) throws Exception {

        Appointment meeting = appointmentTable.getSelectionModel().getSelectedItem();

        if (meeting != null) {

            try {

                Appointment appointmentToModify = AppointmentsDAOImpl.getAppointment(meeting.getAppointmentID());


                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/ModifyAppointment.fxml"));
                Parent parent = loader.load();
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(parent, 800, 600);
                stage.setTitle("Modify Appointment");
                stage.setScene(scene);
                ModifyAppointmentController controller = loader.getController();
                controller.setAppointment(appointmentToModify);

                stage.show();



            } catch (IOException e) {
                errorAlerts("There is an issue with this event.");
                System.out.println(e.getMessage());
            }
        } else if (meeting == null) {
            errorAlerts("Please select a appointment to modify.");
        }

    }

    /**
     * The method loads the AddAppointment stage so the user can add an appointment.
     *<p>
     * This method loads the AddAppointment stage when the user clicked the add appointment button.
     *</p>
     * @param event
     */

    @FXML
    void goToAddAppoint(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("view/AddAppointment.fxml"));
            Parent parent = fxmlLoader.load();
            Stage newWindow = new Stage();
            newWindow.setTitle("Add Appointments");
            newWindow.setScene(new Scene(parent));
            newWindow.show();
            Stage currentWindow = (Stage) addAppointButton.getScene().getWindow();
            currentWindow.close();
        } catch (IOException error) {
            error.printStackTrace();
        }
    }

    /**
     * The method sets up the table for the user to view.
     * <p>
     *This method calls the getAllAppointments() from Appointments Data Access Object class and gets all of the
     * appointments from the database. Then it takes the fx ids from the stage and sets the values from the data base
     * to them once the stage is loaded.
     * </p>
     */

    public void loadAppointmentsTable() {

        try {

            appointID.setCellValueFactory(new PropertyValueFactory<>("AppointmentID"));
            appointTitle.setCellValueFactory(new PropertyValueFactory<>("Title"));
            appointDescription.setCellValueFactory(new PropertyValueFactory<>("Description"));
            location.setCellValueFactory(new PropertyValueFactory<>("Location"));
            contactID.setCellValueFactory(new PropertyValueFactory<>("ContactID"));
            appointType.setCellValueFactory(new PropertyValueFactory<>("Type"));
            startTime.setCellValueFactory(new PropertyValueFactory<>("Start"));
            endTime.setCellValueFactory(new PropertyValueFactory<>("End"));
            customerID.setCellValueFactory(new PropertyValueFactory<>("CustomerID"));
            userID.setCellValueFactory(new PropertyValueFactory<>("UserID"));


           appointments.addAll(AppointmentsDAOImpl.getAllAppointments());


        } catch (Exception e) {
            System.out.println(e);

        }

        appointmentTable.setItems(appointments);


    }


    /**
     * The method deletes the users selected appointment.
     * <p>
     *This method takes the users selection and then it calls the deleteAppointment()
     * method from the Appointment data access object class which removes the appointment from the data base.
     * Then the user is confirmed that the the appointment with the appointments id has been deleted and the type
     * of appointment it was.
     * </p>
     * @throws IOException
     */
    public void deleteAppointment() throws IOException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you would like to delete this appointment?");

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {

            Appointment appointment = appointmentTable.getSelectionModel().getSelectedItem();
            if(appointment != null){

            int appointmentToDelete = appointment.getAppointmentID();
            AppointmentsDAOImpl.deleteAppointment(appointmentToDelete);


            alert.setHeaderText("You have deleted appointment Id "+ appointment.getAppointmentID() +" which was a "+appointment.getType()+" appointment.");
            alert.setTitle("You Have Removed a Appointment!");
            alert.setContentText(null);
            alert.showAndWait();

             appointments.clear();
            loadAppointmentsTable();

        } else {
            errorAlerts("Please select a Appointment to delete.");
            return;
            }
        }

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
        loadAppointmentsTable();
        displayName();
    }


}


