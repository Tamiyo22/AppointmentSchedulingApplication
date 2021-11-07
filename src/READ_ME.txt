•  Appointment Scheduler for Employees, The purpose is to help employees of a global consulting organization organize their meetings, and customers
while providing their data analyst reports to analyze  in order to help the organization focus which areas need additional focus.

•  Melissa Aybar, maybar@my.wgu.edu, student application version: 3, and 10.31.21

•  IntelliJ Community 2020.01, Java SE 11.0.10), and JavaFX-SDK-11.0.2)

•  You will need mysql-connector to contact this project to a database. An IDE, I highly recommend IntelliJ. Java 11.0.10, and JavaFX 11.0.2. Make sure that you include JavaFX as
a library, and connect to your MySQL database to fully enjoy and use the project. The log in username is "test" and the password is also "test"

•  For the appointment type by month report, I created a specialized AppointmentType class for the report. When the user clicks their month of choice
the number associated with that month gets filled into the proper method for the month. The method then calls back to the loadMonthTable. The load month
table then iterates through all of the appointments, when we see our inputted month, we then grab the types for that month and their counts and store
them into a HashMap for a O(1) retrieval and insert time. After our HashMap has all of the key value pairs, I used the for each lambda method to access
each pair, and create Appointment type objects. I choose the lambda expression forEach because I thought it was the cleaner and less bulky way to instantiate
 the implementation of the AppointmentType class objects. The objects which are then input into the types Observable List, which I then used to set the table for the user to view.

•   mysql-connector-java-8.1.23