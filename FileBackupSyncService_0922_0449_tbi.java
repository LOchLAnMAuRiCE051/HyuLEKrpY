// 代码生成时间: 2025-09-22 04:49:02
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.mvc.freemarker.FreemarkerMvcFeature;
import org.glassfish.jersey.server.ServerProperties;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Path("/backup")
public class FileBackupSyncService {

    // The source and destination directories for file backup and sync
    private static final String SOURCE_DIR = "/path/to/source";
    private static final String DESTINATION_DIR = "/path/to/destination";

    @GET
    @Path("/sync")
    @Produces(MediaType.TEXT_PLAIN)
    public String syncFiles() {
        try {
            // Check if source and destination directories exist
            if (!Files.exists(Paths.get(SOURCE_DIR)) || !Files.exists(Paths.get(DESTINATION_DIR))) {
                return "Source or destination directory does not exist.";
            }

            // Iterate through all files in the source directory
            Files.walk(Paths.get(SOURCE_DIR)).forEach(sourcePath -> {
                // Construct the destination path
                Path destinationPath = Paths.get(DESTINATION_DIR, sourcePath.toString().replaceFirst(SOURCE_DIR, ""));

                // Create the destination directory if it does not exist
                if (Files.isDirectory(sourcePath) && !Files.exists(destinationPath)) {
                    Files.createDirectories(destinationPath);
                }

                // Copy file to destination if it does not exist or is older than the source file
                if (Files.isRegularFile(sourcePath) && (!Files.exists(destinationPath) || Files.getLastModifiedTime(sourcePath).after(Files.getLastModifiedTime(destinationPath)))) {
                    Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
                }
            });

            return "File synchronization completed successfully.";
        } catch (IOException e) {
            e.printStackTrace();
            return "Error occurred during file synchronization: " + e.getMessage();
        }
    }
}

// Configuration class for JERSEY
class FileBackupSyncConfig extends ResourceConfig {
    public FileBackupSyncConfig() {
        packages("com.example"); // Replace with your package name
        register(FreemarkerMvcFeature.class);
        property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, "true");
    }
}
