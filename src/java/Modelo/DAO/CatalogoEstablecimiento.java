package Modelo.DAO;

import Hibernate.HibernateUtil;
import Modelo.Establecimiento;
import java.util.ArrayList;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;

/**
 *
 * @author Ivan
 */
public class CatalogoEstablecimiento {

    private ArrayList<Establecimiento> establecimientos;

    public boolean addEstablecimiento(Establecimiento establecimiento) {
        boolean res;
        SessionFactory sf;
        Session session;
        Transaction tx = null;
        try {
            sf = HibernateUtil.getSessionFactory();
            session = sf.openSession();
            tx = session.beginTransaction();
            session.save(establecimiento);
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

    public boolean modificarEstablecimiento(Establecimiento establecimiento) {
        boolean res;
        Session session;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.update(establecimiento);
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

    public Establecimiento getEstablecimiento(int idestablecimiento) {
        Session session;
        session = HibernateUtil.getSessionFactory().openSession();
        Establecimiento es = (Establecimiento) session.get(Establecimiento.class, idestablecimiento);
        session.close();
        return es;
    }

    public boolean eliminarEstablecimiento(int idestablecimiento) {
        boolean res;
        Session session;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.delete(getEstablecimiento(idestablecimiento));
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

    public ArrayList<Establecimiento> getEstablecimientos() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        this.establecimientos = (ArrayList<Establecimiento>) session.createCriteria(Establecimiento.class).addOrder(Order.asc("nombre")).list();
        session.close();
        return this.establecimientos;
    }

    public boolean noExisteEstablecimiento(String nombre) {
        Session sesion;
        boolean res;
        sesion = Hibernate.HibernateUtil.getSessionFactory().openSession();
        String hql = "FROM Establecimiento WHERE nombre='" + nombre + "'";
        Query consulta = sesion.createQuery(hql);
        res = consulta.list().isEmpty();
        sesion.close();
        return res;
    }

}
