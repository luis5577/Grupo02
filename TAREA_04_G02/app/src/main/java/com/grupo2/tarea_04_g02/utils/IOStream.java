package com.grupo2.tarea_04_g02.utils;

import android.os.Environment;

import com.grupo2.tarea_04_g02.Persona;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

public class IOStream {

    private final String ARCHIVO = "data.obj";
    File dataFile = new File(Environment.getExternalStorageDirectory(), ARCHIVO);
    public List<Persona> listarTodos() throws ClassNotFoundException, IOException{
        ObjectInputStream ois = null;
        List<Persona> lista = new ArrayList<>();
        try{
            FileInputStream fis = new FileInputStream(dataFile);
            ois = new ObjectInputStream(fis);
            Object aux = ois.readObject();
            while(true){

                Persona per = new Persona();
                per = (Persona) aux;
                lista.add(per);
                ObjectInputStream oin = new ObjectInputStream(fis);
                aux = oin.readObject();
            }

        } catch (IOException io){
            System.out.println("\n************FIN**************");
        } finally {
            ois.close();

        }
        return lista;
    }
}
