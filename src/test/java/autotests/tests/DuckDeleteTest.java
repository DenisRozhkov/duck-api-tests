package autotests.tests;

import autotests.clients.DuckDeleteClient;
import com.consol.citrus.TestCaseRunner;
import com.consol.citrus.annotations.CitrusResource;
import com.consol.citrus.annotations.CitrusTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.annotations.Optional;
import org.testng.annotations.Test;

import static com.consol.citrus.container.FinallySequence.Builder.doFinally;

@Epic("Тесты на duck-controller")
@Feature("Удаление уточки")
public class DuckDeleteTest extends DuckDeleteClient {

    @Test(description = "Удаление")
    @CitrusTest
    public void deleteDuck(@Optional @CitrusResource TestCaseRunner runner) {
        runner.variable("duckId","1");
        runner.$(doFinally().actions(context ->
                databaseUpdate(runner, "DELETE FROM DUCK WHERE ID=${duckId}")));
        databaseUpdate(runner,
                "insert into DUCK (id, color, height, material, sound, wings_state)\n" +
                        "values (${duckId}, 'orange', 3.0, 'cheese', 'hrum','ACTIVE');");
        deleteDuck(runner, "${duckId}");
        deleteDuckValidate(runner,"{\"message\":\"Duck with id = 1 is deleted\"}");
        validateCountInDatabase(runner,"${duckId}","0");
    }
}