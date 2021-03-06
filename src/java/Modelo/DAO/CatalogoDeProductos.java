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

    private ArrayList<Producto> productos;

    public boolean noExisteProducto(String nombre) {
        Session sesion;
        boolean res;
        sesion = Hibernate.HibernateUtil.getSessionFactory().openSession();
        String hql = "FROM Producto WHERE nombre='" + nombre + "'";
        Query consulta = sesion.createQuery(hql);
        res = consulta.list().isEmpty();
        sesion.close();
        return res;
    }

    public boolean esValido(String nombre) {
        return nombre.matches("[a-zA-Z ñáéíóú]*");
    }

    public ArrayList<Producto> getProductos() {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        Query consulta = session.createQuery("from Producto");
        ArrayList<Producto> lista = (ArrayList<Producto>) consulta.list();
        session.close();
        return lista;
    }

    public boolean addProducto(Producto producto) {
        boolean res;
        SessionFactory sf;
        Session session;
        Transaction tx = null;
        try {
            sf = HibernateUtil.getSessionFactory();
            session = sf.openSession();
            tx = session.beginTransaction();
            session.save(producto);
            tx.commit();
            session.close();
            res = true;
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
            }
            res = false;
        }
        return res;
    }

    public boolean eliminarProducto(int idProducto) {
        boolean res;
        Session session;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.delete(getProducto(idProducto));
            tx.commit();
            session.close();
            res = true;
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
            }
            res = false;
        }
        return res;
    }

    public Producto getProducto(int idProducto) {
        Session session;
        session = HibernateUtil.getSessionFactory().openSession();
        Producto prod = (Producto) session.get(Producto.class, idProducto);
        session.close();
        return prod;
    }

    public boolean modificarProducto(Producto producto) {
        boolean res;
        Session session;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.update(producto);
            tx.commit();
            session.close();
            res = true;
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
            }
            res = false;
        }
        return res;
    }
}
