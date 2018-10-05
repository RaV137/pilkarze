package pl.goldy.danowski.pilkarze.activities;

import android.content.res.Configuration;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.PreferenceActivity;
import android.text.InputFilter;

import pl.goldy.danowski.pilkarze.R;
import pl.goldy.danowski.pilkarze.settings.InputFilterMinMax;

public class SettingsActivity extends PreferenceActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);

        EditTextPreference listSize = (EditTextPreference) findPreference(this.getString(R.string.list_size_key));
        listSize.getEditText().setFilters(
                new InputFilter[]{ new InputFilterMinMax(this.getString(R.string.min_list_size), this.getString(R.string.max_list_size))});

        EditTextPreference minGames = (EditTextPreference) findPreference(this.getString(R.string.min_games_key));
        minGames.getEditText().setFilters(
                new InputFilter[]{ new InputFilterMinMax(this.getString(R.string.min_min_games), this.getString(R.string.max_min_games))});

        EditTextPreference maxGames = (EditTextPreference) findPreference(this.getString(R.string.max_games_key));
        maxGames.getEditText().setFilters(
                new InputFilter[]{ new InputFilterMinMax(this.getString(R.string.min_max_games), this.getString(R.string.max_max_games))});
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }


}


