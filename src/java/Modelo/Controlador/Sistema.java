
package Modelo.Controlador;

import Modelo.Categoria;
import Modelo.DAO.CatalogoDeCategoria;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
    private void processRequestGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Enumeration<String> parametros =  request.getParameterNames();
        RequestDispatcher rd;
        
        if(parametros.hasMoreElements()){
        
        }else{
            request.setAttribute("titulo", ".::Bienvenido::.");
            request.setAttribute("usuario", "Nahúm Gálvez");
            rd = request.getRequestDispatcher("/index.jsp");
            rd.forward(request, response);
        }
    }
    
    private String addCategoria(String nombre, String descripcion){
        
        CatalogoDeCategoria catalogo = new CatalogoDeCategoria();
        String hecho = catalogo.validarNombre(nombre);
        if(hecho.equals("EsValido")){
            hecho = catalogo.addCategoria(new Categoria(nombre, descripcion));
        }
        return hecho;
    }
    
    
    
    private void processRequestPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Enumeration<String> parametros =  request.getParameterNames();
        PrintWriter out = response.getWriter();
        RequestDispatcher rd;
        String res = "";
        if(parametros.hasMoreElements()){
            String accion = request.getParameter("action");
            if(accion != null){
                switch(accion){
                    case "newCat":
                       res = addCategoria(request.getParameter("frmNewName"), request.getParameter("frmNewDesc"));
                    break;
                }
                
            }
            
            String tipo = "info";
            switch(res){
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
            response.setCharacterEncoding("UTF-8");
            String mensaje = "<div class=\"alert alert-dismissable alert-"+tipo+"\"><button type=\"button\" class=\"close\" data-dismiss=\"alert\">*</button>"
                    + "La Categor&iacute;a: \""+new String(request.getParameter("frmNewName").getBytes("UTF-8"))+"\" "+res+"</div>";
            out.println(mensaje);
        }else{
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
        processRequestGet(request, response);
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
        request.setCharacterEncoding("UTF-8");
        processRequestPost(request, response);
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
