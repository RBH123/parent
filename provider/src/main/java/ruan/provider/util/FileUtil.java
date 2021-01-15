package ruan.provider.util;

import cn.hutool.core.date.LocalDateTimeUtil;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;
import ruan.provider.common.ServerException;


@Slf4j
public class FileUtil {

    /**
     * 文件复制
     *
     * @param filePath
     */
    @SneakyThrows
    public static void copyFile(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            throw new ServerException("文件不存在，请重新上传！");
        }
        if (!file.isFile()) {
            throw new ServerException("当前选择不是文件，请重新选择！");
        }
        String name = file.getName();
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate now = LocalDate.now();
        String format = df.format(now);
        name = name.substring(0, name.lastIndexOf(".")).concat(format)
                .concat(name.substring(name.lastIndexOf(".", name.length())));
        File tmpFile = ResourceUtils.getFile("classpath:");
        String absolutePath = tmpFile.getAbsolutePath();
        if (!tmpFile.exists()) {
            return;
        }
        String concat = absolutePath.concat("\\tmp\\".concat(name));
        File newFile = new File(concat);
        if (!newFile.exists()) {
            newFile.createNewFile();
        }
        try (FileInputStream fi = new FileInputStream(file);
                FileOutputStream fo = new FileOutputStream(newFile)) {
            FileChannel inputChannel = fi.getChannel();
            FileChannel outputChannel = fo.getChannel();
            int length = 2097152;
            while (true) {
                if (inputChannel.size() == inputChannel.position()) {
                    break;
                }
                if (inputChannel.size() - inputChannel.position() < length) {
                    length = (int) (inputChannel.size() - inputChannel.position());
                }
                ByteBuffer byteBuffer = ByteBuffer.allocateDirect(length);
                inputChannel.read(byteBuffer);
                byteBuffer.flip();
                outputChannel.write(byteBuffer);
                outputChannel.force(false);
            }
        } catch (Exception e) {
            log.error("", e);
        }
    }

    @SneakyThrows
    public void uploadFile(MultipartFile file) {
        String name = file.getName();
        String now = LocalDateTimeUtil.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        name = name.substring(0, name.lastIndexOf(".")).concat(now)
                .concat(name.substring(name.lastIndexOf("."), name.length()));
        ClassPathResource resource = new ClassPathResource("classpath:");
        File tmpFile = resource.getFile();
        if (!tmpFile.exists()) {
            return;
        }
        File newFile = new File(tmpFile.getAbsolutePath().concat("\\tmp\\".concat(name)));
        if (!newFile.exists()) {
            newFile.createNewFile();
        }
        try (BufferedInputStream bi = new BufferedInputStream(
                file.getInputStream()); BufferedOutputStream bo = new BufferedOutputStream(
                new FileOutputStream(newFile))) {
            byte[] bytes = new byte[1024];
            int length;
            while ((length = bi.read(bytes)) > 0) {
                bo.write(bytes);
            }
        } catch (Exception e) {
            log.error("文件上传异常:{}", e);
        }
    }
}
