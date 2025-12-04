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

    @Test
    @CitrusTest
    public void updateColorAndHeight(@Optional @CitrusResource TestCaseRunner runner) {
        // Изменить цвет и высоту уточки
        runner.$(http()
                .client("http://localhost:2222")
                .send()
                .put("/api/duck/update")
                .queryParam("id", "1")
                .message()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body("{\"color\":\"red\",\"height\":0.25}"));

        runner.$(http()
                .client("http://localhost:2222")
                .receive()
                .response(HttpStatus.OK));
    }

    @Test
    @CitrusTest
    public void updateColorAndSound(@Optional @CitrusResource TestCaseRunner runner) {
        // Изменить цвет и звук уточки
        runner.$(http()
                .client("http://localhost:2222")
                .send()
                .put("/api/duck/update")
                .queryParam("id", "1")
                .message()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body("{\"color\":\"blue\",\"sound\":\"quack-quack\"}"));

        runner.$(http()
                .client("http://localhost:2222")
                .receive()
                .response(HttpStatus.OK));
    }
}