package pl.goldy.danowski.pilkarze.player;

import pl.goldy.danowski.pilkarze.R;

public class Midfielder extends Player {

    public Midfielder(String name, int from, int to) {
        super(name, from, to);
        this.goals = generateNumber(0, (int) Math.floor(games * 0.3));
        this.assists = generateNumber((int) Math.floor(games * 0.05), (int) Math.floor(games * 0.43));
        this.color = R.color.midfielder;
        this.additional = generateNumber(0, (int) Math.floor(games * 0.69));
        this.position = "POM";
    }

    @Override
    public String getAdditional() {
        return "Pod. kluczowe: " + this.additional;
    }

    @Override
    public String toString() {
        return this.position + " | " + this.name + " | " + this.games + " | "
                + this.goals + " | " + this.assists + " | Podania kluczowe: " + this.additional;
    }
}
