# AppointmentSchedulingApplication
A GUI-based scheduling desktop application that pulls and creates data from a MySQL database. It works with multiple time zones and allows for multilanguage log-in for English and French speakers.

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

•  mysql-connector-java-8.1.23


Log in Screen
![image](https://user-images.githubusercontent.com/30645979/140667347-a04c3e62-46ec-47d1-af34-b157e4047cca.png)

![image](https://user-images.githubusercontent.com/30645979/140667383-ba55a1cb-feb5-4d3d-94b4-004c2ca7eade.png)

![image](https://user-images.githubusercontent.com/30645979/140667402-ed7628c4-c32d-43df-ba5d-ff7a676d8af6.png)

![image](https://user-images.githubusercontent.com/30645979/140667423-6f4c505e-7a95-484c-828a-89c0949aa017.png)


![image](https://user-images.githubusercontent.com/30645979/140667477-28754026-059a-4dc9-abf1-5552705749ec.png)

![image](https://user-images.githubusercontent.com/30645979/140667539-ccc7324e-6b29-47a1-aef7-d2349337a466.png)

![image](https://user-images.githubusercontent.com/30645979/140667558-83a1f5d1-356e-4c1c-8b6a-1328c17e29dd.png)

![image](https://user-images.githubusercontent.com/30645979/140667599-934cebd3-1b1d-4018-886f-7a62facb0535.png)


![image](https://user-images.githubusercontent.com/30645979/140667613-c9571b1f-4f61-4739-a5ee-c1d2dae16450.png)

![image](https://user-images.githubusercontent.com/30645979/140667633-fa8cf7dd-893e-43f4-b598-4396dc68ce11.png)

![image](https://user-images.githubusercontent.com/30645979/140667677-0b8cf55f-88c2-4278-a955-07e3fc2e10b2.png)

![image](https://user-images.githubusercontent.com/30645979/140667700-9d4419ef-a1fc-4bed-8992-fba7585ec16d.png)
