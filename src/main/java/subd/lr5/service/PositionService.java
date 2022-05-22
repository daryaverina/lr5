package subd.lr5.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import subd.lr5.models.Position;

import java.util.List;
import java.util.Scanner;

public class PositionService {
    public void PositionMenu(SessionFactory sf) {
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
            System.out.println("Input Position_name:");
            String position_name = scanner.next();
            System.out.println("Input Cost_hour:");
            Double cost_hour = scanner.nextDouble();
            Position positions = new Position(position_name, cost_hour);
            session.save(positions);
        }
        catch (Exception ex){
            System.out.println(ex);
        }

    }

    private void Read(Session session) {
        List<Position> positions = session.createQuery("SELECT c from Position c", Position.class).getResultList();
        System.out.println(positions);
    }

    private void Update(Session session) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Input id of position:");
            int id = scanner.nextInt();
            System.out.println("Input Position_Name:");
            String position_name = scanner.next();
            System.out.println("Input Cost_hour:");
            Double cost_hour = scanner.nextDouble();
            Position position = session.get(Position.class, id);
            position.setPosition_name(position_name);
            position.setCost_hour(cost_hour);
            session.save(position);
        }
        catch (Exception ex){
            System.out.println(ex);
        }

    }

    private void Delete(Session session) {
        try {

            Scanner scanner = new Scanner(System.in);
            System.out.println("Input id of position:");
            int id = scanner.nextInt();
            Position position = session.get(Position.class, id);
            session.delete(position);
        }
        catch (Exception ex){
            System.out.println("Can`t delete row that is used in other table");
        }
    }

    private void Filter(Session session) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Input cost_hour of position:");
            Integer cost_hour = scanner.nextInt();
            List<Position> positions = session.createQuery("SELECT c from Position c WHERE" +
                    " cost_hour > \'" + cost_hour + "\'", Position.class).getResultList();
            System.out.println(positions);
        }
        catch (Exception ex){
            System.out.println(ex);
        }
    }
}
