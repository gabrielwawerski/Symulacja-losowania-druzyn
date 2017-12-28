package sample.classes.group;

import sample.classes.team.Continent;
import sample.classes.team.Team;
import sample.controllers.SceneController;

public class Group {
    private Team[] teams;
    private int index;

    // ASIA, AFRICA, NORTH_AMERICA, SOUTH_AMERICA, EUROPE, AUSTRALIA_OCEANIA
    private int teamCount;
    private int asianTeams;
    private int africanTeams;
    private int northAmericanTeams;
    private int southAmericanTeams;
    private int europeanTeams;
    private int australiaOceaniaTeams;

    private static final int MAX_OTHER_CONTINENTS_TEAMS = 1;

    private static final int MAX_EUROPE_TEAMS = 2;

    public Group() {
        teams = new Team[SceneController.MAX_GROUP_CAPACITY];
        index = 0;

        asianTeams = 0;
        africanTeams = 0;
        northAmericanTeams = 0;
        southAmericanTeams = 0;
        europeanTeams = 0;
        australiaOceaniaTeams = 0;
    }

    public void putTeam(Team team) {
        incrementContinentCounter(team.getContinent());
        teams[index] = team;
        index++;
    }

//    public boolean validateTeamContinent() {
//
//    }

    private void incrementContinentCounter(Continent continent) {
        if (continent == Continent.ASIA)
            asianTeams++;

        if (continent == Continent.AFRICA)
            africanTeams++;

        if (continent == Continent.NORTH_AMERICA)
            northAmericanTeams++;

        if (continent == Continent.SOUTH_AMERICA) // 792 179 302
            southAmericanTeams++;

        if (continent == Continent.EUROPE)
            europeanTeams++;

        if (continent == Continent.AUSTRALIA_OCEANIA)
            australiaOceaniaTeams++;
    }

    public int getTeamCount() {
        return teamCount;
    }

    public int getAsianTeams() {
        return asianTeams;
    }

    public int getAfricanTeams() {
        return africanTeams;
    }

    public int getNorthAmericanTeams() {
        return northAmericanTeams;
    }

    public int getSouthAmericanTeams() {
        return southAmericanTeams;
    }

    public int getEuropeanTeams() {
        return europeanTeams;
    }

    public int getAustraliaOceaniaTeams() {
        return australiaOceaniaTeams;
    }
}
