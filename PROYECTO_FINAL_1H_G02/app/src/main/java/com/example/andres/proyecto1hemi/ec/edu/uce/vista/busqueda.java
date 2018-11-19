package com.example.andres.proyecto1hemi.ec.edu.uce.vista;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;


import com.example.andres.proyecto1hemi.R;
import com.example.andres.proyecto1hemi.ec.edu.uce.controladores.Busqueda;
import com.example.andres.proyecto1hemi.ec.edu.uce.modelo.Vehiculo;
import com.example.andres.proyecto1hemi.ec.edu.uce.modelo.Vehiculo.*;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class busqueda extends AppCompatActivity {
String busqueda1;
ArrayList<Vehiculo> estado1 = new ArrayList<>(100);

    EditText buscar;
  EditText Marca, Placa, Costo;
    Switch Matriculado;
    RadioButton Blanco, Negro, Otro;
    RadioGroup color;
    String ncolor;
    Busqueda busc= new Busqueda();
    Vehiculo vehiculos= new Vehiculo();

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private Spinner dia;
    private Spinner mes;
    private Spinner ano;
    Date fecha;

    private Date fechaAux;

    ArrayList<Vehiculo> items= new ArrayList<Vehiculo>(100);

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menubusqueda, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId()== R.id.item1) {

                busqueda1= buscar.getText().toString();

            int indiceProducto = busc.buscarEnArray(items, busqueda1);
            if (indiceProducto >= 0) {
                // En este caso si existe se imprime por pantalla y fin
                String seleccion=items.get(indiceProducto).getColor();
                String seleccion2=items.get(indiceProducto).getMatriculado().toString();
                Placa.setFocusable(false);
                Placa.setText(items.get(indiceProducto).getPlaca());
                Marca.setText(items.get(indiceProducto).getMarca());
                Costo.setText(items.get(indiceProducto).getCosto().toString());
                dia.setSelection(items.get(indiceProducto).getFecFabricacion().getDate()-1);
                mes.setSelection(items.get(indiceProducto).getFecFabricacion().getMonth());
               // ano.setSelection(items.get(indiceProducto).getFecFabricacion().getYear()+1900);
                //  Fecha.setText(items.get(indiceProducto).getFecFabricacion().toString());
                if(seleccion.equals("Blanco") ){
                    Blanco.setChecked(TRUE);
                }else if(seleccion.equals("Negro")){
                    Negro.setChecked(TRUE);
                }else if(seleccion.equals("Otro")){
                    Otro.setChecked(TRUE);
                }

                if(seleccion2.equals("true")){
                    Matriculado.setChecked(true);
                }else {
                    Matriculado.setChecked(false);
                }
            }
            else {
                //mostrar.setText("El producto no existe en el array!");
                Placa.setFocusable(false);
                Placa.setText("El Vehiculo");
              //  Marca.setFocusable(false);
                Marca.setText("no existe !");
                Costo.setText(null);
                Blanco.setChecked(FALSE);
                Negro.setChecked(FALSE);
                Otro.setChecked(FALSE);
                Matriculado.setChecked(false);
            }


        }else if(item.getItemId()==R.id.item2) {


            try {

                String valideplaca = "[A-Z]{3}" +
                        "\\-" +
                        "[0-9]{4}";
                String placa = Placa.getText().toString();
                Matcher matcher = Pattern.compile(valideplaca).matcher(placa);

                String validecosto = "[0-9]{1,7}"+"\\."+"[0-9]{2}";
                String costo = Costo.getText().toString();
                Matcher matcher2 = Pattern.compile(validecosto).matcher(costo);

                if (Placa.getText().toString().isEmpty())
                //EditText contiene algún caracter!
                {
                    Toast.makeText(this, "Campo Placa Vacio", Toast.LENGTH_LONG).show();
                } else {
                    if (Marca.getText().toString().isEmpty())
                    //EditText contiene algún caracter!
                    {
                        Toast.makeText(this, "Campo Marca Vacio", Toast.LENGTH_LONG).show();
                    } else {
                        if (Costo.getText().toString().isEmpty())
                        //EditText contiene algún caracter!
                        {
                            Toast.makeText(this, "Campo Costo Vacio", Toast.LENGTH_LONG).show();
                        } else {
                            if (color.getCheckedRadioButtonId() == -1) {
                                Toast.makeText(this, "Campo Color Vacio", Toast.LENGTH_LONG).show();
                            }else{
                                if (matcher.matches()  ) {

                                    if(matcher2.matches()){

                                        Vehiculo vehiculo = new Vehiculo();
                                        busqueda1=buscar.getText().toString();
                                        int indiceProducto = busc.buscarEnArray(items, busqueda1);
                                        int opcion=color.getCheckedRadioButtonId();
                                        if(opcion == Blanco.getId() ){
                                            ncolor="Blanco";
                                        }else if(opcion == Negro.getId()){
                                            ncolor="Negro";
                                        }else if(opcion == Otro.getId()){
                                            ncolor="Otro";
                                        }
                                        Boolean estado;
                                        if(Matriculado.isChecked()){
                                            estado=true;
                                        }else{
                                            estado=false;
                                        }

                                        if (ano.getSelectedItem().toString().equals("1950") == true && mes.getSelectedItem().toString().equals("1") && dia.getSelectedItem().toString().equals("1")) {
                                            vehiculo.setFecFabricacion(fechaAux);
                                        } else {
                                            Calendar fechaAux = Calendar.getInstance();
                                            fechaAux.set(Integer.parseInt(ano.getSelectedItem().toString()),
                                                    Integer.parseInt(mes.getSelectedItem().toString())-1,
                                                    Integer.parseInt(dia.getSelectedItem().toString()));
                                            fecha = new Date();
                                            fecha.setTime(fechaAux.getTimeInMillis());

                                        }
                                        SharedPreferences.Editor editor = getSharedPreferences("Examen", MODE_PRIVATE).edit();
                                        Set<String> set = new HashSet<String>();
                                        for (int i = 0; i < items.size(); i++) {
                                            if(i== indiceProducto){
                                                items.set(i,(new Vehiculo(Placa.getText().toString(),Marca.getText().toString(),fecha,Double.parseDouble(Costo.getText().toString()),estado,ncolor)));
                                                set.add(items.get(i).getJSONObject().toString());
                                            }else {
                                                set.add(items.get(i).getJSONObject().toString());
                                            }
                                        }
                                        editor.putStringSet("Examen", set);
                                        editor.commit();
                                        finish();
                                        Intent intent = new Intent(busqueda.this, listado.class);
                                        startActivity(intent);

                                    }else{
                                        Toast.makeText(getApplicationContext(), "Costo mal ingresado (xxx.xx)", Toast.LENGTH_LONG).show();
                                    }
                                } else {
                                    Toast.makeText(getApplicationContext(), "Formato Incorrecto (ABC-1234)", Toast.LENGTH_LONG).show();
                                }
                            }
                        }
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }

        }else if(item.getItemId()==R.id.item3) {

                busqueda1=buscar.getText().toString();

            int indiceProducto = busc.buscarEnArray(items, busqueda1);
            SharedPreferences.Editor editor = getSharedPreferences("Examen", MODE_PRIVATE).edit();
            Set<String> set = new HashSet<String>();
            for (int i = 0; i < items.size(); i++) {
                if(i== indiceProducto){
                    set.remove(items.get(i).getJSONObject().toString());
                }else{
                    set.add(items.get(i).getJSONObject().toString());
                }
            }
            editor.putStringSet("Examen", set);
            editor.commit();
            finish();
            Intent intent = new Intent(busqueda.this, listado.class);
            startActivity(intent);
        }else if(item.getItemId()==R.id.item4) {
            finish();
            Intent intent = new Intent(busqueda.this, listado.class);
            startActivity(intent);
        }else if (item.getItemId() == R.id.item5){

                finish();
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busqueda);
        items = loadFromStorage();
        buscar = (EditText) findViewById(R.id.txtbuscar);
        Placa = (EditText) findViewById(R.id.txtPlaca2);
        Marca = (EditText) findViewById(R.id.txtMarca2);
        Costo = (EditText) findViewById(R.id.txtCosto2);
        Blanco = (RadioButton) findViewById(R.id.rdblanco2);
        Negro = (RadioButton) findViewById(R.id.rdnegro2);
        Otro = (RadioButton) findViewById(R.id.rdotro2);
        Matriculado = (Switch) findViewById(R.id.swmatricula2);
        color = (RadioGroup) findViewById(R.id.rdcolor2);
        dia = (Spinner) findViewById(R.id.spinner_dia);
        mes = (Spinner) findViewById(R.id.spinner_mes);
        ano = (Spinner) findViewById(R.id.spinner_ano);
        cargarFechas();


    }



    public void cargarFechas() {
        String[] opcionesDia = new String[31];
        String[] opcionesMes = new String[12];
        String[] opcionesAno = new String[69];
        for (int i = 1; i <= 31; i++) {
            opcionesDia[i - 1] = String.valueOf(i);
        }
        for (int i = 1; i <= 12; i++) {
            opcionesMes[i - 1] = String.valueOf(i);
        }
        for (int i = 1950; i <= 2018; i++) {
            opcionesAno[i - 1950] = String.valueOf(i);
        }

        ArrayAdapter<String> adapter;
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, opcionesDia);
        this.dia.setAdapter(adapter);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, opcionesMes);
        this.mes.setAdapter(adapter);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, opcionesAno);
        this.ano.setAdapter(adapter);
    }


    public ArrayList<Vehiculo> loadFromStorage()  {
        SharedPreferences mPrefs = getSharedPreferences("Examen", Context.MODE_PRIVATE);
        ArrayList<Vehiculo> items2 = new ArrayList<Vehiculo>(100);
        Set<String> set = mPrefs.getStringSet("Examen", null);
        if (set != null) {
            for (String s : set) {
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    String placa = jsonObject.getString("placa");
                    String marca = jsonObject.getString("marca");
                    String fechas = jsonObject.getString("fecfabricacion");
                    Date posi= sdf.parse(fechas);
                    Double costo = jsonObject.getDouble("costo");
                    Boolean matriculado = jsonObject.getBoolean("matriculado");
                    String color = jsonObject.getString("color");
                    Vehiculo myclass = new Vehiculo(placa, marca,posi, costo, matriculado, color);

                    items2.add(myclass);

                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
        return items2;
    }
}
