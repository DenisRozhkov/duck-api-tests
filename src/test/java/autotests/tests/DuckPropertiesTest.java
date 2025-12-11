package autotests.tests;

import com.consol.citrus.TestCaseRunner;
import com.consol.citrus.annotations.CitrusResource;
import com.consol.citrus.annotations.CitrusTest;
import com.consol.citrus.testng.spring.TestNGCitrusSpringSupport;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.testng.annotations.Optional;
import org.testng.annotations.Test;

import static com.consol.citrus.http.actions.HttpActionBuilder.http;

public class DuckPropertiesTest extends TestNGCitrusSpringSupport {
    public void duckProperties(TestCaseRunner runner, String id) {
        runner.$(http()
                .client("http://localhost:2222")
                .send()
                .get(id));
    }

    public void duckPropertiesValidate(TestCaseRunner runner, String body) {
        runner.$(http()
                .client("http://localhost:2222")
                .receive()
                .response(HttpStatus.OK)
                .message()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(body));
    }
    @Test
    @CitrusTest
    public void getWoodDuckPropertiesEvenId(@Optional @CitrusResource TestCaseRunner runner) {
        // Четный ID: утка с material = wood
        duckProperties(runner,"2");
        duckPropertiesValidate(runner,"{\"material\":\"wood\"}");
    }

    @Test
    @CitrusTest
    public void getRubberDuckPropertiesOddId(@Optional @CitrusResource TestCaseRunner runner) {
        // Нечетный ID: утка с material = rubber
        duckProperties(runner,"1");
        duckPropertiesValidate(runner,"{\"material\":\"rubber\"}");
    }
}