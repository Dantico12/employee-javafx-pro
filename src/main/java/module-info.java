module com.mycompany.employee_managment_system {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.mycompany.employee_managment_system to javafx.fxml;
    exports com.mycompany.employee_managment_system;
}
