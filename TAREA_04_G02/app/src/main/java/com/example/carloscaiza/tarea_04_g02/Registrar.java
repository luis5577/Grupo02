package com.example.carloscaiza.tarea_04_g02;

import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.StringTokenizer;

public class Registrar extends AppCompatActivity {
    private static final String TAG="Registrar";
    private static final String FILE_NAME = "Data.txt";
    private TextView fecha;
    private DatePickerDialog.OnDateSetListener dateListener;
    //---------------------------------------------------------------------------------------------

    EditText usuario,clave,nombre,apellido,email,celular;
    RadioGroup genero;
    String generostring=null;
    EditText fecha_nac;
    TextView tvResults;
    CheckBox asignatura;
    Switch beca;
    Button guardar;

    ArrayList<Person> personas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registrar);

        usuario = findViewById(R.id.usuario);
        clave = findViewById(R.id.clave);
        nombre = findViewById(R.id.nombre);
        apellido = findViewById(R.id.apellido);
        email = findViewById(R.id.email);
        celular = findViewById(R.id.celular);
        genero =  findViewById(R.id.genero);
        fecha_nac = findViewById(R.id.fecha);
        beca =  findViewById(R.id.beca);
        tvResults = findViewById(R.id.tvResults);

        personas = new ArrayList<Person>();
        genero.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId== R.id.rgmasculino){
                    generostring="M";

                }else if(checkedId== R.id.rgfemenino){
                    generostring="F";
                }
            }


        });
        loadData();


        //Desplegar Fecha En un TextView-----------------------------------------------------------
        fecha=findViewById(R.id.fecha);
        fecha.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int ano = cal.get(Calendar.YEAR);
                int mes = cal.get(Calendar.MONTH);
                int dia = cal.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(
                        Registrar.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        dateListener,ano,mes,dia);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        dateListener = new DatePickerDialog.OnDateSetListener(){
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month=month+1;
                Log.d(TAG,dayOfMonth+"/"+month+"/"+year);
                String date =dayOfMonth+"/"+month+"/"+year;
                fecha.setText(date);
            }
        } ;
        //------------------------------------------------------------------------------------------------
    }

    public void btnAdd(View v){

        String user = usuario.getText().toString();
        String pass = clave.getText().toString();
        String name = nombre.getText().toString();
        String surname = apellido.getText().toString();
        String mail = email.getText().toString();
        String phone = celular.getText().toString();
        String generd=generostring;
        String dates=fecha_nac.getText().toString();
        Person person = new Person(user,pass,name,surname,mail,phone,generd,dates);
        personas.add(person);


        setTextToTextView();

    }

    private void setTextToTextView() {
        String text = "";
        for (int i=0; i<personas.size(); i++){
            text = text + personas.get(i).getUsuario() + " " + personas.get(i).getClave() + " " + personas.get(i).getNombre() + " " + personas.get(i).getApellido() + " " + personas.get(i).getEmail() + " " + personas.get(i).getCelular() +"  "+ personas.get(i).getGenero()+"  "+ personas.get(i).getFecha_nac()+"\n";
        }

        tvResults.setText(text);
    }

    public void loadData(){
        personas.clear();

//        File file = getApplicationContext().getFileStreamPath("Data.txt");
        File file = getApplicationContext().getFileStreamPath(FILE_NAME);

        String lineFromFile;

        if (file.exists()){
            try {
                //BufferedReader reader = new BufferedReader(new InputStreamReader(openFileInput("Data.txt")));
                BufferedReader reader = new BufferedReader(new InputStreamReader(openFileInput(FILE_NAME)));

                while ((lineFromFile = reader.readLine()) != null){
                    StringTokenizer tokens = new StringTokenizer(lineFromFile,",");
                    Person person = new Person(tokens.nextToken(), tokens.nextToken(), tokens.nextToken(), tokens.nextToken(), tokens.nextToken(), tokens.nextToken(), tokens.nextToken(),tokens.nextToken());
                    personas.add(person);
                }
                reader.close();

                setTextToTextView();

            }catch (IOException e){
                Toast.makeText(Registrar.this,e.getMessage(),Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void btnGuardarData(View v){

        try {
            FileOutputStream file = openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
            OutputStreamWriter outputFile = new OutputStreamWriter(file);
            for (int i=0; i<personas.size();i++){
                outputFile.write(personas.get(i).getUsuario() + "," + personas.get(i).getClave() + "," + personas.get(i).getNombre() + "," + personas.get(i).getApellido() + "," + personas.get(i).getEmail() + "," + personas.get(i).getCelular() + "\n");
            }
            outputFile.flush();
            outputFile.close();
            Toast.makeText(Registrar.this,"Guardado correctamente" + getFilesDir() + "/" + FILE_NAME, Toast.LENGTH_LONG).show();
        }catch(FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
      /*  finally {
            if (outputFile != null){
                try {
                    outputFile.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }*/


      /*  try {
            FileOutputStream file = openFileOutput("Data.txt",MODE_PRIVATE);
            OutputStreamWriter outputFile = new OutputStreamWriter(file);

            for (int i=0; i<personas.size();i++){
                outputFile.write(personas.get(i).getUsuario() + "," + personas.get(i).getClave() + "," + personas.get(i).getNombre() + "," + personas.get(i).getApellido() + "," + personas.get(i).getEmail() + "," + personas.get(i).getCelular() + "\n");
            }
            outputFile.flush();
            outputFile.close();
            Toast.makeText(Registrar.this,"Guardado correctamente", Toast.LENGTH_SHORT).show();
        }
        catch (IOException e){
            Toast.makeText(Registrar.this,e.getMessage(),Toast.LENGTH_SHORT).show();
        }*/


    }

}
