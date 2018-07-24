package com.apps.jaxpers.vaymer.Model;

public class RestricionesCiudades {

    private  String dias;
    private String id;

    public RestricionesCiudades(String dias, String id) {
        this.dias = dias;
        this.id = id;
    }

    public RestricionesCiudades()
    {
    }

    public String getDias() {
        return dias;
    }

    public void setDias(String dias) {
        this.dias = dias;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}