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

public class DuckFlyTest extends TestNGCitrusSpringSupport {

    public void duckFly(TestCaseRunner runner, String id) {
        runner.$(http()
                .client("http://localhost:2222")
                .send()
                .get("/api/duck/action/fly")
                .queryParam("id", id));
    }

    public void duckFlyValidate(TestCaseRunner runner, String message) {
        runner.$(http()
                .client("http://localhost:2222")
                .receive()
                .response(HttpStatus.OK)
                .message()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(message));
    }

    @Test
    @CitrusTest
    public void flyWithActiveWings(@Optional @CitrusResource TestCaseRunner runner) {
        // Существующий id с активными крыльями
        duckFly(runner, "1");
        duckFlyValidate(runner,"{\"message\":\"I'm flying\"}");

    }

    @Test
    @CitrusTest
    public void flyWithFixedWings(@Optional @CitrusResource TestCaseRunner runner) {
        // Существующий id со связанными крыльями
        duckFly(runner, "2");
        duckFlyValidate(runner,"{\"message\":\"The wings were not found :(\"}");
    }

    @Test
    @CitrusTest
    public void flyWithUndefinedWings(@Optional @CitrusResource TestCaseRunner runner) {
        // Существующий id с крыльями в неопределенном состоянии
        duckFly(runner, "3");
        duckFlyValidate(runner,"{\"message\":\"The wings were not found :(\"}");
    }
}