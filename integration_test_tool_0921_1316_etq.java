// 代码生成时间: 2025-09-21 13:16:11
 * IntegrationTestTool.java
 *
 * This Java program uses JERSEY framework to create a RESTful service
 * that serves as an integration test tool.
 */

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/test")
public class TestResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getTestMessage() {
        return "Integration Test Message";
    }
}

/*
 * IntegrationTestToolApp.java
 *
 * This class configures the JERSEY application and sets up the resource classes.
 */
import org.glassfish.jersey.server.ResourceConfig;
public class IntegrationTestToolApp extends ResourceConfig {
    public IntegrationTestToolApp() {
        register(TestResource.class);
    }
}

/*
 * IntegrationTest.java
 *
 * This is the integration test class for the integration test tool.
 */
public class IntegrationTest extends JerseyTest {

    @Override
    protected Application configure() {
        return new IntegrationTestToolApp();
    }

    @Test
    public void testGetTestMessage() {
        Response response = target("/test").request().get();
        assertEquals(200, response.getStatus());
        assertEquals("Integration Test Message", response.readEntity(String.class));
    }
}
