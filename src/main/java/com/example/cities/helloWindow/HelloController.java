package com.example.cities.helloWindow;

import com.example.cities.mainWindow.MainController;
import com.example.cities.util.AlertUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.Locale;
import java.util.ResourceBundle;

import static com.example.cities.util.FXMLUtils.*;

public class HelloController {
    @FXML
    private ComboBox<String> cbLanguage;
    @FXML
    private TextField playerName;
    @FXML
    private Button startBtn;

    private ObservableList<String> languages;
    private Locale locale;

    public HelloController() {
        languages = FXCollections.observableArrayList("UA", "US");
    }

    @FXML
    public void initialize() {
        cbLanguage.setItems(languages);
        cbLanguage.setCellFactory(c -> new LanguageListCell());
        cbLanguage.setOnAction(this::switchLanguage);

        playerName.setOnAction(this::startButtonHandler);
        playerName.requestFocus();

        startBtn.setOnAction(this::startButtonHandler);
    }

    private void switchLanguage(ActionEvent event) {
        locale = cbLanguage.getValue().equals("UA") ? new Locale("uk", "UA") : Locale.US;

        FXMLLoader fxmlLoader = getLocalizedFXMLLoader("/views/hello-view.fxml", locale);
        Parent root = loadRoot(fxmlLoader);

        HelloController controller = fxmlLoader.getController();
        controller.setLocale(locale);

        Scene scene = createScene(root);
        Stage stage = getStageFromEvent(event);
        ResourceBundle bundle = ResourceBundle.getBundle("languages.lang", locale);
        stage.setTitle(bundle.getString("window-title"));
        stage.setScene(scene);
        stage.show();
    }



    public void startButtonHandler(ActionEvent event) {
        if (playerName.getText().isBlank()) {
            AlertUtils.showInformationAlert("Ім'я не може бути пустим");
        } else {
            Stage stage = getStageFromEvent(event);
            stage.setScene(createMainWindowScene());
            stage.setTitle(stage.getTitle() + " - " + playerName.getText());
            stage.show();
        }
    }

    private Scene createMainWindowScene() {
        FXMLLoader fxmlLoader = getLocalizedFXMLLoader("/views/main-view.fxml", locale);
        Parent root = loadRoot(fxmlLoader);

        MainController controller = fxmlLoader.getController();
        controller.setPlayerName(playerName.getText());

        return createScene(root);
    }

    private static Stage getStageFromEvent(ActionEvent event) {
        Node node = (Node) event.getSource();
        return  (Stage) node.getScene().getWindow();
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
        cbLanguage.getSelectionModel().select(locale.getCountry());
        cbLanguage.setButtonCell(new LanguageListCell());
    }
}