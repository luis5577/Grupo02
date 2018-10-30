package com.grupo2.tarea_04_g02;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.*;
import com.grupo2.tarea_04_g02.utils.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private EditText Name;
    private EditText Password;
    private TextView Info;
    private Button Login;
    private Button Cerrar;
    private Button Registro;
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
        Registro = (Button) findViewById(R.id.btnregistrar);
        Info.setText("Número de intentos restantes: 5");

        Registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(MainActivity.this, Registro.class);
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
        List<Persona> lista = new ArrayList<>();
        IOStream io = new IOStream();
        lista = io.listarTodos();
        boolean bandera=false;
        for (Persona per: lista) {
            System.out.println("Usuario: "+per.getUsuario());
            System.out.println("Clave: "+per.getClave());
            System.out.println("Nombre: "+per.getNombre());
            System.out.println("Celular: "+per.getCelular());
           //System.out.println("Fecha: "+per.getNacimiento());
            System.out.println("Genero: "+per.getGenero());
            System.out.println("Beca: "+per.getBeca());
            System.out.println("Materias Asignadas: "+per.getAsignaturas().size());
            if(user.equals(per.getUsuario()) && pass.equals(per.getClave())) {
                bandera=true;
            }
        }
        if(bandera){
            Intent intent = new Intent(MainActivity.this, Usuarios.class);
            startActivity(intent);
        }else{
            Toast.makeText(this, "Usuario y Clave no válido", Toast.LENGTH_SHORT).show();
        }


    }


}


