package org.jbfavre.thot.helpers;

import android.content.Context;
import android.content.SharedPreferences;

import org.jbfavre.thot.Activities.SetupActivity;
import org.jbfavre.thot.R;

/**
 * Created by jbfavre on 22/07/16.
 */
public class SharedPreferencesHelper {

    static public boolean isAppConfigured(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                context.getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        String endpoint = sharedPreferences.getString(context.getString(R.string.conf_endpoint), null);
        boolean authRequired = sharedPreferences.getBoolean(context.getString(R.string.conf_auth), false);
        if(authRequired){
            String login = sharedPreferences.getString(context.getString(R.string.conf_auth_login), null);
            String password = sharedPreferences.getString(context.getString(R.string.conf_auth_passwd), null);
            return endpoint != null && login != null && password != null;
        }
        return endpoint != null;
    }

    public static void saveConfiguration(Context context,
                                         String endpoint,
                                         boolean authRequired,
                                         String login,
                                         String password,
                                         boolean refreshAtStartup) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                context.getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(context.getString(R.string.conf_endpoint), endpoint);
        editor.putBoolean(context.getString(R.string.conf_auth), authRequired);
        if(authRequired){
            editor.putString(context.getString(R.string.conf_auth_login), login);
            editor.putString(context.getString(R.string.conf_auth_passwd), password);
        }
        editor.putBoolean(context.getString(R.string.conf_refresh_at_startup), refreshAtStartup);
        editor.apply();
    }

    public static String getEndpoint(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                context.getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        return sharedPreferences.getString(context.getString(R.string.conf_endpoint), null);
    }

    public static String getToken(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                context.getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        return sharedPreferences.getString(context.getString(R.string.conf_auth_token), null);
    }

    public static void saveToken(Context context, String authToken) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                context.getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(context.getString(R.string.conf_auth_token), authToken);
        editor.apply();
    }

    public static void clearConfiguration(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                context.getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
}
