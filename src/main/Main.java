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



        JDBC.openConnection();

        launch(args);


        JDBC.closeConnection();
    }

}




