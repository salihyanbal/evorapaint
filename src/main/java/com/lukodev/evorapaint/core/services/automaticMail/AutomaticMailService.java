package com.lukodev.evorapaint.core.services.automaticMail;

import com.lukodev.evorapaint.core.utilities.results.DataResult;
import com.lukodev.evorapaint.core.utilities.results.Result;
import com.lukodev.evorapaint.entities.concretes.*;

public interface AutomaticMailService {

    DataResult<Integer> sendVerificationMail(User user);
    Result sendOrderStatusMail(Order order, Customer customer, OrderStatus orderStatus);
    Result sendOrderStatusMailWithShipment(Order order, Customer customer, OrderStatus orderStatus, Shipment shipment);
}
