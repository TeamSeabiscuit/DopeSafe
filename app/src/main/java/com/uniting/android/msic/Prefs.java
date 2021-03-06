package com.uniting.android.msic;

import android.content.Context;
import android.preference.PreferenceManager;
import android.support.v4.util.ArraySet;
import android.util.Log;

import java.util.Iterator;
import java.util.Set;

/**
 * Butchered by Chris Oelerich sometime after 5/27/16.
 * <p>
 * Convenience class for handling preferences.
 */
public class Prefs {
    private static final String TAG = "Prefs";

    public static String getPhone(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString(context.getString(R.string.emergency_contact_key), context.getString(R.string.pref_default_contact));
    }

    public static void setPhone(Context context, String phone) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString(context.getString(R.string.emergency_contact_key), phone).apply();
    }

    static String getPhoneAux(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString(context.getString(R.string.emergency_contact_aux_key), context.getString(R.string.pref_default_contact));
    }

    static void setPhoneAux(Context context, String phone) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString(context.getString(R.string.emergency_contact_aux_key), phone).apply();
    }

    static Set<String> getPhones(Context context) {
        ArraySet<String> strings = new ArraySet<>();
        strings.add(getPhone(context));

        String phoneAux = getPhoneAux(context);
        if (!phoneAux.equals("") && !phoneAux.equals(context.getString(R.string.pref_default_contact))) {
            strings.add(phoneAux);
        }

        return strings;
//        return PreferenceManager.getDefaultSharedPreferences(context).getStringSet(context.getString(R.string.emergency_contact_key), new ArraySet<>());
    }

    static void setPhones(Context context, Set<String> phones) {
        Iterator<String> iterator = phones.iterator();
        if (iterator.hasNext()) {
            setPhone(context, iterator.next());
        }
        if (iterator.hasNext()) {
            setPhoneAux(context, iterator.next());
        }
//        PreferenceManager.getDefaultSharedPreferences(context).edit().putStringSet(context.getString(R.string.emergency_contact_key), phones).apply();
    }

    static Integer getTime(Context context) {
        return Integer.valueOf(PreferenceManager.getDefaultSharedPreferences(context).getString(context.getString(R.string.countdown_time_key), "5"));
    }

    public static void setTime(Context context, Integer time) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString(context.getString(R.string.countdown_time_key), String.valueOf(time)).apply();
        Log.d(TAG, "setTime() called with: " + "time = [" + time + "]");
    }

    public static String getMsg(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString(context.getString(R.string.emergency_message_key), "");
    }

    public static void setMsg(Context context, String message) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString(context.getString(R.string.emergency_message_key), message).apply();
        Log.d(TAG, "setMsg() called with: " + "message = [" + message + "]");
    }

    public static boolean isLoc(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(context.getString(R.string.enable_location_key), false);
    }

    public static void setLoc(Context context, boolean location) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putBoolean(context.getString(R.string.enable_location_key), location).apply();
        Log.d(TAG, "setLoc() called with: " + "location = [" + location + "]");
    }

//    static boolean isFirstLocReq(Context context) {
//        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
//
//        boolean firstLocReq = sharedPreferences.getBoolean("firstLocReq", true);
//        if(firstLocReq) {
//            sharedPreferences.edit().putBoolean("firstLocReq", false).apply();
//        }
//        return firstLocReq;
//    }


    public static boolean isDisclaimerAccepted(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(context.getString(R.string.disclaimer_accepted_key), false);
    }

    static void setDisclaimerAccepted(Context context, boolean disclaimerAccepted) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putBoolean(context.getString(R.string.disclaimer_accepted_key), disclaimerAccepted).apply();
    }

    static boolean isSetupComplete(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(context.getString(R.string.setup_complete_key), false);
    }

    public static void setSetupComplete(Context context, boolean setupComplete) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putBoolean(context.getString(R.string.setup_complete_key), setupComplete).apply();
    }

}
