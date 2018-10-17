package com.example.andres.tarea_03_g02;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText Name;
    private EditText Password;
    private TextView Info;
    private Button Login;
    private Button Cerrar;

    private int counter=5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Name = (EditText) findViewById(R.id.etName);
        Password = (EditText) findViewById(R.id.etPassword);
        Info = (TextView) findViewById(R.id.tvinfo);
        Login = (Button) findViewById(R.id.btnlogin);
        Cerrar = (Button) findViewById(R.id.btncerrar);

        Info.setText("Número de intentos restantes: 5");
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(Name.getText().toString(), Password.getText().toString());
            }
        });

        Cerrar.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        }
    });

    }

    private void validate(String userName, String userPassword){
        if((userName.equals("Marcos"))&&(userPassword.equals("Alcivar"))){
            Intent intent = new Intent(MainActivity.this, Marcos.class);
            startActivity(intent);
        }  if((userName.equals("Carlos"))&&(userPassword.equals("Caiza"))){
            Intent intent = new Intent(MainActivity.this, Marcos.class);
            startActivity(intent);
        } if((userName.equals("Henry"))&&(userPassword.equals("Cushicondor"))){
            Intent intent = new Intent(MainActivity.this, Marcos.class);
            startActivity(intent);
        } if((userName.equals("Edison"))&&(userPassword.equals("Villagomez"))){
            Intent intent = new Intent(MainActivity.this, Marcos.class);
            startActivity(intent);
        } else  {
            counter--;
            Info.setText("Número de intentos restantes: " + String.valueOf(counter));
            if (counter==0){
                Login.setEnabled(false);
            }
        }
    }
}
