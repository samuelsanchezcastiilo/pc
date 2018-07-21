package com.apps.jaxpers.vaymer.Model;

public class ciudades {

    private String ciudad;
    private String semana1;
    private String semana2;
    private String dia;
    private  String[] digitos ;

    public ciudades(String ciudad, String semana1, String semana2, String dia, String[] digitos) {
        this.ciudad = ciudad;
        this.semana1 = semana1;
        this.semana2 = semana2;
        this.dia = dia;
        this.digitos = digitos;

    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getSemana1() {
        return semana1;
    }

    public void setSemana1(String semana1) {
        this.semana1 = semana1;
    }

    public String getSemana2() {
        return semana2;
    }

    public void setSemana2(String semana2) {
        this.semana2 = semana2;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String[] getDigitos() {
        return digitos;
    }

    public void setDigitos(String[] digitos) {
        this.digitos = digitos;
    }
}
