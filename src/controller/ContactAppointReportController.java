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
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

/**
 * This class has the methods for the Appointment by Locations report.
 * <p>
 * The AppointmentByLocationReport extends the AppointmentsController class and implements the Initializable inerface.
 * By inheriting from the  AppointmentsController the user can navigate to other stages as they could with the MainMenu, and have
 * access to all of the AppointmentsController methods.The ContactAppointReportController class sets the elements needed for the user to
 * view the appointment data from the contacts on click of their buttons.
 * </p>
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






    ObservableList<Appointment> appointments = FXCollections.observableArrayList();

    ObservableList<Appointment> AnikaCosta = FXCollections.observableArrayList();
    ObservableList<Appointment> DanielGarcia = FXCollections.observableArrayList();
    ObservableList<Appointment> LiLee = FXCollections.observableArrayList();

    /**
     * The method adds appointments with the contact Anika Costa to the table to view.
     * <p>
     * This method calls the getAllAppointments() method from the Appointment Data Access Object, which
     * loads all of the appointments. Then we select the ones that have the contact id of 1, which is Anika Costa's id
     * and set it to the table.
     * </p>
     * @param event
     */
    public void loadAnikaCostaTable(ActionEvent event) throws SQLException {

            for(Appointment appointment : AppointmentsDAOImpl.getAllAppointments()){
                if(appointment.getContactID() == 1){
                    AnikaCosta.add(appointment);
                }
            }
        contactTable.setItems(AnikaCosta);
        contactName.setText(" You are viewing Anika Costa's appointments.");

    }

    /**
     * The method adds appointments with the contact Daniel Garcia to the table to view.
     * <p>
     * This method calls the getAllAppointments() method from the Appointment Data Access Object, which
     * loads all of the appointments. Then we select the ones that have the contact id of 2, which is Daniel Garcia's id
     * and set it to the table.
     * </p>
     * @param event
     */
    public void loadDanielGarciaTable(ActionEvent event) throws SQLException {

        for(Appointment appointment : AppointmentsDAOImpl.getAllAppointments()){
            if(appointment.getContactID() == 2){
                DanielGarcia.add(appointment);
            }
        }

        contactTable.setItems(DanielGarcia);
        contactName.setText(" You are viewing Daniel Garcia's appointments.");
    }

    /**
     * The method adds appointments with the contact Li Lee to the table to view.
     * <p>
     * This method calls the getAllAppointments() method from the Appointment Data Access Object, which
     * loads all of the appointments. Then we select the ones that have the contact id of 2, which is Li Lee's id
     * and set it to the table.
     * </p>
     * @param event
     */
    public void loadLiLeeTable(ActionEvent event) throws SQLException {

        for(Appointment appointment : AppointmentsDAOImpl.getAllAppointments()){
            if(appointment.getContactID() == 3){
                LiLee.add(appointment);
            }
        }
        contactTable.setItems(LiLee);
        contactName.setText(" You are viewing Li Lee's appointments.");
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
         startTime.setCellValueFactory(new PropertyValueFactory<>("Start"));
         endTime.setCellValueFactory(new PropertyValueFactory<>("End"));
         customerID.setCellValueFactory(new PropertyValueFactory<>("CustomerID"));

         appointments.addAll(AppointmentsDAOImpl.getAllAppointments());

     } catch (Exception e) {
         System.out.println(e);
     }
        contactTable.setItems(appointments);
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
