package autotests.tests;

import autotests.clients.DuckQuackClient;
import com.consol.citrus.TestCaseRunner;
import com.consol.citrus.annotations.CitrusResource;
import com.consol.citrus.annotations.CitrusTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.annotations.Optional;
import org.testng.annotations.Test;

import static com.consol.citrus.container.FinallySequence.Builder.doFinally;

@Epic("Тесты на duck-action-controller")
@Feature("Метод 'кря'")
public class DuckQuackTest extends DuckQuackClient {

    @Test(description = "Корректный нечётный id, корректный звук")
    @CitrusTest
    public void quackOddId(@Optional @CitrusResource TestCaseRunner runner) {
        runner.variable("duckId","1");
        runner.$(doFinally().actions(context ->
                databaseUpdate(runner, "DELETE FROM DUCK WHERE ID=${duckId}")));
        databaseUpdate(runner,
                "insert into DUCK (id, color, height, material, sound, wings_state)\n" +
                        "values (${duckId}, 'yellow', 0.15, 'rubber', 'quack','ACTIVE');");
        duckQuack(runner,"${duckId}","3","2");
        duckQuackValidate(runner,"{\"sound\":\"quack-quack, quack-quack, quack-quack\"}");
    }

    @Test(description = "Корректный чётный id, корректный звук")
    @CitrusTest
    public void quackEvenId(@Optional @CitrusResource TestCaseRunner runner) {
        runner.variable("duckId","2");
        runner.$(doFinally().actions(context ->
                databaseUpdate(runner, "DELETE FROM DUCK WHERE ID=${duckId}")));
        databaseUpdate(runner,
                "insert into DUCK (id, color, height, material, sound, wings_state)\n" +
                        "values (${duckId}, 'yellow', 0.15, 'rubber', 'quack','ACTIVE');");
        duckQuack(runner,"${duckId}","3","2");
        duckQuackValidate(runner,"{\"sound\":\"quack-quack, quack-quack, quack-quack\"}");
    }
}