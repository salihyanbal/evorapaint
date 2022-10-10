package com.lukodev.evorapaint.core.utilities.upload.imageUpload.localImageService;

import com.lukodev.evorapaint.core.utilities.results.DataResult;
import com.lukodev.evorapaint.core.utilities.results.ErrorDataResult;
import com.lukodev.evorapaint.core.utilities.results.SuccessDataResult;
import com.lukodev.evorapaint.core.utilities.upload.imageUpload.ImageUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Locale;
import java.util.UUID;

@Service
public class LocalImageManager implements ImageUploadService {
    private static String[] ValidImageFileTypes = { ".JPG", ".JPEG", ".PNG", ".TIF", ".TIFF", ".GIF", ".BMP", ".ICO" };
    private Path path;

    private ResourceLoader resourceLoader;
    private Resource resource;

    @Autowired
    public LocalImageManager(ResourceLoader resourceLoader){
        this.resourceLoader = resourceLoader;
        Resource resource = resourceLoader.getResource("classpath:static/uploads/images");
        try {
            path = resource.getFile().toPath();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public DataResult<?> save(MultipartFile file) {
        String extension = '.' + file.getOriginalFilename().split("\\.")[1];;
        boolean isValidFileExtension = Arrays.stream(ValidImageFileTypes).anyMatch(fileType -> fileType.equals(extension.toUpperCase(Locale.ROOT)));
        if(!isValidFileExtension){
            return new ErrorDataResult<>("Geçersiz dosya uzantısı, fotoğraf için kabul edilen uzantılar: " + String.join(",", ValidImageFileTypes));
        }
        String fileName = UUID.randomUUID() + extension;
        File newFile = new File(path + "\\" + fileName);
        try {
            newFile.createNewFile();
            file.transferTo(newFile);
        } catch (IOException e) {
            e.printStackTrace();
            return new ErrorDataResult<>("Resim kaydedilirken bi hata oluştu.");
        }
        return new SuccessDataResult<>(fileName ,"Resim kaydedildi.");
    }

    @Override
    public DataResult<?> delete(String fullFileName) {
        File newFile = new File(path + "\\" + fullFileName);
        boolean isDeleted = newFile.delete();
        if(!isDeleted){
            return new ErrorDataResult<>("Resim silinirken bi hata oluştu");
        }
        return new SuccessDataResult<>("Resim silindi.");
    }
}
