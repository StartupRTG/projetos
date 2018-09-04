package startuprtg.tales.ebac;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Created by Raphael on 01/09/2018.
 */

public class RecuperarSenha extends AppCompatActivity {
    EditText emailTxt;
    Button recBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        setContentView(R.layout.activity_recupera_login);

        emailTxt = (EditText) findViewById(R.id.editText);
        recBtn = (Button) findViewById(R.id.login_btn);

        recBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailTxt.getText().toString();
                Context mContext = getApplicationContext();

                Properties props = new Properties();
                props.put("mail.smtp.auth", "true");
                props.put("mail.smtp.starttls.enable", "true");
                props.put("mail.smtp.host", "smtp.gmail.com");
                props.put("mail.smtp.port", "587");

                Session session = Session.getInstance(props,
                        new javax.mail.Authenticator() {
                            protected PasswordAuthentication getPasswordAuthentication() {
                                return new PasswordAuthentication("rapha95@gmail.com", "080595");
                            }
                        });
                Message message = new MimeMessage(session);
                try {
                    message.setFrom(new InternetAddress("rapha95@gmail.com"));
                    message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("rapha95@gmail.com"));
                    message.setSubject("Recuperação de Senha Ebac");
                    message.setText("Prezado,"
                            + "\n\n Foi solicitado a recuperação de senha para seu CNPJ/CPF."
                            + "\n\n Segue os dados de seu usuário:"
                            + "\n\n Usuário: 123456789"
                            + "\n Senha: 123456"
                            + "\n\n A Ebac agradece o contato.");

                    Transport.send(message);
                } catch (MessagingException e) {
                    e.printStackTrace();
                } finally {
                    Toast.makeText(getBaseContext(), "A recuperação de senha foi enviado. Por favor, verifique seu email", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}
