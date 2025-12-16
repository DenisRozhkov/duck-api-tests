package autotests.tests;

import autotests.clients.DuckFlyClient;
import autotests.payloads.DuckMessage;
import autotests.payloads.DuckProperties;
import com.consol.citrus.TestCaseRunner;
import com.consol.citrus.annotations.CitrusResource;
import com.consol.citrus.annotations.CitrusTest;
import com.consol.citrus.testng.spring.TestNGCitrusSpringSupport;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.testng.annotations.Optional;
import org.testng.annotations.Test;

import static com.consol.citrus.http.actions.HttpActionBuilder.http;

public class DuckFlyTest extends DuckFlyClient {

    @Test
    @CitrusTest
    public void flyWithActiveWings(@Optional @CitrusResource TestCaseRunner runner) {
        // Существующий id с активными крыльями
        createDuck(runner, "yellow", 0.15, "rubber", "quack", "ACTIVE");
        extractId(runner);
        DuckMessage duckMessage = new DuckMessage();
        duckMessage.setMessage("I'm flying");
        duckFly(runner, "${duckId}");
        duckFlyValidate(runner,duckMessage);

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