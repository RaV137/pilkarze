package pl.goldy.danowski.pilkarze.player;


import java.util.Random;

public class Player {

    private static int totalGames = 0;
    protected static int totalGoals = 0, totalAssists = 0;

    protected String name;
    int games;
    int goals;
    int assists;
    String position;
    int additional;

    public static int getTotalGames() {
        return totalGames;
    }

    public static int getTotalGoals() {
        return totalGoals;
    }

    public static int getTotalAssists() {
        return totalAssists;
    }

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
        totalGames += games;
    }

    int generateNumber(int from, int to) {
        Random generator = new Random();
        return generator.nextInt(to - from + 1) + from;
    }

    @Override
    protected void finalize() throws Throwable {
        totalGames -= games;
        totalGoals -= goals;
        totalAssists -= assists;
        super.finalize();
    }
}
