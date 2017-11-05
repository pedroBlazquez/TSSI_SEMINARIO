package conexion;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class Conexion {

    public SessionFactory sessionFactory;

    public Conexion() {
    }

    public void abrirConexion() {
        Configuration configuration = new Configuration();
        configuration.configure();
        ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties())
                .buildServiceRegistry();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
    }

    public void add(Object obj) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(obj);
        session.getTransaction().commit();
        session.close();
    }

    public <T> void addList(List<T> obj_list) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        for(T obj : obj_list) {
            session.save(obj);
        }
        session.getTransaction().commit();
        session.close();
    }
    
    public void update(Object obj) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(obj);
        session.getTransaction().commit();
        session.close();
    }

    public void delete(Object obj) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(obj);
        session.getTransaction().commit();
        session.close();
    }

    public <T> List<T> getListQuery(String query) {

        Session session = sessionFactory.openSession();
        List<T> l = (List<T>) session.createQuery(query).list();
        session.close();
        return l;
    }
    
    public <T> Object ReadOne_simpleid(Class<T> typeClass, int id) {
        Session session = sessionFactory.openSession();
        T obj = (T) session.get(typeClass, id);
        session.close();
        return obj;
    }

    public void InitializeLAZY (Object objeto){
        Session session = sessionFactory.openSession();
        Hibernate.initialize(objeto);
        session.close();
    }
    
    public void Refresh(Object obj) {

        Session session = sessionFactory.openSession();
        session.refresh(obj);
        session.close();
    }

    public void cerrarConexion() {
        sessionFactory.close();
    }
}
