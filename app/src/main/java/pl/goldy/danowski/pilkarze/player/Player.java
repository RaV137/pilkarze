package pl.goldy.danowski.pilkarze.player;


import java.util.Random;

public class Player {

    protected String name;
    int games;
    int goals;
    int assists;
    String position;
    int additional;

    public String getPosition() {
        return position;
    }

    public int getAdditionalValue() {
        return this.additional;
    }

    public String getAdditional() {
        return String.valueOf(additional);
    }

    public String getName() {
        return name;
    }

    public int getGames() {
        return games;
    }

    public int getGoals() {
        return goals;
    }

    public int getAssists() {
        return assists;
    }

    public int getColor() {
        return color;
    }

    protected int color;

    Player(String name, int from, int to) {
        this.name = name;
        this.games = generateNumber(from, to);
    }

    int generateNumber(int from, int to) {
        Random generator = new Random();
        return generator.nextInt(to - from + 1) + from;
    }
}
