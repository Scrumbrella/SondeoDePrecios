package Modelo.Controlador;

import Modelo.Categoria;
import Modelo.DAO.CatalogoDeCategoria;
import Modelo.DAO.CatalogoDeProductos;
import Modelo.Producto;
import Modelo.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Nahum
 */
public class Sistema extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private CatalogoDeProductos catProducto;
    private CatalogoDeCategoria catCategoria;

    private boolean seguridad(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        Usuario user = (Usuario) session.getAttribute("usuario");
        boolean res = true;
        if (user == null) {
            res = false;
            request.getRequestDispatcher("/Login").forward(request, response);
        }
        return res;
    }

    private void processRequestGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Enumeration<String> parametros = request.getParameterNames();
        RequestDispatcher rd;
        if (!parametros.hasMoreElements()) {
            request.setAttribute("titulo", ".::Bienvenido::.");
            HttpSession sesion = request.getSession(true);
            Usuario us = (Usuario) sesion.getAttribute("usuario");
            request.setAttribute("usuario", us);
            rd = request.getRequestDispatcher("/index.jsp");
            request.setAttribute("valido", "ok");
            rd.forward(request, response);
        }
    }

    private void eliminarCategoria(int idCategoria, PrintWriter out) {
        this.catCategoria = new CatalogoDeCategoria();
        Categoria cat = this.catCategoria.getCategoria(idCategoria);
        boolean hecho = this.catCategoria.eliminarCategoria(cat);
        String res = (hecho) ? "1" : "0";
        out.print(res);
    }

    private void mostrarCategorias(PrintWriter out) {
        CatalogoDeCategoria catalogo = new CatalogoDeCategoria();
        ArrayList<Categoria> lista = catalogo.getCategorias();
        String option = "<option  value=\"0\">Selecionar...</option>";
        for (Categoria categoria : lista) {
            option += "<option value=\"" + categoria.getIdcategoria() + "\">" + categoria.getNombre() + "</option>";
        }
        out.println(option);
    }

    private void addCategoria(String nombre, String descripcion, PrintWriter out) {
        CatalogoDeCategoria catalogo = new CatalogoDeCategoria();
        boolean hecho = catalogo.noExisteCategoria(nombre);
        String res = "2";
        if (hecho) {
            hecho = catalogo.addCategoria(new Categoria(nombre, descripcion));
            res = (hecho) ? "1" : "0";
        }
        out.print(res);
    }

    private void getDescCategoria(int idCategoria, PrintWriter out) {
        CatalogoDeCategoria catalogo = new CatalogoDeCategoria();
        out.println(catalogo.getCategoria(idCategoria).getDescripcion());
    }

    private void modificarCategoria(HttpServletRequest request, PrintWriter out) {
        int idCat = Integer.parseInt(request.getParameter("idCat"));
        String nombre, descripcion;
        nombre = request.getParameter("nombre");
        descripcion = request.getParameter("descripcion");
        CatalogoDeCategoria catalogo = new CatalogoDeCategoria();
        Categoria cat = new Categoria(idCat, nombre, descripcion);
        boolean hecho = catalogo.modificarCategoria(cat);
        String res = (hecho) ? "1" : "0";
        out.print(res);
    }

    private void getNombDescCat(int idCategoria, PrintWriter out) {
        this.catCategoria = new CatalogoDeCategoria();
        Categoria cat = this.catCategoria.getCategoria(idCategoria);
        out.print(cat.getDescripcion() + "|" + cat.getNombre());

    }

    private void nuevoProducto(HttpServletRequest request, PrintWriter out) {
        String nombre, descripcion, unidad;
        nombre = request.getParameter("nombre");
        descripcion = request.getParameter("descripcion");
        unidad = request.getParameter("unidad");
        boolean valido = this.catProducto.noExisteProducto(nombre);
        String res = "2";
        if (valido) {
            int idCategoria = Integer.parseInt(request.getParameter("idCategoria"));
            Categoria categoria = this.catCategoria.getCategoria(idCategoria);
            Producto producto = new Producto(categoria, nombre, descripcion, unidad);
            boolean hecho = this.catProducto.addProducto(producto);
            res = (hecho) ? "1" : "0";
        }
        out.print(res);
    }

    private void processRequestPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Enumeration<String> parametros = request.getParameterNames();
        PrintWriter out = response.getWriter();
        this.catProducto = new CatalogoDeProductos();
        this.catCategoria = new CatalogoDeCategoria();
        RequestDispatcher rd;
        if (parametros.hasMoreElements()) {
            String accion = request.getParameter("action");
            if (accion != null) {
                switch (accion) {
                    case "newCat":
                        addCategoria(request.getParameter("frmNewName"), request.getParameter("frmNewDesc"), out);
                        break;
                    case "showCat":
                        mostrarCategorias(out);
                        break;
                    case "descCatDel":
                        getDescCategoria(Integer.parseInt(request.getParameter("idCat")), out);
                        break;
                    case "CatDel":
                        eliminarCategoria(Integer.parseInt(request.getParameter("idCat")), out);
                        break;
                    case "getCatValues":
                        getNombDescCat(Integer.parseInt(request.getParameter("idCat")), out);
                        break;
                    case "CatMod":
                        modificarCategoria(request, out);
                        break;
                    case "newProd":
                        nuevoProducto(request, out);
                        break;
                    case "getCatProd":
                        getCatProd(request, out);
                        break;
                    case "getDataProd":
                        getDataProd(request, out);
                        break;
                }
            }
        } else {
            request.setAttribute("titulo", ".::Bienvenido::.");
            request.setAttribute("usuario", "Nahúm Gálvez");
            rd = request.getRequestDispatcher("/index.jsp");
            rd.forward(request, response);
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        boolean res = seguridad(request, response);
        if (res) {
            processRequestGet(request, response);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        boolean res = seguridad(request, response);
        if (res) {
            request.setCharacterEncoding("UTF-8");
            processRequestPost(request, response);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void getCatProd(HttpServletRequest request, PrintWriter out) {
        int idCat = Integer.parseInt(request.getParameter("idCat"));
        this.catCategoria = new CatalogoDeCategoria();
        Categoria cat = this.catCategoria.getCategoria(idCat);
        out.print(cat.getProds());
    }

    private void getDataProd(HttpServletRequest request, PrintWriter out) {
        int idProd = Integer.parseInt(request.getParameter("idProd"));
        this.catProducto = new CatalogoDeProductos();
        Producto prod = this.catProducto.getProducto(idProd);
        out.print(prod.getIdCategoria() + "|" + prod.getUnidadMedida() + "|" + prod.getNombre() + "|" + prod.getDescripcion());

    }

}
