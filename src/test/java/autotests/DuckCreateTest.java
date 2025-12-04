package autotests;

import com.consol.citrus.TestCaseRunner;
import com.consol.citrus.annotations.CitrusResource;
import com.consol.citrus.annotations.CitrusTest;
import com.consol.citrus.testng.spring.TestNGCitrusSpringSupport;
import org.testng.annotations.Optional;
import org.testng.annotations.Test;

public class DuckCreateTest extends TestNGCitrusSpringSupport {
    @Test(description = "Создание утки с material = rubber ")
    @CitrusTest
    public void createMaterialRubber(@Optional @CitrusResource TestCaseRunner runner) {
        createDuck(runner, "yellow", 0.15, "rubber", "quack", "ACTIVE");
        validateResponseCreate(runner, "{\n"
                + "  \"color\": \"" + "yellow" + "\",\n"
                + "  \"height\": " + 0.15 + ",\n"
                + "  \"material\": \"" + "rubber" + "\",\n"
                + "  \"sound\": \"" + "quack" + "\",\n"
                + "  \"wingsState\": \"" + "ACTIVE"
                + "\"\n" + "}", HttpStatus.OK);
        duckProperties(runner, "${duckId}");
        validateResponseProperties(runner, "{\n"
                + "  \"color\": \"" + "yellow" + "\",\n"
                + "  \"height\": " + 0.15 + ",\n"
                + "  \"material\": \"" + "rubber" + "\",\n"
                + "  \"sound\": \"" + "quack" + "\",\n"
                + "  \"wingsState\": \"" + "ACTIVE"
                + "\"\n" + "}", HttpStatus.OK);
    }

    private void createDuck(TestCaseRunner runner, String yellow, double v, String rubber, String quack, String active) {
    }
}{
}
