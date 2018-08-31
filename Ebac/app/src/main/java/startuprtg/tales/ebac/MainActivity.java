package startuprtg.tales.ebac;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.EventListener;

import static startuprtg.tales.ebac.DbConnection.connect;

public class MainActivity extends AppCompatActivity {

    RelativeLayout activity_loginn;
    AnimationDrawable animationDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        if(connect()) {
            if (isOnline()) {
                SessionManager sessionManager = new SessionManager(this);
                sessionManager.checkLog();
            } else {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
//        } else {
//            Toast.makeText(getApplicationContext(), "Não foi possível conectar ao banco de dados.", Toast.LENGTH_LONG).show();
//        }

        finish();
    }


    public boolean isOnline() {
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return manager.getActiveNetworkInfo() != null && manager.getActiveNetworkInfo().isConnectedOrConnecting();
    }
}
