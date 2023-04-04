package ru.skypro.lesson_27;

import javax.sound.midi.Soundbank;
import java.sql.*;
import java.util.*;

public class EmployeeDAOImpl implements EmployeeDAO{
    private static Connection getConnection() throws SQLException{
        final String user = "postgres";
        final String password = "adminsql";
        final String url = "jdbc:postgresql://localhost:5432/skypro";
        return DriverManager.getConnection(url, user, password);
    }
    @Override
    public void createEmployee(Employee employee) {
        String query = "INSERT INTO employee (first_name, last_name, gender, age, city_id) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = getConnection();) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,employee.getFirst_name());
            preparedStatement.setString(2,employee.getLast_name());
            preparedStatement.setString(3,employee.getGender());
            preparedStatement.setInt(4,employee.getAge());
            preparedStatement.setInt(5,employee.getCity_id());

            int result = preparedStatement.executeUpdate();
            if (result == 1) {
                System.out.println("Employee Create");
            }else {
                System.out.println("Unsuccess add new Employee");
            }

        } catch (SQLException e) {
            System.out.println("SQL Error in method createEmployee");
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Employee> getEmployeeByID(int id) {
        final String query = "SELECT * FROM employee WHERE id = ?";
        try (Connection connection = getConnection(); ) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();

            String firstName = resultSet.getString("first_name");
            String lastName = resultSet.getString("last_name");
            String gender = resultSet.getString("gender");
            int age = resultSet.getInt("age");
            int city_id = resultSet.getInt("city_id");

            Employee employee = new Employee(id, firstName, lastName, gender, age, city_id);
            return Optional.ofNullable(employee);

        } catch (SQLException e) {
            System.err.println("SQL ERRROR in getEmployeeByID");
            e.printStackTrace();
            return Optional.empty();
        }

    }

    @Override
    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();

        try (final Connection collection = getConnection();
             PreparedStatement preparedStatement = collection.prepareStatement("SELECT * FROM employee")) {

            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                String firstName = result.getString("first_name");
                String lastName = result.getString("last_name");
                int age = result.getInt("age");
                int id = result.getInt("id");
                String gender  = result.getString("gender");
                int cityId = result.getInt("city_id");
                employees.add(new Employee(id,firstName,lastName,gender,age,cityId));
            }

        } catch (SQLException e) {
            System.out.println("SQL ERROR in getAllEmployee");
            e.printStackTrace();
        }


        return employees;
    }

    @Override
    public void update(int id, Employee employee) {
        String query = "UPDATE employee SET first_name= ?, last_name = ?, gender = ?, age = ?, city_id = ? WHERE id = ?";
        try (Connection connection = getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,employee.getFirst_name());
            preparedStatement.setString(2,employee.getLast_name());
            preparedStatement.setString(3,employee.getGender());
            preparedStatement.setInt(4,employee.getAge());
            preparedStatement.setInt(5,employee.getCity_id());
            preparedStatement.setInt(6,id);
            int success = preparedStatement.executeUpdate();
            if (success == 1) {
                System.out.println("Employee with id="+id+" UPDATE");
            }else{
                System.out.println("UNSECCESS UPDATE employee with id="+id);
            }
        } catch (SQLException e) {
            System.err.println("SQL Error in update");
            e.printStackTrace();
        }
    }

    @Override
    public void deleteEmployee(int id) {
        String query = "DELETE FROM employee WHERE id = ?";
        try (Connection connection = getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,id);
            int success = preparedStatement.executeUpdate();
            if (success == 1) {
                System.out.println("Employee with id="+id+" deleted");
            }else {
                System.out.println("NOT FOUND employee with id="+id+" for delete");
            }
        } catch (SQLException e) {
            System.err.println("SQL Error in deleteEmployee");
            e.printStackTrace();
        }
    }


}
