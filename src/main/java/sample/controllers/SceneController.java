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


    private ObservableList<String>[] namesTeamsInBasket = new ObservableList[4]; //names of teams in each basket
    private ObservableList<String>[] namesTeamsInGroup = new ObservableList[8]; //Array with names of teams in each
    // group

    //should be more universal
    private final int teamsInBasket = 8;
    private final int numberOfBaskets = 4;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        setDefaultTeams();

    }

    private void setDefaultTeams()
    {

        namesTeamsInBasket[0] = FXCollections.observableArrayList("Rosja", "Niemcy", "Brazylia",
                "Portugalia", "Argentyna", "Belgia", "Polska", "Francja");
        basket1.setItems(namesTeamsInBasket[0]);

        namesTeamsInBasket[1] = FXCollections.observableArrayList("Hiszpania", "Peru", "Szwajcaria",
                "Anglia", "Kolumbia", "Meksyk", "Urugwaj", "Chorwacja");
        basket2.setItems(namesTeamsInBasket[1]);

        namesTeamsInBasket[2] = FXCollections.observableArrayList("Dania", "Islandia", "Kostaryka",
                "Szwecja", "Tunezja", "Egipt", "Senegal", "Iran");
        basket3.setItems(namesTeamsInBasket[2]);

        namesTeamsInBasket[3] = FXCollections.observableArrayList("Serbia", "Nigeria", "Australia",
                "Japonia", "Maroko", "Panama", "Korea Południowa", "Arabia Saudyjska");
        basket4.setItems(namesTeamsInBasket[3]);
    }


    @FXML
    protected void handleDrawButton() {
    }



    @FXML
    protected void handleQuickDrawButton()
    {

        int [] numberOfTeamsInBasket = new int [4];

        for (int i = 0; i < numberOfBaskets ; i++)
        {
            numberOfTeamsInBasket[i] = teamsInBasket;
        }

        for (int i = 0; i < teamsInBasket; i++)
        {
            namesTeamsInGroup[i] = FXCollections.observableArrayList();
        }

        int rand;
        String nameDrawn;

        // binding ObservableList with names of teams and ListView
        groupA.setItems(namesTeamsInGroup[0]);
        groupB.setItems(namesTeamsInGroup[1]);
        groupC.setItems(namesTeamsInGroup[2]);
        groupD.setItems(namesTeamsInGroup[3]);
        groupE.setItems(namesTeamsInGroup[4]);
        groupF.setItems(namesTeamsInGroup[5]);
        groupG.setItems(namesTeamsInGroup[6]);
        groupH.setItems(namesTeamsInGroup[7]);

        // next team in basket
        for (int i = 0; i < teamsInBasket; i++)
        {
            //next basket
            for (int j = 0; j < numberOfBaskets; j++)
            {
                rand = (int)(Math.floor(Math.random() * numberOfTeamsInBasket[j]));
                nameDrawn = namesTeamsInBasket[j].get(rand);
                namesTeamsInGroup[i].add(j, nameDrawn);
                namesTeamsInBasket[j].remove(rand);
                numberOfTeamsInBasket[j]--;

            }

            quickDrawButton.setOnAction(null);
            quickDrawButton.setStyle("-fx-base: #444444; -fx-text-fill: #333333;");
        }



    }


    @FXML
    protected void handleResetButton() {

        setDefaultTeams();
        cleanLists();
        quickDrawButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                handleQuickDrawButton();
            }
        });

        quickDrawButton.setStyle("-fx-base: #000000; -fx-text-fill: #FFFFFF;");
    }

    private void cleanLists()
    {
        for (int i = 0; i < teamsInBasket ; i++)
        {
            namesTeamsInGroup[i].clear();
        }
    }
}
