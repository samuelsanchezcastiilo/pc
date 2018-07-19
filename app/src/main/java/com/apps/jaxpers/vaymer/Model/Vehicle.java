package com.apps.jaxpers.vaymer.Model;

public class Vehicle {



    private String nameVehicle;
    private int digitoVehicle;
    private String typeVehicle;
    private String classVehicle;

    public Vehicle(String nameVehicle, int digitoVehicle, String typeVehicle, String classVehicle) {
        this.nameVehicle = nameVehicle;
        this.digitoVehicle = digitoVehicle;
        this.typeVehicle = typeVehicle;
        this.classVehicle = classVehicle;
    }

    public Vehicle() {
    }

    public String getNameVehicle() {
        return nameVehicle;
    }

    public void setNameVehicle(String nameVehicle) {
        this.nameVehicle = nameVehicle;
    }

    public int getDigitoVehicle() {
        return digitoVehicle;
    }

    public void setDigitoVehicle(int digitoVehicle) {
        this.digitoVehicle = digitoVehicle;
    }

    public String getTypeVehicle() {
        return typeVehicle;
    }

    public void setTypeVehicle(String typeVehicle) {
        this.typeVehicle = typeVehicle;
    }

    public String getClassVehicle() {
        return classVehicle;
    }

    public void setClassVehicle(String classVehicle) {
        this.classVehicle = classVehicle;
    }
}
