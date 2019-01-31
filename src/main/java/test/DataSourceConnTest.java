package test;

import com.violence.util.DataSourceConn;
import org.junit.Test;

import java.sql.Connection;

/**
 * created by user violence
 * created on 31.01.2019
 * class created for project SecurityExample
 */


public class DataSourceConnTest {

    @Test(timeout = 3000)
    public void getPostgreSqlConnectionShouldReturnFasterThatOneSecond() {
        Connection connection = DataSourceConn.getPostgreSqlConnection();
        DataSourceConn.close(null, connection, null);
    }
}
