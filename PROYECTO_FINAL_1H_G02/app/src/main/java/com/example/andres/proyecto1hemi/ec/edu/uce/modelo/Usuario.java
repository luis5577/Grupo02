package com.example.andres.proyecto1hemi.ec.edu.uce.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Usuario implements Serializable  {
    private String usuario;
    private String clave;


    public Usuario(){
    }

    public Usuario(String usuario, String clave) {
        this.usuario = usuario;
        this.clave = clave;

    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }



    @Override
    public String toString() {
        return "Usuario=" + usuario ;
               // ", Clave=" + clave+

    }
}
