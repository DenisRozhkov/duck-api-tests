package autotests.clients;

import com.consol.citrus.http.client.HttpClient;
import com.consol.citrus.testng.spring.TestNGCitrusSpringSupport;
import org.springframework.beans.factory.annotation.Autowired;

public class DuckCreateClient extends TestNGCitrusSpringSupport {
    @Autowired
    protected HttpClient duckService;
}
