package autotests.tests;

import autotests.clients.DuckCreateClient;
import autotests.payloads.DuckProperties;
import com.consol.citrus.TestCaseRunner;
import com.consol.citrus.annotations.CitrusResource;
import com.consol.citrus.annotations.CitrusTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.annotations.Optional;
import org.testng.annotations.Test;

@Epic("Тесты на duck-controller")
@Feature("Создание уточки")
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
        validateDuckInDatabase(runner,"${duckId}","yellow","0.15","rubber","quack","ACTIVE");
    }

}
