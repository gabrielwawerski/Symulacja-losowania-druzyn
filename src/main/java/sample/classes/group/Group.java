package sample.classes.group;

import sample.classes.group.score.Score;
import sample.classes.team.Team;

public class Group {
    private TeamScore[] teamScore;

    public Group() {
    }

    public Group(Team team1, Team team2, Team team3, Team team4) {
        teamScore[0] = new TeamScore(team1);
        teamScore[1] = new TeamScore(team2);
        teamScore[2] = new TeamScore(team3);
        teamScore[3] = new TeamScore(team4);
    }

    private class TeamScore {
        private Team team;
        private Score score;

        TeamScore(Team team) {
            this.team = team;
            score = new Score();
        }
    }
}
