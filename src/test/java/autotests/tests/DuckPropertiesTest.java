package autotests.tests;

import autotests.clients.DuckPropertiesClient;
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

public class DuckPropertiesTest extends DuckPropertiesClient {

    @Test
    @CitrusTest
    public void getWoodDuckPropertiesEvenId(@Optional @CitrusResource TestCaseRunner runner) {
        // Четный ID: утка с material = wood
        duckProperties(runner, "2");
        duckPropertiesValidate(runner, "{\"material\":\"wood\"}");
    }

    @Test
    @CitrusTest
    public void getRubberDuckPropertiesOddId(@Optional @CitrusResource TestCaseRunner runner) {
        // Нечетный ID: утка с material = rubber
        DuckProperties duckProperties = new DuckProperties();
        duckProperties.setColor("yellow");
        duckProperties.setHeight(0.15);
        duckProperties.setMaterial("rubber");
        duckProperties.setSound("quack");
        duckProperties.setWingsState("ACTIVE");
        duckProperties(runner, "1");
        duckPropertiesValidate(runner, duckProperties);
    }
}