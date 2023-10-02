package com.example.moneysave.Models;

public class Gasto {
    private String nombre;
    private double importe;

    public Gasto(String nombre, double importe) {
        this.nombre = nombre;
        this.importe = importe;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    @Override
    public String toString() {
        return "Gasto{" +
                "nombre='" + nombre + '\'' +
                ", importe=" + importe +
                '}';
    }
}
