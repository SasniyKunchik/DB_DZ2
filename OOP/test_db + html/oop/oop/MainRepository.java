package oop;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class MainRepository {

    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASSWORD = "postgres";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/ORIS";

    public static void main(String[] args) throws Exception {
        Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        List<User> users = new ArrayList<>();
        UserRepository userRepository = new UsersRepositoryJdbcImpl(connection);
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().equals("q")) break;
                if (line.trim().isEmpty()) continue;
                String[] parts = line.split(" ");

                if (parts.length != 4) {
                    System.err.println("Неверный формат строки: " + line);
                    continue;
                }

                try {
                    long id = Long.parseLong(parts[0]);
                    String firstName = parts[1];
                    String lastName = parts[2];
                    int age = Integer.parseInt(parts[3]);

                    User user = new User(id, firstName, lastName, age);
                    users.add(user);

                } catch (NumberFormatException e) {
                    System.err.println("Неверный формат чисел в строке: " + line);
                }
            }
            if (users != null && !users.isEmpty()) {
                System.out.println(userRepository.OneRequestSave(users));
            }

            userRepository.findAll().forEach(user -> System.out.println(user.getFirtstName()));

            userRepository.findAllByAge(30).forEach(user -> System.out.println(user.getFirtstName()));
        }
    }
}
