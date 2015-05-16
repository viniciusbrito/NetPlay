/*
 * HibernateUtility.java
 *
 * Created on 27 de Abril de 2006, 15:18
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package utilitario;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author Alexandre Andrade
 */
public class HibernateUtility {
    
    private static SessionFactory factory;
    
    static {
        try {              
            factory = new Configuration().configure().buildSessionFactory();
            
        } catch (Exception e) {
            e.printStackTrace();
            factory = null;
        }
    }
 
    public static Session getSession() {
        return factory.openSession();
    }
}





