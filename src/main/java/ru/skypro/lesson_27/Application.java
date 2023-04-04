package ru.skypro.lesson_27;
import java.sql.*;
public class Application {
    public static void main(String [] args){
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
            }

        } catch (SQLException e) {
            System.out.println("Ошибка подключинея к БД");
            e.printStackTrace();
        }
    }
}
