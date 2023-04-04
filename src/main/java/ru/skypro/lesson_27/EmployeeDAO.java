package ru.skypro.lesson_27;

import java.util.List;
import java.util.Optional;

public interface EmployeeDAO {

    public void createEmployee(Employee employee);

    public Optional<Employee> getEmployeeByID(int id);

    public List<Employee> getAllEmployees();
    public void update(int id, Employee employee);

    public void deleteEmployee(int id);

    ///////////////
    default public void printAllEmployees() {
        List<Employee> list = this.getAllEmployees();
        if (list.isEmpty()) {
            System.out.println("Employees is EMPTY");
            return;
        }
        for (Employee employee : list) {
            System.out.println(employee);
        }
    }
}
