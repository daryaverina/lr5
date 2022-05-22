package subd.lr5.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import subd.lr5.models.Destination;
import subd.lr5.models.Personal;
import subd.lr5.models.Position;

import java.util.List;
import java.util.Scanner;

public class PersonalService {
    public void PersonalMenu(SessionFactory sf) {
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
            System.out.println("Input Full_name:");
            String full_name = scanner.next();
            System.out.println("Input Nadbavka:");
            Double nadbavka = scanner.nextDouble();
            System.out.println("Input Work_Experience:");
            Integer work_experience = scanner.nextInt();
            System.out.println("Input Phone_number:");
            String phone_number = scanner.next();
            System.out.println("Input Mail_address:");
            String mail_address = scanner.next();


            System.out.println("Input id of position");
            int position_id = scanner.nextInt();

            Personal personal = new Personal(full_name, nadbavka, work_experience, phone_number, mail_address,
                    session.get(Position.class, position_id));
            session.save(personal);
        }
        catch (Exception ex){
            System.out.println(ex);
        }

    }

    private void Read(Session session) {
        List<Personal> personals = session.createQuery("SELECT c from Personal c", Personal.class)
                .getResultList();
        System.out.println(personals);
    }

    private void Update(Session session) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Input id of personal:");
            int id = scanner.nextInt();
            System.out.println("Input Full_name:");
            String full_name = scanner.next();
            System.out.println("Input Nadbavka:");
            Double nadbavka = scanner.nextDouble();
            System.out.println("Input Experience:");
            Integer work_experience= scanner.nextInt();
            System.out.println("Input Phone_number:");
            String phone_number = scanner.next();
            System.out.println("Input Mail_address:");
            String mail_address = scanner.next();

            System.out.println("Input id of position");
            int position_id = scanner.nextInt();

            Personal personal = session.get(Personal.class, id);
            personal.setFull_name(full_name);
            personal.setNadbavka(nadbavka);
            personal.setWork_experience(work_experience);
            personal.setPhone_number(phone_number);
            personal.setMail_address(mail_address);
            personal.setPosition(session.get(Position.class, position_id));
            session.save(personal);
        }
        catch (Exception ex){
            System.out.println("Can`t delete row that is used in other table");
        }

    }

    private void Delete(Session session) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Input id of personal:");
            int id = scanner.nextInt();
            Personal personal = session.get(Personal.class, id);
            session.delete(personal);

        }
        catch (Exception ex){
            System.out.println(ex);
        }

    }

    private void Filter(Session session) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Input position_id of personal:");
            int position_id = scanner.nextInt();
            List<Personal> personals = session.createQuery("SELECT c from Personal c WHERE " +
                    "positionid = " + position_id , Personal.class).getResultList();
            System.out.println(personals);
        }
        catch (Exception ex){
            System.out.println(ex);
        }

    }
}
