package autotests.clients;

import autotests.BaseTest;
import autotests.EndpointConfig;
import autotests.payloads.DuckMessage;
import autotests.payloads.DuckProperties;
import com.consol.citrus.TestCaseRunner;
import com.consol.citrus.http.client.HttpClient;
import com.consol.citrus.message.builder.ObjectMappingPayloadBuilder;
import com.consol.citrus.testng.spring.TestNGCitrusSpringSupport;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.qameta.allure.Step;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.test.context.ContextConfiguration;

import static com.consol.citrus.actions.ExecuteSQLAction.Builder.sql;
import static com.consol.citrus.http.actions.HttpActionBuilder.http;

@ContextConfiguration(classes = {EndpointConfig.class})
public class DuckFlyClient extends BaseTest {
    @Autowired
    protected HttpClient duckService;

    @Autowired
    protected SingleConnectionDataSource testDb;

    @Step("Метод полета")
    public void duckFly(TestCaseRunner runner, String id) {
        sendGetRequest(runner,id,"/api/duck/action/fly");
    }

    @Step("Проверяем ответ")
    public void duckFlyValidate(TestCaseRunner runner, String message) {
        validate(runner, message);
    }

    @Step("Проверяем ответ")
    public void duckFlyValidate(TestCaseRunner runner, DuckMessage duckMessage) {
        validate(runner, duckMessage);
    }

    @Step("Проверяем ответ")
    public void duckFlyValidateJson(TestCaseRunner runner, String filePath) {
        validateJson(runner,filePath);
    }

    @Step("Обновляем БД")
    public void databaseUpdate(TestCaseRunner runner, String sql) {
        runner.$(sql(testDb)
                .statement(sql));
    }
}