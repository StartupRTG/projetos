package startuprtg.tales.ebac;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

import static startuprtg.tales.ebac.DbConnection.getUser;

/**
 * Created by Raphael on 19/08/2018.
 */

public class LoginActivity extends AppCompatActivity {
    Button loginBtn;
    EditText loginEd, pwdEd;
    private SessionManager sessionManager;
    private Activity activity;
    private static Usuario usuario;
    private UsuarioDao usuarioDao;

    public static final String MyPreferences = "MyPrefs";
    public static final String Login = "loginKey";
    public static final String Password = "pwdKey";

    RelativeLayout activity_loginn;
    AnimationDrawable animationDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        backgroundAnimation();

        activity = this;
        sessionManager = new SessionManager(this);
        Usuario.getInstance().logOut();
        usuario = Usuario.getInstance();

        loginEd = (EditText) findViewById(R.id.editText);
        pwdEd = (EditText) findViewById(R.id.editText3);
        loginBtn = (Button) findViewById(R.id.login_btn);

        // Create Mask CPF/CNPJ
        SimpleMaskFormatter smfCPF = new SimpleMaskFormatter("NNN.NNN.NNN-NN");
        SimpleMaskFormatter smfCNPJ = new SimpleMaskFormatter("NN.NNN.NNN/NNNN-NN");
        MaskTextWatcher mtwCPF = new MaskTextWatcher(loginEd, smfCPF);
        MaskTextWatcher mtwCNPJ = new MaskTextWatcher(loginEd, smfCNPJ);
        if(loginEd.length()>14) {
            loginEd.addTextChangedListener(mtwCNPJ);
        }else {
            loginEd.addTextChangedListener(mtwCPF);
        }


        if(sessionManager.isLogged()){
            loginEd.setText(sessionManager.getUserLogin());
        }

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Usuario.getInstance().logOut();
        if(sessionManager.isLogged()){
            loginEd.setText(sessionManager.getUserLogin());
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    public void login(){
        if(!isOnline()){
            Snackbar.make(getWindow().getDecorView().getRootView(),"Não há conexão com a internet", Snackbar.LENGTH_LONG).show();
        } else {
            String login = loginEd.getText().toString().replace(".","").replace("-","").replace("/","");
            String pwd = pwdEd.getText().toString();

//            if(getUser(login, pwd)){
//                sessionManager.createLoggedSession(login, pwd);
//                newIntent(getApplicationContext(), BoletoActivity.class);
//                finish();
//            } else {
//                Toast.makeText(getApplicationContext(), "Usuário não encontrado.", Toast.LENGTH_LONG).show();
//            }

            if (login.equals("123456789") && pwd.equals("123456")) {
                sessionManager.createLoggedSession(login, pwd);
                newIntent(getApplicationContext(), BoletoActivity.class);
                finish();
            } else {
                Toast.makeText(getApplicationContext(), "CNPJ/CPF ou Senha incorretos.", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void newIntent(Context context, Class<?> cls){
        Intent intent = new Intent(context, cls);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void backgroundAnimation(){
        activity_loginn = (RelativeLayout) findViewById(R.id.activity_main);
        animationDrawable = (AnimationDrawable) activity_loginn.getBackground();
        animationDrawable.setEnterFadeDuration(4500);
        animationDrawable.setExitFadeDuration(4500);
        animationDrawable.start();
    }

    public boolean isOnline() {
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return manager.getActiveNetworkInfo() != null && manager.getActiveNetworkInfo().isConnectedOrConnecting();
    }
}
