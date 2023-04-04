package ru.skypro.lesson_27;
import java.sql.*;
import java.util.List;

public class Application {

    public static void main(String [] args){

        System.out.println(" ------------ First Task ---------------------");
        final String user = "postgres";
        final String password = "adminsql";
        final String url = "jdbc:postgresql://localhost:5432/skypro";

        try (final Connection connection = DriverManager.getConnection(url,user,password); PreparedStatement preparedStatement
                = connection.prepareStatement("SELECT first_name,last_name, gender, age, city_name FROM employee" +
                " JOIN city ON employee.city_id=city.city_id WHERE id=1");) {

            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String gender = resultSet.getString("gender");
                int age = resultSet.getInt("age");
                String city = resultSet.getString("city_name");
                System.out.printf("%s %s:  %d лет - %s  -  %s",firstName,lastName,age,city,gender);
                System.out.println();
            }

        } catch (SQLException e) {
            System.out.println("Ошибка подключинея к БД");
            e.printStackTrace();
        }
        System.out.println(" ------------ Second Task ---------------------");
        EmployeeDAO employeeDAO = new EmployeeDAOImpl();
        Employee employee = new Employee("first_name", "last_name", "male", 30, 1);
        employeeDAO.createEmployee(employee);
        Employee employee2 = new Employee("new_first_name", "new_last_name", "female", 35, 2);
        employeeDAO.update(19,employee2);
        employeeDAO.getAllEmployees();
        employeeDAO.printAllEmployees();
        System.out.println("Employee with id==1 :"+employeeDAO.getEmployeeByID(1));
        employeeDAO.deleteEmployee(20);
    }
}
