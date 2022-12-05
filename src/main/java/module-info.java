module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.media;
    requires poi;
    requires junit;
    requires hamcrest.all;


    opens com.example.demo to javafx.fxml;
    exports com.example.demo;
}