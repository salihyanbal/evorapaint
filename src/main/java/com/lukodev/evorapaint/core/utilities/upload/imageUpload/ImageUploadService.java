package com.lukodev.evorapaint.core.utilities.upload.imageUpload;

import com.lukodev.evorapaint.core.utilities.results.DataResult;
import org.springframework.web.multipart.MultipartFile;

public interface ImageUploadService {
    DataResult<?> save(MultipartFile file);
    DataResult<?> delete(String fullFileName);
}
