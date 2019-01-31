package test;

import com.violence.repository.UserRepository;
import com.violence.repository.UserRepositoryImpl;
import org.junit.Assert;
import org.junit.Test;

public class UserRepositoryImplTest {
    @Test()
    public void getAllUser() {
        UserRepository userRepository = new UserRepositoryImpl();
        Assert.assertNotNull(userRepository.getAll().get(0));
    }
}