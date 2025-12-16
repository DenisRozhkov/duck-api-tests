package autotests.tests;

import autotests.clients.DuckSwimClient;
import com.consol.citrus.TestCaseRunner;
import com.consol.citrus.annotations.CitrusResource;
import com.consol.citrus.annotations.CitrusTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.annotations.Optional;
import org.testng.annotations.Test;

import static com.consol.citrus.container.FinallySequence.Builder.doFinally;

@Epic("Тесты на duck-action-controller")
@Feature("Метод 'плыть'")
public class DuckSwimTest extends DuckSwimClient {

    @Test(description = "Существующий id")
    @CitrusTest
    public void swimExistingId(@Optional @CitrusResource TestCaseRunner runner) {
        runner.variable("duckId","1");
        runner.$(doFinally().actions(context ->
                databaseUpdate(runner, "DELETE FROM DUCK WHERE ID=${duckId}")));
        databaseUpdate(runner,
                "insert into DUCK (id, color, height, material, sound, wings_state)\n" +
                        "values (${duckId}, 'yellow', 0.15, 'rubber', 'quack','ACTIVE');");
        duckSwim(runner,"${duckId}");
        duckSwimValidate(runner,"{\"message\":\"I'm swimming\"}");
    }

    @Test(description = "Несуществующий id")
    @CitrusTest
    public void swimNonExistingId(@Optional @CitrusResource TestCaseRunner runner) {
        duckSwim(runner,"999");
        duckSwimValidate(runner,"{\"message\":\"Duck with id = 999 is not found\"}");
    }
}