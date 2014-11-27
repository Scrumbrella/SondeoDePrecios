package Modelo.Controlador;

import Modelo.Categoria;
import Modelo.DAO.CatalogoDeProductos;
import Modelo.Producto;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Christian Guillen
 */
public class ProductoController {

    private final CatalogoDeProductos catProducto;

    public ProductoController() {
        catProducto = new CatalogoDeProductos();
    }

    //AGREGAR PRODUCTO
    public String nuevoProducto(HttpServletRequest request, Categoria categoria) {
        Producto producto = null;
        String res = "2";
        String nombre = request.getParameter("nombre");
        String descripcion = request.getParameter("descripcion");
        String unidad = request.getParameter("unidad");
        if (catProducto.noExisteProducto(nombre)) {
            producto = new Producto(categoria, nombre, descripcion, unidad);
            res = (catProducto.addProducto(producto)) ? "1" : "0";
        }
        return res;
    }

    //MODIFICAR PRODUCTO
    public String modProd(HttpServletRequest request, Categoria categoria) {
        int idPro = Integer.parseInt(request.getParameter("idProd"));
        String nombre = request.getParameter("nombre");
        String desc = request.getParameter("desc");
        String unidad = request.getParameter("unidad");
        Producto prod = new Producto(idPro, categoria, nombre, desc, unidad);
        return (catProducto.modificarProducto(prod)) ? "1" : "0";
    }

    //ELIMINAR PRODUCTO
    public String delProd(HttpServletRequest request) {
        int idProd = Integer.parseInt(request.getParameter("idProd"));
        String res = "2";
        res = (catProducto.eliminarProducto(idProd)) ? "1" : "0";
        return res;
    }

    //
    public String getCatProd(HttpServletRequest request, Categoria cat) {
        return cat.getProds();
    }

    //
    public String getDataProd(HttpServletRequest request) {
        int idProd = Integer.parseInt(request.getParameter("idProd"));
        Producto prod = this.catProducto.getProducto(idProd);
        return prod.getIdCategoria() + "|" + prod.getUnidadMedida() + "|" + prod.getNombre() + "|" + prod.getDescripcion();
    }
}
