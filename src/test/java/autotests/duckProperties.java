package autotests;

import com.consol.citrus.TestCaseRunner;
import com.consol.citrus.annotations.CitrusResource;
import com.consol.citrus.annotations.CitrusTest;
import com.consol.citrus.testng.spring.TestNGCitrusSpringSupport;
import org.springframework.http.HttpStatus;
import org.testng.annotations.Optional;
import org.testng.annotations.Test;

import static com.consol.citrus.http.actions.HttpActionBuilder.http;

public class DuckPropertiesTest extends TestNGCitrusSpringSupport {

    @Test
    @CitrusTest
    public void getWoodDuckPropertiesEvenId(@Optional @CitrusResource TestCaseRunner runner) {
        // Четный ID: утка с material = wood
        runner.$(http()
                .client("http://localhost:2222")
                .send()
                .get("/api/duck/properties/2"));

        runner.$(http()
                .client("http://localhost:2222")
                .receive()
                .response(HttpStatus.OK)
                .body("{\"material\":\"wood\"}"));
    }

    @Test
    @CitrusTest
    public void getRubberDuckPropertiesOddId(@Optional @CitrusResource TestCaseRunner runner) {
        // Нечетный ID: утка с material = rubber
        runner.$(http()
                .client("http://localhost:2222")
                .send()
                .get("/api/duck/properties/1"));

        runner.$(http()
                .client("http://localhost:2222")
                .receive()
                .response(HttpStatus.OK)
                .body("{\"material\":\"rubber\"}"));
    }
}