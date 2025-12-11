package autotests.tests;

import com.consol.citrus.TestCaseRunner;
import com.consol.citrus.annotations.CitrusResource;
import com.consol.citrus.annotations.CitrusTest;
import com.consol.citrus.testng.spring.TestNGCitrusSpringSupport;
import org.springframework.http.HttpStatus;
import org.testng.annotations.Optional;
import org.testng.annotations.Test;

import static com.consol.citrus.http.actions.HttpActionBuilder.http;

public class DuckDeleteTest extends TestNGCitrusSpringSupport {

    @Test
    @CitrusTest
    public void deleteDuck(@Optional @CitrusResource TestCaseRunner runner) {
        runner.$(http()
                .client("http://localhost:2222")
                .send()
                .delete("/api/duck/delete")
                .queryParam("id", "1"));

        runner.$(http()
                .client("http://localhost:2222")
                .receive()
                .response(HttpStatus.OK));
    }
}