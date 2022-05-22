package subd.lr5;

import subd.lr5.models.*;
import subd.lr5.service.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.util.Scanner;
import  org.jboss.logging.Logger;
import java.util.logging.Level;
import static java.util.logging.Logger.getLogger;


public class Menu {
    public static void main(String[] args) {
        @SuppressWarnings("unused")                                          //
        Logger logger = org.jboss.logging.Logger.getLogger("org.hibernate"); //clear console from hibernate log
        getLogger("org.hibernate").setLevel(Level.OFF);                      //

        SessionFactory sessionFactory = new Configuration()
                .addAnnotatedClass(Destination.class)
                .addAnnotatedClass(Personal.class)
                .addAnnotatedClass(Position.class)
                .addAnnotatedClass(Project.class)
                .addAnnotatedClass(Work.class)
                .buildSessionFactory();
        RequestService zs = new RequestService();
        zs.work(sessionFactory);
        boolean isMenu = true;
        while(isMenu){
            try {
                //ZaprosService zs = new ZaprosService();
                //zs.work(sessionFactory);
                System.out.println("\n\nInput a number to work with:"
                        + "\n1) Destinations" + "\n2) Personals" + "\n3) Positions" + "\n4) Projects" + "\n5) Works" + "\n6)Request"
                        + "\nInput 0 to finish");

                Scanner scn = new Scanner(System.in);
                int input = scn.nextInt();

                switch (input){
                    case 0:
                        isMenu = false;
                        break;
                    case 1:
                        DestinationService ts = new DestinationService();
                        try {
                            ts.DestinationMenu(sessionFactory);
                        }
                        catch (Exception ex){
                            System.out.println(ex);
                            ts.DestinationMenu(sessionFactory);
                        }
                        break;
                    case 2:
                        PersonalService es = new PersonalService();
                        try {
                            es.PersonalMenu(sessionFactory);
                        }
                        catch (Exception ex){
                            System.out.println(ex);
                            es.PersonalMenu(sessionFactory);
                        }

                        break;
                    case 3:
                        PositionService cls = new PositionService();
                        try {
                            cls.PositionMenu(sessionFactory);
                        }
                        catch (Exception ex){
                            System.out.println(ex);
                            cls.PositionMenu(sessionFactory);
                        }

                        break;
                    case 4:
                        ProjectService cs = new ProjectService();
                        try {
                            cs.ProjectMenu(sessionFactory);
                        }
                        catch (Exception ex){
                            System.out.println(ex);
                            cs.ProjectMenu(sessionFactory);
                        }

                        break;
                    case 5:
                        WorkService exs = new WorkService();
                        try {
                            exs.WorkMenu(sessionFactory);
                        }
                        catch (Exception ex){
                            System.out.println(ex);
                            exs.WorkMenu(sessionFactory);
                        }
                        break;
                    case 6:
                        RequestService zs1 = new RequestService();
                        zs1.work(sessionFactory);
                        break;
                }
            }
            catch (Exception ex){
                System.out.println(ex);
            }
        }
        sessionFactory.close();
    }
}
