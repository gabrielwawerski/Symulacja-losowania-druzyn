package sample.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.WorkerStateEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import sample.classes.basket.Basket;
import sample.classes.threads.Draw;
import sample.classes.threads.tasks.FillBasketTask;
import sample.classes.team.Continent;
import sample.classes.team.Team;

import java.net.URL;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
    private HashMap<Integer, ListView<String>> basketMap;

    // arrays for observable lists that bind to baskets and groupsListViews ListView's
    private ObservableList<String>[] observableListBasket;
    private ObservableList<String>[] observableListGroup;

    // all teams sorted in ascending order - corresponding to their fifa rank + 1
    // 1st place is always taken by the host
    private ArrayList<Team> teams;
    private Team host;

    private final ExecutorService executor = Executors.newFixedThreadPool(2, r -> {
        Thread t = new Thread(r);
        t.setDaemon(true);
        return t;
    });

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


    private int i = 0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        baskets = new Basket[NUMBER_OF_BASKETS];
        basketsListViews = new ListView[NUMBER_OF_BASKETS];
        groupsListViews = new ListView[NUMBER_OF_GROUPS];

//        setDefaultTeams();
        makeTeams();
        setHost(new Team("Russia", 65, Continent.EUROPE));
        putHost(getHost());
        sortTeamsByValueDescending();

        basketsListViews[0] = basket1;
        basketsListViews[1] = basket2;
        basketsListViews[2] = basket3;
        basketsListViews[3] = basket4;

        groupsListViews[0] = groupA;
        groupsListViews[1] = groupB;
        groupsListViews[2] = groupC;
        groupsListViews[3] = groupD;
        groupsListViews[4] = groupE;
        groupsListViews[5] = groupF;
        groupsListViews[6] = groupG;
        groupsListViews[7] = groupH;

        // TODO delete this
        // instantiate all ObservableLists for team names in baskets
//        for (int i = 0; i < NUMBER_OF_BASKETS; i++) {
//            observableListBasket[i] = FXCollections.observableArrayList();
//        }
//
//        for (int i = 0; i < NUMBER_OF_GROUPS; i++) {
//            observableListGroup[i] = FXCollections.observableArrayList();
//        }

        for (int i = 0; i < NUMBER_OF_GROUPS; i++) {
            observableListGroup[i] = FXCollections.observableArrayList();
        }


        // neccessary because arrays don't support generics
//        List<Team>[] lists = (ArrayList<Team>[]) new ArrayList<?>[NUMBER_OF_BASKETS];
        List<List<Team>> teams_ = new ArrayList<>();

        teams_.get(0).get(0) = teams.subList(0, 9); // TODO FIGURE OUT HOW TO ADD teams to this god awful list and iterate over it in for few lines down
        lists[1] = teams.subList(8, 17);
        lists[2] = teams.subList(16, 25);
        lists[3] = teams.subList(24, 33);

        // TODO not quite correct - figure out how to do setOnSucceeded and generally where to initialize FillBasketTask - or maybe swap it to Service or some other thing...
        // lookup how to make an object that encapsulates Task, which returns it's value and can be reseted
        Team[] teams = new Team[MAX_BASKET_CAPACITY];
        for ( ; i < NUMBER_OF_BASKETS; i++) {
            teamsSubLists[i].toArray(teams);
            FillBasketTask fillBasketTask = new FillBasketTask(baskets[i], teams);
            fillBasketTask.setOnSucceeded(event
                    -> observableListBasket[getI()] = fillBasketTask.getValue().getObservableList());
        }
    }


    private int getI() {
        return i;
    }

    @FXML
    protected void handleDrawButton() {
    }

    @FXML
    protected void handleQuickDrawButton() throws InterruptedException {
        disableAllDrawButtons(true);

        Draw quickDraw = new Draw();

        quickDraw.setOnSucceeded(new javafx.event.EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
                // bind array containing teams in each basket to each basket's ListView
                basket1.setItems(observableListBasket[0]);
                basket2.setItems(observableListBasket[1]);
                basket3.setItems(observableListBasket[2]);
                basket4.setItems(observableListBasket[3]);
            }
        });
    }

    @FXML
    protected void handleResetButton() {
        setDefaultTeams();
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
        for (int i = 0; i < MAX_BASKET_CAPACITY; i++) {
            observableListGroup[i].clear();
        }
    }

    private void sortTeamsByValueDescending() { // todo remove
        teams.sort(Collections.reverseOrder());
    }
}
