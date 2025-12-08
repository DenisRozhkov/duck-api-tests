package autotests;

import com.consol.citrus.TestCaseRunner;
import com.consol.citrus.annotations.CitrusResource;
import com.consol.citrus.annotations.CitrusTest;
import com.consol.citrus.testng.spring.TestNGCitrusSpringSupport;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.testng.annotations.Optional;
import org.testng.annotations.Test;

import static com.consol.citrus.http.actions.HttpActionBuilder.http;

public class DuckQuackTest extends TestNGCitrusSpringSupport {
    public void duckQuack(TestCaseRunner runner, String id) {
        runner.$(http()
                .client("http://localhost:2222")
                .send()
                .get("/api/duck/action/quack")
                .queryParam("id", id));
    }

    public void duckQuackValidate(TestCaseRunner runner, String body) {
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
    public void quackOddId(@Optional @CitrusResource TestCaseRunner runner) {
        // Корректный нечётный id, корректный звук
        duckQuack(runner,"1");
        duckQuackValidate(runner,"{\"message\":\"Quack!\"}");
    }

    @Test
    @CitrusTest
    public void quackEvenId(@Optional @CitrusResource TestCaseRunner runner) {
        // Корректный чётный id, корректный звук
        duckQuack(runner,"2");
        duckQuackValidate(runner,"{\"message\":\"Quack!\"}");
    }
}