package mk.payten.live_orders.web;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class FileController {
    @Value("${logging.file.path}")
    private String folderPath;

//    @GetMapping("/video")
//    public ResponseEntity<ByteArrayResource> getVideo() throws IOException {
//        return getFileResponse("C:\\Users\\User\\Downloads\\octetStreamFiles\\bunnyVideo.mp4", "bunnyVideo.mp4");
//    }
//
//    @GetMapping("/image")
//    public ResponseEntity<ByteArrayResource> getImage() throws IOException {
//        return getFileResponse("C:\\Users\\User\\Downloads\\octetStreamFiles\\bunnyImage.jpg", "bunnyImage.jpg");
//    }

//    @GetMapping("/gif")
//    public ResponseEntity<ByteArrayResource> getGif() throws IOException {
//        return getFileResponse("/path/to/image.gif", "image.gif");
//    }
    @GetMapping("/media")
    public ResponseEntity<ByteArrayResource> getMedia() throws IOException{
        String fileName = getFirstFileDetails();
        System.out.println(folderPath+"/"+fileName);
        return getFileResponse(folderPath+"/"+fileName,fileName);
    }

    private ResponseEntity<ByteArrayResource> getFileResponse(String filePath, String fileName) throws IOException {
        Path path = Paths.get(filePath);
        byte[] fileBytes = Files.readAllBytes(path);

        ByteArrayResource fileResource = new ByteArrayResource(fileBytes);

//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
//        headers.setContentDisposition(ContentDisposition.builder("attachment").filename(fileName).build());

        String fileType = Files.probeContentType(path); // Infer media type based on file content

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(fileType)); // Set correct media type
        headers.setContentDisposition(ContentDisposition.builder("attachment").filename(fileName).build());

        return ResponseEntity.ok()
                .headers(headers)
                .body(fileResource);
    }
    public String getFirstFileDetails() throws IOException {
        File folder = new File(folderPath);
        File[] files = folder.listFiles();
        String nameType = "";
        if (files != null && files.length > 0) {
            File firstFile = files[0];
            String fileName = firstFile.getName();
            String fileType = Files.probeContentType(firstFile.toPath());
            String fileExtension = fileType.split("/")[1];

            nameType = fileName;
        }
        System.out.println(nameType);
        return nameType;

}
}