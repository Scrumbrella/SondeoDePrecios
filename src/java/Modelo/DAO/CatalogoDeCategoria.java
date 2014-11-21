package Modelo.DAO;

import Hibernate.HibernateUtil;
import Modelo.Categoria;
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
public class CatalogoDeCategoria {

    private ArrayList<Categoria> categorias;

    public CatalogoDeCategoria() {
    }

    public Categoria validarCategoria(Categoria cat) {
        return cat;
    }

    public boolean addCategoria(Categoria categoria) {
        boolean res;
        SessionFactory sf;
        Session session;
        Transaction tx = null;
        try {
            sf = HibernateUtil.getSessionFactory();
            session = sf.openSession();
            tx = session.beginTransaction();
            session.save(categoria);
            tx.commit();
            session.close();
            res = true;
        } catch (HibernateException ex) {
            if (tx != null) 
                tx.rollback();
            res = false;
        }
        return res;
    }

    public boolean eliminarCategoria(Categoria categoria) {
        boolean res;
        SessionFactory sf;
        Session session;
        Transaction tx = null;
        try {
            sf = HibernateUtil.getSessionFactory();
            session = sf.openSession();
            tx = session.beginTransaction();
            session.delete(categoria);
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

    public boolean modificarCategoria(Categoria categoria) {
        boolean res;
        SessionFactory sf;
        Session session;
        Transaction tx = null;
        try {
            sf = HibernateUtil.getSessionFactory();
            session = sf.openSession();
            tx = session.beginTransaction();
            session.update(categoria);
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

    public ArrayList<Categoria> getCategorias() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query consulta = session.createQuery("from Categoria");
        this.categorias = (ArrayList<Categoria>) consulta.list();
        session.close();
        this.categorias.sort(null);
        return this.categorias;
    }

    public Categoria getCategoria(int idCategoria) {
        SessionFactory sf;
        Session session;
        sf = HibernateUtil.getSessionFactory();
        session = sf.openSession();
        Categoria cat = (Categoria) session.get(Categoria.class, idCategoria);
        session.close();
        return cat;
    }

    public boolean noExisteCategoria(String nombre) {
        Session sesion;
        boolean res;
        sesion = Hibernate.HibernateUtil.getSessionFactory().openSession();
        String hql = "FROM Categoria WHERE nombre='" + nombre+"'";
        Query consulta = sesion.createQuery(hql);
        res = consulta.list().isEmpty();
        sesion.close();
        return res;
    }

}
