package springBootTest.repos;

import org.springframework.data.repository.CrudRepository;
import springBootTest.model.User;

import java.util.List;

public interface UserRepo extends CrudRepository<User, Long> {
    List<User> findUserByName(String secondName);
    List<User> findUserByEmail(String email);
}