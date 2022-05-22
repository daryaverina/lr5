package subd.lr5.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import subd.lr5.models.Project;

import java.util.List;
import java.util.Scanner;

public class ProjectService {
    public void ProjectMenu(SessionFactory sf) {
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
            System.out.println("Input Project_name:");
            String project_name = scanner.next();
            System.out.println("Input Status is_develop: 1 - true, 0 - false");
            int is_developInt = scanner.nextInt();
            Boolean is_develop = true;
            if (is_developInt == 0) is_develop = false;
            Project project = new Project(project_name, is_develop);
            session.save(project);
        }
        catch (Exception ex){
            System.out.println(ex);
        }

    }

    private void Read(Session session) {
        List<Project> projects = session.createQuery("SELECT c from Project c", Project.class).getResultList();
        System.out.println(projects);
    }

    private void Update(Session session) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Input id of project:");
            int id = scanner.nextInt();
            System.out.println("Input project_name:");
            String project_name = scanner.next();
            System.out.println("Input Status is_develop: 1 - true, 0 - false");
            int is_developInt = scanner.nextInt();
            Boolean is_develop = true;
            if (is_developInt == 0) is_develop = false;
            Project project = session.get(Project.class, id);
            project.setProject_name(project_name);
            project.setIs_develop(is_develop);
            session.save(project);
        }
        catch (Exception ex){
            System.out.println(ex);
        }

    }

    private void Delete(Session session) {
        try {

            Scanner scanner = new Scanner(System.in);
            System.out.println("Input id of project:");
            int id = scanner.nextInt();
            Project project = session.get(Project.class, id);
            session.delete(project);
        }
        catch (Exception ex){
            System.out.println("Can`t delete row that is used in other table");
        }
    }

    private void Filter(Session session) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Input project_name of project:");
            String project_name = scanner.next();
            List<Project> projects = session.createQuery("SELECT c from Project c WHERE project_name = \'" + project_name + "\'", Project.class).getResultList();
            System.out.println(projects);
        }
        catch (Exception ex){
            System.out.println(ex);
        }
    }
}
