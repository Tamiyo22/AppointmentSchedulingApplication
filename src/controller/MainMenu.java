package controller;

import DAO.AppointmentsDAOImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import model.Appointment;


import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * The class is the main stage hub that leads to all other stages.
 * <p>
 *  This stage leads to the appointments, customers, and reports stages. It also shows our user a nice
 *  welcome and the current time.
 * </p>
 */


public class MainMenu extends Login implements Initializable {

    @FXML
    private Button Home;

    @FXML
    Label userGreeting;

    @FXML
    private Button LocationReportButton;



    /**
     * The method sets the welcome text to welcome the current logged in user.
     * <p>
     * displays a greeting and sets the time for the user to see while they work
     * </p>
     */
    public void  displayName(){
        userGreeting.setText(("Hello "+ loggedInUser ));
        timeNow();
    }

    /**
     * The method shows an alert if an appointment is within 15 minutes of their time.
     * <p>
     * This method calculates the difference between the time now and the next appointment start time
     * if the start time falls roughly around 15 minutes, then the user will get an alert when they log in.
     * </p>
     */

      public static void appointmentAlert(){
        boolean found = false;
        for(Appointment appointment : AppointmentsDAOImpl.getAllAppointments()){
            LocalDateTime start = appointment.getStart();
            LocalTime appStart= LocalTime.of(start.getHour(),start.getMinute());
            LocalTime nowTime= LocalTime.now();
            long timeDifference = ChronoUnit.MINUTES.between(nowTime,appStart);

            if(timeDifference  <= 15  && timeDifference  > 0){

                found = true;

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Appointment Id "+ appointment.getAppointmentID() );
                alert.setContentText( "Appointment ID "+ appointment.getAppointmentID()+" starts in 15 minutes or less on "+ appointment.getStart().toLocalDate() +
                        " at time "+  appointment.getStart().toLocalTime());
                alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                alert.showAndWait();

                break;
            }
        }

        if(!found) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("You have no appointments in the next 15 minutes.");
            alert.show();
        }

        }


    /**
     *The method returns a message alert.
     * <p>
     * errorAlerts takes in a string message for the user that varies on the situation, and will notify them with
     * that message
     * </p>
     * @param contentText the message that should be displayed
     */
    public static void errorAlerts(String contentText){
        Alert alert = new Alert(Alert.AlertType.ERROR,contentText, ButtonType.OK );
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.showAndWait();
    }

    /**
     * The method allows the user to leave the application.
     * <p>
     * This method gives the user the option to leave the program.
     * </p>
     * @param event on click.
     */
    @FXML
    public void exit(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you would like to exit the program?");

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
        }
    }
/**
 * This method leads to the appointments stage.
 * <p>
 * This method brings up the appointments stage and closes the current stage when the appointment button is pressed.
 * </p>
 * @param event on click
 */
    @FXML
    void goToAppointments(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("view/Appointments.fxml"));
            Parent parent = fxmlLoader.load();
            Stage newWindow = new Stage();
            newWindow.setTitle("Appointment Scheduler");
            newWindow.setScene(new Scene(parent));
            newWindow.show();
            Stage currentWindow = (Stage)((Node) event.getSource()).getScene().getWindow();
            currentWindow.close();
        } catch (IOException error) {
            error.printStackTrace();
        }
    }

    /**
     * This method leads to the Customers stage.
     * <p>
     * This method brings up the Customers stage and closes the current stage when the Customers button is pressed.
     * </p>
     * @param event on click
     */
    @FXML
    void goToCustomers(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("view/Customers.fxml"));
            Parent parent = fxmlLoader.load();
            Stage newWindow = new Stage();
            newWindow.setTitle("Customers");
            newWindow.setScene(new Scene(parent));
            newWindow.show();
            Stage currentWindow = (Stage)((Node) event.getSource()).getScene().getWindow();
            currentWindow.close();

        } catch (IOException error) {
            error.printStackTrace();
        }

    }

    /**
     * The method brings up the home stage.
     * <p>
     * This method brings up the home stage and closes the current stage when the home button is pressed.
     * </p>
     * @param event on click
     */
    @FXML
     void goToHome(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("view/MainMenu.fxml"));
            Parent parent = fxmlLoader.load();
            Stage newWindow = new Stage();
            newWindow.setTitle("Appointment Scheduler");
            newWindow.setScene(new Scene(parent));
            newWindow.show();
            Stage currentWindow = (Stage) Home.getScene().getWindow();
            currentWindow.close();
        } catch (IOException error) {
            error.printStackTrace();
        }

    }

    /**
     * The method brings up the Contact Reports stage.
     * <p>
     * This method brings up the Contact Reports stage and closes the current stage when the Contact Reports button is pressed.
     * </p>
     * @param event on click
     */

    @FXML
    void goToContactReports(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("view/ContactAppointmentReports.fxml"));
            Parent parent = fxmlLoader.load();
            Stage newWindow = new Stage();
            newWindow.setTitle("Appointment Scheduler");
            newWindow.setScene(new Scene(parent));
            newWindow.show();
            Stage currentWindow = (Stage)((Node) event.getSource()).getScene().getWindow();
            currentWindow.close();
        } catch (IOException error) {
            error.printStackTrace();
        }

    }

    /**
     * The method brings up the Location Reports stage.
     * <p>
     * This method brings up the Location Reports stage and closes the current stage when the Location Reports button is pressed.
     * </p>
     * @param event on click
     */
    @FXML
    void goToLocationReports(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("view/AppointmentLocationReport.fxml"));
            Parent parent = fxmlLoader.load();
            Stage newWindow = new Stage();
            newWindow.setTitle("Appointment Scheduler");
            newWindow.setScene(new Scene(parent));
            newWindow.show();
            Stage currentWindow = (Stage) LocationReportButton.getScene().getWindow();
            currentWindow.close();
        } catch (IOException error) {
            error.printStackTrace();
        }

    }

    /**
     * The method brings up the Type Appointments Report stage.
     * <p>
     * This method brings up the Type Appointments Reports stage and closes the current stage when the
     * Type Appointments Reports button is pressed.
     * </p>
     * @param event on click
     */
    @FXML
    void goToTypeAppointments(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("view/AppointmentsByTypeReport.fxml"));
            Parent parent = fxmlLoader.load();
            Stage newWindow = new Stage();
            newWindow.setTitle("Customer Appointments");
            newWindow.setScene(new Scene(parent));
            newWindow.show();
            Stage currentWindow = (Stage)((Node) event.getSource()).getScene().getWindow();
            currentWindow.close();
        } catch (IOException error) {
            error.printStackTrace();
        }

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
        displayName();

    }


}