package com.sladma.common.preferences;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

/**
 * Helper class for manipulation with SharedPreferences
 * <p>
 * Created by sladma
 */
@SuppressLint("CommitPrefEdits")
public class SharedPreferencesHelper {

    private static final String DEFAULT_SHARED_PREFERENCES_NAME = "SladmaSharedPreferences";

    public static void writeLong(@NonNull final Context context, @NonNull final String key, long value) {
        SharedPreferences.Editor editor = getDefaultPreferences(context).edit();
        editor.putLong(key, value);
        editor.commit();
    }

    public static long readLong(@NonNull final Context context, @NonNull final String key, long defaultValue) {
        return getDefaultPreferences(context).getLong(key, defaultValue);
    }

    private static SharedPreferences getDefaultPreferences(@NonNull final Context context) {
        return context.getSharedPreferences(DEFAULT_SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

}
