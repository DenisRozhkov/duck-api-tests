package autotests;

import com.consol.citrus.http.client.HttpClient;
import com.consol.citrus.testng.spring.TestNGCitrusSpringSupport;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseTest extends TestNGCitrusSpringSupport {
    @Autowired
    protected HttpClient duckService;


}
