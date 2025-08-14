package com.task7;

import java.util.List;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
        EmployeeDAO dao = new EmployeeDAO();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- Employee Database Menu ---");
            System.out.println("1. Add Employee");
            System.out.println("2. View Employees");
            System.out.println("3. Update Employee");
            System.out.println("4. Delete Employee");
            System.out.println("5. Exit");
            System.out.print("Choose: ");
            choice = scanner.nextInt();
            
            switch (choice) {
            case 1:
                System.out.print("Name: ");
                scanner.nextLine();
                String name = scanner.nextLine();
                System.out.print("Email: ");
                String email = scanner.nextLine();
                System.out.print("Salary: ");
                double salary = scanner.nextDouble();
                dao.addEmployee(new Employee(name, email, salary));
                break;

            case 2:
                List<Employee> list = dao.getAllEmployees();
                for (Employee emp : list) {
                    System.out.printf("ID: %d, Name: %s, Email: %s, Salary: %.2f%n",
                            emp.getId(), emp.getName(), emp.getEmail(), emp.getSalary());
                }
                break;
                
            case 3:
                System.out.print("Employee ID to update: ");
                int idToUpdate = scanner.nextInt();
                scanner.nextLine();
                System.out.print("New Email: ");
                String newEmail = scanner.nextLine();
                System.out.print("New Salary: ");
                double newSalary = scanner.nextDouble();
                dao.updateEmployee(idToUpdate, newEmail, newSalary);
                break;

            case 4:
                System.out.print("Employee ID to delete: ");
                int idToDelete = scanner.nextInt();
                dao.deleteEmployee(idToDelete);
                break;

            case 5:
                System.out.println("Exiting...");
                break;
                
            default :
            	System.out.println("Choose in the given options only...!");
            	break;
        }

    } while (choice != 5);
}
}
