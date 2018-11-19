package com.example.andres.proyecto1hemi.ec.edu.uce.controladores;

import android.os.Environment;

import com.example.andres.proyecto1hemi.ec.edu.uce.modelo.Usuario;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

public class IOStream {

    private final String ARCHIVO = "data.txt";
    File dataFile = new File(Environment.getExternalStorageDirectory(), ARCHIVO);
    public List<Usuario> listarTodos() throws ClassNotFoundException, IOException{
        ObjectInputStream ois = null;
        List<Usuario> lista = new ArrayList<>();
        try{
            FileInputStream fis = new FileInputStream(dataFile);
            ois = new ObjectInputStream(fis);
            Object aux = ois.readObject();
            while(true){

                Usuario per = new Usuario();
                per = (Usuario) aux;
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
