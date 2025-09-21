package oop;

import java.sql.SQLException;
import java.util.List;

public interface UserRepository extends CrudRepository<User>{
    List<User> findAllByAge (Integer age);
    List<User> findAllByFirstName (String name);
    List<User> findAllByLastName (String name);
    Integer OneRequestSave(List<User> users) throws SQLException;


}
