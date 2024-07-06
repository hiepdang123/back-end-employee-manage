package com.dvh.employee_management.service.imp;

import com.dvh.employee_management.exception.FileException;
import com.dvh.employee_management.service.FileStorageService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileStorageServiceImp implements FileStorageService {
    private final Path fileStorageLocation = Paths.get("uploads").toAbsolutePath().normalize();

    @Override
    public String storeFile(MultipartFile file) {
        try {
            String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();

            //Create folder to store file .you can create any where you want .
            File f = new File("uploads/");
            if(!f.exists()) {
                f.mkdir();
            }

            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation);

            return fileName;
        }catch (Exception e){
            throw new FileException(e.getMessage());
        }
    }
}
