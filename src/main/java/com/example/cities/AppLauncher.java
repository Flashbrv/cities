package com.example.cities;

import com.example.cities.helloWindow.HelloController;
import com.example.cities.util.FXMLUtils;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class AppLauncher extends Application {
    @Override
    public void start(Stage stage) {
        Scene scene = loadScene();
        stage.setScene(scene);
        stage.setTitle("Гра \"Міста\"");
        stage.getIcons().add(loadIcon());
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public Scene loadScene() {
        Locale locale = new Locale("uk", "UA");

        FXMLLoader fxmlLoader = FXMLUtils.getLocalizedFXMLLoader("/views/hello-view.fxml", locale);
        Parent root = FXMLUtils.loadRoot(fxmlLoader);

        HelloController controller = fxmlLoader.getController();
        controller.setLocale(locale);

        return FXMLUtils.createScene(root);
    }

    public Image loadIcon() {
        return new Image(this.getClass().getResource("/images/city_icon.png").toExternalForm());
    }
}