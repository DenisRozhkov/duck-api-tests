package autotests.clients;

import autotests.EndpointConfig;
import com.consol.citrus.TestCaseRunner;
import com.consol.citrus.http.client.HttpClient;
import com.consol.citrus.testng.spring.TestNGCitrusSpringSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;

import static com.consol.citrus.http.actions.HttpActionBuilder.http;

@ContextConfiguration(classes = {EndpointConfig.class})
public class DuckDeleteClient extends TestNGCitrusSpringSupport {
    @Autowired
    protected HttpClient duckService;

    public void deleteDuck(TestCaseRunner runner, String id){
        runner.$(http()
                .client(duckService)
                .send()
                .delete("/api/duck/delete")
                .queryParam("id", id));
    }

    public void deleteDuckValidate(TestCaseRunner runner){
        runner.$(http()
                .client(duckService)
                .receive()
                .response(HttpStatus.OK));
    }
}
