package controller;

import DAO.AppointmentsDAOImpl;
import DAO.ContactDAOImpl;
import DAO.UsersDAOImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Appointment;
import util.Times;


import java.net.URL;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;

/**
 * This class has the methods for Modifying  Appointments.
 * <p>
 * The AppointmentsController extends the AddAppointmentController class and implements the Initializable interface.
 * It has methods that allow the user to change and update appointment elements.
 * </p>
 */

public class ModifyAppointmentController extends AddAppointmentController implements Initializable {

    @FXML
    private ChoiceBox<String> AMPMEnd;

    @FXML
    private ChoiceBox<String> AMPMStart;

    @FXML
    private TextField addAppointmentID;

    @FXML
    private TextField addAppointmentTitle;

    @FXML
    private TextArea addAppointmentDescription;

    @FXML
    private ChoiceBox<String> addAppointmentLocation;

    @FXML
    private ChoiceBox<Integer> addAppointmentCustomerID;

    @FXML
    private DatePicker addStartDate;

    @FXML
    private DatePicker addEndDate;

    @FXML
    private ChoiceBox<String> addAppointmentContact;

    @FXML
    private ChoiceBox<String> addAppointmentType;

    @FXML
    private ChoiceBox<LocalTime> addStartHour;


    @FXML
    private ChoiceBox<LocalTime> addEndHour;



    @FXML
    private TextField addUserID;

    Appointment meeting;


    /**
     * The method returns a boolean based on if the customer already has a meeting at the purposed meeting time.
     * <p>
     *  The method checks on 3 major things:
     *  Is the purposed start less than or equal to the start time that the customer already has
     *  and is the purposed end time greater than the appointment the customer is already in.
     *  Is the purposed start time after  an appointment end time and is the purposed end time before or equal to
     *  anything that the customer is already booked in.
     *  Last check is, out side the window. Is one appoint between another.
     *
     * </p>
     *
     * @param id the customer od
     * @param appointmentID the appointment id
     * @param start the start time
     * @param end the end time
     * @return boolean
     */
    public boolean hasAppointmentsAtTime(int id,int appointmentID,LocalDateTime start, LocalDateTime end)  {


        for (Appointment appointment : AppointmentsDAOImpl.getAllAppointments()) {

            int customer = appointment.getCustomerID();
            int appointId =appointment.getAppointmentID();


            if ((customer == id ) && (appointId != appointmentID)) {

                LocalDateTime appointStart = appointment.getStart();
                LocalDateTime appointEnd = appointment.getEnd();


                if ((appointStart.isAfter(start) || appointStart.isEqual(start)) && appointStart.isBefore(end)) {

                    return true;
                }

                else if (appointEnd.isAfter(start)  && (appointEnd.isBefore(end) || appointEnd.isEqual(end))){

                    return true;
                }

                else if ((appointStart.isBefore(start) || appointStart.isEqual(start)) && (appointEnd.isAfter(end) || appointEnd.isEqual(end))) {

                    return true;

                }
            }
        }

        return false;
    }


    /**
     * The method sets up the appointment that was selected on the prior stage.
     * This method takes all of the elements that are changeable by the user and sets them to the modify stage.
     * @param meeting selected meeting
     */
    public void setAppointment(Appointment meeting) {

        this.meeting = meeting;


        try {

            String contact = ContactDAOImpl.getContactName(meeting.getContactID());
            String location = meeting.getLocation();

            String userId = Integer.toString(UsersDAOImpl.getUserId(Login.loggedInUser));

            addAppointmentTitle.setText(meeting.getTitle());
            addAppointmentDescription.setText(meeting.getDescription());
            addAppointmentType.setValue(meeting.getType());
            addAppointmentCustomerID.setValue(meeting.getCustomerID());
            addAppointmentID.setText(Integer.toString(meeting.getAppointmentID()));
            addAppointmentLocation.setValue(location);
            addUserID.setText(userId);
            addAppointmentContact.setValue(contact);
            System.out.println("line 164 "+meeting.getStart().toLocalDate());
            addStartDate.setValue(meeting.getStart().toLocalDate());
            addEndDate.setValue(meeting.getEnd().toLocalDate());
            addStartHour.setValue(meeting.getStart().toLocalTime());
            addEndHour.setValue(meeting.getEnd().toLocalTime());
            AMPMStart.setValue("PM");
            AMPMEnd.setValue("PM");


            LocalTime start = meeting.getStart().toLocalTime();
            LocalTime end = meeting.getEnd().toLocalTime();



            if(start.getHour() > 12){
                addStartHour.setValue(start.minusHours(12));
                AMPMStart.setValue("PM");
            } else {
                addStartHour.setValue(start);
                AMPMStart.setValue("AM");
            }

            if(end.getHour() > 12){
                addEndHour.setValue(end.minusHours(12));
                AMPMEnd.setValue("PM");
            } else  {
                addEndHour.setValue(end);
                AMPMEnd.setValue("AM");
            }


        }
        catch (Exception exception) {
            exception.printStackTrace();
        }

    }


    /**
     * The method updates the appointment.
     * This method does time checks to make that the input times are correct and are within business hours. It
     * checks all input and then calls the updateAppointment from Appointment DAO and loads the updated
     * appointment into the database.
     * @param event on click
     * @throws Exception NullPointerException
     */

    public void updateAppointment(ActionEvent event) throws Exception {

        try {

            int appointmentID = Integer.parseInt(addAppointmentID.getText());
            String title = addAppointmentTitle.getText();
            String description = addAppointmentDescription.getText();
            String location = addAppointmentLocation.getValue();
            String type = addAppointmentType.getValue();
            LocalDateTime lastUpdate = LocalDateTime.now();
            String lastUpdatedBy = loggedInUser;
            int customerID = addAppointmentCustomerID.getValue();
            int userID = UsersDAOImpl.getUserId(Login.loggedInUser);
            int contactID = ContactDAOImpl.getContactID(addAppointmentContact.getValue());


            LocalDate startDate = addStartDate.getValue();
            LocalTime startHour = addStartHour.getValue();

            LocalDate endDate = addEndDate.getValue();
            LocalTime endHour = addEndHour.getValue();

            if(startDate.isBefore(LocalDate.now())){
                errorAlert("Please select a date in the future.");
                return;
            }



            if (AMPMStart.getValue().equals("PM") && startHour.getHour() != 12) {
                startHour = startHour.plusHours(12);
            }

            if (AMPMEnd.getValue().equals("PM")&& endHour.getHour() != 12) {
                endHour = endHour.plusHours(12);
            }


            LocalDateTime officialStart = LocalDateTime.of(startDate, startHour);
            LocalDateTime officialEnd = LocalDateTime.of(endDate, endHour);


            if (Times.withInBusinessHours(officialStart, AMPMStart.getValue(),AMPMEnd.getValue(), officialEnd)) {
                errorAlert("Please create a appointment within 8 AM and 10 PM EST.");
                return;
            }

            if (startBeforeEnd(startDate,startHour , endDate, endHour)) {
                errorAlert("Your start time must be before your end time");
                return;
            }

            if (hasAppointmentsAtTime(customerID, appointmentID, officialStart, officialEnd)) {
                errorAlert("Your customer has another appointment at this time");
                return;
            }

            AppointmentsDAOImpl.updateAppointment(appointmentID, title, description, location, type, officialStart, officialEnd, lastUpdate, lastUpdatedBy, customerID, userID, contactID);
        }catch (NullPointerException e){
            errorAlert("Please make sure all fields are filled out!");
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("You have updated an appointment");
        alert.setHeaderText("You Have Successfully Updated an Appointment!");
        alert.setContentText(null);
        alert.showAndWait();

        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/Appointments.fxml"));
        Parent parent = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(parent, 1200, 1000);
        stage.setTitle("Appointments");
        stage.setScene(scene);
        stage.show();

    }

    /**
     *
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


        getLoggedInUserID();
        loadContacts();
        addAppointmentContact.setOnAction(this::selectContacts);
        loadMeetingTypes();
        addAppointmentType.setOnAction(this::selectType);
        loadMorningNight();
        loadHours();
        loadCustomerIds();
        loadLocations();
        addStartHour.setOnAction(this::selectStartHour);
        addEndHour.setOnAction(this::selectEndHour);
        addAppointmentCustomerID.setOnAction(this::selectIDs);
        addAppointmentLocation.setOnAction(this::selectLocation);

    }

}