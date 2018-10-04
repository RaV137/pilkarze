package pl.goldy.danowski.pilkarze.list;

import java.util.ArrayList;

import pl.goldy.danowski.pilkarze.player.Player;

public class PlayerListHandler {

    private static ArrayList<Player> players;
    private static int size = 18;
    private static boolean initialized = false;

    public static ArrayList<Player> getPlayers() {
        return initialized ? players : new ArrayList<Player>();
    }

    public static int getSize() {
        return size;
    }

    public static void setSize(int size) {
        PlayerListHandler.size = size;
    }

    public static void clearList() {
        players.clear();
    }

    public static void fillList() {
        initialized = true;

        // TODO

    }
}
