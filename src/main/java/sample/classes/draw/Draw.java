package sample.classes.draw;

import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import sample.classes.team.Team;
import sample.controllers.SceneController;

import java.util.*;
// TODO receive ObservableLists to baskets and groups, draw teams and put them in baskets, ?then return?
/**
 * Main class for drawing logic and more.
 * <br>Remember to first get the instance of a class before using it, using
 * {@link Draw#getInstance} method.
 */
public class Draw extends Service<Void> {
    public static ObservableList<String>[] baskets;
    public static ObservableList<String>[] groups;
    public static HashMap<String, Team> teams;
    public static Draw instance = null;

    private Draw() {
    }

    public static Draw getInstance() {
        if (instance == null)
            instance = new Draw();
        return instance;
    }

    /**
     * Injects fields sent from {@link SceneController} class.
     * @param _baskets {@link SceneController#observableListBasket}
     * @param _groups {@link SceneController#observableListGroup}
     * @param _teams {@link SceneController#teams}
     */
    public static void injectFields(ObservableList<String>[] _baskets, ObservableList<String>[] _groups,
                                    HashMap<String, Team> _teams) {
        baskets = _baskets;
        groups = _groups;
        teams = _teams;
    }

    Ordering<Map.Entry<String, Team>> byMapValues = new Ordering<Map.Entry<String, Team>>() {
        @Override
        public int compare(Map.Entry<String, Team> left, Map.Entry<String, Team> right) {
            return left.getValue().compareTo(right.getValue());
        }
    };

    public void sortTeamsMapByValuesDescending() {
        // create a list of map entries
        List<Map.Entry<String, Team>> _teams = Lists.newArrayList(teams.entrySet());
        Collections.sort(_teams, byMapValues.reverse());
    }

    @Override
    protected Task<Void> createTask() {
        return new Task<Void>() {
            @Override
            protected Void call() {
                Collections.sort(teams);
                Collections.reverse(teams);

                for (String x : baskets)

                // 1. posortowanie druzyn malejaco ratingiem
                // 2. wlozenie druzyn do koszykow
                // 3. losowanie i zapelnianie grup
                return null;
            }
        };
    }
}
