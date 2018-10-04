package pl.goldy.danowski.pilkarze.player;

import pl.goldy.danowski.pilkarze.R;

public class Goalkeeper extends Player {

    public Goalkeeper(String name, int from, int to) {
        super(name, from, to);
        this.color = R.color.goalkeeper;
        this.additional = generateNumber(0, games);
        this.goals = 0;
        this.assists = generateNumber(0, Math.max((int) Math.floor(games * 0.06), 1));
        this.position = "BR";
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
}
