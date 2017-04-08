package poc.springbootexample.models.User;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by norner on 11/03/2017.
 */
public interface UserDao extends CrudRepository<User, Long>{
    /**
     * This method will find an User instance in the database by its email.
     * Note that this method is not implemented and its working code will be
     * automagically generated from its signature by Spring Data JPA.
     */
    public User findByEmail(String email);
}
