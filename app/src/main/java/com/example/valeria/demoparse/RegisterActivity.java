package com.example.valeria.demoparse;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import org.apache.commons.lang3.text.WordUtils;

public class RegisterActivity extends AppCompatActivity {

    protected EditText mUsername;
    protected EditText mMail;
    protected EditText mPassword;
    protected Button mLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        // Enable Local Datastore.
        //Parse.enableLocalDatastore(this);
        //Parse.initialize(this, "DnvKVjOM3QtWIi3USoplZP4D3Y62LDCaWnf0hTR4", "Y4B18EFxNqbTIqPZ6N1AYGP3aWeKURZy0QGQFy5Q");

        //inicializar
        mUsername = (EditText)findViewById(R.id.nombre);
        mMail = (EditText)findViewById(R.id.mail);
        mPassword = (EditText)findViewById(R.id.pass);
        mLogin = (Button)findViewById(R.id.login);

        //listen boton
        mLogin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                final String usuario = mUsername.getText().toString().trim();
                String password = mPassword.getText().toString().trim();
                String mail = mMail.getText().toString().trim();

                ParseUser user = new ParseUser();
                user.setUsername(usuario);
                user.setPassword(password);
                user.setEmail(mail);

                user.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if(e == null)
                        {
                            Toast.makeText(RegisterActivity.this, "¡Bienvenido " + usuario + "!", Toast.LENGTH_LONG).show();
                            //Take to another page.
                            Intent takeUserHome = new Intent(RegisterActivity.this, MainActivity.class);
                            startActivity(takeUserHome);
                        }
                        else {
                            final AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                            builder.setTitle("¡Error!");
                            builder.setMessage(WordUtils.capitalize(e.getMessage()));
                            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                }
                            });

                            AlertDialog alert = builder.create();
                            alert.show();
                        }
                    }
                });
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_register, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
