package oop;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryJdbcImpl implements UserRepository {

    private Connection connection;

    private static final String SQL_REQUEST_ALL = "select id, first_name, last_name, age from driver";
    private static String SQL_REQUEST_BY_ID = "select id, first_name, last_name, age from driver where id = ?";
    private static String SQL_REQUEST_BY_FNAME = "select id, first_name, last_name, age from driver where first_name = ?";
    private static String SQL_REQUEST_BY_LNAME = "select id, first_name, last_name, age from driver where last_name = ?";
    private static String SQL_REQUEST_BY_AGEUP = "select id, first_name, last_name, age from driver where age >= ?";
    private static final String SQL_SAVE = "INSERT INTO driver (first_name, last_name, age) VALUES ?";
    public UsersRepositoryJdbcImpl(Connection connection) {this.connection = connection;}
    @Override
    public List<User> findAll() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(SQL_REQUEST_ALL);

        List<User> result = new ArrayList<>();

        while (resultSet.next()) {
            User user = new User(
                    resultSet.getLong(1),
                    resultSet.getString(2),
                    resultSet.getString("last_name"),
                    resultSet.getInt("age")
            );
            result.add(user);
        }
        statement.close();
        resultSet.close();
        return result;
    }

    @Override
    public Optional<User> findById(Long id) {
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement(SQL_REQUEST_BY_ID);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();


            while (resultSet.next()) {
                User user = new User(
                        resultSet.getLong(1),
                        resultSet.getString(2),
                        resultSet.getString("last_name"),
                        resultSet.getInt("age")
                );
                statement.close();
                resultSet.close();
                return Optional.of(user);
            }
            statement.close();
            resultSet.close();
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void save(User entity) {

    }

    @Override
    public void update(User entity) {

    }

    @Override
    public void remove(User entity) {

    }

    @Override
    public void removeById(Long id) {

    }

    @Override
    public List<User> findAllByAge(Integer age) {
        PreparedStatement statement;
        List<User> result = new ArrayList<>();
        try {

            statement = connection.prepareStatement(SQL_REQUEST_BY_AGEUP);
            statement.setInt(1, age);
            ResultSet resultSet = statement.executeQuery();



            while (resultSet.next()) {
                User user = new User(
                        resultSet.getLong(1),
                        resultSet.getString(2),
                        resultSet.getString("last_name"),
                        resultSet.getInt("age")
                );
                result.add(user);
            }
            statement.close();
            resultSet.close();
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> findAllByFirstName(String name) {
        PreparedStatement statement;
        List<User> result = new ArrayList<>();
        try {

            statement = connection.prepareStatement(SQL_REQUEST_BY_FNAME);
            String search = "%" + name + "%";
            statement.setString(1, search);
            ResultSet resultSet = statement.executeQuery(search);



            while (resultSet.next()) {
                User user = new User(
                        resultSet.getLong(1),
                        resultSet.getString(2),
                        resultSet.getString("last_name"),
                        resultSet.getInt("age")
                );
                result.add(user);
            }
            statement.close();
            resultSet.close();
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> findAllByLastName(String name) {
        PreparedStatement statement;
        List<User> result = new ArrayList<>();
        try {

            statement = connection.prepareStatement(SQL_REQUEST_BY_LNAME);
            String search = "%" + name + "%";
            statement.setString(1, search);
            ResultSet resultSet = statement.executeQuery(search);



            while (resultSet.next()) {
                User user = new User(
                        resultSet.getLong(1),
                        resultSet.getString(2),
                        resultSet.getString("last_name"),
                        resultSet.getInt("age")
                );
                result.add(user);
            }
            statement.close();
            resultSet.close();
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Integer OneRequestSave(List<User> users) throws SQLException {

        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO driver (first_name, last_name, age) VALUES ");

        for (int i = 0; i < users.size(); i++) {
            if (i > 0) sb.append(",");
            String safeName = users.get(i).getFirtstName().replace("'", "''");
            String safeLastName = users.get(i).getLastName().replace("'", "''");
            sb.append(String.format("('%s', '%s', %d)", safeName, safeLastName, users.get(i).getAge()));
        }
        String sql = sb.toString();
        Statement stmt = connection.createStatement();
        int rowsAffected = stmt.executeUpdate(sql);
        return rowsAffected;
    }
}
