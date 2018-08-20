package startuprtg.tales.ebac;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;

/**
 * Created by Raphael on 19/08/2018.
 */

public class SessionManager {
    private SharedPreferences pref;
    private Activity activity;
    private static final String KEY_LOGIN = "login";
    private static final String KEY_PWD = "password";
    private static final String IS_LOGIN = "IsLogged";

    public SessionManager(Activity activity){
        this.activity = activity;
        pref = activity.getSharedPreferences("count", 0);
    }

    public void createLoggedSession(String login, String password){
        pref.edit().putBoolean(IS_LOGIN, true).putString(KEY_LOGIN, login).putString(KEY_PWD, password).apply();
    }

    void checkLog(){
        if(!this.isLogged() || pref.getString(KEY_LOGIN, null) == null || pref.getString(KEY_PWD, null) == null){
            Intent intent = new Intent(activity.getApplicationContext(), LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            activity.startActivity(intent);
        } else {
            if(pref.getString(KEY_LOGIN, null) != null && pref.getString(KEY_PWD, null) != null){
                Intent intent = new Intent(activity.getApplicationContext(), BoletoActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                activity.startActivity(intent);
            } else {
                Intent intent = new Intent(activity.getApplicationContext(), LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                activity.startActivity(intent);
            }
        }
    }

    public String getUserLogin(){
        return pref.getString(KEY_LOGIN, null);
    }

    public String getUserPwd(){
        return pref.getString(KEY_PWD, null);
    }

    public void logOut(){
        pref.edit().clear().apply();

        Intent intent = new Intent(activity.getApplicationContext(), LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        activity.startActivity(intent);
    }

    public boolean isLogged(){
        return pref.getBoolean(IS_LOGIN, false);
    }
}
