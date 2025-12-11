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

public class DuckSwimTest extends TestNGCitrusSpringSupport {

    public void duckSwim(TestCaseRunner runner, String id) {
        runner.$(http()
                .client("http://localhost:2222")
                .send()
                .get("/api/duck/action/swim")
                .queryParam("id", id));
    }

    public void duckSwimValidate(TestCaseRunner runner, String body) {
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
    public void swimExistingId(@Optional @CitrusResource TestCaseRunner runner) {
        // Существующий id
        duckSwim(runner,"1");
        duckSwimValidate(runner,"{\"message\":\"I'm swimming\"}");
    }

    @Test
    @CitrusTest
    public void swimNonExistingId(@Optional @CitrusResource TestCaseRunner runner) {
        // Несуществующий id
        duckSwim(runner,"999");
        duckSwimValidate(runner,"");
    }
}