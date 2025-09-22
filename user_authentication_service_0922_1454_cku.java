// 代码生成时间: 2025-09-22 14:54:23
 * This service checks if a user has valid credentials and returns a response accordingly.
 */

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;
import java.util.HashMap;

@Path("/auth")
public class UserAuthenticationService {

    private static Map<String, String> userCredentials = new HashMap<>();
    private static final String VALID_USERNAME = "testUser";
    private static final String VALID_PASSWORD = "testPassword";

    static {
        // Pre-populating user credentials for demonstration purposes.
        userCredentials.put(VALID_USERNAME, VALID_PASSWORD);
    }

    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    public Response authenticateUser(String credentials) {
        try {
            Map<String, String> loginDetails = parseCredentials(credentials);
            String username = loginDetails.get("username");
            String password = loginDetails.get("password");

            if (isValidUser(username, password)) {
                return Response.status(Response.Status.OK).entity(
                    "{"message":"User authenticated successfully."}").build();
            } else {
                return Response.status(Response.Status.UNAUTHORIZED).entity(
                    "{"message":"Invalid username or password."}").build();
            }
        } catch (Exception e) {
            // Log the exception and return a server error response if something goes wrong.
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(
                "{"message":"Server error while processing authentication."}").build();
        }
    }

    // Helper method to parse the credentials string into a Map.
    private Map<String, String> parseCredentials(String credentials) {
        try {
            // Assuming credentials are in the format: "username:password"
            String[] parts = credentials.split(":");
            return Map.of(parts[0], parts[1]);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid credentials format.");
        }
    }

    // Helper method to check if the username and password are valid.
    private boolean isValidUser(String username, String password) {
        return userCredentials.containsKey(username) && userCredentials.get(username).equals(password);
    }
}
