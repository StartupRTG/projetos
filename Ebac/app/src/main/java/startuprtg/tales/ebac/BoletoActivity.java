package startuprtg.tales.ebac;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.List;

/**
 * Created by Raphael on 16/08/2018.
 */

public class BoletoActivity extends AppCompatActivity {
    List<Boleto> boletoList;
    ImageButton logoutBtn;

    private SessionManager sessionManager;
    private Usuario usuario;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boleto);

        sessionManager = new SessionManager(this);
        usuario = Usuario.getInstance();

        RecyclerView rv = (RecyclerView)findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this));

        BoletoAdapter adapter = new BoletoAdapter(boletoList);
        rv.setAdapter(adapter);

        logoutBtn = (ImageButton) findViewById(R.id.logoutBtn);
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    public void logout(){
        sessionManager.logOut();
        Usuario.getInstance().logOut();

        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }
}
