package com.example.carloscaiza.tarea_04_g02;

public class Person {

    private String usuario;
    private String clave;
    private String nombre;
    private String apellido;
    private String email;
    private String celular;
    private String genero;
    private String fecha_nac;
    /*private String asignaturas [];
    private String becado;*/

    //public Person(String usuario, String clave, String nombre, String apellido, String email, String celular, String genero, String fecha_nac, String[] asignaturas, String becado) {
    public Person(String usuario, String clave, String nombre, String apellido, String email, String celular, String genero, String fecha_nac) {
        this.usuario = usuario;
        this.clave = clave;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.celular = celular;
        this.genero = genero;
        this.fecha_nac = fecha_nac;
        /*this.asignaturas = asignaturas;
        this.becado = becado;*/
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

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getFecha_nac() {
        return fecha_nac;
    }

    public void setFecha_nac(String fecha_nac) {
        this.fecha_nac = fecha_nac;
    }


  /*  public String[] getAsignaturas() {
        return asignaturas;
    }

    public void setAsignaturas(String[] asignaturas) {
        this.asignaturas = asignaturas;
    }

    public String getBecado() {
        return becado;
    }

    public void setBecado(String becado) {
        this.becado = becado;
    }*/
}
