package sample.classes.group;

import sample.classes.group.score.Score;
import sample.classes.team.Team;

public class Group {
    private TeamBundle[] teamBundle;

    public Group() {
        teamBundle[0] = new TeamBundle();
        teamBundle[1] = new TeamBundle();
        teamBundle[2] = new TeamBundle();
        teamBundle[3] = new TeamBundle();
    }

    public Group(Team team1, Team team2, Team team3, Team team4) {
        teamBundle[0] = new TeamBundle(team1);
        teamBundle[1] = new TeamBundle(team2);
        teamBundle[2] = new TeamBundle(team3);
        teamBundle[3] = new TeamBundle(team4);
    }

    public Team[] getAllTeams() {
        Team[] teams = new Team[4];
        for (int i = 0; i < teamBundle.length; i++)
            teams[i] = teamBundle[i].getTeam();
        return teams;
    }

    private class TeamBundle {
        private Team team;
        private Score score;

        TeamBundle() {
            score = new Score();
        }

        TeamBundle(Team team) {
            this.team = team;
            score = new Score();
        }

        public void putTeam(Team team) {
            this.team = team;
        }

        public Team getTeam() {
            return team;
        }

        public Score getScore() {
            return score;
        }
    }
}
