package subd.lr5.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import subd.lr5.models.Destination;
import subd.lr5.models.Work;

import java.util.List;
import java.util.Scanner;

public class WorkService {
    public void WorkMenu(SessionFactory sf) {
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
            System.out.println("Input Salary_date:");
            String dt = scanner.next();
            java.sql.Date sql_date = java.sql.Date.valueOf(dt);
            System.out.println("Input Hours_amount:");
            int hours_amount = scanner.nextInt();

            System.out.println("Input id of destination");
            int destination_id = scanner.nextInt();

            Work works = new Work(sql_date, hours_amount, session.get(Destination.class, destination_id));
            session.save(works);
        }
        catch (Exception ex){
            System.out.println(ex);
        }

    }

    private void Read(Session session) {
        List<Work> works = session.createQuery("SELECT e from Work e", Work.class).getResultList();
        System.out.println(works);
    }

    private void Update(Session session) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Input id of work:");
            int id = scanner.nextInt();
            System.out.println("Input Date:");
            String dt = scanner.next();
            java.sql.Date sql_date = java.sql.Date.valueOf(dt);
            System.out.println("Input Hours_amount:");
            int hours_amount = scanner.nextInt();

            System.out.println("Input id of destination");
            int destination_id = scanner.nextInt();

            Work works = session.get(Work.class, id);
            works.setSalary_date(sql_date);
            works.setHours_amount(hours_amount);
            works.setDestination(session.get(Destination.class, destination_id));
            session.save(works);
        }
        catch (Exception ex){
            System.out.println(ex);
        }

    }

    private void Delete(Session session) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Input id of work:");
            int id = scanner.nextInt();
            Work works = session.get(Work.class, id);
            session.delete(works);
        }
        catch (Exception ex){
            System.out.println("Can`t delete row that is used in other table");
        }

    }

    private void Filter(Session session) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Input destination_id of works:");
            int destination_id = scanner.nextInt();
            List<Work> works = session.createQuery("SELECT e from Work e WHERE destinationid = " + destination_id , Work.class).getResultList();
            System.out.println(works);
        }
        catch (Exception ex){
            System.out.println(ex);
        }

    }
}
