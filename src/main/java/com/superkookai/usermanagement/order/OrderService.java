package com.superkookai.usermanagement.order;

import com.superkookai.usermanagement.mail.GoogleMailService;

public class OrderService {
    private GoogleMailService googleMailService;

    public OrderService(){
        this.googleMailService = new GoogleMailService();
        this.googleMailService.setUrl("mail.google.com");
        this.googleMailService.setPort("42");
    }

    public void createOrder(){
        //Created Order

        //Send mail
        googleMailService.sendEmail("marketing@gmail.com", "Order created.");
    }
}
