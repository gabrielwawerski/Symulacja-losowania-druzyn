package sample.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class SceneController implements Initializable {
    @FXML private ListView<String> basket1;
    @FXML private ListView<String> basket2;
    @FXML private ListView<String> basket3;
    @FXML private ListView<String> basket4;

    @FXML private Button drawButton;
    @FXML private Button quickDrawButton;
    @FXML private Button resetButton;

    @FXML private ListView<String> groupA;
    @FXML private ListView<String> groupB;
    @FXML private ListView<String> groupC;
    @FXML private ListView<String> groupD;
    @FXML private ListView<String> groupE;
    @FXML private ListView<String> groupF;
    @FXML private ListView<String> groupG;
    @FXML private ListView<String> groupH;

    // FIXME bad instantiation
    private ObservableList<String>[] namesOfTeamsInBasket = new ObservableList[4]; //names of teams in each basket
    private ObservableList<String>[] namesOfTeamsInGroup = new ObservableList[8]; //Array with names of teams in each
    // group

    //should be more universal
    private static final int TEAMS_IN_BASKET = 8;
    private static final int NUMBER_OF_BASKETS = 4;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setDefaultTeams();
    }

    private void setDefaultTeams() {
        namesOfTeamsInBasket[0]
                = FXCollections.observableArrayList("Rosja", "Niemcy",
                "Brazylia", "Portugalia",
                "Argentyna", "Belgia",
                "Polska", "Francja");
        basket1.setItems(namesOfTeamsInBasket[0]);

        namesOfTeamsInBasket[1]
                = FXCollections.observableArrayList("Hiszpania", "Peru",
                "Szwajcaria", "Anglia",
                "Kolumbia", "Meksyk",
                "Urugwaj", "Chorwacja");
        basket2.setItems(namesOfTeamsInBasket[1]);

        namesOfTeamsInBasket[2]
                = FXCollections.observableArrayList("Dania", "Islandia",
                "Kostaryka", "Szwecja",
                "Tunezja", "Egipt",
                "Senegal", "Iran");
        basket3.setItems(namesOfTeamsInBasket[2]);

        namesOfTeamsInBasket[3]
                = FXCollections.observableArrayList("Serbia", "Nigeria",
                "Australia", "Japonia",
                "Maroko", "Panama",
                "Korea Południowa", "Arabia Saudyjska");
        basket4.setItems(namesOfTeamsInBasket[3]);
    }

    @FXML
    protected void handleDrawButton() {
    }

    @FXML
    protected void handleQuickDrawButton() throws InterruptedException {
        int[] numberOfTeamsInBasket = new int[4];
        int rand;
        String nameDrawn;

        for (int i = 0; i < NUMBER_OF_BASKETS; i++) {
            numberOfTeamsInBasket[i] = TEAMS_IN_BASKET;
        }

        for (int i = 0; i < TEAMS_IN_BASKET; i++) {
            namesOfTeamsInGroup[i] = FXCollections.observableArrayList();
        }

        // binding ObservableList with names of teams and ListView
        groupA.setItems(namesOfTeamsInGroup[0]);
        groupB.setItems(namesOfTeamsInGroup[1]);
        groupC.setItems(namesOfTeamsInGroup[2]);
        groupD.setItems(namesOfTeamsInGroup[3]);
        groupE.setItems(namesOfTeamsInGroup[4]);
        groupF.setItems(namesOfTeamsInGroup[5]);
        groupG.setItems(namesOfTeamsInGroup[6]);
        groupH.setItems(namesOfTeamsInGroup[7]);

        // next team in basket
        for (int i = 0; i < NUMBER_OF_BASKETS; i++) {
            for (int j = 0; j < TEAMS_IN_BASKET; j++) {
                rand = (int)(Math.floor(Math.random() * numberOfTeamsInBasket[i]));
                nameDrawn = namesOfTeamsInBasket[i].get(rand);
                System.out.print(nameDrawn + " ");
                Thread.sleep(500);
                namesOfTeamsInGroup[j].add(i, nameDrawn);
                namesOfTeamsInBasket[i].remove(rand);
                numberOfTeamsInBasket[i]--;
            }
            System.out.println();
        }
        setUnableStyleAndOnAction(quickDrawButton);
        setUnableStyleAndOnAction(drawButton);
    }

    private void setUnableStyleAndOnAction(Button button) {
        button.setOnAction(null);
        quickDrawButton.setStyle("-fx-base: #444444; -fx-text-fill: #333333;");
    }

    @FXML
    protected void handleResetButton() {
        setDefaultTeams();
        cleanLists();

        quickDrawButton.setOnAction((ActionEvent e) -> {
                try {
                    handleQuickDrawButton();
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
        });

        quickDrawButton.setStyle("-fx-base: #000000; -fx-text-fill: #FFFFFF;");
        drawButton.setStyle("-fx-base: #000000; -fx-text-fill: #FFFFFF;");
    }

    private void cleanLists() {
        for (int i = 0; i < TEAMS_IN_BASKET; i++) {
            namesOfTeamsInGroup[i].clear();
        }
    }
}
