package sample.classes.threads.tasks;

import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import sample.classes.basket.Basket;
import sample.classes.team.Team;
import sample.controllers.SceneController;

import java.util.ArrayList;
import java.util.List;

public class FillBasketService extends Service<ObservableList<String>[]> {
    private Basket[] baskets; // container for teams
    private List<Team>[] teams; // each list contains 8 teams

    public FillBasketService(Basket[] baskets, List<Team>[] teams) {
        this.baskets = baskets;
        this.teams = teams;
    }

    @Override
    protected Task<ObservableList<String>[]> createTask() {
        return new Task<ObservableList<String>[]>() {
            @Override
            protected ObservableList<String>[] call() throws Exception {
                Team[] tempArray = new Team[SceneController.TEAMS_IN_BASKET]; // array for toArray call
                List<String> list = new ArrayList<>();
                String[] teamNames;

                for (int i = 0; i < SceneController.NUMBER_OF_BASKETS; i++) {
                        teamNames = baskets[i].getTeamNames();
                        teams[i].toArray(tempArray);
                        baskets[i].setTeams(tempArray);
                }

                return null;
            }
        };
    }
}
