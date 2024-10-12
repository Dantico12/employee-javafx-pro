package com.mycompany.employee_managment_system;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private double x = 0;
    private double y = 0;

    @Override
    public void start(Stage stage) throws IOException {
        // Initialize the window style to be transparent
        stage.initStyle(StageStyle.TRANSPARENT);

        // Load the primary FXML file and create the scene
        scene = new Scene(loadFXML("primary"), 640, 480);

        // Load CSS files
        String loginCss = getClass().getResource("/com/mycompany/employee_managment_system/LoginDesign.css") != null
            ? getClass().getResource("/com/mycompany/employee_managment_system/LoginDesign.css").toExternalForm()
            : null;

        String dashboardCss = getClass().getResource("/com/mycompany/employee_managment_system/dashboardDesign.css") != null
            ? getClass().getResource("/com/mycompany/employee_managment_system/dashboardDesign.css").toExternalForm()
            : null;

        if (loginCss == null) {
            System.out.println("LoginDesign.css not found!");
        } else {
            scene.getStylesheets().add(loginCss); // Add CSS stylesheet if found
        }

        if (dashboardCss == null) {
            System.out.println("dashboardDesign.css not found!");
        } else {
            scene.getStylesheets().add(dashboardCss); // Add CSS stylesheet if found
        }

        // Handle mouse press events to record the initial mouse position
        scene.setOnMousePressed((MouseEvent event) -> {
            x = event.getSceneX();
            y = event.getSceneY();
        });

        // Handle mouse drag events to move the window along with the mouse
        scene.setOnMouseDragged((MouseEvent event) -> {
            stage.setX(event.getScreenX() - x);
            stage.setY(event.getScreenY() - y);
            stage.setOpacity(0.8); // Make window semi-transparent while dragging
        });

        // Restore window opacity when the mouse is released
        scene.setOnMouseReleased((MouseEvent event) -> {
            stage.setOpacity(1);
        });

        // Set the scene and show the stage
        stage.setScene(scene);
        stage.show();
    }

    // Change the root of the scene dynamically
    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    // Load the FXML file for the given name
    private static Parent loadFXML(String fxml) throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
            return fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace(); // Print stack trace for debugging
            throw new IOException("Failed to load FXML: " + fxml, e);
        }
    }

    // Main entry point for the application
    public static void main(String[] args) {
        launch();
    }
}
