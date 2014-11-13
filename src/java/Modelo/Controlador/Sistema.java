package Modelo.Controlador;

import Modelo.Categoria;
import Modelo.DAO.CatalogoDeCategoria;
import Modelo.DAO.CatalogoDeProductos;
import Modelo.Producto;
import Modelo.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
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
            request.setAttribute("valido", "ok");
            request.setAttribute("titulo", ".::Bienvenido::.");
            HttpSession sesion = request.getSession(true);
            Usuario us = (Usuario) sesion.getAttribute("usuario");
            request.setAttribute("usuario", us);
            rd = request.getRequestDispatcher("/index.jsp");
            rd.forward(request, response);
        }
    }

    private void eliminarCategoria(int idCategoria, PrintWriter out) {
        CatalogoDeCategoria catalogo = new CatalogoDeCategoria();
        Categoria cat = catalogo.getCategoria(idCategoria);
        boolean hecho = catalogo.eliminarCategoria(cat);
        if (hecho) {
            out.print("ok");
        } else {
            out.print("fail");
        }
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
        String hecho = catalogo.validarNombre(nombre, true);
        if (hecho.equals("EsValido")) {
            hecho = catalogo.addCategoria(new Categoria(nombre, descripcion));
        }

        String tipo = "info";
        switch (hecho) {
            case "ya existe":
                tipo = "danger";
                break;
            case "":
                tipo = "warning";
                break;
            case "se ingreso correctamente":
                tipo = "success";
                break;
            case "no se ingreso":
                tipo = "danger";
                break;
        }
        String mensaje = "<div class=\"alert alert-dismissable alert-" + tipo + "\"><button type=\"button\" class=\"close\" data-dismiss=\"alert\">*</button>"
                + "La Categor&iacute;a: \"" + nombre + "\" " + hecho + "</div>";
        out.println(mensaje);
    }

    private void seleccionarCategoria(int idCategoria, PrintWriter out) {
        CatalogoDeCategoria catalogo = new CatalogoDeCategoria();
        out.print(catalogo.getCategoria(idCategoria).getDescripcion());
    }

    private void modificarCategoria(String nombre, String descripcion, int idCategoria, PrintWriter out) {
        CatalogoDeCategoria catalogo = new CatalogoDeCategoria();
        String hecho = catalogo.validarNombre(nombre, false);
        if (hecho.equals("EsValido")) {
            hecho = catalogo.modificarCategoria(new Categoria(idCategoria, nombre, descripcion));
        }

        String tipo = "info";
        switch (hecho) {
            case "ya existe":
                tipo = "danger";
                break;
            case "":
                tipo = "warning";
                break;
            case "se modifico correctamente":
                tipo = "success";
                break;
            case "no se modifico":
                tipo = "danger";
                break;
        }
        String mensaje = "<div class=\"alert alert-dismissable alert-" + tipo + "\"><button type=\"button\" class=\"close\" data-dismiss=\"alert\">*</button>"
                + "La Categor&iacute;a: \"" + nombre + "\" " + hecho + "</div>";
        out.println(mensaje);
    }

    private void selCat(int idCategoria, PrintWriter out) {
        CatalogoDeCategoria catalogo = new CatalogoDeCategoria();
        Categoria cat = catalogo.getCategoria(idCategoria);
        out.print(cat.getDescripcion() + "|" + cat.getNombre());
    }

    private void addProducto(String nombre, String descripcion, String unidad, int idCategoria) {
        CatalogoDeProductos catalogoPro = new CatalogoDeProductos();
        boolean valido = catalogoPro.validarNombre(nombre);
        if (valido) {
            CatalogoDeCategoria catalogoCat = new CatalogoDeCategoria();
            Categoria categoria = catalogoCat.getCategoria(idCategoria);
            Producto producto = new Producto(categoria, nombre, descripcion, unidad);
            boolean hecho = catalogoPro.addProducto(producto);
        }

    }

    private void processRequestPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Enumeration<String> parametros = request.getParameterNames();
        PrintWriter out = response.getWriter();
        this.catProducto = new CatalogoDeProductos();
        this.catCategoria = new CatalogoDeCategoria();
        RequestDispatcher rd;
        if (parametros.hasMoreElements()) {
            String accion = request.getParameter("action");
            String nombre;
            String descripcion;
            if (accion != null) {
                switch (accion) {
                    case "newCat":
                        addCategoria(request.getParameter("frmNewName"), request.getParameter("frmNewDesc"), out);
                        break;
                    case "showCat":
                        mostrarCategorias(out);
                        break;
                    case "descCatDel":
                        seleccionarCategoria(Integer.parseInt(request.getParameter("idCat")), out);
                        break;
                    case "CatDel":
                        eliminarCategoria(Integer.parseInt(request.getParameter("idCat")), out);
                        break;
                    case "getCatValues":
                        selCat(Integer.parseInt(request.getParameter("idCat")), out);
                        break;
                    case "CatMod":
                        nombre = request.getParameter("nombre");
                        descripcion = request.getParameter("descripcion");
                        int idCat = Integer.parseInt(request.getParameter("idCat"));
                        modificarCategoria(nombre, descripcion, idCat, out);
                        break;
                    case "newProd":
                        nombre = request.getParameter("nombre");
                        descripcion = request.getParameter("descripcion");
                        String unidad = request.getParameter("unidad");
                        boolean valido = catProducto.validarNombre(nombre);
                        if (valido) {
                            int idCategoria = Integer.parseInt(request.getParameter("idCategoria"));
                            Categoria categoria = this.catCategoria.getCategoria(idCategoria);
                            Producto producto = new Producto(categoria, nombre, descripcion, unidad);
                            boolean hecho = this.catProducto.addProducto(producto);
                            if (hecho) {
                                out.print("Si");
                            } else {
                                out.print("no");
                            }
                        } else {
                            out.print("No Valido");
                        }

                        //String //addProducto();
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

}
