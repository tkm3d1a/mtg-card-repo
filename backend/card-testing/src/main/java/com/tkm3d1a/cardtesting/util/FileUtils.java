package com.tkm3d1a.cardtesting.util;

import java.io.ByteArrayOutputStream;
import java.util.zip.Deflater;

public class FileUtils {

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
}
