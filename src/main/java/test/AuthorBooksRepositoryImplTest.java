package test;

import com.violence.repository.AuthorBooksRepository;
import com.violence.repository.AuthorBooksRepositoryImpl;
import org.junit.Assert;
import org.junit.Test;

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