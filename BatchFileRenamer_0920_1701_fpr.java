// 代码生成时间: 2025-09-20 17:01:53
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

@Path("/rename")
public class BatchFileRenamer {

    @POST
    @Path("/process")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response processRename(
            @FormDataParam("file") List<File> files,
            @FormDataParam("file") FormDataContentDisposition fileDetail,
            @FormDataParam("newName") String newName) {

        try {
            // Extract the directory path from the file details
            String directoryPath = fileDetail.getFileName();
            directoryPath = directoryPath.substring(0, directoryPath.lastIndexOf(File.separator));

            // List to hold the renamed files details
            List<String> renamedFiles = new ArrayList<>();

            // Rename all files in the list
            for (File file : files) {
                Path oldPath = file.toPath();
                String fileName = file.getName();
                String newFileName = String.format("%s_%s", newName, fileName);
                Path newPath = Paths.get(directoryPath, newFileName);

                // Rename the file
                Files.move(oldPath, newPath);
                renamedFiles.add(newFileName);
            }

            // Return the list of renamed files
            return Response.ok().entity(renamedFiles).build();
        } catch (IOException e) {
            // Handle exceptions and return an appropriate response
            return Response.serverError().entity("Error: " + e.getMessage()).build();
        }
    }

    @GET
    @Path("/help")
    @Produces(MediaType.TEXT_PLAIN)
    public String getHelp() {
        // Provide documentation on how to use the tool
        return "POST /rename/process with multipart/form-data containing:
                - 'file': the file to rename (can be multiple)
                - 'newName': the new name prefix for the files";
    }
}
