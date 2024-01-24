module com.example.pogodaapi {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.json;


    opens com.example.pogodaapi to javafx.fxml;
    exports com.example.pogodaapi;
}