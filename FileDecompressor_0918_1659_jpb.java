// 代码生成时间: 2025-09-18 16:59:38
package com.example.util;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@Path("/decompress")
public class FileDecompressor {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response decompressFile(@QueryParam("filePath") String filePath, @QueryParam("outputPath") String outputPath) {
        try {
            // Read the zip file from the provided path
            InputStream zipInputStream = new java.io.FileInputStream(filePath);
            ZipInputStream zipIn = new ZipInputStream(zipInputStream);
            ZipEntry entry = zipIn.getNextEntry();
            byte[] buffer = new byte[1024];
            int len;

            // Create directories and write files to the output path
            while (entry != null) {
                String filePathToSave = outputPath + entry.getName();
                if (!entry.isDirectory()) {
                    // Write file content to the file
                    OutputStream out = new java.io.FileOutputStream(filePathToSave);
                    while ((len = zipIn.read(buffer)) > 0) {
                        out.write(buffer, 0, len);
                    }
                    out.close();
                } else {
                    // Create the directory if it does not exist
                    new java.io.File(filePathToSave).mkdirs();
                }
                zipIn.closeEntry();
                entry = zipIn.getNextEntry();
            }
            zipIn.close();

            // Return successful response
            return Response.status(Response.Status.OK).entity("File decompressed successfully").build();
        } catch (IOException e) {
            // Handle exceptions and return error response
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error decompressing file: " + e.getMessage()).build();
        }
    }
}
