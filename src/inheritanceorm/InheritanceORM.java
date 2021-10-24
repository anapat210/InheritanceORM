/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inheritanceorm;

import java.util.List;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author asus
 */
public class InheritanceORM {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner inp = new Scanner(System.in);
        program : while(true){
            System.out.println("InheritanceORM Demo Menu");
            System.out.println("1 : Insert FullTime Employee");
            System.out.println("2 : Insert PartTime Employee");
            System.out.println("3 : Update FullTime Employee");
            System.out.println("4 : Update PartTime Employee");
            System.out.println("5 : Remove FullTime Employee");
            System.out.println("6 : Remove PartTime Employee");
            System.out.println("0 : Exit Program");
            int menu = inp.nextInt();
            switch(menu){
            case 1:
                FulltimeEmployee emp1 = new FulltimeEmployee();
                System.out.println("Enter FullTimeEmployee Name");
                inp.nextLine();
                String emp1Name = inp.nextLine();
                emp1.setName(emp1Name);
                System.out.println("Enter FullTimeEmployee Salary");
                int emp1Salary = inp.nextInt();
                emp1.setSalary(emp1Salary);
                if(InsertEmployee(emp1) == 1){
                    System.out.println("Insert FulltimeEmployee Successful");
                }else{
                    System.out.println("Insert FulltimeEmployee Unsuccessful");
                }
                break;
            case 2:
                ParttimeEmployee emp2 = new ParttimeEmployee();
                System.out.println("Enter PartTimeEmployee Name");
                inp.nextLine();
                String emp2Name = inp.nextLine();
                emp2.setName(emp2Name);
                System.out.println("Enter PartTimeEmployee HourWork");
                int emp2HourWork = inp.nextInt();
                emp2.setHourWork(emp2HourWork);
                if(InsertEmployee(emp2) == 1){
                    System.out.println("Insert PartTimeEmployee Successful");
                }else{
                    System.out.println("Insert PartTimeEmployee Unsuccessful");
                }
                break;
            case 3:
                System.out.println("Enter FullTimeEmployee ID to Update");
                long emp3ID = inp.nextLong();
                FulltimeEmployee emp3 = findFullTimeEmployeeById(emp3ID);
                System.out.println("Enter FullTimeEmployee Name");
                inp.nextLine();
                String emp3Name = inp.nextLine();
                emp3.setName(emp3Name);
                System.out.println("Enter FullTimeEmployee Salary");
                int emp3Salary = inp.nextInt();
                emp3.setSalary(emp3Salary);
                if(updateFulltimeEmployee(emp3) == 1){
                    System.out.println("Update FullTimeEmployee Successful");
                }else{
                    System.out.println("Update FullTimeEmployee Unsuccessful");
                }
                break;
            case 4:
                System.out.println("Enter PartTimeEmployee ID to Update");
                long emp4ID = inp.nextLong();
                ParttimeEmployee emp4 = findPartTimeEmployeeById(emp4ID);
                System.out.println("Enter PartTimeEmployee Name");
                inp.nextLine();
                String emp4Name = inp.nextLine();
                emp4.setName(emp4Name);
                System.out.println("Enter PartTimeEmployee HourWork");
                int emp4HourWork = inp.nextInt();
                emp4.setHourWork(emp4HourWork);
                if(updateParttimeEmployee(emp4) == 1){
                    System.out.println("Update PartTimeEmployee Successful");
                }else{
                    System.out.println("Update PartTimeEmployee Unsuccessful");
                }
                break;
            case 5:
                System.out.println("Enter FullTimeEmployee ID to Delete");
                long emp5ID = inp.nextLong();
                FulltimeEmployee emp5 = findFullTimeEmployeeById(emp5ID);
                
                if(updateFulltimeEmployee(emp5) == 1){
                    System.out.println("Delete FullTimeEmployee Successful");
                }else{
                    System.out.println("Delete FullTimeEmployee Unsuccessful");
                }
                break;
            case 6:
                System.out.println("Enter PartTimeEmployee ID to Delete");
                long emp6ID = inp.nextLong();
                FulltimeEmployee emp6 = findFullTimeEmployeeById(emp6ID);
                
                if(updateFulltimeEmployee(emp6) == 1){
                    System.out.println("Delete PartTimeEmployee Successful");
                }else{
                    System.out.println("Delete PartTimeEmployee Unsuccessful");
                }
                break;
            case 0:
                break program;
            }
        }
//        FulltimeEmployee emp1 = new FulltimeEmployee();
//        emp1.setName("John");
//        emp1.setSalary(5000);
//
//        ParttimeEmployee emp2 = new ParttimeEmployee();
//        emp2.setName("Mary");
//        emp2.setHourWork(8);
//        InsertEmployee(emp1);
//        InsertEmployee(emp2);
//        updateFulltimeEmployee(emp1);
//        removeFulltimeEmployee(emp1);
        
//        long empID = 1;
//        FulltimeEmployee emp = findFullTimeEmployeeById(empID);
//        emp.setSalary(4000);
//        System.out.println(emp1.getName())
//        updateFulltimeEmployee(emp);
        
    }
        public static void printAllEmployee(List<AbstractEmployee> employeeList) {
        for(AbstractEmployee emp : employeeList) {
           System.out.print(emp.getId() + " ");
           System.out.print(emp.getName() + " ");
       }
    }
    
    
    public static List<AbstractEmployee> findAllEmployee() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("InheritanceORMPU");
        EntityManager em = emf.createEntityManager();
        List<AbstractEmployee> empList = (List<AbstractEmployee>) em.createNamedQuery("AbstractEmployee.findAll").getResultList();
        em.close();
        return empList;
    }
    public static int InsertEmployee(Object object) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("InheritanceORMPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(object);
            em.getTransaction().commit();
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
            return 0;
        } finally {
            em.close();
        }
    }
    public static int updateFulltimeEmployee(FulltimeEmployee emp) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("InheritanceORMPU");
        EntityManager em = emf.createEntityManager();
        FulltimeEmployee fromDb = em.find(FulltimeEmployee.class, emp.getId());
        fromDb.setName(emp.getName());
        fromDb.setSalary(emp.getSalary());
        em.getTransaction().begin();
        try {
            em.persist(fromDb);
            em.getTransaction().commit();
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
            return 0;
        } finally {
            em.close();
        }
    }
        
    public static int removeFulltimeEmployee(FulltimeEmployee emp) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("InheritanceORMPU");
        EntityManager em = emf.createEntityManager();
        FulltimeEmployee fromDb = em.find(FulltimeEmployee.class, emp.getId());
        em.getTransaction().begin();
        try {
            em.remove(fromDb);
            em.getTransaction().commit();
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
            return 0;
        } finally {
            em.close();
        }
                
    }
        
    public static FulltimeEmployee findFullTimeEmployeeById(Long id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("InheritanceORMPU");
        EntityManager em = emf.createEntityManager();
        FulltimeEmployee emp = em.find(FulltimeEmployee.class, id);
        em.close();
        return emp;
    }
    
    public static int updateParttimeEmployee(ParttimeEmployee emp) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("InheritanceORMPU");
        EntityManager em = emf.createEntityManager();
        ParttimeEmployee fromDb = em.find(ParttimeEmployee.class, emp.getId());
        fromDb.setName(emp.getName());
        fromDb.setHourWork(emp.getHourWork());
        em.getTransaction().begin();
        try {
            em.persist(fromDb);
            em.getTransaction().commit();
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
            return 0;
        } finally {
            em.close();
        }
    }
        
    public static int removeParttimeEmployee(ParttimeEmployee emp) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("InheritanceORMPU");
        EntityManager em = emf.createEntityManager();
        FulltimeEmployee fromDb = em.find(FulltimeEmployee.class, emp.getId());
        em.getTransaction().begin();
        try {
            em.remove(fromDb);
            em.getTransaction().commit();
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
            return 0;
        } finally {
            em.close();
        }
                
    }
        
    public static ParttimeEmployee findPartTimeEmployeeById(Long id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("InheritanceORMPU");
        EntityManager em = emf.createEntityManager();
        ParttimeEmployee emp = em.find(ParttimeEmployee.class, id);
        em.close();
        return emp;
    }
    
    
    
}
