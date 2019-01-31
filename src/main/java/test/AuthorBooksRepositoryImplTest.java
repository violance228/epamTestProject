package test;

import com.violence.repository.AuthorBooksRepository;
import com.violence.repository.AuthorBooksRepositoryImpl;
import org.junit.Assert;
import org.junit.Test;

/**
 * created by user violence
 * created on 31.01.2019
 * class created for project library
 */


public class AuthorBooksRepositoryImplTest {

    @Test
    public void getById() {
        AuthorBooksRepository authorBooksRepository = new AuthorBooksRepositoryImpl();

    }

    @Test
    public void getAllCheckNotNullResult() {
        AuthorBooksRepository authorBooksRepository = new AuthorBooksRepositoryImpl();
        Assert.assertNotNull(authorBooksRepository.getAll().get(0));
    }
}