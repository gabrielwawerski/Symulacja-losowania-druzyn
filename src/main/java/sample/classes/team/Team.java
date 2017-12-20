package sample.classes.team;

import javafx.scene.image.Image;

public class Team implements Comparable<Team> {
    private String name;
    private int rating;
    private Continent continent;

    private static int instanceCount = 0;

    public Team(String name, int rating, Continent continent) {
        this.name = name;
        this.rating = rating;
        this.continent = continent;
        instanceCount++;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Continent getContinent() {
        return continent;
    }

    public void setContinent(Continent continent) {
        this.continent = continent;
    }

    public static int getInstanceCount() {
        return instanceCount;
    }

    @Override
    public int compareTo(Team other) {
        return Integer.compare(this.rating, other.rating);
    }
}
