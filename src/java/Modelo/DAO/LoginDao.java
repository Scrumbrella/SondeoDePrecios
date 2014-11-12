/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.DAO;

import Modelo.Usuario;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Nahum
 */
public class LoginDao {

    public static Usuario validarUsuario(String email, String pass) {
        Usuario user = null;
        Session sesion;
        try {
            sesion = Hibernate.HibernateUtil.getSessionFactory().openSession();
            String hql = "FROM Usuario WHERE email='" + email + "' AND pass='" + pass + "'";
            Query consulta = sesion.createQuery(hql);
            if (!consulta.list().isEmpty()) {
                user = (Usuario) consulta.list().get(0);
            }
        } catch (HibernateException ex) {
            throw ex;
        }
        return user;
    }

}
