package Modelo.Controlador;

import Modelo.DAO.CatalogoEstablecimiento;
import Modelo.Establecimiento;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;

public class EstablecimientoController {

    private final CatalogoEstablecimiento catEstablecimiento;

    EstablecimientoController() {
        catEstablecimiento = new CatalogoEstablecimiento();
    }

    //Agregar Establecimiento
    public String addEstablecimiento(HttpServletRequest request) {
        String res = "2";
        String nombre = request.getParameter("nombre");
        String direccion = request.getParameter("address");
        if (catEstablecimiento.noExisteEstablecimiento(nombre)) {
            Establecimiento establecimiento = new Establecimiento(nombre,direccion);
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
        int idEstablecimiento = Integer.parseInt(request.getParameter("idEsta"));
        String res = "2";
        res = (catEstablecimiento.eliminarEstablecimiento(idEstablecimiento)) ? "1" : "0";
        return res;
    }
    
    public String getEstablecimientos(){
        ArrayList<Establecimiento> lista = catEstablecimiento.getEstablecimientos();
        String option = "<option  value=\"0\">Selecionar...</option>";
        for (Establecimiento esta : lista) {
            option += "<option value=\"" + esta.getIdestablecimiento() + "\">" + esta.getNombre() + "</option>";
        }
        return option;
    }
    public String getAddressEsta(HttpServletRequest request){
        int idEsta = Integer.parseInt(request.getParameter("idEsta"));
        return catEstablecimiento.getEstablecimiento(idEsta).getDireccion();
    }
}

