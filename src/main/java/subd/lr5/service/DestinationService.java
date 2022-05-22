package subd.lr5.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import subd.lr5.models.Destination;
import subd.lr5.models.Personal;
import subd.lr5.models.Project;

import java.util.List;
import java.util.Scanner;

public class DestinationService {
    public void DestinationMenu(SessionFactory sf) {
        System.out.println("Input a number to choose the action:"
                + "\n1) Create" + "\n2) Read" + "\n3) Update" + "\n4) Delete" + "\n5) Filter");
        Scanner scn = new Scanner(System.in);
        int input = scn.nextInt();

        Session session = null;
        session = sf.getCurrentSession();
        session.beginTransaction();

        try {
            switch (input){
                case 1:
                    Create(session);
                    break;
                case 2:
                    Read(session);
                    break;
                case 3:
                    Update(session);
                    break;
                case 4:
                    Delete(session);
                    break;
                case 5:
                    Filter(session);
                    break;
            }
            session.getTransaction().commit();
        }
        catch (Exception ex){
            System.out.println(ex);
        }

    }


    private void Create(Session session) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Input Destination_Date:");
            String dd = scanner.next();
            java.sql.Date sql_destination_date = java.sql.Date.valueOf(dd);

            System.out.println("Input id of personal");
            int personal_id = scanner.nextInt();
            System.out.println("Input id of project");
            int project_id = scanner.nextInt();

            Destination destination = new Destination(sql_destination_date,
                    session.get(Personal.class, personal_id), session.get(Project.class, project_id));
            session.save(destination);
        }
        catch (Exception ex){
            System.out.println(ex);
        }

    }

    private void Read(Session session) {
        List<Destination> destinations = session.createQuery("SELECT c from Destination c", Destination.class).getResultList();
        System.out.println(destinations);
    }

    private void Update(Session session) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Input id of destination:");
            int id = scanner.nextInt();
            System.out.println("Input Destination_Date:");
            String dd = scanner.next();
            java.sql.Date sql_destination_date = java.sql.Date.valueOf(dd);

            System.out.println("Input id of project");
            int project_id = scanner.nextInt();

            System.out.println("Input id of personal");
            int personal_id = scanner.nextInt();


            Destination destination = session.get(Destination.class, id);
            destination.setDestination_date(sql_destination_date);
            destination.setProject(session.get(Project.class, project_id));
            destination.setPersonal(session.get(Personal.class, personal_id));
            session.save(destination);
        }
        catch (Exception ex){
            System.out.println(ex);
        }

    }

    private void Delete(Session session) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Input id of destination:");
            int id = scanner.nextInt();
            Destination destination = session.get(Destination.class, id);
            session.delete(destination);
        }
        catch (Exception ex){
            System.out.println("Can`t delete row that is used in other table");
        }

    }

    private void Filter(Session session) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Input personal_id of destination:");
            int personal_id = scanner.nextInt();
            List<Destination> destinations = session.createQuery("SELECT c from Destination WHERE personal_id = " + personal_id , Destination.class).getResultList();
            System.out.println(destinations);
        }
        catch (Exception ex){
            System.out.println(ex);
        }

    }
}
