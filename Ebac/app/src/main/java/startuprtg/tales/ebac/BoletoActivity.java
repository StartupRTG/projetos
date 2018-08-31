package startuprtg.tales.ebac;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.util.List;

import static startuprtg.tales.ebac.DbConnection.getBoleto;

/**
 * Created by Raphael on 16/08/2018.
 */

public class BoletoActivity extends AppCompatActivity {
//    List<Boleto> boletoList = getBoleto();
    List<Boleto> boletoList;
    List<Boleto> boletoListPago;
    ImageButton logoutBtn;

    private SessionManager sessionManager;
    private Usuario usuario;

    LinearLayout activity_boleto2;
    AnimationDrawable animationDrawable;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boleto2);

        backgroundAnimation();

        sessionManager = new SessionManager(this);
        usuario = Usuario.getInstance();

        RecyclerView rv = (RecyclerView)findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this));

        BoletoAdapter adapter = new BoletoAdapter(boletoList);
        rv.setAdapter(adapter);

        logoutBtn = (ImageButton) findViewById(R.id.imageButton);
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });

        // Teste Tales
        RecyclerView rv2 = (RecyclerView)findViewById(R.id.rv2);
        rv.setLayoutManager(new LinearLayoutManager(this));

        BoletoAdapter adapter2 = new BoletoAdapter(boletoListPago);
        rv2.setAdapter(adapter2);

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

    public void backgroundAnimation(){
        activity_boleto2 = (LinearLayout) findViewById(R.id.activity_main);
        animationDrawable = (AnimationDrawable) activity_boleto2.getBackground();
        animationDrawable.setEnterFadeDuration(4500);
        animationDrawable.setExitFadeDuration(4500);
        animationDrawable.start();
    }
}
