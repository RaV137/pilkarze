package pl.goldy.danowski.pilkarze.statisctics;

public class Stat {

    private String name;
    private int value;

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    public Stat(String name, int value) {

        this.name = name;
        this.value = value;
    }
}
