package sample.classes.threads;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import sample.classes.basket.Basket;
import sample.classes.team.Team;
import sample.controllers.SceneController;

import java.util.List;
// TODO receive ObservableLists to baskets and groups, threads teams and put them in baskets, ?then return?
/**
 * Main class for drawing logic and more.
 */
public class FillBasketService extends Service<ObservableList<String>> {
    private Basket[] baskets;
    private List<List<Team>> teams;

    public FillBasketService(Basket[] baskets, List<List<Team>> teams) {
        this.baskets = baskets;
        this.teams = teams;
    }

    public void startDraw() {
        System.out.println("FillBasketService started.");
        start();
    }

    @Override
    protected Task<ObservableList<String>> createTask() {
        return new Task<ObservableList<String>>() {
            @Override
            protected ObservableList<String> call() {
                updateProgress(0, SceneController.NUMBER_OF_BASKETS);
                for (int i = 0; i < SceneController.NUMBER_OF_BASKETS; i++) {
                    System.out.println("Main loop iteration " + i);
                    for (int j = 0; j < SceneController.MAX_BASKET_CAPACITY; j++) {
                        System.out.println("Inner loop" + j + "iteration");
                        baskets[i].setTeam(j, teams.get(i).get(j)); // TODO FIXME
                    }
                    updateProgress(i, SceneController.NUMBER_OF_BASKETS);
                    updateValue(newObservableList());
                }
                return null;
            }
        };
    }

    private ObservableList<String> newObservableList() {
        return FXCollections.observableArrayList(baskets[(int) getProgress()].getCurrentTeamNames());
    }
}