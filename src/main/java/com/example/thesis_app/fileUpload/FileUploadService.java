package com.example.thesis_app.fileUpload;

import com.example.thesis_app.configuration.auth.CustomPrincipal;
import com.example.thesis_app.fileUpload.dto.response.ProfessorFileListResponseItem;
import com.example.thesis_app.thesis.Thesis;
import com.example.thesis_app.thesis.ThesisRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class FileUploadService {
    private static final Logger logger = LoggerFactory.getLogger(FileUploadService.class);

    @Value("${file.upload-dir}")
    private String uploadDir;

    private final FileUploadRepository fileUploadRepository;
    private final ThesisRepository thesisRepository;

    @Autowired
    public FileUploadService(FileUploadRepository fileUploadRepository, ThesisRepository thesisRepository) {
        this.fileUploadRepository = fileUploadRepository;
        this.thesisRepository = thesisRepository;
    }

    public void uploadFile(MultipartFile file, Long thesisId, CustomPrincipal principal) {
        if(thesisRepository.studentCheckPermission(thesisId, principal.getName()) == 0) {
            throw new RuntimeException("Thesis does not exist or is not accessible");
        }

        File directory = new File(uploadDir);
        if (!directory.exists()) {
            boolean dirCreated = directory.mkdirs();
            if (!dirCreated) {
                throw new RuntimeException("Failed to create directory: " + uploadDir);
            }
        }

        try {
            String fileDir = uploadDir + file.getOriginalFilename();
            File saveFile = new File(fileDir);
            file.transferTo(saveFile);

            FileUpload fileUpload = new FileUpload();

            Thesis thesis = new Thesis();
            thesis.setId(thesisId);

            fileUpload.setThesis(thesis);
            fileUpload.setFilePath(fileDir);
            fileUpload.setFileName(file.getOriginalFilename());
            fileUpload.setFileType(file.getContentType());
            fileUpload.setTimestamp(new Date());

            fileUploadRepository.save(fileUpload);
        } catch (Exception e) {
            throw new RuntimeException("File upload failed.");
        }
    }

    public List<ProfessorFileListResponseItem> getThesisFiles(Long thesisId, CustomPrincipal principal) {
        Optional<List<ProfessorFileListResponseItem>> fileOptional = fileUploadRepository.getThesisFiles(thesisId, principal.getName());

        if(fileOptional.isEmpty()) {
            throw new RuntimeException("Thesis not found or is inaccessible.");
        }

        return fileOptional.get();
    }

    public ResponseEntity<Object> getFile(Long id, CustomPrincipal principal) {
        Optional<FileUpload> fileOptional = fileUploadRepository.getByIdSecure(id, principal.getName());

        if(fileOptional.isEmpty()) {
            throw new RuntimeException("File not found or is not accessible.");
        }

        Path filePath = Paths.get(fileOptional.get().getFilePath());
        try {
            Resource resource = new UrlResource(filePath.toUri());
            if (!resource.exists() || !resource.isReadable()) {
                throw new RuntimeException("File not found or not readable.");
            }

            String contentType = Files.probeContentType(filePath);
            if (contentType == null) {
                contentType = "application/octet-stream";
            }

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                    .body(resource);
        } catch (Exception e) {
            throw new RuntimeException("File not found or is not accessible.");
        }
    }
}
