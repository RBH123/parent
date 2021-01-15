package ruan.provider.message.mail;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Mail implements Serializable {

    private static final long serialVersionUID = 7370583919951800432L;
    private String from;
    private List<String> to;
    private List<String> cc;
    private String body;
    private String title;
    private Map<String,String> images;
    private Map<String,String> attachments;
}
