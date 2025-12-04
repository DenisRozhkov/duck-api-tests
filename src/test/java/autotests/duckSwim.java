package autotests;

import com.consol.citrus.TestCaseRunner;
import com.consol.citrus.annotations.CitrusResource;
import com.consol.citrus.annotations.CitrusTest;
import com.consol.citrus.testng.spring.TestNGCitrusSpringSupport;
import org.springframework.http.HttpStatus;
import org.testng.annotations.Optional;
import org.testng.annotations.Test;

import static com.consol.citrus.http.actions.HttpActionBuilder.http;

public class DuckSwimTest extends TestNGCitrusSpringSupport {

    @Test
    @CitrusTest
    public void swimExistingId(@Optional @CitrusResource TestCaseRunner runner) {
        // Существующий id
        runner.$(http()
                .client("http://localhost:2222")
                .send()
                .get("/api/duck/action/swim")
                .queryParam("id", "1"));

        runner.$(http()
                .client("http://localhost:2222")
                .receive()
                .response(HttpStatus.OK)
                .body("{\"message\":\"I'm swimming\"}"));
    }

    @Test
    @CitrusTest
    public void swimNonExistingId(@Optional @CitrusResource TestCaseRunner runner) {
        // Несуществующий id
        runner.$(http()
                .client("http://localhost:2222")
                .send()
                .get("/api/duck/action/swim")
                .queryParam("id", "999"));

        runner.$(http()
                .client("http://localhost:2222")
                .receive()
                .response(HttpStatus.NOT_FOUND));
    }
}