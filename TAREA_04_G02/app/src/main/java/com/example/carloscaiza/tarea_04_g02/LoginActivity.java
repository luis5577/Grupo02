package com.example.carloscaiza.tarea_04_g02;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {


    private TextView registrar1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final Button btn = (Button) findViewById(R.id.ingresar);
        registrar1 = findViewById(R.id.registro);
        registrar1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent registrar = new Intent(LoginActivity.this, Registrar.class);
                LoginActivity.this.startActivity(registrar);
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = ((EditText) findViewById(R.id.txtusuario)).getText().toString();
                String pass = ((EditText) findViewById(R.id.txtpassword)).getText().toString();

                if((user.equals("carlos") && pass.equals("caiza")) || (user.equals("marcos") && pass.equals("alcivar"))){

                    Intent nuevoform = new Intent(LoginActivity.this,Secundario.class);
                    startActivity(nuevoform);

                }else {
                    Toast.makeText(getApplicationContext(),"Usuario incorrecto", Toast.LENGTH_SHORT).show();
                }

            }

        });

        Button btnSalir = (Button) findViewById(R.id.salir);

        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //CERRAR APP
                finish();
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }

        });

    }



    // @Override
    //public boolean onCreateOptionsMenu(Menu menu){
    //   getMenuInflater().inflate(R.menu.Login, menu);
    //   return true;
    //}

    //@Override
    //public boolean onOptionsItemSelected(MenuItem item){
    //  int id = item.getItemId();
    //if (id==R.id.action_settings){
    //  return true;
    //}
    //return super.onOptionsItemSelected(item);
    //}

}
