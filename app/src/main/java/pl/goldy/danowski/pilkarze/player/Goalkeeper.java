package pl.goldy.danowski.pilkarze.player;

import pl.goldy.danowski.pilkarze.R;

public class Goalkeeper extends Player {

    private static int totalAdditional = 0;
    private static int count = 0;

    public Goalkeeper(String name, int from, int to) {
        super(name, from, to);
        this.color = R.color.goalkeeper;
        this.additional = generateNumber(0, games);
        this.goals = 0;
        this.assists = 0;
        this.position = "BR";

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
        return "Czyste konta: " + this.additional;
    }

    @Override
    public String toString() {
        return this.position + " | " + this.name + " | " + this.games + " | "
                + this.goals + " | " + this.assists + " | Czyste konta: " + this.additional;
    }

    @Override
    protected void finalize() throws Throwable {
        totalAdditional -= additional;
        count--;
        super.finalize();
    }
}
