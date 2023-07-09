package com.tkm3d1a.cardtesting.defaultCardData;

import com.tkm3d1a.cardtesting.util.FileUtils;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class DefaultCardDataService {

    @Resource
    private DefaultCardDataRepository defaultCardDataRepository;

    public String uploadDefaultCardData(MultipartFile file) throws IOException {
        DefaultCardData cardData = defaultCardDataRepository.save(DefaultCardData.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .fileData(FileUtils.compressFile(file.getBytes()))
                .build());
        if(cardData != null){
            return "file upload successfully: " + cardData.getName();
        }
        return null;
    }
}
