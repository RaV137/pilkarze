package pl.goldy.danowski.pilkarze.statisctics;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import pl.goldy.danowski.pilkarze.R;

public class StatisticsAdapter extends ArrayAdapter<Stat> {

    public StatisticsAdapter(Context context, ArrayList<Stat> stats) {
        super(context, 0, stats);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Stat stat = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.stat, parent, false);
        }

        TextView tvName = convertView.findViewById(R.id.statistic_name);
        TextView tvValue = convertView.findViewById(R.id.statistic_value);

        assert stat != null : "Received stat is null!";
        tvName.setText(stat.getName());
        tvValue.setText(String.valueOf(stat.getValue()));

        return convertView;
    }

}
