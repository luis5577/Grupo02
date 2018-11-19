package com.example.andres.proyecto1hemi.ec.edu.uce.vista;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.andres.proyecto1hemi.MainActivity;
import com.example.andres.proyecto1hemi.R;
import com.example.andres.proyecto1hemi.ec.edu.uce.modelo.Vehiculo;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class listado extends AppCompatActivity {
    ListView listaV;
    //TextView carros;
    Vehiculo vehiculo= new Vehiculo();
    ArrayList<Vehiculo> items = new ArrayList<Vehiculo>(100);

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    String fecha1="2015-11-13";
    String fecha2="1998-03-05";
    Date f1=sdf.parse(fecha1);
    Date f2=sdf.parse(fecha2);
    public listado() throws ParseException {
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId()== R.id.item1) {
            Intent intent = new Intent(listado.this, editar.class);
            startActivity(intent);
        }else if(item.getItemId()==R.id.item2) {
            Intent intent = new Intent(listado.this, busqueda.class);
            startActivity(intent);
        }else if (item.getItemId() == R.id.item3){
            finish();
            Intent intent = new Intent(listado.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        } else if(item.getItemId()== R.id.item4)
        {
            finish();
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }else if (item.getItemId() == R.id.item5){

            items=loadFromStorage();
            Collections.sort(items,new Vehiculo.MyComparator());
            //////////////////////////////////////////////////////////////////////////////////////////
            //Vehiculo buffer;
            //for (int i = 0; i < items.size(); i++) {

              //  for (int j = 0; j < i; j++) {

                //    if (items.get(i).compararplaca(items.get(j))<0) {
                  //      buffer = items.get(j);
                    //    items.set( j, items.get(i));
                  //      items.set(i,buffer);
                //    }
              //  }
            //}
            //////////////////////////////////////////////////////////////////////////////////////////
            //SharedPreferences.Editor editor = getSharedPreferences("Examen", MODE_PRIVATE).edit();

            //Set<String> set = new HashSet<String>();
            //for (int i = 0; i < items.size(); i++) {
              //  set.add(items.get(i).getJSONObject().toString());
            //}
            //editor.putStringSet("Examen", set);
           // editor.commit();
            ArrayAdapter<Vehiculo> adapterM = new ArrayAdapter<Vehiculo>(this, android.R.layout.simple_expandable_list_item_1, items );
            listaV.setAdapter(adapterM);
          //  carros.setText(items.toString());
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);

        items=loadFromStorage();
        if(items.isEmpty()) {
            items.add(new Vehiculo("XTR-9784", "Audi",f1,79990.0, TRUE, "Negro"));
            items.add(new Vehiculo("CCD-0789", "Honda",f2,15340.0, FALSE, "Blanco"));

        }

       // carros=(TextView) findViewById(R.id.txtcarros);
        listaV = (ListView) findViewById(R.id.listado);
        Collections.sort(items,new Vehiculo.MyComparator());
        SharedPreferences.Editor editor = getSharedPreferences("Examen", MODE_PRIVATE).edit();

        Set<String> set = new HashSet<String>();
        for (int i = 0; i < items.size(); i++) {
            set.add(items.get(i).getJSONObject().toString());
        }



        editor.putStringSet("Examen", set);
        editor.commit();

       ArrayAdapter<Vehiculo> adapterM = new ArrayAdapter<Vehiculo>(this, android.R.layout.simple_expandable_list_item_1, loadFromStorage() );
        listaV.setAdapter(adapterM);
        //carros.setText(set.toString());
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
