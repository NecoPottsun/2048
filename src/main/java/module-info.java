module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.media;
    requires poi;
//    requires org.apache.xmlbeans;
//    requires org.apache.poi.ooxml;
//    requires org.apache.poi.ooxml.schemas;
//    requires org.apache.commons.collections4;
//    requires org.apache.logging.log4j.appserver;
//    requires org.apache.logging.log4j;
//    requires org.apache.logging.log4j.core;
//    requires org.apache.commons.compress;

    opens com.example.demo to javafx.fxml;
    exports com.example.demo;
}