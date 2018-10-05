package pl.goldy.danowski.pilkarze.activities;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import pl.goldy.danowski.pilkarze.R;
import pl.goldy.danowski.pilkarze.list.PlayerListHandler;

public class PlayersListActivity extends AppCompatActivity {

    private View headView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_players_list);
        PlayerListHandler.initialize();
        PlayerListHandler.fillList(getBaseContext());

        headView = findViewById(R.id.player_list_layout);
        PlayerListHandler.printPlayers(headView);
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
                openSettings();
                return true;
            case R.id.statistics:
                // TODO
                return true;
            case R.id.reset:
                reset();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void openSettings() {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    private void reset() {
        PlayerListHandler.reset(getBaseContext(), headView);
    }

    public void sortByPosition(View v) {
        PlayerListHandler.sortByPosition(headView);
    }

    public void sortByName(View v) {
        PlayerListHandler.sortByName(headView);
    }

    public void sortByGames(View v) {
        PlayerListHandler.sortByGames(headView);
    }

    public void sortByGoals(View v) {
        PlayerListHandler.sortByGoals(headView);
    }

    public void sortByAssists(View v) {
        PlayerListHandler.sortByAssists(headView);
    }

    public void sortByAdditional(View v) {
        PlayerListHandler.sortByAdditional(headView);
    }

}
