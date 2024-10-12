package com.mycompany.employee_managment_system;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class PrimaryController {

    @FXML
    private Button close;

    @FXML
    private Button loginBtn;

    @FXML
    private StackPane mainform;

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;

    // Database Tools
    private Connection conn;
    private PreparedStatement pst;
    private ResultSet rst;

    private double x = 0;
    private double y = 0;

    @FXML
    void close(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void loginAdmin(ActionEvent event) {
        String sql = "SELECT * FROM admin WHERE username=? and password=?";
        
        // Creating an instance of the database class
        database db = new database();
        conn = db.ConnectDB();

        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, username.getText());
            pst.setString(2, password.getText());

            rst = pst.executeQuery();

            Alert alert;
            if (username.getText().isEmpty() || password.getText().isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill in the blank fields");
                alert.showAndWait();
            } else {
                if (rst.next()) {
                    
                    getData.username=username.getText();
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successful login");
                    alert.showAndWait();

                    loginBtn.getScene().getWindow().hide();

                    Parent root = FXMLLoader.load(getClass().getResource("/fxml/dashboard.fxml"));
                    Stage stage = new Stage();
                    Scene scene = new Scene(root);

                    // Set mouse events for dragging the window
                    root.setOnMousePressed((MouseEvent mouseEvent) -> {
                        x = mouseEvent.getSceneX();
                        y = mouseEvent.getSceneY();
                    });

                    root.setOnMouseDragged((MouseEvent mouseEvent) -> {
                        stage.setX(mouseEvent.getScreenX() - x);
                        stage.setY(mouseEvent.getScreenY() - y);
                    });

                    stage.initStyle(StageStyle.TRANSPARENT);
                    stage.setScene(scene);
                    stage.show();

                } else {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Wrong username or password");
                    alert.showAndWait();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
}
