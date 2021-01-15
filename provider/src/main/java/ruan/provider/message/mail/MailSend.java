package ruan.provider.message.mail;

import java.util.Date;
import java.util.Map;
import javax.mail.internet.MimeMessage;
import lombok.SneakyThrows;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import ruan.provider.common.ServerException;

/**
 * 邮件发送
 */
@Component
public class MailSend {

    @Autowired
    private JavaMailSender javaMailSender;


    /**
     * 发送简单邮件，不带图片和附件
     *
     * @param from
     * @param to
     * @param cc
     * @param body
     * @param title
     */
    @SneakyThrows
    public void send(Mail mail) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "utf-8");
        buildBasicInfo(mail, helper);
        if (MapUtils.isNotEmpty(mail.getImages()) || MapUtils.isNotEmpty(mail.getAttachments())) {

        } else {
            javaMailSender.send(mimeMessage);
        }
    }

    @SneakyThrows
    public void sendComplete(Mail mail) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "utf-8");

    }

    @SneakyThrows
    private void buildBasicInfo(Mail mail, MimeMessageHelper helper) {
        if (StringUtils.isEmpty(mail.getFrom())) {
            throw new ServerException("邮件来源不能为空！");
        }
        helper.setFrom(mail.getFrom());
        if (CollectionUtils.isEmpty(mail.getTo())) {
            throw new ServerException("目标地址不能为空！");
        }
        helper.setTo(mail.getTo().toArray(new String[mail.getTo().size()]));
        if (CollectionUtils.isNotEmpty(mail.getCc())) {
            helper.setCc(mail.getCc().toArray(new String[mail.getCc().size()]));
        }
        helper.setText(mail.getBody());
        helper.setSentDate(new Date());
        helper.setSubject(mail.getTitle());
    }

    private void buildImage(Mail mail, MimeMessageHelper helper) {
        Map<String, String> images = mail.getImages();
        if (MapUtils.isEmpty(images)) {
            return;
        }
        String body = mail.getBody();
        if (StringUtils.isNotBlank(body) && body.contains("img")) {

        }
    }
}
