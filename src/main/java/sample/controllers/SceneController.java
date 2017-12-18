package sample.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import sample.classes.team.Continent;
import sample.classes.team.Team;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SceneController implements Initializable {
    @FXML
    private ListView<String> basket1;
    @FXML
    private ListView basket2;
    @FXML
    private ListView basket3;
    @FXML
    private ListView basket4;

    @FXML
    private Button drawButton;
    @FXML
    private Button quickDrawButton;
    @FXML
    private Button resetButton;

    @FXML
    private ListView groupA;
    @FXML
    private ListView groupB;
    @FXML
    private ListView groupC;
    @FXML
    private ListView groupD;
    @FXML
    private ListView groupE;
    @FXML
    private ListView groupF;
    @FXML
    private ListView groupG;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        initializeTeams();
        ObservableList<String> namesBasket1 = FXCollections.observableArrayList("Rosja", "Niemcy", "Brazylia",
                "Portugalia", "Argentyna", "Belgia", "Polska", "Francja");
        basket1.setItems(namesBasket1);

        ObservableList<String> namesBasket2 = FXCollections.observableArrayList("Hiszpania", "Peru", "Szwajcaria",
                "Anglia", "Kolumbia", "Meksyk", "Urugwaj", "Chorwacja");
        basket2.setItems(namesBasket2);

        ObservableList<String> namesBasket3 = FXCollections.observableArrayList("Dania", "Islandia", "Kostaryka",
                "Szwecja", "Tunezja", "Egipt", "Senegal", "Iran");
        basket3.setItems(namesBasket3);

        ObservableList<String> namesBasket4 = FXCollections.observableArrayList("Serbia", "Nigeria", "Australia",
                "Japonia", "Maroko", "Panama", "Korea Południowa", "Arabia Saudyjska");
        basket4.setItems(namesBasket4);
    }

    private void initializeTeams() {
    }

    
    @FXML
    protected void handleDrawButton() {
    }

    @FXML
    protected void handleQuickDrawButton() {
    }

    @FXML
    protected void handleResetButton() {
    }
}
