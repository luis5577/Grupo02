package com.example.andres.proyecto1hemi.ec.edu.uce.modelo;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

public class Vehiculo implements Serializable {

private String  placa;
private String marca;
private Date fecFabricacion;
private Double costo;
private Boolean matriculado;
private String color;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");



public  Vehiculo(){
}



public Vehiculo(String placa, String marca,Date fecFabricacion, Double costo, Boolean matriculado, String color){
    this.placa=placa;
    this.marca=marca;
    this.fecFabricacion=fecFabricacion;
    this.costo=costo;
    this.matriculado=matriculado;
    this.color=color;

}



    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Double getCosto() {
        return costo;
    }

    public void setCosto(Double costo) {
        this.costo = costo;
    }

    public Boolean getMatriculado() {
        return matriculado;
    }

    public void setMatriculado(Boolean matriculado) {
        this.matriculado = matriculado;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Date getFecFabricacion() {
        return fecFabricacion;
    }

    public void setFecFabricacion (Date fecFabricacion) {
        this.fecFabricacion = fecFabricacion;
    }

    public boolean equals(Object objetoAComparar){
        boolean isEqual = false;

        /*
         * Se comprueba en primer lugar que el objeto que se ha recivido es una instancia de la clase Producto
         * si lo es entonces se procedera hacer el casting a un objeto de la clase Producto. Se debe hacer esto
         * ya que no se puede comparar un OBJECT con un PRODUCTO.
         */
        if(objetoAComparar instanceof Vehiculo){
            //Casting de objeto --> Producto. Con esto ya podemos comparar un Producto con otro Producto
            Vehiculo productoComprar = (Vehiculo)objetoAComparar;

            /* Recogemos el nombre del producto que se ha pasado por parametro para comprarlo con el objeto
             * con el que se ha hecho la llamada.
             * En el caso de que tengan el mismo nombre entonces camiaremos la variable de retorno a TRUE;
             */
            if(productoComprar.getPlaca().equals(this.placa)){
                //Si se llega hasta aqui quiere decir que los objetos tienen el mismo nombre
                isEqual = true;
            }
        }
        /**
         * En el caso de no ser una instancia de la clase Producto la variable isEqual permanece en FALSE
         * y por tanto no es igual o no pertenece a la clase.
         */
        return isEqual;
    }


  public   static  class MyComparator implements Comparator<Vehiculo> {

        public int compare(Vehiculo o1, Vehiculo o2) {
            if (o1.getPlaca().compareTo(o2.getPlaca())<0) {
                return -1;
            } else if (o1.getPlaca().compareTo(o2.getPlaca())>0) {
                return 1;
            }
            return 0;
        }}



    @Override
    public String toString() {
        SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd");
        String str;
        return "Placa=" + placa +
                ", Marca=" + marca +
                ", FecFabricacion=" + formateador.format(fecFabricacion) +
                ", Costo=" + costo +
                ", Matriculado=" + matriculado +
                ", Color=" + color  ;
    }

    public int compararplaca(Vehiculo arreglo) {
        int resultado;
        if (this.placa.compareTo(arreglo.getPlaca()) < 0) {
            resultado = -1;
        } else if (this.placa.compareTo(arreglo.getPlaca()) > 0) {
            resultado = 1;
        } else {
            resultado = 0;
        }
        return resultado;

    }

    public JSONObject getJSONObject() {

        String str;
        JSONObject obj = new JSONObject();
        try {
            SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd");
            obj.put("placa", this.getPlaca());
            obj.put("marca", this.getMarca());
            obj.put("fecfabricacion",formateador.format(this.getFecFabricacion()));
            obj.put("costo", this.getCosto());
            obj.put("matriculado", this.getMatriculado());
            obj.put("color", this.getColor());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return obj;
    }



}
