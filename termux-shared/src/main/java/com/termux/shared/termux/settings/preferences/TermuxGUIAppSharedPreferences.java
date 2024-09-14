package com.termux.shared.termux.settings.preferences;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.termux.shared.android.PackageUtils;
import com.termux.shared.settings.preferences.AppSharedPreferences;
import com.termux.shared.settings.preferences.SharedPreferenceUtils;
import com.termux.shared.termux.TermuxConstants;
import com.termux.shared.termux.TermuxUtils;
import com.termux.shared.termux.settings.preferences.TermuxPreferenceConstants.TERMUX_GUI_APP;
import com.termux.shared.logger.Logger;

public class TermuxGUIAppSharedPreferences extends AppSharedPreferences {

    private static final String LOG_TAG = "TermuxGUIAppSharedPreferences";

    private TermuxGUIAppSharedPreferences(@NonNull Context context) {
        super(context, SharedPreferenceUtils.getPrivateSharedPreferences(context, TermuxConstants.TERMUX_GUI_DEFAULT_PREFERENCES_FILE_BASENAME_WITHOUT_EXTENSION), SharedPreferenceUtils.getPrivateAndMultiProcessSharedPreferences(context, TermuxConstants.TERMUX_GUI_DEFAULT_PREFERENCES_FILE_BASENAME_WITHOUT_EXTENSION));
    }

    /**
     * Get {@link TermuxGUIAppSharedPreferences}.
     *
     * @param context The {@link Context} to use to get the {@link Context} of the
     *                {@link TermuxConstants#TERMUX_GUI_PACKAGE_NAME}.
     * @return Returns the {@link TermuxGUIAppSharedPreferences}. This will {@code null} if an exception is raised.
     */
    @Nullable
    public static TermuxGUIAppSharedPreferences build(@NonNull final Context context) {
        Context termuxGUIPackageContext = PackageUtils.getContextForPackage(context, TermuxConstants.TERMUX_GUI_PACKAGE_NAME);
        if (termuxGUIPackageContext == null)
            return null;
        else
            return new TermuxGUIAppSharedPreferences(termuxGUIPackageContext);
    }

    /**
     * Get {@link TermuxGUIAppSharedPreferences}.
     *
     * @param context The {@link Context} to use to get the {@link Context} of the
     *                {@link TermuxConstants#TERMUX_GUI_PACKAGE_NAME}.
     * @param exitAppOnError If {@code true} and failed to get package context, then a dialog will
     *                       be shown which when dismissed will exit the app.
     * @return Returns the {@link TermuxGUIAppSharedPreferences}. This will {@code null} if an exception is raised.
     */
    public static TermuxGUIAppSharedPreferences build(@NonNull final Context context, final boolean exitAppOnError) {
        Context termuxGUIPackageContext = TermuxUtils.getContextForPackageOrExitApp(context, TermuxConstants.TERMUX_GUI_PACKAGE_NAME, exitAppOnError);
        if (termuxGUIPackageContext == null)
            return null;
        else
            return new TermuxGUIAppSharedPreferences(termuxGUIPackageContext);
    }

    public int getLogLevel(boolean readFromFile) {
        if (readFromFile)
            return SharedPreferenceUtils.getInt(mMultiProcessSharedPreferences, TERMUX_GUI_APP.KEY_LOG_LEVEL, Logger.DEFAULT_LOG_LEVEL);
        else
            return SharedPreferenceUtils.getInt(mSharedPreferences, TERMUX_GUI_APP.KEY_LOG_LEVEL, Logger.DEFAULT_LOG_LEVEL);
    }

    public void setLogLevel(Context context, int logLevel, boolean commitToFile) {
        logLevel = Logger.setLogLevel(context, logLevel);
        SharedPreferenceUtils.setInt(mSharedPreferences, TERMUX_GUI_APP.KEY_LOG_LEVEL, logLevel, commitToFile);
    }

}
