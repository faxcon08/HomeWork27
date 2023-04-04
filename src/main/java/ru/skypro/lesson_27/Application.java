package ru.skypro.lesson_27;
import java.sql.*;
public class Application {
    public static void main(String [] args) throws SQLException{
        final String user = "postgres";
        final String password = "adminsql";
        final String url = "jdbc:postgresql://localhost:5432/skypro";
        System.out.println("Hellow, World");
    }
}
