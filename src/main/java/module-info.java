module com.example.cities {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires org.jsoup;

    opens com.example.cities to javafx.fxml;
    exports com.example.cities;
    exports com.example.cities.helloWindow;
    opens com.example.cities.helloWindow to javafx.fxml;
    exports com.example.cities.mainWindow;
    opens com.example.cities.mainWindow to javafx.fxml;
}