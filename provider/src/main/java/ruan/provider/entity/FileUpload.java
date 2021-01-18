package ruan.provider.entity;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FileUpload implements Serializable {

    private static final long serialVersionUID = 6132388450628328199L;
    private int totalShard;
    private int currentShard;
    private long currentSize;
    private long size;
    private String fileName;
    private MultipartFile file;
}
