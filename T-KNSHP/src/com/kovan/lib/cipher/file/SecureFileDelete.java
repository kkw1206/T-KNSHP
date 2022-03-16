package com.kovan.lib.cipher.file;


import org.apache.commons.io.FileExistsException;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Path;
import java.security.SecureRandom;

public class SecureFileDelete {
    public static final int WIPE_BLOCK_BYTES_SIZE = 64;

    public SecureFileDelete() {
    }

    public static void wipeAndDelete(String fileName) throws IOException {
        File file = new File(fileName);
        wipeAndDelete(file);
    }

    public static void wipeAndDelete(Path filePath) throws IOException {
        File file = filePath.toFile();
        wipeAndDelete(file);
    }

    public static void wipeAndDelete(File file) throws IOException {
        if (file.exists()) {
            long length = file.length();
            wipeFileUsingFileByte(file, length, (byte)0);
            wipeFileUsingFileByte(file, length, (byte)1);
            wipeFileUsingRandomByte(file, length);
            file.delete();
        } else {
            throw new FileExistsException();
        }
    }

    private static boolean wipeFileUsingRandomByte(File file, long fileLength) throws IOException {
        SecureRandom random = new SecureRandom();
        RandomAccessFile raf = new RandomAccessFile(file, "rws");
        raf.seek(0L);
        raf.getFilePointer();
        byte[] fillBytes = new byte[64];
        int blockCounter = 0;

        for(long pos = 0L; pos < fileLength; ++blockCounter) {
            random.nextBytes(fillBytes);
            raf.write(fillBytes);
            pos += (long)fillBytes.length;
        }

        raf.close();
        return true;
    }

    private static boolean wipeFileUsingFileByte(File file, long fileLength, byte fillByte) throws IOException {
        RandomAccessFile raf = new RandomAccessFile(file, "rws");
        raf.seek(0L);
        raf.getFilePointer();
        byte[] fillBytes = new byte[64];

        int blockCounter;
        for(blockCounter = 0; blockCounter < fillBytes.length; ++blockCounter) {
            fillBytes[blockCounter] = fillByte;
        }

        blockCounter = 0;

        for(long pos = 0L; pos < fileLength; ++blockCounter) {
            raf.write(fillBytes);
            pos += (long)fillBytes.length;
        }

        raf.close();
        return true;
    }

    public String toString() {
        return "WIPE_BLOCK_BYTES_SIZE is 64";
    }
}
