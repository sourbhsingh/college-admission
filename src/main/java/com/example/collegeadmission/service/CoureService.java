package com.example.collegeadmission.service;

import com.example.collegeadmission.model.Document;
import com.example.collegeadmission.repository.CourseRepository;
import com.example.collegeadmission.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CoureService {
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private DocumentRepository docRepository;
    private String uploadDir = System.getProperty("user.dir") + "/target/classes/static/uploads";


    public void saveFile(MultipartFile file) throws IOException {
        File dir = new File(uploadDir);
        if(!dir.exists()) {
            System.out.println("Directory not found");
            if(dir.mkdirs())
                System.out.println("Directory created");
        }

        String path = uploadDir + File.separator + file.getOriginalFilename();
        file.transferTo(new File(path));


        Document doc = new Document();
        doc.setFileName(file.getOriginalFilename());
        doc.setFilePath("/uploads/" + file.getOriginalFilename());


        docRepository.save(doc);
    }

    public List<Document> getAllFiles() {
        return docRepository.findAll();
    }
}
