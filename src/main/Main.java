package main;
/**
 * @Author Melissa Aybar
 * c195 Software 2
 */


import DAO.JDBC;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import util.TimeManger;

import java.time.*;

/**
 * The class holds methods that start the program.
 * <p>
 * The Main class extends the Application class. With this class we call launch which
 * calls the arguments start and main, and begins user interaction.
 * </p>
 */

public class Main extends Application {

    /**
     * This method loads the login stage. Start method returns immediately, and loads the login stage.
     * @param primaryStage for login
     * @throws Exception e
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
        primaryStage.setTitle("Login Screen");
        primaryStage.setScene(new Scene(root, 1000, 700));
        primaryStage.show();

    }

    /**
     * This method starts and closes our database connection. The main method launches the JDBC (Java Database Connectivity) API
     * to provide data access to the program.
     * @param args launched
     */

    public static void main(String[] args) {
        ZoneId local = ZoneId.systemDefault();
        LocalDate ld = LocalDate.now();
        LocalTime lt = LocalTime.of(14,00);
        LocalDateTime ldt = LocalDateTime.of(ld,lt);
        ZonedDateTime zdt = ZonedDateTime.of(ldt, local);

        ZoneId est = ZoneId.of("America/New_York");
        ZoneId utc = ZoneId.of("UTC");
        System.out.println(TimeManger.changeTimeZone(zdt,utc));

        LocalDateTime start = TimeManger.changeTimeZone(zdt,utc).toLocalDateTime();
        System.out.println(start);
        String startStr = start.toString().replace("T"," ");
        System.out.println(startStr);
        LocalDateTime startLdt = LocalDateTime.parse(startStr.replace(" ","T"));
        ZonedDateTime zdtStart = ZonedDateTime.of(startLdt,utc);

        System.out.println(TimeManger.changeTimeZone(zdtStart,local));




        JDBC.openConnection();

        launch(args);


        JDBC.closeConnection();
    }

}




