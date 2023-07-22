package com.tkm3d1a.cardtesting.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.util.zip.Deflater;

@Slf4j
public class FileUtils {

    private static final String TYPE = "text/csv";

    public static byte[] compressFile(byte[] data){
        Deflater deflater = new Deflater();
        deflater.setLevel(Deflater.BEST_COMPRESSION);
        deflater.setInput(data);
        deflater.finish();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] tmp = new byte[4*1024];
        while(!deflater.finished()){
            int size = deflater.deflate(tmp);
            outputStream.write(tmp, 0, size);
        }

        try {
            outputStream.close();
        } catch (Exception ignored){

        }

        return outputStream.toByteArray();
    }

    public static boolean isCSVFile(MultipartFile file){
        log.info("********FileUtils isCSVFile*********");
        if(TYPE.equals(file.getContentType())){
            log.info("File uploaded is CSV");
            log.info("************************************");
            return true;
        }

        log.warn("File uploaded is not csv");
        log.info("************************************");
        return false;
    }
}
