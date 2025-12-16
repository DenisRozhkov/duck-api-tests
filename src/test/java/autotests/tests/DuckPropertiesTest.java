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

import java.util.concurrent.atomic.AtomicInteger;

import static com.consol.citrus.http.actions.HttpActionBuilder.http;

public class DuckPropertiesTest extends DuckPropertiesClient {

    @Test
    @CitrusTest
    public void getWoodDuckPropertiesEvenId(@Optional @CitrusResource TestCaseRunner runner) {
        // Четный ID: утка с material = wood
        AtomicInteger id = new AtomicInteger();
        do{
            createDuck(runner, "yellow", 0.15, "wood", "quack", "ACTIVE");
            extractId(runner);

            runner.$(a -> {
                id.set(Integer.parseInt(a.getVariable("duckId")));
            });
        }while (id.get() % 2 !=0);
        duckProperties(runner,"${duckId}");
        duckPropertiesValidate(runner, "{\n"
                + "  \"color\": \"" + "yellow" + "\",\n"
                + "  \"height\": " + 0.15 + ",\n"
                + "  \"material\": \"" + "wood" + "\",\n"
                + "  \"sound\": \"" + "quack" + "\",\n"
                + "  \"wingsState\": \"" + "ACTIVE"
                + "\"\n" + "}");
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
        AtomicInteger id = new AtomicInteger();
        do{
            createDuck(runner, "yellow", 0.15, "rubber", "quack", "ACTIVE");
            extractId(runner);

            runner.$(a -> {
                id.set(Integer.parseInt(a.getVariable("duckId")));
            });
        }while (id.get() % 2 !=1);
        duckProperties(runner,"${duckId}");
        duckPropertiesValidate(runner, duckProperties);
    }
}