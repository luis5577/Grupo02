package com.example.carloscaiza.tarea_04_g02;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Secundario extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info);

        Button btnRegresar = (Button) findViewById(R.id.regresar);
        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //REGRESAR A LA VISTA ANTERIOR
                //onBackPressed();

                //REGRESAR A LA VISTA LOGIN RESET
                Intent i = new Intent(Secundario.this,LoginActivity.class);
                startActivity(i);

                //REGRESAR A LA VISTA ANTERIOR
                // finish();
                //System.exit(0);

                //CERRAR APP
                //finish();
                //Intent intent = new Intent(Intent.ACTION_MAIN);
                //intent.addCategory(Intent.CATEGORY_HOME);
                //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                //startActivity(intent);
            }

        });

    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu){

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        switch (item.getItemId()){
            case R.id.salir:
                Intent i = new Intent(Secundario.this,LoginActivity.class);
                startActivity(i);
                finish();
                Toast.makeText(this,"Salir",Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
        //return super.onOptionsItemSelected(item);
    }


}