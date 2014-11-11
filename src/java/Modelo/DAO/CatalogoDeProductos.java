
package Modelo.DAO;

import Hibernate.HibernateUtil;
import Modelo.Categoria;
import Modelo.Producto;
import java.util.ArrayList;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Nahum
 */
public class CatalogoDeProductos {
    ArrayList<Producto> productos;
    public boolean validarNombre(String nombre){
        boolean valido = esValido(nombre);
        if(valido){
            this.productos = getProductos();
            for (Producto producto : this.productos) {
                if(producto.esIgual(nombre)){
                    valido = false;
                    break;
                }
            }
            
        }
        return valido;
    }
    
    public boolean esValido(String nombre){
        return nombre.matches("[a-zA-Z ñáéíóú]*");
    }
    
    public ArrayList<Producto> getProductos(){
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        Query consulta = session.createQuery("from Producto");
        ArrayList<Producto> lista = (ArrayList<Producto>) consulta.list();
        session.close();
        return lista;
    }
    
  
    public boolean addProducto(Producto producto){
        boolean res;
        SessionFactory sf;
        Session session;
        Transaction tx = null;
        try{
            sf = HibernateUtil.getSessionFactory();
            session = sf.openSession();
            tx = session.beginTransaction();
            session.save(producto);
            tx.commit();
            session.close();
            res = true;
        }catch(HibernateException ex){
            if(tx != null)
                tx.rollback();
            res = false;
        }
        return res;
    }
    
    public boolean eliminarProducto(int idProducto){
        return false;
    }
    
    public Producto getProducto(int idProducto){
        return null;
    }
    
    public boolean modificarProducto(int idProducto, int idCategoria, String nombre, String descripcion, String unidad){
        return false;
    }
}
