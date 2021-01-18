package ruan.provider.util;

import cn.hutool.core.date.LocalDateTimeUtil;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import ruan.provider.common.ServerException;
import ruan.provider.entity.FileUpload;


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
    public static void uploadFile(MultipartFile file) {
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

    public static void multiFileUpload(HttpServletRequest request) {
        CommonsMultipartResolver resolver = new CommonsMultipartResolver(
                request.getSession().getServletContext());
        boolean multipart = resolver.isMultipart(request);
        if (multipart) {
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            List<MultipartFile> files = multipartRequest.getFiles("files");
            if (CollectionUtils.isEmpty(files)) {
                return;
            }
            files.forEach(f -> {
                String name = f.getOriginalFilename();
                String now = LocalDateTimeUtil.format(LocalDate.now(), "yyyy-MM-dd");
                name = name.substring(0, name.lastIndexOf(".")).concat(now)
                        .concat(name.substring(name.lastIndexOf("."), name.length()));
                File tmp = null;
                try {
                    tmp = ResourceUtils.getFile("classpath:\\tmp");
                } catch (Exception e) {
                    log.error("文件不存在:{}", e);
                }
                if (!tmp.exists()) {
                    return;
                }
                File newFile = new File(tmp.getAbsolutePath().concat("\\").concat(name));
                try (BufferedInputStream bi = new BufferedInputStream(f.getInputStream());
                        BufferedOutputStream bo = new BufferedOutputStream(
                                new FileOutputStream(newFile))) {
                    byte[] bytes = new byte[1024];
                    int length;
                    while ((length = bi.read(bytes)) > 0) {
                        bo.write(bytes);
                    }
                } catch (Exception e) {
                    log.error("多文件上传异常:{}", e);
                }
            });
        }
    }


    public static void breakpointResume(FileUpload fileUpload) {
        if (fileUpload == null) {
            return;
        }
        try {
            File tmp = ResourceUtils.getFile("classpath:\\tmp");
            if (!tmp.exists()) {
                tmp.mkdir();
            }
            MultipartFile uploadFile = fileUpload.getFile();
            String fileName = fileUpload.getFileName();
            File newFile = new File(tmp.getAbsolutePath().concat("\\").concat(fileName));
            if (!newFile.exists()) {
                newFile.createNewFile();
            }
            try (BufferedInputStream bi = new BufferedInputStream(
                    uploadFile.getInputStream()); RandomAccessFile raOutput = new RandomAccessFile(
                    newFile, "rw")) {
                raOutput.setLength(uploadFile.getSize());
                if (fileUpload.getCurrentShard() < fileUpload.getTotalShard() - 1) {
                    raOutput.seek(fileUpload.getCurrentShard() * fileUpload.getCurrentSize());
                } else {
                    raOutput.seek(fileUpload.getSize() - fileUpload.getCurrentSize());
                }
                int length;
                byte[] bytes = new byte[1024];
                while ((length = bi.read(bytes)) > -1) {
                    raOutput.write(bytes, 0, length);
                }
            } catch (Exception e) {
                log.error("", e);
            }
        } catch (Exception e) {
            log.error("", e);
        }
    }

    public void keepUpload(File sourceFile, File targetFile, long position) {
        try (RandomAccessFile raInput = new RandomAccessFile(sourceFile, "rw");
                RandomAccessFile raOutput = new RandomAccessFile(targetFile, "rw")) {
            raInput.seek(position);
            raOutput.seek(position);
            byte[] bytes = new byte[1024];
            int length;
            while ((length = raInput.read(bytes)) != -1){
                raOutput.write(bytes,0,length);
            }
        } catch (Exception e) {

        }
    }
}
