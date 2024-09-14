package com.termux.app.fragments.settings;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.Keep;
import androidx.preference.PreferenceDataStore;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;
import com.termux.R;
import com.termux.shared.termux.settings.preferences.TermuxGUIAppSharedPreferences;

@Keep
public class TermuxGUIPreferencesFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        Context context = getContext();
        if (context == null)
            return;
        PreferenceManager preferenceManager = getPreferenceManager();
        preferenceManager.setPreferenceDataStore(TermuxGUIPreferencesDataStore.getInstance(context));
        setPreferencesFromResource(R.xml.termux_gui_preferences, rootKey);
    }
}

class TermuxGUIPreferencesDataStore extends PreferenceDataStore {

    private final Context mContext;

    private final TermuxGUIAppSharedPreferences mPreferences;

    private static TermuxGUIPreferencesDataStore mInstance;

    private TermuxGUIPreferencesDataStore(Context context) {
        mContext = context;
        mPreferences = TermuxGUIAppSharedPreferences.build(context, true);
    }

    public static synchronized TermuxGUIPreferencesDataStore getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new TermuxGUIPreferencesDataStore(context);
        }
        return mInstance;
    }
}
