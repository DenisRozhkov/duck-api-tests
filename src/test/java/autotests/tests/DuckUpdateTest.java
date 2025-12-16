package autotests.tests;

import autotests.clients.DuckUpdateClient;
import com.consol.citrus.TestCaseRunner;
import com.consol.citrus.annotations.CitrusResource;
import com.consol.citrus.annotations.CitrusTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Test;

public class DuckUpdateTest extends DuckUpdateClient {

    @Test
    @CitrusTest
    public void updateColorAndHeight(@Optional @CitrusResource TestCaseRunner runner) {
        // Изменение цвета и высоты уточки
        createDuck(runner, "yellow", 0.15, "rubber", "quack", "ACTIVE");
        extractId(runner);
        duckUpdate(runner,"${duckId}","10","blue","rubber","quack");
        duckUpdateValidate(runner,"{\"message\":\"Duck with id = " + "${duckId}" + " is updated\"}");
    }

    @Test
    @CitrusTest
    public void updateColorAndSound(@Optional @CitrusResource TestCaseRunner runner) {
        // Изменение цвета и звука уточки
        createDuck(runner, "yellow", 0.15, "rubber", "quack", "ACTIVE");
        extractId(runner);
        duckUpdate(runner,"${duckId}","0.15","blue","rubber","krya");
        duckUpdateValidate(runner,"{\"message\":\"Duck with id = " + "${duckId}" + " is updated\"}");
    }
}