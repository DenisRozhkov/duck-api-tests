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
        duckUpdate(runner,"1","{\"color\":\"red\",\"height\":0.25}");
        duckUpdateValidate(runner);
    }

    @Test
    @CitrusTest
    public void updateColorAndSound(@Optional @CitrusResource TestCaseRunner runner) {
        // Изменение цвета и звука уточки
        duckUpdate(runner,"1","{\"color\":\"blue\",\"sound\":\"quack-quack\"}");
        duckUpdateValidate(runner);
    }
}