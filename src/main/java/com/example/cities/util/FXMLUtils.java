package com.example.cities.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class FXMLUtils {
    public static FXMLLoader getLocalizedFXMLLoader(String viewPath, Locale locale) {
            FXMLLoader fxmlLoader = new FXMLLoader(FXMLUtils.class.getResource(viewPath),
                    ResourceBundle.getBundle("languages.lang", locale));
            return fxmlLoader;
    }

    public static Parent loadRoot(FXMLLoader fxmlLoader) {
        try {
            return fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Scene createScene(Parent root) {
        Scene scene = new Scene(root);
        scene.getStylesheets().add(FXMLUtils.class.getResource("/css/style.css").toExternalForm());

        return scene;
    }
}
