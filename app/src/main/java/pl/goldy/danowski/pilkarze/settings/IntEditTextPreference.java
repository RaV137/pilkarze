package pl.goldy.danowski.pilkarze.settings;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;

import pl.goldy.danowski.pilkarze.R;

public class IntEditTextPreference extends EditTextPreference {

    public IntEditTextPreference(Context context) {
        super(context);
    }

    public IntEditTextPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public IntEditTextPreference(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected String getPersistedString(String defaultReturnValue) {
        return String.valueOf(getPersistedInt(-1));
    }

    @Override
    protected boolean persistString(String value) {
        return persistInt(Integer.valueOf(value));
    }

    @Override
    protected void onPrepareDialogBuilder(AlertDialog.Builder builder) {
        builder.getContext().setTheme(R.style.PreferencesTheme);
        super.onPrepareDialogBuilder(builder);
    }

    @Override
    protected void showDialog(Bundle state) {
        super.showDialog(state);

        final View editText = getDialog().getWindow().findViewById(android.R.id.edit);
        if (editText != null) {
            ((EditText) editText).setTextColor(ContextCompat.getColor(getContext(), R.color.textColor));
        }
    }
}
