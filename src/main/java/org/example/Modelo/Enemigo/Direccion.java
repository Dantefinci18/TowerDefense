package org.example.Modelo.Enemigo;

public class Direccion {
    private double dx;
    private double dy;

    public Direccion(double dx, double dy){
        this.dx = dx;
        this.dy = dy;
    }

    public void reestablecer(double dx, double dy){
        this.dx = dx;
        this.dy = dy;
    }

    public double obtenerAngulo(){return Math.toDegrees(Math.atan2(this.dy, this.dx));}
}
