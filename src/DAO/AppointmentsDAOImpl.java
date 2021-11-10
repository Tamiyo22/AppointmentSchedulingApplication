package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointment;
import model.Times;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * This is the Appointment Data Access Object Implementation that has database manipulation methods.
 * The Appointment Data Access Object or Appointment DAO allows us to have data operations without exposing the
 * details of the database. The data operations here assist with our appointment data.
 */
public class AppointmentsDAOImpl {
    private static final Connection connection = JDBC.getConnection();

    /**
     * The method returns all of the appointments in the database.
     * This method returns an ObservableList of all the appointments from the database.
     * I convert the UTC appointment times saved on the database into local user-friendly times.
     * @return ObservableList
     */
    public static ObservableList<Appointment> getAllAppointments(){
        ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();

        try {
            String sqlStatement = "SELECT * FROM appointments";
            Query.makeQuery(sqlStatement);
            Appointment appointmentResult;
            ResultSet result = Query.getResult();



            while (result.next()) {
                int appointId = result.getInt("Appointment_ID");
                String title = result.getString("Title");
                String description = result.getString("Description");
                String location = result.getString("Location");
                String type = result.getString("Type");
                LocalDateTime start = result.getTimestamp("Start").toLocalDateTime();
                LocalDateTime end = result.getTimestamp("End").toLocalDateTime();
                int customerId = result.getInt("Customer_ID");
                int userId = result.getInt("User_ID");
                int contactId = result.getInt("Contact_ID");

                appointmentResult = new Appointment(appointId,title,description, location, type, start, end, customerId, userId,contactId);
                allAppointments.add(appointmentResult);
            }
        } catch(SQLException e){
            e.printStackTrace();
        }

        //setting up for user-friendly time viewing in their own zone

        for(Appointment appointment : allAppointments){


            LocalDateTime start = Times.convertToLocalDateTime(appointment.getStart());
            LocalDateTime end = Times.convertToLocalDateTime(appointment.getEnd());

            appointment.setStart(start);
            appointment.setEnd(end);

        }

        return allAppointments;

    }

/**
 * The method sets up the data needed for the contacts appointment table.
 * This method queries the database and selects all of the data  for contacts appointment table.
 *
 */

public static ObservableList<Appointment> getAllContactAppointments(){
    ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();

    try {
        String sqlStatement = "SELECT * FROM appointments";
        Query.makeQuery(sqlStatement);
        Appointment appointmentResult;
        ResultSet result = Query.getResult();



        while (result.next()) {
            int appointId = result.getInt("Appointment_ID");
            String title = result.getString("Title");
            String description = result.getString("Description");
            String type = result.getString("Type");
            LocalDateTime start = result.getTimestamp("Start").toLocalDateTime();
            LocalDateTime end = result.getTimestamp("End").toLocalDateTime();
            int customerId = result.getInt("Customer_ID");

            appointmentResult = new Appointment(appointId,title,description, type, start, end, customerId);
            allAppointments.add(appointmentResult);
        }
    } catch(SQLException e){
        e.printStackTrace();
    }

    //setting up for user-friendly time viewing in their own zone

    for(Appointment appointment : allAppointments){


        LocalDateTime start = Times.convertToLocalDateTime(appointment.getStart());
        System.out.println(start);
        LocalDateTime end = Times.convertToLocalDateTime(appointment.getEnd());

        appointment.setStart(start);
        System.out.println(appointment.getStart());
        appointment.setEnd(end);

    }

    return allAppointments;

}



    /**
     * I take in the inputted data from our GUI and via our AddAppointmentController and load it here onto the database
     * @param title appointment title
     * @param description appointment description
     * @param location appointment location
     * @param type appointment type
     * @param start appointment start
     * @param end appointment end
     * @param createDate appointment createDate
     * @param createdBy appointment createdBy
     * @param lastUpdate appointment lastUpdate
     * @param lastUpdatedBy appointment lastUpdatedBy
     * @param customerID appointment customerID
     * @param userID appointment userID
     * @param contactID appointment contactID
     */


    public static void addAppointment( String title, String description, String location,String type, LocalDateTime start, LocalDateTime end, LocalDateTime createDate, String createdBy, LocalDateTime lastUpdate, String lastUpdatedBy, int customerID, int userID, int contactID){
        try{

            String sqlStatement = "INSERT INTO appointments VALUES(NULL,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sqlStatement, Statement.RETURN_GENERATED_KEYS);

            ZoneId utc = ZoneId.of("UTC");


            ps.setString(1,title);
            ps.setString(2,description);
            ps.setString(3,location);
            ps.setString(4,type);
            ps.setTimestamp(5,Timestamp.valueOf(Times.convertToUTC(start)));
            ps.setTimestamp(6,Timestamp.valueOf(Times.convertToUTC(end)));
            ps.setTimestamp(7,Timestamp.valueOf(Times.convertToUTC(createDate)));
            ps.setString(8,createdBy);
            ps.setTimestamp(9,Timestamp.valueOf(Times.convertToUTC(lastUpdate)));
            ps.setString(10,lastUpdatedBy);
            ps.setInt(11,customerID);
            ps.setInt(12,userID);
            ps.setInt(13,contactID);

            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();
            rs.next();

        } catch (SQLException exception) {
            exception.printStackTrace();
        }

    }

    /**
     * This method deletes an appointment from the data base.
     * I take the selected appointment via our appointments stage using our AppointmentsController, and
     * load that information here in order to delete the proper appointment from the database.
     * @param appointmentId id
     */

    public static void deleteAppointment(int appointmentId){
        try{

            String sqlStatement = "DELETE FROM appointments  WHERE Appointment_ID = '" + appointmentId+ "'";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sqlStatement);

            ps.execute();

        }catch (SQLException exception) {
            exception.printStackTrace();
        }

    }

    /**
     * This method returns an appointment by its id input.
     * In this method we select the appointment by its id from the database and return all of its information.
     * @param appointmentID
     * @return appointment
     *
     */
    public static Appointment getAppointment(int appointmentID)  {
        JDBC.getConnection();
        String sqlStatement="select * FROM appointments WHERE Appointment_ID = '" + appointmentID+ "'";
        Query.makeQuery(sqlStatement);
        Appointment appointmentResult = null;
        ResultSet result = Query.getResult();

        try {
            while (result.next()) {
                int appointId = result.getInt("Appointment_ID");
                String title = result.getString("Title");
                String description = result.getString("Description");
                String location = result.getString("Location");
                String type = result.getString("Type");
                LocalDateTime start = result.getTimestamp("Start").toLocalDateTime();
                LocalDateTime end = result.getTimestamp("End").toLocalDateTime();
                int customerId = result.getInt("Customer_ID");
                int userId = result.getInt("User_ID");
                int contactId = result.getInt("Contact_ID");

                appointmentResult = new Appointment(appointId , title, description, location, type , start, end, customerId,userId,contactId );
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return appointmentResult;
    }


    /**
     * The method updates the appointment.
     * Using the sql command SET, we set the database with updated data.
     * @param appointmentID appointmentI
     * @param title title
     * @param description description
     * @param location location
     * @param type type
     * @param officialStart officialStart
     * @param officialEnd officialEnd
     * @param lastUpdate lastUpdate
     * @param lastUpdatedBy lastUpdatedBy
     * @param customerID customerID
     * @param userID userID
     * @param contactID contactID
     */
    public static void updateAppointment(int appointmentID,String title, String description, String location, String type,  LocalDateTime officialStart,  LocalDateTime officialEnd ,  LocalDateTime  lastUpdate, String lastUpdatedBy, int  customerID, int userID, int contactID){
        try{

            String sqlStatement = "UPDATE appointments SET  Title = ? , Description = ? , Location = ? , Type = ? , Start = ? , End =?, Last_Update = ?,   Last_Updated_By =? , Customer_ID = ?, User_ID = ?, Contact_ID = ? WHERE Appointment_ID = '" + appointmentID+ "'";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sqlStatement);


            ps.setString(1,title);
            ps.setString(2,description);
            ps.setString(3,location);
            ps.setString(4,type);
            ps.setTimestamp(5,Timestamp.valueOf(Times.convertToUTC(officialStart)));
            ps.setTimestamp(6,Timestamp.valueOf(Times.convertToUTC(officialEnd)));
            ps.setTimestamp(7,Timestamp.valueOf(Times.convertToUTC(lastUpdate)));
            ps.setString(8,lastUpdatedBy);
            ps.setInt(9,customerID);
            ps.setInt(10,userID);
            ps.setInt(11,contactID);

            ps.execute();

        }catch (SQLException exception) {
            exception.printStackTrace();
        }

    }


}
