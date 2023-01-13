package azure_functions_tutorial;

import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.HttpMethod;
import com.microsoft.azure.functions.HttpRequestMessage;
import com.microsoft.azure.functions.HttpResponseMessage;
import com.microsoft.azure.functions.HttpStatus;
import com.microsoft.azure.functions.annotation.*;

import java.util.Optional;
import java.util.logging.*;

/**
 * Azure Functions with HTTP Trigger.
 */
public class Function {
    /**
     * This function listens at endpoint "/api/HttpExample". Two ways to invoke it using "curl" command in bash:
     * 1. curl -d "HTTP Body" {your host}/api/HttpExample
     * 2. curl "{your host}/api/HttpExample?name=HTTP%20Query"
     */
    @FunctionName("HttpExample")
    public HttpResponseMessage run(
            @HttpTrigger(
                name = "req",
                methods = {HttpMethod.GET, HttpMethod.POST},
                authLevel = AuthorizationLevel.ANONYMOUS)
                HttpRequestMessage<Optional<String>> request,
            final ExecutionContext context) {
        context.getLogger().info("Java HTTP trigger processed a request.");

        // Parse query parameter
        final String query = request.getQueryParameters().get("name");
        final String name = request.getBody().orElse(query);

        if (name == null) {
            return request.createResponseBuilder(HttpStatus.BAD_REQUEST).body("Please pass a name on the query string or in the request body").build();
        } else {
            return request.createResponseBuilder(HttpStatus.OK).body("Hello, " + name).build();
        }
    }

    @FunctionName("HttpTriggerJavaVersion")
    public static HttpResponseMessage HttpTriggerJavaVersion(
            @HttpTrigger(
                    name = "req",
                    methods = {HttpMethod.GET, HttpMethod.POST},
                    authLevel = AuthorizationLevel.ANONYMOUS)
            HttpRequestMessage<Optional<String>> request,
            final ExecutionContext executionContext
    ) {
        final Logger log = executionContext.getLogger();
        log.info("Java HTTP Trigger Processed a request");

        final String javaVersion = getJavaVersion();

        log.info("Function - HttpTriggerJavaVersion" + javaVersion);
        return request.createResponseBuilder(HttpStatus.OK).body("HttpTriggerJavaVersion: " + javaVersion).build();
    }

    /**
     * {second} {minute} {hour} {day} {month} {day-of-week}
      */
    @FunctionName("ScheduledTriggerJavaVersion")
    public static void ScheduledTriggerJavaVersion(
            @TimerTrigger(
                    name = "req",
                    schedule = "0/30 * * * * *") String timerInfo,
            ExecutionContext executionContext) {
        final Logger log = executionContext.getLogger();
        log.info("Timer info: " + timerInfo);
    }

    @FunctionName("ScheduledTriggerJavaVersionWithWebHook")
    public static void ScheduledTriggerJavaVersionWithWebHook(
            @TimerTrigger(
                    name = "req",
                    schedule = "0 0/1 * * * *") String timerInfo,
            ExecutionContext executionContext) {
        final Logger log = executionContext.getLogger();
        log.info("Running scheduled trigger with web hook");
        log.info("Timer info: " + timerInfo);
    }

    private static String getJavaVersion() {
        return String.join(" - ", System.getProperty("java.home"), System.getProperty("java.version"));
    }
}
