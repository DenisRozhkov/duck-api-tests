package autotests.clients;

import autotests.BaseTest;
import autotests.EndpointConfig;
import com.consol.citrus.TestCaseRunner;
import com.consol.citrus.http.client.HttpClient;
import com.consol.citrus.testng.spring.TestNGCitrusSpringSupport;
import io.qameta.allure.Step;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.test.context.ContextConfiguration;

import static com.consol.citrus.actions.ExecuteSQLAction.Builder.sql;
import static com.consol.citrus.actions.ExecuteSQLQueryAction.Builder.query;
import static com.consol.citrus.http.actions.HttpActionBuilder.http;

@ContextConfiguration(classes = {EndpointConfig.class})
public class DuckDeleteClient extends BaseTest {
    @Autowired
    protected HttpClient duckService;

    @Autowired
    protected SingleConnectionDataSource testDb;

    @Step("Удаляем утку")
    public void deleteDuck(TestCaseRunner runner, String id){
        runner.$(http()
                .client(duckService)
                .send()
                .delete("/api/duck/delete")
                .queryParam("id", id));
    }

    @Step("Проверяем ответ")
    public void deleteDuckValidate(TestCaseRunner runner, String message){
        validate(runner,message);
    }

    @Step("Обновляем БД")
    public void databaseUpdate(TestCaseRunner runner, String sql) {
        runner.$(sql(testDb)
                .statement(sql));
    }

    @Step("Проверяем БД")
    protected void validateCountInDatabase(TestCaseRunner runner, String id, String count) {
        runner.$(query(testDb)
                .statement("SELECT COUNT(1) as CNT FROM DUCK WHERE ID=" + id)
                .validate("CNT",count));
    }
}
