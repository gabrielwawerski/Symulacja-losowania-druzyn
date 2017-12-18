package sample.classes.team;

import javafx.scene.image.Image;

public class Team {
    private String name;
    private int rating;
    private Image flag;
    private Continent continent;

    public Team(String name, int rating, Image flag, Continent continent) {
        this.name = name;
        this.rating = rating;
        this.flag = flag;
        this.continent = continent;
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

    public Image getFlag() {
        return flag;
    }

    public void setFlag(Image flag) {
        this.flag = flag;
    }

    public Continent getContinent() {
        return continent;
    }

    public void setContinent(Continent continent) {
        this.continent = continent;
    }
}
