package com.curtis.apache.io;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.*;

/**
 * @author curtis.cai
 * @desc TODO
 * @date 2021-11-29
 * @email curtis.cai@outlook.com
 * @reference
 */
public class IOUtilsTest {

    @Test
    public void testCopy() {
        File sourceFile = new File("E:\\CurtisProjects\\curtis-base\\curtis-apache\\src\\main\\resources\\testfile\\test.txt");
        File copyFile = new File("E:\\CurtisProjects\\curtis-base\\curtis-apache\\src\\main\\resources\\testfile\\test-copy.txt");
        try (FileInputStream fileInputStream = new FileInputStream(sourceFile);
             FileOutputStream fileOutputStream = new FileOutputStream(copyFile);) {
            IOUtils.copy(fileInputStream, fileOutputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCloseQuietly(){
        File sourceFile = new File("E:\\CurtisProjects\\curtis-base\\curtis-apache\\src\\main\\resources\\testfile\\test.txt");
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(sourceFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        IOUtils.closeQuietly(fileInputStream);
    }
}
