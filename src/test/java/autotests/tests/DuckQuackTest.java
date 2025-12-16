package autotests.tests;

import autotests.clients.DuckQuackClient;
import com.consol.citrus.TestCaseRunner;
import com.consol.citrus.annotations.CitrusResource;
import com.consol.citrus.annotations.CitrusTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Test;

import java.util.concurrent.atomic.AtomicInteger;

public class DuckQuackTest extends DuckQuackClient {

    @Test
    @CitrusTest
    public void quackOddId(@Optional @CitrusResource TestCaseRunner runner) {
        // Корректный нечётный id, корректный звук
        AtomicInteger id = new AtomicInteger();
        do{
            createDuck(runner, "yellow", 0.15, "wood", "quack", "ACTIVE");
            extractId(runner);

            runner.$(a -> {
                id.set(Integer.parseInt(a.getVariable("duckId")));
            });
        }while (id.get() % 2 !=1);
        duckQuack(runner,"${duckId}","3","2");
        duckQuackValidate(runner,"{\"sound\":\"quack-quack, quack-quack, quack-quack\"}");
    }

    @Test
    @CitrusTest
    public void quackEvenId(@Optional @CitrusResource TestCaseRunner runner) {
        // Корректный чётный id, корректный звук
        AtomicInteger id = new AtomicInteger();
        do{
            createDuck(runner, "yellow", 0.15, "wood", "quack", "ACTIVE");
            extractId(runner);

            runner.$(a -> {
                id.set(Integer.parseInt(a.getVariable("duckId")));
            });
        }while (id.get() % 2 !=0);
        duckQuack(runner,"${duckId}","3","2");
        duckQuackValidate(runner,"{\"sound\":\"quack-quack, quack-quack, quack-quack\"}");
    }
}