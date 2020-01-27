package com.achan.exam.util.file;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;
import java.util.stream.Stream;

/**
 * 文件操作
 * @author Achan
 * @date 2020/1/17
 */
@Slf4j
public class FileUtil {

    /**
     * 向filePath路径文件写入context
     * @param context 需要写入的内容
     * @param filePath 指定文件路径
     * @throws IOException
     */
    public static void writeToFile(String context, String filePath) throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            log.debug("file '{}' not exists, create it.", filePath);
            file.createNewFile();
        }
        OutputStream outputStream = new FileOutputStream(file);
        outputStream.write(context.getBytes());
        outputStream.flush();
        outputStream.close();
    }

    /**
     * 读取指定路径文本文件
     * @param path 路径
     * @return 文本文件内容
     * @throws IOException
     */
    public static String readTextFile(String path) throws IOException {
        File file = new File(path);
        if (!file.exists()) {
            log.debug("file '{}' not exists!", path);
            return null;
        }
        return readTextFile(file);
    }

    /**
     * 从输入流中读取文本文件
     * @param inputStream 文本文件输入流
     * @return 文本文件内容
     * @throws IOException
     */
    public static String readTextFile(InputStream inputStream) throws IOException {
        StringBuilder builder = new StringBuilder();
        byte[] b = new byte[1024];
        int len = 0;
        while ((len = inputStream.read(b, 0, len)) != -1) {
            builder.append(new String(b));
        }
        return builder.toString();
    }

    /**
     * 读取文本文件
     * @param file 需要读取的文件
     * @return 文本文件的内容
     * @throws IOException
     */
    public static String readTextFile(File file) throws IOException {
        StringBuilder builder = new StringBuilder();
        Path filePath = file.toPath();
        // try-with-resources
        try (Stream<String> lines = Files.lines(filePath)) {
            lines.forEach(line -> builder.append(line));
        }
        return builder.toString();
    }

    /**
     * 接收web文件并保存到指定目录
     * @param file 需要保存的web文件
     * @param folder 保存到folder文件夹下
     * @param fileName 新文件的名字
     * @return 所保存的新file对象
     * @throws IOException
     */
    public static File transferTo(MultipartFile file, File folder, String fileName) throws IOException {
        if (!folder.isDirectory() || !folder.exists()) {
            folder.mkdirs();
        }
        File newFile = new File(folder, fileName);
        file.transferTo(newFile);
        log.debug("data file '{}' save in '{}'", file.getOriginalFilename(), newFile.toString());
        return newFile;
    }

    /**
     * 接收web文件并保存到指定目录，新文件使用uuid命名
     * @param file 需要保存的web文件
     * @param folder 保存到folder文件夹下
     * @return 所保存的新file对象
     * @throws IOException
     */
    public static File transferTo(MultipartFile file, File folder) throws IOException {
        String oldName = file.getOriginalFilename();
        String uuid = UUID.randomUUID().toString();
        String newName = uuid + oldName.substring(oldName.lastIndexOf('.'));
        return transferTo(file, folder, newName);
    }
}
