package com.example.andres.proyecto1hemi;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.andres.proyecto1hemi.ec.edu.uce.controladores.IOStream;
import com.example.andres.proyecto1hemi.ec.edu.uce.modelo.Usuario;
import com.example.andres.proyecto1hemi.ec.edu.uce.vista.listado;
import com.example.andres.proyecto1hemi.ec.edu.uce.vista.registro;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private EditText Name;
    private EditText Password;

    private Button Login;
    private Button Cerrar;
    private Button Registro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Name = (EditText) findViewById(R.id.etName);
        Password = (EditText) findViewById(R.id.etPassword);

        Login = (Button) findViewById(R.id.btnlogin);
        Cerrar = (Button) findViewById(R.id.btncerrar);
        Registro = (Button) findViewById(R.id.btnregistrar);

        Registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(MainActivity.this, registro.class);
                startActivity(intent);

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

            }
        });

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    irListado(Name.getText().toString(), Password.getText().toString());
                }catch(Exception e){
                    e.printStackTrace();
                }

            }
        });


    }

    private void irListado(String user, String pass) throws IOException, ClassNotFoundException {
        System.out.println("Usuario: "+ user +"- clave: " + pass);
        List<Usuario> lista = new ArrayList<>();
        IOStream io = new IOStream();
        lista = io.listarTodos();
        boolean bandera=false;
        for (Usuario per: lista) {
            System.out.println("Usuario: "+per.getUsuario());
            System.out.println("Clave: "+per.getClave());

            if(user.equals(per.getUsuario()) && pass.equals(per.getClave())) {
                bandera=true;
            }
        }
        if(bandera){
            Intent intent = new Intent(MainActivity.this, listado.class);
            startActivity(intent);
        }else{
            Toast.makeText(this, "Usuario y Clave no válido", Toast.LENGTH_SHORT).show();
        }


    }
}




