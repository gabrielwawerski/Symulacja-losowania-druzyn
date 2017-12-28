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
public class FillBasketTask extends Task<ObservableList<String>> {
    private final Basket basket;
    private final List<Team> teams;

    public FillBasketTask(Basket basket, List<Team> teams) {
        this.basket = basket;
        this.teams = teams;
    }

    public void startDraw() {
        System.out.println("FillBasketService started.");
        try { call(); }
        catch (Exception e) { e.printStackTrace(); }
    }

    private ObservableList<String> newObservableList() {
        System.out.println("getProgress(): " + getProgress());
        return FXCollections.observableArrayList(basket.getCurrentTeamNames());
    }

    @Override
    protected ObservableList<String> call() throws Exception {
        for (int i = 0; i < SceneController.MAX_BASKET_CAPACITY; i++) {
            basket.putTeam(teams.get(i)); // TODO FIXME
        }
        System.out.println("returning new observable list");
        return newObservableList();
    }
}