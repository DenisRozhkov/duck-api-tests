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

public class DuckUpdateTest extends TestNGCitrusSpringSupport {

    public void duckUpdate(TestCaseRunner runner, String id, String body) {
        runner.$(http()
                .client("http://localhost:2222")
                .send()
                .put("/api/duck/update")
                .queryParam("id", id)
                .message()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(body));
    }

    @Test
    @CitrusTest
    public void updateColorAndHeight(@Optional @CitrusResource TestCaseRunner runner) {
        // Изменение цвета и высоты уточки
        duckUpdate(runner,"1","{\"color\":\"red\",\"height\":0.25}");
        runner.$(http()
                .client("http://localhost:2222")
                .receive()
                .response(HttpStatus.OK));
    }

    @Test
    @CitrusTest
    public void updateColorAndSound(@Optional @CitrusResource TestCaseRunner runner) {
        // Изменение цвета и звука уточки
        duckUpdate(runner,"1","{\"color\":\"blue\",\"sound\":\"quack-quack\"}");
        runner.$(http()
                .client("http://localhost:2222")
                .receive()
                .response(HttpStatus.OK));
    }
}