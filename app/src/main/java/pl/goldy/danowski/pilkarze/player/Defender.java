package pl.goldy.danowski.pilkarze.player;

import pl.goldy.danowski.pilkarze.R;

public class Defender extends Player {

    public Defender(String name, int from, int to) {
        super(name, from, to);
        this.goals = generateNumber(0, (int) Math.floor(games * 0.19));
        this.assists = generateNumber(0,(int) Math.floor(games * 0.21));
        this.color = R.color.defender;
        this.additional = generateNumber(0, (int) Math.floor(games * 6.1));
        this.position = "OBR";
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
}
