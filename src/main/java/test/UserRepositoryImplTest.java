package test;

import com.violence.repository.UserRepository;
import com.violence.repository.UserRepositoryImpl;
import org.junit.Assert;
import org.junit.Test;

/**
 * created by user violence
 * created on 31.01.2019
 * class created for project SecurityExample
 */


public class UserRepositoryImplTest {
    @Test()
    public void getAllUser() {
        UserRepository userRepository = new UserRepositoryImpl();
        Assert.assertNotNull(userRepository.getAll().get(0));
    }
}