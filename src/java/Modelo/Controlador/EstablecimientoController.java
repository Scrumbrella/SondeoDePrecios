package Modelo.Controlador;

import Modelo.DAO.CatalogoEstablecimiento;
import Modelo.Establecimiento;
import javax.servlet.http.HttpServletRequest;

public class EstablecimientoController {

    private final CatalogoEstablecimiento catEstablecimiento;

    EstablecimientoController() {
        catEstablecimiento = new CatalogoEstablecimiento();
    }

    /*//Agregar Establecimiento
    public String addEstablecimiento(HttpServletRequest request) {
    System.out.print("Paso a add");
    Establecimiento establecimiento = new Establecimiento("Aff", "Bf");
    System.out.print("se creo");
    
    String res = "2";
    String nombre = request.getParameter("nombre");
    String direccion = request.getParameter("addres");
    if (catEstablecimiento.noExisteEstablecimiento(nombre)) {
    System.out.print("Paso");
    //establecimiento = new Establecimiento(nombre,direccion);
    res = (catEstablecimiento.addEstablecimiento(establecimiento)) ? "1" : "0";
    }
    return res;
    }
    
    //Modificar EStablecimiento
    public String modificarEstablecimiento(HttpServletRequest request) {
    int idEstablecimiento = Integer.parseInt(request.getParameter("idEstablecimiento"));
    String nombre = request.getParameter("nombre");
    String direccion = request.getParameter("direccion");
    Establecimiento establecimiento = new Establecimiento(idEstablecimiento, nombre, direccion);
    return (catEstablecimiento.modificarEstablecimiento(establecimiento)) ? "1" : "0";
    }
    
    //Eliminar Establecimiento
    public String eliminarEstablecimiento(HttpServletRequest request) {
    int idEstablecimiento = Integer.parseInt(request.getParameter("idEstablecimiento"));
    String res = "2";
    res = (catEstablecimiento.eliminarEstablecimiento(idEstablecimiento)) ? "1" : "0";
    return res;
    }
    }*/
}