package Modelo;
// Generated 11-23-2014 10:06:42 AM by Hibernate Tools 3.6.0

import java.util.HashSet;
import java.util.Set;

/**
 * Establecimiento generated by hbm2java
 */
public class Establecimiento implements java.io.Serializable {

    private int idestablecimiento;
    private String nombre;
    private String direccion;
    private Set precios = new HashSet(0);

    public Establecimiento() {
    }

    public Establecimiento(String nombre, String direccion) {
        this.nombre = nombre;
        this.direccion = direccion;
    }

    public Establecimiento(int idestablecimiento, String nombre, String direccion) {
        this.idestablecimiento = idestablecimiento;
        this.nombre = nombre;
        this.direccion = direccion;
    }

    public Establecimiento(int idestablecimiento, String nombre, String direccion, Set precios) {
        this.idestablecimiento = idestablecimiento;
        this.nombre = nombre;
        this.direccion = direccion;
        this.precios = precios;
    }

    public int getIdestablecimiento() {
        return this.idestablecimiento;
    }

    public void setIdestablecimiento(int idestablecimiento) {
        this.idestablecimiento = idestablecimiento;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return this.direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Set getPrecios() {
        return this.precios;
    }

    public void setPrecios(Set precios) {
        this.precios = precios;
    }

}
