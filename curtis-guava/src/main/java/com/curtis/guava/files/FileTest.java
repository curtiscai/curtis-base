package com.curtis.guava.files;

import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import com.google.common.io.ByteSource;
import com.google.common.io.FileWriteMode;
import com.google.common.io.Files;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author curtis.cai
 * @desc Guava Collection之Files api的使用
 * @date 2021-11-20
 * @email curtis.cai@outlook.com
 * @reference
 */
public class FileTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileTest.class);

    // @Before
    // public void before() {
    //     File targetFile1 = new File("E:\\CurtisProjects\\curtis-base\\curtis-guava\\src\\main\\resources\\testfile\\1.txt");
    //     File targetFile2 = new File("E:\\CurtisProjects\\curtis-base\\curtis-guava\\src\\main\\resources\\testfile\\2.txt");
    //     if (targetFile1.exists()) {
    //         targetFile1.delete();
    //     }
    //     if (targetFile2.exists()) {
    //         targetFile2.delete();
    //     }
    // }

    /**
     * 如果文件已经存在执行的是覆盖操作，并且没有错误提示
     *
     * @throws IOException
     */
    @Test
    public void testCopyFile() throws IOException {
        File sourceFile = new File("E:\\CurtisProjects\\curtis-base\\curtis-guava\\src\\main\\resources\\testfile\\test.txt");
        File targetFile1 = new File("E:\\CurtisProjects\\curtis-base\\curtis-guava\\src\\main\\resources\\testfile\\test-1.txt");
        File targetFile2 = new File("E:\\CurtisProjects\\curtis-base\\curtis-guava\\src\\main\\resources\\testfile\\test-2.txt");
        Files.copy(sourceFile, targetFile1);

        FileOutputStream fileOutputStream = new FileOutputStream(targetFile2);
        Files.copy(sourceFile, fileOutputStream);
    }

    /**
     * 移动或重命名文件
     *
     * @throws IOException
     * @throws InterruptedException
     */
    @Test
    public void testMoveFile() throws IOException, InterruptedException {
        File sourceFile = new File("E:\\CurtisProjects\\curtis-base\\curtis-guava\\src\\main\\resources\\testfile\\test.txt");
        File targetFile = new File("E:\\CurtisProjects\\curtis-base\\curtis-guava\\src\\main\\resources\\testfile\\test-move.txt");
        Files.move(sourceFile, targetFile);
        TimeUnit.SECONDS.sleep(10);
        Files.move(targetFile, sourceFile);
    }

    /**
     * 读取文件名或后缀
     */
    @Test
    public void testGetNameAndExtension() {
        String fileName = "test.xlsx.txt";
        LOGGER.info("fileName -> {}", fileName);

        // 获取文件扩展名
        String fileExtension = Files.getFileExtension(fileName);
        LOGGER.info("fileExtension -> {}", fileExtension);

        // 获取文件名(除文件扩展名外的文件名)
        String nameWithoutExtension = Files.getNameWithoutExtension(fileName);
        LOGGER.info("nameWithoutExtension -> {}", nameWithoutExtension);
    }

    /**
     * 按行读取文件内容
     *
     * @throws IOException
     */
    @Test
    public void testReadFile() throws IOException {
        File sourceFile = new File("E:\\CurtisProjects\\curtis-base\\curtis-guava\\src\\main\\resources\\testfile\\test.txt");
        List<String> contentList = Files.readLines(sourceFile, Charsets.UTF_8);
        for (String content : contentList) {
            LOGGER.info(content);
        }
    }

    @Test
    public void testHash() throws IOException {
        File sourceFile = new File("E:\\CurtisProjects\\curtis-base\\curtis-guava\\src\\main\\resources\\testfile\\test.txt");

        // MD5消息摘要算法
        HashCode md5HashCode = Files.asByteSource(sourceFile).hash(Hashing.md5());
        String md5Str = md5HashCode.toString();
        LOGGER.info("md5Str -> {}", md5Str);

        // SHA256消息摘要算法
        HashCode sha256Hash = Files.asByteSource(sourceFile).hash(Hashing.sha256());
        String sha256Str = sha256Hash.toString();
        LOGGER.info("sha256Str -> {}", sha256Str);
    }


    @Test
    public void testWriteAndAppendFile() throws IOException {
        File newFile1 = new File("E:\\CurtisProjects\\curtis-base\\curtis-guava\\src\\main\\resources\\testfile\\newfile-1.txt");
        String str1 = "第一行";
        byte[] str1Bytes = str1.getBytes(Charsets.UTF_8);
        Files.write(str1Bytes, newFile1);
        String str2 = "第二行";
        byte[] str2Bytes = str2.getBytes(Charsets.UTF_8);
        Files.write(str2Bytes, newFile1);


        File newFile2 = new File("E:\\CurtisProjects\\curtis-base\\curtis-guava\\src\\main\\resources\\testfile\\newfile-2.txt");
        // 在文件尾部追加文本(不带回车)
        Files.asCharSink(newFile2, Charsets.UTF_8, FileWriteMode.APPEND).write("首行");
        Files.asCharSink(newFile2, Charsets.UTF_8, FileWriteMode.APPEND).write(System.getProperty("line.separator"));

        List<String> strList = Lists.newArrayList("第一行", "第二行", "第三行", "第四行");
        // 在文件尾部追加文本集合(使用系统行分隔符，末尾文本也会添加系统行分隔符)
        Files.asCharSink(newFile2, Charsets.UTF_8, FileWriteMode.APPEND).writeLines(strList);
        Files.asCharSink(newFile2, Charsets.UTF_8, FileWriteMode.APPEND).writeLines(strList, System.getProperty("line.separator"));
    }

    @Test
    public void testByte() throws IOException {
        File newFile1 = new File("E:\\CurtisProjects\\curtis-base\\curtis-guava\\src\\main\\resources\\testfile\\newfile-1.txt");
        String str1 = "第一行";
        byte[] str1Bytes = str1.getBytes(Charsets.UTF_8);
        Files.write(str1Bytes, newFile1);
        String str2 = "第二行";
        byte[] str2Bytes = str2.getBytes(Charsets.UTF_8);
        Files.write(str2Bytes, newFile1);

        Files.asByteSource(newFile1);


        File newFile2 = new File("E:\\CurtisProjects\\curtis-base\\curtis-guava\\src\\main\\resources\\testfile\\newfile-2.txt");
        // 在文件尾部追加文本(不带回车)
        Files.asCharSink(newFile2, Charsets.UTF_8, FileWriteMode.APPEND).write("首行");
        Files.asCharSink(newFile2, Charsets.UTF_8, FileWriteMode.APPEND).write(System.getProperty("line.separator"));

        List<String> strList = Lists.newArrayList("第一行", "第二行", "第三行", "第四行");
        // 在文件尾部追加文本集合(使用系统行分隔符，末尾文本也会添加系统行分隔符)
        Files.asCharSink(newFile2, Charsets.UTF_8, FileWriteMode.APPEND).writeLines(strList);
        Files.asCharSink(newFile2, Charsets.UTF_8, FileWriteMode.APPEND).writeLines(strList, System.getProperty("line.separator"));
    }
}
