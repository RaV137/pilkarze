package pl.goldy.danowski.pilkarze.list;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import pl.goldy.danowski.pilkarze.R;
import pl.goldy.danowski.pilkarze.player.Player;

public class PlayersAdapter extends ArrayAdapter<Player> {

    public PlayersAdapter(Context context, ArrayList<Player> users) {
        super(context, 0, users);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Player player = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.player, parent, false);
        }

        TextView tvPosition = convertView.findViewById(R.id.position);
        TextView tvName = convertView.findViewById(R.id.name);
        TextView tvGames = convertView.findViewById(R.id.games);
        TextView tvGoals = convertView.findViewById(R.id.goals);
        TextView tvAssists = convertView.findViewById(R.id.assists);
        TextView tvAdditional = convertView.findViewById(R.id.additional);

        assert player != null : "Received player is null!";
        tvPosition.setText(player.getPosition());
        tvPosition.setTextColor(ContextCompat.getColor(getContext(), player.getColor()));
        tvName.setText(player.getName());
        tvGames.setText(String.valueOf(player.getGames()));
        tvGoals.setText(String.valueOf(player.getGoals()));
        tvAssists.setText(String.valueOf(player.getAssists()));
        tvAdditional.setText(player.getAdditional());

        return convertView;
    }
}