package sample.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import java.net.URL;
import java.sql.Time;
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
    private ObservableList<String>[] namesTeamsInGroup = new ObservableList[7]; //Array with names of teams in each group

    //should be more universal
    private final int teamsInBasket = 8;
    private final int numberOfBaskets = 4;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

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


        // binding ObservableList with names of teams and ListView
        groupA.setItems(namesTeamsInGroup[0]);
        groupB.setItems(namesTeamsInGroup[1]);
        groupC.setItems(namesTeamsInGroup[2]);
        groupB.setItems(namesTeamsInGroup[3]);
        groupD.setItems(namesTeamsInGroup[4]);
        groupE.setItems(namesTeamsInGroup[5]);
        groupF.setItems(namesTeamsInGroup[6]);

    }


    @FXML
    protected void handleDrawButton() {
    }



    @FXML
    protected void handleQuickDrawButton() {

        int [] numberOfTeamsInBasket = new int [4];

        for (int i = 0; i < numberOfBaskets ; i++)
        {
            numberOfTeamsInBasket[i] = namesTeamsInBasket[i].size();
        }

        int rand = 0;
        String nameDrawn = "";

        // next team in basket
        for (int i = 0; i < teamsInBasket; i++)
        {
            //next basket
            for (int j = 0; j < numberOfBaskets; j++)
            {
                rand = (int)(Math.floor(Math.random() * numberOfTeamsInBasket[j]));
                nameDrawn = namesTeamsInBasket[j].get(rand);
                //namesTeamsInGroup[i].add(nameDrawn);
                System.out.println(nameDrawn);
                namesTeamsInBasket[j].remove(rand);
                numberOfTeamsInBasket[j]--;

            }
            System.out.println();
        }

    }

    @FXML
    protected void handleResetButton() {
    }
}
