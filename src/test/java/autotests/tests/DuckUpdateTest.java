package autotests.tests;

import autotests.clients.DuckUpdateClient;
import com.consol.citrus.TestCaseRunner;
import com.consol.citrus.annotations.CitrusResource;
import com.consol.citrus.annotations.CitrusTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.annotations.Optional;
import org.testng.annotations.Test;

import static com.consol.citrus.container.FinallySequence.Builder.doFinally;

@Epic("Тесты на duck-controller")
@Feature("Обновление свойств уточки")
public class DuckUpdateTest extends DuckUpdateClient {

    @Test(description = "Изменение цвета и высоты уточки")
    @CitrusTest
    public void updateColorAndHeight(@Optional @CitrusResource TestCaseRunner runner) {
        runner.variable("duckId","1");
        runner.$(doFinally().actions(context ->
                databaseUpdate(runner, "DELETE FROM DUCK WHERE ID=${duckId}")));
        databaseUpdate(runner,
                "insert into DUCK (id, color, height, material, sound, wings_state)\n" +
                        "values (${duckId}, 'yellow', 0.15, 'rubber', 'quack','ACTIVE');");
        duckUpdate(runner,"${duckId}","10","blue","rubber","quack");
        duckUpdateValidate(runner,"{\"message\":\"Duck with id = 1 is updated\"}");
        validateDuckInDatabase(runner,"${duckId}","blue","10","rubber","quack","ACTIVE");
    }

    @Test(description = "Изменение цвета и звука уточки")
    @CitrusTest
    public void updateColorAndSound(@Optional @CitrusResource TestCaseRunner runner) {
        runner.variable("duckId","1");
        runner.$(doFinally().actions(context ->
                databaseUpdate(runner, "DELETE FROM DUCK WHERE ID=${duckId}")));
        databaseUpdate(runner,
                "insert into DUCK (id, color, height, material, sound, wings_state)\n" +
                        "values (${duckId}, 'yellow', 0.15, 'rubber', 'quack','ACTIVE');");
        duckUpdate(runner,"${duckId}","0.15","blue","rubber","krya");
        duckUpdateValidate(runner,"{\"message\":\"Duck with id = 1 is updated\"}");
        validateDuckInDatabase(runner,"${duckId}","blue","0.15","rubber","krya","ACTIVE");
    }
}