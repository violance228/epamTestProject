package test;

import com.violence.util.DataSourceConn;
import org.junit.Test;

import java.sql.Connection;

public class DataSourceConnTest {

    @Test(timeout = 3000)
    public void getPostgreSqlConnectionShouldReturnFasterThatOneSecond() {
        Connection connection = DataSourceConn.getPostgreSqlConnection();
        DataSourceConn.close(null, connection, null);
    }
}
