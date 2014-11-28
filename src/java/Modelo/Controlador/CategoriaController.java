package Modelo.Controlador;

import Modelo.Categoria;
import Modelo.DAO.CatalogoDeCategoria;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Christian Guillen
 */
public class CategoriaController {

    private Categoria categoria;
    private final CatalogoDeCategoria catalogo;

    public CategoriaController() {
        catalogo = new CatalogoDeCategoria();
        categoria = new Categoria();
    }

    //Agregar Categoria
    public String addCategoria(String nombre, String descripcion) {
        boolean hecho = catalogo.noExisteCategoria(nombre);
        String res = "2";
        if (hecho) {
            hecho = catalogo.addCategoria(new Categoria(nombre, descripcion));
            res = (hecho) ? "1" : "0";
        }
        return res;
    }

    //Modificar Categoria
    public String modificarCategoria(HttpServletRequest request) {
        int idCat = Integer.parseInt(request.getParameter("idCat"));
        String nombre, descripcion;
        nombre = request.getParameter("nombre");
        descripcion = request.getParameter("descripcion");
        categoria = new Categoria(idCat, nombre, descripcion);
        boolean hecho = catalogo.modificarCategoria(categoria);
        return (hecho) ? "1" : "0";
    }

    //Eliminar Categoria
    public String eliminarCategoria(int idCategoria) {
        return (catalogo.eliminarCategoria(catalogo.getCategoria(idCategoria))) ? "1" : "0";
    }

    //Mostar Categoria
    public String mostrarCategorias() {
        ArrayList<Categoria> lista = catalogo.getCategorias();
        String option = "<option value=\"0\">Seleccionar</option>";
        for (Categoria cat : lista) {
            option += "<option value=\"" + cat.getIdcategoria() + "\">" + cat.getNombre() + "</option>";
        }
        return option;
    }

    //
    public String getDescCategoria(int idCategoria) {
        return catalogo.getCategoria(idCategoria).getDescripcion();
    }

    //
    public String getNombDescCat(int idCategoria) {
        categoria = catalogo.getCategoria(idCategoria);
        return categoria.getDescripcion() + "|" + categoria.getNombre();
    }

    ///METODOS PARA OTROS CONTROLADORES
    public Categoria getCategoria() {
        return categoria;
    }
}
