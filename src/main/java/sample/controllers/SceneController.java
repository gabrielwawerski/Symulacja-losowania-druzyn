package sample.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;

public class SceneController {

    @FXML
    private ListView koszyk1;
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
    private Button generate;

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


    @FXML
    protected void handleDrawButton() {
    }

    @FXML
    protected void handleQuickDrawButton() {
    }

    @FXML
    protected void handleResetButton() {
    }

    @FXML
    public void generateTeams()
    {
        koszyk1.getItems().addAll("asdasd", "asdasd", "asdas");
        koszyk1.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }
}