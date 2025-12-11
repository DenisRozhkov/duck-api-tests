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
        DuckMessage duckMessage = new DuckMessage();
        duckMessage.setMessage("I'm flying");
        duckFly(runner, "1");
        duckFlyValidate(runner,duckMessage);

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