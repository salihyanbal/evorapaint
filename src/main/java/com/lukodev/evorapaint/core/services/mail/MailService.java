package com.lukodev.evorapaint.core.services.mail;

import com.lukodev.evorapaint.core.utilities.results.Result;

import javax.mail.MessagingException;
import java.util.Map;

public interface MailService {

    Result sendSimpleMessage(String to, String subject, String text);
    Result sendMimeMessage(String to, String subject, String templateName, Map<String, Object> props) throws MessagingException;

}
