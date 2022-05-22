package subd.lr5.service;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import subd.lr5.models.*;

import java.util.List;

public class RequestService {
    public void work (SessionFactory sessionFactory) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        List<Personal> personals = session.createQuery("SELECT p FROM Personal p", Personal.class).getResultList();
        System.out.print("~Personals~");
        for (int i = 0; i < personals.size(); i++) {
            System.out.format("\nPersonal name: %s, Work Experience: %s, Phone Number: %s, Position name: %s", personals.get(i).getFull_name(),
                    personals.get(i).getWork_experience(),
                    personals.get(i).getPhone_number(),
                    personals.get(i).getPosition().getPosition_name());
        }
        session.getTransaction().commit();
    }
}
