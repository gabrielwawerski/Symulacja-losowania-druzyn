package sample.classes.threads.tasks;

import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import sample.classes.basket.Basket;
import sample.classes.team.Team;
import sample.controllers.SceneController;

import java.util.ArrayList;
import java.util.List;

public class FillBasketTask extends Task<Basket> {
    private Basket basket; // container for teams
    private Team[] teams; // each list contains 8 teams

    public FillBasketTask(Basket basket, Team[] teams) {
        this.basket = basket;
        this.teams = teams;
    }

    @Override
    protected Basket call() throws Exception {
        for (int i = 0; i < Basket.MAX_BASKET_CAPACITY; i++) {

            updateProgress(i, Basket.MAX_BASKET_CAPACITY);
        }
        return basket;
    }
}
