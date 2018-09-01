package startuprtg.tales.ebac;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Raphael on 01/09/2018.
 */

public class RecuperarSenha extends AppCompatActivity {
    EditText emailTxt;
    Button recBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recupera_login);

        emailTxt = (EditText) findViewById(R.id.editText);
        recBtn = (Button) findViewById(R.id.login_btn);

        recBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailTxt.getText().toString();

                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("message/rfc822");
                i.putExtra(Intent.EXTRA_EMAIL  , email);
                i.putExtra(Intent.EXTRA_SUBJECT, "Recuperação de Senha");
                i.putExtra(Intent.EXTRA_TEXT   , "Prezado,\n\nVocê solicitou recuperação de senha.\n\nUsuário: 123456\nSenha: 123456\n\nAtencioasmente,\n\nEbac.");
                try {
                    startActivity(Intent.createChooser(i, "Send mail..."));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(getApplicationContext(), "Não foi possível enviar email para " + email, Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
