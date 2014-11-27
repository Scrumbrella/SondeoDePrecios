package Modelo.Controlador;

import Modelo.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
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

    private final CategoriaController controladorCategoria = new CategoriaController();
    private final ProductoController controladorProductos = new ProductoController();
    private final EstablecimientoController controladorEstablecimiento = new EstablecimientoController();

    private void processRequestPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Enumeration<String> parametros = request.getParameterNames();
        PrintWriter out = response.getWriter();
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
                    case "modProd":
                        modProd(request, out);
                        break;
                    case "delProd":
                        delProd(request, out);
                        break;
                    case "newEsta":
                        addEstablecimiento(request, out);
                        break;
                    case "showEstas":
                        mostrarEstablecimientos(request, out);
                        break;
                    case "getEsta":
                        getEsta(request, out);
                        break;
                    case "delEsta":
                        eliminarEstablecimiento(request, out);
                        break;
                }
            }
        } else {
            request.setAttribute("titulo", ".::Bienvenido::.");
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

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    //USUARIO
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

    //CATEGORIA
    private void addCategoria(String nombre, String descripcion, PrintWriter out) {
        out.print(this.controladorCategoria.addCategoria(nombre, descripcion));
    }

    private void modificarCategoria(HttpServletRequest request, PrintWriter out) {
        out.print(controladorCategoria.modificarCategoria(request));
    }

    private void eliminarCategoria(int idCategoria, PrintWriter out) {
        out.print(controladorCategoria.eliminarCategoria(idCategoria));
    }

    private void mostrarCategorias(PrintWriter out) {
        out.print(controladorCategoria.mostrarCategorias());
    }

    private void getDescCategoria(int idCategoria, PrintWriter out) {
        out.print(controladorCategoria.getDescCategoria(idCategoria));
    }

    private void getNombDescCat(int idCategoria, PrintWriter out) {
        out.print(controladorCategoria.getNombDescCat(idCategoria));
    }

    //PRODUCTO
    private void nuevoProducto(HttpServletRequest request, PrintWriter out) {
        int idCategoria = Integer.parseInt(request.getParameter("idCategoria"));
        controladorCategoria.getNombDescCat(idCategoria);
        out.print(controladorProductos.nuevoProducto(request, controladorCategoria.getCategoria()));
    }

    private void modProd(HttpServletRequest request, PrintWriter out) {
        int idCategoria = Integer.parseInt(request.getParameter("idCat"));
        controladorCategoria.getNombDescCat(idCategoria);
        out.print(controladorProductos.modProd(request, controladorCategoria.getCategoria()));
    }

    private void delProd(HttpServletRequest request, PrintWriter out) {
        out.print(controladorProductos.delProd(request));
    }

    private void getCatProd(HttpServletRequest request, PrintWriter out) {
        int idCat = Integer.parseInt(request.getParameter("idCat"));
        controladorCategoria.getNombDescCat(idCat);
        out.print(controladorProductos.getCatProd(request, controladorCategoria.getCategoria()));
    }

    private void getDataProd(HttpServletRequest request, PrintWriter out) {
        out.print(controladorProductos.getDataProd(request));
    }

    //ESTABLECIMIENTO
    private void addEstablecimiento(HttpServletRequest request, PrintWriter out) {
    out.print(controladorEstablecimiento.addEstablecimiento(request));
    }
    
    /*private void modificarEstablecimiento(HttpServletRequest request, PrintWriter out) {
    out.print(controladorEstablecimiento.modificarEstablecimiento(request));
    }
    */
    private void eliminarEstablecimiento(HttpServletRequest request, PrintWriter out) {
    out.print(controladorEstablecimiento.eliminarEstablecimiento(request));
    }

    private void mostrarEstablecimientos(HttpServletRequest request, PrintWriter out) {
        out.print(controladorEstablecimiento.getEstablecimientos());
    }

    private void getEsta(HttpServletRequest request, PrintWriter out) {
        out.print(controladorEstablecimiento.getAddressEsta(request));
    }

}
