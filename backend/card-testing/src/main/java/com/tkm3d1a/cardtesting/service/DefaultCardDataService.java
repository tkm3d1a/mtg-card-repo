package com.tkm3d1a.cardtesting.service;

import com.tkm3d1a.cardtesting.entity.DefaultCardData;
import com.tkm3d1a.cardtesting.repository.DefaultCardDataRepository;
import com.tkm3d1a.cardtesting.util.FileUtils;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DefaultCardDataService {

    private final @NonNull DefaultCardDataRepository defaultCardDataRepository;

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
