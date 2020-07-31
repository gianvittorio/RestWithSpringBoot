package br.com.gianvittorio.service;

import br.com.gianvittorio.config.FileStorageConfig;
import br.com.gianvittorio.exception.FileStorageException;
import br.com.gianvittorio.exception.MyFileNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileStorageService {
    private final Path fileStorageLocation;

    @Autowired
    public FileStorageService(FileStorageConfig fileStorageConfig) {
        fileStorageLocation = Paths.get(fileStorageConfig.getUploadDir())
                .toAbsolutePath()
                .normalize();
        try {
            if (Files.notExists(fileStorageLocation)) {
                Files.createDirectory(fileStorageLocation);
            }
        } catch (Exception e) {
            throw new FileStorageException("Could not create the directory " +
                    "where file was supposed to be stored " + fileStorageLocation, e);
        }
    }

    public String storeFile(MultipartFile file) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            if (fileName.contains("..")) {
                throw new FileStorageException("Sorry, filename contains an invalid path sequence " +
                        fileName);
            }
            Path targetLocation = fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return fileName;
        } catch (Exception e) {
            throw new FileStorageException("Could not store file " +
                    fileName + ". Please try again", e);
        }
    }

    public Resource loadFileAsResource(String filename) {
        try {
            Path filePath = fileStorageLocation.resolve(filename).normalize();
            UrlResource resource = new UrlResource(filePath.toUri());
            if (resource.exists()) {
                return resource;
            } else {
                throw new MyFileNotFoundException("File not found " + filename);
            }
        } catch (Exception e) {
            throw new MyFileNotFoundException("File not found " + filename, e);
        }
    }
}
