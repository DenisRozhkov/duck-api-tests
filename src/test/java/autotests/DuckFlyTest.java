package autotests;

import com.consol.citrus.TestCaseRunner;
import com.consol.citrus.annotations.CitrusResource;
import com.consol.citrus.annotations.CitrusTest;
import com.consol.citrus.testng.spring.TestNGCitrusSpringSupport;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.testng.annotations.Optional;
import org.testng.annotations.Test;

import static com.consol.citrus.dsl.MessageSupport.MessageBodySupport.fromBody;
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

    public void createDuck(TestCaseRunner runner, String color, double height, String material,
                           String sound, String wingsState) {
        runner.$(http()
                .client("http://localhost:2222")
                .send()
                .post("/api/duck/create")
                .message()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body("{\n" +
                        "\"color\": \"" + color + "\",\n" +
                        "\"height\": " + height + ",\n" +
                        "\"material\": \"" + material + "\",\n" +
                        "\"sound\": \"" + sound + "\",\n" +
                        "\"wingsState\": \"" + wingsState + "\"\n" + "}"));
    }

    public void extractId(TestCaseRunner runner) {
        runner.$(http()
                .client("http://localhost:2222")
                .receive()
                .response(HttpStatus.OK)
                .message()
                .extract(fromBody().expression("$.id", "duckId")));
    }

    @Test
    @CitrusTest
    public void flyWithActiveWings(@Optional @CitrusResource TestCaseRunner runner) {
        // Существующий id с активными крыльями
        createDuck(runner, "yellow", 0.15, "rubber", "quack", "ACTIVE");
        extractId(runner);
        duckFly(runner, "${duckId}");
        duckFlyValidate(runner,"{\"message\":\"I'm flying\"}");

    }

    @Test
    @CitrusTest
    public void flyWithFixedWings(@Optional @CitrusResource TestCaseRunner runner) {
        // Существующий id со связанными крыльями
        createDuck(runner, "yellow", 0.15, "rubber", "quack", "FIXED");
        extractId(runner);
        duckFly(runner, "${duckId}");
        duckFlyValidate(runner,"{\"message\":\"I can't fly\"}");
    }

    @Test
    @CitrusTest
    public void flyWithUndefinedWings(@Optional @CitrusResource TestCaseRunner runner) {
        // Существующий id с крыльями в неопределенном состоянии
        createDuck(runner, "yellow", 0.15, "rubber", "quack", "UNDEFINED");
        extractId(runner);
        duckFly(runner, "${duckId}");
        duckFlyValidate(runner,"{\"message\":\"Wings are not detected\"}");
    }
}