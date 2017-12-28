package sample.controllers;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import sample.classes.basket.Basket;
import sample.classes.threads.FillBasketTask;
import sample.classes.team.Continent;
import sample.classes.team.Team;

import java.net.URL;
import java.util.*;

public class SceneController implements Initializable {
    // TODO? put baskets and groupsListViews into an array, for later letting the user to choose
    // the number of baskets and groupsListViews and so that they can be iterated on?
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

    private ListView<String>[] basketsListViews;
    private ListView<String>[] groupsListViews;
    private Basket[] baskets;
    // arrays for observable lists that bind to baskets and groupsListViews ListView's
    private ObservableList<String>[] observableListBasket;
    private ObservableList<String>[] observableListGroup;
    // all teams sorted in ascending order - corresponding to their fifa rank + 1
    // 1st place is always taken by the host
    private ArrayList<Team> teams;
    private Team host;
    // TODO should be more universal
    public static final int NUMBER_OF_BASKETS = 4; // total amount of baskets for teams
    public static final int MAX_BASKET_CAPACITY = 8; // total amount of teams in a single basket
    public static final int NUMBER_OF_GROUPS = 8;
    public static final int MAX_GROUP_CAPACITY = 4;
    // the initial size of the team ArrayList (should be equal to the amount of pre added teams)
    private static final int INITIAL_TEAMS_AMOUNT = 32;
    // styles for different button states
    private static final String BUTTON_DISABLED_STYLE = "-fx-base: #444444; -fx-text-fill: #333333;";
    private static final String BUTTON_ENABLED_STYLE = "-fx-base: #000000; -fx-text-fill: #FFFFFF;";

    List<List<Team>> teamsSubLists;
    List<Team> subList1;
    List<Team> subList2;
    List<Team> subList3;
    List<Team> subList4;

    static int i = 0;
    static FillBasketTask fillBasketTask;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        baskets = new Basket[NUMBER_OF_BASKETS];
        basketsListViews = new ListView[NUMBER_OF_BASKETS];
        groupsListViews = new ListView[NUMBER_OF_GROUPS];

        makeTeams();
        setHost(new Team("Russia", 65, Continent.EUROPE));
        putHost(getHost());
        sortTeamsByValueDescending();

        baskets[0] = new Basket(basket1);
        baskets[1] = new Basket(basket2);
        baskets[2] = new Basket(basket3);
        baskets[3] = new Basket(basket4);

        groupsListViews[0] = groupA;
        groupsListViews[1] = groupB;
        groupsListViews[2] = groupC;
        groupsListViews[3] = groupD;
        groupsListViews[4] = groupE;
        groupsListViews[5] = groupF;
        groupsListViews[6] = groupG;
        groupsListViews[7] = groupH;

        teamsSubLists = new ArrayList<>(NUMBER_OF_BASKETS);
        subList1 = new ArrayList<>(teams.subList(0, 8));
        subList2 = new ArrayList<>(teams.subList(8, 16));
        subList3 = new ArrayList<>(teams.subList(16, 24));
        subList4 = new ArrayList<>(teams.subList(24, 32));

        teamsSubLists.add(subList1);
        teamsSubLists.add(subList2);
        teamsSubLists.add(subList3);
        teamsSubLists.add(subList4);

        // put teams to baskets
        for ( ; i < NUMBER_OF_BASKETS; i++) {
            for (int j = 0; j < MAX_BASKET_CAPACITY; j++) {
                baskets[i].putTeam(teamsSubLists.get(i).get(j));
            }
            System.out.println("i = " + i);
            baskets[i].setObservableLists(FXCollections.observableArrayList(baskets[i].getCurrentTeamNames()));
        }
        System.out.println("i POZA LOOP = " + i);
        // TODO not quite correct - figure out how to do setOnSucceeded and generally where to initialize FillBasketTask - or maybe swap it to Service or some other thing...
        // lookup how to make an object that encapsulates Task, which returns it's value and can be reseted


        // task for assigning teams to baskets

        // task for putting teams to groups

        // task that handles ui events - moving teams
        System.out.println("Initialized correctly.");
    }

    @FXML
    protected void handleDrawButton() {
    }


    @FXML
    protected void handleQuickDrawButton() throws InterruptedException {
        disableAllDrawButtons(true);
    }

    @FXML
    protected void handleResetButton() {
        cleanLists();

        disableAllDrawButtons(false);
    }

    private void setHost(Team team) {
        host = team;
    }

    private void putHost(Team team) {
        teams.add(0, team);
    }

    private Team getHost() {
        return host;
    }

    @Deprecated
    private void setDefaultTeams() {
        observableListBasket[0]
                = FXCollections.observableArrayList("Rosja", "Niemcy",
                "Brazylia", "Portugalia",
                "Argentyna", "Belgia",
                "Polska", "Francja");
        basket1.setItems(observableListBasket[0]);

        observableListBasket[1]
                = FXCollections.observableArrayList("Hiszpania", "Peru",
                "Szwajcaria", "Anglia",
                "Kolumbia", "Meksyk",
                "Urugwaj", "Chorwacja");
        basket2.setItems(observableListBasket[1]);

        observableListBasket[2]
                = FXCollections.observableArrayList("Dania", "Islandia",
                "Kostaryka", "Szwecja",
                "Tunezja", "Egipt",
                "Senegal", "Iran");
        basket3.setItems(observableListBasket[2]);

        observableListBasket[3]
                = FXCollections.observableArrayList("Serbia", "Nigeria",
                "Australia", "Japonia",
                "Maroko", "Panama",
                "Korea Południowa", "Arabia Saudyjska");
        basket4.setItems(observableListBasket[3]);
    }

    /**
     * @see #setButtonStyle(Button, String)
     * @see #setButtonsStyle(String, Button...)
     */
    @Deprecated
    private void setDisabledStyleAndOnAction(Button button) {
        button.setOnAction(null);
        quickDrawButton.setStyle(BUTTON_DISABLED_STYLE);
    }

    /**
     * Sets style of a button. Available styles:
     * <br>{@linkplain #BUTTON_ENABLED_STYLE}
     * <br>{@linkplain #BUTTON_DISABLED_STYLE}
     * @param button button to apply the style to
     * @param STYLE the style to apply to the button
     */
    private void setButtonStyle(Button button, String STYLE) {
        quickDrawButton.setStyle(STYLE);
    }

    /**
     * Sets the style of buttons array. Available styles:
     * <br>{@linkplain #BUTTON_ENABLED_STYLE}
     * <br>{@linkplain #BUTTON_DISABLED_STYLE}
     * @param STYLE the style to apply to all buttons
     * @param buttons buttons to apply the style to
     */
    private void setButtonsStyle(String STYLE, Button... buttons) {
        for (Button x : buttons) {
            x.setStyle(STYLE);
        }
    }

    /**
     * Sets the {@link SceneController#drawButton} and {@link SceneController#quickDrawButton}
     * to the desired state.
     * @param state the state to set the buttons
     */
    private void setDisableDrawButtons(boolean state) {
        drawButton.setDisable(state);
        quickDrawButton.setDisable(state);
    }

    /**
     * If the {@code value} argument is true, all threads buttons are disabled and their styles are
     * changed to {@link #BUTTON_DISABLED_STYLE}, otherwise all buttons are enabled and their styles
     * are changed to {@link #BUTTON_ENABLED_STYLE}.
     * @param value boolean that decides whether all threads buttons are disabled or enabled
     */
    private void disableAllDrawButtons(boolean value) {
        if (value) {
            setDisableDrawButtons(true);
            setButtonsStyle(BUTTON_DISABLED_STYLE, drawButton, quickDrawButton);
            return;
        }
        setDisableDrawButtons(false);
        setButtonsStyle(BUTTON_ENABLED_STYLE, drawButton, quickDrawButton);
    }

    /**
     * Instantiaties and initializes all teams
     */
    private void makeTeams() {
        teams = new ArrayList<>(INITIAL_TEAMS_AMOUNT);
        for (int i = 0; i < INITIAL_TEAMS_AMOUNT; i++) {
            teams.add(new Team("asd", 50, Continent.EUROPE));
        }
        // TODO code for all team instances
    }

    private void cleanLists() {
        for (int i = 0; i < NUMBER_OF_BASKETS; i++) {
            baskets[i].getListView().getItems().clear(); // TODO basket listviews should be manually coded and not added in fxml?
        }
    }

    private void sortTeamsByValueDescending() { // todo remove
        teams.sort(Collections.reverseOrder());
    }
}
