package pl.goldy.danowski.pilkarze.player;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import pl.goldy.danowski.pilkarze.R;
import pl.goldy.danowski.pilkarze.list.PlayersAdapter;
import pl.goldy.danowski.pilkarze.settings.sharedPreferences.SharedPreferencesUtils;
import pl.goldy.danowski.pilkarze.statisctics.Stat;
import pl.goldy.danowski.pilkarze.statisctics.StatisticsAdapter;
import pl.goldy.danowski.pilkarze.statisctics.StatisticsNames;

public class PlayerListHandler {

    private static Map<String, Integer> sortCount;
    private static ArrayList<Player> players;
    private static boolean initialized = false;

    public static void initialize(Context context) {
//        if (initialized) throw new AssertionError("Class already initialized!");

        if(initialized)
            return;

        sortCount = new HashMap<>();
        players = new ArrayList<>();
        initialized = true;

        fillList(context);
    }

    public static void printPlayers(View v) {
        if (!initialized) throw new AssertionError("Class not initialized!");

        ListView list = v.findViewById(R.id.playersList);
        PlayersAdapter adapter = new PlayersAdapter(v.getContext(), players);
        list.setAdapter(adapter);
    }

    public static void fillList(Context context) {
        if (!initialized) throw new AssertionError("Class not initialized!");

        int size = SharedPreferencesUtils.getIntegerPreference(context, context.getResources().getString(R.string.list_size_key), 18);
        int from = SharedPreferencesUtils.getIntegerPreference(context, context.getResources().getString(R.string.min_games_key), 0);
        int to = SharedPreferencesUtils.getIntegerPreference(context, context.getResources().getString(R.string.max_games_key), 50);

        if(to < from) {
            to = from + 1;
            SharedPreferencesUtils.setIntegerPreference(context, context.getResources().getString(R.string.max_games_key), to);
            Toast.makeText(context, "Maksymalna liczba gier musi być większa niż minimalna! Nowa wartość to " + to, Toast.LENGTH_SHORT).show();
        }

        for(int i = 0; i < size; ++i) {
            int num = generateNum(4);
            switch(num % 4) {
                case 0:
                    players.add(new Goalkeeper(generateSurnameAndName(), from, to));
                    break;
                case 1:
                    players.add(new Defender(generateSurnameAndName(), from, to));
                    break;
                case 2:
                    players.add(new Midfielder(generateSurnameAndName(), from, to));
                    break;
                case 3:
                    players.add(new Forward(generateSurnameAndName(), from, to));
                    break;
            }
        }
    }

    public static void sortByPosition(View v) {
        if (!initialized) throw new AssertionError("Class not initialized!");

        String key = "POSITION";
        setToDefaultExcept(v, R.id.position, key);
        if(sortCount.get(key) % 2 == 1) {
            players.sort(new Comparator<Player>() {
                @Override
                public int compare(Player o1, Player o2) {
                    return getIntegerForPosition(o1.getPosition()) - getIntegerForPosition(o2.getPosition());
                }
            });
        } else {
            players.sort(new Comparator<Player>() {
                @Override
                public int compare(Player o1, Player o2) {
                    return getIntegerForPosition(o2.getPosition()) - getIntegerForPosition(o1.getPosition());
                }
            });
        }
        printPlayers(v);
    }

    public static void sortByName(View v) {
        if (!initialized) throw new AssertionError("Class not initialized!");

        String key = "NAME";
        setToDefaultExcept(v, R.id.name, key);
        if(sortCount.get(key) % 2 == 1) {
            players.sort(new Comparator<Player>() {
                @Override
                public int compare(Player o1, Player o2) {
                    return o1.getName().compareTo(o2.getName());
                }
            });
        } else {
            players.sort(new Comparator<Player>() {
                @Override
                public int compare(Player o1, Player o2) {
                    return o2.getName().compareTo(o1.getName());
                }
            });
        }
        printPlayers(v);
    }

    public static void sortByGames(View v) {
        if (!initialized) throw new AssertionError("Class not initialized!");

        String key = "GAMES";
        setToDefaultExcept(v, R.id.games, key);
        if(sortCount.get(key) % 2 == 0) {
            players.sort(new Comparator<Player>() {
                @Override
                public int compare(Player o1, Player o2) {
                    return o1.getGames() - o2.getGames();
                }
            });
        } else {
            players.sort(new Comparator<Player>() {
                @Override
                public int compare(Player o1, Player o2) {
                    return o2.getGames() - o1.getGames();
                }
            });
        }
        printPlayers(v);
    }

    public static void sortByGoals(View v) {
        if (!initialized) throw new AssertionError("Class not initialized!");

        String key = "GOALS";
        setToDefaultExcept(v, R.id.goals, key);
        if(sortCount.get(key) % 2 == 0) {
            players.sort(new Comparator<Player>() {
                @Override
                public int compare(Player o1, Player o2) {
                    return o1.getGoals() - o2.getGoals();
                }
            });
        } else {
            players.sort(new Comparator<Player>() {
                @Override
                public int compare(Player o1, Player o2) {
                    return o2.getGoals() - o1.getGoals();
                }
            });
        }
        printPlayers(v);
    }

    public static void sortByAssists(View v) {
        if (!initialized) throw new AssertionError("Class not initialized!");

        String key = "ASSISTS";
        setToDefaultExcept(v, R.id.assists, key);
        if(sortCount.get(key) % 2 == 0) {
            players.sort(new Comparator<Player>() {
                @Override
                public int compare(Player o1, Player o2) {
                    return o1.getAssists() - o2.getAssists();
                }
            });
        } else {
            players.sort(new Comparator<Player>() {
                @Override
                public int compare(Player o1, Player o2) {
                    return o2.getAssists() - o1.getAssists();
                }
            });
        }
        printPlayers(v);
    }

    public static void sortByAdditional(View v) {
        if (!initialized) throw new AssertionError("Class not initialized!");

        String key = "ADDITIONAL";
        setToDefaultExcept(v, R.id.additional, key);
        if(sortCount.get(key) % 2 == 0) {
            players.sort(new Comparator<Player>() {
                @Override
                public int compare(Player o1, Player o2) {
                    return o1.getAdditionalValue() - o2.getAdditionalValue();
                }
            });
        } else {
            players.sort(new Comparator<Player>() {
                @Override
                public int compare(Player o1, Player o2) {
                    return o2.getAdditionalValue() - o1.getAdditionalValue();
                }
            });
        }
        printPlayers(v);
    }

    public static void reset(Context context, View view) {
        if (!initialized) throw new AssertionError("Class not initialized!");

        players.clear();
//        clearPlayers();
        fillList(context);
        printPlayers(view);
        setToDefault(view);
        sortCount.clear();
    }

    public static void showStatistics(View v) {
        if (!initialized) throw new AssertionError("Class not initialized!");

        ArrayList<Stat> statistics = getStatistics();

        ListView list = v.findViewById(R.id.statistics);
        StatisticsAdapter adapter = new StatisticsAdapter(v.getContext(), statistics);
        list.setAdapter(adapter);
    }

    public static void setToDefaults(View v) {
        setToDefault(v);
        sortCount.clear();
    }



//    private static void clearPlayers() {
//        try{
//            for(Player p : players) {
//                if(p instanceof Goalkeeper) {
//                    ((Goalkeeper) p).finalize();
//                } else if(p instanceof Defender) {
//                    ((Defender) p).finalize();
//                } else if(p instanceof Midfielder) {
//                    ((Midfielder) p).finalize();
//                } else if(p instanceof Forward) {
//                    ((Forward) p).finalize();
//                }
//                players.remove(p);
//            }
//        } catch (Throwable t) {
//            t.printStackTrace();
//        }
//    }

    private static ArrayList<Stat> getStatistics() {
        ArrayList<Stat> stats = new ArrayList<>();

        stats.add(new Stat(StatisticsNames.GOALKEEPERS_COUNT.getName(), getGoalkeeperCount()));
        stats.add(new Stat(StatisticsNames.DEFENDERS_COUNT.getName(), getDefenderCount()));
        stats.add(new Stat(StatisticsNames.MIDFIELDERS_COUNT.getName(), getMidfielderCount()));
        stats.add(new Stat(StatisticsNames.FORWARDS_COUNT.getName(), getForwardCount()));
        stats.add(new Stat(StatisticsNames.TOTAL_GAMES.getName(), getTotalGames()));
        stats.add(new Stat(StatisticsNames.TOTAL_GOALS.getName(), getTotalGoals()));
        stats.add(new Stat(StatisticsNames.TOTAL_ASSISTS.getName(), getTotalAssists()));
        stats.add(new Stat(StatisticsNames.GOALKEEPERS_ADDITIONAL.getName(), getGoalkeeperAdditional()));
        stats.add(new Stat(StatisticsNames.DEFENDERS_ADDITIONAL.getName(), getDefenderAdditional()));
        stats.add(new Stat(StatisticsNames.MIDFIELDERS_ADDITIONAL.getName(), getMidfielderAdditional()));
        stats.add(new Stat(StatisticsNames.FORWARDS_ADDITIONAL.getName(), getForwardAdditional()));

        return stats;
    }

    private static int getGoalkeeperCount() {
        return Goalkeeper.getCount();
    }

    private static int getDefenderCount() {
        return Defender.getCount();
    }

    private static int getMidfielderCount() {
        return Midfielder.getCount();
    }

    private static int getForwardCount() {
        return Forward.getCount();
    }

    private static int getTotalGames() {
        return Player.getTotalGames();
    }

    private static int getTotalGoals() {
        return Player.getTotalGoals();
    }

    private static int getTotalAssists() {
        return Player.getTotalAssists();
    }

    private static int getGoalkeeperAdditional() {
        return Goalkeeper.getTotalAdditional();
    }

    private static int getDefenderAdditional() {
        return Defender.getTotalAdditional();
    }

    private static int getMidfielderAdditional() {
        return Midfielder.getTotalAdditional();
    }

    private static int getForwardAdditional() {
        return Forward.getTotalAdditional();
    }

    private static int generateNum(int exlBound) {
        Random gen = new Random();
        return gen.nextInt(exlBound);
    }

    private static String generateName() {
        String[] names = { "Wojciech", "Zdzisław", "Stanisław", "Jacek", "Mieczysław", "Hieronim", "Władysław", "Adam", "Marcin", "Mateusz" };
        return names[generateNum(names.length)];
    }

    private static String generateSurname() {
        String[] surnames = { "Kowalski", "Nowak", "Grzyb", "Szkutnik", "Jarkiewicz", "Bochnia", "Witkowski", "Kręcioła", "Bombrzyński", "Mak" };
        return surnames[generateNum(surnames.length)];
    }

    private static String generateSurnameAndName() {
        String name = generateName();
        String surname = generateSurname();
        return surname + " " + name;
    }

    private static int getIntegerForPosition(final String pos) {
        int num;
        switch (pos) {
            case "BR":
                num = 0;
                break;
            case "OBR":
                num = 1;
                break;
            case "POM":
                num = 2;
                break;
            default:
                num = 3;
                break;
        }
        return num;
    }

    private static void setToDefaultExcept(View v, int id, String key) {
        setToDefault(v);

        TextView changeColorView = v.findViewById(id);
        changeColorView.setTextColor(ContextCompat.getColor(v.getContext(), R.color.colorPrimary));

        if(!sortCount.containsKey(key)) {
            sortCount.clear();
            sortCount.put(key, 1);
        } else {
            int count = sortCount.get(key);
            sortCount.clear();
            if(count >= 2) {
                sortCount.put(key, 1);
            } else {
                sortCount.put(key, count + 1);
            }
        }
    }

    private static void setToDefault(View v) {
        TextView position = v.findViewById(R.id.position);
        position.setTextColor(ContextCompat.getColor(v.getContext(), R.color.textColor));
        TextView name = v.findViewById(R.id.name);
        name.setTextColor(ContextCompat.getColor(v.getContext(), R.color.textColor));
        TextView games = v.findViewById(R.id.games);
        games.setTextColor(ContextCompat.getColor(v.getContext(), R.color.textColor));
        TextView goals = v.findViewById(R.id.goals);
        goals.setTextColor(ContextCompat.getColor(v.getContext(), R.color.textColor));
        TextView assists = v.findViewById(R.id.assists);
        assists.setTextColor(ContextCompat.getColor(v.getContext(), R.color.textColor));
        TextView additional = v.findViewById(R.id.additional);
        additional.setTextColor(ContextCompat.getColor(v.getContext(), R.color.textColor));
    }

}
