package controller;

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
import model.User;


import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;


/**
 * The class has the methods for user log in.
 * <p>
 *This class has the methods that verify user log ins, and track the times and attempts of users to log in. It also holds
 * methods that communicate with the user like timeNow() and errorAlerts() which help the user stay notified about
 * their interactions with the application. This class also holds the methods to pull keys from the ResourceBundle
 * package incase we have a france speaking user log in.
 * </p>
 *
 */

public class Login implements Initializable {

    @FXML
    private Button CancelLoginInButton;

    @FXML
    private Label Date;

    @FXML
    private Button LoginButton;


    @FXML
    private Label Time;

    @FXML
    private Label Zone;


    @FXML
    private Label employeePetsText;

    @FXML
    private Label loginGreeting;


    @FXML
    private Label passwordText;


    @FXML
    private Label thankYouText;

    @FXML
    private Label timeText;

    @FXML
    private TextField userName;

    @FXML
    private Label userNameText;

    @FXML
    private PasswordField userPassword;

    @FXML
    private Label zoneText;



    Locale locale   = Locale.getDefault();
    public static boolean successfulLogin= false;
    private final static Logger logger = Logger.getLogger("login_activity.txt");
    public static String loggedInUser;


    /**
     * The method sets the users local information for them to view on the main stages.
     * <p>
     * This method gets the users local date and time zone information and sets it for the user to see.
     * This is an expansion of part of A1 where we show the users location in the login. I thought it would be a nice
     * personal touch to not just add the zone, but add the time and date, and expand on the idea of the original task
     * to show this not only in the user login, but through out the GUI to remind the user of the time while they are
     * working.
     * </p>
     */
    public void timeNow() {

        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss:aa");
        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.FULL, locale);
        ZoneId zoneId = ZoneId.systemDefault();

         String timeCurrent = sdf.format(new Date());
         String date = dateFormat.format(new Date());
         String zone = zoneId.toString();


        Time.setText(timeCurrent);
        Date.setText(date);
        Zone.setText(zone);
    }


    /**
     * This method exits the program.
     * <p>
     *  This method checks to see if the user would like to exit the program in english or in french if that is
     *  their location/language setting. If the users setting is french, then this method pulls the french translation
     *  from the resource bundle.
     * </p>
     *
     * @param event on click.
     */

    @FXML
    public void exit(ActionEvent event) {
        ResourceBundle rb = ResourceBundle.getBundle("ResourceBundle/FranceResource_fr_FR", Locale.getDefault());

        try{
            if (Locale.getDefault().getLanguage().equals("fr")) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, rb.getString("ExitProgram"));
                ((Button) alert.getDialogPane().lookupButton(ButtonType.OK)).setText(rb.getString("OK"));
                Optional<ButtonType> result = alert.showAndWait();


                if (result.isPresent() && result.get() == ButtonType.OK) {
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.close();
                }

            } else {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you would like to exit the program?");

                Optional<ButtonType> result = alert.showAndWait();

                if (result.isPresent() && result.get() == ButtonType.OK) {
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.close();
                }

            }
        } catch (Exception e){
            System.out.println(e);
        }

    }
/**
The method gets user log in info for records
 <p>
 This method sets up the pathway to the login_activity.txt file and formats the text so that when we check the logs
 they are not shown is xml, which I thought would be a more user friendly and organized way to view the logs.
 </p>
 */

public static void createLog(){

    try {
        FileHandler logHandler = new FileHandler("login_activity.txt", true);
        logger.addHandler(logHandler);
        SimpleFormatter formatter = new SimpleFormatter();
        logHandler.setFormatter(formatter);
    } catch (IOException e) {
        e.printStackTrace();
    }

}



    /**
     * The method sends an alert to the user and provides them an error message response.
     * <p>
     *  The method allows the GUI to communicate with the user and notify the user if errors that need to
     *  be adjusted with a variety of inputs. This method is called back several times throughout this project.
     * </p>
     *
     * @param contentText is the error message provided to the user.
     */

    public void errorAlert(String contentText) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText(contentText);
        alert.showAndWait();
    }

    /**
     * The method converts english language log in to a french log in.
     * <p>
     * This method listens for a user tha speaks french based on their computer language settings and uses
     * the resource bundle to properly translate the log in screen words for the french speaking user to understand
     * </p>
     */

    @FXML
    public void regionListener() {

        try {
            ResourceBundle rb = ResourceBundle.getBundle("ResourceBundle/FranceResource_fr_FR", Locale.getDefault());

            if (Locale.getDefault().getLanguage().equals("fr")) {
                loginGreeting.setText(rb.getString("Hello"));
                timeText.setText(rb.getString("Time"));
                zoneText.setText(rb.getString("Zone"));
                        thankYouText.setText(rb.getString("HardWork"));
                userNameText.setText(rb.getString("Username"));
                        passwordText.setText(rb.getString("Password"));
                LoginButton.setText(rb.getString("Login"));
                        CancelLoginInButton.setText(rb.getString("Cancel"));
                employeePetsText.setText(rb.getString("PetsGreeting"));

            }

        }
        catch (Exception e){
            System.out.println(e);
        }

    }

    /**
     * The method takes in the username entered and password and returns true if they are correctly in our database.
     * <p>
     * This method calls the User Data Access Object Implementation and verifies that the
     * user password matches the username key.
     * </p>
     * @param userName username text
     * @param password password text
     * @return boolean
     */

    public Boolean verifyLogin(String userName, String password) {
        Boolean flag = false;

        for (User user : UsersDAOImpl.users) {
            String selectedUserName = user.getUserName();
            String selectedPassword = user.getPassword();

            if (selectedUserName.equals(userName)) {
                if (selectedPassword.equals(password)) {
                    flag = true;
                    break;
                }
            }
        }

        return flag;
    }

    /**
     *The method allows the user access to the rest of the application.
     * <p>
     * The method calls the verifyLogin to help verify if the user should be allowed into the system or
     * should receive a log in error. If verifyLogin returns true we are allowed to the main stage. The logger
     * also logs the success. If verifyLogin returns false there is an error shown and the logger logs the
     * users failure.
     * </p>
     * @param event on click
     */
@FXML
   void userAccess(ActionEvent event){
        UsersDAOImpl.getUsers();
        String user = userName.getText();
        String password = userPassword.getText();
        boolean verified = verifyLogin(user, password);
        Locale currentLocale = Locale.getDefault();
        LocalDateTime now = LocalDateTime.now();

        //if passwords match, open main window
        if (verified) {
           successfulLogin = true;
           loggedInUser=user;
            logger.log(Level.INFO, "User " + loggedInUser + " login successful");
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("view/MainMenu.fxml"));
                Parent parent = fxmlLoader.load();
                MainMenu main = fxmlLoader.getController();
                main.displayName();
                Stage newWindow = new Stage();
                newWindow.setTitle("Appointment Scheduler");
                newWindow.setScene(new Scene(parent));
                newWindow.show();
                Stage currentWindow = (Stage) LoginButton.getScene().getWindow();
                currentWindow.close();
                MainMenu.appointmentAlert();
            } catch (IOException error) {
                error.printStackTrace();
            }
        } else {
            Alert noMatch = new Alert(Alert.AlertType.ERROR);
            successfulLogin=false;
            String failedUser=userName.getText();
            if(failedUser.length() == 0){
                failedUser= "Unknown";
            }
            logger.log(Level.INFO, "User " + failedUser + " login unsuccessful");
            if (currentLocale.getLanguage().equals("en")) {
                noMatch.setContentText("Username or password not found.");
            } else if (currentLocale.getLanguage().equals("fr")) {
                ResourceBundle rb = ResourceBundle.getBundle("ResourceBundle/FranceResource_fr_FR", Locale.getDefault());
                ((Button) noMatch.getDialogPane().lookupButton(ButtonType.OK)).setText(rb.getString("OK"));
                noMatch.setContentText(rb.getString("UserNotFound"));
            }
            noMatch.show();

        }

    }


    /**
     *
     * The method initializes the methods for set up.
     * <p>
     * This method sets up the information needed for the user to add an appointment using the interface Initializable
     *  which helps call the methods after the controller has been initialized after the root element has processed.
     *  </p>
     * @param location The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resourceBundle The resources used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL location, ResourceBundle resourceBundle) {
      
       regionListener();
        createLog();
        timeNow();

    }
}
