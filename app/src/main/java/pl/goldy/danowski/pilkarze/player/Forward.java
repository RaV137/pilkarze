package pl.goldy.danowski.pilkarze.player;

import pl.goldy.danowski.pilkarze.R;

public class Forward extends Player {

    private static int totalAdditional = 0;
    private static int count = 0;

    public Forward(String name, int from, int to) {
        super(name, from, to);
        this.goals = generateNumber((int) Math.floor(games * 0.1), (int) Math.floor(games * 1.1));
        this.assists = generateNumber(0, (int) Math.floor(games * 0.3));
        this.color = R.color.forward;
        this.additional = generateNumber((int) Math.floor(goals * 1.3), (int) Math.floor(games * 5.6));
        this.position = "NAP";

        totalGoals += goals;
        totalAssists += assists;
        totalAdditional += additional;
        count++;
    }

    public static int getCount() {
        return count;
    }

    public static int getTotalAdditional() {
        return totalAdditional;
    }

    @Override
    public String getAdditional() {
        return "Strzały celne: " + this.additional;
    }

    @Override
    public String toString() {
        return this.position + " | " + this.name + " | " + this.games + " | "
                + this.goals + " | " + this.assists + " | Strzały celne: " + this.additional;
    }

    @Override
    protected void finalize() throws Throwable {
        totalAdditional -= additional;
        count--;
        super.finalize();
    }
}
