// 代码生成时间: 2025-09-24 01:14:27
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@Path("/api/RandomNumber")
public class RandomNumberGeneratorService {

    /**
     * Generates a random number between 0 and 100.
     *
     * @return Random number as a JSON string.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getRandomNumber() {
        try {
            // Generate a random number between 0 and 100
            int randomNumber = ThreadLocalRandom.current().nextInt(0, 101);

            // Return the random number as a JSON string
            return String.format({""%s": "%d"}, "randomNumber", randomNumber);

        } catch (Exception e) {
            // Handle any exceptions that may occur
            // In a real-world scenario, this should be more specific to the exception type
            return String.format({""%s": "%s"}, "error", e.getMessage());
        }
    }
}
