package controller;

import DAO.AppointmentsDAOImpl;
import DAO.ContactDAOImpl;
import DAO.CustomerDAOImpl;
import DAO.UsersDAOImpl;

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

import model.Appointment;
import model.Times;
import model.User;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;

/**
 * This class extends the MainMenu class and implements the Initializable inerface.
 * <p>
 * The AddAppointmentController class inherits the methods of the MainMenu class and the Initializable interface. By inherited from the MainMenu the user
 * can navigate to other stages as they could with the MainMenu. The Initializable class will run the methods needed to run
 * as soon as the stage root is processed. The AddAppointmentController class sets the elements needed for the uer to see
 * and then gets the data from the user to put into the database and create a new appointment.
 * </p>
 */

public class AddAppointmentController extends MainMenu implements Initializable {

    @FXML
    private ChoiceBox<String> AMPMEnd;

    @FXML
    private ChoiceBox<String> AMPMStart;

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




    /**
     * Note:
     * <p>
     * Meeting types, locations and Morning Night are listed as private static final to reduce the memory foot print since these will be shared across and inherited.
     *  For the types of meetings, I choose to hard code the types of sessions you could choose from to give more structure
     *  and give the data analyst more predictability about which types of meetings are scheduled more.
     * Having this also helps our admin/events team, for example a planning session could require catering, and we could
     * built on a trigger to the admin in that location to order food. However, a check in meeting might not need catering.
     </p>
     */

    private static final String[] types = {"Planning Session", "De-Briefing", "Designing Session", "Development Feed Back", "Testing Talks", "Evaluation", "Initial Meeting", "Check In"};
    ObservableList<String> contacts = FXCollections.observableArrayList();
    ObservableList<Integer> customerIDs = FXCollections.observableArrayList();
    ObservableList<LocalTime> times = FXCollections.observableArrayList();
    private static final String[] locations={"Phoenix, Arizona", "White Plains, New York", "Montreal, Canada", "London, England"};
    private static final String[] MorningNight = { "AM", "PM"};


    LocalTime startHour;
    LocalTime endHour;


    /**
     * startBeforeEnd returns true if the ending time is before the starting time .
     * <p>
     * startBeforeEnd checks that the starting input comes after the ending input and returns true if it is, false if it isn't. It also
     * makes sure that the dates are correctly aligned.
     </p>
     * @return
     */
    public boolean startBeforeEnd(LocalDate start, LocalTime startHour , LocalDate end, LocalTime endHour){

         if(start.isAfter(end)){

             return true;
         }

         if(endHour.isBefore(startHour)){

             return true;
        }

       return false;
    }

    /**
     * loadMorningNight sets up AM and PM options for drop down menu.
     * <p> This method takes the fxId's for the starting times
     * AM/PM time of day, and adds the MorningNight array nodes to their drop down combo boxes so they can be options for the user.
     * </p>
     */
    public void loadMorningNight(){

        AMPMStart.getItems().addAll(MorningNight);
        AMPMEnd.getItems().addAll(MorningNight);

    }

    /**
     * method loadContacts sets up the contacts for the user to view.
     * <p>
     * This method sets up contacts for drop down selection in the combo box by calling the getAllContacts from the
     * Contacts DAO and pulling the current contacts from the database.
     * </p>
     */

    public void loadContacts() {
        try {
            contacts.addAll(ContactDAOImpl.getAllContacts());

        } catch (Exception e) {
            System.out.println(e);
        }
        addAppointmentContact.setItems(contacts);

    }

    /**
     * loadCustomerIds gets all of the customer IDs and sets them to the list. This method calls the getAllID's method
     * from the Customer Data Access Object (DAO) to call all current Customer Id's from the database. Then this method
     * adds it to the customer Id  combo box as a selection for the user.
     */

    public void loadCustomerIds() {
        try {
            customerIDs.addAll(CustomerDAOImpl.getAllIDs());

        } catch (Exception e) {
            System.out.println(e);
        }
        addAppointmentCustomerID.setItems(customerIDs);

    }

    /**
     * selectIDs gets the value from the Customer ID combo box.
     * <p>
     *This method gets the current value that has been selected at the select ID combo box.
     * </p>
     * @param event
     */

    public void selectIDs(ActionEvent event){
        addAppointmentCustomerID.getValue();

    }

    /**
     * selectContacts gets the value from the contacts combo box.
     * <p> This method gets the current value that has been selected at the contact combo box.
     *  </p>
     * @param event
     */
    public void selectContacts(ActionEvent event){
         addAppointmentContact.getValue();
    }

    /**
     * getContactID returns the contact id of the contact selected for the current appointment.
     * <p>
     *  This method calls the getContactID method from the Contact Data Access Object (DAO) to get the current
     *  contacts Id's from the database. Then this method returns that id. It will return -1 as an error if no contact id exists.
     *  </p>
     * @return
     *
     */
    public int getContactID() {
        int id = -1;
        if(addAppointmentContact.getValue() != null) {
             id =ContactDAOImpl.getContactID(addAppointmentContact.getValue());
        }
        return id;
    }

    /**
     * getLoggedInUserID gets the current logged in user ID.
     * <p>
     * This method calls the getUserId method from the User Data Access Object (DAO) to get the
     * current users Id's from the database. Then this method sets that id. It will select -1 as an error if
     * there is an error with getting the current user id.
     * </p>
     */
    public void getLoggedInUserID()  {
        int id =-1;
        try {
            if (Login.loggedInUser != null) {
                id = UsersDAOImpl.getUserId(Login.loggedInUser);
            }
        } catch(Exception e){

            e.printStackTrace();
        }
        addUserID.setText(String.valueOf(UsersDAOImpl.getUserId(Login.loggedInUser)));
    }


    /**
     * loadMeetingTypes() sets the meeting types as a selection.
     * <p>
     * This method loads the types adds all of the meeting types that have been preloaded into
     * the types array into a combo box for the user to select.
     * </p>
     */
    public void loadMeetingTypes() {
        addAppointmentType.getItems().addAll(types);
    }

    /**
     * selectLocation gets the value from the location combo box.
     * <p> This method gets the current value that has been selected at the location combo box.
     *  </p>
     * @param event
     */

    public void selectLocation(ActionEvent event){
       addAppointmentLocation.getValue();
    }

    /**
     * This method sets the location types as a selection.
     * <p>
     * This method loads the  locations from all of the locations that have been preloaded into
     * the types array into a combo box for the user to select.
     * </p>
     */
    public void loadLocations() {
        addAppointmentLocation.getItems().addAll(locations);
    }

    /**
     * selectType gets the value from the type combo box.
     * <p> This method gets the current value that has been selected at the type combo box.
     *  </p>
     * @param event
     */

    public void selectType(ActionEvent event){
        addAppointmentType.getValue();
    }

    /**
     * selectStarthour gets the value from the start hour combo box.
     * <p> This method gets the current value that has been selected at the start hour combo box.
     *  </p>
     * @param event
     */
    public void selectStartHour(ActionEvent event){
        startHour = addStartHour.getValue();

    }
    /**
     * selectEndhour gets the value from the start hour combo box.
     * <p> This method gets the current value that has been selected at the end hour combo box.
     *  </p>
     * @param event
     */
    public void selectEndHour(ActionEvent event){
      endHour= addEndHour.getValue();
    }

    /**
     * This method sets the hours as a selection for the users start and end hours.
     * <p>
     * This method loads the hours created by the for loop into a combo box for the user to see.
     * </p>
     */

    public void loadHours(){
        for(int i = 1; i <= 12; i++){
            LocalTime hours = LocalTime.of(i,00);
            times.add(hours);

            if( i <= 12) {
                hours = hours.plusMinutes(15);
                times.add(hours);
            }

            if( i <=12) {
                hours = hours.plusMinutes(15);
                times.add(hours);
            }

            if( i <= 12) {
                hours = hours.plusMinutes(15);
                times.add(hours);
            }


        }
        addStartHour.setItems(times);
        addEndHour.setItems(times);

    }

    /**
     * The method checks the returns true if the customer has an appointment booked during a certain time frame.
     * <p>
     *  This method calls the getAllAppointments method for the Appointment DOA and checks if the customer id is on the appointments list.
     *  Then from there we compare the new possible start and end times with the times the customer also has booked.
     * </p>
     * @param id
     * @param start
     * @param end
     * @return
     */

    public boolean hasAppointmentsAtTime(int id,LocalDateTime start, LocalDateTime end)  {

    for (Appointment appointment : AppointmentsDAOImpl.getAllAppointments()) {

        int customer = appointment.getCustomerID();


        if (customer == id) {


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
     * The method saves the appointment.
     * <p>
     * This method takes all of the inputted values, sets and checks them before calling the addAppointment method
     * from the Appointment DOA. If a value isn't entered, then we notify the user, else we set and enter the values
     * to be placed into the data base by the Appointment DOA addAppointment method.
     * </p>
     * @param event on save click
     * @throws Exception
     */

    public void saveAppointment(ActionEvent event) throws Exception {

        try {
            String title = addAppointmentTitle.getText();

            String description = addAppointmentDescription.getText();

            String location = addAppointmentLocation.getValue();


            String type = addAppointmentType.getValue();


            LocalDateTime createDate = LocalDateTime.now();
            String createdBy = loggedInUser;
            LocalDateTime lastUpdate = LocalDateTime.now();
            String lastUpdatedBy = User.getLoggedUser();
            int customerID = addAppointmentCustomerID.getValue();


            int userID = UsersDAOImpl.getUserId(Login.loggedInUser);
            int contactID = getContactID();


            LocalDate startDate = addStartDate.getValue();
            LocalTime startHour = addStartHour.getValue();


            LocalDate endDate = addEndDate.getValue();
            LocalTime endHour = addEndHour.getValue();



            if (AMPMStart.getValue().equals("PM") && startHour.getHour() != 12) {
                startHour = startHour.plusHours(12);
            }

            if (AMPMEnd.getValue().equals("PM")&& endHour.getHour() != 12) {
                endHour = endHour.plusHours(12);
            }

            if(startDate.isBefore(LocalDate.now())){
                errorAlert("Please select a date in the future.");
                return;
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

            if (hasAppointmentsAtTime(customerID, officialStart, officialEnd)) {
                errorAlert("Your customer has another appointment at this time");
                return;
            }



            AppointmentsDAOImpl.addAppointment(title, description, location,type, officialStart, officialEnd, createDate, createdBy,  lastUpdate, lastUpdatedBy, customerID, userID, contactID);
        }catch(NullPointerException e){
            errorAlert("Please make sure that all fields are entered!");
            return;
        }


        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Added New Appointment!");
        alert.setHeaderText("You Have Successfully Added a New Appointment!");
        alert.setContentText(null);
        alert.showAndWait();

        try{
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/Appointments.fxml"));
        Parent parent = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(parent, 1200, 1000);
        stage.setTitle("Appointments");
        stage.setScene(scene);
        stage.show();

        } catch (IOException error) {
            error.printStackTrace();
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


        getLoggedInUserID();
        loadContacts();
        addAppointmentContact.setOnAction(this::selectContacts);
        loadMeetingTypes();
        addAppointmentType.setOnAction(this::selectType);
        loadHours();
        loadCustomerIds();
        loadLocations();
        loadMorningNight();

        addStartHour.setOnAction(this::selectStartHour);
        addEndHour.setOnAction(this::selectEndHour);
        addAppointmentCustomerID.setOnAction(this::selectIDs);
        addAppointmentLocation.setOnAction(this::selectLocation);



    }
}
