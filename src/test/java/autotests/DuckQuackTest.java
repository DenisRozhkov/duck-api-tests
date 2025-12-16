package autotests;

import com.consol.citrus.TestCaseRunner;
import com.consol.citrus.annotations.CitrusResource;
import com.consol.citrus.annotations.CitrusTest;
import com.consol.citrus.testng.spring.TestNGCitrusSpringSupport;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.testng.annotations.Optional;
import org.testng.annotations.Test;

import java.util.concurrent.atomic.AtomicInteger;

import static com.consol.citrus.dsl.MessageSupport.MessageBodySupport.fromBody;
import static com.consol.citrus.http.actions.HttpActionBuilder.http;

public class DuckQuackTest extends TestNGCitrusSpringSupport {
    public void duckQuack(TestCaseRunner runner, String id, String repetitionCount, String soundCount) {
        runner.$(http().client("http://localhost:2222")
                .send()
                .get("/api/duck/action/quack")
                .queryParam("id", id)
                .queryParam("repetitionCount", repetitionCount)
                .queryParam("soundCount", soundCount));
    }

    public void duckQuackValidate(TestCaseRunner runner, String body) {
        runner.$(http()
                .client("http://localhost:2222")
                .receive()
                .response(HttpStatus.OK)
                .message()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(body));
    }

    public void createDuck(TestCaseRunner runner, String color, double height, String material,
                           String sound, String wingsState) {
        runner.$(http()
                .client("http://localhost:2222")
                .send()
                .post("/api/duck/create")
                .message()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body("{\n" +
                        "\"color\": \"" + color + "\",\n" +
                        "\"height\": " + height + ",\n" +
                        "\"material\": \"" + material + "\",\n" +
                        "\"sound\": \"" + sound + "\",\n" +
                        "\"wingsState\": \"" + wingsState + "\"\n" + "}"));
    }

    public void extractId(TestCaseRunner runner) {
        runner.$(http()
                .client("http://localhost:2222")
                .receive()
                .response(HttpStatus.OK)
                .message()
                .extract(fromBody().expression("$.id", "duckId")));
    }

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