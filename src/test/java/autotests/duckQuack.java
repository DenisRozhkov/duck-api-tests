package autotests;

import com.consol.citrus.TestCaseRunner;
import com.consol.citrus.annotations.CitrusResource;
import com.consol.citrus.annotations.CitrusTest;
import com.consol.citrus.testng.spring.TestNGCitrusSpringSupport;
import org.springframework.http.HttpStatus;
import org.testng.annotations.Optional;
import org.testng.annotations.Test;

import static com.consol.citrus.http.actions.HttpActionBuilder.http;

public class DuckQuackTest extends TestNGCitrusSpringSupport {

    @Test
    @CitrusTest
    public void quackOddId(@Optional @CitrusResource TestCaseRunner runner) {
        // Корректный нечётный id, корректный звук
        runner.$(http()
                .client("http://localhost:2222")
                .send()
                .get("/api/duck/action/quack")
                .queryParam("id", "1"));

        runner.$(http()
                .client("http://localhost:2222")
                .receive()
                .response(HttpStatus.OK)
                .body("{\"message\":\"Quack!\"}"));
    }

    @Test
    @CitrusTest
    public void quackEvenId(@Optional @CitrusResource TestCaseRunner runner) {
        // Корректный чётный id, корректный звук
        runner.$(http()
                .client("http://localhost:2222")
                .send()
                .get("/api/duck/action/quack")
                .queryParam("id", "2"));

        runner.$(http()
                .client("http://localhost:2222")
                .receive()
                .response(HttpStatus.OK)
                .body("{\"message\":\"Quack!\"}"));
    }
}