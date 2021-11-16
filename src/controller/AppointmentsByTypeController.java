package controller;
import DAO.AppointmentsDAOImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.scene.layout.AnchorPane;
import model.Appointment;
import model.AppointmentType;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

/**
 * This class has the methods for the Appointment by Types by Month report.
 * <p>
 * The AppointmentsByTypeController extends the AppointmentsController class and implements the Initializable inerface.
 * By inheriting from the  AppointmentsController the user can navigate to other stages as they could with the MainMenu, and have
 * access to all of the AppointmentsController methods.The AppointmentsByTypeController class handles the elements needed for the user to
 * view the appointment data from the types of appointments on click of the month button.
 * </p>
 */

public class AppointmentsByTypeController extends AppointmentsController implements Initializable {

    @FXML
    private Button AppointmentsButton;

    @FXML
    private Button August;

    @FXML
    private Button CustomerButton;

    @FXML
    private Label Date;

    @FXML
    private Button December;

    @FXML
    private Button FebruaryButton;

    @FXML
    private Button Home;

    @FXML
    private Button JanuaryButton;

    @FXML
    private Button JulyButton;

    @FXML
    private Button JuneButton;

    @FXML
    private Button Logout;

    @FXML
    private Button MarchButton;

    @FXML
    private Button MayButton;

    @FXML
    private AnchorPane Navigation;

    @FXML
    private Button NovemberButton;

    @FXML
    private Button OctoberButton;

    @FXML
    private Button SeptemberButton;

    @FXML
    private Label Time;

    @FXML
    private Label Zone;

    @FXML
    private TableColumn<AppointmentType, Integer> amount;

    @FXML
    private TableColumn<AppointmentType, String> appointType;


    @FXML
    private Label userGreeting;


    @FXML
    private Label typeName;

    @FXML
    private TableView<AppointmentType> typesTable;





    ObservableList<AppointmentType> appointments = FXCollections.observableArrayList();
    ObservableList<AppointmentType> types = FXCollections.observableArrayList();



    /**
     * The method sets the table with appointments from a month, their types and their type counts.
     * <p>
     * This method calls the getAllAppointments() method from the Appointment Data Access Object, which
     * loads all of the appointments. From there we use a hashmap to add to the count of each appointment type of that month.
     * From there we use lambda function for each to trim down the code of taking each element from the storage hashmap
     * creating a AppointmentType object from it, and adding that element to the types observable list and then setting
     * those values to the main table to be viewed by the user.
     * </p>
     * @param monthNum int associated with the month.
     */

public void loadMonthTable(int monthNum){

    HashMap<String,Integer> storage = new HashMap<>();

    for (Appointment appointment : AppointmentsDAOImpl.getAllAppointments()) {

        if (appointment.getStart().getMonthValue() == monthNum) {
            String current = appointment.getType();
            if(!storage.containsKey(current)){
                storage.put(appointment.getType(),1);
            }else {
                int oldValue = storage.get(current);
                storage.replace(current,oldValue+1);
            }
        }
    }
    storage.forEach((type,total)-> types.add(new AppointmentType(type,total)));

    typesTable.setItems(types);

}

    /**
     * The method sets the typesTable with January appointment type counts.
     * <p>
     * This method calls the loadMonthTable with the associated number for January to set the typesTable with
     *  January appointment type counts.
     * </p>
     * @param event on January button click.
     */

    @FXML
    public void loadJanuaryTable(ActionEvent event)  {

        types.clear();
        loadMonthTable( 1);

        typeName.setText(" You are viewing appointment types in January");
        typeName.setStyle("-fx-background-color:  #4285F4; -fx-text-fill: white;");

    }

    /**
     * The method sets the typesTable with February appointment type counts.
     * <p>
     * This method calls the loadMonthTable with the associated number for February to set the typesTable with
     *  February appointment type counts.
     * </p>
     * @param event on February button click.
     */

    @FXML
    public void loadFebruaryTable(ActionEvent event) {
        types.clear();
        loadMonthTable( 2);

        typeName.setText(" You are viewing appointment types in February");
        typeName.setStyle("-fx-background-color:  #EA4335; -fx-text-fill: white;");
    }

    /**
     * The method sets the typesTable with March appointment type counts.
     * <p>
     * This method calls the loadMonthTable with the associated number for March to set the typesTable with
     *  March appointment type counts.
     * </p>
     * @param event
     */

    @FXML
    public void loadMarchTable(ActionEvent event)  {
        types.clear();
        loadMonthTable( 3);

        typeName.setText(" You are viewing appointment types in March");
        typeName.setStyle("-fx-background-color: #34A853; -fx-text-fill: white;");
    }

    /**
     * The method sets the typesTable with April appointment type counts.
     * <p>
     * This method calls the loadMonthTable with the associated number for April to set the typesTable with
     *  April appointment type counts.
     * </p>
     * @param event
     */
    @FXML
    public void loadAprilTable(ActionEvent event)  {
        types.clear();
        loadMonthTable( 3);

        typeName.setText(" You are viewing appointment types in April");
        typeName.setStyle("-fx-background-color:  #FBBC05; -fx-text-fill: white;");
    }

    /**
     * The method sets the typesTable with May appointment type counts.
     * <p>
     * This method calls the loadMonthTable with the associated number for May to set the typesTable with
     *  May appointment type counts.
     * </p>
     * @param event
     */
    @FXML
    public void loadMayTable(ActionEvent event)  {

        types.clear();
        loadMonthTable( 5);

        typeName.setText(" You are viewing appointment types in May");
        typeName.setStyle("-fx-background-color: #34A853; -fx-text-fill: white;");
    }

    /**
     * The method sets the typesTable with June appointment type counts.
     * <p>
     * This method calls the loadMonthTable with the associated number for June to set the typesTable with
     *  June appointment type counts.
     * </p>
     * @param event
     */
    @FXML
    public void loadJuneTable(ActionEvent event)  {
        types.clear();

        loadMonthTable( 6);

        typeName.setText(" You are viewing appointment types in June");
        typeName.setStyle("-fx-background-color:  #FBBC05; -fx-text-fill: white;");

    }

    /**
     * The method sets the typesTable with July appointment type counts.
     * <p>
     * This method calls the loadMonthTable with the associated number for July to set the typesTable with
     *  July appointment type counts.
     * </p>
     * @param event
     */
    @FXML
    public void loadJulyTable(ActionEvent event) {
        types.clear();

        loadMonthTable( 7);

        typeName.setText(" You are viewing appointment types in July");
        typeName.setStyle("-fx-background-color:  #EA4335; -fx-text-fill: white;");
    }

    /**
     * The method sets the typesTable with August appointment type counts.
     * <p>
     * This method calls the loadMonthTable with the associated number for August to set the typesTable with
     * August appointment type counts.
     * </p>
     * @param event
     */
    @FXML
    public void loadAugustTable(ActionEvent event) {

        types.clear();
        loadMonthTable(8);

        typeName.setText(" You are viewing appointment types in August");
        typeName.setStyle("-fx-background-color:  #4285F4; -fx-text-fill: white;");

    }

    /**
     * The method sets the typesTable with September appointment type counts.
     * <p>
     * This method calls the loadMonthTable with the associated number for September to set the typesTable with
     * September appointment type counts.
     * </p>
     * @param event
     */
    @FXML
    public void loadSeptemberTable(ActionEvent event)  {

        types.clear();
        loadMonthTable( 9);

        typeName.setText(" You are viewing appointment types in September");
        typeName.setStyle("-fx-background-color:  #4285F4; -fx-text-fill: white;");
    }

    /**
     * The method sets the typesTable with October appointment type counts.
     * <p>
     * This method calls the loadMonthTable with the associated number for October to set the typesTable with
     * October appointment type counts.
     * </p>
     * @param event
     */
    @FXML
    public void loadOctoberTable(ActionEvent event)  {

        types.clear();
        loadMonthTable( 10);

        typeName.setText(" You are viewing appointment types in October");
        typeName.setStyle("-fx-background-color: #34A853; -fx-text-fill: white;");
    }

    /**
     * The method sets the typesTable with November appointment type counts.
     * <p>
     * This method calls the loadMonthTable with the associated number for November to set the typesTable with
     * November appointment type counts.
     * </p>
     * @param event
     */
    @FXML
    public void loadNovemberTable(ActionEvent event) {

        types.clear();
        loadMonthTable( 11);

        typeName.setText(" You are viewing appointment types in November");
        typeName.setStyle("-fx-background-color:  #FBBC05; -fx-text-fill: white;");

    }

    /**
     * The method sets the typesTable with December appointment type counts.
     * <p>
     * This method calls the loadMonthTable with the associated number for December to set the typesTable with
     * December appointment type counts.
     * </p>
     * @param event
     */
    @FXML
    public void loadDecemberTable(ActionEvent event)  {

        types.clear();
        loadMonthTable( 12);

        typeName.setText(" You are viewing appointment types in December");
        typeName.setStyle("-fx-background-color:  #EA4335; -fx-text-fill: white;");
    }

    /**
     * The method sets up the elements needed for the table.
     * <p>
     * This method takes the fx ids from the stage and sets the values from the
     * to them once the stage is loaded.
     * </p>
     * @throws NullPointerException
     */
    @FXML
    public void loadTypesByMonthTable() throws NullPointerException {

        try {

            appointType.setCellValueFactory(new PropertyValueFactory<>("appointmentType"));
            amount.setCellValueFactory(new PropertyValueFactory<>("typeTotal"));

            appointments.addAll(types);

            typesTable.setItems(appointments);

        } catch (NullPointerException e) {
            System.out.println(e);

        }

    }
    /**
     * The method initializes the methods for set up.
     * <p>
     *  This method sets up the information needed for the user to view appointment types tables using the interface Initializable
     *  which helps call the methods after the controller has been initialized after the root element has processed.
     * </p>
     * @param location The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resourceBundle The resources used to localize the root object, or null if the root object was not localized.
     */

    @Override
    public void initialize(URL location, ResourceBundle resourceBundle) {


        loadTypesByMonthTable();
        displayName();


    }

}
