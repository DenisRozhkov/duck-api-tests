package autotests.tests;

import autotests.clients.DuckSwimClient;
import com.consol.citrus.TestCaseRunner;
import com.consol.citrus.annotations.CitrusResource;
import com.consol.citrus.annotations.CitrusTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Test;

public class DuckSwimTest extends DuckSwimClient {

    @Test
    @CitrusTest
    public void swimExistingId(@Optional @CitrusResource TestCaseRunner runner) {
        // Существующий id
        createDuck(runner, "yellow", 0.15, "rubber", "quack", "ACTIVE");
        extractId(runner);
        duckSwim(runner, "${duckId}");
        duckSwimValidate(runner, "{\"message\":\"I'm swimming\"}");
    }

    @Test
    @CitrusTest
    public void swimNonExistingId(@Optional @CitrusResource TestCaseRunner runner) {
        // Несуществующий id
        deleteDuck(runner, "2");
        duckSwim(runner, "2");
        duckSwimValidate(runner, "{\"message\":\"Duck with id = 2 is not found\"}");
    }
}