package autotests.tests;

import autotests.clients.DuckPropertiesClient;
import autotests.payloads.DuckProperties;
import com.consol.citrus.TestCaseRunner;
import com.consol.citrus.annotations.CitrusResource;
import com.consol.citrus.annotations.CitrusTest;
import com.consol.citrus.testng.spring.TestNGCitrusSpringSupport;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.testng.annotations.Optional;
import org.testng.annotations.Test;

import static com.consol.citrus.container.FinallySequence.Builder.doFinally;
import static com.consol.citrus.http.actions.HttpActionBuilder.http;

@Epic("Тесты на duck-action-controller")
@Feature("Получение свойств")
public class DuckPropertiesTest extends DuckPropertiesClient {

    @Test(description = "Четный ID: утка с material = wood")
    @CitrusTest
    public void getWoodDuckPropertiesEvenId(@Optional @CitrusResource TestCaseRunner runner) {
        runner.variable("duckId","2");
        runner.$(doFinally().actions(context ->
                databaseUpdate(runner, "DELETE FROM DUCK WHERE ID=${duckId}")));
        databaseUpdate(runner,
                "insert into DUCK (id, color, height, material, sound, wings_state)\n" +
                        "values (${duckId}, 'yellow', 0.15, 'wood', 'quack','ACTIVE');");
        DuckProperties duckProperties = new DuckProperties();
        duckProperties.setColor("yellow");
        duckProperties.setHeight(0.15);
        duckProperties.setMaterial("wood");
        duckProperties.setSound("quack");
        duckProperties.setWingsState("ACTIVE");
        duckProperties(runner, "${duckId}");
        duckPropertiesValidate(runner, duckProperties);
    }

    @Test(description = "Нечетный ID: утка с material = rubber")
    @CitrusTest
    public void getRubberDuckPropertiesOddId(@Optional @CitrusResource TestCaseRunner runner) {
        runner.variable("duckId","1");
        runner.$(doFinally().actions(context ->
                databaseUpdate(runner, "DELETE FROM DUCK WHERE ID=${duckId}")));
        databaseUpdate(runner,
                "insert into DUCK (id, color, height, material, sound, wings_state)\n" +
                        "values (${duckId}, 'yellow', 0.15, 'rubber', 'quack','ACTIVE');");
        DuckProperties duckProperties = new DuckProperties();
        duckProperties.setColor("yellow");
        duckProperties.setHeight(0.15);
        duckProperties.setMaterial("rubber");
        duckProperties.setSound("quack");
        duckProperties.setWingsState("ACTIVE");
        duckProperties(runner, "${duckId}");
        duckPropertiesValidate(runner, duckProperties);
    }
}