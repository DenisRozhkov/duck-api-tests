package autotests.tests;

import autotests.clients.DuckQuackClient;
import com.consol.citrus.TestCaseRunner;
import com.consol.citrus.annotations.CitrusResource;
import com.consol.citrus.annotations.CitrusTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Test;

public class DuckQuackTest extends DuckQuackClient {

    @Test
    @CitrusTest
    public void quackOddId(@Optional @CitrusResource TestCaseRunner runner) {
        // Корректный нечётный id, корректный звук
        duckQuack(runner,"1");
        duckQuackValidate(runner,"{\"message\":\"Quack!\"}");
    }

    @Test
    @CitrusTest
    public void quackEvenId(@Optional @CitrusResource TestCaseRunner runner) {
        // Корректный чётный id, корректный звук
        duckQuack(runner,"2");
        duckQuackValidate(runner,"{\"message\":\"Quack!\"}");
    }
}