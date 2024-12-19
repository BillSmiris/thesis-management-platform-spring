package com.example.thesis_app.fileUpload;

import com.example.thesis_app.configuration.auth.CustomPrincipal;
import com.example.thesis_app.thesis.Thesis;
import com.example.thesis_app.thesis.ThesisRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

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

            fileUploadRepository.save(fileUpload);
        } catch (Exception e) {
            throw new RuntimeException("File upload failed.");
        }
    }
}
