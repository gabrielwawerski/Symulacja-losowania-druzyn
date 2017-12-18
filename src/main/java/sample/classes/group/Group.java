package sample.classes.group;

import sample.classes.group.score.Score;
import sample.classes.team.Team;

public class Group {
    private TeamScore[] teamScore;

    public Group() {
    }

    private class TeamScore {
        private Team team;
        private Score score;
    }
}
