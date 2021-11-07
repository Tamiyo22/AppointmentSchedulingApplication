package model;

/**
 * The model class for all appointments types.
 * This is the appointments types class. It allows us to make appointment objects with their totals.
 */
public class AppointmentType {


    private String appointmentType;
    private int typeTotal;

    /**
     *
     * @param appointmentType the appointment type
     * @param typeTotal the appointment types total
     */
    public AppointmentType(String appointmentType, int typeTotal) {
        this.appointmentType = appointmentType;
        this.typeTotal = typeTotal;
    }

    /**
     * The method returns the appointment type.
     * This method accesses the type of appointment and returns it.
     * @return the appointment type
     */
    public String getAppointmentType() {
        return appointmentType;
    }

    /**
     * Sets the appointment type.
     * This method takes in an string and sets it as the type of appointment.
     * @param appointmentType string appointment
     */
    public void setAppointmentType(String appointmentType) {
        this.appointmentType = appointmentType;
    }

    /**
     * The method returns the appointment total.
     * This method accesses the total of that appointment and returns it.
     * @return the appointment total
     */
    public int getTypeTotal() {
        return typeTotal;
    }

    /**
     * Sets the appointment type total.
     * This method takes in an integer and sets it as the total amount of that type of appointment.
     * @param typeTotal int
     */
    public void setTypeTotal(int typeTotal) {
        this.typeTotal = typeTotal;
    }



}
