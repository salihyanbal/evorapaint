package com.lukodev.evorapaint.core.services.automaticMail;

import com.lukodev.evorapaint.core.services.mail.MailService;
import com.lukodev.evorapaint.core.utilities.results.DataResult;
import com.lukodev.evorapaint.core.utilities.results.Result;
import com.lukodev.evorapaint.core.utilities.results.SuccessDataResult;
import com.lukodev.evorapaint.core.utilities.results.SuccessResult;
import com.lukodev.evorapaint.entities.concretes.*;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Component
public class AutomaticMailManager implements AutomaticMailService{

    private MailService mailService;

    public AutomaticMailManager(MailService mailService) {
        this.mailService = mailService;
    }

    public DataResult<Integer> sendVerificationMail(User user){
        Random random = new Random();
        int verificationCode = random.nextInt(10000) + 100000;
        Map<String, Object> props = new HashMap<String, Object>();
        props.put("firstName", user.getFirstName());
        props.put("lastName", user.getLastName());
        props.put("verificationCode", verificationCode);
        try {
            this.mailService.sendMimeMessage(user.getEmail(), "[Evorapaint] Üyelik doğrulama e-postası", "email-verification-template", props);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return new SuccessDataResult<>(verificationCode);
    }

    public Result sendOrderStatusMail(Order order, Customer customer, OrderStatus orderStatus){
        Map<String, Object> props = new HashMap<>();
        boolean isShipped = order.getOrderStatus().getId() == 4;
        props.put("firstName", customer.getFirstName());
        props.put("lastName", customer.getLastName());
        props.put("orderId", order.getId());
        props.put("orderStatus", orderStatus.getName());
        props.put("estimatedDeliveryDate", order.getEstimatedDeliveryDate());
        props.put("isShipped", false);
        try {
            this.mailService.sendMimeMessage(customer.getEmail(), "[Evorapaint] Sipariş durumu", "order-status-template", props);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return new SuccessResult("Sipariş bilgilendirme maili gönderildi.");
    }

    public Result sendOrderStatusMailWithShipment(Order order, Customer customer, OrderStatus orderStatus, Shipment shipment){
        Map<String, Object> props = new HashMap<>();
        props.put("firstName", customer.getFirstName());
        props.put("lastName", customer.getLastName());
        props.put("orderId", order.getId());
        props.put("orderStatus", orderStatus.getName());
        props.put("estimatedDeliveryDate", order.getEstimatedDeliveryDate());
        props.put("isShipped", true);
        props.put("shipmentTrackingLink", shipment.getTrackingLink());
        try {
            this.mailService.sendMimeMessage(customer.getEmail(), "[Evorapaint] Sipariş durumu", "order-status-template", props);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return new SuccessResult("Sipariş bilgilendirme maili gönderildi.");
    }

}
