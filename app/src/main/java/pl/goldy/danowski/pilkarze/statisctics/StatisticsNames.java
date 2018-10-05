package pl.goldy.danowski.pilkarze.statisctics;

public enum StatisticsNames {
    GOALKEEPERS_COUNT("Ilość bramkarzy:"),
    DEFENDERS_COUNT("Ilość obrońców:"),
    MIDFIELDERS_COUNT("Ilość pomocników:"),
    FORWARDS_COUNT("Ilość napastników:"),
    TOTAL_GOALS("Całkowita ilość strzelonych goli"),
    TOTAL_GAMES("Całkowita ilość rozegranych meczów"),
    TOTAL_ASSISTS("Całkowita ilość asyst"),
    GOALKEEPERS_ADDITIONAL("Całkowita ilość czystych kont bramkarzy"),
    DEFENDERS_ADDITIONAL("Całkowita ilość interwencji obrońców"),
    MIDFIELDERS_ADDITIONAL("Całkowita ilość podań kluczowych pomocników"),
    FORWARDS_ADDITIONAL("Całkowita ilość strzałów celnych napastników");

    private String name;

    StatisticsNames(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
