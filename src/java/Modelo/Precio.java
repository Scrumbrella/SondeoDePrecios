package Modelo;
// Generated 11-23-2014 10:06:42 AM by Hibernate Tools 3.6.0


import java.math.BigDecimal;

/**
 * Precio generated by hbm2java
 */
public class Precio  implements java.io.Serializable {


     private PrecioId id;
     private Establecimiento establecimiento;
     private Producto producto;
     private BigDecimal precio;

    public Precio() {
    }

    public Precio(PrecioId id, Establecimiento establecimiento, Producto producto, BigDecimal precio) {
       this.id = id;
       this.establecimiento = establecimiento;
       this.producto = producto;
       this.precio = precio;
    }
   
    public PrecioId getId() {
        return this.id;
    }
    
    public void setId(PrecioId id) {
        this.id = id;
    }
    public Establecimiento getEstablecimiento() {
        return this.establecimiento;
    }
    
    public void setEstablecimiento(Establecimiento establecimiento) {
        this.establecimiento = establecimiento;
    }
    public Producto getProducto() {
        return this.producto;
    }
    
    public void setProducto(Producto producto) {
        this.producto = producto;
    }
    public BigDecimal getPrecio() {
        return this.precio;
    }
    
    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }




}


