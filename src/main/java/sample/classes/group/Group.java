package sample.classes.group;

import sample.classes.group.score.Score;
import sample.classes.team.Team;

public class Group {
    private TeamBundle[] teamBundle;

    public Group() {
    }

    public Group(Team team1, Team team2, Team team3, Team team4) {
        teamBundle[0] = new TeamBundle(team1);
        teamBundle[1] = new TeamBundle(team2);
        teamBundle[2] = new TeamBundle(team3);
        teamBundle[3] = new TeamBundle(team4);
    }

    private class TeamBundle {
        private Team team;
        private Score score;

        TeamBundle(Team team) {
            this.team = team;
            score = new Score();
        }

        public Team getTeam() {
            return team;
        }

        public Score getScore() {
            return score;
        }
    }
}
