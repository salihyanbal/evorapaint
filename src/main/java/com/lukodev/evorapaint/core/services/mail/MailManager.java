package com.lukodev.evorapaint.core.services.mail;

import com.lukodev.evorapaint.core.utilities.results.Result;
import com.lukodev.evorapaint.core.utilities.results.SuccessResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Component
public class MailManager implements MailService{

    private Environment env;
    private JavaMailSender emailSender;
    private SpringTemplateEngine templateEngine;


    @Autowired
    public MailManager(Environment env, JavaMailSender emailSender, SpringTemplateEngine templateEngine) {
        this.env = env;
        this.emailSender = emailSender;
        this.templateEngine = templateEngine;
    }

    public Result sendSimpleMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(env.getProperty("spring.mail.username"));
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
        return new SuccessResult(to + " mail adresine mail gönderildi.");
    }

    public Result sendMimeMessage(String to, String subject, String templateName, Map<String, Object> props) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());
        Context context = new Context();
        context.setVariables(props);
        String html = templateEngine.process(templateName, context);
        helper.setTo(to);
        helper.setText(html, true);
        helper.setSubject(subject);
        helper.setFrom(env.getProperty("spring.mail.username"));
        emailSender.send(message);
        return new SuccessResult(to + " mail adresine mail gönderildi.");
    }
}
