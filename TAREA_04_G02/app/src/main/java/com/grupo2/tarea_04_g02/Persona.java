package com.grupo2.tarea_04_g02;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import static java.lang.Boolean.TRUE;

public class Persona implements Serializable  {
    private String usuario;
    private String clave;
    private String nombre;
    private String apellido;
    private String email;
    private Integer celular;
    private Boolean beca;
    private Integer genero;
    private Date nacimiento;
    private List<String> asignaturas;

    public Persona(){
    }

    public Persona(String usuario, String clave, String nombre, String apellido, String email, Integer celular, Boolean beca, Integer genero, List<String> asignaturas, Date nacimiento) {
        this.usuario = usuario;
        this.clave = clave;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.celular = celular;
        this.genero = genero;
        this.nacimiento = nacimiento;
        this.beca = beca;
        this.asignaturas = asignaturas;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getCelular() {
        return celular;
    }

    public void setCelular(Integer celular) {
        this.celular = celular;
    }

    public Boolean getBeca() {
        return beca;
    }

    public void setBeca(Boolean beca) {
        this.beca = beca;
    }

    public Integer getGenero() {
        return genero;
    }

    public void setGenero(Integer genero) {
        this.genero = genero;
    }

    public List<String> getAsignaturas() {
        return asignaturas;
    }

    public void setAsignaturas(List<String> asignaturas) {
        this.asignaturas = asignaturas;
    }

    public Date getNacimiento() {
        return nacimiento;
    }

    public void setNacimiento(Date nacimiento) {
        this.nacimiento = nacimiento;
    }

    @Override
    public String toString() {
        return "Usuario=" + usuario +
               // ", Clave=" + clave+
                ", Nombre=" + nombre +
                ", Apellido=" + apellido +
                ", Email=" + email +
               ", Celular=" + celular +
               ", Genero=" + genero +
                ", Materias=" + asignaturas +
                ", Fecha=" + nacimiento +
                ", Beca=" + beca ;
    }
}
