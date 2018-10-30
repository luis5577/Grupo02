package com.grupo2.tarea_04_g02;

import android.content.Intent;
import android.os.Environment;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Calendar;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import android.app.DatePickerDialog;
import java.util.ArrayList;
import java.util.List;
import android.text.InputType;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class Registro extends AppCompatActivity {
    private EditText nombre;
    private EditText apellido;
    private EditText usuario;
    private EditText contraseña;
    private EditText email;
    private EditText celular;
    private Button regist;
    private RadioGroup genero;
    private RadioButton Masculino;
    private RadioButton Femenino;
    private RadioButton SE;
    private EditText Fecha;
    private CheckBox Mat1;
    private CheckBox Mat2;
    private CheckBox Mat3;
    private CheckBox Mat4;
    private CheckBox Mat5;
    private Switch beca;
    DatePickerDialog picker;
    final Calendar cldr = Calendar.getInstance();
    Date fnac= new Date();
    private final String ARCHIVO = "data.obj";

    List<Persona> lista = new ArrayList<>();
    File dataFile = new File(Environment.getExternalStorageDirectory(), ARCHIVO);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        usuario = (EditText) findViewById(R.id.btnusuario);
        contraseña = (EditText) findViewById(R.id.btnclave);
        nombre = (EditText) findViewById(R.id.btnnombre);
        apellido = (EditText) findViewById(R.id.btnapellido);
        email = (EditText) findViewById(R.id.btncorreo);
        celular = (EditText) findViewById(R.id.txtcelular);
        regist = (Button) findViewById(R.id.btnregistro);
        beca=(Switch) findViewById(R.id.swbeca);
        Masculino = (RadioButton) findViewById(R.id.rhombre);
        Femenino = (RadioButton) findViewById(R.id.rmujer);
        genero = (RadioGroup) findViewById(R.id.rgenero);
        SE=(RadioButton) findViewById(R.id.rvacio);
        Mat1=(CheckBox) findViewById(R.id.Mdatos);
        Mat2=(CheckBox) findViewById(R.id.Mweb);
        Mat3=(CheckBox) findViewById(R.id.MInformacion);
        Mat4=(CheckBox) findViewById(R.id.MOptativa3);
        Mat5=(CheckBox) findViewById(R.id.Mtitulacion);
        Fecha = (EditText) findViewById(R.id.txtfecha);

        Fecha.setInputType(InputType.TYPE_NULL);
        regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrar();

            }
        });

        Fecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fecha();
            }
        });

    }


private void fecha(){
    int day = cldr.get(Calendar.DAY_OF_MONTH);
    int month = cldr.get(Calendar.MONTH);
    int year = cldr.get(Calendar.YEAR);

fnac.setTime(cldr.getTimeInMillis());
    picker = new DatePickerDialog(Registro.this,
            new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    Fecha.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                }
            }, year, month, day);
    picker.show();
}
    private void registrar(){
        Integer sexo =0;

        try {

            String validemail = "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                    "\\@" +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                    "(" +
                    "\\." +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                    ")+";
            String correo = email.getText().toString();
            Matcher matcher = Pattern.compile(validemail).matcher(correo);

            String validecell = "[09]{2}"+"[a-zA-Z0-9]{8}";
            String celu = celular.getText().toString();
            Matcher matcher2 = Pattern.compile(validecell).matcher(celu);


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
                    if (nombre.getText().toString().isEmpty())
                    //EditText contiene algún caracter!
                    {
                        Toast.makeText(this, "Campo Nombre Vacio", Toast.LENGTH_LONG).show();
                    } else {
                        if (apellido.getText().toString().isEmpty())
                        //EditText contiene algún caracter!
                        {
                            Toast.makeText(this, "Campo Apellido Vacio", Toast.LENGTH_LONG).show();
                        } else {
                            if (email.getText().toString().isEmpty())
                            //EditText contiene algún caracter!
                            {
                                Toast.makeText(this, "Campo Email Vacio", Toast.LENGTH_LONG).show();
                            } else {
                                if (celular.getText().toString().isEmpty())
                                //EditText contiene algún caracter!
                                {
                                    Toast.makeText(this, "Campo celular Vacio", Toast.LENGTH_LONG).show();
                                } else {
                                    if ((Mat1.isChecked()== FALSE) && (Mat2.isChecked()== FALSE)&&(Mat3.isChecked()== FALSE) && (Mat4.isChecked()== FALSE)&&(Mat5.isChecked()== FALSE)) {
                                    Toast.makeText(this, "Campo Materia Vacio", Toast.LENGTH_LONG).show();
                                }else{
                                        if (genero.getCheckedRadioButtonId() == -1) {
                                            Toast.makeText(this, "Campo Genero Vacio", Toast.LENGTH_LONG).show();

                                        }else{
                                            if (matcher.matches()  ) {

                                                if(matcher2.matches()){
                                                    List<String> materias = new ArrayList<>();
                                                    if(Mat1.isChecked() ){
                                                        materias.add("Analisis de Datos");
                                                    }
                                                    if(Mat2.isChecked()){
                                                        materias.add("Programacion Web");
                                                    }
                                                    if(Mat3.isChecked()){
                                                        materias.add("Sistemas de Información");
                                                    }
                                                    if(Mat4.isChecked()){
                                                        materias.add("Optativa 3");
                                                    }
                                                    if(Mat5.isChecked()){
                                                        materias.add("Titulación");
                                                    }

                                                    Boolean estado;
                                                    if(beca.isChecked()){
                                                        estado=true;
                                                    }else{
                                                        estado=false;
                                                    }
                                                    int opcion=genero.getCheckedRadioButtonId();
                                                    if(opcion == Masculino.getId() ){
                                                        sexo=1;
                                                    }else if(opcion == Femenino.getId()){
                                                        sexo=2;
                                                    }else if(opcion == SE.getId()){
                                                        sexo=3;
                                                    }
                                                    FileOutputStream out = new FileOutputStream(dataFile, true);
                                                    ObjectOutputStream ost = new ObjectOutputStream(out);
                                                    ost.writeObject(new Persona(usuario.getText().toString(), contraseña.getText().toString(),
                                                            nombre.getText().toString(), apellido.getText().toString(), email.getText().toString(), Integer.parseInt(celular.getText().toString()),estado,sexo,materias,fnac));
                                                    ost.close();
                                                    Intent intent = new Intent(Registro.this, MainActivity.class);
                                                    startActivity(intent);
                                                }else{
                                                    Toast.makeText(getApplicationContext(), "Celular Invalido", Toast.LENGTH_LONG).show();
                                                }
                                            } else {
                                                Toast.makeText(getApplicationContext(), "Correo Invalido", Toast.LENGTH_LONG).show();
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
