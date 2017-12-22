package sample.classes.draw;

import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import sample.classes.team.Team;
import sample.controllers.SceneController;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
// TODO receive ObservableLists to baskets and groups, draw teams and put them in baskets, ?then return?
/**
 * Main class for drawing logic and more.
 */
public class Draw extends Service<Void> {
    private ObservableList<String>[] baskets;
    private ObservableList<String>[] groups;
    private HashMap<String, Team> teams;
    private AtomicInteger i = new AtomicInteger(0);

    /**
     * Injects fields sent from {@link SceneController} class.
     * @param baskets {@link SceneController#observableListBasket}
     * @param groups {@link SceneController#observableListGroup}
     * @param teams {@link SceneController#teams}
     */
    public Draw(ObservableList<String>[] baskets, ObservableList<String>[] groups,
                HashMap<String, Team> teams) {
        this.baskets = baskets;
        this.groups = groups;
        this.teams = teams;
    }

    public void startDraw() {
        createTask();
    }

    @Override
    protected Task<Void> createTask() {
        // lambda expression, which returns Task<Void> object and overrides call method
        return new Task<Void>() {
            @Override
            protected Void call() {
                // losowanie i zapelnianie grup (glowna petla for)
                return null;
            }
        };
    }
}
