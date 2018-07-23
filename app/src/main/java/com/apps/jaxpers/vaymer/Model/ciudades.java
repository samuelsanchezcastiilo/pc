package com.apps.jaxpers.vaymer.Model;

public class ciudades {

    private String id ;
    private String nombre;
    private String informacion;

    public ciudades(String id, String nombre, String informacion) {
        this.id = id;
        this.nombre = nombre;
        this.informacion = informacion;
    }

    public ciudades() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getInformacion() {
        return informacion;
    }

    public void setInformacion(String informacion) {
        this.informacion = informacion;
    }
}