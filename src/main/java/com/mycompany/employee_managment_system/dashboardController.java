/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.employee_managment_system;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author PC
 */
public class dashboardController implements Initializable {

    @FXML
    private AnchorPane main_form;

    @FXML
    private Button addEmployeeBtn;

    @FXML
    private Button addEmployee_DeleteBtn;

    @FXML
    private TextField addEmployee_EmployeeID;

    @FXML
    private TextField addEmployee_FirstName;

    @FXML
    private ComboBox<?> addEmployee_Gender;

    @FXML
    private TextField addEmployee_LastName;

    @FXML
    private ComboBox<?> addEmployee_Position;

    @FXML
    private Button addEmployee_addBtn;

    @FXML
    private Button addEmployee_clearBtn;

    @FXML
    private TableColumn<employeeData, String> addEmployee_dateMember;

    @FXML
    private TableColumn<employeeData, String> addEmployee_employeeID;

    @FXML
    private TableColumn<employeeData, String> addEmployee_firstName;

    @FXML
    private AnchorPane addEmployee_form;

    @FXML
    private TableColumn<employeeData, String> addEmployee_gender;

    @FXML
    private ImageView addEmployee_image;

    @FXML
    private Button addEmployee_importBtn;

    @FXML
    private TableColumn<employeeData, String> addEmployee_lastName;

    @FXML
    private TableColumn<employeeData, String> addEmployee_phone;

    @FXML
    private TextField addEmployee_phoneNum;

    @FXML
    private TableColumn<employeeData, String> addEmployee_position;

    @FXML
    private TextField addEmployee_search;

    @FXML
    private TableView<employeeData> addEmployee_tableView;

    @FXML
    private Button addEmployee_updateBtn;

    @FXML
    private Button close;

    @FXML
    private BarChart<String, Number> hom_chart;

    @FXML
    private Button home_btn;

    @FXML
    private AnchorPane home_form;

    @FXML
    private Label home_totalEmployees;

    @FXML
    private Label home_totalInactive;

    @FXML
    private Label home_totalPresent;

    @FXML
    private Button logout_btn;

    @FXML
    private TextField salary_EmployeeID;

    @FXML
    private Button salary_btn;

    @FXML
    private Button salary_clearBtn;

    @FXML
    private TableColumn<employeeData, String> salary_colEmployeeID;

    @FXML
    private TableColumn<employeeData, String> salary_colFirstName;

    @FXML
    private TableColumn<employeeData, String> salary_colLastName;

    @FXML
    private TableColumn<employeeData, String> salary_colPosition;

    @FXML
    private TableColumn<employeeData, String> salary_colSalary;

    @FXML
    private Label salary_employeeID;

    @FXML
    private Label salary_firstName;

    @FXML
    private AnchorPane salary_form;

    @FXML
    private Label salary_lastName;

    @FXML
    private Label salary_position;

    @FXML
    private TextField salary_salary;

    @FXML
    private TableView<employeeData> salary_tableView;

    @FXML
    private Button salary_updateBtn;

    @FXML
    private Label username;

    private Connection conn = null;
    private Statement st = null;
    private PreparedStatement pst = null;
    private ResultSet rst = null;

    private Image image;

    public void homeTotalEmployee() {
        String sql = "SELECT COUNT(id) AS total_count FROM employee"; // Use an alias

        database db = new database();
        conn = db.ConnectDB(); // Ensure your connection is successful
        int countData = 0;

        try {
            pst = conn.prepareStatement(sql); // Prepare the statement
            rst = pst.executeQuery(); // Execute the prepared statement

            // Check if the result set has data
            if (rst.next()) {
                countData = rst.getInt("total_count"); // Use the alias "total_count"
            }

            // Set the text in the GUI component
            home_totalEmployees.setText(String.valueOf(countData));

        } catch (SQLException e) {
            e.printStackTrace(); // Catch SQL-related errors
        } finally {
            // Proper resource management
            try {
                if (rst != null) {
                    rst.close();
                }
                if (pst != null) {
                    pst.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace(); // Handle exceptions when closing resources
            }
        }
    }

    public void addEmployeeTotalPresent() {
        String sql = "SELECT COUNT(id) AS total_count FROM employee_info;";

        database db = new database();
        conn = db.ConnectDB();  // Ensure your connection is successful
        int countData = 0;

        try {
            pst = conn.prepareStatement(sql); // Prepare the statement
            rst = pst.executeQuery(); // Execute the prepared statement

            // Check if the result set has data
            if (rst.next()) {
                countData = rst.getInt("total_count"); // Use the alias "total_count"
            }

            // Set the text in the GUI component
            home_totalPresent.setText(String.valueOf(countData));

        } catch (SQLException e) {
            e.printStackTrace(); // Catch SQL-related errors
        } finally {
            // Proper resource management
            try {
                if (rst != null) {
                    rst.close();
                }
                if (pst != null) {
                    pst.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace(); // Handle exceptions when closing resources
            }
        }
    }

    public void homeTotalInactive() {
        String sql = "SELECT COUNT(id) AS total_count FROM employee_info WHERE salary = '0.0';"; // Use COUNT and alias

        database db = new database();
        conn = db.ConnectDB(); // Ensure your connection is successful
        int countData = 0;

        try {
            pst = conn.prepareStatement(sql); // Prepare the statement
            rst = pst.executeQuery(); // Execute the prepared statement

            // Check if the result set has data
            if (rst.next()) {
                countData = rst.getInt("total_count"); // Use the alias "total_count"
            }

            home_totalInactive.setText(String.valueOf(countData));

        } catch (SQLException e) {
            e.printStackTrace(); // Catch SQL-related errors
        } finally {
            // Proper resource management
            try {
                if (rst != null) {
                    rst.close();
                }
                if (pst != null) {
                    pst.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace(); // Handle exceptions when closing resources
            }
        }
    }

    public void homeChart() {
    hom_chart.getData().clear(); // Clear existing data

    // Quoting "date" because it is a reserved keyword in PostgreSQL
    String sql = "SELECT \"date\", COUNT(id) AS employee_count FROM employee GROUP BY \"date\" ORDER BY \"date\" ASC LIMIT 7;";

    database db = new database();
    conn = db.ConnectDB(); // Establish a connection

    ResultSet rst = null; // Declare ResultSet here for better visibility
    PreparedStatement pst = null; // Declare PreparedStatement here for better visibility

    try {
        // Create chart series
        XYChart.Series<String, Number> chart = new XYChart.Series<>();

        pst = conn.prepareStatement(sql); // Prepare the statement
        rst = pst.executeQuery(); // Execute the prepared statement

        // Iterate through result set
        while (rst.next()) {
            chart.getData().add(new XYChart.Data<>(rst.getString("date"), rst.getInt("employee_count"))); // Fetch using alias
        }

        // Add the populated series to the chart
        hom_chart.getData().add(chart); // Ensure compatibility

    } catch (SQLException e) {
        e.printStackTrace(); // Catch SQL-related errors

    } finally {
        // Properly close resources
        try {
            if (rst != null) {
                rst.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace(); // Print any exception related to closing resources
        }
    }
}

    public void addEmployeeAdd() {
        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());

        database db = new database();
        conn = db.ConnectDB();

        try {
            Alert alert;
            if (addEmployee_EmployeeID.getText().isEmpty()
                    || addEmployee_FirstName.getText().isEmpty()
                    || addEmployee_LastName.getText().isEmpty()
                    || addEmployee_Gender.getSelectionModel().getSelectedItem() == null
                    || addEmployee_phoneNum.getText().isEmpty()
                    || addEmployee_Position.getSelectionModel().getSelectedItem() == null
                    || getData.path == null || getData.path.isEmpty()) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Fill all the blank fields");
                alert.showAndWait();
                return;
            }

            int employeeId;
            try {
                employeeId = Integer.parseInt(addEmployee_EmployeeID.getText());
            } catch (NumberFormatException e) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Employee ID must be a valid integer");
                alert.showAndWait();
                return;
            }

            // Check if employee ID already exists
            String checkSql = "SELECT id FROM employee WHERE id = ?";
            try (PreparedStatement checkStmt = conn.prepareStatement(checkSql)) {
                checkStmt.setInt(1, employeeId);
                try (ResultSet rs = checkStmt.executeQuery()) {
                    if (rs.next()) {
                        alert = new Alert(AlertType.ERROR);
                        alert.setTitle("Error Message");
                        alert.setHeaderText(null);
                        alert.setContentText("Employee ID: " + employeeId + " Already Exists!");
                        alert.showAndWait();
                        return;
                    }
                }
            }

            // Start transaction
            conn.setAutoCommit(false);

            // Insert new employee
            String insertSql = "INSERT INTO employee (id, \"firstName\", \"lastName\", gender, phone, \"position\", image, date) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement pst = conn.prepareStatement(insertSql)) {
                pst.setInt(1, employeeId);
                pst.setString(2, addEmployee_FirstName.getText());
                pst.setString(3, addEmployee_LastName.getText());
                pst.setString(4, (String) addEmployee_Gender.getSelectionModel().getSelectedItem());
                pst.setString(5, addEmployee_phoneNum.getText());
                pst.setString(6, (String) addEmployee_Position.getSelectionModel().getSelectedItem());
                pst.setString(7, getData.path.replace("\\", "\\\\"));
                pst.setDate(8, sqlDate);
                pst.executeUpdate();
            }

            // Insert employee info (without date column)
            String insertInfoSql = "INSERT INTO employee_info (id, \"firstName\", \"lastName\", \"position\", salary) "
                    + "VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement pst = conn.prepareStatement(insertInfoSql)) {
                pst.setInt(1, employeeId);
                pst.setString(2, addEmployee_FirstName.getText());
                pst.setString(3, addEmployee_LastName.getText());
                pst.setString(4, (String) addEmployee_Position.getSelectionModel().getSelectedItem());
                pst.setDouble(5, 0.0);  // Default salary value
                pst.executeUpdate();
            }

            // Commit transaction
            conn.commit();

            alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Information Message");
            alert.setHeaderText(null);
            alert.setContentText("Successfully Added!");
            alert.showAndWait();

            addEmployeeShowListData();
            addEmployeeReset();

        } catch (SQLException e) {
            // Rollback transaction on error
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
            // Show an error alert
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Database Error");
            alert.setHeaderText(null);
            alert.setContentText("An error occurred while adding the employee: " + e.getMessage());
            alert.showAndWait();
        } finally {
            // Reset auto-commit and close the connection
            try {
                if (conn != null) {
                    conn.setAutoCommit(true);
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void adEmployeeUpdate() {
        String uri = getData.path;
        uri = uri.replace("\\", "\\\\");
        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());

        String sql = "UPDATE employee SET firstName ='" + addEmployee_FirstName.getText() + "',lastName='" + addEmployee_LastName.getText() + "',gender='" + addEmployee_Gender.getSelectionModel().getSelectedItem() + "'"
                + ",phone='" + addEmployee_phoneNum.getText() + "',image='" + uri + "' ,date='" + sqlDate + "' WHERE id='" + addEmployee_EmployeeID.getText() + "'";

        database db = new database();
        conn = db.ConnectDB();
        try {

            Alert alert;
            if (addEmployee_EmployeeID.getText().isEmpty()
                    || addEmployee_FirstName.getText().isEmpty()
                    || addEmployee_LastName.getText().isEmpty()
                    || addEmployee_Gender.getSelectionModel().getSelectedItem() == null
                    || addEmployee_phoneNum.getText().isEmpty()
                    || addEmployee_Position.getSelectionModel().getSelectedItem() == null
                    || getData.path == null || getData.path == "") {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("fill all the blank fields");
                alert.showAndWait();
            } else {
                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("confirmation message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to UPDATE Employee ID:" + addEmployee_EmployeeID.getText() + "?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {
                    st = conn.createStatement();
                    st.executeUpdate(sql);
                    String updateInfo = "UPDATE employee_info SET firstName='" + addEmployee_FirstName.getText() + "',"
                            + "lastame='" + addEmployee_LastName.getText() + "'"
                            + ",position='" + addEmployee_Position.getSelectionModel().getSelectedItem() + "'WHERE id='" + addEmployee_EmployeeID.getText() + "'";

                    pst = conn.prepareStatement(updateInfo);
                    pst.executeUpdate();

                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully updated");
                    alert.showAndWait();

                    addEmployeeShowListData();
                    addEmployeeReset();
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void addEmployeeDelete() {
        String sql = "DELETE FROM employee WHERE id='" + addEmployee_EmployeeID.getText() + "'";

        database db = new database();
        conn = db.ConnectDB();

        try {

            Alert alert;
            if (addEmployee_EmployeeID.getText().isEmpty()
                    || addEmployee_FirstName.getText().isEmpty()
                    || addEmployee_LastName.getText().isEmpty()
                    || addEmployee_Gender.getSelectionModel().getSelectedItem() == null
                    || addEmployee_phoneNum.getText().isEmpty()
                    || addEmployee_Position.getSelectionModel().getSelectedItem() == null
                    || getData.path == null || getData.path == "") {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("fill all the blank fields");
                alert.showAndWait();
            } else {
                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("confirmation message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to DELETE Employee ID:" + addEmployee_EmployeeID.getText() + "?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {
                    st = conn.createStatement();
                    st.executeUpdate(sql);
                    String deleteInfo = "DELETE FROM employee_info WHERE id='" + addEmployee_EmployeeID.getText() + "'";
                    pst = conn.prepareStatement(deleteInfo);
                    pst.executeUpdate();

                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully deleted");
                    alert.showAndWait();

                    addEmployeeShowListData();
                    addEmployeeReset();
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void addEmployeeReset() {
        addEmployee_EmployeeID.setText("");
        addEmployee_FirstName.setText("");
        addEmployee_LastName.setText("");
        addEmployee_Gender.getSelectionModel().clearSelection();
        addEmployee_phoneNum.setText("");
        addEmployee_Position.getSelectionModel().clearSelection();
        addEmployee_image.setImage(null);
        getData.path = "";
    }

    public void addEmployeeInsertImage() {
        FileChooser open = new FileChooser();

        File file = open.showOpenDialog(main_form.getScene().getWindow());

        if (file != null) {
            getData.path = file.getAbsolutePath();
            image = new Image(file.toURI().toString(), 127, 126, false, true);
            addEmployee_image.setImage(image);

        }
    }
    private String[] positionList = {"Marketer Coordinator", "Web Developer(back end)", "Web Developer(Front end)", "App developer"};

    public void addEmployeePositionList() {
        List<String> listP = new ArrayList<>();
        for (String data : positionList) {
            listP.add(data);

        }
        ObservableList listData = FXCollections.observableArrayList(listP);
        addEmployee_Position.setItems(listData);
    }
    private String[] listGender = {"Male", "Female", "Others"};

    public void addEmployeeGenderList() {
        List<String> listG = new ArrayList<>();
        for (String data : listGender) {
            listG.add(data);

        }
        ObservableList listData = FXCollections.observableArrayList(listG);
        addEmployee_Gender.setItems(listData);
    }

    public void addEmployeeSearch() {
    // Ensure addEmployeeList is properly initialized
    if (addEmployeeList == null || addEmployeeList.isEmpty()) {
        System.out.println("Employee List is empty or null");
        return; // Early return if there's no data to search
    }

    // Setup a filtered list
    FilteredList<employeeData> filter = new FilteredList<>(addEmployeeList, e -> true);

    // Add listener for the search text field
    addEmployee_search.textProperty().addListener((Observable, oldValue, newValue) -> {
        filter.setPredicate(predicateEmployeeData -> {
            // If the search field is empty, return all items
            if (newValue == null || newValue.isEmpty()) {
                return true;
            }

            // Case-insensitive search key
            String searchKey = newValue.toLowerCase();

            // Perform checks for each employee data field
            return predicateEmployeeData.getId().toString().contains(searchKey) ||
                   predicateEmployeeData.getFirstName().toLowerCase().contains(searchKey) ||
                   predicateEmployeeData.getLastName().toLowerCase().contains(searchKey) ||
                   predicateEmployeeData.getGender().toLowerCase().contains(searchKey) ||
                   predicateEmployeeData.getPhone().toLowerCase().contains(searchKey) ||
                   predicateEmployeeData.getPosition().toLowerCase().contains(searchKey) ||
                   predicateEmployeeData.getDate().toString().contains(searchKey);
        });
    });

    // Create a sorted list based on the filter
    SortedList<employeeData> sortList = new SortedList<>(filter);
    
    // Bind the comparator of the sorted list to the table view's comparator
    sortList.comparatorProperty().bind(addEmployee_tableView.comparatorProperty());

    // Add sorted (and filtered) data to the table view
    addEmployee_tableView.setItems(sortList);
}


    public ObservableList<employeeData> addEmployeeListData() {
        ObservableList<employeeData> listData = FXCollections.observableArrayList();
        String sql = "SELECT * FROM employee";

        // Creating an instance of the database class
        database db = new database();
        conn = db.ConnectDB();

        try {
            // Check if the connection is successful
            if (conn == null) {
                System.out.println("Database connection failed!");
                return listData; // Return an empty list
            }

            // Use the correct PreparedStatement variable
            pst = conn.prepareStatement(sql); // Prepare the statement

            rst = pst.executeQuery(); // Execute the statement (use st, not pst)

            employeeData employeeD;

            while (rst.next()) {
                // Fetching data from the result set
                employeeD = new employeeData(
                        rst.getInt("id"),
                        rst.getString("firstName"),
                        rst.getString("lastName"),
                        rst.getString("gender"),
                        rst.getString("phone"),
                        rst.getString("position"),
                        rst.getString("image"),
                        rst.getDate("date") // Make sure this column matches your DB structure
                );
                listData.add(employeeD);
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Catch SQL-related errors
        } catch (Exception e) {
            e.printStackTrace(); // Catch any other exceptions
        } finally {
            // Ensure resources are closed in the finally block
            try {
                if (rst != null) {
                    rst.close();
                }
                if (st != null) {
                    st.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace(); // Handle closing exceptions
            }
        }

        return listData; // Return the populated list
    }

    private ObservableList<employeeData> addEmployeeList;

    public void addEmployeeShowListData() {
        addEmployeeList = addEmployeeListData();
        addEmployee_employeeID.setCellValueFactory(new PropertyValueFactory<>("id"));
        addEmployee_firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        addEmployee_lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        addEmployee_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        addEmployee_phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        addEmployee_position.setCellValueFactory(new PropertyValueFactory<>("position"));
        addEmployee_dateMember.setCellValueFactory(new PropertyValueFactory<>("date"));

        addEmployee_tableView.setItems(addEmployeeList);

    }

    public void addEmployeeSelect() {
    employeeData employeeD = addEmployee_tableView.getSelectionModel().getSelectedItem();
    int num = addEmployee_tableView.getSelectionModel().getSelectedIndex();

    // Simplified index check
    if (num < 0 || employeeD == null) {
        return; // Ensure employeeD is not null
    }

    addEmployee_EmployeeID.setText(String.valueOf(employeeD.getId()));
    addEmployee_FirstName.setText(employeeD.getFirstName());
    addEmployee_LastName.setText(employeeD.getLastName());
    addEmployee_gender.setText(employeeD.getGender());
    addEmployee_phoneNum.setText(employeeD.getPhone());

    getData.path = employeeD.getImage(); // Assuming this is the correct way to store it

    // Check if the image path is valid
    if (getData.path != null && !getData.path.isEmpty()) {
        String uri = "file:" + getData.path;
        image = new Image(uri, 127, 126, false, true);
        addEmployee_image.setImage(image);
    } else {
        // Handle image not found case
        addEmployee_image.setImage(null); // Optionally reset the image
    }
}


    public void salaryupdate() {
        String sql = "UPDATE employee_info SET salary='" + salary_salary.getText() + "' WHERE id='" + salary_EmployeeID.getText() + "'";

        database db = new database();
        conn = db.ConnectDB();
        try {
            Alert alert;
            if (salary_EmployeeID.getText().isEmpty()
                    || salary_firstName.getText().isEmpty()
                    || salary_lastName.getText().isEmpty()
                    || salary_lastName.getText().isEmpty()) {

                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error message");
                alert.setHeaderText(null);
                alert.setContentText("please select item first");
                alert.showAndWait();

            } else {
                st = conn.createStatement();
                st.executeUpdate(sql);

                alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Information message");
                alert.setHeaderText(null);
                alert.setContentText("successsfully updated");
                alert.showAndWait();

                salaryShowListData();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void salaryReset() {
        salary_EmployeeID.setText("");
        salary_firstName.setText("");
        salary_lastName.setText("");
        salary_position.setText("");
        salary_salary.setText("");

    }

    public ObservableList<employeeData> salaryListData() {
        ObservableList<employeeData> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM employee_info";

        database db = new database();
        conn = db.ConnectDB();
        try {
            pst = conn.prepareStatement(sql);
            rst = pst.executeQuery();
            employeeData employeeD;

            while (rst.next()) {
                employeeD = new employeeData(rst.getInt("id"),
                        rst.getString("firstName"),
                        rst.getString("lastName"),
                        rst.getString("position"),
                        rst.getDouble("salary"));
                listData.add(employeeD);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return listData;
    }

    private ObservableList<employeeData> salaryList;

    public void salaryShowListData() {
        salaryList = salaryListData();

        salary_colEmployeeID.setCellValueFactory(new PropertyValueFactory<>("id"));
        salary_colFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        salary_colLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        salary_colPosition.setCellValueFactory(new PropertyValueFactory<>("position"));
        salary_colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));

        salary_tableView.setItems(salaryList);
    }

    public void salarySelect() {
        employeeData employeeD = salary_tableView.getSelectionModel().getSelectedItem();
        int num = salary_tableView.getSelectionModel().getSelectedIndex();
        if ((num - 1) < -1) {
            return;
        }
        salary_EmployeeID.setText(String.valueOf(employeeD.getId()));
        salary_firstName.setText(employeeD.getFirstName());
        salary_lastName.setText(employeeD.getLastName());
        salary_position.setText(employeeD.getPosition());
        salary_salary.setText(String.valueOf(employeeD.getSalary()));

    }

    public void defaultNav() {
        home_btn.setStyle("-fx-background-color:linear-gradient(to bottom right,#3a4368,#28966c)");
    }

    public void displayUsername() {
        username.setText(getData.username);

    }

    public void switchForm(ActionEvent event) {
        if (event.getSource() == home_btn) {
            home_form.setVisible(true);
            addEmployee_form.setVisible(false);
            salary_form.setVisible(false);

            home_btn.setStyle("-fx-background-color:linear-gradient(to bottom right,#3a4368,#28966c)");
            addEmployeeBtn.setStyle("-fx-background-color:transparent");
            salary_btn.setStyle("-fx-background-color:transparent");
            homeTotalEmployee();
            addEmployeeTotalPresent();
            homeTotalInactive();
            homeChart();
        } else if (event.getSource() == addEmployeeBtn) {
            home_form.setVisible(false);
            addEmployee_form.setVisible(true);
            salary_form.setVisible(false);

            home_btn.setStyle("-fx-background-color:transparent");
            addEmployeeBtn.setStyle("-fx-background-color:linear-gradient(to bottom right,#3a4368,#28966c)");
            salary_btn.setStyle("-fx-background-color:transparent");
            addEmployeePositionList();
            addEmployeeGenderList();
            addEmployeeSearch();

        } else if (event.getSource() == salary_btn) {
            home_form.setVisible(false);
            addEmployee_form.setVisible(false);
            salary_form.setVisible(true);

            home_btn.setStyle("-fx-background-color:transparent");
            addEmployeeBtn.setStyle("-fx-background-color:transparent");
            salary_btn.setStyle("-fx-background-color:linear-gradient(to bottom right,#3a4368,#28966c)");
            salaryShowListData();
        }
    }

    private double x = 0;
    private double y = 0;

    public void logout() {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation message");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to logout");
        Optional<ButtonType> option = alert.showAndWait();
        try {
            if (option.get().equals(ButtonType.OK)) {
                logout_btn.getScene().getWindow().hide();
                Parent root = FXMLLoader.load(getClass().getResource("primary.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);

                stage.initStyle(StageStyle.TRANSPARENT);
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

                stage.setScene(scene);
                stage.show();
            }
        } catch (Exception e) {

            e.printStackTrace();
        }

    }

    public void close() {
        System.exit(0);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        displayUsername();
        defaultNav();
        homeTotalEmployee();
        addEmployeeTotalPresent();
        homeTotalInactive();
        homeChart();
        addEmployeeShowListData();
        addEmployeePositionList();
        addEmployeeGenderList();
        salaryShowListData();
    }

}
