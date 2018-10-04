package pl.goldy.danowski.pilkarze.activities;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import pl.goldy.danowski.pilkarze.R;
import pl.goldy.danowski.pilkarze.adapters.PlayersAdapter;
import pl.goldy.danowski.pilkarze.player.Defender;
import pl.goldy.danowski.pilkarze.player.Forward;
import pl.goldy.danowski.pilkarze.player.Goalkeeper;
import pl.goldy.danowski.pilkarze.player.Midfielder;
import pl.goldy.danowski.pilkarze.player.Player;

public class PlayersListActivity extends AppCompatActivity {

    private Map<String, Integer> sortCount;
    private ArrayList<Player> players;
    private int size;
    private int from;
    private int to;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_players_list);

        sortCount = new HashMap<>();
        players = new ArrayList<>();
        setSize(18);
        setFrom(0);
        setTo(50);
        fillList();
        printPlayers();
    }

    // zapobiegnięcie onDestroy() i onCreate() przy zmianie orientacji
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.player_list_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.settings:
                // TODO
                return true;
            case R.id.statistics:
                // TODO
                return true;
            case R.id.reset:
                // TODO
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void printPlayers() {
        ListView list = findViewById(R.id.playersList);
        PlayersAdapter adapter = new PlayersAdapter(getBaseContext(), players);
        list.setAdapter(adapter);
    }

    private int generateNum(int exlBound) {
        Random gen = new Random();
        return gen.nextInt(exlBound);
    }

    private String generateName() {
        String[] names = { "Wojciech", "Zdzisław", "Stanisław", "Jacek", "Mieczysław", "Hieronim", "Władysław", "Adam", "Marcin", "Mateusz" };
        return names[generateNum(names.length)];
    }

    private String generateSurname() {
        String[] surnames = { "Kowalski", "Nowak", "Grzyb", "Szkutnik", "Jarkiewicz", "Bochnia", "Witkowski", "Kręcioła", "Bombrzyński", "Mak" };
        return surnames[generateNum(surnames.length)];
    }

    private String generateSurnameAndName() {
        String name = generateName();
        String surname = generateSurname();
        return surname + " " + name;
    }

    private void fillList() {
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

    private int getIntegerForPosition(final String pos) {
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

    public void sortByPosition(View v) {
        String key = "POSITION";
        setToDefaultExcept(R.id.position, key);
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
        printPlayers();
    }

    public void sortByName(View v) {
        String key = "NAME";
        setToDefaultExcept(R.id.name, key);
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
        printPlayers();
    }

    public void sortByGames(View v) {
        String key = "GAMES";
        setToDefaultExcept(R.id.games, key);
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
        printPlayers();
    }

    public void sortByGoals(View v) {
        String key = "GOALS";
        setToDefaultExcept(R.id.goals, key);
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
        printPlayers();
    }

    public void sortByAssists(View v) {
        String key = "ASSISTS";
        setToDefaultExcept(R.id.assists, key);
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
        printPlayers();
    }

    public void sortByAdditional(View v) {
        String key = "ADDITIONAL";
        setToDefaultExcept(R.id.additional, key);
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
        printPlayers();
    }

    private void setToDefaultExcept(int id, String key) {
        TextView position = findViewById(R.id.position);
        position.setTextColor(ContextCompat.getColor(this, R.color.textColor));
        TextView name = findViewById(R.id.name);
        name.setTextColor(ContextCompat.getColor(this, R.color.textColor));
        TextView games = findViewById(R.id.games);
        games.setTextColor(ContextCompat.getColor(this, R.color.textColor));
        TextView goals = findViewById(R.id.goals);
        goals.setTextColor(ContextCompat.getColor(this, R.color.textColor));
        TextView assists = findViewById(R.id.assists);
        assists.setTextColor(ContextCompat.getColor(this, R.color.textColor));
        TextView additional = findViewById(R.id.additional);
        additional.setTextColor(ContextCompat.getColor(this, R.color.textColor));

        TextView changeColorView = findViewById(id);
        changeColorView.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary));

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


    // ======================= Getters and setters =======================

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
    }
}
