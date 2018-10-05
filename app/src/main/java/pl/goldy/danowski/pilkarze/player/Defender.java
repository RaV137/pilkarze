package pl.goldy.danowski.pilkarze.player;

import pl.goldy.danowski.pilkarze.R;

public class Defender extends Player {

    private static int totalAdditional = 0;
    private static int count = 0;

    public Defender(String name, int from, int to) {
        super(name, from, to);
        this.goals = generateNumber(0, (int) Math.floor(games * 0.19));
        this.assists = generateNumber(0,(int) Math.floor(games * 0.21));
        this.color = R.color.defender;
        this.additional = generateNumber(0, (int) Math.floor(games * 6.1));
        this.position = "OBR";

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
        return "Interwencje: " + this.additional;
    }

    @Override
    public String toString() {
        return this.position + " | " + this.name + " | " + this.games + " | "
                + this.goals + " | " + this.assists + " | Interwencje: " + this.additional;
    }

    @Override
    protected void finalize() throws Throwable {
        totalAdditional -= additional;
        count--;
        super.finalize();
    }
}
