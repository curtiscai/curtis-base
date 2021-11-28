package com.curtis.apache.io;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author curtis.cai
 * @desc TODO
 * @date 2021-11-29
 * @email curtis.cai@outlook.com
 * @reference
 */
public class FileUtilsTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileUtilsTest.class);

    /**
     * 文件拷贝，如果文件不存在则赋值，如果存在则覆盖
     */
    @Test
    public void testFileCopy() {
        File sourceFile = new File("E:\\CurtisProjects\\curtis-base\\curtis-apache\\src\\main\\resources\\testfile\\test.txt");
        File targetFile1 = new File("E:\\CurtisProjects\\curtis-base\\curtis-apache\\src\\main\\resources\\testfile\\test-1.txt");
        File targetFile2 = new File("E:\\CurtisProjects\\curtis-base\\curtis-apache\\src\\main\\resources\\testfile\\test-2.txt");

        try {
            FileUtils.copyFile(sourceFile, targetFile1);
            FileUtils.copyFile(sourceFile, targetFile2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 目录拷贝，如果目录不存在则创建，如果存在则覆盖，目录下的文件也是这样操作
     */
    @Test
    public void testDirCopy() {
        File sourceDir = new File("E:\\CurtisProjects\\curtis-base\\curtis-apache\\src\\main\\resources\\testfile");
        File targetDir = new File("E:\\CurtisProjects\\curtis-base\\curtis-apache\\src\\main\\resources\\testfile-copy");

        try {
            FileUtils.copyDirectory(sourceDir, targetDir);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 递归删除目录
     */
    @Test
    public void testDeleteDirectory() {
        // 目录准备
        File sourceDir = new File("E:\\CurtisProjects\\curtis-base\\curtis-apache\\src\\main\\resources\\testfile");
        File targetDir = new File("E:\\CurtisProjects\\curtis-base\\curtis-apache\\src\\main\\resources\\testfile-delete");
        try {
            FileUtils.copyDirectory(sourceDir, targetDir);
            TimeUnit.SECONDS.sleep(30);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        try {
            FileUtils.deleteDirectory(targetDir);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建目录(如果父目录不存在则递归创建)
     * 判断目录是否为空
     */
    @Test
    public void testMkdir() {
        File dir1 = new File("E:\\CurtisProjects\\curtis-base\\curtis-apache\\src\\main\\resources\\testfile");
        File dir2 = new File("E:\\CurtisProjects\\curtis-base\\curtis-apache\\src\\main\\resources\\testfile\\test\\test");

        try {
            // 创建目录，如果目录存在则什么也不做，如果目录的父目录也不存在则递归创建
            FileUtils.forceMkdir(dir2);
            // 判断目录是否为空，要求目录必须存在否则抛出异常
            boolean emptyDirectory1 = FileUtils.isEmptyDirectory(dir1);
            boolean emptyDirectory2 = FileUtils.isEmptyDirectory(dir2);
            // 01:19:45,866  INFO FileUtilsTest:88 - emptyDirectory1 -> false, emptyDirectory2 -> true
            LOGGER.info("emptyDirectory1 -> {}, emptyDirectory2 -> {}", emptyDirectory1, emptyDirectory2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 清理目录，但是不删除目录本身
     */
    @Test
    public void testCleanDirectory() {
        // 目录准备
        File sourceDir = new File("E:\\CurtisProjects\\curtis-base\\curtis-apache\\src\\main\\resources\\testfile");
        File targetDir = new File("E:\\CurtisProjects\\curtis-base\\curtis-apache\\src\\main\\resources\\testfile-test");
        try {
            FileUtils.copyDirectory(sourceDir, targetDir);
            TimeUnit.SECONDS.sleep(30);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        try {
            FileUtils.cleanDirectory(targetDir);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
