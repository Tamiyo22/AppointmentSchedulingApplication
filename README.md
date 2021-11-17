# AppointmentSchedulingApplication
A GUI-based scheduling desktop application that pulls and creates data from a MySQL database. It works with multiple time zones and allows for multilanguage log-in for English and French speakers.

•  Appointment Scheduler for Employees, The purpose is to help employees of a global consulting organization organize their meetings, and customers
while providing their data analyst reports to analyze  in order to help the organization focus which areas need additional focus.

•  Melissa Aybar, maybar@my.wgu.edu, student application version: 3, and 11.16.21

•  IntelliJ Community 2020.01, Java SE 11.0.10), and JavaFX-SDK-11.0.2)

•  You will need mysql-connector to contact this project to a database. An IDE, I highly recommend IntelliJ. Java 11.0.10, and JavaFX 11.0.2. Make sure that you include JavaFX as
a library, and connect to your MySQL database to fully enjoy and use the project. The log in username is "test" and the password is also "test"

•  For the appointment type by month report, I created a specialized AppointmentType class for the report. When the user clicks their month of choice
the number associated with that month gets filled into the proper method for the month. The method then calls back to the loadMonthTable. The load month
table then iterates through all of the appointments, when we see our inputted month, we then grab the types for that month and their counts and store
them into a HashMap for a O(1) retrieval and insert time. After our HashMap has all of the key value pairs, I used the for each lambda method to access
each pair, and create Appointment type objects. I choose the lambda expression forEach because I thought it was the cleaner and less bulky way to instantiate
 the implementation of the AppointmentType class objects. The objects which are then input into the types Observable List, which I then used to set the table for the user to view.

•  mysql-connector-java-8.1.23


Log in Screen
![image](https://user-images.githubusercontent.com/30645979/142281468-bffee522-3ad0-473f-9f83-b61f2feba53a.png)

Appointments
![image](https://user-images.githubusercontent.com/30645979/142277779-f85bbd78-097d-42e2-a324-07ee2b8ba5e5.png)

In all time zones. world wide.
EST
![image](https://user-images.githubusercontent.com/30645979/142281734-556dafd4-6900-4f8a-b4a0-b86a122f6b59.png)

HST
![image](https://user-images.githubusercontent.com/30645979/142281920-4a0e9f87-bcda-41d5-a745-22e4b97ab585.png)

GMT
![image](https://user-images.githubusercontent.com/30645979/142282184-0ebdefa4-34c6-4329-8ec2-7f5033be39b9.png)


All fields need to be filled
![image](https://user-images.githubusercontent.com/30645979/142278058-d8db5e53-3f03-45c0-b4f7-25825351cbf3.png)

If you have an appointment
![image](https://user-images.githubusercontent.com/30645979/142278255-9bed938b-465d-4839-8c83-20653ecc2fef.png)


Adding appointment with a customer that already has an appointment
![image](https://user-images.githubusercontent.com/30645979/142278733-939e8f01-5aec-4198-8ac9-c8f99e4ea15a.png)

Error
![image](https://user-images.githubusercontent.com/30645979/142278775-029cbbed-65e4-4e4e-8ae1-bea1bda5efed.png)

Contact Appointment Reports
![image](https://user-images.githubusercontent.com/30645979/142278986-40996c64-f8af-468a-b500-e1bd59ea1917.png)

Location Appointment Reports
![image](https://user-images.githubusercontent.com/30645979/142279084-60168e0f-6a82-4b45-a8ff-4994c877e712.png)

Appointment Type By Month Report
![image](https://user-images.githubusercontent.com/30645979/142281118-21556cd5-6d6b-4ad6-be57-d0c31b39d33b.png)
![image](https://user-images.githubusercontent.com/30645979/140667700-9d4419ef-a1fc-4bed-8992-fba7585ec16d.png)
