package sample.classes.basket;

import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import sample.classes.team.Team;
import sample.controllers.SceneController;

public class Basket {
    private Team[] teams;
    private ListView<String> listView;
    private ObservableList<String> observableList;
    private int index;

    // TODO think about whether group's ListView is needed here
    // and if it is, it should never be used inside Task

    public Basket(ListView<String> listView) {
        this.listView = listView;
        teams = new Team[SceneController.NUMBER_OF_BASKETS];
        index = 0;
    }

    public Team getTeam(int index) {
        if (index > SceneController.MAX_BASKET_CAPACITY)
            throw new IllegalArgumentException("Wrong index for teams array. Found:" + index);
        return teams[index];
    }

    public void putTeam(Team team) {
        if (index > teams.length)
            try {
                throw new Exception("should this check be necessary?");
            } catch (Exception e) {
                e.printStackTrace();
            }
        teams[index++] = team;
    }

    public void putTeam(int index, Team team) {
        teams[index] = team;
    }

    public Team[] getTeams() {
        return teams;
    }

    public String[] getCurrentTeamNames() {
        String[] returnvalue = new String[index];
        for (int i = 0; i < index; i++) {
            returnvalue[i] = teams[i].getName();
        }
        return returnvalue;
    }

    public void setTeams(Team[] teams) {
        this.teams = teams;
    }

    public String[] getTeamNames() {
        String[] returnValue = new String[teams.length];
        for (int i = 0; i < teams.length; i++) {
            returnValue[i] = teams[i].getName();
        }
        return returnValue;
    }

    public ListView<String> getListView() {
        return listView;
    }

    public ObservableList<String> getObservableList() {
        return observableList;
    }

    public void setObservableLists(ObservableList<String> observableList) {
        this.observableList = observableList;
    }
}
