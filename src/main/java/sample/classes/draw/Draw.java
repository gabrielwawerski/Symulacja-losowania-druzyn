package sample.classes.draw;

import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import sample.classes.team.Team;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Needed in order to use variables
 * // TODO receive ObservableLists to baskets and groups, draw teams and put them in baskets, ?then return?
 */
public class Draw extends Service<Void> {
    private ObservableList<String>[] baskets;
    private ObservableList<String>[] groups;
    private ArrayList<Team> teams;

    public Draw(ObservableList<String>[] baskets, ObservableList<String>[] groups, ArrayList<Team> teams) {
        this.baskets = baskets;
        this.groups = groups;
        this.teams = teams;
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
