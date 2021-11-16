package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointment;

import java.sql.*;
import java.time.*;


/**
 * This is the Appointment Data Access Object Implementation that has database manipulation methods.
 * @author Melissa Aybar
 */
public class AppointmentsDAOImpl {
    private static final Connection connection = JDBC.getConnection();


    /**
     * The method returns all of the appointments in the database.
     * @return ObservableList list of all the appointments from the database or an empty list.
     */
    public static ObservableList<Appointment> getAllAppointments(){
        ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();

        try {
            String sqlStatement = "SELECT * FROM appointments";
            Query.makeQuery(sqlStatement);
            //Appointment appointmentResult;
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


                Appointment appointment = new Appointment();
                appointment.setAppointmentID(appointId);
                appointment.setTitle(title);
                appointment.setDescription(description);
                appointment.setLocation(location);
                appointment.setType(type);
                appointment.setStart(start);
                appointment.setEnd(end);
                appointment.setCustomerID(customerId);
                appointment.setUserID(userId);
                appointment.setContactID(contactId);

                allAppointments.add(appointment);
            }
        } catch(SQLException e){
            e.printStackTrace();
        }

       return allAppointments;

    }




    /**
     * Adds an appointment into the database.
     * @param title Appointment title
     * @param description Appointment description
     * @param location Appointment location
     * @param type Appointment type
     * @param start Appointment start
     * @param end Appointment end
     * @param createDate Appointment createDate
     * @param createdBy Appointment createdBy
     * @param lastUpdate Appointment lastUpdate
     * @param lastUpdatedBy Appointment lastUpdatedBy
     * @param customerID Appointment customerID
     * @param userID Appointment userID
     * @param contactID Appointment contactID
     */
    public static void addAppointment( String title, String description, String location,String type, LocalDateTime start, LocalDateTime end, LocalDateTime createDate, String createdBy, LocalDateTime lastUpdate, String lastUpdatedBy, int customerID, int userID, int contactID){
        try{

            String sqlStatement = "INSERT INTO appointments (Title, Description, Location, Type, Start, End, Create_Date, Created_By, Last_Update, Last_Updated_By, Customer_Id, User_ID, Contact_ID) VALUES(?,?,?,?,?,?,now(),?,now(),?,?,?,?)";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sqlStatement, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1,title);
            ps.setString(2,description);
            ps.setString(3,location);
            ps.setString(4,type);
            ps.setTimestamp(5,Timestamp.valueOf(start));
            ps.setTimestamp(6,Timestamp.valueOf(end));
            ps.setString(7,createdBy);
            ps.setString(8,lastUpdatedBy);
            ps.setInt(9,customerID);
            ps.setInt(10,userID);
            ps.setInt(11,contactID);

            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();
            rs.next();

        } catch (SQLException exception) {
            exception.printStackTrace();
        }

    }

    /**
     * This method removes an appointment from the database.
     * @param appointmentId Id of the appointment to be removed
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
                Timestamp startStamp = result.getTimestamp("Start");
                LocalDateTime start = startStamp.toLocalDateTime();
                Timestamp  endStamp = result.getTimestamp("End");
                LocalDateTime end = endStamp.toLocalDateTime();
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
            ps.setTimestamp(5,Timestamp.valueOf(officialStart));
            ps.setTimestamp(6,Timestamp.valueOf(officialEnd));
            ps.setTimestamp(7,Timestamp.valueOf(lastUpdate));
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