package com.example.andres.proyecto1hemi.ec.edu.uce.vista;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;

import com.example.andres.proyecto1hemi.MainActivity;
import com.example.andres.proyecto1hemi.R;
import com.example.andres.proyecto1hemi.ec.edu.uce.modelo.Usuario;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Boolean.FALSE;


public class registro extends AppCompatActivity {


    private EditText usuario;
    private EditText contraseña;
    private Button regist;
    private final String ARCHIVO = "data.txt";

    List<Usuario> lista = new ArrayList<>();
    File dataFile = new File(Environment.getExternalStorageDirectory(), ARCHIVO);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        usuario = (EditText) findViewById(R.id.txtusuario);
        contraseña = (EditText) findViewById(R.id.txtcontraseña);
        regist = (Button) findViewById(R.id.btnregistro);
        regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrar();

            }
        });

    }

    private void registrar(){
        Integer sexo =0;

        try {




            if (usuario.getText().toString().isEmpty())
            //EditText contiene algún caracter!
            {
                Toast.makeText(this, "Campo Usuario Vacio", Toast.LENGTH_LONG).show();
            } else {
                if (contraseña.getText().toString().isEmpty())
                //EditText contiene algún caracter!
                {
                    Toast.makeText(this, "Campo Contraseña Vacio", Toast.LENGTH_LONG).show();
                } else {
                    FileOutputStream out = new FileOutputStream(dataFile, true);
                    ObjectOutputStream ost = new ObjectOutputStream(out);
                    ost.writeObject(new Usuario(usuario.getText().toString(), contraseña.getText().toString()));
                    ost.close();
                    Intent intent = new Intent(registro.this, MainActivity.class);
                    startActivity(intent);
                }}

        }catch (Exception e){
            e.printStackTrace();
        }

}}
