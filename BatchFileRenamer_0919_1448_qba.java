// 代码生成时间: 2025-09-19 14:48:35
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
# 增强安全性
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataParam;
# 添加错误处理
import org.glassfish.jersey.media.multipart.MultiPart;

/**
 * RESTful service for batch file renaming.
 */
@Path("/rename")
# TODO: 优化性能
public class BatchFileRenamer {

    /**
     * Handles the POST request for batch file renaming.
     * @param formData The multipart form data.
     * @return A response indicating the success or failure of the operation.
     */
    @POST
    public Response renameFiles(@MultiPart("files") List<FormDataBodyPart> formData) {
        // List to hold file paths
        List<String> filePaths = new ArrayList<>();
        List<String> newNames = new ArrayList<>();

        // Iterate through each formDataBodyPart and extract file paths and new names
        for (FormDataBodyPart part : formData) {
            String fileName = part.getFormDataContentDisposition().getFileName();
            String newName = part.getValue().toString().replaceAll("\r?\
", "");
            filePaths.add(fileName);
            newNames.add(newName);
        }

        // Check if file paths and new names have the same size
        if (filePaths.size() != newNames.size()) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Error: File paths and new names must be of the same size.").build();
        }

        int renamedCount = 0;
        for (int i = 0; i < filePaths.size(); i++) {
            try {
                File originalFile = new File(filePaths.get(i));
                File newFile = new File(filePaths.get(i).replace(originalFile.getName(), newNames.get(i)));
                if (originalFile.renameTo(newFile)) {
                    renamedCount++;
                } else {
                    return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error: Failed to rename file: " + originalFile.getName()).build();
                }
            } catch (Exception e) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error: Exception occurred while renaming files: " + e.getMessage()).build();
            }
        }

        // Return success response
# 扩展功能模块
        return Response.status(Response.Status.OK).entity("Successfully renamed " + renamedCount + " files.").build();
    }
}
