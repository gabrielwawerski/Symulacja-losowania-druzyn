package sample.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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

    // TODO check if it's okay to instantiate this way
    private ObservableList<String>[] teamNamesInBasket = new ObservableList[4]; //names of teams in each basket
    private ObservableList<String>[] teamNamesInGroup = new ObservableList[8]; //Array with names of teams in each
    // group

    // TODO should be more universal
    private static final int NUMBER_OF_BASKETS = 4; // the amount of baskets
    private static final int TEAMS_IN_BASKET = 8; // the amount of teams in a single basket

    // styles for different button states
    private static final String BUTTON_DISABLED_STYLE = "-fx-base: #444444; -fx-text-fill: #333333;";
    private static final String BUTTON_ENABLED_STYLE = "-fx-base: #000000; -fx-text-fill: #FFFFFF;";

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setDefaultTeams();
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

        // instantiate all ObservableLists for team names in groups
        for (int i = 0; i < TEAMS_IN_BASKET; i++) {
            teamNamesInGroup[i] = FXCollections.observableArrayList();
        }

        // binding ObservableList with names of teams and ListView
        groupA.setItems(teamNamesInGroup[0]);
        groupB.setItems(teamNamesInGroup[1]);
        groupC.setItems(teamNamesInGroup[2]);
        groupD.setItems(teamNamesInGroup[3]);
        groupE.setItems(teamNamesInGroup[4]);
        groupF.setItems(teamNamesInGroup[5]);
        groupG.setItems(teamNamesInGroup[6]);
        groupH.setItems(teamNamesInGroup[7]);

        // next team in basket
        for (int i = 0; i < NUMBER_OF_BASKETS; i++) {
            for (int j = 0; j < TEAMS_IN_BASKET; j++) {
                rand = (int)(Math.floor(Math.random() * numberOfTeamsInBasket[i]));
                nameDrawn = teamNamesInBasket[i].get(rand);
                System.out.print(nameDrawn + " ");
                Thread.sleep(100);
                teamNamesInGroup[j].add(i, nameDrawn);
                teamNamesInBasket[i].remove(rand);
                numberOfTeamsInBasket[i]--;
            }
            System.out.println();
        }
        setUnableStyleAndOnAction(quickDrawButton);
        setUnableStyleAndOnAction(drawButton);
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

        quickDrawButton.setStyle(BUTTON_ENABLED_STYLE);
        drawButton.setStyle(BUTTON_ENABLED_STYLE);
    }

    private void setDefaultTeams() {
        teamNamesInBasket[0]
                = FXCollections.observableArrayList("Rosja", "Niemcy",
                "Brazylia", "Portugalia",
                "Argentyna", "Belgia",
                "Polska", "Francja");
        basket1.setItems(teamNamesInBasket[0]);

        teamNamesInBasket[1]
                = FXCollections.observableArrayList("Hiszpania", "Peru",
                "Szwajcaria", "Anglia",
                "Kolumbia", "Meksyk",
                "Urugwaj", "Chorwacja");
        basket2.setItems(teamNamesInBasket[1]);

        teamNamesInBasket[2]
                = FXCollections.observableArrayList("Dania", "Islandia",
                "Kostaryka", "Szwecja",
                "Tunezja", "Egipt",
                "Senegal", "Iran");
        basket3.setItems(teamNamesInBasket[2]);

        teamNamesInBasket[3]
                = FXCollections.observableArrayList("Serbia", "Nigeria",
                "Australia", "Japonia",
                "Maroko", "Panama",
                "Korea Południowa", "Arabia Saudyjska");
        basket4.setItems(teamNamesInBasket[3]);
    }

    private void setUnableStyleAndOnAction(Button button) {
        button.setOnAction(null);
        quickDrawButton.setStyle(BUTTON_DISABLED_STYLE);
    }

    private void cleanLists() {
        for (int i = 0; i < TEAMS_IN_BASKET; i++) {
            teamNamesInGroup[i].clear();
        }
    }
}
