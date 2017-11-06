package conexion;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class Conexion {

    SessionFactory sessionFactory;

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

    public void addSeveral(List<Object> objects) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        for (Object object : objects) {
            try {
                session.save(object);
            } catch(Exception e) {
                e.printStackTrace();
                session.getTransaction().rollback();
                session.close();
                return;
            }
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

    //Esta version nos permite determinar el limite de registros que queremos
    public <T> List<T> getListQuery(String query, int maxResults) {

        Session session = sessionFactory.openSession();
        List<T> l = (List<T>) session.createQuery(query).setMaxResults(maxResults).list();
        session.close();
        return l;
    }

    public <T> Object ReadOne_simpleid(Class<T> typeClass, int id) {
        Session session = sessionFactory.openSession();
        T obj = (T) session.get(typeClass, id);
        session.close();
        return obj;
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
