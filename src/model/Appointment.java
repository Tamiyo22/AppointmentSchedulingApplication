package model;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The model class for all appointments.
 * This is the appointments class. It models the base of what every appointment should be capable of having and doing.
 */

public class Appointment {

    private int appointmentID;
    private String title;
    private String description;
    private String  location;
    private String type;
    private LocalDateTime start;
    private LocalDateTime end;
    private LocalDateTime createDate;
    private String createdBy;
    private LocalDateTime lastUpdate;
    private LocalDateTime lastUpdatedBy;
    private int customerID;
    private int userID;
    private int contactID;


    public Appointment(){};
    /**
     this constructor is used for setting the main table on the appointments form

     @param title appointment title
      * @param description appointment description
     * @param location appointment location
     * @param type appointment type
     * @param start appointment start time
     * @param end appointment end time
     * @param customerID appointment customer id
     * @param userID appointment userID
     * @param contactID appointment contact id
     */

    public Appointment(int appointmentID, String title, String description, String location, String type, LocalDateTime  start, LocalDateTime end, int customerID, int userID, int contactID) {
        this.appointmentID = appointmentID;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.start = start;
        this.end = end;
        this.customerID = customerID;
        this.userID = userID;
        this.contactID = contactID;
    }

    /**
     * this constructor is used when adding appointments
     * @param  title title
     * @param description description
     * @param location location
     * @param type type
     * @param start start
     * @param end end
     * @param createDate createDate
     * @param createdBy createdBy
     * @param lastUpdate lastUpdate
     * @param lastUpdatedBy lastUpdatedBy
     * @param customerID customerID
     * @param userID userID
     * @param contactID contactID
     */

    public Appointment( String title, String description, String location, String type, LocalDateTime start, LocalDateTime end, LocalDateTime createDate, String createdBy, LocalDateTime lastUpdate, LocalDateTime lastUpdatedBy, int customerID, int userID, int contactID) {
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.start = start;
        this.end = end;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
        this.customerID = customerID;
        this.userID = userID;
        this.contactID = contactID;
    }


    /**
     * this constructor is used for a complete appointment add
     * @param appointmentID appointment id
     * @param title appointment title
     * @param description appointment description
     * @param location appointment location
     * @param type appointment type
     * @param start appointment type
     * @param end appointment end
     * @param createDate appointment create date
     * @param createdBy appointment created by
     * @param lastUpdate appointment last update
     * @param lastUpdatedBy appointment last updated by
     * @param customerID appointment customer id
     * @param userID appointment user id
     * @param contactID appointment contact id
     */
    public Appointment(int appointmentID, String title, String description, String location, String type, LocalDateTime  start, LocalDateTime end, LocalDateTime createDate, String createdBy, LocalDateTime lastUpdate, LocalDateTime lastUpdatedBy, int customerID, int userID, int contactID) {
        this.appointmentID = appointmentID;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.start = start;
        this.end = end;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
        this.customerID = customerID;
        this.userID = userID;
        this.contactID = contactID;
    }



    /**
     * this constructor was created to help with the information needed for contact meetings
     * @param appointmentID appointment id
     * @param title appointment title
     * @param description appointment description
     * @param type appointment type
     * @param start appointment start time
     * @param end appointment end time
     * @param customerID appointment  customer id
     */

    public Appointment(int appointmentID, String title,String description, String type, LocalDateTime start, LocalDateTime end, int customerID) {
        this.appointmentID = appointmentID;
        this.title = title;
        this.description = description;
        this.type = type;
        this.start = start;
        this.end = end;
        this.customerID = customerID;

    }


    /**
     * The method returns the appointment ID.
     * This method accesses the appointmentID and returns it.
     * @return the appointmentID.
     */
    public int getAppointmentID() {
        return appointmentID;
    }

    /**
     * The method takes in the id and sets it.
     * This method takes in a integer and sets it to be a appointmentid.
     * @param appointmentID id to set.
     */
    public void setAppointmentID(int appointmentID) {
        this.appointmentID = appointmentID;
    }

    /**
     * The method returns the appointment title.
     * This method accesses the appointment title and returns it.
     * @return the title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * The method takes in the title and sets it.
     * This method takes in a string and sets it to be a title.
     * @param title id to set.
     */
    public void setTitle(String title) {
        this.title = title;
    }


    /**
     * The method returns the appointment Description.
     * This method accesses the Description and returns it.
     * @return Description.
     */
    public String getDescription() {
        return description;
    }


    /**
     * The method takes in the Description and sets it.
     * This method takes in a string and sets it to be a Description.
     * @param description becomes Description.
     */
    public void setDescription(String description) {
        this.description = description;
    }


    /**
     * The method returns the appointment Location.
     * This method accesses the Location and returns it.
     * @return Location.
     */
    public String getLocation() {
        return this.location;
    }


    /**
     * The method takes in the location and sets it.
     * This method takes in a string and sets it to be a appointment location.
     * @param location becomes Location.
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * The method returns the appointment Type.
     * This method accesses the Type and returns it.
     * @return Type.
     */
    public String getType() {
        return type;
    }

    /**
     * The method takes in the type and sets it.
     * This method takes in a string and sets it to be a appointment type.
     * @param type becomes type.
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * The method returns the appointment start time.
     * This method accesses the start time and returns it.
     * @return start time.
     */
    public LocalDateTime getStart() {
        return start;
    }

    /**
     * The method takes in the start time and sets it.
     * This method takes in a start time and sets it to be a appointment start time.
     * @param start becomes start time.
     */
    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    /**
     * The method returns the appointment end time.
     * This method accesses the end time and returns it.
     * @return end time.
     */
    public LocalDateTime getEnd() {
        return end;
    }

    /**
     * The method takes in the end time and sets it.
     * This method takes in a end time and sets it to be a appointment end time.
     * @param end becomes end time.
     */
    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    /**
     * The method returns the appointment Create Date.
     * This method accesses the Create Date and returns it.
     * @return Create Date.
     */
    public LocalDateTime getCreateDate() {
        return createDate;
    }
    /**
     * The method takes in the date time and sets it.
     * This method takes in a  Local Date Time and sets it to be a appointment date.
     * @param createDate becomes appointment date.
     */
    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    /**
     * The method returns who created the appointment.
     * This method accesses who created the appointment and returns it.
     * @return CreatedBy.
     */
    public String getCreatedBy() {
        return createdBy;
    }
    /**
     * The method takes in the createdBy and sets it.
     * This method takes in a createdBy and sets it to be a appointment createdBy.
     * @param createdBy becomes createdBy.
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
    /**
     * The method returns the appointment LastUpdate.
     * This method accesses the appointment LastUpdate and returns it.
     * @return LastUpdate.
     */
    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }
    /**
     * The method takes in the lastUpdate and sets it.
     * This method takes in a lastUpdate value and sets it to be a appointment lastUpdate.
     * @param lastUpdate becomes lastUpdate.
     */
    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
    /**
     * The method returns the appointment LastUpdatedBy.
     * This method accesses the appointment LastUpdatedBy and returns it.
     * @return LastUpdatedBy.
     */
    public LocalDateTime getLastUpdatedBy() {
        return lastUpdatedBy;
    }
    /**
     * The method takes in the LastUpdatedBy and sets it.
     * This method takes in a LastUpdatedBy value and sets it to be a appointment LastUpdatedBy.
     * @param lastUpdatedBy becomes LastUpdatedBy.
     */
    public void setLastUpdatedBy(LocalDateTime lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }
    /**
     * The method returns the appointment CustomerID.
     * This method accesses the appointment CustomerID and returns it.
     * @return CustomerID.
     */
    public int getCustomerID() {
        return customerID;
    }
    /**
     * The method takes in the customerID and sets it.
     * This method takes in a customerID value and sets it to be a appointment customerID.
     * @param customerID becomes LastUpdatedBy.
     */
    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    /**
     * The method returns the appointment UserID.
     * This method accesses the appointment UserID and returns it.
     * @return UserID.
     */
    public int getUserID() {
        return userID;
    }

    /**
     * The method takes in the userID and sets it.
     * This method takes in a userID value and sets it to be a appointment userID.
     * @param userID becomes LastUpdatedBy.
     */
    public void setUserID(int userID) {
        this.userID = userID;
    }
    /**
     * The method returns the appointment ContactID.
     * This method accesses the appointment ContactID and returns it.
     * @return ContactID.
     */
    public int getContactID() {
        return contactID;
    }
    /**
     * The method takes in the contactID and sets it.
     * This method takes in a contactID value and sets it to be a appointment contactID.
     * @param contactID becomes contactID.
     */
    public void setContactID(int contactID) {
        this.contactID = contactID;
    }

    public String getZonedStart(){
        String formattedString = "";
        DateTimeFormatter timeZoneName = DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm z");
        ZonedDateTime zonedStart = start.atZone(ZoneId.systemDefault());
        formattedString = timeZoneName.format(zonedStart);

        return formattedString;
    }

    public String getZonedEnd(){
        String formattedString = "";
        DateTimeFormatter timeZoneName = DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm z");
        ZonedDateTime zonedEnd = end.atZone(ZoneId.systemDefault());
        formattedString = timeZoneName.format(zonedEnd);
        return formattedString;
    }




}
