package com.example.andres.proyecto1hemi.ec.edu.uce.controladores;

import com.example.andres.proyecto1hemi.ec.edu.uce.modelo.Vehiculo;

import java.util.ArrayList;
import java.util.Date;

public class Busqueda {

    public static int buscarEnArray(ArrayList<Vehiculo> arrayDondeBuscar, String nobreParametroBusqueda){

        int ubicacionEnArray = arrayDondeBuscar.indexOf(new Vehiculo(nobreParametroBusqueda,null,null,0.0,null,null));

        return ubicacionEnArray;
    }

}
