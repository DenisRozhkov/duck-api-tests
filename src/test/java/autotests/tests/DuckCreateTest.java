package autotests.tests;

import autotests.clients.DuckCreateClient;
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

public class DuckCreateTest extends DuckCreateClient {
    @Test(description = "Создание утки с material = rubber ")
    @CitrusTest
    public void createMaterialRubber(@Optional @CitrusResource TestCaseRunner runner) {
        DuckProperties duckProperties = new DuckProperties();
        duckProperties.setColor("yellow");
        duckProperties.setHeight(0.15);
        duckProperties.setMaterial("rubber");
        duckProperties.setSound("quack");
        duckProperties.setWingsState("ACTIVE");
        createDuck(runner, duckProperties);
        duckCreateValidateJson(runner, "duckCreateTest/duckYellow.json");
        extractId(runner);
        duckProperties(runner,"${duckId}");
        duckPropertiesValidate(runner,"{\n"
                + "  \"color\": \"" + "yellow" + "\",\n"
                + "  \"height\": " + 0.15 + ",\n"
                + "  \"material\": \"" + "rubber" + "\",\n"
                + "  \"sound\": \"" + "quack" + "\",\n"
                + "  \"wingsState\": \"" + "ACTIVE"
                + "\"\n" + "}");
    }

    @Test(description = "Создание утки с material = wood ")
    @CitrusTest
    public void createMaterialWood(@Optional @CitrusResource TestCaseRunner runner) {
        DuckProperties duckProperties = new DuckProperties();
        duckProperties.setColor("yellow");
        duckProperties.setHeight(0.15);
        duckProperties.setMaterial("wood");
        duckProperties.setSound("quack");
        duckProperties.setWingsState("ACTIVE");
        createDuck(runner, duckProperties);
        duckCreateValidateJson(runner, "{\n"
                + "  \"color\": \"" + "yellow" + "\",\n"
                + "  \"height\": " + 0.15 + ",\n"
                + "  \"material\": \"" + "wood" + "\",\n"
                + "  \"sound\": \"" + "quack" + "\",\n"
                + "  \"wingsState\": \"" + "ACTIVE"
                + "\"\n" + "}");
        extractId(runner);
        duckProperties(runner,"${duckId}");
        duckPropertiesValidate(runner,"{\n"
                + "  \"color\": \"" + "yellow" + "\",\n"
                + "  \"height\": " + 0.15 + ",\n"
                + "  \"material\": \"" + "rubber" + "\",\n"
                + "  \"sound\": \"" + "quack" + "\",\n"
                + "  \"wingsState\": \"" + "ACTIVE"
                + "\"\n" + "}");
    }

}
